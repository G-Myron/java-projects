package reverse_polish_notation_calculator;
import java.util.Stack;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculate {
	static Stack<Double> stack= new Stack<Double>();
	static Operate oper_= new Operate(stack);
	static My_Calculator calc = new My_Calculator(oper_);
	
	public static void main(String[] args) {}
}

class Operate{
	private Stack<Double> stack;
	private double number=0, point=0;
	private Frame stack_frame= new Frame("The STACK");
	private JLabel stack_label= new JLabel();
	
	Operate(Stack<Double> st){
		stack_frame.setLocation(900, 100);
		stack_frame.setSize(250, 500);
		stack_frame.setBackground(Color.darkGray);
		stack_frame.addWindowListener(new CloseWindow(stack_frame));
		
		
		stack_label.setBounds(0, 0, 200, 300);
		stack_label.setFont(new Font("TimesRoman",Font.PLAIN,30));
		stack_label.setForeground(Color.magenta);
		stack_frame.add(stack_label);
		stack_frame.setVisible(true);
		
		stack=st;}
	
	private void print_stack() {
		String text="<html>";
		for(int i=0; i<stack.size(); i++) text+=String.valueOf(stack.elementAt(stack.size()-i-1))+"<br>";
		stack_label.setText(text+"</html>");
		stack_frame.setVisible(true);
	}
	
	public void opration_operator(String button, boolean display) {
		if("1234567890".indexOf(button.charAt(0))>=0) {
			if(point==0) {
				number= 10*number + Double.valueOf(button);
				if(display) Calculate.calc.display.setText(String.valueOf((int)number));
			}
			else {
				double num=Double.valueOf(button);
				for(int i=0; i<point; i++)num/=10;
				number+= num;
				point+=1;
				if(display) Calculate.calc.display.setText(String.valueOf(number));
			}
		}
		else if(button.equals(".")) {
			point+=1;
			if(display) Calculate.calc.display.setText(String.valueOf(number));
		}
		else if(button.equals("Delete")) {
			number/=10;
			number= Double.valueOf((int)number);
			if(display) Calculate.calc.display.setText(String.valueOf(number));
		}
		else if(button.equals("CE")) {
			number=0;
			Calculate.calc.display.setText("");
		}
		else if(button.equals("Enter")) {
			try{stack.push(Double.valueOf(Calculate.calc.display.getText()));}
			catch(Exception e) {}
			this.print_stack();
			point=0;
			number=0;
			Calculate.calc.display.setText("");
		}
		else if(button.equals("Clr")) {
			number=0;
			stack.clear();
			this.print_stack();
			Calculate.calc.display.setText("0");
		}
		else if(button.equals("=")) {
			this.print_stack(); //It's not null, since is defined in main using new Stack;
			if(! stack.empty()) Calculate.calc.display.setText(String.valueOf(stack.lastElement()));
		}
		else new Operations(stack,button,display);
		
		Calculate.calc.display.requestFocus();
	}
}

class Operations {
	double last,pre_last;
	
	Operations(Stack<Double> stack, String button, boolean display) {
		if(stack.size()>1 && button.length()==1) {
			last=stack.pop();
			pre_last=stack.pop();
			if(display) Calculate.calc.display.setText(String.valueOf(pre_last)+button+String.valueOf(last));
			
			switch(button.charAt(0)) {
			case '+':stack.push(sum());break;
			case '-':stack.push(difference());break;
			case '*':stack.push(product());break;
			case '/':if(last==0)Calculate.calc.display.setText("Division by 0");
					else stack.push(pre_last/last); break;
			default:stack.push(pre_last);stack.push(last);
			}}
	}
	double sum() 		{return pre_last+last;}
	double difference() {return pre_last-last;}
	double product() 	{return pre_last*last;}
	double quotient() 	{return pre_last/last;}
}

/*-----------------------------------------------------Interface---------------------------------------------------*/

class My_Calculator extends Frame{
	private static final long serialVersionUID = 0;
	private final int y0=20, buttons_d=50;
	public TextField display = new TextField("0");
	
