/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import decksplayer.ventanamixer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author familia
 */
public class Imagenes extends JPanel{
JProgressBar jp;
    public Imagenes(JProgressBar jp) {
        this.setSize(82, 82);
        jp=jp;
    }
      @Override
        public void paint(Graphics g)
        {
            super.paint(g);
            Dimension heigh=getSize();
            
            Image image;
            Toolkit t1=Toolkit.getDefaultToolkit(); 
                image=t1.getImage(getClass().getResource("bandeja3.png"));
                double r=0;
      r= Math.toRadians(jp.getPercentComplete()*360);
            System.out.println(r);
       AffineTransform at=new AffineTransform();
    at.rotate(r, this.getWidth()/2,this.getHeight()/2); 
   ( (Graphics2D)g).setTransform(at); 
      image.setAccelerationPriority(1);
  g.drawImage(image, 5,5,this.getWidth()-9, this.getHeight()-9,this);
       g.setColor(Color.decode("#FF0000"));
          g.drawArc(4, 4, this.getWidth()-8, this.getHeight()-8, 0, (int)Math.toDegrees(r));
        g.drawArc(3, 3, this.getWidth()-7, this.getHeight()-7, 0, (int)Math.toDegrees(r));
      g.drawArc(2, 2, this.getWidth()-6, this.getHeight()-6, 0, (int)Math.toDegrees(r));
          g.drawArc(1, 1, this.getWidth()-5, this.getHeight()-5, 0, (int)Math.toDegrees(r));
     
     }

   
  
    
}
