/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetres;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author ofilw
 */
public class panAnimation extends JPanel
{
    private int posX=-50,poxY=190;

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
        
        g.setColor(Color.gray);
        g.fillRect(0,0, this.getWidth(),this.getHeight());
        g.setColor(Color.black);
        g.fillOval(posX, poxY, 5, 5);
        g.setColor(Color.red);
        g.fillOval(posX-10, poxY, 5, 5);
         g.setColor(Color.blue);
        g.fillOval(posX-20, poxY, 5, 5);
         g.setColor(Color.green);
        g.fillOval(posX-30, poxY, 5, 5);
    }
    
}
