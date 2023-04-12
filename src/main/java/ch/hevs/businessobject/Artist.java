package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Artist")
public class Artist extends Person {

	@Column(name="artistName")
	private String artistName;
	
	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
	private Set<Album> albums;

	@ManyToMany
	private Set<Song> songs;
	
	
	// GETTER SETTER
	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public Set<Song> getSongs() {
		return songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}
	
	// CONSTRUCTOR
	public Artist(long id, String firstName, String lastName) {
		super(id, firstName, lastName);
		albums = new HashSet<Album>();
		songs = new HashSet<Song>();
	}
	
	public Artist()
	{
		super();
		albums = new HashSet<Album>();
		songs = new HashSet<Song>();
	}
	
}
