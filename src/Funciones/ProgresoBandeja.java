/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import decksplayer.ventanamixer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author familia
 */
public class ProgresoBandeja extends JPanel{
    private JPanel panel;
    private Timer timer;
    private int tiempo;
    private JProgressBar barraDeProgreso;
    private JLabel textField;
    private int h=0,m=0,s=0;
    private JSlider slider;
    private int tipo;
    private double porc=0;
    private boolean automixer;
    private JTextField nombre;
   
    

  
    
    public  ProgresoBandeja(int tiempo,int tipo,JProgressBar barraDeProgreso,JLabel texto,Listener listener,JSlider slider,JTextField nombre)
   {
     this.tiempo=tiempo;
     this.barraDeProgreso=barraDeProgreso;
     this.textField=texto;
     this.slider=slider;
     this.tipo=tipo;
     this.panel=this;
     this.nombre=nombre;
     panel.setSize(64, 64);
       
     timer=new Timer(tiempo, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
          barraDeProgreso.setValue(listener.progresos());
          
           s++;
           if(s==60)
           {
               m++;
               s=0;
           }else if(m==60)
           {
           m=0;
           s=0;
           h++;
           }
           if(automixer)
             crossfade(tipo, (int) porc);
           if((int) porc==100)
           {
           stop();
           }
           
          // textField.setText(h+":"+m+":"+s);
         panel.updateUI();
         }
        
     });
   }  
    public void pintarPanel()
    {
    if (this.getGraphics() != null) {
                paint(panel.getGraphics());
            }
   
    }
    public void start()
    {
    timer.start();
    }
    public void stop()
    {
    timer.stop();
    h=0;
    m=0;
    s=0;
    }
    public void restart()
    {
    timer.restart();
    }
     @Override
        public void paint(Graphics g) {
            super.paint(g);
           
           
            Dimension heigh = panel.getSize();

            Image image;
            Toolkit t1 = Toolkit.getDefaultToolkit();
            image = t1.getImage(getClass().getResource("/dj/Imagenes/bandeja3.png"));
            //  ImageIcon img=new ImageIcon(image);
            // g.drawImage(image ,0,0,this);

            double r = Math.toRadians(barraDeProgreso.getPercentComplete() * 360);
            AffineTransform at = new AffineTransform();
            System.out.println(r);

            at.rotate(r, this.getWidth() / 2, this.getHeight() / 2);
            ((Graphics2D) g).setTransform(at);
            image.setAccelerationPriority(1);
           // porc2 = jProgressBar4.getPercentComplete() * 100;
            Color c=new Color(255, 0, 0, 80);

            g.setColor(c);
            g.fillArc(1, 1, this.getHeight() - 2, this.getWidth() - 2, 0, (int) Math.toDegrees(r));
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(),panel);
            //this.repaint();
            porc=barraDeProgreso.getPercentComplete()*100;
            super.repaint();
        }
        private void crossfade(int n, int num) {
        switch (n) {
            case 1:
                switch (num) {
                    case 90:
                        ventanamixer.b2.setPosRandom();
                                     
            try {
                ventanamixer.timerBandeja2.nombre.setText(ventanamixer.b2.devolverNombre(""));
            } catch (Exception ex) {
                Logger.getLogger(ProgresoBandeja.class.getName()).log(Level.SEVERE, null, ex);
            }
        
                        
                        
                        
                        break;
                    case 91:
                        ventanamixer.b2.play(""); 
                       ventanamixer.controles1.getPlayer().addBasicPlayerListener(ventanamixer.escucha2);
                        ventanamixer.timerBandeja2.barraDeProgreso.setMaximum(ventanamixer.escucha2.taman());
                        ventanamixer.timerBandeja2.slider.setMaximum(ventanamixer.escucha2.taman());
          
                        slider.setValue(10);
                        break;

                    case 92:
                        slider.setValue(20);
                        break;
                    case 93:
                        slider.setValue(30);
                        break;
                    case 94:
                        slider.setValue(40);
                        break;
                    case 95:
                        slider.setValue(50);
                        break;
                    case 96:
                        slider.setValue(60);
                        break;
                    case 97:
                        slider.setValue(70);
                        break;
                    case 98:
                        slider.setValue(80);
                        break;
                    case 99:
                        slider.setValue(90);
                        break;
                    case 100:
                        slider.setValue(100);
                       
                        timer.stop();
                      
                        
                        break;

                }
                break;
            case 2:
                switch (num) {
                  /*  case 90:
                        switch (func) {
                            case 0:
                                con = (int) (Math.random() * jTable1.getRowCount());
                                posicion = con;
                                System.out.println("Cambio2");
                                play1d();
                                break;
                            case 1:
                                posicion++;
                                con = posicion;
                                play1d();
                                break;
                            case 2:

                                siguiente1();
                                play1d();

                                break;
                        }

                        break;*/
                    
                    case 90:
                        ventanamixer.b1.setPosRandom();
                       
        {
            try {
                ventanamixer.timerBandeja.nombre.setText(ventanamixer.b1.devolverNombre(""));
            } catch (Exception ex) {
                Logger.getLogger(ProgresoBandeja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                        
                        
                       
                      //  ventanamixer.b1.getPos();
                        

                    case 91:
                        slider.setValue(90);
                        ventanamixer.b1.play("");
                        ventanamixer.controles.getPlayer().addBasicPlayerListener(ventanamixer.escucha1);
                         ventanamixer.timerBandeja.barraDeProgreso.setMaximum(ventanamixer.escucha1.taman());
                        ventanamixer.timerBandeja.slider.setMaximum(ventanamixer.escucha1.taman());
                        break;

                    case 92:
                        slider.setValue(80);
                        break;
                    case 93:
                        slider.setValue(70);
                        break;
                    case 94:
                        slider.setValue(60);
                        break;
                    case 95:
                        slider.setValue(50);
                        break;
                    case 96:
                        slider.setValue(40);
                        break;
                    case 97:
                        slider.setValue(30);
                        break;
                    case 98:
                        slider.setValue(20);
                        break;
                    case 99:
                        slider.setValue(10);
                        break;

                    case 100:
                         slider.setValue(0);
                        timer.stop();
                        //cBand2.stop();
                     /* barraDeProgreso.setMaximum(ventanamixer.escucha1.taman());
                        slider.setMaximum(ventanamixer.escucha1.taman());*/
                        break;

                }
                break;
        }
    }
        public void setAutomixer(boolean value)
        {
        automixer=value;
        }
    
}
