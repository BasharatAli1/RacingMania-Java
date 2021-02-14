import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    int mul = 10;
    CarView carView = new CarView();
    private int move = 20;
    private int count = 0;
    int speed = 2;
    int space;
    int life = 2;
    
    public CarCtrl(CarView carView)
    {
        this.carView  = carView;
        
        // parameters are delay (in ms) and ActionListener. The delay parameter is used
        // to set both the initial delay and the delay between event firing
        timer = new Timer(20, this);
        timer.start();       // starting the timer using the start method
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        // if carView.scoreStart == true, then oppCar exist
        if(carView.scoreStart && (carView.oppCars.get(0).y >= carView.car.y + 100))
        {
            carView.score = carView.score + 1;
            // next stage
            if(carView.score % 5 == 0)
            {
                carView.stageComplete = true;
//                try {
//                    
//                    carView.label.setText("Stage Completed");
//                    carView.carArena.frame.add(carView.label);
//                    sleep(2000);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(CarCtrl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                    carView.label.setText("");
                carView.stageComplete = false;
                carView.stageNum++;
                speed += 4;
            }
            carView.scoreStart = false;
        }
        Rectangle rect;
        count++;
        
        
        // for-each loop
        // For each Rectangle r in the array oppCars
        
        // player's_Car crashing with opp_Car
        for(Rectangle r:carView.oppCars)
        {
            if(r.intersects(carView.car))
            {
//                carView.car.y = r.y + carView.car.height;
//                timer.stop();
                carView.gameOver = true;
                move = 0;
                speed = 0;
            }
        }
        
        
        for(int i=0; i < carView.oppCars.size(); i++)
        {
            rect = carView.oppCars.get(i);    // .get return element at array's ith elemen

            // Move opp car and tree forward
            rect.y += speed;
            
            if(rect.y+rect.height > carView.carArena.frameHeight + 80)
            {
                // remove opp car and tree when it geos to bottom
                carView.oppCars.remove(rect);
                
                carView.addOppCars(false);
                carView.addTree(false);
                // on the base of removeNum we will decide the next combination of different opponent cars
                carView.removeNum++;
            }
        }
        
        for(int i=0; i < carView.treeArr.size(); i++)
        {
            rect = carView.treeArr.get(i);    // .get return element at array's ith elemen

            // Move opp car and tree forward
            rect.y += speed;
            
            if(rect.y+rect.height > carView.carArena.frameHeight + 80)
            {
                // remove opp car and tree when it geos to bottom
                carView.treeArr.remove(rect);
                
                carView.addTree(false);
            }
        }
        
        // speed of carView.line
        for(int i=0; i < carView.line.size(); i++)
        {
            rect = carView.line.get(i);    // .get return element at array's ith element
            // Move line forward
            rect.y += speed;
            
        // Remove carView.line
            if(rect.y > carView.carArena.frameHeight)
            {
                carView.line.remove(rect);
                carView.addLines(false);
            }
        }
        
        for(int i=0; i < carView.boundry1.size(); i++)
        {
            rect = carView.boundry1.get(i);
            // Move boundry forward
            rect.y += speed;
            
        // Remove boundry1.line
            if(rect.y > carView.carArena.frameHeight)
            {
                carView.boundry1.remove(rect);
                carView.addLines(false);
            }
        }
        
        for(int i=0; i < carView.boundry2.size(); i++)
        {
            rect = carView.boundry2.get(i);
            // Move boundry forward
            rect.y += speed;
            
        // Remove boundry2.line
            rect = carView.boundry2.get(i);
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
        if(carView.car.x - move > (carView.carArena.frameWidth-150)/2 - 72)
            carView.car.x -= move;
    }
    public void moveRight()
    {
        if(carView.car.x + move < (carView.carArena.frameWidth-150)/2 + 28)
            carView.car.x += move;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {int key = ke.getKeyCode();
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

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
}
