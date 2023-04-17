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
			al1songs.add(new Song("Welcome Back", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al1songs.add(new Song("Perturbators's Theme", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al1songs.add(new Song("Raw Power", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al1songs.add(new Song("Future Club", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al1songs.add(new Song("War Against Machines", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al1songs.add(new Song("Hard Wired", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al1songs.add(new Song("She Is Young, She is Next", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al1songs.add(new Song("Humans Are Such Easy Prey", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al1songs.add(new Song("Minuit", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al1songs.add(new Song("Satanic Rites", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al1songs.add(new Song("Complete Domination (feat. Carpenter Brut", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al1songs.add(new Song("Last Kiss", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al1songs.add(new Song("Dangerous Days", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			for (Song song : al1songs) {
				song.addArtist(a);
				al1.addSong(song);
			}
			a.addAlbum(al1);
			
			Album al2 = new Album("Nocturne City", "02-08-2012");
			List<Song> al2songs = new ArrayList<Song>();
			al2songs.add(new Song("Intro (The Journey)", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al2songs.add(new Song("Welcome to Nocturne City", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al2songs.add(new Song("Fantasy", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al2songs.add(new Song("Night Business", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al2songs.add(new Song("There Is No Love Highway", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			al2songs.add(new Song("Vengeance", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
			for (Song song : al2songs) {
				song.addArtist(a);
				al2.addSong(song);
			}
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
