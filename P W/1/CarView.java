
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
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
    KeyHnd keyHnd;
    int space;
    private int carHeight = 80, carWidth = 60;
    ArrayList<Rectangle> oppCars;       // Array of rectangle type
    Rectangle car;
    Random rand;        // to appear cars on both sides of roads
    
    public CarView()
    {
        System.out.println("CarView Constructor 2");
        keyHnd = new KeyHnd(this);
    }
    
    public CarView(CarArena carArena)
    {
        System.out.println("CarView Constructor");
        oppCars = new ArrayList<Rectangle>();
        space = 2;
        this.carArena = carArena;
        
        // Make Player's Car
        car = new Rectangle(carArena.frameWidth/2 - 68, carArena.frameHeight - 110, carWidth, carHeight);    /* (margin_left, margin_top, rect_width, rec_height) */
        System.out.println(carArena.frameWidth);
        System.out.println(carArena.frameHeight);
    }
    
    public void addOppCars(boolean isFirstCar)
    {
        int positionx = rand.nextInt()%2;       // Answer will always 1 or 0
        int X = 0, Y = 0;
        int oppWidth = carWidth, oppHeight = carHeight;
        
        if(positionx == 0)
            X = carArena.frameWidth/2 - 68;     // car on left side of road
        else
            X = carArena.frameWidth/2 + 10;     // car on right side of road
        if(isFirstCar)
            oppCars.add(new Rectangle(X, (Y - 100 - (oppCars.size()*space)), oppWidth, oppHeight));   // 1st appearance of any car
        else
            oppCars.add(new Rectangle(X, oppCars.get(oppCars.size()-1).y-300, oppWidth, oppHeight));    // After 1st appearance
        System.out.println("Y = " + Y);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        g.setColor(Color.cyan);         // background
        g.fillRect(0, 0, carArena.frameWidth, carArena.frameHeight);    /* (margin_left, margin_top, rect_width, rec_height) */
        g.setColor(Color.gray);     // Road
        g.fillRect(carArena.frameWidth/2 - 100, 0, 200, carArena.frameHeight);

        /* Player's Car */
        g.setColor(Color.red);
        g.fillRect(car.x, car.y, car.width, car.height);    // car.x give value of x-axis of object car
        
        g.setColor(Color.blue);
        g.drawLine(carArena.frameWidth/2, 0, carArena.frameWidth/2, carArena.frameHeight);
        
        for(Rectangle rect:oppCars)
        {
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
        
        
        repaint();
    }
}
