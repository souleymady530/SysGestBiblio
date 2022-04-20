/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anim;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author ofilw
 */
public class panAnim extends JPanel{
     private int posX=0,poxY=0;

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPoxY() {
        return poxY;
    }

    public void setPoxY(int poxY) {
        this.poxY = poxY;
    }
    
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(posX-60, poxY, this.getWidth(), this.getHeight());
        
        g.setColor(Color.black);
        g.fillOval(posX, poxY, 10,10);
        
         g.setColor(Color.black);
        g.fillOval(posX-10, poxY, 10,10);
        
         g.setColor(Color.blue);
        g.fillOval(posX-20, poxY, 10,10);
        
         g.setColor(Color.blue);
        g.fillOval(posX-30, poxY, 10,10);
        
         g.setColor(Color.red);
        g.fillOval(posX-40, poxY, 10,10);
        
         g.setColor(Color.red);
        g.fillOval(posX-50, poxY, 10,10);
    }
    
    
}
