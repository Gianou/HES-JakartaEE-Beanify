package ch.hevs.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import ch.hevs.businessobject.Artist;
import ch.hevs.businessobject.Song;

public class PersistanceTest {

	@Test
	public void test() {
		
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
			
			em.persist(s);
			em.persist(a);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			/*
			try {
				tx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
			*/
		}
		
	}
	
}
