/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import BaseDatos.Json;
import clases.Cancion;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author familia
 */
public class Buscador {
    private FileFilter filtro[];
   // private File file[];
    private JFileChooser buscar;
    private Cancion cancion;
    private BaseDatos bd;
    private DefaultTableModel model;
    
     private Json json;
    public Buscador(Json json) {
        buscar=new JFileChooser();
   
        bd=new BaseDatos();
        try{
        model=json.tableModel("");
        }catch(Exception ex){}
        this.json=json;
    }
   
    public void buscar() throws Exception
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
            int n=model.getRowCount();
            ArrayList<Cancion> ca=new ArrayList<>();
           for (File file1:file) //ciclo for each de esta forma hago un recorrido por todos los archivos
            {
               
                cancion=new Cancion(n, file1.getName(), file1.getTotalSpace(),file1.getAbsolutePath());
                System.out.println(cancion.getId()+":"+cancion.getNombre());
             //bd.agregar(cancion);
             ca.add(cancion);
                n++;
            }
             json.SetJson(ca);
            model=json.tableModel("");
           // model=bd.DevolverListado("asc","");
          
        }
         else {
            buscar.cancelSelection();
            buscar.resetChoosableFileFilters();
        }
     
    }
    public DefaultTableModel devolverModelo(String tr)throws  Exception
    {
        /*
         model=bd.DevolverListado("asc",tr);*/
        
        model=json.tableModel(tr);
    return model;
    }
    public DefaultTableModel devolverCancion()
    {
        
    return model;
    }
       public void eliminar(int n) {
        
      try{
        bd.eliminar(n);
        model.getDataVector().removeElementAt(n);
    }catch(Exception ex){}
       }
     public ArrayList<JSONObject> objetos()
    {
     return json.getObjetos();
   }
     public JFileChooser chooser()
     {
         return buscar;
     }
}
