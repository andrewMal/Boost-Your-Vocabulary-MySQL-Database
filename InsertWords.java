package boostYourVoc;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;


public class InsertWords extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTextField textFieldForeign;
	static JTextField textFieldNative;

	
	public InsertWords() {
		setTitle("Insert words");
		addWindowListener(new HelperUtil.InsWindowAdapter());
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 467);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldForeign = new JTextField();
		textFieldForeign.setFont(new Font("Tahoma", Font.BOLD, 17));
		textFieldForeign.setBounds(154, 67, 289, 41);
		contentPane.add(textFieldForeign);
		textFieldForeign.setColumns(10);
		
		textFieldNative = new JTextField();
		textFieldNative.setFont(new Font("Tahoma", Font.BOLD, 17));
		textFieldNative.setBounds(154, 180, 289, 41);
		contentPane.add(textFieldNative);
		textFieldNative.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(Model.newInsertListener);
				
		btnInsert.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnInsert.setForeground(SystemColor.activeCaption);
		btnInsert.setBounds(233, 256, 130, 46);
		contentPane.add(btnInsert);
		
		JButton btnClose = new JButton("");
		btnClose.setIcon(new ImageIcon(InsertWords.class.getResource("/boostYourVoc/Exit.png")));
		btnClose.addActionListener(HelperUtil.insertCloseListener);
			
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnClose.setForeground(SystemColor.activeCaption);
		btnClose.setBounds(498, 371, 87, 36);
		contentPane.add(btnClose);
		
		JLabel lblNewLabel = new JLabel("Foreign");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(SystemColor.menu);
		lblNewLabel.setBounds(245, 29, 107, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Native");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setForeground(SystemColor.menu);
		lblNewLabel_1.setBounds(231, 137, 135, 30);
		contentPane.add(lblNewLabel_1);
	}
}
