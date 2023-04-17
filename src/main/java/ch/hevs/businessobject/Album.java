package ch.hevs.businessobject;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Album")
public class Album {
 
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name="album")
	private String albumTitle;
	
	@Column(name="date")
	private String releaseDate;
	
	@ManyToOne
	private Artist artist;
	
	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //Eager required to display the song with Album.songs in the view
	private Set<Song> songs;
	
	public void addSong(Song song) {
		song.setAlbum(this);
		this.songs.add(song);
	}

	
	// GETTER SETTER
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Set<Song> getSongs() {
		return songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}
	
	//CONSTRUCTOR
	public Album() {
		super();
		songs = new HashSet <Song>();
	}
	
	public Album(String albumTitle, String date) {
		super();
		this.albumTitle = albumTitle;
		this.releaseDate = date;
		songs = new HashSet <Song>();
	}
	
}
