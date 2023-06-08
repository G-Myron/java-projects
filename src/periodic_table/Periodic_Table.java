package periodic_table;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

import javax.swing.JLabel;

/*
By Command Line:
cd Users\Μύρωνας\eclipse-workspace\Programs\bin
java Periodic_Table
*/

public class Periodic_Table {
	static Table p_table = new Table();
	
	public static void main(String[] args) {
		p_table.setVisible(true);
	}
}

class Table extends Frame{
	private static final long serialVersionUID = 1L;
	static final int size=40,first_x=size,first_y=first_x;
	public static String symbol,full_name;
	Stack<Integer> n_stack= new Stack<>(),l_stack= new Stack<>();
	private String orbitals= "spdfg";
	boolean push=true;
	static TextField display;
	static JLabel el_prop= new JLabel();
	
	Table(){
		super("Periodic Table - Myron");
		this.setLayout(null);
		this.setFont(new Font("TimesRom",Font.PLAIN,15));
		this.setBackground(Color.cyan);
		//this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
		this.setMinimumSize(new Dimension(100,100));
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.addWindowListener(new CloseProgram());
		
		display = new TextField();
		display.setBounds(300, 50, 500, 30);
		display.setForeground(Color.white);
		display.setBackground(Color.darkGray);
		Button ok_button = new Button("��");
		ok_button.setBounds(800,50, size,30);
		ok_button.setForeground(Color.white);
		ok_button.setBackground(Color.darkGray);
		el_prop.setBackground(Color.black);
		el_prop.setForeground(Color.magenta);
		el_prop.setFont(new Font("TimesRoman",Font.PLAIN,30));
		el_prop.setBounds(Table.first_x, 400, Toolkit.getDefaultToolkit().getScreenSize().width-Table.first_x, 300);
		this.add(display);
		this.add(ok_button);
		this.add(el_prop);
		
		int n,l,atomic_n=0,rows=7;
		String orbitals="";
		this.n_l_lists(1, rows, 1, 0);
		for(int i=0; i<=this.l_stack.lastIndexOf(1); i++) {
			n=this.n_stack.elementAt(i);
			l=this.l_stack.elementAt(i);
			orbitals+= String.valueOf(n) + this.orbitals.charAt(l);
				for(int m=-l; m<=l; m++) {
					for(int s=0; s<2; s++) {
						atomic_n++;
						Button button = new Button(Table.elements_Name(atomic_n));
						if(l==0)button.setBounds(first_x+size*s,first_y+n*size, size,size);
						else button.setBounds(first_x+size*(2*(rows/2+1)*(rows/2+1)+2-2*(l+1)*(l+1)+2*(l+m)+s), first_y+(n+l-1)*size, size,size);
						
						button.setFont(new Font("TimesRoman", Font.PLAIN, 15));
						button.setForeground(Color.white);
						button.setBackground(Color.black);
						if(m==l && s>0) {
							orbitals+= String.valueOf((2*(m+l)+s+1) +" ");
							button.addActionListener(new Button_Pushed(atomic_n, orbitals));
						}
						else button.addActionListener(new Button_Pushed(atomic_n, orbitals+ String.valueOf((2*(m+l)+s+1))));
						this.add(button);
					}
				}
		}
	}
	
