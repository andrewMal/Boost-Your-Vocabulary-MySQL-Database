package boostYourVoc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;

public class TypeTrain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JPanel contentPane;
	static JTextField textField2;

	static PreparedStatement pst;
	static ResultSet rs;
	static JTextField textField1;
	static String answer;
	static JLabel lblScore;


	public TypeTrain() {
		setTitle("Type Training");
		addWindowListener(new Model.TypeTrainWinAdapter());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 446);
		contentPane = new JPanel();
		
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		JLabel lblTraining = new JLabel("Training");
		lblTraining.setHorizontalAlignment(SwingConstants.CENTER);
		lblTraining.setForeground(SystemColor.menu);
		lblTraining.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTraining.setBounds(238, 13, 141, 32);
		contentPane.add(lblTraining);
		
		textField2 = new JTextField();
		textField2.setHorizontalAlignment(SwingConstants.CENTER);
		textField2.setFont(new Font("Carlito", Font.BOLD | Font.ITALIC, 16));
		textField2.setBounds(103, 177, 411, 32);
		contentPane.add(textField2);
		textField2.setColumns(10);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.setForeground(SystemColor.activeCaption);
		btnCheck.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCheck.addMouseListener(new Model.typeTrainMouseListener());

		btnCheck.addActionListener(Model.typeTrainCheckListener);
		btnCheck.setBounds(260, 246, 97, 51);
		contentPane.add(btnCheck);
		
		JButton btnClose = new JButton("");
		btnClose.setIcon(new ImageIcon(TypeTrain.class.getResource("/boostYourVoc/Exit.png")));
		btnClose.addActionListener(HelperUtil.typeTrainCloseListener);
			
		btnClose.setBounds(537, 344, 68, 42);
		contentPane.add(btnClose);
		
		textField1 = new JTextField();
		textField1.setHorizontalAlignment(SwingConstants.CENTER);
		textField1.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField1.setForeground(new Color(0, 0, 0));
		textField1.setEditable(false);
		textField1.setBounds(103, 97, 411, 32);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Word for translation");
		lblNewLabel.setForeground(SystemColor.menu);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(104, 58, 170, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Type your answer :");
		lblNewLabel_2.setForeground(SystemColor.menu);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(103, 142, 170, 22);
		contentPane.add(lblNewLabel_2);
		
		JButton buttonNext = new JButton("");
		buttonNext.addActionListener(Model.typeTrainNextListener);
		
		buttonNext.setIcon(new ImageIcon(TypeTrain.class.getResource("/boostYourVoc/Next_track.png")));
		buttonNext.setBounds(260, 310, 97, 25);
		contentPane.add(buttonNext);

		
		JLabel lblScore = new JLabel("Percentage of correct answers");
		lblScore.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblScore.setForeground(SystemColor.menu);
		lblScore.setBounds(12, 329, 218, 32);
		contentPane.add(lblScore);
		
		
	}
}
