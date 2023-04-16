package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.beanifyservice.Beanify;
import ch.hevs.beanifyservice.BeanifyBean;
import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;

public class ArtistBean
{
	private List<Artist> artists;
	private List<String> artistNames;
	private Beanify beanify;
	private String selectedArtistName;
	private Artist selectedArtist;
	private List<Album> selectedArtistAlbums;
	private List<String> selectedArtistAlbumsNames;
	
	
	
	@PostConstruct
    public void initialize(){
		try {
    	InitialContext ctx = new InitialContext();
		beanify = (Beanify) ctx.lookup("java:global/Beanify-0.0.1-SNAPSHOT/BeanifyBean!ch.hevs.beanifyservice.Beanify");
			
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
    
	public String selectArtist() {
		
		setSelectedArtist(beanify.getArtist(selectedArtistName));
		selectedArtistAlbums = new ArrayList<Album>();
		
		selectedArtistAlbums = beanify.getAlbums(selectedArtist.getId());
		selectedArtistAlbumsNames = new ArrayList<String>();
		for (Album album : selectedArtistAlbums) {
			this.selectedArtistAlbumsNames.add(album.getAlbumTitle());
		}
		
		return "showAlbums";
	}

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

	public List<Album> getSelectedArtistAlbums() {
		return selectedArtistAlbums;
	}

	public void setSelectedArtistAlbums(List<Album> selectedArtistAlbums) {
		this.selectedArtistAlbums = selectedArtistAlbums;
	}

	public List<String> getSelectedArtistAlbumsNames() {
		return selectedArtistAlbumsNames;
	}

	public void setSelectedArtistAlbumsNames(List<String> selectedArtistAlbumsNames) {
		this.selectedArtistAlbumsNames = selectedArtistAlbumsNames;
	}

}