	public static String elements_Name(int atomic_number) {
		switch(atomic_number) {
		case 1:symbol="H";full_name="��������";break;
		case 2:symbol="He";full_name="����";break;
		
		case 3:symbol="Li";full_name="�����";break;
		case 4:symbol="Be";full_name="��������";break;
		case 5:symbol="B";full_name="�����";break;
		case 6:symbol="C";full_name="��������";break;
		case 7:symbol="N";full_name="�����";break;
		case 8:symbol="O";full_name="�������";break;
		case 9:symbol="F";full_name="������";break;
		case 10:symbol="Ne";full_name="����";break;
		
		case 11:symbol="Na";full_name="������";break;
		case 12:symbol="Mg";full_name="��������";break;
		case 13:symbol="Al";full_name="�������";break;
		case 14:symbol="Si";full_name="�������";break;
		case 15:symbol="P";full_name="��������";break;
		case 16:symbol="S";full_name="����";break;
		case 17:symbol="Cl";full_name="������";break;
		case 18:symbol="Ar";full_name="����";break;
		
		case 19:symbol="K";full_name="�����";break;
		case 20:symbol="Ca";full_name="��������";break;
		case 21:symbol="Sc";full_name="�������";break;
		case 22:symbol="Ti";full_name="�������";break;
		case 23:symbol="V";full_name="�������";break;
		case 24:symbol="Cr";full_name="������";break;
		case 25:symbol="Mn";full_name="��������";break;
		case 26:symbol="Fe";full_name="�������";break;
		case 27:symbol="Co";full_name="��������";break;
		case 28:symbol="Ni";full_name="�������";break;
		case 29:symbol="Cu";full_name="������";break;
		case 30:symbol="Zn";full_name="�����������";break;
		case 31:symbol="Ga";full_name="������";break;
		case 32:symbol="Ge";full_name="��������";break;
		case 33:symbol="As";full_name="��������";break;
		case 34:symbol="Se";full_name="�������";break;
		case 35:symbol="Br";full_name="������";break;
		case 36:symbol="Kr";full_name="������";break;
		
		case 37:symbol="Rb";full_name="��������";break;
		case 38:symbol="Sr";full_name="��������";break;
		case 39:symbol="Y";full_name="������";break;
		case 40:symbol="Zr";full_name="��������";break;
		case 41:symbol="Nb";full_name="������";break;
		case 42:symbol="Mo";full_name="�����������";break;
		case 43:symbol="Tc";full_name="��������";break;
		case 44:symbol="Ru";full_name="��������";break;
		case 45:symbol="Rh";full_name="�����";break;
		case 46:symbol="Pd";full_name="��������";break;
		case 47:symbol="Ag";full_name="�������";break;
		case 48:symbol="Cd";full_name="������";break;
		case 49:symbol="In";full_name="�����";break;
		case 50:symbol="Sn";full_name="����������";break;
		case 51:symbol="Sb";full_name="���������";break;
		case 52:symbol="Te";full_name="���������";break;
		case 53:symbol="I";full_name="�����";break;
		case 54:symbol="Xe";full_name="����";break;
		
		case 55:symbol="Cs";full_name="������";break;
		case 56:symbol="Ba";full_name="�����";break;
		case 57:symbol="La";full_name="��������";break;

		case 87:symbol="Fr";full_name="�������";break;
		case 88:symbol="Ra";full_name="�����";break;
		case 89:symbol="Ac";full_name="�������";break;
		case 90:symbol="Th";full_name="�����";break;
		case 91:symbol="Pa";full_name="�����������";break;
		case 92:symbol="U";full_name="�������";break;
		case 93:symbol="Np";full_name="����������";break;
		case 94:symbol="Pu";full_name="���������";break;
		case 95:symbol="Am";full_name="��������";break;
		case 98:symbol="Cf";full_name="����������";break;
		case 99:symbol="Es";full_name="���������";break;
		case 100:symbol="Fm";full_name="������";break;
		case 101:symbol="Md";full_name="����������";break;
		
		default:symbol="["+String.valueOf(atomic_number)+"]";full_name="_";break;}
		return symbol;
	}
	
	public void n_l_lists(int n, int n_max, int l_max, int l_min) {
		int l;
		if(n>n_max)return;
		
		for(l=l_min; l<l_max; l++) {
			if(n==n_max && l==1) push=false;
			if(push) {
				n_stack.push(n);
				l_stack.push(l);
			}
			if(l>0) n_l_lists(n+1,n_max,l,l-1);
		}
		if(l==n) n_l_lists(n+1,n_max,n+1,l-1);
		
		if((n==n_max && l==2) || (n_max==3 && n==3 && l==1)) {
			n_stack.push(n);
			l_stack.push(1);
		}
		
		return;
	}
}

class Button_Pushed implements ActionListener{
	private int atomic_num;
	private String orbitals;
	
	Button_Pushed(int atomic, String orbitals){
		this.atomic_num=atomic;
		this.orbitals=orbitals;
	}
	
	public void actionPerformed(ActionEvent e) {
		Table.elements_Name(this.atomic_num);
		String props= this.orbitals +": \n"+ Table.full_name +" ( "+ Table.symbol +" "+ String.valueOf(this.atomic_num) +" )";
		
		Table.display.setText(orbitals);
		Table.el_prop.setText("<html>"+props.replaceAll("\n", "<br>")+"</html>");
	}
}

class CloseProgram extends WindowAdapter{
	public void windowClosing(WindowEvent closeWindowAndExit){
		System.exit(0);}
}
