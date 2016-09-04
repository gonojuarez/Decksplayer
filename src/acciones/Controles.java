/*
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import clases.*;
import com.sun.prism.impl.PrismSettings;
import decksplayer.ventanamixer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.DirectoryChooser;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.filechooser.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javazoom.jl.converter.*;
import javazoom.jl.decoder.*;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerEventLauncher;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import javazoom.spi.vorbis.sampled.file.*;
import sun.font.EAttribute;
import java.lang.Object;

/*
 *
 * @author joaco
 */
public class Controles {

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
    private BasicPlayer playtu,playerc;
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
BaseDatos bd;
    public Controles() throws Exception {
        init();
bd=new BaseDatos();
    }

    public void init() {

        player = new BasicPlayer();
        playtu = new BasicPlayer();
        
        
       
    
    }

   

   

    public String getname(int numero) {
        String name = "";
        try {
             name=bd.devolvercancion(numero).getNombre().replaceAll("<","'");
        } catch (Exception e) {
        }
     
        return name;
    }

    public void playSong(int i) throws BasicPlayerException {
       
        try {
            player.open(new File(bd.devolvercancion(i).getDireccion().replace(bd.devolvercancion(i).getNombre(), bd.devolvercancion(i).getNombre().replace("<","'"))));
        } catch (Exception ex) {
            Logger.getLogger(Controles.class.getName()).log(Level.SEVERE, null, ex);
        }

                player.play();
              
        
            
        
    }

    public void eliminar(int n) {
        model.getDataVector().removeElementAt(n);
    }

    public void playsSong(int id) throws BasicPlayerException {
    
          
                try {
                    playtu.open(new File(bd.devolvercancion(id).getDireccion().replace(bd.devolvercancion(id).getNombre(),bd.devolvercancion(id).getNombre().replaceAll("<","'"))));
                } catch (Exception ex) {
                    Logger.getLogger(Controles.class.getName()).log(Level.SEVERE, null, ex);
                }
                playtu.play();
                
            

        
    }

    public void setVolumen(float a) throws BasicPlayerException {
        try {
            player.setGain(a);

        } catch (BasicPlayerException ex) {
            Logger.getLogger(Controles.class.getName()).log(Level.SEVERE, null, ex);
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

    public void setVolumen1(float a) throws BasicPlayerException {
        try {
            playtu.setGain(a);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(Controles.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public BasicPlayer getPlayer1() {
        return playtu;
    }
     public BasicPlayer getPlayer2() {
        return playerc;
    }

    public int Play1() throws Exception {
        int n = 0;

        if (playtu.getLineCurrentBufferSize() != 0) {
            n = 1;
            playtu.play();

        } else {
            n = 0;
            playtu.stop();
        }
        return n;
    }

    public void Pausa1() throws Exception {
        playtu.pause();
    }

    public void Continuar1() throws Exception {
        playtu.resume();

    }

    public void Stop1() throws Exception {

        playtu.stop();

    }

    public int size() {
        return player.getLineBufferSize();
    }

    public void setPan(double x) {
        try {
            player.setPan(x);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(Controles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPan1(double x) {
        try {
            playtu.setPan(x);
            
        } catch (Exception ex) {
        }
    }

    public void siguientes() {

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

    public void adelantar1(int n) {
        try {
            playtu.seek(n);
            
        } catch (Exception ex) {

        }
    }

    public void pitch(int n) {
        player.setSleepTime(n);
        
    }

    public void pitch1(int n) {
        playtu.setSleepTime(n);
        
    }

   
   
 
    
}
 