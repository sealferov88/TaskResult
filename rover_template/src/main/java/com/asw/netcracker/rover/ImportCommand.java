/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asw.netcracker.rover;

import java.util.List;

/**
 *
 * @author Alex
 */
public class ImportCommand implements RoverCommand{
    private final List<RoverCommand> commands;
    
    public ImportCommand(List<RoverCommand> commands){
        this.commands =commands;
    }

    @Override
    public void execute() {
        for(RoverCommand command: commands){
            command.execute();
        }
    }

    public String toString()
    {

        return "Import  " + "Command number: " + commands.size();
    }
}
