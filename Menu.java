// MENU

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.sound.sampled.*;

class Menu extends JFrame implements ActionListener{
	private Pixel_Pokemon game;
	JButton playBtn = new JButton("PLAY");
	Clip menuBgm;

	public Menu(Pixel_Pokemon g){
		super("Pixel Pokemon");
		setSize(1000, 800);
		game = g;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		playBtn.addActionListener(this);
		
		ImageIcon back = new ImageIcon("bg/menu.png");
		JLabel backLabel = new JLabel(back);

		JLayeredPane mPage = new JLayeredPane();
		mPage.setLayout(null);

		backLabel.setSize(800, 800);
		backLabel.setLocation(0, 0);
		mPage.add(backLabel, 1);

		// PLAY
		playBtn.setSize(200, 50);
		playBtn.setLocation(390, 440);
		playBtn.setOpaque(false);
		mPage.add(playBtn, 2);

		add(mPage);
		setVisible(true);

	}
	
	/*public void playSound(){
		try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("menu bgm.au"));
        menuBgm = AudioSystem.getClip();
        menuBgm.open(audioInputStream);

        menuBgm.loop(Clip.LOOP_CONTINUOUSLY);
		}
    	catch (Exception e) {
        	e.printStackTrace();
    	}
	}*/

    public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();

		if (source == playBtn){
			new Pixel_Pokemon(game, this);
			setVisible(false);
  	 	}
    }
}

/*class GameOver extends JFrame implements ActionListener{
	private Pixel_Pokemon game;
	private GamePanel panel;
	
	JButton reBtn = new JButton("REMATCH");
	JButton newBtn = new JButton("NEW GAME");
	
	Clip gameOverBgm;

	public GameOver(Pixel_Pokemon g, GamePanel p){
		super("Pixel_Pokemon");
		setSize(1500, 800);
		
		game = g;
		panel = p;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		reBtn.addActionListener(this);
		newBtn.addActionListener(this);

		ImageIcon back = new ImageIcon("bg/gameoverscreen.png");
		
		JLabel backLabel = new JLabel(back);

		JLayeredPane gPage = new JLayeredPane();
		gPage.setLayout(null);

		backLabel.setSize(1500, 800);
		backLabel.setLocation(0, 0);
		gPage.add(backLabel, 1);

		// REMATCH
		reBtn.setSize(260, 80);
		reBtn.setLocation(450, 420);
		reBtn.setOpaque(false);
		gPage.add(reBtn, 2);

		// NEW GAME
		newBtn.setSize(250, 80);
		newBtn.setLocation(800, 420);
		newBtn.setOpaque(false);
		gPage.add(newBtn, 2);
		
		add(gPage);
		setVisible(true);
		
		// MUSIC
		panel.simpleBgm.stop();
		panel.waterBgm.stop();
		panel.spaceBgm.stop();
		panel.iceBgm.stop();
	
		playSound();

	}
	
	public void playSound(){
    	try {
	        AudioInputStream gameOverBgmFile = AudioSystem.getAudioInputStream(this.getClass().getResource("game over bgm.au"));
	        gameOverBgm = AudioSystem.getClip();
	        gameOverBgm.open(gameOverBgmFile);
	
	        gameOverBgm.loop(Clip.LOOP_CONTINUOUSLY);
		}
    	catch (Exception e) {
        	e.printStackTrace();
    	}
    }

    public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		
		//NEW GAME
		if (source == newBtn){
			gameOverBgm.stop();
			
			setVisible(false);
			new Pixel_Pokemon();
  	 	}
  	 	
  	 	//REMATCH
  	 	if (source == reBtn){
  	 		gameOverBgm.stop();
  	 		
			setVisible(false);
			
			if (panel.bgmNum == 1){
				panel.simpleBgm.loop(Clip.LOOP_CONTINUOUSLY);	
			}
			if (panel.bgmNum == 2){
				panel.waterBgm.loop(Clip.LOOP_CONTINUOUSLY);
			}
			if (panel.bgmNum == 3){
				panel.spaceBgm.loop(Clip.LOOP_CONTINUOUSLY);
			}
			if (panel.bgmNum == 4){
				panel.iceBgm.loop(Clip.LOOP_CONTINUOUSLY);
			}
			
			game.resetGame();
  	 	}
    }
}*/
