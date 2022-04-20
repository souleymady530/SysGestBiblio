/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anim;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;

/**
 *
 * @author ofilw
 */
public class fenAnim extends JFrame
{
    
     panAnim pa=new panAnim();
    public fenAnim()
    {
        this.setTitle("Système de Gestion de Bibliothèque V1.0");
        this.setSize(660, 320);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        panImage pi=new panImage();
        pi.setLayout(new BorderLayout());
        pi.add(pa,BorderLayout.SOUTH);
       pi.add(new pi(),BorderLayout.CENTER);
      this.getContentPane().add(pi);
      this.setResizable(false);
         //this.setUndecorated(true);
     //  this.getRootPane().setBounds(-50, -50, WIDTH, HEIGHT);this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));
         this.setVisible(true);
         
      go();
         
        this.dispose();
     
        
    }
    
    
   
    
    
    public void go()
    {
        
            //Or While(true)
        
            for(int i=0;i<1000;i++)
            {

                int x=pa.getPosX(),y=pa.getPoxY();
                x++;
                
                pa.setPosX(x);

                pa.repaint();
                
                
                try
                {
                    Thread.sleep(2);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                
                if(x == pa.getWidth() || y == pa.getHeight())
                {
                    pa.setPosX(0);
                    pa.setPoxY(0);
                }

            
            } 
          
        }
       
    
    
}
