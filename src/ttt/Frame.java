package ttt;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean running = true;
	int winstate = 0;
	
	int sizex;
	int sizey;
	
	JPanel game;
	
	JButton[][] grid;
	
	JButton topleft;
	JButton topmid;
	JButton topright;
	JButton midleft;
	JButton midmid;
	JButton midright;
	JButton botleft;
	JButton botmid;
	JButton botright;
	
	int bestx = -1;
	int besty = -1;
	
	public Frame(int sx, int sy) {
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		sizex = sx;
		sizey = sy;
		
		game = new JPanel();
		game.setSize(sizex, sizey);
		game.setPreferredSize(new Dimension(sizex, sizey));
		game.setLayout(new GridLayout(0,3));
		
		init();
		
		this.pack();
		this.setVisible(true);
		
		
	}
	
	public void init() {
		this.setTitle("Playing...");
		
		game.removeAll();
		this.getContentPane().removeAll();
		
		grid = new JButton[3][3];
		
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid[0].length; y++) {
				grid[x][y] = new JButton("");
				grid[x][y].setSize(game.getWidth() / 3, game.getHeight() / 3);
				grid[x][y].addActionListener(this);
				game.add(grid[x][y]);
			}
		}
		running = true;
		this.getContentPane().removeAll();
		this.getContentPane().add(game);
		this.repaint();
	}
	
	public void calc(JButton but) {
		but.setText("X");
		but.setEnabled(false);
		
		if((winstate = checkWin()) != 0) {
			running = false;
			EndGame();
		}
		
		if(running) {
			CheckBest();
			
			grid[bestx][besty].setText("O");
			grid[bestx][besty].setEnabled(false);
			
			bestx = -1;
			besty = -1;
			
			if((winstate = checkWin()) != 0) {
				running = false;	
				EndGame();
		}
		
		
		
		
		
		}
		
	}
	
	public int checkWin() {
		int X=0;
		int O=0;
		int won = 0;
		
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid[x].length; y++) {
				if(grid[x][y].getText() == "X")X++;
				if(grid[x][y].getText() == "O")O++;
				if(X==3) {
					won = -1;
				}
				if(O==3) {
					won = 1;
				}
			}
			X=0;
			O=0;
		}
		
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid[x].length; y++) {
				if(grid[y][x].getText() == "X")X++;
				if(grid[y][x].getText() == "O")O++;
				if(X==3) {
					won = -1;
				}
				if(O==3) {
					won = 1;
				}
			}
			X=0;
			O=0;
		}
		
		if((grid[0][0].getText() == "X")&&(grid[1][1].getText() == "X")&&(grid[2][2].getText() == "X")) {
			won = -1;
		}
		if((grid[0][2].getText() == "X")&&(grid[1][1].getText() == "X")&&(grid[2][0].getText() == "X")) {
			won = -1;
		}
		if((grid[0][0].getText() == "O")&&(grid[1][1].getText() == "O")&&(grid[2][2].getText() == "O")) {
			won = 1;
		}
		if((grid[0][2].getText() == "O")&&(grid[1][1].getText() == "O")&&(grid[2][0].getText() == "O")) {
			won = 1;
		}
				
		return won;
	}
	
	public void CheckBest() {
		int Xw=0;
		int Ow=0;
		int Xs=0;
		int Os=0;
		
		if(grid[1][1].getText() == "") {
			bestx = 1;
			besty = 1;
		}
		
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid[x].length; y++) {
				
//				Waagerecht
				if(grid[x][y].getText() == "X") {
					Xw++;
				}
				if(grid[x][y].getText() == "O")Ow++;
				if(Xw==2) {
					for(int ya = 0; ya < grid[x].length; ya++) {
						if(grid[x][ya].getText()=="") {
							bestx = x;
							besty = ya;
						}
					}
				}
				if(Ow==2) {
					for(int ya = 0; ya < grid[x].length; ya++) {
						if(grid[x][ya].getText()=="") {
							bestx = x;
							besty = ya;
						}
					}
					
				}
				
//				Senkrecht
				if(grid[y][x].getText() == "X") {
					Xs++;
				}
				if(grid[y][x].getText() == "O")Os++;
				if(Xs==2) {
					for(int ya = 0; ya < grid[x].length; ya++) {
						if(grid[x][ya].getText()=="") {
							bestx = x;
							besty = ya;
						}
					}
				}
				if(Os==2) {
					for(int ya = 0; ya < grid[x].length; ya++) {
						if(grid[x][ya].getText()=="") {
							bestx = x;
							besty = ya;
						}
					}

				}
				Xw=0;
				Ow=0;
				Xs=0;
				Os=0;
			}
		}
		

//		\
		if((grid[0][0].getText() == "X")&&(grid[1][1].getText() == "X")&&(grid[2][2].getText() == "")) {
			bestx = 2;
			besty = 2;
		}
		if((grid[0][0].getText() == "X")&&(grid[1][1].getText() == "")&&(grid[2][2].getText() == "X")) {
			bestx = 1;
			besty = 1;
		}
		if((grid[0][0].getText() == "")&&(grid[1][1].getText() == "X")&&(grid[2][2].getText() == "X")) {
			bestx = 0;
			besty = 0;
		}
//		/
		if((grid[0][2].getText() == "X")&&(grid[1][1].getText() == "X")&&(grid[2][0].getText() == "")) {
			bestx = 2;
			besty = 0;
		}
		if((grid[0][2].getText() == "X")&&(grid[1][1].getText() == "")&&(grid[2][0].getText() == "X")) {
			bestx = 1;
			besty = 1;
		}
		if((grid[0][2].getText() == "")&&(grid[1][1].getText() == "X")&&(grid[2][0].getText() == "X")) {
			bestx = 0;
			besty = 2;
		}
		
		if(bestx == -1 && besty == -1) {
			boolean occupied = true;
			while(occupied) {
				bestx = (int) (Math.random() * 3);
				besty = (int) (Math.random() * 3);
				if((bestx < 4 && bestx > -1) && (besty < 4 && besty > -1)) {	
					if(grid[bestx][besty].getText() == "")occupied = false;
				}
			}
		}
		if(bestx == -1 && besty != -1) {
			boolean occupied = true;
			while(occupied) {
				bestx = (int) (Math.random() *3);
				
				if(bestx < 4 && bestx > -1) {
					if(grid[bestx][besty].getText() == "")occupied = false;
				}
			}
		}
		if(bestx != -1 && besty == -1) {
			boolean occupied = true;
			while(occupied) {
				besty = (int) (Math.random() *3);
					
				if(besty < 4 && besty > -1) {
					if(grid[bestx][besty].getText() == "")occupied = false;
				}
			}
		}
		
		System.out.println("bestx " + bestx + " besty " + besty);

	}
	
	public int getSquare(JButton but) {
		if(but.getText() == "X") return -1;
		if(but.getText() == "O") return 1;
		return 0;
	}
	
	public void EndGame() {
		if(winstate==1)this.setTitle("You lost.");
		if(winstate==-1)this.setTitle("You won.");
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid[x].length; y++) {
				grid[x][y].setEnabled(false);
//				grid[x][y].setText("");
			}
		}
//		grid[1][1].setText(grid[1][1].getText() + " Neu");
		grid[1][1].setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(running) {
			System.out.println(ae.getSource());
			calc((JButton) ae.getSource());
		}else {
			init();
		}
		
	}

}
