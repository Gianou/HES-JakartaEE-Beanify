package ch.hevs.beanifyservice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ch.hevs.businessobject.Album;
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
			
			Album al1 = new Album();
			al1.setAlbumTitle("Title1");
			al1.setReleaseDate("1999-09-09");
			
			Album al2 = new Album();
			al2.setAlbumTitle("Title2");
			al2.setReleaseDate("1992-09-09");
			
			//al1.setArtist(a);
			//al2.setArtist(a);
			a.addAlbum(al1);
			a.addAlbum(al2);
			
			em.persist(s);
			em.persist(a);
			em.persist(b);
			em.persist(c);
			em.persist(al1);
			em.persist(al2);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "showArtists";
		
	}

}
