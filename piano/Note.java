package piano;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class Note extends Component { //JPanel //Panel
	private static final long serialVersionUID = 1L;
	static boolean Erase_all_notes=false;
	static final int diameter=30, notes_distance=5*diameter/3;
	protected int position, notes_played;
	private boolean set_sharp;
	protected int duration_1=0;
	
	Note(int vertical, int horizontal, boolean sharp){
		super();
		this.setSize(Pentagram.full_width,Pentagram.Pentagram_height);
		
		this.position=vertical;
		this.notes_played=(sharp)? horizontal+1:horizontal;
		this.set_sharp=sharp;
		Erase_all_notes=false;
	}
	
	public void paint(Graphics g) {
		//g.fillOval(diameter + notes_distance*notes_played, position-diameter/2, diameter, diameter);
		
		for(int i=Pentagram.upper_pentagram_note; i>=position-1; i-=Pentagram.lines_distance) //i>=position-1 για να ισχύει και όταν το lines_distance είναι μονό.
			g.fillRect(diameter + notes_distance*notes_played -diameter/4, i, diameter+diameter/2,4);
		for(int i=Pentagram.lower_pentagram_note; i<=position; i+=Pentagram.lines_distance)
			g.fillRect(diameter + notes_distance*notes_played -diameter/4, i, diameter+diameter/2,4);
		
		if(Erase_all_notes) this.setVisible(false);
		if(set_sharp) this.hastag(g, diameter + notes_distance*notes_played -diameter, position-diameter/2+1);
	}
	void hastag(Graphics g, int x, int y) {
		final int width= (int) (diameter/6);
		final int dist= (int) (diameter/3)-(int)(2*width/3);
		//g.drawRect(x,y, diameter, diameter);
		g.fillRect(x+dist,y, width, diameter);
		g.fillRect(x+2*dist+width,y, width, diameter);
		g.fillRect(x,y+dist, diameter, width);
		g.fillRect(x,y+2*dist+width, diameter, width);
	}
	
	int get_tone() {
		int note= this.position + Pentagram.first_line-Pentagram.Pentagram_height;
		note/=-Pentagram.lines_distance/2; //Distance from 4E
		note+=2+4*7; //Distance from 4C
		
		if(set_sharp) return Modifiers.note_to_tone(note/7, note%7)+1;
		else return Modifiers.note_to_tone(note/7, note%7);
	}
	
	boolean is_sharp() { return this.set_sharp; }
	void set_vert_position(int vertical) { this.position=vertical; }
	void set_horiz_position(int horizontal) { this.notes_played=horizontal; }
	void delete_me() { this.setVisible(false); }
	int get_vert() { return this.position; }
	int get_horiz() { return diameter + notes_distance*notes_played; }
	int get_inverse_time() { return duration_1; }
}

class Rest extends Note{
	private static final long serialVersionUID = 1L;
	Rest(int hor) {super(Pentagram.Pentagram_height/2, hor, false); super.duration_1=4;}
	
	public void paint(Graphics g) {super.paint(g);
		g.setColor(Color.gray);
		g.fillOval(diameter + notes_distance*notes_played, Pentagram.first_line, diameter, 4*Pentagram.lines_distance-1);
	}
	int get_tone() {return -1;}
}

class Whole extends Note{
	private static final long serialVersionUID = 1L;
	Whole(int pos, int num, boolean sharp) {super(pos, num, sharp); super.duration_1=1;}
	
	public void paint(Graphics g) {super.paint(g);
		g.drawOval(diameter + notes_distance*notes_played, position-diameter/2, diameter, diameter);
	}
}
class Half extends Whole{
	private static final long serialVersionUID = 1L;
	Half(int pos, int num, boolean sharp) {super(pos, num, sharp); super.duration_1=2;}
	
	public void paint(Graphics g) {super.paint(g);
		g.fillRect(notes_distance*notes_played + 2*(diameter-1), position-2*(diameter-1), 4, 2*diameter);
	}
	
}
class Quarter extends Note{
	private static final long serialVersionUID = 1L;
	Quarter(int pos, int num, boolean sharp) {super(pos, num, sharp); super.duration_1=4;}
	
	public void paint(Graphics g) {super.paint(g);
		g.fillOval(diameter + notes_distance*notes_played, position-diameter/2, diameter, diameter);
		g.fillRect(notes_distance*notes_played + 2*(diameter-1), position-2*diameter, 4, 2*(diameter+1));
	}
}
class Eighth extends Quarter{
	private static final long serialVersionUID = 1L;
	Eighth(int pos, int num, boolean sharp) {super(pos, num, sharp); super.duration_1=8;}
	
	public void paint(Graphics g) {super.paint(g);
		g.fillRect(notes_distance*notes_played + 2*(diameter-1), position-2*(diameter+1), 20,3);
	}
}
class Sixteenth extends Eighth{
	private static final long serialVersionUID = 1L;
	Sixteenth(int pos, int num, boolean sharp) {super(pos, num, sharp); super.duration_1=16;}
	
	public void paint(Graphics g) {super.paint(g);
		g.fillRect(notes_distance*notes_played + 2*(diameter-1), position-2*(diameter-2), 20,3);
	}
}
class ThirtySeconds extends Sixteenth{
	private static final long serialVersionUID = 1L;
	ThirtySeconds(int pos, int num, boolean sharp) {super(pos, num, sharp); super.duration_1=32;}
	
	public void paint(Graphics g) {super.paint(g);
		g.fillRect(notes_distance*notes_played + 2*(diameter-1), position-2*(diameter-5), 20,3);
	}
}
