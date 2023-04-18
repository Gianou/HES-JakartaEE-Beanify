package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Song")
public class Song {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name="title")
	private String songTitle;
	
	@Column(name="url")
	private String url;
	
	@ManyToOne
	private Album album;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Artist> artists;
	
	//Helper method
	public void addArtist(Artist artist) {
		this.artists.add(artist);
	}

	//CONSTRUCTOR
	public Song() {
		super();
		artists = new HashSet<Artist>();
	}
	
	public Song(String title, String url) {
		super();
		this.songTitle = title;
		this.url = url;
		artists = new HashSet<Artist>();
	}
	
	// GETTER SETTER

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getSongTitle() {
		return songTitle;
	}


	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Album getAlbum() {
		return album;
	}


	public void setAlbum(Album album) {
		this.album = album;
	}


	public Set<Artist> getArtists() {
		return artists;
	}


	public void setArtists(Set<Artist> artists) {
		this.artists = artists;
	}
	
}