	My_Calculator(Operate op){
		super("My Calculator");
		this.setLayout(null);
		this.setFont(new Font("TimesRoman",Font.PLAIN,20));
		this.setBackground(Color.gray);
		this.setLocation(300, 200);
		this.setSize(6*buttons_d, 6*buttons_d);
		this.setResizable(false);
		this.addWindowListener(new EndProgram());
		
		display.setEditable(true);
		display.setBounds(7, 30, 4*buttons_d, 40);
		display.addKeyListener(new Key_Pressed(op));
		this.add(display);
		
		Button enter= new Button("Enter");
		enter.setFont(new Font("TimesRoman", Font.BOLD, 14));
		enter.setForeground(Color.black);
		enter.setBackground(Color.red);
		enter.addActionListener(new Button_Pushed("Enter",op));
		enter.setBounds(4*buttons_d,30, 2*buttons_d,40);
		this.add(enter);
		
		for(int i=-2; i<9; i++) {
			Button button = new Button(String.valueOf(i+1));
			button.setBounds(buttons_d*(i%3),y0+buttons_d*(3-i/3), buttons_d,buttons_d);
			if(i==-1) button.setBounds(0,y0+4*buttons_d, 2*buttons_d,buttons_d);
			button.addActionListener(new Button_Pushed(String.valueOf(i+1),op));
			button.setFont(new Font("TimesRoman", Font.PLAIN, 14));
			if(i==-2) {
				button = new Button(".");
				button.setBounds(2*buttons_d,y0+4*buttons_d, buttons_d,buttons_d);
				button.addActionListener(new Button_Pushed(".",op));
				button.setFont(new Font("TimesRoman", Font.BOLD, 14));
			}
			button.setForeground(Color.white);
			button.setBackground(Color.black);
			this.add(button);
		}
		String name="";
		for(int i=1; i<5; i++) {
			switch(i){
			case 1:name="/";break;
			case 2:name="*";break;
			case 3:name="-";break;
			case 4:name="+";break;
			}
			Button button = new Button(name);
			button.setBounds(3*buttons_d,y0+buttons_d*i, buttons_d,buttons_d);
			button.setFont(new Font("TimesRoman", Font.PLAIN, 18));
			button.setForeground(Color.black);
			button.setBackground(Color.orange);
			button.addActionListener(new Button_Pushed(name,op));
			this.add(button);
		}
		for(int i=1; i<5; i++) {
			switch(i){
			case 1:name="Clr";break;
			case 2:name="CE";break;
			case 3:name="Delete";break;
			case 4:name="=";break;
			}
			Button button = new Button(name);
			button.setBounds(4*buttons_d,y0+buttons_d*i, buttons_d+20,buttons_d);
			button.setFont(new Font("TimesRoman", Font.PLAIN, 15));
			button.setForeground(Color.blue);
			button.setBackground(Color.lightGray);
			button.addActionListener(new Button_Pushed(name,op));
			this.add(button);
		}
		this.setVisible(true);
	}
}

/*-------------------------------------------------------Listeners-------------------------------------------------*/

class Button_Pushed implements ActionListener{
	private String button;
	static Operate oper_;
	
	Button_Pushed(String b, Operate op){
		button=b;
		oper_=op;}
	
	public void actionPerformed(ActionEvent E) {
		oper_.opration_operator(button,true);}
}

class Key_Pressed implements KeyListener{
	static Operate oper_;
	
	Key_Pressed(Operate op){ oper_=op;}
	
	public void keyPressed(KeyEvent key) {
		if(KeyEvent.getKeyText(key.getKeyCode()).equals("Escape")) System.exit(0);
		else if(key.getKeyChar()=='\n') oper_.opration_operator("Enter",false);
		else if(key.getKeyCode()==17) oper_.opration_operator("Clr",false);
		else oper_.opration_operator(String.valueOf(key.getKeyChar()),false);
		}
	
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
}

class EndProgram extends WindowAdapter{
	public void windowClosing(WindowEvent closeWindowAndExit){
		System.exit(0);}
}
class CloseWindow extends WindowAdapter{
	private Frame frame;
	CloseWindow(Frame f){
		this.frame=f;}
	
	public void windowClosing(WindowEvent closeWindowAndExit){
		this.frame.setVisible(false);}
}