/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagenes;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author familia
 */
public class eqImage extends JPanel{
      
    JPanel panel;
    public eqImage()
    {
        panel=this;
        panel.setSize(60,60);
   
    }
  

   
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
         g.drawRect(0, 0,50,50);
     g.setColor(Color.RED);
     g.fillRect(0, 0,50,50);
     super.repaint();
    }
     public void pintarPanel()
    {
    if (this.getGraphics() != null) {
                paint(panel.getGraphics());
            }
   
    }
    
   
}
