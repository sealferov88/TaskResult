/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asw.netcracker;

import com.asw.netcracker.rover.*;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;


/**
 *
 * @author Alex
 */
public class Main {
    static final int roverSize = 40; //размер ровера
    static final int fieldSize = 20; //размер поля (поле квадратное)
    static Ground ground;       //земля
    static final int delay = 200;   //задержка в миллисекундах для рисования
    
    public static void main(String[] args) throws Exception{
        
        InputStream is = Main.class.getResourceAsStream("/rover2.json");  
        //Moveable mover = (x,y)->System.out.println("MOVER!!!"+x+" "+y);
        //Turnable turner = (d,l)->System.out.println("TURNER!!!"+d+" "+l);
        
        //Объекты для обработки перемещения
        Turner turner = new Turner();
        Mover mover = new Mover();
        
        //Земля и радар
        GroundVisor visor = new GroundVisor();
        ground = new Ground(fieldSize,fieldSize);
        visor.setGround(ground);
        
        
        //Препятствия
        ground.get(10,10).setState(CellState.OCCUPIED);
        ground.get(4,15).setState(CellState.OCCUPIED);
        ground.get(6,8).setState(CellState.OCCUPIED);
        
        //Ровер
        Rover rover = new Rover(mover, turner, visor);
        
        //панель для отображения земли
        PaintFrame frame = new PaintFrame();
        //Визульаный компонент для отображения ровера
        Button roverImage = new Button("R");
        frame.add(roverImage);
        roverImage.setBounds(0,0,roverSize,roverSize);
        
        //Настройка объектов для перемещения
        turner.setRover(rover);
        turner.setRoverImage(roverImage);
        
        
        mover.setRover(rover);
        mover.setRoverImage(roverImage);
        
        //Запуск программы ровера
        rover.executeProgramFile(is);
        
        
    }
    
    
    static class PaintFrame extends Panel {
        
                
        public PaintFrame(){
            super();
            Frame f = new Frame("Test rocer");
            f.setSize((fieldSize+1)*roverSize , (fieldSize+1)*roverSize );
            f.add(this);
            this.setLayout(null);
            f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
             }
            });
            f.show();
        }
        @Override
        public void paint(Graphics g){
            
            for (int i=0;i<fieldSize;i++ ){
                g.setColor(Color.BLACK);
                g.drawLine(0, i*roverSize, (roverSize*fieldSize), i*roverSize);
                g.drawLine(i*roverSize, 0, i*roverSize, (roverSize*fieldSize) );
                
                for (int j=0;j<fieldSize;j++){
                    if (ground.get(i, j).getState() == CellState.OCCUPIED){
                        //препятствие
                        g.setColor(Color.red);
                        g.fillRect(i*roverSize, j*roverSize, roverSize, roverSize);
                    }
                    
                }
            }
        }
        
    }
    
}
