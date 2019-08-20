/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import clases.Cancion;
import de.craften.ui.swingmaterial.MaterialColor;
import acciones.cargarDatosPanel;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author familia
 */
public class Buscador2 {
    private JFileChooser buscar;
        private FileFilter filtro[];
    //private Cancion cancion;
    
    public Buscador2(){
    buscar=new JFileChooser();
    
    }
    
      public void buscar(cargarDatosPanel cargarDatosPanel) throws Exception
    {
    filtro=new FileFilter[4];//genero los filtros para poder seleccionar el tipo de musica
      filtro[0] = new FileNameExtensionFilter("Archivos MP3(.mp3)", "MP3");
      filtro[1] = new FileNameExtensionFilter("Archivos wav(.wav)", "WAV");
      filtro[3] = new FileNameExtensionFilter("Archivos MP3 WAV ogg", "MP3", "WAV", "OGG");
      filtro[2] = new FileNameExtensionFilter("Archivos OGG(.ogg)", "OGG");
      buscar.setFileFilter(filtro[0]);
      buscar.setFileFilter(filtro[1]);
      buscar.setFileFilter(filtro[2]);
      buscar.setFileFilter(filtro[3]);
      buscar.setAcceptAllFileFilterUsed(false);
      buscar.setFileHidingEnabled(true);
      buscar.setMultiSelectionEnabled(true);
      buscar.setFileSelectionMode(0);
      
      
      
      if(buscar.showOpenDialog(buscar)==JFileChooser.APPROVE_OPTION)
        {
           File file[] =buscar.getSelectedFiles();
            int n=0;
            ArrayList<Cancion> ca=new ArrayList<>();
           for (File file1:file) //ciclo for each de esta forma hago un recorrido por todos los archivos
            {
              
              Cancion  cancion=new Cancion(n, file1.getName(),file1.getAbsolutePath());
                System.out.println(cancion.getId()+":"+cancion.getNombre());
             //bd.agregar(cancion);
             ca.add(cancion);
                n++;
            }
           cargarDatosPanel.cargarDatosJpanel("",ca,1);
       
           // model=bd.DevolverListado("asc","");
          
        }
         else {
            buscar.cancelSelection();
            buscar.resetChoosableFileFilters();
        }
     
    }
    
}
