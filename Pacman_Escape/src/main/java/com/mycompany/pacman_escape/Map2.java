/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pacman_escape;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Yoga Pramana ST
 */
public class Map2 implements Map{
    public short[] loadMapMaze() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/mapmaze2.txt"))) {
            String line = reader.readLine();
            String[] values = line.split(" ");
            short[] mapMaze = new short[values.length];
            for (int i = 0; i < values.length; i++) {
                mapMaze[i] = Short.parseShort(values[i]);
            }
            System.out.println("MapMaze loaded successfully.");
            return mapMaze;
        } catch (IOException e) {
            System.out.println("Error occurred while loading MapMaze: " + e.getMessage());
            return null;
        }
    }
    private final short MapMaze[] = loadMapMaze();
    
    @Override
    public short[] getMapMaze() {
        return MapMaze;
    }

    @Override
    public int getLevelDataSize() {
        return MapMaze.length;
    }
    int finish1x=0;
    int finish1y=432;
    int finish2x=408;
    int finish2y=456;
    public int[] getfinishpos1() {
        int[] position = new int[4];
        // Contoh nilai koordinat x dan y
        position[0] = finish1x;
        position[1] = finish1y;
        position[2] = finish2x;
        position[3]=finish2y;
        return position;
    }    
}
