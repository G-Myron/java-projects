package tic_tac_toe;
import java.awt.event.*;

class Button_Click implements MouseListener{
	private final String chars=" X O";
	private Cell_Button button;
	static boolean continue_game=true;
	Button_Click(Cell_Button B){button=B;}
	
	public void mousePressed(MouseEvent E) {
		try {
			if(button.enabled) {
				Interface.stack.push(button);
				continue_game= button.set_Value(chars.charAt(E.getButton()));
				if(continue_game && Tic_Tac.pc)
					Tic_Tac.PCP.play();
		}}
		catch(Exception e){}
	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}

class Other_Click implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Restart Game"))Tic_Tac.reset();
		if(e.getActionCommand().equals("<-Undo")) if(Button_Click.continue_game &&! Interface.stack.empty()) Tic_Tac.undo();
		if(e.getActionCommand().equals("Play Second/First"))Tic_Tac.play_first= ! Tic_Tac.play_first;
		if(e.getActionCommand().equals("Alone / vsPC"))Tic_Tac.pc= ! Tic_Tac.pc;
	}
}
class Choice_Click implements ItemListener{
	public void itemStateChanged(ItemEvent e) {
		if(e.getItem().equals("2 players"))Tic_Tac.pc= false;
		if(e.getItem().equals("Play with robot"))Tic_Tac.pc= true;
		Tic_Tac.PCP.hardness = (String) e.getItem();
	}
}
class DisplayReader implements KeyListener{
	public void keyTyped(KeyEvent e) {
		if("23456789".contains(KeyEvent.getKeyText(e.getKeyChar()))) Tic_Tac.N = e.getKeyChar()-48;
		if(KeyEvent.getKeyText(e.getKeyChar()).equals("Enter")) Tic_Tac.reset();
	}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}

class EndGame extends WindowAdapter{
	public void windowClosing(WindowEvent closeWindowAndExit){
		System.exit(0);}
}
