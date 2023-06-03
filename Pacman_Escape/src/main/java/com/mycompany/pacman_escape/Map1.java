/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pacman_escape;

/**
 *
 * @author W I N D O W S
 */
public class Map1 implements Map{
    private final short MapMaze[] = {
    	23, 00, 0, 0, 23, 0, 23, 00, 23, 0, 23, 0, 0, 0, 0,
        25, 18, 26, 18, 20, 0, 21, 00, 21, 0, 17, 26, 26, 30, 0,
        0, 21, 0, 17, 24, 26, 28, 00, 21, 0, 21, 0, 0, 0, 0,
        0, 21, 0, 21, 0, 0, 0, 0, 17, 26, 24, 26, 22, 0, 0,
        0, 21, 0, 21, 0, 27, 26, 26, 20, 0, 0, 00, 21, 0, 0,
        0, 21, 0, 21, 0, 0, 0, 0, 21, 0, 19, 26, 28, 0, 0,
        0, 21, 0, 25, 26, 18, 26, 26, 28, 0, 21, 0, 0, 0, 0,
        0, 21, 0, 0, 0, 21, 0, 0, 0, 0, 21, 00, 27, 26, 22,
        27, 24, 26, 26, 26, 24, 22, 0, 19, 26, 20, 0, 0, 0, 21,
        0, 0, 0, 0, 0, 0, 21, 0, 29, 0, 21, 0, 27, 26, 20,
        19, 16, 16, 16, 16, 0, 21, 0, 0, 0, 21, 00, 00, 00, 21,
        22, 0, 0, 0, 16, 0, 17, 26, 30, 0, 17, 26, 26, 26, 28,
        22, 0, 16, 16, 16, 00, 21, 0, 0, 0, 21, 0, 0, 0, 0,
        22, 0, 0, 0, 0, 0, 21, 0, 27, 26, 24, 26, 18, 30, 0,
        25, 26, 26, 36, 27, 26, 28, 0, 0, 0, 0, 0, 29, 0, 0
    };
    
    @Override
    public short[] getMapMaze() {
        return MapMaze;
    }
    
}
