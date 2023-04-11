package ch.hevs.bankservice;

import java.util.List;

import ch.hevs.businessobject.Artist;

public interface Beanify {

	Artist getartist(String artistName);
	public List<Artist> getartistList();
}
