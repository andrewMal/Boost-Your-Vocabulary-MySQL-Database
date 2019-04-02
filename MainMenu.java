package boostYourVoc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.sql.Connection;

public class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1526692153713113920L;
	private JPanel contentPane;
	public static Connection conn;

	
	public MainMenu(){
		addWindowListener(new Model.ConnWinAdapter());
			
		setTitle("Boost Your Vocubulary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setForeground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVocab = new JLabel("Boost your Vocabulary ");
		lblVocab.setForeground(new Color(240, 240, 240));
		lblVocab.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblVocab.setHorizontalAlignment(SwingConstants.CENTER);
		lblVocab.setBounds(58, 13, 315, 45);
		contentPane.add(lblVocab);
		
		JButton btnInsertNewWord = new JButton("Insert new word");
		btnInsertNewWord.addActionListener(HelperUtil.insertOpenListener);
		
		btnInsertNewWord.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnInsertNewWord.setForeground(SystemColor.activeCaption);
		btnInsertNewWord.setBounds(139, 71, 154, 25);
		contentPane.add(btnInsertNewWord);
		
		JButton btnMainMenuUpDel = new JButton("Update/Delete");
		btnMainMenuUpDel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMainMenuUpDel.setForeground(SystemColor.activeCaption);
		btnMainMenuUpDel.addActionListener(HelperUtil.updateOpenListener);
		
		btnMainMenuUpDel.setBounds(139, 109, 154, 25);
		contentPane.add(btnMainMenuUpDel);
		
		JButton btnMainMenuTraining = new JButton("Training");
		btnMainMenuTraining.addActionListener(HelperUtil.trainOpenListener);
				
		btnMainMenuTraining.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMainMenuTraining.setForeground(SystemColor.activeCaption);
		btnMainMenuTraining.setBounds(139, 147, 154, 25);
		contentPane.add(btnMainMenuTraining);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(HelperUtil.mainMenuCloseListener);
				
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnExit.setForeground(SystemColor.activeCaption);
		btnExit.setBounds(139, 185, 154, 25);
		contentPane.add(btnExit);
		
		JButton btnAbout = new JButton("");
		btnAbout.addActionListener(HelperUtil.aboutOpenListener);
		
		btnAbout.setBackground(SystemColor.activeCaption);
		btnAbout.setToolTipText("About");
		btnAbout.setIcon(new ImageIcon(MainMenu.class.getResource("/boostYourVoc/About.png")));
		btnAbout.setBounds(381, 207, 39, 33);
		contentPane.add(btnAbout);
	}
}
