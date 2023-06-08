package piano;
import java.util.Stack;

public class PianoMain {
	static final int WHITE_WIDTH=80, WHITE_LENGTH=200, BLACK_WIDTH=60, BLACK_LENGTH=150;
	static final int SCREEN_WIDTH =java.awt.Toolkit.getDefaultToolkit().getScreenSize().width,
			SCREEN_HEIGHT =java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	static int OCTAVES=2; //notes_number=12*OCTAVES;
	static int PIANOS_FIRST_OCTAVE=4;
	static int TEMPO=2400; // milliseconds in a meter.
	
	static private Piano_GUI piano = new Piano_GUI();
	static Pentagram pentagram= new Pentagram();
	static Stack<Pentagram> previous_sheets= new Stack<>();
	
	public static void main(String[] args) {
		new Play_Tone();
	}
	
	static void reset() {
		newPentagram();
		pentagram.clear();
		piano.renew();
	}
	
	static Pentagram newPentagram() {
		previous_sheets.push(pentagram);
		pentagram= new Pentagram();
		return pentagram;
	}
	static void deletePentagrams() {
		for(int i=0; i<previous_sheets.size(); i++) previous_sheets.get(i).dispose();
		previous_sheets.clear();
	}
}