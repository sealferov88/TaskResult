/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asw.netcracker.rover;

/**
 *
 * @author Alex
 */
public class Ground {
    private GroundCell[][] landscape;
    private int width;
    private int height;
    
    public Ground(int x, int y){
        this.width = x;
        this.height = y;
        landscape = new GroundCell[x][y];
        for (int i=0; i<width;i++)
            for (int j=0; j<height;j++){
                landscape[i][j] = new GroundCell();
            }
    }
    public GroundCell get(int x, int y){
        return landscape[x][y];
    }
    
}
