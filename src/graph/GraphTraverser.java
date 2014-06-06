package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import pitbullNumber.Album;
import pitbullNumber.Artist;
import pitbullNumber.Song;

/**
 * Class that traverses through the network of Artists to find how far away the
 * artist you are searching for is from Another Artist.
 * 
 * @author Kieraj Mumick
 */
public class GraphTraverser {

	/**
	 * Keeps track of which artists have already been found; this prevents
	 * duplicates to be added to the Queue during BFS.
	 */
	public static HashSet<String> artists = new HashSet<String>();

	/**
	 * Method that traverses through the network of artists.
	 * 
	 * @param artistKey
	 *            Spotify's ID for the artist you are starting from.
	 * @param targetName
	 *            The name of the artist you would like to find how far away
	 *            from the start artist.
	 * @return The number of hops between the start and target artist. returns
	 *         -1 if nothing was found (i.e., in the scenario where the graph is
	 *         not connected).
	 */
	public static int traverseGraph(String artistKey, String targetName) {
		// set up the queue for BFS
		Queue<Node<Artist>> q = new LinkedList<Node<Artist>>();
		Node<Artist> nd = new Node<Artist>(new Artist(artistKey), 0);
		// find the neighbors of n and add them to n's adjacency list
		Artist nArtist = nd.getData();
		ArrayList<Album> nAlbums = nArtist.getAlbums();
		for (Album album : nAlbums) {
			ArrayList<Song> songs = album.getSongs();
			for (Song song : songs) {
				ArrayList<Artist> artists = song.getAllArtists();
				for (Artist result : artists) {
					try {
						if (result != null
								&& nArtist != null
								&& !result.toString()
										.equals(nArtist.toString())) {

							Node<Artist> toAdd = new Node<Artist>(result,
									nd.getLevel() + 1);
							nd.addEdge(toAdd);
							toAdd.addEdge(nd);

							if (toAdd.toString().toLowerCase()
									.equals(targetName.toLowerCase())) {
								return toAdd.getLevel();
							}

						}
					} catch (NullPointerException e) {
						// e.printStackTrace();
					}
				}
			}
		}
		System.out.println(nd);
		q.add(nd);

		// perform BFS
		while (!q.isEmpty()) {
			System.out.println("Queue: " + q);
			Node<Artist> n = q.remove();
			System.out.println(n.getAdjacent());
			// go through each of the adjacent nodes to n and add it to the
			// queue.
			for (Node<Artist> node : n.getAdjacent()) {
				if (!node.isVisited()) {
					node.setVisited(true);
					Artist artist = node.getData();
					if (artist != null) {
						ArrayList<Album> albums = artist.getAlbums();
						for (Album album : albums) {
							ArrayList<Song> songs = album.getSongs();
							for (Song song : songs) {
								ArrayList<Artist> artists = song
										.getAllArtists();
								for (Artist result : artists) {
									try {
										if (result != null
												&& artist != null
												&& !result.toString().equals(
														artist.toString())) {
											if (!GraphTraverser.artists
													.contains(result.toString())) {
												System.out.println(result);

												Node<Artist> toAdd = new Node<Artist>(
														result,
														node.getLevel() + 1);
												node.addEdge(toAdd);
												toAdd.addEdge(node);
												q.add(toAdd);

												if (toAdd
														.toString()
														.toLowerCase()
														.equals(targetName
																.toLowerCase())) {
													return toAdd.getLevel();
												}

												GraphTraverser.artists
														.add(result.toString());
											}
										}
									} catch (NullPointerException e) {
										// do nothing
									}
								}
							}
						}
					}
				}
			}
		}
		// return -1 if not found.
		return -1;
	}

}
