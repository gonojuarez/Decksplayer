/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import clases.Cancion;
import de.craften.ui.swingmaterial.MaterialColor;
import de.craften.ui.swingmaterial.MaterialProgressSpinner;
import clases.DibujarBordes;
import interfaz.song;
import interfaz.songChica;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.TransferHandler;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author gonzalo
 */
public class cargarDatosPanel {
    private JPanel panel;
    //private ArrayList<song> canciones;
    private ArrayList<Cancion> listadoCanciones;
    private Component component;
    private JScrollBar scrollbar;
    
    public cargarDatosPanel(JPanel panel,JScrollBar scrollBar){
        this.panel=panel;
        this.panel.setBorder(new DibujarBordes().borderContentPanel());
        this.panel.setDropTarget(target());
        this.component=component;
        this.scrollbar=scrollBar;
       
    }
    public void reiniciar(String text){
      if(panel!=null){  
          cargarDatosJpanel(text, listadoCanciones, 1);
      }
    }
    
    public void restablecer(){
        panel.removeAll();
    }

   
   
        
               private int calcularCantLibenX(int width) {
        return (int) Math.round(panel.getWidth() / width);
    }

    private int calcularCantLibenY(int height) {
        return (int) Math.round(panel.getHeight() / height);
       
    }
    private void agregarCanciones(song canciones){
        canciones.getMaterialPane().setBorder(new DibujarBordes().bordeBandeja());
              // canciones.addMouseListener(mia);
             panel.add(canciones);
    }
    private void agregarCanciones(songChica canciones){
             panel.add(canciones);
    }
    

  
    
      public void cargarDatosJpanel(String text,ArrayList<Cancion> listado,int opcion){
         panel.removeAll();
          if(listadoCanciones==null||listadoCanciones.isEmpty()){
          listadoCanciones=new ArrayList<>();
          }
         
         
       ArrayList<Cancion> cancionesFiltrados=(ArrayList<Cancion>)listadoCanciones.stream().filter(p->p.getNombre().toLowerCase().contains(text)).collect(Collectors.toList());
        int cantX=0,cantY=0, cantMax=0;
            switch(opcion){
                case  0:
                     cantX=calcularCantLibenX(120);
                    cantY=calcularCantLibenY(120);
                    cantMax=cantX*cantY;
                            getCantidadMaxima(120,120);

                    break;
                case 1:
                     cantX=calcularCantLibenX(panel.getWidth()/2);
                     cantY=calcularCantLibenY(51);
                     cantMax=cantX*cantY;
                             getCantidadMaxima(51,panel.getWidth()/2);

                    break;
            }
           
        for (int i = 0; i <cantX ;i++) {
            for (int j = 0; j <cantY; j++) {
                try{
                  int pos=(scrollbar.getValue()*cantMax)+(j*cantX)+i;
                  switch(opcion){
                      case 0:
                         song canc=new song();
                        canc.setTitle(cancionesFiltrados.get(pos).getNombre());
                        canc.setSize(120, 120);
                        canc.setLocation(((i * 120)), (j * 120));
                        canc.setId(pos+"");
                        
                         agregarCanciones(canc);
                    break;
                      case 1:
                          
                           songChica cancion=new songChica(cancionesFiltrados.get(pos));   
                            cancion.setTitle("  "+(pos+1)+"  "+ cancionesFiltrados.get(pos).getNombre());
                            cancion.setSize(panel.getWidth()/2,51);
                            cancion.getLabel().setSize(cancion.getWidth()/2, cancion.getHeight()/2);
                            cancion.getjProgressBar1().setSize(cancion.getWidth()/2, cancion.getHeight()/2);
                            cancion.setLocation(i*(panel.getWidth()/2), (j*51));
                            agregarCanciones(cancion);

                          break;
                  }
                }catch(Exception ex){
                    
                
            }
            
        }
       
    }
         panel.updateUI();
      }
      
      public DropTarget target(){
      return new DropTarget(){
          @Override
          public synchronized void drop(DropTargetDropEvent dtde) {
              dtde.acceptDrop(DnDConstants.ACTION_COPY);
              try {
                  
                  List<File> droppedFiles = (List<File>)
                          dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                  
                  int pos=listadoCanciones.size();
                
                     for (File file : droppedFiles) {
                        // process files
                        if(!file.isDirectory()&&(file.getName().toLowerCase().endsWith(".mp3")||file.getName().toLowerCase().endsWith(".ogg")||file.getName().toLowerCase().endsWith(".wav"))){
                       
                            Cancion   cancion=new Cancion(pos, file.getName(),file.length(),file.getAbsolutePath());
                            pos++;
                            listadoCanciones.add(cancion);
                            
                        }
                      
                     }
                    
//                    droppedFiles.clear();
                     cargarDatosJpanel("",listadoCanciones,1);
                     
              } catch (UnsupportedFlavorException ex) {
                  Logger.getLogger(cargarDatosPanel.class.getName()).log(Level.SEVERE, null, ex);
              } catch (IOException ex) {
                  Logger.getLogger(cargarDatosPanel.class.getName()).log(Level.SEVERE, null, ex);
              }
         
          }
      
      };
      }
      
      public  int getCantidadMaxima(int height,int with){
        int cantMax= listadoCanciones.size()/(calcularCantLibenX(with)*calcularCantLibenY(height));
       scrollbar.setMaximum(cantMax);
           return cantMax;
      }
       
      

  
    
}
