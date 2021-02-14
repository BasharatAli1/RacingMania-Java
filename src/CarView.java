import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Basharat Ali
 */
public class CarView  extends JPanel
{
    CarArena carArena;
    CarCtrl carCtrl;
    private int carHeight = 80, carWidth = 57;
    ArrayList<Rectangle> oppCars;       // Array of rectangle type
    ArrayList<Rectangle> treeArr;       // Array of rectangle type
    Rectangle car;
    Random decideCar;        // to appear cars on both sides of roads
    
    JLabel label;
    
    ArrayList <Rectangle> line;      // 
    ArrayList <Rectangle> boundry1;      // 
    ArrayList <Rectangle> boundry2;      // 
    boolean boundary1Flag = true; 
    boolean boundary2Flag = true;
    boolean scoreStart = false;
    boolean gameOver = false;
    boolean stageComplete = false;
    
    int score = 0;
    int removeNum = 0;
    int stageNum = 1;
    
    BufferedImage roadGrass;
    BufferedImage road;
    BufferedImage playerCar;
    BufferedImage oppCar1;
    BufferedImage oppCar2;
    BufferedImage oppCar3; 
    BufferedImage oppCar4; 
    BufferedImage tree; 
    
    
    public CarView()
    {
        label = new JLabel("");
    }
    
