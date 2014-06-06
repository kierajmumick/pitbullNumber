package pitbullNumber;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Representation of a Song. Each song is in an album and is sung by an artist.
 * Each song also has a name and an ID given to it by Spotify.
 * 
 * @author Kieraj Mumick.
 */
public class Song {

	/**
	 * The song's ID, given by spotify.
	 */
	private final String id;

	/**
	 * The song's artist.
	 */
	private final Artist artist;

	/**
	 * The song's album.
	 */
	private final Album album;

	/**
	 * The song's name.
	 */
	private final String name;

	/**
	 * Constructor to create a song.
	 * 
	 * @param id
	 *            The id for the song.
	 * @param artist
	 *            The artist of the song.
	 * @param album
	 *            The album of the song.
	 * @param name
	 *            The name of the song.
	 * @param extraAlbumInfo
	 *            The extra information about the artist.
	 */
	public Song(String id, Artist artist, Album album, String name) {
		this.id = id;
		this.artist = artist;
		this.album = album;
		this.name = name;
	}

	/**
	 * Gets all the artists affiliated with a song.
	 * 
	 * @return an ArrayList of all the artists affiliated with a song.
	 */
	public ArrayList<Artist> getAllArtists() {
		ArrayList<Artist> artists = new ArrayList<Artist>();
		artists.add(this.artist);
		if (this.name.toLowerCase().contains("feat")) {
			// get the substring to search
			int index = this.name.indexOf("feat");
			index += 4;
			if (this.name.toLowerCase().contains("featured"))
				index += 4;
			// get rid of the unnecessary stuff.
			String substring = this.name.substring(index);
			substring = substring.replace(" ", "-");
			substring = substring.replace("(", "");
			substring = substring.replace(")", "");
			substring = substring.replace(".", "");
			substring = substring.replace("&", "");
			substring = substring.replace(this.artist.getName(), "");
			// trim the -s from the sides
			substring = substring.replace("-", " ").trim().replace(" ", "-");

			// get the URL to search with
			String searchURL = "http://ws.spotify.com/search/1/artist?q="
					+ substring;
			Artist a = Song.searchForArtist(searchURL);
			if (a != null)
				artists.add(a);
		} else if (this.name.contains("ftd")) {
			// get the substring to search.
			int index = this.name.indexOf("ftd");
			index += 3;
			// get rid of the unnecessary stuff.
			String substring = this.name.substring(index);
			substring = substring.replace(" ", "-");
			substring = substring.replace("(", "");
			substring = substring.replace(")", "");
			substring = substring.replace(".", "");
			substring = substring.replace("&", "");
			substring = substring.replace(this.artist.getName(), "");
			// trim the -s from the sides
			substring = substring.replace("-", " ").trim().replace(" ", "-");

			// get the URL to search with
			System.out.println(substring);
			String searchURL = "http://ws.spotify.com/search/1/artist?q="
					+ substring;
			Artist a = Song.searchForArtist(searchURL);
			if (a != null)
				artists.add(a);
		}
		// return the artists from the song.
		return artists;
	}

	/**
	 * Searches for an artist that is mentioned in a string.
	 * 
	 * @param searchURL
	 *            the URL to find the search results.
	 * @return The first artist in the search results. If no artist was found,
	 *         or an exception was thrown, returns null.
	 */
	public static Artist searchForArtist(String searchURL) {
		try {
			// set up the document
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(searchURL);
			document.getDocumentElement().normalize();

			// initialize the artist that we have found to null.
			Artist a = null;

			// get the first artist in the search results.
			NodeList artists = document.getElementsByTagName("artist");
			Node n = artists.item(0);
			if (n != null && n.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) n;
				String id = e.getAttribute("href");
				a = new Artist(id);
			}

			// return the artist.
			return a;

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// e.printStackTrace();
			return null;
		}
	}

	@Override
	public String toString() {
		return this.name + " (" + this.artist.getName() + ")";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((album == null) ? 0 : album.hashCode());
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (album == null) {
			if (other.album != null)
				return false;
		} else if (!album.equals(other.album))
			return false;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the artist
	 */
	public Artist getArtist() {
		return artist;
	}

	/**
	 * @return the album
	 */
	public Album getAlbum() {
		return album;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
