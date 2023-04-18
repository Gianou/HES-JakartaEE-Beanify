package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
	

	@Column(name="email", unique=true)
	private String email;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Song> likedSongs;
		
	//Helper method
	@TransactionAttribute(value = TransactionAttributeType.MANDATORY)
	public void addLikedSong(Song song) {
		this.likedSongs.add(song);
	}
	
	@TransactionAttribute(value = TransactionAttributeType.MANDATORY)
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
