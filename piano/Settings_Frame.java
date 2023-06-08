package piano;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;
import javax.swing.JOptionPane;

public class Settings_Frame extends Frame{
	private static final long serialVersionUID = 1L;
	private final Button save_button= new Button(), default_button= new Button(), restart_button= new Button();
	private final Stack<TextField> displays= new Stack<>();
	private final Stack<Checkbox> checkers= new Stack<>();
	private final String[] inputs= {"Tempo Beats/Min","First octave"};
	private final String[] checks= {"Draw notes"};
	
	Settings_Frame(){
		super("Piano Settings");
		this.setLayout(null);
		this.setBounds(100,100,1000,500);
		this.addWindowListener(new CloseFrame());
		this.setBackground(new Color(125,200,255));
		this.setFont(new Font("TimesRoman",Font.PLAIN,20));
		
		default_button.setBounds(10, 400, 200, 50);
		default_button.setBackground(Color.red);
		default_button.addActionListener(new SetValues());
		default_button.setLabel("Set default values");
		this.add(default_button);
		save_button.setBounds(210, 400, 100, 50);
		save_button.setBackground(Color.orange);
		save_button.addActionListener(new GetValues());
		save_button.setLabel("Save");
		this.add(save_button);
		restart_button.setBounds(110, 450, 200, 40);
		restart_button.setBackground(Color.lightGray);
		restart_button.addActionListener(new ResetMain());
		restart_button.setLabel("Restart");
		this.add(restart_button);
		
		
		add_input_takers(new Label(), new TextField(), inputs[0]+" =", 240000/PianoMain.TEMPO, 50);
		add_input_takers(new Label(), new TextField(), inputs[1]+" =",PianoMain.PIANOS_FIRST_OCTAVE, 100);
		
		add_check_takers(new Label(), new Checkbox(), checks[0], true, 50);
	}
	
	private void add_input_takers(Label label, TextField display, String text, int constant, int y_pos) {
		label.setBounds(10, y_pos, 200, 40);
		label.setFont(new Font("TimesRoman",Font.PLAIN,22));
		label.setBackground(new Color(20,0,140));
		label.setForeground(Color.white);
		label.setText(text);
		this.add(label);
		display.setBounds(210, y_pos, 100, 40);
		display.setFont(new Font("TimesRoman",Font.PLAIN,22));
		display.setBackground(Color.yellow);
		display.setText(String.valueOf(constant));
		display.addKeyListener(new EnterListener());
		this.add(display);
		this.displays.push(display);
	}
	
	private void add_check_takers(Label label, Checkbox check, String text, boolean initial, int y_pos) {
		label.setBounds(500, y_pos, 150, 40);
		label.setFont(new Font("TimesRoman",Font.PLAIN,22));
		label.setBackground(new Color(20,0,140));
		label.setForeground(Color.white);
		label.setText(text);
		this.add(label);
		check.setBounds(650, y_pos, 50, 40);
		check.setBackground(Color.yellow);
		check.addItemListener(new Checker());
		check.setState(initial);
		this.add(check);
		this.checkers.push(check);
	}
	
	void setDefault() {
		PianoMain.TEMPO=2400;
		PianoMain.PIANOS_FIRST_OCTAVE=4;
		Pentagram.set_drawing(true);
		
		displays.get(0).setText(String.valueOf(240000/PianoMain.TEMPO));
		displays.get(1).setText(String.valueOf(PianoMain.PIANOS_FIRST_OCTAVE));
		checkers.get(0).setState(true);
	}
	
	void getInputs() {
		int[] values= {0,0};
		for(int i=0; i<2; i++)
			try { values[i]= toInt(displays.get(i).getText(),inputs[i]);
			}catch(IntException e) {return;}
		
		change_tempo(values[0]);
		change_first_oct(values[1]);
		Settings_Frame.this.setVisible(false);
	}
	void change_tempo(int tempo_bpm) {
		PianoMain.TEMPO=240000/tempo_bpm;
	}
	void change_first_oct(int oct) {
		PianoMain.PIANOS_FIRST_OCTAVE= oct;
		for(int i=0; i<Piano_GUI.buttons_list.size(); i++)
			Piano_GUI.buttons_list.get(i).reset_tone();
	}
	
	private Integer toInt(String text, String errormessage) throws IntException{
		if(text.isEmpty()) throw new IntException(errormessage);
		for(int i=0; i<text.length(); i++)
			if("0123456789".indexOf(text.charAt(i))==-1) throw new IntException(errormessage);
		return Integer.parseInt(text);
	}
	
	void set_focus() {displays.get(0).requestFocus();}
	
	private class GetValues implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			Settings_Frame.this.getInputs();}
	}
	private class SetValues implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			Settings_Frame.this.setDefault();}
	}
	
	private class ResetMain implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			PianoMain.reset();
			Settings_Frame.this.setVisible(false);
		}
	}
	
	private class Checker implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			Pentagram.set_drawing(checkers.get(0).getState());
		}
	}
	
	private class IntException extends Exception{
		private static final long serialVersionUID = 1L;
		private static final String Message= " has to be an INTEGER";
		
		IntException(String name){ JOptionPane.showMessageDialog(null, name+Message, "ERROR", JOptionPane.ERROR_MESSAGE);}
	}
	private class EnterListener implements KeyListener{
		
		public void keyPressed(KeyEvent key) {
			if(KeyEvent.getKeyText(key.getKeyCode()).equals("Enter")) Settings_Frame.this.getInputs();}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent key) {}
	}
	private final class CloseFrame extends WindowAdapter{
		public void windowClosing(WindowEvent closeWindowAndExit){
			Settings_Frame.this.setVisible(false);
		}
	}
}
