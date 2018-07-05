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
public class TurnCommand implements RoverCommand{
    private final Turnable turnable;
    private final Direction direction;
    private final int length;
    
    public TurnCommand(Turnable turnable,Direction direction, int length){
        this.direction = direction;
        this.turnable = turnable;
        this.length = length;
    }

    @Override
    public void execute() {
        turnable.turnTo(direction, length);
    }

    public String toString()
    {
        return "TurnCommand  " + "Direction: " + direction.toString() + "  Length:  " + length;
    }

    
}
