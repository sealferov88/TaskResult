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
public class GroundCell {
    private CellState state = CellState.FREE;
    //private int x;
    //private int y;

    public void setState(CellState state) {
        this.state = state;
    }

    public CellState getState() {
        return state;
    }
    
}
