package piano;
import java.util.Random;

public class Pieces {
	//4C_16 means a sixteenth of note 4C. 5D#_2 means a half of note 5D#.
	//If no time, the note has the previous time. default is eighths.
	//5E_8 5C 4F 3D_3 means 5E 5C 4F of eighths and 3D of duration a third.
	
	/*tempo=75*/static final String Fur_Elise= "5E_16 5D# 5E 5D# 5E 4B 5D 5C 4A_8  4C_16 4E 4A 4B_8  4E_16 4G# 4B 5C_8  "
			+ "5E_16 5D# 5E 5D# 5E 4B 5D 5C 4A_8  4C_16 4E 4A 4B_8  4D_16 5C 4B 4A_4 ";
	/*tempo=176*/static final String La_Campanella= "5D#_16 6D# 6D# 6C# 5B 5B 5A# 5G# 5G 5G# 5A# 5D# 5D# 5E 5D# 5C# 4B 4B 4A# 4G# 4G 4G# 4A# 4D# "
			+ "5D# 6D# 6D# 6C# 5B 5B 5A# 5G# 5G 5G# 5A# 5D# 5D# 5E 5D# 5C# 5G# 5B 6D# 5G 5B 6D# 5D# ";
	/*tempo=200*/static final String Mozart_40= "5D#_8 5D 5D_4 5D#_8 5D 5D_4 5D#_8 5D 5D_4 5A#  5A#_8 5A 5G_4 5G_8 5F 5D#_4 5D#_8 5D 5C_4 5C  "
			+ "5D_8 5C 5C_4 5D_8 5C 5C_4 5D_8 5C 5C_4 5A  5A_8 5G 5F#_4 5F#_8 5D# 5D_4 5D_8 5C 4A#_4 4A#  "
			+ "5A#_8 5A 5A_4 6C 5F# 5A 5G 5D  5A#_8 5A 5A_4 6C 5F# 5A 5G 5A# 5A_8 5G 5F 5D# 5D_4 ";
	/*tempo=175*/static final String Alla_Turca= "4B_16 4A 4G# 4A 5C_8  5D_16 5C 4B 5C 5E_8  5F_16 5E 5D# 5E 5B 5A 5G# 5A 5B 5A 5G# 5A 6C_4 "
			+ "5A_8 6C 5B 5A 5G 5A 5B 5A 5G 5A 5B 5A 5G 5F# 5E_4 5E ";
	
	static String random_notes() {
		String piece="";
		Random r=new Random();
		int[] tones= r.ints(10, Modifiers.name_to_tone("4C"), Modifiers.name_to_tone("5C")).toArray(); //12=0C...131==9C.
		for(int i=0; i<tones.length; i++) piece+= Modifiers.tone_to_name(tones[i]) +" ";
		System.out.println(piece);
		return piece;
	}
}
