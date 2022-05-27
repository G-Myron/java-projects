package piano;
import java.util.Stack;

public class Auto_Player extends Thread{
	private static boolean read_notes=true, dont_play=false;
	private static String[] piece= null;
	private int num_of_notes=0;
	private final Stack<Integer> tones= new Stack<>();
	private final Stack<Integer> durations= new Stack<>();
	
	public void run() {
		dont_play=false;
		if(read_notes) this.num_of_notes=Pentagram.NotesPlayed.size();
		else this.num_of_notes=piece.length;
		this.play_sequence();
	}
	
	private void play_sequence() {
		if(read_notes) read_pentagram();
		else read_list(piece);
		
		//for(int t=0; t<2; t++)
		for(int i=0; i<num_of_notes; i++)
			play(tones.elementAt(i),durations.elementAt(i));
	}
	
	private void play(int tone, int duration_1) {
		if(dont_play) return;
		if(tone>=0) {
			Play_Tone.start_note(tone);
			Play_Tone.stop_note((int) (PianoMain.TEMPO/duration_1));
		}
		else try {Thread.sleep((int) (PianoMain.TEMPO/duration_1));
			} catch (InterruptedException e) {e.printStackTrace();}
		if(!read_notes) PianoMain.pentagram.draw_note(Modifiers.tone_to_name(tone), duration_1);
	}
	
	
	private void read_pentagram() {
		for(int x=0; x<num_of_notes; x++) {
			tones.push(Pentagram.NotesPlayed.elementAt(x).get_tone());
			durations.push(Pentagram.NotesPlayed.elementAt(x).get_inverse_time());
	}}
	
	private void read_list(String[] notes){
		String name,time_1="8";
		
		for(int i=0; i<notes.length; i++) {
			name=notes[i].split("_")[0];
			tones.push(Modifiers.name_to_tone(name));
			if(notes[i].split("_").length>1) time_1=notes[i].split("_")[1];
			durations.push(Integer.valueOf(time_1));
	}}
	
	
	static void setPiece(String[] pce) {
		read_notes=false;
		if(pce==null) read_notes=true;
		else piece= pce;
	}
	static void stop_playing() {dont_play=true;}
}
