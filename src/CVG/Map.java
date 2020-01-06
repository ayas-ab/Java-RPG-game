/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CVG;

import CVG.blocks.Block;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Ayas
 */
public class Map {

    public Map() throws IOException {
        
        
       this.loadMap(new File("src/CVG/map_solid.txt").getAbsolutePath());
       this.loadMap(new File("src/CVG/map_items.txt").getAbsolutePath());


    }

    public void loadMap(String m) throws FileNotFoundException {

        File file = new File(m);
        Scanner inputFile = new Scanner(file);
        // goes through the file and adds each line to the line arraylist
        int lasty = 0, lastx = 0;
        while (inputFile.hasNextLine()) {
        	

            String line = inputFile.nextLine();
            if (!line.startsWith("!")) {
                String[] blocks = line.split(" ");
                //id,thisOneCode,MoveToCode
                for (int i = 0; i < blocks.length; i++) {
                	
               
                    try {
                        if (blocks[i].contains(",")){
                        
                            String[] door = blocks[i].split(",");
                          
                           
                            Game.getBlocks().add(new Block(lastx, lasty, Integer.parseInt(door[0]), door[1], door[2]));
                          
                        
                        }else{
                        Game.getBlocks().add(new Block(lastx, lasty, Integer.parseInt(blocks[i]), "", ""));
                        }
                        lastx += 100;
                    } catch (Exception e) {
                        lastx += 100;
                        
                       
                    }

                }
                lastx = 0;
                lasty += 100;

            }
        }

    }

}
