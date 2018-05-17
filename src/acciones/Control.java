/*
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import BaseDatos.Json;
//import beatit.Main;
import clases.*;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javazoom.jl.converter.*;
import javazoom.jl.decoder.*;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
//import beatit.*;
import org.json.JSONException;
import org.json.JSONObject;
/*
 *
 * @author joaco
 */
public class Control{

    private JFileChooser buscar;
    private javax.swing.filechooser.FileFilter filtro[];
    private File file[];
    private File fw[];
    private Timer tiempo;
    private TimerTask task;
    private ArrayList lista;
    private Cancion cancion;
    private DefaultTableModel model;
    private Decoder dec;
    private Converter conv;
    private BasicPlayer player;
    private BasicController controll;
    private String display="";
    private int tamaño=0;
    private File indice[];
    private RandomAccessFile flow;
    private int sx = 0;
    public long time = 0;
    public String can[];
public TreeSet trw;
BasicController control,control1;
public int tam=0,progresos;
int n=15;
 private Json json;
 

//beatit.Main mn;
    public Control(Json json) throws Exception {
        init();
        this.json=json;
    }

    public void init() {

        player = new BasicPlayer();
       
        
        
       
    
    }

  

   

 
    public void playSong(int i,String txt) throws BasicPlayerException {
       ArrayList<JSONObject> canciones=json.getCanciones(txt);
                  player.stop();
       
        try {
            player.open(new File(canciones.get(i).getString("direccion")));
        } catch (JSONException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
       
                player.play();
              
        
            
        
    }
    public void eff(int n)throws BasicPlayerException
    {
    try{
     BasicPlayer bl=new BasicPlayer();
     switch(n)
     {
         case 0:
     bl.open(getClass().getResource("/sound/siren1.wav"));
     break;
         case 1:
              bl.open(getClass().getResource("/sound/yelp3.wav"));
             break;
       case 2:
              bl.open(getClass().getResource("/sound/tone12.wav"));
            
             break;
         case 3:
              bl.open(getClass().getResource("/sound/alarm.wav"));
            
             break; 
               case 4:
              bl.open(getClass().getResource("/sound/DINGDONG.mp3"));
            
             break;     
     }
    bl.play();
    if(bl.getLineBufferSize()==-1)
    {
    bl.play();
    }
    }catch(Exception ex)
    {}
   
    }

    public void eliminar(int n) {
        model.getDataVector().removeElementAt(n);
    }

   

    public void setVolumen(float a) throws BasicPlayerException {
        try {
            player.setGain(a);

        } catch (BasicPlayerException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BasicPlayer getPlayer() {
        return player;
    }

    public int play() throws BasicPlayerException {
        int n = 0;

        if (player.getLineCurrentBufferSize() != 0) {
            n = 1;
            player.play();

        } else {
            n = 0;
            player.stop();
        }
        return n;

    }

    public void Pausa() throws Exception {
        player.pause();
    }

    public void Continuar() throws Exception {
        player.resume();
    }

    public void Stop() throws Exception {

        player.stop();
        
    }

   

  
    

    
   

    public int size() {
        return player.getLineBufferSize();
    }

    public void setPan(double x) {
        try {
            player.setPan(x);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
   

    public int size(int n) {
        Cancion c = (Cancion) lista.get(n);
        int r = (int) c.getTamaño();
        return r;
    }

    public void adelantar(int n) {
        try {
            player.seek(n);
        } catch (Exception ex) {

        }
    }

   

    public void pitch(int n) {
        player.setSleepTime(n);
        
    }

   
 
   
   public void loop1() 
   {
   /* for(int i=0;i<player.getMixers().toArray().length;i++)
            {
              //  System.out.println(player.getMixers().toArray()[i]);
            }
      */
   }
   public double calcularBpm(int id,String txt) throws Exception
   {
       //mn=new Main();
   double n1=0;
//   n1=mn.cargarCancion(bd.devolvercancion(id,txt).getDireccion().replace(bd.devolvercancion(id,txt).getNombre(), bd.devolvercancion(id,txt).getNombre().replace("{{","'")));
   return 0;
   }
   public Json devolverJson()
   {
   return json;
   }


    
}
 