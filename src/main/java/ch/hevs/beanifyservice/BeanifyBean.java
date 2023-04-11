package ch.hevs.beanifyservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import ch.hevs.businessobject.Artist;

@Stateless
public class BeanifyBean implements Beanify{
	
	@PersistenceContext(name = "BeanifyPU", type=PersistenceContextType.TRANSACTION)
	private EntityManager em;

	@Override
	public Artist getArtist(String artistName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Artist> getArtists() {
		return em.createQuery("FROM Artist").getResultList();
	}

}
