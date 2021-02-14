
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
public class CarCtrl implements ActionListener, KeyListener
{
    Timer timer;        // creating a Timer object, registering one or more action listeners on it, and starting the timer using the start method

    CarView carView = new CarView();
    private int move = 20;
    private int count = 0;
    int speed;
    int space;
    
    public CarCtrl(CarView carView)
    {
        this.carView  = carView;
        initKeyHnd();
        // parameters are delay (in ms) and ActionListener. The delay parameter is used
        // to set both the initial delay and the delay between event firing
        timer = new Timer(20, this); 
        timer.start();       // starting the timer using the start method
        
//        addKeyListener(this);
        
    }
    
    public void initKeyHnd()
    {
    
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {    
        Rectangle rect;
        count++;
        
        // player's_Car crashing with opp_Car
        for(Rectangle r:carView.oppCars)
        {
            if(r.intersects(carView.car))
                carView.car.y = r.y + carView.car.height;
        }
        
        // speed up cars after a specific time
        for(int i=0; i < carView.oppCars.size(); i++)
        {
            rect = carView.oppCars.get(i);
            if(count % 1000 == 0)
            {
                if(move < 10)
                    speed++;
            }
            rect.y += speed;
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
        
        // speed of carView.line
        for(int i=0; i < carView.line.size(); i++)
        {
            rect = carView.line.get(i);
            if(count % 1000 == 0)
            {
                speed++;
            }
            rect.y += speed;
        }
        
        // Remove carView.line
        for(int i=0; i<carView.line.size(); i++)
        {
            rect = carView.line.get(i);    // .get return element at array's ith elemen
            if(rect.y > carView.carArena.frameHeight)
            {
                carView.line.remove(rect);
                carView.addLines(false);
            }
        }
        
        // speed of boundry1.line
        for(int i=0; i < carView.boundry1.size(); i++)
        {
            rect = carView.boundry1.get(i);
            if(count % 1000 == 0)
            {
                speed++;
            }
            rect.y += speed;
        }
        
        // speed of boundry2.line
        for(int i=0; i < carView.boundry2.size(); i++)
        {
            rect = carView.boundry2.get(i);
            if(count % 1000 == 0)
            {
                speed++;
            }
            rect.y += speed;
        }
        
        // Remove boundry1.line
        for(int i=0; i<carView.boundry1.size(); i++)
        {
            rect = carView.boundry1.get(i);    // .get return element at array's ith elemen
            if(rect.y > carView.carArena.frameHeight)
            {
                carView.boundry1.remove(rect);
                carView.addLines(false);
            }
        }
        
        // Remove boundry2.line
        for(int i=0; i<carView.boundry2.size(); i++)
        {
            rect = carView.boundry2.get(i);    // .get return element at array's ith elemen
            if(rect.y > carView.carArena.frameHeight)
            {
                carView.boundry2.remove(rect);
                carView.addLines(false);
            }
        }
    }

    public void moveUp()
    {
        if(carView.car.y - move > 0)
            carView.car.y -= move;
    }
    
    public void moveDown()
    {

        if(carView.car.y + carView.car.height + move < carView.carArena.frameHeight - 18)
            carView.car.y += move;
    }
    public void moveLeft()
    {
        if(carView.car.x - move > carView.carArena.frameWidth/2 - 72)
            carView.car.x -= move;
    }
    public void moveRight()
    {
        if(carView.car.x + move < carView.carArena.frameWidth/2 + 28)
            carView.car.x += move;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int key = ke.getKeyCode();
        switch(key)
        {
            case KeyEvent.VK_UP:
                moveUp();
                break;
            case KeyEvent.VK_DOWN:
                moveDown();
                break;
            case KeyEvent.VK_LEFT:
                moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                moveRight();
                break;
            default:
                break;
        }
    }
}
