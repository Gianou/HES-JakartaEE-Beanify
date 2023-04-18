package ch.hevs.beanifyservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

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
		
		return (Artist) em.createQuery("From Artist a WHERE a.id =: id")
				.setParameter("id", id).getSingleResult();
	}

	@Override
	public List<Artist> getArtists() {
		return em.createQuery("SELECT a FROM Artist a").getResultList();
	}

	@Override
	public Subscriber getSubscriberByID(Long id) {
		return (Subscriber) em.createQuery("From Subscriber s WHERE s.id =: id").setParameter("id", id).getSingleResult();
		
	}

	@Override
	public Song getSongById(Long id) {
		return (Song) em.createQuery("From Song s WHERE s.id =: id").setParameter("id", id).getSingleResult();
		
	}

	@Override
	public void addLikedSongToSubscriber(Subscriber sub, Song song) {
		Subscriber sub2 = em.merge(sub);
		Song song2 = em.merge(song);
		sub2.addLikedSong(song2);
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
	public List<Album> loadArtistAlbums(Artist artist) {
		Artist artist2 = em.merge(artist);
		artist2.getAlbums();
		return em.createQuery("FROM Album al Where artist_id =: artid").setParameter("artid", artist2.getId()).getResultList();
	}


	@Override
	public void removeLikedSongToSubscriber(Subscriber sub, Song song) {
		Subscriber sub2 = em.merge(sub);
		Song song2 = em.merge(song);
		sub2.deleteLikedSong(song2);	
	}
	
}
