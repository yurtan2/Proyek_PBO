/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pacman_escape;

/**
 *
 * @author W I N D O W S
 */
public interface Map <T> {
    T getMapMaze();
    public int getLevelDataSize();
    public int[] getfinishpos1();
    T loadMapMaze(); 
}
