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
public class GroundVisor {
    private Ground ground;

    public void setGround(Ground ground) {
        this.ground = ground;
    }
    
    public boolean hasObstacles(int x, int y){
        return ground.get(x, y).getState() == CellState.OCCUPIED;
    }
    
    
}
