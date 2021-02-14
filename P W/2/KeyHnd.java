
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class KeyHnd implements ActionListener
{
    Timer timer;        // creating a Timer object, registering one or more action listeners on it, and starting the timer using the start method

    CarView carView = new CarView();
    private int move = 20;
    private int count = 0;
    int speed;
    int space;
    
    public KeyHnd(CarView carView)
    {
        this.carView  = carView;
        initKeyHnd();
        // parameters are delay (in ms) and ActionListener. The delay parameter is used
        // to set both the initial delay and the delay between event firing
        timer = new Timer(20, this); 
        timer.start();       // starting the timer using the start method
    }
    
    public void initKeyHnd()
    {
    
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {    
        Rectangle rect;
        count++;
        
        // speed up cars after a specific time
        for(int i=0; i < carView.oppCars.size(); i++)
        {
            rect = carView.oppCars.get(i);
            if(count % 1000 == 0)
            {
                if(move < 10)
                    speed++;
                    move += 10;
            }
            rect.y += speed;
            System.out.println("ok");
        }
        // player's_Car crashing with opp_Car
        for(Rectangle r:carView.oppCars)
        {
            if(r.intersects(carView.car))
                carView.car.y = r.y + carView.car.height;
        }
        
        // remove opp car when it geos to bottom
        for(int i=0; i<carView.oppCars.size(); i++)
        {
            rect=carView.oppCars.get(i);    // .get return element at array's ith elemen
            if(rect.y+rect.height > carView.carArena.frameHeight + 80)
            {
                carView.oppCars.remove(rect);
                carView.addOppCars(false);
            }
        }
        
        // speed of line
//        for(int i=0; i < line.size(); i++)
//        {
//            rect = line.get(i);
//            if(count % 1000 == 0)
//            {
//                speed++;
//            }
//            rect.y += speed;
//        }
//        
//        // Remove line
//        for(int i=0; i<line.size(); i++)
//        {
//            rect = line.get(i);    // .get return element at array's ith elemen
//            if(rect.y > frameHeight)
//            {
//                line.remove(rect);
//                lineFlag = false;
//                addLines(false);
//            }
//        }
    }
}
