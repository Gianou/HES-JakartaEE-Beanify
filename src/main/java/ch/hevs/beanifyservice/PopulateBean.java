package ch.hevs.beanifyservice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;
import ch.hevs.businessobject.Song;

import java.util.ArrayList;
import java.util.List;

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
			
			
			Artist b = new Artist();
			b.setArtistName("Antho");
			Artist c = new Artist();
			c.setArtistName("David");
			
			
			// PERTURBATOR
			Artist a = new Artist();
			a.setArtistName("Perturbator");
			
			Album al1 = new Album("Dangerous Days", "17-06-2014");
			List<Song> al1songs = new ArrayList<Song>();
			al1songs.add(new Song("Welcome Back"));
			al1songs.add(new Song("Perturbators's Theme"));
			al1songs.add(new Song("Raw Power"));
			al1songs.add(new Song("Future Club"));
			al1songs.add(new Song("War Against Machines"));
			al1songs.add(new Song("Hard Wired"));
			al1songs.add(new Song("She Is Young, She is Next"));
			al1songs.add(new Song("Humans Are Such Easy Prey"));
			al1songs.add(new Song("Minuit"));
			al1songs.add(new Song("Satanic Rites"));
			al1songs.add(new Song("Complete Domination (feat. Carpenter Brut"));
			al1songs.add(new Song("Last Kiss"));
			al1songs.add(new Song("Dangerous Days"));
			for (Song song : al1songs) {
				al1.addSong(song);
			}
			
			
			
			Album al2 = new Album("Nocturne City", "02-08-2012");
			List<Song> al2songs = new ArrayList<Song>();
			al2songs.add(new Song("Intro (The Journey)"));
			al2songs.add(new Song("Welcome to Nocturne City"));
			al2songs.add(new Song("Fantasy"));
			al2songs.add(new Song("Night Business"));
			al2songs.add(new Song("There Is No Love Highway"));
			al2songs.add(new Song("Vengeance"));
			for (Song song : al2songs) {
				al2.addSong(song);
			}
			
			
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
