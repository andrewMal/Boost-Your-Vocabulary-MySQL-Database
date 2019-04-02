package boostYourVoc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextArea;


public class About extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtWwwlinkedincominandreasmalliotakis;
	private JTextField txtHttpsgithubcomandrewmal;

	
	public About() {
		setTitle("About");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAboutClose = new JButton("");
		btnAboutClose.addActionListener(HelperUtil.aboutCloseListener);
			
		btnAboutClose.setIcon(new ImageIcon(About.class.getResource("/boostYourVoc/Exit.png")));
		btnAboutClose.setBounds(385, 215, 35, 25);
		contentPane.add(btnAboutClose);
		
		JLabel lblRrrr = new JLabel("Version 0.1 ");
		lblRrrr.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRrrr.setForeground(SystemColor.menu);
		lblRrrr.setBounds(12, 13, 81, 36);
		contentPane.add(lblRrrr);
		
		JTextPane txtpnboostYourVocabulary = new JTextPane();
		txtpnboostYourVocabulary.setEditable(false);
		txtpnboostYourVocabulary.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnboostYourVocabulary.setForeground(SystemColor.menu);
		txtpnboostYourVocabulary.setBackground(SystemColor.activeCaption);
		txtpnboostYourVocabulary.setText("\"Boost your vocabulary\" is open source, easy to use application  which help you to train your own choice of words while you can update your database evrerytime you want.");
		txtpnboostYourVocabulary.setBounds(12, 62, 408, 70);
		contentPane.add(txtpnboostYourVocabulary);
		
		JTextArea txtrCreatedByAndreas = new JTextArea();
		txtrCreatedByAndreas.setEditable(false);
		txtrCreatedByAndreas.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 13));
		txtrCreatedByAndreas.setBackground(SystemColor.activeCaption);
		txtrCreatedByAndreas.setLineWrap(true);
		txtrCreatedByAndreas.setText("Created by Andreas Malliotakis  ");
		txtrCreatedByAndreas.setBounds(12, 129, 258, 25);
		contentPane.add(txtrCreatedByAndreas);
		
		txtWwwlinkedincominandreasmalliotakis = new JTextField();
		txtWwwlinkedincominandreasmalliotakis.setEditable(false);
		txtWwwlinkedincominandreasmalliotakis.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtWwwlinkedincominandreasmalliotakis.setBackground(SystemColor.activeCaption);
		txtWwwlinkedincominandreasmalliotakis.setText("www.linkedin.com/in/andreas-malliotakis-047289158");
		txtWwwlinkedincominandreasmalliotakis.setBounds(12, 167, 319, 22);
		contentPane.add(txtWwwlinkedincominandreasmalliotakis);
		txtWwwlinkedincominandreasmalliotakis.setColumns(10);
		
		txtHttpsgithubcomandrewmal = new JTextField();
		txtHttpsgithubcomandrewmal.setEditable(false);
		txtHttpsgithubcomandrewmal.setBackground(SystemColor.activeCaption);
		txtHttpsgithubcomandrewmal.setText("https://github.com/andrewMal");
		txtHttpsgithubcomandrewmal.setBounds(12, 202, 188, 22);
		contentPane.add(txtHttpsgithubcomandrewmal);
		txtHttpsgithubcomandrewmal.setColumns(10);
	}
}
