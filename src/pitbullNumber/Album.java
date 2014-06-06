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
 * Representation of an album that an artist writes. Ever album has songs and an
 * artist.
 * 
 * @author Kieraj Mumick.
 */
public class Album {

	/**
	 * The base URL for looking up stuff.
	 */
	private final String baseURL = "http://ws.spotify.com/lookup/1/?uri=";

	/**
	 * The portion of the URL to add onto the end of the Spotify artist's id.
	 */
	private final String endURL = "&extras=track";

	/**
	 * ArrayList that contains all of the songs on this album.
	 */
	private ArrayList<Song> songs;

	/**
	 * The year the album was released.
	 */
	private Integer year;

	/**
	 * The id for this album.
	 */
	private Integer id;

	/**
	 * The name for this album.
	 */
	private String name;

	/**
	 * The artist for this album.
	 */
	private Artist artist;

	/**
	 * Constructor to create an album.
	 * 
	 * @param albumID
	 *            The id that can be used to lookup this album on Spotify.
	 */
	public Album(String albumID, Artist artist) {
		this.artist = artist;
		this.songs = new ArrayList<Song>();
		this.parse(baseURL + albumID + endURL);
	}

	/**
	 * Parse through the XML file and get all the songs on the album.
	 * 
	 * @param file
	 */
	public void parse(String file) {
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			document.getDocumentElement().normalize();

			// Get the attributes for the album.
			NodeList nodeList = document.getElementsByTagName("album");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node n = nodeList.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					// get the element from the node we are at.
					Element e = (Element) n;
					// get the element for the artist
					String name = e.getElementsByTagName("name").item(0)
							.getTextContent();
					this.name = name;

					// go through all the tracks on the album
					Element tracks = (Element) e.getElementsByTagName("tracks")
							.item(0);
					NodeList allTracks = tracks.getElementsByTagName("track");
					for (int j = 0; j < allTracks.getLength(); j++) {
						Node track = allTracks.item(j);
						if (track.getNodeType() == Node.ELEMENT_NODE) {
							// get the element for the tracks
							Element t = (Element) track;
							// get the element for track
							String songID = t.getAttribute("href");
							String songName = t.getElementsByTagName("name")
									.item(0).getTextContent();
							Song song = new Song(songID, this.artist, this,
									songName);
							this.songs.add(song);
						}
					}
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		String description = "-----------------------------------------------------\n";
		description += "Album: " + this.name + "\n";
		description += "-----------------------------------------------------";
		for (Song s : this.songs)
			description += "\n" + s.toString();
		return description;
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
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((baseURL == null) ? 0 : baseURL.hashCode());
		result = prime * result + ((endURL == null) ? 0 : endURL.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((songs == null) ? 0 : songs.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		Album other = (Album) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (baseURL == null) {
			if (other.baseURL != null)
				return false;
		} else if (!baseURL.equals(other.baseURL))
			return false;
		if (endURL == null) {
			if (other.endURL != null)
				return false;
		} else if (!endURL.equals(other.endURL))
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
		if (songs == null) {
			if (other.songs != null)
				return false;
		} else if (!songs.equals(other.songs))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	/**
	 * @return the baseURL
	 */
	public String getBaseURL() {
		return baseURL;
	}

	/**
	 * @return the endURL
	 */
	public String getEndURL() {
		return endURL;
	}

	/**
	 * @return the songs
	 */
	public ArrayList<Song> getSongs() {
		return songs;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the artist
	 */
	public Artist getArtist() {
		return artist;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Album a = new Album("spotify:album:6G9fHYDCoyEErUkHrFYfs4", null);
	}

}
