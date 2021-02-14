
import java.awt.Rectangle;
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
public class KeyHnd {
    CarView carView = new CarView();
    private int move = 20;
    private int count = 0;
    int speed;
//    Timer timer;        // creating a Timer object, registering one or more action listeners on it, and starting the timer using the start method
    
    public KeyHnd(CarView carView)
    {
        this.carView  = carView;
        initKeyHnd();
        speed = 100;
        
//        timer = new Timer(20, (ActionListener) carView);
//        timer.start();       // starting the timer using the start method
    }
    
    public void initKeyHnd()
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
        }
    }
}
