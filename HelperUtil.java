package boostYourVoc;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class HelperUtil {

	static class InsWindowAdapter extends WindowAdapter{
		
		@Override
		public void windowActivated(WindowEvent e) {
			InsertWords.textFieldNative.setText("");
			InsertWords.textFieldForeign.setText("");
		}
	}
	//Listeners for close and open with lambda
	static ActionListener insertCloseListener = (e)->{
		VocabularyApp.mainframe.setEnabled(true);
		VocabularyApp.insertframe.setVisible(false);
	};
	
	static ActionListener updateCloseListener = (e)->{
		VocabularyApp.searchframe.setEnabled(true);
		VocabularyApp.updateframe.setVisible(false);
	};
	
	static ActionListener aboutCloseListener = (e)->{
		VocabularyApp.mainframe.setEnabled(true);
		VocabularyApp.aboutframe.setVisible(false);
	};
	
	static ActionListener multipleCloseListener = (e)->{
		VocabularyApp.mainframe.setEnabled(true);
		VocabularyApp.multipleframe.setVisible(false);
	};
	
	static ActionListener searchCloseListener = (e)->{
		VocabularyApp.mainframe.setEnabled(true);
		VocabularyApp.searchframe.setVisible(false);
	};
	
	static ActionListener typeTrainCloseListener = (e)->{
		VocabularyApp.mainframe.setEnabled(true);
		VocabularyApp.typetrainframe.setVisible(false);
	};
	
	static ActionListener mainMenuCloseListener = (e)->{
		System.exit(0);
	};
	
	static ActionListener insertOpenListener =(e)->{
		VocabularyApp.insertframe.setVisible(true);
		VocabularyApp.mainframe.setEnabled(false);
	};
	
	static ActionListener updateOpenListener = (e)->{
		VocabularyApp.searchframe.setVisible(true);
		VocabularyApp.mainframe.setEnabled(false);
	};
	
	static ActionListener trainOpenListener = (e)->{
		Object[] options ={"Type Training","Multiple choice training"};
		int n = JOptionPane.showOptionDialog(null, "What kind of training do you want", "Choose your training", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (n==0){
			VocabularyApp.typetrainframe.setVisible(true);
			VocabularyApp.mainframe.setEnabled(false);
		}
		else{
			VocabularyApp.multipleframe.setVisible(true);
			VocabularyApp.mainframe.setEnabled(false);
		}
	};
	
	static ActionListener aboutOpenListener = (e)->{
		VocabularyApp.aboutframe.setVisible(true);
		VocabularyApp.mainframe.setEnabled(false);
	};
}
