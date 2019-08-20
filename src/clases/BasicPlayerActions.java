/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 *
 * @author gonzalo
 */
public class BasicPlayerActions {
    private BasicPlayer basicPlayer;
    private PlayerListener playerListener;
    public BasicPlayerActions(){
       
        basicPlayer=new BasicPlayer();
        
    }

   

   
    
    public void play(String path){
        try {
            File file=new File(path);
            basicPlayer.open(file);
            
        } catch (BasicPlayerException ex) {
            Logger.getLogger(BasicPlayerActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            basicPlayer.play();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(BasicPlayerActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean isPlaying(){
        return basicPlayer.getStatus()==0;
    }
    public void pause(){
        try {
            basicPlayer.pause();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(BasicPlayerActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stop(){
        try {
            basicPlayer.stop();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(BasicPlayerActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void resume(){
        try {
            basicPlayer.resume();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(BasicPlayerActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BasicPlayer getBasicPlayer(){
        return basicPlayer;
    }
    
     public boolean isPause(){
        return basicPlayer.getStatus()==1;
    }
     public boolean isStopped(){
         return basicPlayer.getStatus()==2;
     }
     public boolean isUnknow(){
         return basicPlayer.getStatus()==-1;
     }
     
    
     public void setPlayerListener(PlayerListener playerListener){
         this.playerListener=playerListener;
         basicPlayer.addBasicPlayerListener(playerListener);
     }
     
    public PlayerListener getPlayerListener(){
        return this.playerListener;
    }
     public void updatePlayerListener(JProgressBar progressBar){
     this.playerListener.setProgressBar(progressBar);
     }
}
