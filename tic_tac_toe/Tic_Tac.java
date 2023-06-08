package tic_tac_toe;

public class Tic_Tac {
	static boolean play_first=false, pc=true;
	static int N=3;
	static Interface frame;
	static ComputerPlayer PCP= new ComputerPlayer();
	
	public static void main(String[] args) {
		frame= new Interface();
		if(play_first) PCP.play();
	}
	
	static void reset() {
		Cell_Button.x_stack.clear();
		Cell_Button.o_stack.clear();
		Interface.stack.clear();
		Interface.all_buttons.clear();
		
		frame.setVisible(false);
		frame= new Interface();
		if(play_first) PCP.play();
	}
	static void undo() {
		final Cell_Button lastButton= Interface.stack.pop();
		switch(lastButton.getLabel().charAt(0)) {
			case 'X': Cell_Button.x_stack.pop();break;
			case 'O': Cell_Button.o_stack.pop();break;
		}
		lastButton.renew();
		ComputerPlayer.last_computer_move=Cell_Button.o_stack.lastElement();
	}
}
