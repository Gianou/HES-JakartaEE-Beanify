package ch.hevs.beanifyservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;
import ch.hevs.businessobject.Song;
import ch.hevs.businessobject.Subscriber;

@Stateless
public class BeanifyBean implements Beanify{
	
	@PersistenceContext(name = "BeanifyPU", type=PersistenceContextType.TRANSACTION)
	private EntityManager em;

	@Override
	public Artist getArtistByID(Long id) {
		return em.find(Artist.class, id);
	}
	
	@Override
	public Subscriber getSubscriberByID(Long id) {
		return em.find(Subscriber.class, id);	
	}
	
	@Override
	public Song getSongById(Long id) {
		return em.find(Song.class, id);	
	}

	@Override
	public List<Artist> getArtists() {
		return em.createQuery("SELECT a FROM Artist a").getResultList();
	}

	@Override
	public List<Long> getSubscriberLikedSongs(Subscriber sub) {
		Subscriber sub2 = em.merge(sub);
		Long subID = sub2.getId();
		
		return em.createQuery("SELECT so.id FROM Subscriber s JOIN s.likedSongs so WHERE s.id =: subconnected").setParameter("subconnected", sub.getId()).getResultList();
	}

	@Override
	public List<Subscriber> getSubscribers() {
		return em.createQuery("SELECT sub FROM Subscriber sub").getResultList();
	}
	
	@Override
	public List<Song> loadLikedSongs(Subscriber sub) {
		Subscriber sub2 = em.merge(sub);
		
		return em.createQuery("SELECT so FROM Subscriber s JOIN s.likedSongs so WHERE s.id =: subconnected").setParameter("subconnected", sub.getId()).getResultList();
	}

	@Override
	public List<Album> loadArtistAlbums(Artist artist) {
		Artist artist2 = em.merge(artist);
		//return artist2.getAlbums(); // Does not retrieve the albums
		return em.createQuery("FROM Album al Where artist_id =: artid").setParameter("artid", artist2.getId()).getResultList();
	}

	@Override
	public void addLikedSongToSubscriber(Subscriber sub, Song song) {
		Subscriber sub2 = em.merge(sub);
		Song song2 = em.merge(song);
		sub2.addLikedSong(song2);
	}

	@Override
	public void removeLikedSongToSubscriber(Subscriber sub, Song song) {
		Subscriber sub2 = em.merge(sub);
		Song song2 = em.merge(song);
		sub2.deleteLikedSong(song2);	
	}
	
}
