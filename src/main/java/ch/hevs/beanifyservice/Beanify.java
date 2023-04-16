package ch.hevs.beanifyservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Album;
import ch.hevs.businessobject.Artist;

@Local
public interface Beanify {

	Artist getArtist(String artistName);
	List<Artist> getArtists();
	List<Album> getAlbums(float id);
	
}
