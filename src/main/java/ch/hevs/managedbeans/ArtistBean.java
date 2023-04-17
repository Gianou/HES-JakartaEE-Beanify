package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.beanifyservice.Beanify;
import ch.hevs.beanifyservice.BeanifyBean;
import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;
import ch.hevs.businessobject.Song;
import ch.hevs.businessobject.Subscriber;

public class ArtistBean
{
	private List<Artist> artists;
	private List<String> artistNames;
	private Beanify beanify;
	private String selectedArtistName;
	private Artist selectedArtist;
	private Long likedSongId;
	private Subscriber currentSubscriber;
	
	
	public Long getLikedSongId() {
		return likedSongId;
	}
	
	public void setLikedSongId(Long likedSongId) {
		this.likedSongId = likedSongId;
	}

	
	public void addToSubLikedSong(Long id) {
		//System.out.println("ça set la chanson likée");
		likedSongId = id;
		Song likedSong = beanify.getSongById(likedSongId);
		//System.out.println(likedSong.getSongTitle());
		//System.out.println(currentSubscriber.getEmail());
		//Check if already in liked songs
		if(currentSubscriber.getLikedSongs().contains(likedSong)) {
		//if(true) {
			System.out.println("Retourne chez toi");
			return;
		}
		else {
			currentSubscriber.addLikedSong(likedSong);
			//System.out.println("Ajouté au sub");
			beanify.persistSub(currentSubscriber);
			//this.likedSong = likedSong;
		}
		
	}

	@PostConstruct
    public void initialize(){
		try {
    	InitialContext ctx = new InitialContext();
		beanify = (Beanify) ctx.lookup("java:global/Beanify-0.0.1-SNAPSHOT/BeanifyBean!ch.hevs.beanifyservice.Beanify");
			
		// get subscriber
		currentSubscriber = beanify.getSubscriberByEmail("name.surname@domain.com");
		
    	// get clients
		artists = beanify.getArtists();
		this.artistNames = new ArrayList<String>();
		for (Artist artist : artists) {
			this.artistNames.add(artist.getArtistName());
		}
		
		}catch(NamingException e) {
			e.printStackTrace();
		}
    }
    
	// Method called when button clicked
	public String selectArtist() {
		setSelectedArtist(beanify.getArtist(selectedArtistName)); // Is there a way to get the Artist rather then the ArtistName?
		System.out.println("Ca select l'artiste!");
		return "showAlbums";
	}
	
	// Getter and Setter
	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public List<String> getArtistNames() {
		return artistNames;
	}

	public void setArtistNames(List<String> artistNames) {
		this.artistNames = artistNames;
	}

	public Beanify getBeanify() {
		return beanify;
	}

	public void setBeanify(Beanify beanify) {
		this.beanify = beanify;
	}

	public String getSelectedArtistName() {
		return selectedArtistName;
	}

	public void setSelectedArtistName(String selectedArtistName) {
		System.out.println("Ca change d'artiste");
		this.selectedArtistName = selectedArtistName;
	}

	public Artist getSelectedArtist() {
		return selectedArtist;
	}

	public void setSelectedArtist(Artist selectedArtist) {
		this.selectedArtist = selectedArtist;
	}

	

}


