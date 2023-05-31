package com.mycompany.pacman_escape;

import javax.swing.JFrame;
public class Pacman_Escape extends JFrame {
    
        public Pacman_Escape () {
		add(new Model());
	}
	
	
	public static void main(String[] args) {
		Pacman_Escape pac = new Pacman_Escape();
		pac.setVisible(true);
		pac.setTitle("Pacman");
		pac.setSize(720,1280);
		pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pac.setLocationRelativeTo(null);
		
	}
}
