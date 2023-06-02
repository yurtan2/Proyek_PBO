package com.mycompany.pacman_escape;

import static com.mycompany.pacman_escape.Music.playBackgroundMusic;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
public class Pacman_Escape extends JFrame {
    
        public Pacman_Escape (){
		add(new Model());
            try {
                playBackgroundMusic("src/music/PacmanMusic.wav");
            } catch (IOException ex) {
                Logger.getLogger(Pacman_Escape.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	
	
	public static void main(String[] args) {
            StartMenu menu = new StartMenu();
            menu.setVisible(true);
            menu.setSize(1280,720);
            menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
            menu.setLocationRelativeTo(null);
	}
}
