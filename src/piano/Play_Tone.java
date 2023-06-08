package piano;
import javax.sound.midi.*;

public class Play_Tone {
	private static MidiChannel[] channels;
	private static int VOLUME = 100, INSTRUMENT = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments, up to 15.
	private static Synthesizer synth = null;
	private static Piano_Button button_playing=null;
	private static long StartTime=0;
	static Sequencer player;
	
	Play_Tone(){
		try { player= MidiSystem.getSequencer(); }
		catch (MidiUnavailableException e) {e.printStackTrace();}
	}
	
	static void start_note(int note) { //from 0 to 127 (60 = Middle C).
		StartTime=System.currentTimeMillis();
		try {
			synth = MidiSystem.getSynthesizer();
			channels = synth.getChannels();
			
			synth.open();
			channels[INSTRUMENT].noteOn(note, VOLUME );
		}
		catch (Exception e) {throw new RuntimeException(e);}
	}
	static void stop_note() {
		long duration = System.currentTimeMillis()-StartTime;
		int time_1 = PianoMain.TEMPO/(int)duration;
		if(button_playing !=null) {
			Piano_GUI.display.add_to_display(" "+button_playing.get_note()+"1/"+String.valueOf(time_1));
			PianoMain.pentagram.draw_note(button_playing.get_note(), time_1);
			button_playing.released();
			stop_note(0);
		}
	}
	
	static void play_note(int note) {
		try{
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			ShortMessage a = new ShortMessage();
			
			a.setMessage(ShortMessage.NOTE_ON, INSTRUMENT, note, VOLUME);
			track.add(new MidiEvent(a, 0));
			
			player.open();
			player.setSequence(seq);
			player.start();
		}
		catch (Exception e) {throw new RuntimeException(e);}
	}
	
	
	static void play_note(Piano_Button button) {
		Piano_GUI.display.add_to_display(" "+button.get_note());
		button.pressed();
		button_playing=button;
		play_note(button.get_tone());
	}
	static void start_note(Piano_Button button) {
		button_playing=button;
		start_note(button.get_tone());
		button.pressed();
	}
	static void stop_note(int millisec_duration) {
		if(synth != null && synth.isOpen()) {
			try {Thread.sleep(millisec_duration);}
			catch (InterruptedException e) {e.printStackTrace();}
			synth.close();
		}
	}
}
