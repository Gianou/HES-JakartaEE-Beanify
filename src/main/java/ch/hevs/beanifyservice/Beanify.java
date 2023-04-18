package ch.hevs.beanifyservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;
import ch.hevs.businessobject.Song;
import ch.hevs.businessobject.Subscriber;

@Local
public interface Beanify {

	Artist getArtistByID(Long id);
	List<Artist> getArtists();
	
	Subscriber getSubscriberByID(Long id);
	List<Subscriber> getSubscribers();
	
	Song getSongById(Long id);
	void addLikedSongToSubscriber(Subscriber sub, Song song);
	
	List<Long>getSubscriberLikedSongs(Subscriber sub);
}
