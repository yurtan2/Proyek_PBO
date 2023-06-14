/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pacman_escape;

/**
 *
 * @author Yoga Pramana ST
 */
public abstract class Maps<T> implements Map<T>{
    protected int finish1x;
    protected int finish1y;
    protected int finish2x;
    protected int finish2y;
    protected T mapMaze;

    public Maps(int finish1x, int finish1y, int finish2x, int finish2y) {
        this.finish1x = finish1x;
        this.finish1y = finish1y;
        this.finish2x = finish2x;
        this.finish2y = finish2y;
        this.mapMaze = loadMapMaze();
    }
    
    public abstract T loadMapMaze();
    
    @Override
    public T getMapMaze() {
        return mapMaze;
    }

    @Override
    public int getLevelDataSize() {
        // Implement according to the size of the specific map data type T
        return 0;
    }

    @Override
    public int[] getfinishpos1() {
        int[] positions = new int[4];
        positions[0] = finish1x;
        positions[1] = finish1y;
        positions[2] = finish2x;
        positions[3] = finish2y;
        return positions;
    }

}
