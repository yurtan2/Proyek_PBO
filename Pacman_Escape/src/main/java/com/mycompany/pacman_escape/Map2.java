/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pacman_escape;

/**
 *
 * @author Yoga Pramana ST
 */
public class Map2 implements Map{
    private final short MapMaze[] = {
    	23, 00, 19, 26, 30, 00, 00, 23, 00, 00, 00, 00, 19, 30, 00, 00, 23, 00, 23, 00,
        25, 26, 20, 00, 00, 00, 19, 24, 22, 00, 19, 26, 28, 00, 00, 19, 24, 26, 24, 22,
        00, 00, 17, 26, 26, 18, 28, 00, 25, 26, 20, 00, 00, 00, 19, 20, 00, 00, 00, 21,
        00, 27, 20, 00, 00, 21, 00, 00, 00, 00, 17, 30, 00, 19, 24, 24, 22, 00, 19, 28,
        00, 00, 21, 00, 19, 28, 00, 23, 00, 19, 28, 00, 00, 21, 00, 00, 21, 00, 21, 00,
        24, 00, 25, 18, 28, 00, 00, 17, 26, 28, 00, 00, 19, 24, 22, 00, 17, 26, 28, 00,
        21, 00, 00, 21, 00, 00, 19, 20, 00, 00, 00, 19, 28, 00, 25, 26, 20, 00, 00, 00,
        25, 22, 00, 17, 26, 00, 00, 21, 00, 19, 26, 20, 00, 00, 00, 00, 21, 00, 25, 22,
        00, 25, 26, 20, 00, 00, 19, 24, 26, 28, 00, 25, 22, 00, 24, 00, 00, 00, 00, 21,
        00, 00, 00, 21, 00, 19, 28, 00, 00, 00, 00, 00, 17, 26, 24, 30, 00, 27, 26, 20,
        00, 23, 00, 17, 26, 28, 00, 00, 19, 30, 00, 19, 28, 00, 00, 00, 00, 00, 00, 21,
        00, 17, 26, 28, 00, 00, 00, 19, 28, 00, 00, 21, 00, 00, 23, 00, 19, 26, 18, 28,
        19, 28, 00, 00, 00, 27, 18, 28, 00, 00, 19, 24, 26, 26, 16, 26, 28, 00, 21, 00,
        21, 00, 00, 23, 00, 00, 21, 00, 00, 27, 20, 00, 00, 00, 21, 00, 00, 00, 17, 30,
        17, 30, 00, 25, 26, 18, 24, 30, 00, 00, 17, 30, 00, 27, 28, 00, 19, 26, 28, 00,
        21, 00, 00, 00, 00, 29, 00, 00, 00, 27, 20, 00, 00, 00, 00, 00, 21, 00, 00, 00,
        25, 26, 18, 30, 00, 00, 00, 23, 00, 00 ,25, 26, 22, 00, 27, 18, 24, 26, 30, 00,
        00, 00, 21, 00, 00, 19, 26, 24, 22, 00, 00, 00, 21, 00, 00, 21, 00, 00, 00, 00,
        24, 00, 21, 00, 00, 21, 00, 00, 21, 00, 27, 18, 28, 00, 19, 24, 26, 18, 30, 00,
        25, 26, 24, 26, 26, 28, 00, 27, 28, 00, 00, 29, 00, 00, 29, 00, 00, 29, 00, 00
    };
    
    @Override
    public short[] getMapMaze() {
        return MapMaze;
    }

    @Override
    public int getLevelDataSize() {
        return MapMaze.length;
    }

    @Override
    public int[] getfinishpos1() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public short[] loadMapMaze() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
