/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CVG.blocks;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Ayas
 */
public class Block {

    private int x, y;
    private boolean solid;
    BufferedImage image;
    private String moveToCodeId;
    private String codId;
    private boolean isDoor = false;
    private int Type;

    public String getCodeId() {
        return this.codId;
    }

    public String getMoveToCodeId() {
        return this.moveToCodeId;
    }
    
    public int getType(){
        return this.Type;
    }

    public Block(int xi, int yi, int type, String codeId, String moveToCodeId) throws IOException {
        String tilename = "";
        this.moveToCodeId = moveToCodeId;
        this.codId = codeId;
        this.x = xi;
        this.y = yi;
        this.Type = type;
        this.solid = false;
        switch (type) {
            case 0:
                tilename = "dirt";
                break;
            case 1:
                tilename = "stone";
                this.solid = true;
                break;

            case 2:
                tilename = "water";
                break;
            case 3:
                tilename = "tree";
                break;
            case 4:
                tilename = "houseLeft";
                break;

            case 5:
                tilename = "houseRight";
                break;

            case 6:
                tilename = "grass";
                break;
            case 7:
                tilename = "door";
                this.isDoor = true;
                break;
            case 8:
                tilename = "houseTopLeft";
                
                break;
                
                
            case 9:
                tilename = "houseTopRight";
               
                break;
                
                
           case 10:
                tilename = "houseTop";
                
                break;
        }
        image = ImageIO.read(new File("src/Images/tiles/" + tilename + ".png"));

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void move(int speed, boolean x, boolean y) {
        if (x) {
            this.x += speed;
        } else {
            this.y += speed;
        }
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public boolean isSolid() {
        return this.solid;
    }

    public boolean isDoor(){
        return this.isDoor;
    }
}
