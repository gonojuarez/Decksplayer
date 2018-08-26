/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import com.sun.org.apache.xalan.internal.xsltc.cmdline.getopt.GetOpt;
import decksplayer.ventanamixer;
import java.awt.Label;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

/**
 *
 * @author familia
 */
public class Listener implements BasicPlayerListener{
int tam=0;
int progreso=0;
public float[] equalizer;
public float[] eq = new float[32];

public int estado;
public int bitrate;
public int chanels;
public int sampleBits;
public Float sampleFrame;
public JLabel textfield;
int seconds=0;

    public Listener(JLabel label) {
        textfield=label;
    }


    @Override
    public void opened(Object o, Map map) {
        if (map.containsKey("audio.length.bytes")) {
            
     double bytesLength = Double.parseDouble(map.get("audio.length.bytes").toString());
     chanels=Integer.parseInt(map.get("audio.channels").toString());
     System.out.println("mapaa1"+map.toString());
   
           /* sampleFrame= Float.parseFloat(map.get("audio.framerate.fps")+"");
            System.out.println("mapaa1"+map.toString());
            bitrate=(Integer.parseInt(map.get("bitrate")+""))/1000;
            System.out.println("total tiempo "+((bitrate/sampleFrame)*3600));*/
        
 
            
            
   
     tam=(int)bytesLength;
    
 }
        
    }
    /*utilizo esta parte para captar los audios y poder realizar las 
      distintas funciones como el progreso del audio
      y los ecualizadores  
    */
    
    @Override
    public void progress(int i, long l, byte[] bytes, Map map)  {
       try{
        float progressUpdate = (float) (i * 1.0f/ l * 1.0f);
        int progressNow = (int) (l * progressUpdate);
        progreso=progressNow;
        System.out.println("mapaa2"+map.toString());
        if(map.get("mp3.position.microseconds")!=null)
        {
            seconds=Integer.parseInt(map.get("mp3.position.microseconds")+"")/1000000;
            int minute=0;
            int hour=0;
            if(seconds>60)
            {
                minute=seconds/60;
                seconds=seconds-(minute*60);
                if(minute>60)
                {
                    hour=minute/60;
                }
            }
            System.out.println("totalPr "+minute+":"+seconds);
            textfield.setText(hour+":"+minute+":"+seconds);
            
        }
        equalizer=(float[])map.get("mp3.equalizer");
        System.arraycopy(eq, 0, equalizer, 0, equalizer.length);
       }catch(Exception ex)
       {
           System.out.println(""+ex.getMessage());
       }
    }

    @Override
    public void stateUpdated(BasicPlayerEvent bpe) {
     try{
      // System.out.print(bpe.getPosition());
      
     }catch(Exception ex){
     }
    }

    @Override
    public void setController(BasicController bc) {
       
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public int taman()
  {
  return tam;
  }
public int progresos()
{
 
return progreso;
}
    public void setearValor(JProgressBar jps)
    {
    jps.setValue(progreso);
       
    
    }
    public void setEq(int n,int value)
    {
    eq[n]=(float)value/100;
    }
    public Object tipo()
    {
    
      return  null;
    }
    public int getSeconds()
    {
    return seconds;
    }
}
