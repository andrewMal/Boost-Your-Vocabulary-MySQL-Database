package boostYourVoc;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class Model {
	
	static int turns;//count every answer
	static int correct;//count correct answers

	static class ConnWinAdapter extends WindowAdapter{
		
		@Override
		public void windowOpened(WindowEvent e) { //database connection when you open the MainMenu
			String url = "jdbc:mysql://localhost:3306/Vocabulary";
			String username ="andreas1987";
			String password ="pass7557";
			
			try{
				MainMenu.conn = DriverManager.getConnection(url, username, password); // connect with database
			}catch (SQLException ex){
				JOptionPane.showMessageDialog(null, "Cannot connect to database!","Access failed",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	static class UpdateWinAdapter extends WindowAdapter{
		
		@Override
		public void windowActivated(WindowEvent e) {
			try{
				if (Search.byForeign){    //search by foreign
					String sql = "SELECT ID, FOREIGN_W, NATIVE_W FROM VOCABULARY WHERE FOREIGN_W LIKE ?";
					UpdateWords.pst = MainMenu.conn.prepareStatement(sql); //prepare
					UpdateWords.pst.setString(1, Search.foreignWord + '%');
					UpdateWords.rs = UpdateWords.pst.executeQuery();//result
					if (UpdateWords.rs.next()){
						UpdateWords.textFieldID.setText(UpdateWords.rs.getString("ID"));
						UpdateWords.textFieldUpForeign.setText(UpdateWords.rs.getString("FOREIGN_W"));
						UpdateWords.textFieldUpNative.setText(UpdateWords.rs.getString("NATIVE_W"));
					}
				}
				else{ //search by native
					String sql = "SELECT ID, FOREIGN_W, NATIVE_W FROM VOCABULARY WHERE NATIVE_W LIKE ?";
					UpdateWords.pst = MainMenu.conn.prepareStatement(sql);
					UpdateWords.pst.setString(1, Search.nativeWord + '%');
					UpdateWords.rs = UpdateWords.pst.executeQuery();
					if (UpdateWords.rs.next()){
						UpdateWords.textFieldID.setText(UpdateWords.rs.getString("ID"));
						UpdateWords.textFieldUpForeign.setText(UpdateWords.rs.getString("FOREIGN_W"));
						UpdateWords.textFieldUpNative.setText(UpdateWords.rs.getString("NATIVE_W"));
					}
				}
			}catch(SQLException e2){
				//e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "Something went wrong  (SQL exception)","Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		@Override                                    
		public void windowDeactivated(WindowEvent e) {
			UpdateWords.textFieldUpForeign.setText(""); //clean our window
			UpdateWords.textFieldUpNative.setText("");
		}
	}
	
	static class TypeTrainWinAdapter extends WindowAdapter{
		@Override
		public void windowActivated(WindowEvent e) {
			
			try{                                                                    // random peak of word
				String sql="SELECT ID, FOREIGN_W, NATIVE_W FROM VOCABULARY ORDER BY RAND() LIMIT 100";
				TypeTrain.pst=MainMenu.conn.prepareStatement(sql);
				TypeTrain.rs=TypeTrain.pst.executeQuery();
				if (TypeTrain.rs.next()){
					TypeTrain.textField1.setText(TypeTrain.rs.getString("FOREIGN_W"));
					TypeTrain.answer = TypeTrain.rs.getString("NATIVE_W");
					
				}
				
			}catch(SQLException e8){
				//e8.printStackTrace();
				JOptionPane.showMessageDialog(null, "Something went wrong (SQL exception)","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		@Override
		public void windowDeactivated(WindowEvent e) {
			TypeTrain.textField2.setText(""); //clean
			correct=0;                        // reset the counters
			turns=0;
		}
	}
	
	static class MultipleTrainWinAdapter extends WindowAdapter{
		public void windowDeactivated(WindowEvent e) {
			correct=0;
			turns=0;
		}
	}

	static ActionListener newInsertListener=(e)->{ // insert
		try{
			String foreignW = InsertWords.textFieldForeign.getText();
			String nativeW = InsertWords.textFieldNative.getText();
			if (!foreignW.equals("") && !nativeW.equals("")){ // check if the fields aren't empty  
				                                                     // i had set the id to auto increment
				PreparedStatement p = MainMenu.conn.prepareStatement("INSERT INTO VOCABULARY VALUES (?,?,?)");
	
				p.setInt(1, 0); // set values from fields except id which is auto-incremented
				p.setString(2, foreignW);
				p.setString(3, nativeW);
				
				p.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "The word insterted succesfully.","INSERT",JOptionPane.PLAIN_MESSAGE);
				
				p.close();
			}
			else {
				JOptionPane.showMessageDialog(null,"You have to type in both of the fields","Type something",JOptionPane.INFORMATION_MESSAGE);
			}
		}catch(SQLException e1){
			//e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Something went wrong in your insertion (SQL exception) make sure you don't have that word in your database","Error",JOptionPane.ERROR_MESSAGE);
		}
	};
	

	static ActionListener searchFListener =(e)->{
		Search.foreignWord = Search.textFieldForeignS.getText();
		VocabularyApp.searchframe.setEnabled(false);
		VocabularyApp.updateframe.setVisible(true);
		Search.byForeign=true;
	};
	
	static ActionListener searchNListener = (e)->{
		Search.nativeWord = Search.textFieldNativeS.getText();
		VocabularyApp.searchframe.setEnabled(false);
		VocabularyApp.updateframe.setVisible(true);
		Search.byForeign=false;
	};
	
	static ActionListener firstRecordListener = (e)->{ //first record
		try{
			if(UpdateWords.rs.first()){
				UpdateWords.textFieldID.setText(UpdateWords.rs.getString("ID"));
				UpdateWords.textFieldUpForeign.setText(UpdateWords.rs.getString("FOREIGN_W"));
				UpdateWords.textFieldUpNative.setText(UpdateWords.rs.getString("NATIVE_W"));
			}
			else{
				JOptionPane.showMessageDialog(null, "No words from your search","Empty result",JOptionPane.WARNING_MESSAGE);
			}
		}catch(SQLException e4){
			JOptionPane.showMessageDialog(null, "Something went wrong with Database (SQL exception)","Error",JOptionPane.ERROR_MESSAGE);
		}
	};
	
	static ActionListener nextRecordListener = (e)->{ //next record
		try{
			if (UpdateWords.rs.next()){
				UpdateWords.textFieldID.setText(UpdateWords.rs.getString("ID"));
				UpdateWords.textFieldUpForeign.setText(UpdateWords.rs.getString("FOREIGN_W"));
				UpdateWords.textFieldUpNative.setText(UpdateWords.rs.getString("NATIVE_W"));
			}
			else{
				UpdateWords.rs.last();
			}
		}catch(SQLException e6){
			//e6.printStackTrace();
			JOptionPane.showMessageDialog(null, "Something went wrong with Database (SQL exception)","Error",JOptionPane.ERROR_MESSAGE);
		}
	};
	
	static ActionListener previousRecordListener = (e)->{ // previous record
		try{
			if(UpdateWords.rs.previous()){
				UpdateWords.textFieldID.setText(UpdateWords.rs.getString("ID"));
				UpdateWords.textFieldUpForeign.setText(UpdateWords.rs.getString("FOREIGN_W"));
				UpdateWords.textFieldUpNative.setText(UpdateWords.rs.getString("NATIVE_W"));
			}
			else{
				UpdateWords.rs.first();					}
		}catch(SQLException e5){
			//e5.printStackTrace();
			JOptionPane.showMessageDialog(null, "Something went wrong with Database (SQL exception)","Error",JOptionPane.ERROR_MESSAGE);
		}
	};
	
	static ActionListener lastRecordListener= (e)->{ // last record
		try{
			if (UpdateWords.rs.last()){
				UpdateWords.textFieldID.setText(UpdateWords.rs.getString("ID"));
				UpdateWords.textFieldUpForeign.setText(UpdateWords.rs.getString("FOREIGN_W"));
				UpdateWords.textFieldUpNative.setText(UpdateWords.rs.getString("NATIVE_W"));
			}
			else{
				JOptionPane.showMessageDialog(null, "No words from your search","Empty result",JOptionPane.WARNING_MESSAGE);
			}
		}catch(SQLException e7){
			//e7.printStackTrace();
			JOptionPane.showMessageDialog(null, "Something went wrong with Database (SQL exception)","Error",JOptionPane.ERROR_MESSAGE);
		}
	};
	
	static ActionListener doUpdateListener = (e)->{
		try{
			String query = "UPDATE vocabulary set FOREIGN_W=?, NATIVE_W=? where ID=?";
			PreparedStatement preparedStm = MainMenu.conn.prepareStatement(query);
			preparedStm.setString(1, UpdateWords.textFieldUpForeign.getText());
			preparedStm.setString(2, UpdateWords.textFieldUpNative.getText());
			preparedStm.setInt(3, Integer.parseInt(UpdateWords.textFieldID.getText()));
			
			preparedStm.executeUpdate();
			
			JOptionPane.showMessageDialog(null,"Update done","Update",JOptionPane.PLAIN_MESSAGE);
			preparedStm.close();
			
		}catch(SQLException e2){
			//e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Something went wrong with your update (SQL exception) make sure you don't have that word in your database","Error",JOptionPane.ERROR_MESSAGE);
		}
	};
	
	static ActionListener doDeleteListener = (e)->{
		try{
			String query = "DELETE from vocabulary WHERE ID =?";
			PreparedStatement preparedStm = MainMenu.conn.prepareStatement(query);
			preparedStm.setInt(1,Integer.parseInt(UpdateWords.textFieldID.getText()));
			
			int dialogButton;
			dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure ?","Warning",JOptionPane.YES_NO_OPTION);
			
			if (dialogButton ==JOptionPane.YES_OPTION) preparedStm.execute();
			
		}catch(SQLException e3){
			//e3.printStackTrace();
			JOptionPane.showMessageDialog(null, "Something went wrong with Database (SQL exception)","Error",JOptionPane.ERROR_MESSAGE);
		}
	};
	
	static ActionListener typeTrainCheckListener = (e)->{
		turns++;
		if(TypeTrain.textField2.getText().equals(TypeTrain.answer)){ //check if the answer is correct
			TypeTrain.textField2.setForeground(new Color(0, 255, 0)); //set Green if is correct
			TypeTrain.textField2.setEditable(false);                 // turn the field not editable
			correct++;
		}
		else{
			TypeTrain.textField2.setForeground(new Color(255, 0, 0)); //set red
			TypeTrain.textField2.setEditable(false);
			TypeTrain.textField2.setText(TypeTrain.textField2.getText()+"   correct: "+TypeTrain.answer); //view of correct answer
			
		}
		
	};
	
	static ActionListener typeTrainNextListener = (e)->{ //next word 
		try{
			TypeTrain.textField2.setEditable(true); //editable again
			TypeTrain.textField2.setForeground(new Color(0, 0, 0)); //black text color
			TypeTrain.textField2.setText("");
			if(Model.turns%2==0){ //alternate the demanded word
				if (TypeTrain.rs.next()){
					TypeTrain.textField1.setText(TypeTrain.rs.getString("NATIVE_W"));
					TypeTrain.answer = TypeTrain.rs.getString("FOREIGN_W");
				}
			}
			else{
				if (TypeTrain.rs.next()){
					TypeTrain.textField1.setText(TypeTrain.rs.getString("FOREIGN_W"));
					TypeTrain.answer = TypeTrain.rs.getString("NATIVE_W");
				}
			}
			
			
		}catch(SQLException e8){
			//e8.printStackTrace();
			JOptionPane.showMessageDialog(null, "Something went wrong with Database (SQL exception)","Error",JOptionPane.ERROR_MESSAGE);
		}
	};
	
	
	static ActionListener multipleTrainNext = (e)->{
		try{																// take a demanded word randomly
			String sql="SELECT ID, FOREIGN_W, NATIVE_W FROM VOCABULARY ORDER BY RAND() LIMIT 100";
			MultipleTrain.pst=MainMenu.conn.prepareStatement(sql);
			MultipleTrain.rs=MultipleTrain.pst.executeQuery();
			if(turns%2==0){ // query is for Native 
				if (MultipleTrain.rs.next()){
					MultipleTrain.textField.setText(MultipleTrain.rs.getString("FOREIGN_W"));
					MultipleTrain.correctAnswer = MultipleTrain.rs.getString("NATIVE_W");
					MultipleTrain.answer1= MultipleTrain.rs.getString("NATIVE_W");
				}
				if (MultipleTrain.rs.next()){                                   // take a wrong answer
					MultipleTrain.answer2 = MultipleTrain.rs.getString("NATIVE_W");
				}
				if (MultipleTrain.rs.next()){									// take an other wrong answer
					MultipleTrain.answer3 = MultipleTrain.rs.getString("NATIVE_W");
				}
			}
			else{ //query is for foreign
				if (MultipleTrain.rs.next()){
					MultipleTrain.textField.setText(MultipleTrain.rs.getString("NATIVE_W"));
					MultipleTrain.correctAnswer = MultipleTrain.rs.getString("FOREIGN_W");
					MultipleTrain.answer1= MultipleTrain.rs.getString("FOREIGN_W");
				}
				if (MultipleTrain.rs.next()){                                   // take a wrong answer
					MultipleTrain.answer2 = MultipleTrain.rs.getString("FOREIGN_W");
				}
				if (MultipleTrain.rs.next()){									// take an other wrong answer
					MultipleTrain.answer3 = MultipleTrain.rs.getString("FOREIGN_W");
				}
			}
			List <String> answers = new ArrayList<>(); // add the answers in ArrayList
			answers.add(MultipleTrain.answer1);
			answers.add(MultipleTrain.answer2);
			answers.add(MultipleTrain.answer3);
			Collections.sort(answers);                 // sort the answers 
			MultipleTrain.textFieldChoice1.setText(answers.get(0));
			MultipleTrain.textFieldChoice2.setText(answers.get(1));
			MultipleTrain.textFieldChoice3.setText(answers.get(2));
			MultipleTrain.textFieldChoice1.setForeground(new Color(0, 0, 0));
			MultipleTrain.textFieldChoice2.setForeground(new Color(0, 0, 0));
			MultipleTrain.textFieldChoice3.setForeground(new Color(0, 0, 0));
			
		}catch(SQLException e8){
			//e8.printStackTrace();
			JOptionPane.showMessageDialog(null, "Something went wrong with Database (SQL exception)","Error",JOptionPane.ERROR_MESSAGE);
		}
	};
	
	static class typeTrainMouseListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) { // when you clicked everywhere with your mouse the progress bar refreshed
			JProgressBar progressBar = new JProgressBar();
			progressBar.setBounds(12, 367, 185, 19);
			TypeTrain.contentPane.add(progressBar);
			progressBar.setValue((Model.correct*100)/Model.turns);
			progressBar.setStringPainted(true);
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
	static class multipleChoiceOneMouseListener implements MouseListener{
		public void mouseClicked(MouseEvent e) { //when you clicked with your mouse in textfield turn red or green depends from your answer
			turns++;
			if(MultipleTrain.correctAnswer.equals(MultipleTrain.textFieldChoice1.getText())){
				MultipleTrain.textFieldChoice1.setForeground(new Color(0, 255, 0));
				correct++;
			}
			else{
				MultipleTrain.textFieldChoice1.setForeground(new Color(255, 0, 0));
			}
			JProgressBar progressBar = new JProgressBar();
			progressBar.setStringPainted(true);
			progressBar.setBounds(12, 291, 181, 24);
			MultipleTrain.contentPane.add(progressBar);
			progressBar.setValue((correct*100)/turns);
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}

	static class multipleChoiceTwoMouseListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			turns++;
			if(MultipleTrain.correctAnswer.equals(MultipleTrain.textFieldChoice2.getText())){
				MultipleTrain.textFieldChoice2.setForeground(new Color(0, 255, 0));
				correct++;
			}
			else{
				MultipleTrain.textFieldChoice2.setForeground(new Color(255, 0, 0));
			}
			JProgressBar progressBar = new JProgressBar();
			progressBar.setStringPainted(true);
			progressBar.setBounds(12, 291, 181, 24);
			MultipleTrain.contentPane.add(progressBar);
			progressBar.setValue((correct*100)/turns);
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
	static class multipleChoiceThreeMouseListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			turns++;
			if(MultipleTrain.correctAnswer.equals(MultipleTrain.textFieldChoice3.getText())){
				MultipleTrain.textFieldChoice3.setForeground(new Color(0, 255, 0));
				correct++;
			}
			else{
				MultipleTrain.textFieldChoice3.setForeground(new Color(255, 0, 0));
			}
			JProgressBar progressBar = new JProgressBar();
			progressBar.setStringPainted(true);
			progressBar.setBounds(12, 291, 181, 24);
			MultipleTrain.contentPane.add(progressBar);
			progressBar.setValue((correct*100)/turns);
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
}

