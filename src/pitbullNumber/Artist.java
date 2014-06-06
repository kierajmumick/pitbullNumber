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
 * Representation of a music artist. Each artist has a bunch of albums, each of
 * which have songs. Each artist also has a name and an ID from spotify.
 * 
 * @author Kieraj Mumick.
 */
public class Artist {

	/**
	 * The base URL for looking up stuff.
	 */
	private final String baseURL = "http://ws.spotify.com/lookup/1/?uri=";
	private final String endURL = "&extras=album";

	/**
	 * The albums by this artist.
	 */
	private ArrayList<Album> albums;

	/**
	 * The name of this artist.
	 */
	private String name;

	/**
	 * The ID of this artist, given by Spotify.
	 */
	private String id;

	/**
	 * Constructor for an Artist with a given ID.
	 * 
	 * @param id
	 */
	public Artist(String id) {
		this.id = id;
		this.albums = new ArrayList<Album>();
		this.parse(baseURL + id + endURL);
	}

	/**
	 * Parse through the XML file and get all the songs on the album.
	 * 
	 * @param file
	 */
	public void parse(String file) {
		try {

			// set up the document to parse through
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			document.getDocumentElement().normalize();

			// Get the name of this artist
			NodeList nl = document.getElementsByTagName("artist");
			Node no = nl.item(0);
			if (no.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) no;
				this.name = e.getElementsByTagName("name").item(0)
						.getTextContent();
			}

			// Get the IDs for all of the albums by this artist.
			ArrayList<String> ids = new ArrayList<String>();
			ArrayList<String> names = new ArrayList<String>();
			NodeList nodeList = document.getElementsByTagName("album");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node n = nodeList.item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					// get the element from the node we are at.
					Element e = (Element) n;
					String albumID = e.getAttribute("href");

					Node artistNode = e.getElementsByTagName("artist").item(0);
					Element artistElement = (Element) artistNode;
					String artist = artistElement.getElementsByTagName("name")
							.item(0).getTextContent();
					if (name.toLowerCase().equals("various artists")
							|| !artist.toLowerCase().contains(
									this.name.toLowerCase()))
						continue;

					names.add(name);
					ids.add(albumID);
				}
			}

			// create albums out of each of the album IDs and add them to our
			// albums list
			for (String id : ids)
				this.albums.add(new Album(id, this));

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		// String description =
		// "--------------------------------------------------\n";
		// description += "||          " + this.name + "         ||\n";
		// description += "--------------------------------------------------";
		// for (Album a : this.albums)
		// description += "\n\n" + a;
		return this.name;
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
		result = prime * result + ((albums == null) ? 0 : albums.hashCode());
		result = prime * result + ((baseURL == null) ? 0 : baseURL.hashCode());
		result = prime * result + ((endURL == null) ? 0 : endURL.hashCode());
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
		Artist other = (Artist) obj;
		if (albums == null) {
			if (other.albums != null)
				return false;
		} else if (!albums.equals(other.albums))
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
	 * @return the albums
	 */
	public ArrayList<Album> getAlbums() {
		return albums;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	public static void main(String[] args) {
		Artist a = new Artist("spotify:artist:0TnOYISbd1XYRBk9myaseg");
		System.out.println(a);
	}

}
