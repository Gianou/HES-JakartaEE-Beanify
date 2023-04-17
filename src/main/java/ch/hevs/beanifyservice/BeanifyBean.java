package ch.hevs.beanifyservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;

@Stateless
public class BeanifyBean implements Beanify{
	
	@PersistenceContext(name = "BeanifyPU", type=PersistenceContextType.TRANSACTION)
	private EntityManager em;

	public Artist getArtist(String artistName) {
		
		return (Artist) em.createQuery("From Artist a WHERE a.artistName =: artistName").setParameter("artistName", artistName).getSingleResult();
	}

	public List<Artist> getArtists() {
		return em.createQuery("FROM Artist").getResultList();
	}

	
	@Override
	public List<Album> getAlbums(float id) {
		// TO DO add a where id = artist Id or something
		return em.createQuery("From Album").getResultList();
		
	}
	

}
