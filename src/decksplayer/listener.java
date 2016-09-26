/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decksplayer;

import java.util.Map;
import javax.swing.JProgressBar;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

/**
 *
 * @author familia
 */
public class listener implements BasicPlayerListener{
int tam=0;
int progreso=0;
public float[] equalizer;
public float[] eq = new float[32];

public int estado;

    public listener() {
    }


    @Override
    public void opened(Object o, Map map) {
        if (map.containsKey("audio.length.bytes")) {
            
     double bytesLength = Double.parseDouble(map.get("audio.length.bytes").toString());
   
     tam=(int)bytesLength;
    
 }
        
    }
    /*utilizo esta parte para captar los audios y poder realizar las 
      distintas funciones como el progreso del audio
      y los ecualizadores  
    */
    @Override
    public void progress(int i, long l, byte[] bytes, Map map) {
       try{
        
        float progressUpdate = (float) (i * 1.0f/ l * 1.0f);
        int progressNow = (int) (l * progressUpdate);
        progreso=progressNow;
        equalizer=(float[])map.get("mp3.equalizer");
        System.arraycopy(eq, 0, equalizer, 0, equalizer.length);
        System.out.print(map.toString());
          // System.out.println(progressNow);
       }catch(Exception ex){}
    }

    @Override
    public void stateUpdated(BasicPlayerEvent bpe) {
     try{
       System.out.print(bpe.getPosition());
      
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
}
