package ch.hevs.beanifyservice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ch.hevs.businessobject.Artist;
import ch.hevs.businessobject.Song;

import javax.ejb.Stateless;

@Stateless
public class PopulateBean implements Populate{

	@Override
	public String populate() {
		EntityTransaction tx = null;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("BeanifyPU");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Song s = new Song();
			s.setSongTitle("sucepute");
			s.setUrl("URL");
			
			Artist a = new Artist();
			a.setArtistName("Perturbator");
			Artist b = new Artist();
			b.setArtistName("Antho");
			Artist c = new Artist();
			c.setArtistName("David");
			
			em.persist(s);
			em.persist(a);
			em.persist(b);
			em.persist(c);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "showArtists";
		
	}

}
