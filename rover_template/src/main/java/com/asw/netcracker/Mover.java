/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asw.netcracker;

import com.asw.netcracker.rover.*;
import java.awt.Color;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class Mover implements Moveable {

    private Rover rover;
    private Component roverImage;

    public void setRover(Rover rover) {
        this.rover = rover;
    }

    public void setRoverImage(Component roverImage) {
        this.roverImage = roverImage;
    }

    
    @Override
    public void move(int xend, int yend) {
        //алгоритм Брезенхема
        int xstart = rover.getX(), ystart = rover.getY();

        /**
         * xstart, ystart - начало; xend, yend - конец;
         */
        int x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;

        dx = xend - xstart;//проекция на ось икс
        dy = yend - ystart;//проекция на ось игрек

        incx = sign(dx);
        /*
	 * Определяем, в какую сторону нужно будет сдвигаться. Если dx < 0, т.е. отрезок идёт
	 * справа налево по иксу, то incx будет равен -1.
	 * Это будет использоваться в цикле постороения.
         */
        incy = sign(dy);
        /*
	 * Аналогично. Если рисуем отрезок снизу вверх -
	 * это будет отрицательный сдвиг для y (иначе - положительный).
         */

        if (dx < 0) {
            dx = -dx;//далее мы будем сравнивать: "if (dx < dy)"
        }
        if (dy < 0) {
            dy = -dy;//поэтому необходимо сделать dx = |dx|; dy = |dy|
        }

        if (dx > dy) //определяем наклон отрезка:
        {
            /*
	  * Если dx > dy, то значит отрезок "вытянут" вдоль оси икс, т.е. он скорее длинный, чем высокий.
	  * Значит в цикле нужно будет идти по икс (строчка el = dx;), значит "протягивать" прямую по иксу
	  * надо в соответствии с тем, слева направо и справа налево она идёт (pdx = incx;), при этом
	  * по y сдвиг такой отсутствует.
             */
            pdx = incx;
            pdy = 0;
            es = dy;
            el = dx;
        } else//случай, когда прямая скорее "высокая", чем длинная, т.е. вытянута по оси y
        {
            pdx = 0;
            pdy = incy;
            es = dx;
            el = dy;//тогда в цикле будем двигаться по y
        }

        x = xstart;
        y = ystart;
        err = el / 2;

        if (!rover.setXY(x, y)) {
            roverImage.setForeground(Color.red);
        } else {
            //удалось переместиться
            roverImage.setBounds(x * roverImage.getWidth(), y * roverImage.getHeight(), roverImage.getWidth(), roverImage.getHeight());
            roverImage.setForeground(Color.LIGHT_GRAY);
        }
        try {
            Thread.currentThread().sleep(Main.delay);
        } catch (InterruptedException ex) {
            Logger.getLogger(Turner.class.getName()).log(Level.SEVERE, null, ex);
        }

        //все последующие точки возможно надо сдвигать, поэтому первую ставим вне цикла
        for (int t = 0; t < el; t++)//идём по всем точкам, начиная со второй и до последней
        {
            err -= es;
            if (err < 0) {
                err += el;
                x += incx;//сдвинуть прямую (сместить вверх или вниз, если цикл проходит по иксам)
                y += incy;//или сместить влево-вправо, если цикл проходит по y
            } else {
                x += pdx;//продолжить тянуть прямую дальше, т.е. сдвинуть влево или вправо, если
                y += pdy;//цикл идёт по иксу; сдвинуть вверх или вниз, если по y
            }

            if (!rover.setXY(x, y)) {
                roverImage.setForeground(Color.red);
            } else {
                //удалось переместиться
                roverImage.setForeground(Color.LIGHT_GRAY);
                roverImage.setBounds(x * roverImage.getWidth(), y * roverImage.getHeight(), roverImage.getWidth(), roverImage.getHeight());
            }

            try {
                Thread.currentThread().sleep(Main.delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(Turner.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private int sign(int x) {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
        //возвращает 0, если аргумент (x) равен нулю; -1, если x < 0 и 1, если x > 0.
    }

}
