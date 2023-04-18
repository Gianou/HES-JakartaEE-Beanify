package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Subscriber")
public class Subscriber extends Person{
	

	@Column(name="email")
	private String email;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Song> likedSongs;
		
	//Helper method
	public void addLikedSong(Song song) {
		this.likedSongs.add(song);
	}
	
	public void deleteLikedSong(Song song) {
		this.likedSongs.remove(song);
	}
	
	// GETTER SETTER
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Song> getLikedSongs() {
		return likedSongs;
	}

	public void setLikedSongs(List<Song> likedSongs) {
		this.likedSongs = likedSongs;
	}

	//CONSTRUCTOR
	public Subscriber(String email, String firstName, String lastName) {
		super(firstName, lastName);
		this.email = email;
		likedSongs = new ArrayList <Song>();
	}
	
	public Subscriber()
	{
		super();
		likedSongs = new ArrayList <Song>();
	}

	
	@Override
	public String toString() {
		String result = getFirstName();
		result += " ";
		result += getLastName();
		return result;
	}
	
	
	

}
