package piano;

public class Modifiers {
	
	static String tone_to_name(int tone) {
		if(tone<0) return null;
		final String[] Notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
		return String.valueOf(tone/12-1)+Notes[tone%12];
	}
	static int name_to_tone(String name) {
		if(name.length()<2) return -1;
		
		final int octave= name.charAt(0)-48;
		final int value= (name.charAt(1)-65 +5)%7;
		
		if(name.length()>2) return note_to_tone(octave, value)+1;
		else return note_to_tone(octave, value);
	}
	
	static int note_to_tone(int octave, int note_value) {
		//final String note_name= String.valueOf(note/7)+"CDEFGAB".charAt(note%7);
		int tone= 12*(octave+1)+ 2*(note_value%7);
		if(note_value%7 > 2) tone-=1;
		return tone;
	}
}
