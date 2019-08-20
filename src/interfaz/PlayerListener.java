/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.util.Map;
import javax.swing.JProgressBar;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

/**
 *
 * @author gonzalo
 */
public class PlayerListener implements BasicPlayerListener{
    private JProgressBar progressBar;
    private static int size;
    public float[] equalizer;
    public float[] eq = new float[32];
    public PlayerListener(JProgressBar progressBar){
    this.progressBar=progressBar;
    }

    @Override
    public void opened(Object arg0, Map arg1) {
             if (arg1.containsKey("audio.length.bytes")) {    
        double bytesLength = Double.parseDouble(arg1.get("audio.length.bytes").toString());
        
        size=(int) bytesLength;
        System.out.println("mapaa1"+ size);
        progressBar.setMaximum((int)bytesLength);
        
    }
    }
    
    public void setProgressBar(JProgressBar progresBar){
        this.progressBar=progresBar;
        this.progressBar.setMaximum(size);
        
    }
   

    @Override
    public void progress(int arg0, long arg1, byte[] arg2, Map arg3) {
        try{
            float progressUpdate = (float) (arg0 * 1.0f/ arg1 * 1.0f);
            int progressNow = (int) (arg1 * progressUpdate);
            progressBar.setValue(progressNow);   
            int seconds,minute=0;
            if(arg3.get("mp3.position.microseconds")!=null)
        {
            seconds=Integer.parseInt(arg3.get("mp3.position.microseconds")+"")/1000000;
          
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
             equalizer=(float[])arg3.get("mp3.equalizer");
             System.arraycopy(eq, 0, equalizer, 0, equalizer.length);
        }
          }catch(Exception ex){
            System.out.println(""+ex.getMessage());
        }
    }

    @Override
    public void stateUpdated(BasicPlayerEvent arg0) {
    }

    @Override
    public void setController(BasicController arg0) {
        
    }
    public void setEq(int n,int value)
    {
    eq[n]=(float)value/100;
    }
   
    
}
