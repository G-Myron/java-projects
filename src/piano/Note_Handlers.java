package piano;
import java.awt.event.*;

class Play_Note implements MouseListener{
	private Piano_Button button;
	static boolean mouse_is_pressed=false;
	
	Play_Note(Piano_Button B){button=B;}
	
	public void mousePressed(MouseEvent E) {
		Play_Tone.start_note(button);
		mouse_is_pressed=true;
	}
	public void mouseReleased(MouseEvent e) {
		Play_Tone.stop_note();
		mouse_is_pressed=false;
	}
	
	public void mouseEntered(MouseEvent e) {
		if( mouse_is_pressed) Play_Tone.start_note(button);
	}
	public void mouseExited(MouseEvent e) {
		if( mouse_is_pressed) Play_Tone.stop_note();
	}
	
	public void mouseClicked(MouseEvent e) {}
}

class Note_Pressed implements KeyListener{
	
	public void keyPressed(KeyEvent key) {
		boolean del=true;
		final int oct=PianoMain.PIANOS_FIRST_OCTAVE+1;
		
		if(KeyEvent.getKeyText(key.getKeyCode()).equals("Escape")) System.exit(0);
		else if(KeyEvent.getKeyText(key.getKeyCode()).equals("Caps Lock"))	Play_Tone.play_note(12*oct);
		else if(KeyEvent.getKeyText(key.getKeyCode()).equals("Q"))			Play_Tone.play_note(12*oct+1);
		else if(KeyEvent.getKeyText(key.getKeyCode()).equals("S"))			Play_Tone.play_note(12*oct+2);
		else if(KeyEvent.getKeyText(key.getKeyCode()).equals("E"))			Play_Tone.play_note(12*oct+3);
		else if(KeyEvent.getKeyText(key.getKeyCode()).equals("F"))			Play_Tone.play_note(12*oct+4);
		else if(KeyEvent.getKeyText(key.getKeyCode()).equals("G"))			Play_Tone.play_note(12*oct+5);
		else if(KeyEvent.getKeyText(key.getKeyCode()).equals("Y"))			Play_Tone.play_note(12*oct+6);
		else if(KeyEvent.getKeyText(key.getKeyCode()).equals("J"))			Play_Tone.play_note(12*oct+7);
		else if(KeyEvent.getKeyText(key.getKeyCode()).equals("I"))			Play_Tone.play_note(12*oct+8);
		else if(KeyEvent.getKeyText(key.getKeyCode()).equals("L"))			Play_Tone.play_note(12*oct+9);
		else if(KeyEvent.getKeyText(key.getKeyCode()).equals("P"))			Play_Tone.play_note(12*oct+10);
		else if(KeyEvent.getKeyText(key.getKeyCode()).equals("Quote"))		Play_Tone.play_note(12*oct+11);
		else if(KeyEvent.getKeyText(key.getKeyCode()).equals("Enter"))		Play_Tone.play_note(12*oct+12);
		
		else if(KeyEvent.getKeyText(key.getKeyCode()).equals("Backspace")) {
			Piano_GUI.display.delete_last_note();
			del=false;
		}
		else del=false;
		
		if(del) Piano_GUI.display.clear();
		}
	
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
}
