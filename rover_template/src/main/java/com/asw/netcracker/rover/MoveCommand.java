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
public class MoveCommand implements RoverCommand{
    private final Moveable moveable;
    private final int x;
    private final int y;

    @Override
    public void execute() {
        moveable.move(x, y);
    }
    
    public MoveCommand(Moveable moveable, int x, int y){
        this.moveable = moveable;
        this.x = x;
        this.y = y;
    }
    public String toString()
    {
        return "MoveCommand  " + "X: " + x + "  Y:  " + y;
    }

}
