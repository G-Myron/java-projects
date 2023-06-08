package tic_tac_toe;
import java.awt.*;

public class Cell_Button extends Button{
	private static final long serialVersionUID = 1;
	private int x,y;
	static Buttons_Stack x_stack= new Buttons_Stack("X"), o_stack= new Buttons_Stack("O");
	boolean enabled=true;
	
	Cell_Button(int X, int Y){
		super(" ");
		this.x=X;
		this.y=Y;
		this.setBounds(100*y,100*x, 100,100);
		this.setBackground(Color.yellow);
		this.addMouseListener(new Button_Click(this));
		this.setName("B"+String.valueOf(y+Tic_Tac.N*x-Tic_Tac.N-1));
	}
	
	boolean set_Value(char xo) {
		if(xo=='X') {this.enabled=false;this.setLabel("X"); if(x_stack.end_of_game(this,true)) return Interface.all_buttons.enabledAll(false);}
		if(xo=='O') {this.enabled=false;this.setLabel("O"); if(o_stack.end_of_game(this,true)) return Interface.all_buttons.enabledAll(false);}
		if(xo==' ') return false;
		return true;}
	
	int get_xy(char xy) {
		switch(xy) {
		case 'x':return this.x;
		case 'y':return this.y;
		default: return 0;
	}}
	
	void renew() {
		this.setLabel(" ");
		this.setBounds(100*y,100*x, 100,100);
		this.setBackground(Color.yellow);
		this.setForeground(Color.black);
		this.addMouseListener(new Button_Click(this));
		this.setName("B"+String.valueOf(y+Tic_Tac.N*x-Tic_Tac.N-1));
		this.enabled=true;
	}
}
