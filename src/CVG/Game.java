package CVG;

import static CVG.Game.Mobs;
import CVG.blocks.Block;
import Mobs.Character;
import Mobs.Mob;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends Canvas implements Runnable, KeyListener {

    private Font font = new Font(null, Font.BOLD, 30);
    private BufferStrategy strategy;
    private Thread GameThread;
    boolean running = true;

    private static ArrayList<Block> tempMap = new ArrayList<Block>();
    private BufferedImage water, stone, dirt;
    public static Character player;
    public static ArrayList<Mob> Mobs = new ArrayList<Mob>();
    private static ArrayList<Block> blocks = new ArrayList<Block>();
    public static Map map;

    public static ArrayList<Block> getBlocks() {
        return blocks;
    }

    public Game() throws IOException {
        map = new Map();
        //Creating Blocks

        player = new Character();
        Mobs.add(new Mob(200, 0, 1));
        Mobs.add(new Mob(200, 200, 1));
        Mobs.add(new Mob(200, 300, 2));
        try {
       
            water = ImageIO.read(new File("src/Images/tiles/water.png"));
            stone = ImageIO.read((new File("src/Images/tiles/stone.png")));
            dirt = ImageIO.read((new File("src/Images/tiles/dirt.png")));
            /*BG = ImageIO.read(this.getClass().getResource("images\\bg.jpg"));
             Tower = ImageIO.read(this.getClass().getResource(
             "images\\tower.png"));
             Tank = ImageIO
             .read(this.getClass().getResource("images\\tank.png"));
             */
        } catch (Exception e) {
            System.out.print(e);
        }

        // create a frame to contain our game
        JFrame container = new JFrame("Game");
        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension(1000, 520));
        panel.setLayout(null);
        container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setup our canvas size and put it into the content of the frame
        setBounds(0, 0, 1200, 600);
        panel.add(this);
        setIgnoreRepaint(true);

        container.pack();
        container.setResizable(false);
        container.setLocationRelativeTo(null);
        container.setVisible(true);
        createBufferStrategy(2);
        this.requestFocus();
        this.addKeyListener(this);
        this.setFocusable(true);
        strategy = getBufferStrategy();
        GameThread = new Thread(this);
        GameThread.start();

    }

    @Override
    public void run() {

        while (running) {
             draw();
            player.update();
            // Mobs.get(0).moveTo(player.getX(), player.getY());
            for (int i = 0; i < Mobs.size(); i++) {
                Mobs.get(i).Move();
                Mobs.get(i).update();
                Rectangle thisMob = new Rectangle(Mobs.get(i).getX(), Mobs.get(i).getY(), 100, 100);
                for (int block = 0; block < blocks.size(); block++) {

                    if (blocks.get(block).isSolid()) {
                        Rectangle left_block = new Rectangle(blocks.get(block).getX(), blocks.get(block).getY(), 1, 100);
                        Rectangle right_block = new Rectangle(blocks.get(block).getX() + 100, blocks.get(block).getY(), 1, 100);
                        Rectangle top_block = new Rectangle(blocks.get(block).getX(), blocks.get(block).getY(), 100, 1);
                        Rectangle down_block = new Rectangle(blocks.get(block).getX(), blocks.get(block).getY() + 100, 100, 1);
                        if (left_block.intersects(thisMob)) {
                            Mobs.get(i).setX(Mobs.get(i).getX() - Mobs.get(i).getSpeed());

                        }
                        if (right_block.intersects(thisMob)) {
                            Mobs.get(i).setX(Mobs.get(i).getX() + Mobs.get(i).getSpeed());
                        }
                        if (top_block.intersects(thisMob)) {
                            Mobs.get(i).setY(Mobs.get(i).getY() - Mobs.get(i).getSpeed());
                        }
                        if (down_block.intersects(thisMob)) {
                            Mobs.get(i).setY(Mobs.get(i).getY() + Mobs.get(i).getSpeed());
                        }
                    }
                }
                if (Mobs.get(i).getType() != 2) {
                    Mobs.get(i).checkDistance(player.getX(), player.getY());
                }
            }

 

            try {

                Thread.sleep(100);

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public void draw() {
        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
        if (running == true) {
            // Get hold of a graphics context for the accelerated
            // surface and blank it out
            g.setColor(Color.black);
            g.fillRect(0, 0, 1100, 600);
            //g.drawImage(front.getScaledInstance(50, 50, Image.SCALE_DEFAULT), 0, 0, null);
            //g.drawImage(front, 0+100, 0, -100, 100, null);
            g.setColor(Color.red);
            for (int i = 0; i < this.blocks.size(); i++) {
                g.drawImage(blocks.get(i).getImage(), blocks.get(i).getX(), blocks.get(i).getY(), 100, 100, null);

            }
            for (int i = 0; i < Mobs.size(); i++) {
                g.drawImage(Mobs.get(i).getSprite(), Mobs.get(i).getX(), Mobs.get(i).getY(), 100, 100, null);
                // g.fillRect(Mobs.get(i).getX(), Mobs.get(i).getY(), 100, 100);
            }
            g.drawImage(player.getSprite(), player.getX(), player.getY(), 100, 100, null);
            strategy.show();
            // g.fillRect(this.Player1.getX(), this.Player1.getY(), 70, 120);

        }
    }

    public static void main(String[] args) throws IOException {
       new Game();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:
                player.MoveUp();

                break;

            case KeyEvent.VK_DOWN:
                player.MoveDown();

                break;

            case KeyEvent.VK_SPACE:

                break;

            case KeyEvent.VK_LEFT:
                player.MoveLeft();

                break;

            case KeyEvent.VK_RIGHT:
                player.MoveRight();

                break;
           

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        switch (e.getKeyCode()) {

            case KeyEvent.VK_UP:
                player.stopeUp();

                break;

            case KeyEvent.VK_DOWN:
                player.stopeDown();

                break;

            case KeyEvent.VK_SPACE:

                break;

            case KeyEvent.VK_LEFT:
                player.stopLeft();

                break;

            case KeyEvent.VK_RIGHT:
                player.StopRight();

                break;

        }

    }

    public boolean close(Block b, Character p) {

        if (Math.sqrt(Math.pow(b.getX() - p.getX(), 2) + Math.pow(b.getY() - p.getY(), 2)) <= 400) {
            return true;
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
