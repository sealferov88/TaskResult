/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asw.netcracker;

import com.asw.netcracker.rover.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class Turner implements Turnable {

    private Rover rover;
    private Component roverImage;

    public void setRover(Rover rover) {
        this.rover = rover;
    }

    public void setRoverImage(Component roverImage) {
        this.roverImage = roverImage;
    }

    @Override
    public void turnTo(Direction direction, int length) {

        int deltaX = 0, deltaY = 0;
        switch (direction) {
            case NORTH:
                deltaY = -1;
                break;
            case SOUTH:
                deltaY = 1;
                break;
            case WEST:
                deltaX = -1;
                break;
            case EAST:
                deltaX = 1;
                break;
        }

        for (int i = 0; i < length; i++) {
            int x = rover.getX();
            int y = rover.getY();

            if (!rover.setXY(x + deltaX, y + deltaY)) {
                roverImage.setForeground(Color.red);
            } else {
                //удалось переместиться
                roverImage.setForeground(Color.LIGHT_GRAY);
                roverImage.setBounds(x * roverImage.getWidth() + (deltaX * roverImage.getWidth()), y * roverImage.getHeight() + (deltaY * roverImage.getHeight()), roverImage.getWidth(), roverImage.getHeight());
            }

            try {
                Thread.currentThread().sleep(Main.delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(Turner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
