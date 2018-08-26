/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import acciones.Control;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import org.json.JSONException;

/**
 *
 * @author familia
 */
public class Bandeja {
    private Control controles;
    private int estado;
    private int position=-1;
    private int sizeFile;
    public Bandeja(Control controles)
    {
    this.controles=controles;
    }
    public void play(String txt)
    {
        if(position==-1)
            position=0;
        try {
          
                
                if(estado==1)    
                {
                    resume();
                }
                else
                {
                 controles.playSong(controles.getPlayer(),position, txt);
                 estado=1;
                }
               
               
            
        } catch (BasicPlayerException ex) {
            Logger.getLogger(Bandeja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void stop()
    {estado=0;
        try{
        try {   
            controles.Stop(controles.getPlayer());
        } catch (BasicPlayerException ex) {
            Logger.getLogger(Bandeja.class.getName()).log(Level.SEVERE, null, ex);
        }
         }catch(Exception ex)
        {
        
        }
    }
    public void pausa(int tipo)
    {
         try{
      
             try {
                 controles.Pausa(controles.getPlayer());
             } catch (BasicPlayerException ex) {
                 Logger.getLogger(Bandeja.class.getName()).log(Level.SEVERE, null, ex);
             }
           
       
       
        }catch(Exception ex)
        {
        
        }
    }
    public void resume()
    {
        try {
             
            try {
                controles.Continuar(controles.getPlayer());
            } catch (BasicPlayerException ex) {
                Logger.getLogger(Bandeja.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        } catch (Exception ex) {
            Logger.getLogger(Bandeja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int anterior(int pos)
    {
         pos--;
    if(pos<0)
            pos=controles.devolverJson().getObjetos().size()-1;
       position=pos;
    return pos;
    }
    public int siguiente(int pos)
    {
       pos++;
     if(pos<0||pos>=controles.devolverJson().getObjetos().size())
            pos=0;
       position=pos;
    return pos;
    }
    public int getPos()
    {
    return position;
    }
    public  String devolverNombre(String txt)throws Exception
    {
        return controles.devolverJson().getCanciones(txt).get(position).getString("cancion");
    }
    public long getSizeFile(String txt)throws JSONException
    {
    return controles.devolverJson().getCanciones(txt).get(position).getLong("size");
    }
    public void setPosRandom()
    {
    position=(int) (Math.random()*controles.devolverJson().getObjetos().size());
    }
    public void setPosition(int pos)
    {
    position=pos;
    }
    public int getEstado()
    {
    return estado;
    }
    
    
}
