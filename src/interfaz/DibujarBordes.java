/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import de.craften.ui.swingmaterial.MaterialColor;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Scrollbar;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.SliderUI;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicProgressBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.BasicSliderUI;
import javax.swing.plaf.metal.MetalSliderUI;
import javax.swing.plaf.synth.SynthContext;
import javax.swing.plaf.synth.SynthProgressBarUI;
import javax.swing.plaf.synth.SynthSliderUI;


/**
 *
 * @author familia
 */
public class DibujarBordes {
            Insets i;
  public Border bordeBandeja(){
    return new Border(){
        Insets i;
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            
              Graphics2D g2 = (Graphics2D) g;
        Paint gp = new ColorUIResource(MaterialColor.BLUEGREY_800);
        
           g2.setPaint(gp);
           g2.fillRoundRect(0, 0,c.getWidth()-15,c.getHeight()-15,25,25);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            i=new Insets(0, 0,0 , 0);
             return i; 
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    
    };
    }
    public Border borderContentPanel(){
    return new Border(){
        Insets i;
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            
              Graphics2D g2 = (Graphics2D) g;
        Paint gp = new ColorUIResource(MaterialColor.BLUEGREY_900);
        
           g2.setPaint(gp);
           g2.fillRoundRect(0, 0,c.getWidth(),c.getHeight(),25,25);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            i=new Insets(0, 0,0 , 0);
             return i; 
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    
    };
    }
    
        public Border buttonUp(){
    return new Border(){
        Insets i;
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            
              Graphics2D g2 = (Graphics2D) g;
        Paint gp = new ColorUIResource(MaterialColor.AMBER_300);
        
           
        }

        @Override
        public Insets getBorderInsets(Component c) {
            i=new Insets(0, 0,0 , 0);
             return i; 
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    
    };
    }
        public RoundRectangle2D.Float getShape(){
        
        return new RoundRectangle2D.Float(0, 0, 700,545, 25, 25);
      
        }
        
      public static Border bordeBoton(){
    return new Border(){
        Insets i;
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            
              Graphics2D g2 = (Graphics2D) g;
        Paint gp = new ColorUIResource(MaterialColor.TRANSPARENT);   
           g2.setPaint(gp);
           g2.drawRoundRect(0, 0,c.getWidth()-1,c.getHeight()-1,25,25);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            i=new Insets(0, 0,0 , 0);
             return i; 
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    
    };
    } 
      
      public  ButtonUI buttonUi1(){
    
        return new BasicButtonUI(){
       
         @Override
         public void paint(Graphics g, JComponent c) {
            super.paint(g, c); //To change body of generated methods, choose Tools | Templates.
           Graphics2D g2 = (Graphics2D) g;
            Paint gp = new ColorUIResource(MaterialColor.CYAN_900);
            g2.setPaint(gp);
            g2.setBackground(MaterialColor.BLUEGREY_700);
            g2.drawRoundRect(0, 0, c.getWidth()-2, c.getHeight()-2,2,2);
         }
        };
    }
      
         public  ButtonUI buttonUi2(){
    
        return new BasicButtonUI(){
       
         @Override
         public void paint(Graphics g, JComponent c) {
            super.paint(g, c); //To change body of generated methods, choose Tools | Templates.
           Graphics2D g2 = (Graphics2D) g;
            Paint gp = new ColorUIResource(MaterialColor.TRANSPARENT);
            g2.setPaint(gp);
            g2.setBackground(MaterialColor.BLUEGREY_700);
           g2.drawRoundRect(0, 0, c.getWidth()-2, c.getHeight()-2,2,2);
         }
        };
    }
     public ProgressBarUI progressBarUI1(){
         return new BasicProgressBarUI() {
             @Override
             public void paint(Graphics g, JComponent c) {
                 super.paint(g, c); //To change body of generated methods, choose Tools | Templates.
                 Graphics2D g2 = (Graphics2D) g;
                 Paint gp = new ColorUIResource(MaterialColor.GREEN_800);
                 g2.setPaint(gp);
                 if(progressBar.getPercentComplete()>0.0f)
                g2.drawRect(0, 0,(int)(progressBar.getWidth()*progressBar.getPercentComplete()), progressBar.getHeight());
             }

           


           
             
             
             
           
         /*
             @Override
             protected void paintIndeterminate(Graphics g, JComponent c) {
                 super.paintIndeterminate(g, c); //To change body of generated methods, choose Tools | Templates.
                
             }
         
*/
           
         
             
         };
         }
      public  ScrollBarUI newScrollBarUI(int size) {
    return new BasicScrollBarUI() {
        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            super.paintTrack(g, c, trackBounds); //To change body of generated methods, choose Tools | Templates.
            
            int tw = trackBounds.width;
            int th = trackBounds.height;
                       
            Graphics2D g2 = (Graphics2D) g;
            Paint gp = null;
             gp=new ColorUIResource(MaterialColor.BLUEA_200);
             g2.setPaint(gp);
            g2.fillRect(7, 20,7, c.getHeight()-40);
             setTrackColor(MaterialColor.BLUEGREY_800);
            setScrollBarWidth(size);
            c.invalidate();
        }
           
        @Override
        public void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            super.paintTrack(g, c, thumbBounds);
            
            int tw = thumbBounds.width;
            int th = thumbBounds.height;
           
            g.translate(thumbBounds.x, thumbBounds.y);
            Graphics2D g2 = (Graphics2D) g;
            Paint gp = null;
             
            if (this.scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                  gp = new ColorUIResource(MaterialColor.BLUEA_400);
            }
            if (this.scrollbar.getOrientation() == JScrollBar.HORIZONTAL) {
         gp = new ColorUIResource(MaterialColor.BLUEA_400);
            }
            
            g2.setPaint(gp);
            g2.fillOval(0, 0, 20, 20);
          c.invalidate();
        }

