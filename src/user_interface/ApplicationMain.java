package user_interface;

import graph.GraphTraverser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Class for the GUI of the application.
 * 
 * @author Kieraj Mumick
 *
 */
public class ApplicationMain extends JFrame {

	/**
	 * Generated serialVersionUID for encoding.
	 */
	private static final long serialVersionUID = -4549338209780047297L;

	/**
	 * Constructor to create the main window.
	 */
	public ApplicationMain() {
		// set the size and location of the screen
		this.setSize(new Dimension(200, 75));
		this.setLocation(new Point(40, 40));
		this.setResizable(false);
		this.setTitle("Pitbull Number");

		// create a JComboBox to put the choices in.
		String[] choices = { "Lil Jon", "Ying Yang Twins", "Twista, Jim Jones",
				"Jason Derulo", "Don Omar", "Toby Love", "Yung Berg",
				"Sophia Del Carmen", "Qwote", "Qwote", "Rls", "Bo Hagon",
				"Lil Scrappy", "Ice Cube", "Gangsta Boo", "8-Ball & MJG",
				"Oobie", "DJ Flexx", "Too $hort", "Khugo", "Naadei",
				"Machel Montano", "LMFAO", "Ying Yang Twins",
				"Pitbull feat. Ke$ha", "The Hood Ratz", "China Dog",
				"DJ Kizzy Rock", "D-Roc", "K Dolla", "Twista", "Dra Day",
				"DJ Victoriouz", "Tia London", "Scotty", "Waka Flocka Flame",
				"Ray J", "Yo Gatti", "Chris Brown",
				"Da Wreck Of Triple Darkness", "Yo Gotti", "Lloyd", "Raekwon",
				"Lil Boosie & Webbie (Featuring Bun B)", "Buk Of Psychodrama",
				"Lil Boosie", "OJ The Juiceman", "Ericka Shevon", "Do Or Die",
				"Smigg Dirtee", "Juvenile", "Snoop Dogg", "Kanye West",
				"R. Kelly", "Jim Jones", "Ricky Blaze", "2 Chainz", "Trav",
				"Sen City", "Mel Matrix", "Future", "Serena Ryder", "Estelle",
				"Chink Santana", "Charlie Rock", "Maino", "Chase & Status",
				"Young Swift", "Jadakiss", "Randy Huston",
				"Jim Jones, Sen City, Cam'ron", "Jim Jones, Snoop Dogg, Rell",
				"DJ Prostyle", "Roscoe Dash",
				"Jim Jones, Gucci Mane, Sen City",
				"Jim Jones, Cam'ron, Snoop Dogg",
				"Jim Jones, Hard Luck, Mel Matrix", "Cam'ron, Juelz Santana",
				"Jim Jones,Young Prince,Kasino,Nyemiah",
				"Jim Jones, Yo Gotti,Cam'ron, Vado,Chubbie Baby",
				"Sean Garrett", "Rell", "Oshy", "Aaron Lacrate", "Cam'Ron",
				"Wyclef Jean", "The Game", "Ashanti", "Ridin Thumb",
				"H.O.P.E.L.E.S.S.", "Victoria Beckham", "Kelis",
				"What We Live", "Juelz Santana", "Bun B", "Freekey Zekey",
				"Rugz D. Bewler", "Chink Santanna", "Riva Starr",
				"Jason Derulo", "Tyga", "Jordin Sparks", "Kid Ink", };
		final JComboBox<String> cb = new JComboBox<String>(choices);
		cb.setEditable(true);
		this.add(cb);

		JButton b = new JButton("calculate");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// display a new frame saying this may take a while
				JFrame frame = new JFrame();
				frame.setSize(new Dimension(200, 75));
				frame.setLocation(new Point(40, 40 + 75));
				JLabel l = new JLabel("Calculating...");
				frame.add(l);
				frame.setTitle("Calculating...");
				frame.setVisible(true);

				JFrame frame2 = new JFrame();
				frame2.setSize(new Dimension(200, 75));
				frame2.setLocation(new Point(40, 40 + 150));
				JLabel l2 = new JLabel();
				// perform the calculation
				String artist = cb.getSelectedItem().toString();
				int dist = GraphTraverser.traverseGraph(
						"spotify:artist:0TnOYISbd1XYRBk9myaseg", artist);
				l2.setText(artist + " is " + dist + " away from Pitbull");
				frame2.add(l2);
				frame2.setVisible(true);
			}

		});
		this.add(b, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
