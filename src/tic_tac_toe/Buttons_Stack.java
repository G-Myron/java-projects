package tic_tac_toe;
import java.awt.Color;
import java.util.Stack;
import javax.swing.JOptionPane;

public class Buttons_Stack extends Stack<Cell_Button>{
	private static final long serialVersionUID=1;
	String name;
	
	Buttons_Stack(String name){
		super();
		this.name=name;}
	
	boolean end_of_game(Cell_Button b, boolean push) {
		if(push)this.push(b);
		final int x=b.get_xy('x'), y=b.get_xy('y');
		int x_s=0,y_s=0,xy_s=0,s_xy=0;
		
		for(int i=0; i<this.size(); i++) {
			if(this.elementAt(i).get_xy('x')==x) x_s++;
			if(this.elementAt(i).get_xy('y')==y) y_s++;
			if(this.elementAt(i).get_xy('x')==this.elementAt(i).get_xy('y')) xy_s++;
			if(this.elementAt(i).get_xy('x')+this.elementAt(i).get_xy('y')==Tic_Tac.N+1) s_xy++;
			
			if(x_s==Tic_Tac.N || y_s==Tic_Tac.N || xy_s==Tic_Tac.N || s_xy==Tic_Tac.N) {
				winning_cells(x,y, x_s,y_s,xy_s,s_xy);
				JOptionPane.showMessageDialog(null, this.name + " Wins");
				return true;
			}
		}
		
		if(Interface.stack.size()==Tic_Tac.N*Tic_Tac.N) {
			JOptionPane.showMessageDialog(null," It's a Tie");
			return true;
		}
		return false;
	}
	
	private void winning_cells(int x,int y, int x_s,int y_s,int xy_s,int s_xy) {
		for(int i=0; i<this.size(); i++)
			if(x_s==Tic_Tac.N && this.elementAt(i).get_xy('x')==x || y_s==Tic_Tac.N && this.elementAt(i).get_xy('y')==y ||
				xy_s==Tic_Tac.N && this.elementAt(i).get_xy('x')==this.elementAt(i).get_xy('y') ||
				s_xy==Tic_Tac.N && this.elementAt(i).get_xy('x')+this.elementAt(i).get_xy('y')==Tic_Tac.N+1){
			this.elementAt(i).setBackground(Color.red);
			this.elementAt(i).setForeground(Color.white);
	}}
	
	static Cell_Button empty_neighbor(Cell_Button b, String XO) {
		if(b==null)return null;
		final int x=b.get_xy('x'), y=b.get_xy('y');
		int x_s=0,y_s=0,xy_s=0,s_xy=0;
		
		for(int i=1; i<=Tic_Tac.N; i++) {
			if(Interface.all_buttons.button_at(x,i).getLabel().equals(XO)) x_s++;
			if(Interface.all_buttons.button_at(i,y).getLabel().equals(XO)) y_s++;
			if(Interface.all_buttons.button_at(i,i).getLabel().equals(XO)) xy_s++;
			if(Interface.all_buttons.button_at(i,Tic_Tac.N+1-i).getLabel().equals(XO)) s_xy++;
		}
		
		if(x_s==Tic_Tac.N-1) for(int i=1; i<=Tic_Tac.N; i++)
			if(Interface.all_buttons.button_at(x,i).getLabel().equals(" ")) return Interface.all_buttons.button_at(x,i);
		if(y_s==Tic_Tac.N-1) for(int i=1; i<=Tic_Tac.N; i++)
			if(Interface.all_buttons.button_at(i,y).getLabel().equals(" ")) return Interface.all_buttons.button_at(i,y);
		if(xy_s==Tic_Tac.N-1) for(int i=1; i<=Tic_Tac.N; i++)
			if(Interface.all_buttons.button_at(i,i).getLabel().equals(" ")) return Interface.all_buttons.button_at(i,i);
		if(s_xy==Tic_Tac.N-1) for(int i=1; i<=Tic_Tac.N; i++)
			if(Interface.all_buttons.button_at(i,Tic_Tac.N+1-i).getLabel().equals(" ")) return Interface.all_buttons.button_at(i,Tic_Tac.N+1-i);
		
		return null;
	}
	
	Cell_Button button_at(int x, int y) {
		for(int i=0; i<this.size(); i++)
			if(this.elementAt(i).get_xy('x')==x && this.elementAt(i).get_xy('y')==y)
				return this.elementAt(i);
		return null;
	}
	
	boolean enabledAll(boolean t_f) {
		for(int i=0; i<this.size(); i++)
			this.elementAt(i).enabled=t_f;
		return t_f;
	}
}
