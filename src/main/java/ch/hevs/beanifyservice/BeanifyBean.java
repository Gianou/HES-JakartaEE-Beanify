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

	public Artist getArtist(String artistName) {
		
		return (Artist) em.createQuery("From Artist a WHERE a.artistName =: artistName").setParameter("artistName", artistName).getSingleResult();
	}

	public List<Artist> getArtists() {
		return em.createQuery("SELECT a FROM Artist a").getResultList();
	}

	@Override
	public Subscriber getSubscriberByEmail(String email) {
		return (Subscriber) em.createQuery("From Subscriber s WHERE s.email =: email").setParameter("email", email).getSingleResult();
		
	}

	@Override
	public Song getSongById(Long id) {
		return (Song) em.createQuery("From Song s WHERE s.id =: id").setParameter("id", id).getSingleResult();
		
	}

	public void addLikedSongToSubscriber(Subscriber sub, Song song) {
		Subscriber sub2 = em.merge(sub);
		Song song2 = em.merge(song);
		sub2.addLikedSong(song2);
	}

	@Override
	public List<Song> getSubscriberLikedSongs(Subscriber sub) {
		return em.createQuery("From Subscriber_Song").getResultList();
	}
	
}
