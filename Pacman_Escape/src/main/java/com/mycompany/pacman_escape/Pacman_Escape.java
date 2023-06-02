package com.mycompany.pacman_escape;

import static com.mycompany.pacman_escape.Model.playBackgroundMusic;
import javax.swing.JFrame;
public class Pacman_Escape extends JFrame {
    
        public Pacman_Escape () {
		add(new Model());
                playBackgroundMusic("src/music/PacmanMusic.wav");
	}
	
	
	public static void main(String[] args) {
            StartMenu menu = new StartMenu();
            menu.setVisible(true);
            menu.setSize(1280,720);
            menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
            menu.setLocationRelativeTo(null);
	}
}
