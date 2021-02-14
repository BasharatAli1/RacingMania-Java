
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

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
    private int carHeight = 80, carWidth = 60;
    ArrayList<Rectangle> oppCars;       // Array of rectangle type
    Rectangle car;
    Random rand;        // to appear cars on both sides of roads
    
    BufferedImage roadGrass;
    BufferedImage road;
    BufferedImage playerCar;
    BufferedImage oppCar1;
    BufferedImage oppCar2;
    BufferedImage oppCar3; 
    
    
    public CarView()
    {
        System.out.println("CarView Constructor 2");
    }
    
    public CarView(CarArena carArena)
    {        
        System.out.println("CarView Constructor");
        oppCars = new ArrayList<Rectangle>();        
        
        carCtrl = new CarCtrl(this);

        try {
            road = ImageIO.read(new File("C:\\Users\\Basharat Ali\\Documents\\NetBeansProjects\\JavaApplication25\\images\\road.png"));
            roadGrass = ImageIO.read(new File("C:\\Users\\Basharat Ali\\Documents\\NetBeansProjects\\JavaApplication25\\images\\RoadGrass.png"));        
            playerCar = ImageIO.read(new File("C:\\Users\\Basharat Ali\\Documents\\NetBeansProjects\\CarRacingGame\\images\\playerCar.png"));
        oppCar1 = ImageIO.read(new File("C:\\Users\\Basharat Ali\\Documents\\NetBeansProjects\\JavaApplication25\\images\\oppCar1.png"));
        oppCar2 = ImageIO.read(new File("C:\\Users\\Basharat Ali\\Documents\\NetBeansProjects\\JavaApplication25\\images\\oppCar2.png"));
        oppCar3 = ImageIO.read(new File("C:\\Users\\Basharat Ali\\Documents\\NetBeansProjects\\JavaApplication25\\images\\oppCar3.png"));
        } catch (IOException ex) {
            Logger.getLogger(CarView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        carCtrl.space = 2;
        carCtrl.speed = 2;
        carCtrl.space = 300;
        this.carArena = carArena;
        
        // Make Player's Car (Rectangle)
        car = new Rectangle(carArena.frameWidth/2 - 68, carArena.frameHeight - 110, carWidth, carHeight);    /* (margin_left, margin_top, rect_width, rec_height) */
        System.out.println(carArena.frameWidth);
        System.out.println(carArena.frameHeight);
        
        setFocusable(true);
        // Make Opponent's Car
        addOppCars(true);
        addOppCars(true);
        addOppCars(true);
        addOppCars(true);
    }
    
    public void addOppCars(boolean isFirstCar)
    {
        int positionx;
        rand = new Random();
        positionx = rand.nextInt() % 2;       // Answer will always 1 or 0
        int X = 0, Y = 0;
        int oppWidth = carWidth, oppHeight = carHeight;
        
        if(positionx == 0)
            X = carArena.frameWidth/2 - 68;     // car on left side of road
        else
            X = carArena.frameWidth/2 + 10;     // car on right side of road
        if(isFirstCar)
            oppCars.add(new Rectangle(X, (Y - 100 - (oppCars.size()*carCtrl.space)), oppWidth, oppHeight));   // 1st appearance of any car
        else
            oppCars.add(new Rectangle(X, oppCars.get(oppCars.size()-1).y-300, oppWidth, oppHeight));    // After 1st appearance
    }
        @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
//        g.setColor(Color.cyan);         // background
//        g.fillRect(0, 0, carArena.frameWidth, carArena.frameHeight);    /* (margin_left, margin_top, rect_width, rec_height) */
//        g.setColor(Color.gray);     // Road
//        g.fillRect(carArena.frameWidth/2 - 100, 0, 200, carArena.frameHeight);
                
        g.drawImage(roadGrass, 0, 0, null);
        g.drawImage(road, carArena.frameWidth/2 - 100, 0, null);

        /* Player's Car */
//        g.setColor(Color.red);
//        g.fillRect(car.x, car.y, car.width, car.height);    // car.x give value of x-axis of object car
                
        g.drawImage(playerCar, car.x, car.y, null);
        
        g.setColor(Color.blue);
        g.drawLine(carArena.frameWidth/2, 0, carArena.frameWidth/2, carArena.frameHeight);
        
//        g.setColor(Color.white);
        
        // for-each loop
        // For each Rectangle rect in the array oppCars
        for(Rectangle rect:oppCars)
        {
            int oppCarNum;
            rand = new Random();
            oppCarNum = rand.nextInt() % 3 + 1;       // Answer will always 1 or 0
//            g.fillRect(rect.x, rect.y, rect.width, rect.height);
            
            if(oppCarNum == 1)
                g.drawImage(oppCar1, rect.x, rect.y, null);
            else if(oppCarNum == 2)
            {
                g.drawImage(oppCar2, rect.x, rect.y, null);
            }
            else if(oppCarNum == 3)
            {
                g.drawImage(oppCar3, rect.x, rect.y, null);
            }
        }
        
        
        repaint();
    }

}
