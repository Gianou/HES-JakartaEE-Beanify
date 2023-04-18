package ch.hevs.beanifyservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;
import ch.hevs.businessobject.Song;
import ch.hevs.businessobject.Subscriber;

@Local
public interface Beanify {

	public Artist getArtistByID(Long id);
	public List<Artist> getArtists();
	
	public List<Album> loadArtistAlbums(Artist artist);
	public List<Song> loadLikedSongs(Subscriber sub);
	
	public Song getSongById(Long id);
	
	public Subscriber getSubscriberByID(Long id);
	public List<Subscriber> getSubscribers();
	
	public void addLikedSongToSubscriber(Subscriber sub, Song song);
	public void removeLikedSongToSubscriber(Subscriber sub, Song song);
	public List<Long>getSubscriberLikedSongs(Subscriber sub);
}
