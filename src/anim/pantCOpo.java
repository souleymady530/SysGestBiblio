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
public class pantCOpo extends JPanel
{
    
     public void paintComponent(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
       
        
        g.draw3DRect(30, 30, 400, 200, true);
       
        // g.setColor(Color.black);
        //g.fill3DRect(40, 60, 400,150,true);
    }
}
