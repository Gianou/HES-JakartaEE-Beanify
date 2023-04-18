package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.beanifyservice.Beanify;
import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;
import ch.hevs.businessobject.Song;
import ch.hevs.businessobject.Subscriber;

@ManagedBean
public class ArtistBean
{
	private Beanify beanify;
	
	private List<Artist> artists;
	private Artist selectedArtist;
	private Long selectedArtistId;
	 	
	private List<Subscriber> subscribers;
	private Subscriber selectedSubscriber;
	private Long selectedSubscriberId;
	
	private List<Song> likedSongs;
	
	private List<Album> albums;

	@PostConstruct
    public void initialize(){
		try {
	    	InitialContext ctx = new InitialContext();
			beanify = (Beanify) ctx.lookup("java:global/Beanify-0.0.1-SNAPSHOT/BeanifyBean!ch.hevs.beanifyservice.Beanify");
				
			// GET SUBSCRIBERS and set a default value
			subscribers = beanify.getSubscribers();
			selectedSubscriberId = subscribers.get(0).getId();
			
	    	// GET ARTISTS and set a default value
			artists = beanify.getArtists();
			selectedArtistId = artists.get(0).getId();
		
		}catch(NamingException e) {
			e.printStackTrace();
		}
    }
	
	public void addToSubLikedSong(Long id) {
		Song likedSong = beanify.getSongById(id);
		selectedSubscriber = beanify.getSubscriberByID(selectedSubscriberId);
		List <Long> alreadyLiked = new ArrayList<Long>();
		alreadyLiked = beanify.getSubscriberLikedSongs(selectedSubscriber);
		
		if(alreadyLiked.contains(likedSong.getId())) {
			return;
		}
		else {
			beanify.addLikedSongToSubscriber(selectedSubscriber, likedSong);
		}
		
	}
	
	public void deleteSelectedSong(Long id) {
		Song likedSong = beanify.getSongById(id);
		selectedSubscriber = beanify.getSubscriberByID(selectedSubscriberId);
		beanify.removeLikedSongToSubscriber(selectedSubscriber, likedSong);
		likedSongs = beanify.loadLikedSongs(selectedSubscriber);
	}
	
	
	public void reloadSubscriber(boolean mustLoadLikedSong) {
		selectedSubscriber  = beanify.getSubscriberByID(selectedSubscriberId);
		if(mustLoadLikedSong) {
			likedSongs = beanify.loadLikedSongs(selectedSubscriber);
		}
		
	}
    
	
	public String selectArtistAndDisplayAlbums() {
		//Fetch the albums
		selectedArtist = beanify.getArtistByID(selectedArtistId);
		albums = beanify.loadArtistAlbums(selectedArtist);
		//albums = selectedArtist.getAlbums(); // Does not retrieve the albums
		return "showAlbums";
	}

	public String myLikedSongs() {
		selectedSubscriber  = beanify.getSubscriberByID(selectedSubscriberId);
		likedSongs = beanify.loadLikedSongs(selectedSubscriber);
		return "showLikedSongs";
	}
	
	// Getter and Setter
	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public Beanify getBeanify() {
		return beanify;
	}

	public void setBeanify(Beanify beanify) {
		this.beanify = beanify;
	}

	public Long getSelectedArtistId() {
		return selectedArtistId;
	}

	public void setSelectedArtistId(Long selectedArtistId) {
		this.selectedArtistId = selectedArtistId;
	}

	public Artist getSelectedArtist() {
		return selectedArtist;
	}

	public void setSelectedArtist(Artist selectedArtist) {
		this.selectedArtist = selectedArtist;
	}

	
	public Long getSelectedSubscriberId() {
		return selectedSubscriberId;
	}

	public void setSelectedSubscriberId(Long selectedSubscriberId) {
		this.selectedSubscriberId = selectedSubscriberId;
	}

	public List<Subscriber> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<Subscriber> subscribers) {
		this.subscribers = subscribers;
	}

	public Subscriber getSelectedSubscriber() {
		return selectedSubscriber;
	}

	public void setSelectedSubscriber(Subscriber selectedSubscriber) {
		this.selectedSubscriber = selectedSubscriber;
	}
	
	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public List<Song> getLikedSongs() {
		return likedSongs;
	}

	public void setLikedSongs(List<Song> likedSongs) {
		this.likedSongs = likedSongs;
	}

}


