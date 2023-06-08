package piano;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;

public class Pentagram extends Frame{
	private static final long serialVersionUID = 1L;
	static final int full_width = PianoMain.SCREEN_WIDTH+10, Pentagram_height=PianoMain.SCREEN_HEIGHT/2, lines_distance=35;
	static final int starting_height=170+PianoMain.WHITE_LENGTH, first_line=Pentagram_height/2-2*lines_distance;
	static final int upper_pentagram_note=Pentagram_height - first_line -10*lines_distance/2;
	static final int lower_pentagram_note=upper_pentagram_note +12*lines_distance/2;
	//private static final String notes_names = "CDEFGAB";
	private static boolean write_notes=true;
	private static int notes_played=0;
	static Stack<Note> NotesPlayed = new Stack<>();
	
	Pentagram(){
		super();
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setBounds(0,starting_height, full_width,PianoMain.SCREEN_HEIGHT-starting_height);
		this.addWindowListener(new ClosePentagram());
		
		for(int n=0; n<5; n++) this.add(nth_line(n));
		
		this.setVisible(true);
	}
	
	Panel nth_line(int n) { // creates the n_th pentagram line.
		final int lines_height =n*lines_distance;
		Panel line= new Panel();
		line.setBackground(Color.black);
		line.setBounds(0,Pentagram_height/*για Panel +starting_height*/- first_line -lines_height,full_width,4);
		return line;
	}
	
	void draw_note(String note, int time_1) {
		Note note_to_play = null;
		Pentagram pent_used= this;
		if(!write_notes) return;
		if(!this.isVisible()) {
			pent_used= PianoMain.newPentagram();
			notes_played=0;
		}
		
		if(note!=null) {
			boolean sharp= (note.length()>=3)? true: false;
			final int octave= (int)note.charAt(0)-48, tone= "CDEFGAB".indexOf(note.charAt(1));
			int position = Pentagram_height - first_line + (int)(((5-octave)*7-(5+tone))*lines_distance*0.5);
			
			if(time_1<=64) {
				if(time_1<=1) note_to_play= new Whole(position, notes_played++, sharp);
				else if(time_1<=2) note_to_play= new Half(position, notes_played++, sharp);
				else if(time_1<=4) note_to_play= new Quarter(position, notes_played++, sharp);
				else if(time_1<=8) note_to_play= new Eighth(position, notes_played++, sharp);
				else if(time_1<=16) note_to_play= new Sixteenth(position, notes_played++, sharp);
				else note_to_play= new ThirtySeconds(position, notes_played++, sharp);
				
				if(sharp) notes_played++;
			}
		}
		else note_to_play= new Rest(notes_played++);
		
		if(note_to_play!=null) {
			pent_used.add(note_to_play);
			pent_used.repaint();
			NotesPlayed.push(note_to_play);
		}
		
		if(notes_played>full_width/Note.notes_distance) { new_page();}
	}
	
	
	void clear() {
		Note.Erase_all_notes=true;
		notes_played=0;
		NotesPlayed.clear();
		PianoMain.deletePentagrams();
		if(!this.isDisplayable()) PianoMain.newPentagram().repaint();
		else this.repaint();
	}
	void new_page() {
		Pentagram pent_used= PianoMain.newPentagram();
		notes_played=1;
		if(!NotesPlayed.isEmpty()) {
			NotesPlayed.lastElement().set_horiz_position(0);
			pent_used.add(NotesPlayed.lastElement());
			pent_used.repaint();
		}
	}
	void delete_last() {
		if(! NotesPlayed.isEmpty()) {
			Note last = NotesPlayed.pop();
			last.delete_me();
			notes_played--;
			if(last.is_sharp()) notes_played--;
		}
	}
	
	static void set_drawing(boolean write) {write_notes=write;}
	
	private class ClosePentagram extends WindowAdapter{
		public void windowClosing(WindowEvent closeWindowAndExit){
			Pentagram.this.dispose();
		}
	}
}
