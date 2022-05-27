package piano;
import java.awt.Color;
import java.awt.TextField;

public class Display extends TextField{
	private static final long serialVersionUID = 1L;
	
	Display(){
		super();
		this.setBounds(50, 50, 200, 50);
		this.setBackground(Color.black);
		this.setForeground(Color.white);
		this.addKeyListener(new Note_Pressed());
	}
	
	void add_to_display(String s) {
		this.setText(/*this.getText() + */s);
	}
	
	void delete_last_note() {
		String text_list[] = this.getText().split(" ");
		String text=text_list[0];
		for(int i=1; i<text_list.length-1; i++) text+=" "+text_list[i];
		this.setText(text);
	}
	void clear() {this.setText("");}
}
