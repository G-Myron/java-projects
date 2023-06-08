package piano;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PieceChooser extends Choice{
	private static final long serialVersionUID = 1L;
	
	PieceChooser(){
		super();
		this.setFont(new Font("TimesRoman",Font.PLAIN,20));
		this.setBackground(Color.blue);
		this.setForeground(Color.orange);
		this.addItem("-pentagram-");
		this.addItem("Fur Elise");
		this.addItem("La Campanella");
		this.addItem("Mozart 40th");
		this.addItem("Alla Turca");
		this.addItem("Random");
		this.addItemListener(new Selector());
		this.select(0);
	}
	
	PieceChooser set(int x, int y) {
		this.setBounds(x, y, 220,0);
		return this;
	}
	
	void reset() {
		this.select(0);
		Auto_Player.stop_playing();
		Auto_Player.setPiece(null);
	}
	
	private class Selector implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if(e.getItem()=="-pentagram-") Auto_Player.setPiece(null);
			else if(e.getItem()=="Fur Elise") Auto_Player.setPiece(Pieces.Fur_Elise.split(" "));
			else if(e.getItem()=="La Campanella") Auto_Player.setPiece(Pieces.La_Campanella.split(" "));
			else if(e.getItem()=="Mozart 40th") Auto_Player.setPiece(Pieces.Mozart_40.split(" "));
			else if(e.getItem()=="Alla Turca") Auto_Player.setPiece(Pieces.Alla_Turca.split(" "));
			else if(e.getItem()=="Random") Auto_Player.setPiece(Pieces.random_notes().split(" "));
	}}
}
