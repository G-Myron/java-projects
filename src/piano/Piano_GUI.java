package piano;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;

public class Piano_GUI extends Frame {
	private static final long serialVersionUID = 1L;
	private final int notes_number=12*PianoMain.OCTAVES;
	private int whites_num=0,blacks_num=0;
	static final Stack<Piano_Button> buttons_list = new Stack<>();
	static final Display display= new Display();
	private final Settings_Frame settingsFrame= new Settings_Frame();
	private final PieceChooser chooser= new PieceChooser();
	
	Piano_GUI(){
		super("Myron Piano");
		this.setLayout(null);
		this.setBounds(0, 0, 100*notes_number, PianoMain.SCREEN_HEIGHT/2);
		this.setExtendedState(Frame.MAXIMIZED_HORIZ);
		this.setBackground(Color.gray);
		this.setFont(new Font("TimesRoman",Font.PLAIN,25));
		this.addWindowListener(new EndProgram());
		
		boolean is_black=false;
		int position=0;
		
		for(int x=0; x<=notes_number; x++) {
			final Piano_Button button= new Piano_Button(x,is_black, position);
			buttons_list.add(button);
			if(is_black) {this.add(button); blacks_num++;}
			else whites_num++;
			
			if(x%12 !=4 && x%12 !=11) is_black= !is_black;// if not E or B note, the next has opposite color.
			else blacks_num++;
			
			if(is_black) position=blacks_num; else position=whites_num;
		}
		for(int i=0; i<=notes_number; i++) {
			final Piano_Button button = buttons_list.get(i);
			if(! button.isBlack()) this.add(button);
		}
		
		
		this.add(display);
		this.add(new CommandButton("Clear sheets", 300, 50, new ClearPentagram()));
		this.add(new CommandButton("Delete last", 500, 50, new DeleteLastNote()));
		this.add(new CommandButton("Rest", 700, 50, new AddRest()));
		this.add(new CommandButton("AutoPlay", 900, 50, new PlayAuto()));
		this.add(chooser.set(900, 100));
		
		CommandButton settings= new CommandButton("Settings", 1100, 50, new OpenSet());
		settings.setBackground(Color.white);
		this.add(settings);
		
		
		this.setVisible(true);
	}
	
	void renew() {
		display.clear();
		this.settingsFrame.setDefault();
		chooser.reset();
		this.toFront();
	}
	
	
	
	private class CommandButton extends Button {
		private static final long serialVersionUID = 1L;
		CommandButton(String label, int x, int y, ActionListener listener){
			super(label);
			this.setBounds(x, y, 180,50);
			this.setBackground(Color.blue);
			this.setForeground(Color.orange);
			this.addActionListener(listener);
		}
	}
	
	private final class ClearPentagram implements ActionListener{	public void actionPerformed(ActionEvent e) {
			PianoMain.pentagram.clear();
			Piano_GUI.display.clear();
	}}
	private final class DeleteLastNote implements ActionListener{	public void actionPerformed(ActionEvent e) {
			PianoMain.pentagram.delete_last();
			Piano_GUI.display.delete_last_note();
	}}
	private final class PlayAuto implements ActionListener{	public void actionPerformed(ActionEvent e) {
			final Auto_Player player= new Auto_Player();
			player.start();
	}}
	private final class AddRest implements ActionListener{	public void actionPerformed(ActionEvent e) {
			PianoMain.pentagram.draw_note(null, 0);
	}}
	private final class OpenSet implements ActionListener{	public void actionPerformed(ActionEvent e) {
		settingsFrame.set_focus();
		settingsFrame.setVisible(true);
	}}
	
	private final class EndProgram extends WindowAdapter{
		public void windowClosing(WindowEvent closeWindowAndExit){System.exit(0);}
	}
}
