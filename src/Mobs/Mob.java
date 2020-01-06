/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mobs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.round;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Ayas
 */
public class Mob {

    private int lastx, lasty;
    public long endWalk = 0;
    private int speed = 6;
    public int start = 0;
    private String name = "";
    private int x, y, type;
    private BufferedImage current, front, back, left, right;
    public boolean isUp = false, isDown = false, isLeft = false, isRight = false, isStill = true;
    Random Rand = new Random();
    Random rand = new Random();

    public void Move() {
       
        if (start == 0) {
            if (System.currentTimeMillis() >= endWalk) {
                this.stopeUp();
                this.MoveDown();
                this.StopRight();
                this.stopLeft();
                endWalk = System.currentTimeMillis() + 2000;
            } else {
                start = Rand.nextInt(3);
            }

        } else if (start == 1) {
            if (System.currentTimeMillis() >= endWalk) {
                this.stopeDown();
                this.StopRight();
                this.stopLeft();
                this.MoveUp();
                endWalk = System.currentTimeMillis() + 2000;
            } else {
                start = Rand.nextInt(3);
            }
        } else if (start == 2) {
            if (System.currentTimeMillis() >= endWalk) {
                this.stopeDown();
                this.stopeUp();
                this.stopLeft();
                this.MoveRight();

                endWalk = System.currentTimeMillis() + 2000;
            } else {
                start = Rand.nextInt(3);
            }
        } else if (start == 3) {
            if (System.currentTimeMillis() >= endWalk) {
                this.stopeDown();
                this.stopeUp();
                this.MoveLeft();
                this.StopRight();
                endWalk = System.currentTimeMillis() + 2000;
            }

        } else {
            start = Rand.nextInt(3);
        }

    }

    public int getType(){
        return this.type;
    }
    public Mob(int x, int y, int type) throws IOException {
        this.x = x;
        this.y = y;
        this.lastx = x;
        this.lasty = y;
        this.type = type;
        if (type == 1){
            this.name = "grassMob";
        }else if (type == 2){
            this.name = "human";
        }
        current = ImageIO.read(new File("src/Images/" + this.name + "/front.png"));
        front = ImageIO.read(new File("src/Images/" + this.name + "/front.png"));
        back = ImageIO.read(new File("src/Images/" + this.name + "/back.png"));
        left = ImageIO.read(new File("src/Images/" + this.name + "/left.png"));
        right = ImageIO.read(new File("src/Images/" + this.name + "/right.png"));

    }

    public int getSpeed() {
        return this.speed;
    }

    public void update() {
        if (isLeft) {
            this.lastx = x;
            x -= speed;
        }

        if (isRight) {
             this.lastx = x;
            x += speed;
        }

        if (isDown) {
            this.lasty = y;
            y += speed;
        }

        if (isUp) {
            this.lasty = y;
            y -= speed;
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

    public void setX(int x) {
        //this.lastx = this.x;
        this.x = x;
    }

    public void setY(int y) {
        //lasty = this.y;
        this.y = y;
    }

    public void bgMove(int speed, boolean x, boolean y) {

        if (x) {
           // this.lastx = this.x;
            this.x += speed;
        } else {
           // this.lasty = this.y;
            this.y += speed;
        }
    }

    public void checkDistance(int x, int y) {

        if (Math.sqrt(Math.pow(x - this.getX(), 2) + Math.pow(y - this.getY(), 2)) <= 150) {
            // this.setAlone(false);
            this.start = 20;//any number more than 3
            this.StopRight();
            this.stopLeft();
            this.stopeDown();
            this.stopeUp();
            this.moveTo(x, y);
        }
    }

    public void moveTo(int x, int y) {
        double dy = y - this.getY();
        double dx = x - this.getX();
        double angle = Math.atan2(dy, dx);
        double vx = this.getSpeed() * Math.cos(angle);
        double vy = this.getSpeed() * Math.sin(angle);
        this.setX((int) (this.getX() + round(vx)));
        this.setY((int) (this.getY() + round(vy)));
        if (vx < 0 && vy >= 0) {
            this.current = this.left;

        } else if (vx > -1 && vy >= 0) {
            this.current = this.right;
        } else if (vx >= 0 && vy <= 0) {
            this.current = this.back;
        } else if (vx >= 0 && vy >= 0) {
            this.current = this.front;
        } else {
            this.current = this.front;
        }

    }
    
    public void resetPosition(){
        this.x = this.lastx;
        this.y = this.lasty;
    }

}
