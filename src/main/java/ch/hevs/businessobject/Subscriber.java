package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Subscriber")
public class Subscriber extends Person{
	

	@Column(name="email")
	private String email;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Song> likedSongs;
		
	//Helper method
		public void addLikedSong(Song song) {
			this.likedSongs.add(song);
		}
	
	// GETTER SETTER
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Set<Song> getLikedSongs() {
		return likedSongs;
	}

	public void setLikedSongs(Set<Song> likedSongs) {
		this.likedSongs = likedSongs;
	}

	//CONSTRUCTOR
	public Subscriber(String email, String firstName, String lastName) {
		super(firstName, lastName);
		this.email = email;
		likedSongs = new HashSet<Song>();
	}
	
	public Subscriber()
	{
		super();
		likedSongs = new HashSet<Song>();
	}

	
	@Override
	public String toString() {
		String result = getFirstName();
		result += " ";
		result += getLastName();
		return result;
	}
	
	
	

}
