package tic_tac_toe;
import java.util.*;

public class ComputerPlayer {
	private final Buttons_Stack availables= new Buttons_Stack("");
	static Cell_Button last_computer_move;
	String hardness="";
	
	void play() {find_availables();
		Cell_Button button=null;
		if(hardness.equals("Easy")) button= choose_cell_easy();
		else if(hardness.equals("Medium")) button= choose_cell_medium();
		else button= choose_cell();
		
		try {Thread.sleep(200, 0);} catch (InterruptedException e){}
		if(button.enabled) {
			Interface.stack.push(button);
			Button_Click.continue_game= button.set_Value('O');
		}
		else System.out.printf("Button %s Disabled",button.getName());
		last_computer_move=button;
	}
	
	private Cell_Button choose_cell() {
		if(Tic_Tac.N%2==1 && availables.contains(Interface.all_buttons.elementAt(Tic_Tac.N*Tic_Tac.N/2)))
			return Interface.all_buttons.elementAt(Tic_Tac.N*Tic_Tac.N/2);
		
		Cell_Button attacker= Buttons_Stack.empty_neighbor(last_computer_move,"O");
		if(attacker!=null)return attacker;
		
		Cell_Button survivor= Buttons_Stack.empty_neighbor(Interface.stack.lastElement(),"X");
		if(survivor!=null)return survivor;
		
		for(int i=1; i<=Tic_Tac.N; i++) {
			if(availables.contains(Interface.all_buttons.button_at(i, i))) return Interface.all_buttons.button_at(i, i);
			if(availables.contains(Interface.all_buttons.button_at(i, Tic_Tac.N+1-i))) return Interface.all_buttons.button_at(i, Tic_Tac.N+1-i);
		}
		
		System.out.println("Random");
		return availables.elementAt(new Random().nextInt(availables.size()));
	}
	
	private Cell_Button choose_cell_medium() {
		
		Cell_Button attacker= Buttons_Stack.empty_neighbor(last_computer_move,"O");
		if(attacker!=null)return attacker;
		
		Cell_Button survivor= Buttons_Stack.empty_neighbor(Interface.stack.lastElement(),"X");
		if(survivor!=null)return survivor;
		
		System.out.println("Random");
		return availables.elementAt(new Random().nextInt(availables.size()));
	}
	
	private Cell_Button choose_cell_easy() {
		return availables.elementAt(new Random().nextInt(availables.size()));
	}
	
	void find_availables() {
		availables.clear();
		for(int i=0; i<Interface.all_buttons.size(); i++)
			if(! Interface.stack.contains((Interface.all_buttons.elementAt(i))))
				availables.push(Interface.all_buttons.elementAt(i));
	}
}
