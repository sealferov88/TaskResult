/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asw.netcracker.rover;

import java.io.InputStream;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Alex
 */
public class Rover implements ProgramFileAware {

    private final Moveable moveable;
    private final Turnable turnable;
    private final GroundVisor visor;

    private int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public boolean setXY(int x, int y) {
        try {
            if (!visor.hasObstacles(x, y)) {
                this.x = x;
                this.y = y;
                return true;
            } else {
                System.out.println("BOOM with Obstacles");
                return false;
                
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("BOOM with border");
            return false;
        }
    }

    

    private int Direction;

    public Moveable getMoveable() {
        return moveable;
    }

    public Turnable getTurnable() {
        return turnable;
    }

    public Rover(Moveable moveable, Turnable turnable, GroundVisor visor) {
        this.moveable = moveable;
        this.turnable = turnable;
        this.visor = visor;
    }

    @Override
    public void executeProgramFile(InputStream is) {
        
        List<RoverCommand> commands =  RoverCommandParser.loadCommands(this, is);
        for (RoverCommand command:commands)
            command.execute();

    }

}
