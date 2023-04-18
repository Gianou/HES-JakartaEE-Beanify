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
	 
	private List<Subscriber> subscribers;
	private List<String> subscriberNames;
	private String selectedSubscriberName;
	private Subscriber selectedSubscriber;
	private Long selectedSubscriberId;
	
	private Subscriber currentSubscriber;

	

	@PostConstruct
    public void initialize(){
		try {
	    	InitialContext ctx = new InitialContext();
			beanify = (Beanify) ctx.lookup("java:global/Beanify-0.0.1-SNAPSHOT/BeanifyBean!ch.hevs.beanifyservice.Beanify");
				
			// get subscriber
			//selectedSubscriber = beanify.getSubscriberByEmail("name.surname@domain.com");
			
			subscribers = beanify.getSubscribers();
			/*
			selectedSubscriberName = subscribers.get(0).getFirstName(); // Default value
			subscriberNames = new ArrayList<String>();
			for(Subscriber sub : subscribers) {
				subscriberNames.add(sub.getFirstName());
				System.out.println("Test");
			}
			*/
			
	    	// get artists and set first as default
			artists = beanify.getArtists();
			// TO DO Check this
			selectedArtistName = artists.get(0).getArtistName(); // Default value
			this.artistNames = new ArrayList<String>();
			for (Artist artist : artists) {
				this.artistNames.add(artist.getArtistName());
			}
		
		}catch(NamingException e) {
			e.printStackTrace();
		}
    }
	
	public void addToSubLikedSong(Long id) {
		Song likedSong = beanify.getSongById(id);
		
		// TO DO Check if test works
		//Subscriber sub = beanify.getSubscriberByEmail("name.surname@domain.com");
		selectedSubscriber  = beanify.getSubscriberByEmail(selectedSubscriberId);
		
		if(selectedSubscriber.getLikedSongs().contains(likedSong.getId())) {
		//if(true) {
			System.out.println("Retourne chez toi");
			return;
		}
		else {
			beanify.addLikedSongToSubscriber(selectedSubscriber, likedSong);
		}
		
	}
    
	// Method called when button clicked
	public String selectArtist() {
		setSelectedArtist(beanify.getArtist(selectedArtistName)); // Is there a way to get the Artist rather then the ArtistName?
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

	public String myLikedSongs() {
		// without "reloading" the currentSubscriber via the bean, the subscriber's list of song is not updated in the entity
		//currentSubscriber = beanify.getSubscriberByEmail("name.surname@domain.com");
		return "showLikedSongs";
	}
	
	public Long getSelectedSubscriberId() {
		return selectedSubscriberId;
	}

	public void setSelectedSubscriberId(Long selectedSubscriberId) {
		this.selectedSubscriberId = selectedSubscriberId;
	}

	public Subscriber getCurrentSubscriber() {
		return currentSubscriber;
	}

	public void setCurrentSubscriber(Subscriber currentSubscriber) {
		this.currentSubscriber = currentSubscriber;
	}

	public List<Subscriber> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<Subscriber> subscribers) {
		this.subscribers = subscribers;
	}

	public List<String> getSubscriberNames() {
		return subscriberNames;
	}

	public void setSubscriberNames(List<String> subscriberNames) {
		this.subscriberNames = subscriberNames;
	}

	public String getSelectedSubscriberName() {
		return selectedSubscriberName;
	}

	public void setSelectedSubscriberName(String selectedSubscriberName) {
		this.selectedSubscriberName = selectedSubscriberName;
	}

	public Subscriber getSelectedSubscriber() {
		return selectedSubscriber;
	}

	public void setSelectedSubscriber(Subscriber selectedSubscriber) {
		this.selectedSubscriber = selectedSubscriber;
	}

}


