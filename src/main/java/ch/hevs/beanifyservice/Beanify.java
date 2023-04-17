package ch.hevs.beanifyservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;
import ch.hevs.businessobject.Song;
import ch.hevs.businessobject.Subscriber;

@Local
public interface Beanify {

	Artist getArtist(String artistName);
	List<Artist> getArtists();
	Subscriber getSubscriberByEmail(String email);
	Song getSongById(Long id);
	void addLikedSongToSubscriber(Subscriber sub, Song song);
	List<Song>getSubscriberLikedSongs(Subscriber sub); 
}
