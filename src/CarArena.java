
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Basharat Ali
 */
public class CarArena 
{
    JFrame frame;
    
    CarCtrl carCrl;
    int frameHeight;
    int frameWidth = 650;
    public CarArena()
    {
            initGUI();
    }
    public void initGUI()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frameHeight = (int) screenSize.getHeight() - 38;
        
        frame = new JFrame("Racing Mania");
//        frame.setBackground(Color.black);
        
    
        CarView carView = new CarView(this);    
        carCrl = new CarCtrl(carView);
        frame.add(carView);
        frame.addKeyListener((KeyListener) carCrl);
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setResizable(false);
        
        frame.setSize(frameWidth, frameHeight);     // .setSize(width, height)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
