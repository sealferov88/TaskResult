/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asw.netcracker.rover;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class LoggingCommand implements RoverCommand{
    private final RoverCommand command;
    
    public LoggingCommand(RoverCommand command){
        this.command = command;
    }

    @Override
    public void execute() {
        
        String out = "Log:  ";
        /*
        Добавить код получения значения всех полей для объекта command. Строка описания формируется в виде:
        Log: {classname} [*{fieldName = {fieldValue}}]
        
        */
        out+= command.toString();

        System.out.println(out);
        command.execute();
    }

    public String toString()
    {
        return "Log:  " + command.toString();
    }



}
