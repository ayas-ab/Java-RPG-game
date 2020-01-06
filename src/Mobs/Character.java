/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobs;

import CVG.Game;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Ayas
 */
public class Character {

    private int speed = 13;
    private int x, y;
    BufferedImage current, front, back, left, right;
    public boolean isUp = false, isDown = false, isLeft = false, isRight = false, isStill = true;

    public Character() throws IOException {
        x = 420;
        y = 200;

        current = ImageIO.read(new File("src/Images/Basic/front.png"));
        front = ImageIO.read(new File("src/Images/Basic/front.png"));
        back = ImageIO.read(new File("src/Images/Basic/back.png"));
        left = ImageIO.read(new File("src/Images/Basic/left.png"));
        right = ImageIO.read(new File("src/Images/Basic/right.png"));

    }

    public void update() {
         for (int iMob = 0; iMob < Game.Mobs.size(); iMob++) {
             
                if (isLeft) {                    
                    Game.Mobs.get(iMob).bgMove(speed, true, false);
                    

                }

                if (isRight) {
                    Game.Mobs.get(iMob).bgMove(-speed, true, false);

                }

                if (isDown) {
                    Game.Mobs.get(iMob).bgMove(-speed, false, true);

                }

                if (isUp) {
                    Game.Mobs.get(iMob).bgMove(speed, false, true);
                }
         }
        for (int iBlocks = 0; iBlocks < Game.getBlocks().size(); iBlocks++) {
           

                if (isLeft) {

                    Game.getBlocks().get(iBlocks).move(speed, true, false);                    

                }

                if (isRight) {
                    Game.getBlocks().get(iBlocks).move(-speed, true, false);

                }

                if (isDown) {
                    Game.getBlocks().get(iBlocks).move(-speed, false, true);

                }

                if (isUp) {
                    Game.getBlocks().get(iBlocks).move(+speed, false, true);
                }
            }
        
    }

    public void MoveLeft() {
        if (!current.equals(left)) {
            current = left;
        }
        isLeft = true;

    }

    public void MoveRight() {
        if (!current.equals(right)) {
            current = right;
        }
        isRight = true;

    }

    public void MoveUp() {
        if (!current.equals(back)) {
            current = back;
        }

        isUp = true;

    }

    public void MoveDown() {
        if (!current.equals(front)) {
            current = front;
        }
        isDown = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getSprite() {
        return current;
    }

    public void stopLeft() {
        isLeft = false;

    }

    public void stopeDown() {
        isDown = false;
    }

    public void StopRight() {
        isRight = false;
    }

    public void stopeUp() {
        isUp = false;
    }
    
    public void setSpeed(int s){
        this.speed = s;
    }
    
    public void setX(int y){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }

}
