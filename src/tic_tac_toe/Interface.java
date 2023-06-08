package tic_tac_toe;
import java.awt.*;

public class Interface extends Frame {
	private static final long serialVersionUID = 1;
	static Buttons_Stack stack= new Buttons_Stack(""), all_buttons= new Buttons_Stack("");
	
	Interface(){
		super("Tic Tac Toe");
		this.setLayout(null);
		this.setBounds(200, 10, 400+Tic_Tac.N*100, 200+Tic_Tac.N*100);
		this.setBackground(Color.lightGray);
		this.setFont(new Font("TimesRoman",Font.PLAIN,40));
		this.addWindowListener(new EndGame());
		
		for(int x=1; x<=Tic_Tac.N; x++) {for(int y=1; y<=Tic_Tac.N; y++) {
			Cell_Button button= new Cell_Button(x,y);
			this.add(button);
			all_buttons.push(button);
		}}
		
		Button reset= new Button();
		reset.setFont(new Font("TimesRoman",Font.PLAIN,20));
		reset.setLabel("Restart Game");
		reset.setBounds(Tic_Tac.N*100+150,100, 200,50);
		reset.setBackground(Color.green);
		reset.addActionListener(new Other_Click());
		this.add(reset);
		
		Button undo= new Button();
		undo.setFont(new Font("TimesRoman",Font.PLAIN,20));
		undo.setLabel("<-Undo");
		undo.setBounds(Tic_Tac.N*100+150,150, 200,50);
		undo.setBackground(Color.green);
		undo.addActionListener(new Other_Click());
		this.add(undo);
		
		Button play_second= new Button();
		play_second.setFont(new Font("TimesRoman",Font.PLAIN,20));
		play_second.setLabel("Play Second/First");
		play_second.setBounds(Tic_Tac.N*100+150,200, 200,50);
		play_second.setBackground(Color.green);
		play_second.addActionListener(new Other_Click());
		this.add(play_second);
		
		Button players= new Button();
		players.setFont(new Font("TimesRoman",Font.PLAIN,20));
		players.setLabel("Alone / vsPC");
		players.setBounds(Tic_Tac.N*100+150,250, 200,50);
		players.setBackground(Color.green);
		players.addActionListener(new Other_Click());
		this.add(players);/*
		Choice players= new Choice();
		players.setFont(new Font("TimesRoman",Font.PLAIN,20));
		players.add("2 players");
		players.add("Play with robot");
		players.setBounds(Tic_Tac.N*100+150,250, 200,50);
		players.setBackground(Color.green);
		players.addItemListener(new Choice_Click());
		this.add(players);*/
		
		Choice hardness= new Choice();
		hardness.setFont(new Font("TimesRoman",Font.PLAIN,21));
		hardness.add("Select Hardness");
		hardness.add("Easy");
		hardness.add("Medium");
		hardness.add("Hard");
		hardness.setBounds(Tic_Tac.N*100+150,300, 250,50);
		hardness.setBackground(Color.green);
		hardness.addItemListener(new Choice_Click());
		this.add(hardness);
		
		TextField dimensions= new TextField();
		dimensions.setFont(new Font("TimesRoman",Font.PLAIN,21));
		dimensions.setText("Dimensions:");
		dimensions.setBounds(Tic_Tac.N*100+150,340, 200,30);
		dimensions.setBackground(Color.green);
		dimensions.addKeyListener(new DisplayReader());
		this.add(dimensions);
		
		
		this.setVisible(true);
	}
}
