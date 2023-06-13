/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pacman_escape;

/**
 *
 * @author Yoga Pramana ST
 */
public abstract class Maps implements Map{
    protected int finish1x;
    protected int finish1y;
    protected int finish2x;
    protected int finish2y;

    public Maps(int finish1x, int finish1y, int finish2x, int finish2y) {
        this.finish1x = finish1x;
        this.finish1y = finish1y;
        this.finish2x = finish2x;
        this.finish2y = finish2y;
    }

}
