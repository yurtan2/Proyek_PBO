package com.mycompany.pacman_escape;

import static com.mycompany.pacman_escape.Model.playBackgroundMusic;
import javax.swing.JFrame;
public class Pacman_Escape extends JFrame {
    
        public Pacman_Escape () {
		add(new Model());
                playBackgroundMusic("src/music/PacmanMusic.wav");
	}
	
	
	public static void main(String[] args) {
            /*
		Pacman_Escape pac = new Pacman_Escape();
		pac.setVisible(true);
		pac.setTitle("Pacman");
		pac.setSize(1280,720);
		pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pac.setLocationRelativeTo(null);
                
		*/
            Awal awal = new Awal();
            awal.setVisible(true);
            awal.setLocationRelativeTo(null);
            awal.setSize(1280,720);
            awal.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
