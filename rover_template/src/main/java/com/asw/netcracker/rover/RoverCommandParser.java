/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asw.netcracker.rover;


import com.asw.netcracker.Main;
import sun.rmi.runtime.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Alex
 */
public class RoverCommandParser {
    public static RoverCommand loadCommand(Rover rover,JsonObject command){
        /*
            добавить код извлечения параметров команд ровера и их создания
        
        */
        if(command.containsKey("move"))
        {
            JsonArray f = command.getJsonArray("move");
            return new MoveCommand(rover.getMoveable(),
                    f.getInt(0),
                    f.getInt(1));
        }
        else {
            if (command.containsKey("turn")) {
                JsonArray f = command.getJsonArray("turn");
                Direction dir = getDirection(f.getString(0));
                return new TurnCommand(rover.getTurnable(),
                        dir,
                        f.getInt(1));
            }
            else
            {
                if(command.containsKey("log"))
                {
                    JsonObject bufCommand = command.getJsonObject("log");
                    if(bufCommand.containsKey("move"))
                    {
                        JsonArray f = bufCommand.getJsonArray("move");
                        return new LoggingCommand(new MoveCommand(rover.getMoveable(),
                                f.getInt(0),
                                f.getInt(1)));
                    }
                    else {
                        if (bufCommand.containsKey("turn")) {
                            JsonArray f = bufCommand.getJsonArray("turn");
                            Direction dir = getDirection(f.getString(0));
                            return new LoggingCommand (new TurnCommand(rover.getTurnable(),
                                    dir,
                                    f.getInt(1)));
                        }
                        else
                        {
                            if (bufCommand.containsKey("include")) {
                                String f = bufCommand.getString("include");
                                return new LoggingCommand (new ImportCommand(loadCommands(rover,Main.class.getResourceAsStream("/" + f))));
                            }
                        }

                    }
                }
            }
        }


        return null;
    }

    public static Direction getDirection(String str)
    {
        if(str.equals("WEST"))
            return Direction.WEST;
        else
            if(str.equals("EAST"))
                return Direction.EAST;
            else
                if (str.equals("NORTH"))
                    return Direction.NORTH;
                else
                    return Direction.SOUTH;
    }

    public static List<RoverCommand> loadCommands(Rover rover, InputStream is){
        List<RoverCommand> rc = new ArrayList<>();
        JsonReader rdr = Json.createReader(is);
        JsonArray commands = rdr.readArray();
        
        for (JsonObject command : commands.getValuesAs(JsonObject.class)) {
            RoverCommand currentCommand = loadCommand(rover, command);
            rc.add(currentCommand);
        }
        
        return rc;
    }
    
}
