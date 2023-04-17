package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Artist")
public class Artist extends Person {

	@Column(name="artistName", unique=true)
	private String artistName;
	
	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Album> albums;
	
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
	
	public void addAlbum(Album album) {
		albums.add(album);
		album.setArtist(this);
	}
	
	// CONSTRUCTOR
	public Artist(String artistName, String firstName, String lastName) {
		super(firstName, lastName);
		this.artistName = artistName;
		albums = new HashSet<Album>();
		
	}
	
	public Artist()
	{
		super();
		albums = new HashSet<Album>();
		
	}
	
}
