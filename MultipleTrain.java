package boostYourVoc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class MultipleTrain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JPanel contentPane;
	static JTextField textField;
	static String correctAnswer;
	static String answer1;
	static String answer2;
	static String answer3;
	static PreparedStatement pst;
	static ResultSet rs;
	List <String> answers;
	static JTextField textFieldChoice1;
	static JTextField textFieldChoice2;
	static JTextField textFieldChoice3;
	

	
	public MultipleTrain() {
		setTitle("Multiple Training");
		addWindowListener(new Model.MultipleTrainWinAdapter());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 714, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBounds(201, 97, 293, 50);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblMultiplechoiceTrainning = new JLabel("MultipleChoice Trainning");
		lblMultiplechoiceTrainning.setForeground(SystemColor.menu);
		lblMultiplechoiceTrainning.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblMultiplechoiceTrainning.setBounds(237, 13, 222, 34);
		contentPane.add(lblMultiplechoiceTrainning);
		
		JButton buttonNext = new JButton("");
		buttonNext.addActionListener(Model.multipleTrainNext);
		buttonNext.setIcon(new ImageIcon(MultipleTrain.class.getResource("/boostYourVoc/Next_track.png")));
		buttonNext.setBounds(294, 281, 131, 34);
		contentPane.add(buttonNext);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(HelperUtil.multipleCloseListener);
			
		button_1.setIcon(new ImageIcon(MultipleTrain.class.getResource("/boostYourVoc/Exit.png")));
		button_1.setBounds(595, 272, 89, 43);
		contentPane.add(button_1);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(12, 291, 181, 24);
		contentPane.add(progressBar);
		
		textFieldChoice1 = new JTextField();
		textFieldChoice1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldChoice1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		textFieldChoice1.addMouseListener(new Model.multipleChoiceOneMouseListener());
				
				
//				MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				turns++;
//				if(correctAnswer.equals(textFieldChoice1.getText())){
//					textFieldChoice1.setForeground(new Color(0, 255, 0));
//					correct++;
//				}
//				else{
//					textFieldChoice1.setForeground(new Color(255, 0, 0));
//				}
//				progressBar.setValue((correct*100)/turns);
//			}
//		});
		textFieldChoice1.setEditable(false);
		textFieldChoice1.setBounds(19, 203, 206, 50);
		contentPane.add(textFieldChoice1);
		textFieldChoice1.setColumns(10);
		
		textFieldChoice2 = new JTextField();
		textFieldChoice2.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldChoice2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		textFieldChoice2.addMouseListener(new Model.multipleChoiceTwoMouseListener());
				
//				MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				turns++;
//				if(correctAnswer.equals(textFieldChoice2.getText())){
//					textFieldChoice2.setForeground(new Color(0, 255, 0));
//					correct++;
//				}
//				else{
//					textFieldChoice2.setForeground(new Color(255, 0, 0));
//				}
//				progressBar.setValue((correct*100)/turns);
//			}
//		});
		textFieldChoice2.setEditable(false);
		textFieldChoice2.setBounds(244, 203, 206, 50);
		contentPane.add(textFieldChoice2);
		textFieldChoice2.setColumns(10);
		
		textFieldChoice3 = new JTextField();
		textFieldChoice3.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldChoice3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		textFieldChoice3.addMouseListener(new Model.multipleChoiceThreeMouseListener());
		
		textFieldChoice3.setEditable(false);
		textFieldChoice3.setBounds(469, 203, 206, 50);
		contentPane.add(textFieldChoice3);
		textFieldChoice3.setColumns(10);
		
		JLabel label = new JLabel("Percentage of correct answers");
		label.setForeground(SystemColor.menu);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		label.setBounds(0, 253, 218, 32);
		contentPane.add(label);
		
	}
}