        @Override
        protected BasicScrollBarUI.ArrowButtonListener createArrowButtonListener() {
            return super.createArrowButtonListener(); //To change body of generated methods, choose Tools | Templates.
        }

        public void setScrollBarWidth(int scrollBarWidth) {
            this.scrollBarWidth = scrollBarWidth;
        }

        public void setThumbColor(Color thumbColor) {
            this.thumbColor = thumbColor;
        }

        public void setTrackColor(Color trackColor) {
            this.trackColor = trackColor;
        }

        public void setTrackRect(Rectangle trackRect) {
            this.trackRect = trackRect;
        }
     

        public void setThumbDarkShadowColor(Color thumbDarkShadowColor) {
            this.thumbDarkShadowColor = thumbDarkShadowColor;
        }

        public void setThumbLightShadowColor(Color thumbLightShadowColor) {
            this.thumbLightShadowColor = thumbLightShadowColor;
        }
        
        
        

        
        

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton(0);
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton(1);
        }
        

        private JButton createZeroButton(int pos) {
            JButton jbutton = new JButton();
            jbutton.setPreferredSize(new Dimension(20, 20));
            jbutton.setMinimumSize(new Dimension(20, 20));
            jbutton.setMaximumSize(new Dimension(20,20));
            jbutton.setUI(buttonUi(pos));
            jbutton.setBorder(new DibujarBordes().buttonUp());
          
            
            return jbutton;
        }
    };
}

private static ButtonUI buttonUi(int pos){
return new BasicButtonUI(){
        

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c); //To change body of generated methods, choose Tools | Templates.
           Graphics2D g2 = (Graphics2D) g;
         Paint   gp=new ColorUIResource(MaterialColor.BLUEGREY_800);
              g2.setPaint(gp);
              g2.fillRect(0, 0, c.getWidth(), c.getHeight());           
         gp = new ColorUIResource(MaterialColor.CYAN_900);
         g2.setPaint(gp);
         switch(pos){
             case 0:
              int[] Xcoord = {0, c.getWidth()/2,  c.getWidth()};
             int[] Ycoord = { c.getHeight(), 0, c.getHeight()};
             //            g.fillRect(0, 0, 20, 20);
              g2.fillPolygon(Xcoord,Ycoord, 3);
              break;
             case 1:
                   int[]Xcoord1 = {0, c.getWidth()/2,  c.getWidth()};
             int[] Ycoord1 = { 0, c.getHeight(),0};
             //            g.fillRect(0, 0, 20, 20);
              g2.fillPolygon(Xcoord1,Ycoord1, 3);
              break;
            
    }
        
          
    }
    

};
}

public SliderUI getSliderUI(JSlider b){
    return new BasicSliderUI(b){
      
   

        @Override
        public void paintThumb(Graphics g) {
            super.paintThumb(g); //To change body of generated methods, choose Tools | Templates.
            Graphics2D g2 = (Graphics2D) g;
            Paint gp = new ColorUIResource(MaterialColor.BLUEA_700);
            g2.setPaint(gp);
             b.setBackground(MaterialColor.BLUEGREY_900);
            g2.fillRect(b.getWidth()/4,0,b.getWidth()/2,b.getHeight());
        }

        @Override
        public void paintFocus(Graphics g) {
            super.paintFocus(g); //To change body of generated methods, choose Tools | Templates.
             Graphics2D g2 = (Graphics2D) g;
            Paint gp = new ColorUIResource(MaterialColor.AMBERA_400);
            g2.setPaint(gp);
             b.setBackground(MaterialColor.BLUEGREY_900);
            g2.fillRect(b.getWidth()/4,0,b.getWidth()/2,b.getHeight());
        }

        @Override
        protected void paintMinorTickForVertSlider(Graphics g, Rectangle tickBounds, int y) {
            super.paintMinorTickForVertSlider(g, tickBounds, y); //To change body of generated methods, choose Tools | Templates.
             Graphics2D g2 = (Graphics2D) g;
            Paint gp = new ColorUIResource(MaterialColor.AMBERA_400);
            g2.setPaint(gp);
             b.setBackground(MaterialColor.BLUEGREY_900);
            g2.fillRect(b.getWidth()/4,0,b.getWidth()/2,b.getHeight());
        }
        

      
        
        
    };
}
      
}