    public CarView(CarArena carArena)
    {        
        oppCars = new ArrayList<Rectangle>();       // Create an ArrayList object
        treeArr = new ArrayList<Rectangle>();       // Create an ArrayList object
        
        line = new ArrayList<Rectangle>();       // Create an ArrayList object
        boundry1 = new ArrayList<Rectangle>();       // Create an ArrayList object
        boundry2 = new ArrayList<Rectangle>();       // Create an ArrayList object
        
        carCtrl = new CarCtrl(this);

        try {
            road = ImageIO.read(new File("C:\\Users\\Basharat Ali\\Documents\\NetBeansProjects\\CarRacingGame\\images\\road.png"));
            roadGrass = ImageIO.read(new File("C:\\Users\\Basharat Ali\\Documents\\NetBeansProjects\\CarRacingGame\\images\\background.png"));        
            playerCar = ImageIO.read(new File("C:\\Users\\Basharat Ali\\Documents\\NetBeansProjects\\CarRacingGame\\images\\playerCar.png"));
            oppCar1 = ImageIO.read(new File("C:\\Users\\Basharat Ali\\Documents\\NetBeansProjects\\CarRacingGame\\images\\oppCar1.png"));
            oppCar2 = ImageIO.read(new File("C:\\Users\\Basharat Ali\\Documents\\NetBeansProjects\\CarRacingGame\\images\\oppCar2.png"));
            oppCar3 = ImageIO.read(new File("C:\\Users\\Basharat Ali\\Documents\\NetBeansProjects\\CarRacingGame\\images\\oppCar3.png"));
            oppCar4 = ImageIO.read(new File("C:\\Users\\Basharat Ali\\Documents\\NetBeansProjects\\CarRacingGame\\images\\oppCar4.png"));
            tree = ImageIO.read(new File("C:\\Users\\Basharat Ali\\Documents\\NetBeansProjects\\CarRacingGame\\images\\tree.png"));
        } catch (IOException ex) {
            Logger.getLogger(CarView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        carCtrl.space = 2;
        carCtrl.speed = 2;
        carCtrl.space = 300;
        this.carArena = carArena;
        
        // Make Player's Car (Rectangle), and adjust it's location
        car = new Rectangle((carArena.frameWidth-150)/2 - 68, carArena.frameHeight - 125, carWidth, carHeight);    /* (margin_left, margin_top, rect_width, rec_height) */
        
  
        addLines(true);
        addLines(true);
        addLines(true);
        addLines(true);
        addLines(true);
        addLines(true);
        
        // Make Opponent's Car
        addOppCars(true);
        addOppCars(true);
        addOppCars(true);
        addOppCars(true);
        
        addTree(true);
        addTree(true);
        addTree(true);
        addTree(true);
        addTree(true);
        addTree(true);
    }
    
    public void addLines(boolean isFirstLine)
    {
        
        int x = (carArena.frameWidth-150)/2 - 2;
        int y = 700;
        int width = 4;
        int height = 100;
        int lineSpace = 130;
        if(isFirstLine)
        {
            line.add(new Rectangle(x, y - (line.size() * lineSpace), width, height));
            boundry1.add(new Rectangle(150, y - (boundry1.size() * (lineSpace - 3)), 20, 50));
            boundry2.add(new Rectangle(332, y - (boundry2.size() * (lineSpace - 3)), 18, 50));
        }
        else
        {
            line.add(new Rectangle(x, line.get(line.size()-1).y - lineSpace, width, height));
            boundry1.add(new Rectangle(150, boundry1.get(boundry1.size()-1).y - lineSpace, 20, 50));
            boundry2.add(new Rectangle(332, boundry2.get(boundry2.size()-1).y - lineSpace, 18, 50));
        }
    }
    
    public void addOppCars(boolean isFirstCar)
    {
        int carSide;
        decideCar = new Random();
        carSide = decideCar.nextInt() % 2;       // Answer will always 1 or 0
        int X = 0, Y = 0;
        int oppWidth = carWidth, oppHeight = carHeight;
        
        if(carSide == 0)
            X = (carArena.frameWidth-150)/2 - 68;     // car on left side of road
        else
            X = (carArena.frameWidth-150)/2 + 10;     // car on right side of road
        
        if(isFirstCar)
            oppCars.add(new Rectangle(X, (Y - 100 - (oppCars.size()*carCtrl.space)), oppWidth, oppHeight));   // 1st appearance of any car
        else
            oppCars.add(new Rectangle(X, oppCars.get(oppCars.size()-1).y-300, oppWidth, oppHeight));    // After 1st appearance
        scoreStart = true;
    }
        
    public void addTree(boolean isFirstTree)
    {
        
        int treeSpace = 210;
        int y = 700;
        
        if(isFirstTree)   // 1st appearance of any car
        {
            treeArr.add(new Rectangle(43, y - (treeArr.size() * (treeSpace - 3)), 50, 50));     /* (margin_left, margin_top, rect_width, rec_height) */
            treeArr.add(new Rectangle(400, y - (treeArr.size() * (treeSpace - 3)), 50, 50));
        }
        else    // After 1st appearance
        {
            treeArr.add(new Rectangle(43, y - (treeArr.size() * (treeSpace - 3)), 50, 50));    /* (margin_left, margin_top, rect_width, rec_height) */
            treeArr.add(new Rectangle(400, y - (treeArr.size() * (treeSpace - 3)), 50, 50));
        }
        
//        if(isFirstLine)
//        {
//            boundry1.add(new Rectangle(150, y - (boundry1.size() * (lineSpace - 3)), 20, 50));
//            boundry2.add(new Rectangle(332, y - (boundry2.size() * (lineSpace - 3)), 18, 50));
//        }
//        else
//        {
//            line.add(new Rectangle(x, line.get(line.size()-1).y - lineSpace, width, height));
//            boundry1.add(new Rectangle(150, boundry1.get(boundry1.size()-1).y - lineSpace, 20, 50));
//            boundry2.add(new Rectangle(332, boundry2.get(boundry2.size()-1).y - lineSpace, 18, 50));
//        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        
        MyFileHandler mf = new MyFileHandler();
        String prevScore = mf.readMyFile();
            
        super.paintComponent(g);
//        g.setColor(Color.cyan);         // background
//        g.fillRect(0, 0, carArena.frameWidth, carArena.frameHeight);
        g.setColor(Color.black);     // Road
        g.fillRect(0, 0, carArena.frameWidth, carArena.frameHeight);    /* (margin_left, margin_top, rect_width, rec_height) */
                
        //observer is not needed for the BufferedImage class
        g.drawImage(roadGrass, 0, 0, null);     //(Image img, int x, int y, ImageObserver observer)
        g.drawImage(road, (carArena.frameWidth-150)/2 - 100, 0, null);

        /* Player's Car */
//        g.setColor(Color.red);
//        g.fillRect(car.x, car.y, car.width, car.height);    // car.x give value of x-axis of object car
        
        // Line color
        g.setColor(Color.white);
        // Line b/w Road
        for(Rectangle rect:line)
        {
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
        
        g.setColor(Color.yellow);
        for(Rectangle rect:boundry1)
        {
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
        
        for(Rectangle rect:boundry2)
        {
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
                
        g.drawImage(playerCar, car.x, car.y, null);     //(Image img, int x, int y, ImageObserver observer)
        
        // Line b/w road
//        g.setColor(Color.blue);
//        g.drawLine(carArena.frameWidth/2, 0, (carArena.frameWidth-150)/2, carArena.frameHeight);
        
//        g.setColor(Color.white);
        
        // for-each loop
        // For each Rectangle rect in the array oppCars
        
        
        if(removeNum % 4 == 0)
        {
            g.drawImage(oppCar1, oppCars.get(0).x, oppCars.get(0).y, null);     //(Image img, int x, int y, ImageObserver observer)
            g.drawImage(oppCar2, oppCars.get(1).x, oppCars.get(1).y, null);
            g.drawImage(oppCar3, oppCars.get(2).x, oppCars.get(2).y, null);
            g.drawImage(oppCar4, oppCars.get(3).x, oppCars.get(3).y, null);
        }
        else if(removeNum % 4 == 1)
        {
            g.drawImage(oppCar2, oppCars.get(0).x, oppCars.get(0).y, null);
            g.drawImage(oppCar3, oppCars.get(1).x, oppCars.get(1).y, null);
            g.drawImage(oppCar4, oppCars.get(2).x, oppCars.get(2).y, null); 
            g.drawImage(oppCar1, oppCars.get(3).x, oppCars.get(3).y, null);
        }
        else if(removeNum % 4 == 2)
        {
            g.drawImage(oppCar3, oppCars.get(0).x, oppCars.get(0).y, null);
            g.drawImage(oppCar4, oppCars.get(1).x, oppCars.get(1).y, null);
            g.drawImage(oppCar1, oppCars.get(2).x, oppCars.get(2).y, null);
            g.drawImage(oppCar2, oppCars.get(3).x, oppCars.get(3).y, null);
        }
        else if(removeNum % 4 == 3)
        {
            g.drawImage(oppCar4, oppCars.get(0).x, oppCars.get(0).y, null);
            g.drawImage(oppCar1, oppCars.get(1).x, oppCars.get(1).y, null);
            g.drawImage(oppCar2, oppCars.get(2).x, oppCars.get(2).y, null);
            g.drawImage(oppCar3, oppCars.get(3).x, oppCars.get(3).y, null);
        }
                
            g.drawImage(tree, treeArr.get(0).x, treeArr.get(0).y, null);     //(Image img, int x, int y, ImageObserver observer)
            g.drawImage(tree, treeArr.get(1).x, treeArr.get(1).y, null);
            g.drawImage(tree, treeArr.get(2).x, treeArr.get(2).y, null);
            g.drawImage(tree, treeArr.get(3).x, treeArr.get(3).y, null);
            g.drawImage(tree, treeArr.get(4).x, treeArr.get(4).y, null);
            g.drawImage(tree, treeArr.get(5).x, treeArr.get(5).y, null);
            
        g.setColor(Color.white);
        Font f = new Font("", 20, 15);
        g.setFont(f);
        
        
        g.drawString("Stage: " + stageNum, 510, 50);
        
        g.drawString("Score: " + score, 510, 70);
        if(!gameOver)
            g.drawString("Speed: " + stageNum * 10 + " kmph", 510, 90);
        else
            g.drawString("Speed: 0 kmph", 510, 90);
        
        if(!gameOver)
            g.drawString("Status: " + "Playing", 510, 110);
        else
            g.drawString("Status: " + "Game Over", 510, 110);
        
        g.drawString("Highest Score: " + prevScore, 510, 140);
        
        g.setFont(new Font("", Font.BOLD, 20));
        g.drawString("Racing Mania", 510, 30);
        
        g.setFont(new Font("", Font.BOLD, 25));
        if(gameOver)
        {
            prevScore = mf.readMyFile();
            if(score > parseInt(prevScore))
            {
                mf.writeMyFile(Integer.toString(score));
            }
            g.drawString("Game Over", 185, 200);
        }
//        if(stageComplete)
//            g.drawString("Stage Complete", 180, 200);
        repaint();
    }
}