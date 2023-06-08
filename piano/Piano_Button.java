package piano;
import java.awt.*;

public class Piano_Button extends Button{
	private static final long serialVersionUID = 1L;
	private static int white_button_name=0, black_button_name=0;
	private int x_pos, tone, place;
	private boolean black;
	
	Piano_Button(int X, boolean black, int place) {
		super(" ");
		this.x_pos=X;
		this.tone=X+12*(PianoMain.PIANOS_FIRST_OCTAVE+1);
		this.place=place;
		this.black=black;
		if(black) black_button();
		else white_button();
		this.addMouseListener(new Play_Note(this));
	}
	
	void white_button() {
		this.setBounds(50+PianoMain.WHITE_WIDTH*place, 150, PianoMain.WHITE_WIDTH,PianoMain.WHITE_LENGTH);
		this.setBackground(Color.white);
		/*switch(white_button_name%7){
		case 0: this.setBackground(Color.red);break;
		case 1: this.setBackground(Color.orange);break;
		case 2: this.setBackground(Color.yellow);break;
		case 3: this.setBackground(Color.green);break;
		case 4: this.setBackground(Color.cyan);break;
		case 5: this.setBackground(Color.blue);break;
		case 6: this.setBackground(Color.magenta);break;
		}*/
		this.setName("White" + String.valueOf(white_button_name++));
	}
	void black_button() {
		this.setBounds(50+PianoMain.WHITE_WIDTH*place + PianoMain.WHITE_WIDTH-PianoMain.BLACK_WIDTH/2, 150, PianoMain.BLACK_WIDTH,PianoMain.BLACK_LENGTH);
		this.setBackground(Color.black);
		this.setForeground(Color.white);
		this.setName("Black" + String.valueOf(black_button_name++));
	}
	
	void pressed() {this.setBackground(Color.cyan);}
	void released() {
		if(black) this.setBackground(Color.black);
		else this.setBackground(Color.white);
	}
	
	String get_note() {return Modifiers.tone_to_name(this.tone);}
	
	void reset_tone() {this.tone=this.x_pos+12*(PianoMain.PIANOS_FIRST_OCTAVE+1);}
	int get_tone() { return this.tone;}
	boolean isBlack() { return this.black;}
	//boolean isPressed() { return this.getBackground()==Color.cyan;}
	
}
