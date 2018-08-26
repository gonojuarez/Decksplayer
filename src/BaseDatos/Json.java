/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import clases.Cancion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author familia
 */
public class Json {

private    JSONObject objeto;
private ArrayList<JSONObject> objetos;
private JSONArray arreglos;
    public Json() {
        objetos=new ArrayList<>();
        try {
            if(listado().length()>0)
            for(int i=0;i<listado().length();i++)
            {
                objetos.add(listado().getJSONObject(i));
            }
        } catch (JSONException ex) {
            Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SetJson( ArrayList<Cancion> ca) {
        objeto = new JSONObject();
        try {
          
           
         
            for (int i = 0; i < ca.size(); i++) {
             
                objeto = new JSONObject();
                objeto.put("id", objetos.size());
                objeto.put("cancion", ca.get(i).getNombre());
                objeto.put("direccion", ca.get(i).getDireccion());
                objeto.put("size", ca.get(i).getTamaño());
                objeto.put("siguiente",0);
                objeto.put("Cantidad de Reproducciones", 0);
                objetos.add(objeto);
            }
            guardarJson();
            
          
        } catch (Exception ex) {
            Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void guardarJson()
    {
        JSONArray arreglo=new JSONArray();
        for(int i=0;i<objetos.size();i++)
        {
            try {
                objetos.get(i).put("id",i);
                arreglo.put(i, objetos.get(i));
            } catch (JSONException ex) {
                Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        escribirArchivo(arreglo);
       
    }

   
    public JSONArray obj() throws Exception {
       
        return listado();

    }

   private JSONArray listado() throws JSONException
    { String cadena, txt = "";
    
        FileReader f = null;
        try {
            File file=new File("musica.db");
           if(file.exists()){
               
           
            BufferedReader b = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
          
            
            while ((cadena = b.readLine()) != null) {
                txt += cadena;
            }
           }else
           {
               escribirArchivo(null);
           
           }
        } catch (Exception ex) {
            Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);

        }
        if(txt.length()==0)
        {
        txt="[]";
        }
     return new JSONArray(txt);
    }
  

    public JSONArray getArreglos() {
        return arreglos;
    }

    public void setArreglos(JSONArray arreglos) {
        this.arreglos = arreglos;
    }

    public JSONObject getObjeto() {
        return objeto;
    }

    public void setObjeto(JSONObject objeto) {
        this.objeto = objeto;
    }

    public ArrayList<JSONObject> getObjetos() {
        return objetos;
    }

    public void setObjetos(ArrayList<JSONObject> objetos) {
        this.objetos = objetos;
    }
    public DefaultTableModel tableModel(String texto)throws JSONException
    {
    DefaultTableModel model = new DefaultTableModel() {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
     model.addColumn("Nombre");
    ArrayList<JSONObject> listado=getCanciones(texto);
    
   if(texto.length()==0&&objetos.size()>0){
    listado=objetos;
    }
    for (int i = 0; i < listado.size(); i++) {
                String nombre[] = {listado.get(i).getString("cancion")};
                model.addRow(nombre);
            }
    
    
      return model;
    }
    public ArrayList<JSONObject> getCanciones(String texto)
    {
    ArrayList<JSONObject> canciones=new ArrayList<>();
    int tam=objetos.size()/10;
    int cont=0;
 
        do{
         int in=cont*10;
         int fin=cont*10+10;
         if(fin>=objetos.size())
         {
         fin=objetos.size();
         }
        filtrar(canciones, in,fin , texto);
      
        cont++;
        }while(cont<=tam);
    
    
    return canciones;
    }
   
    private void filtrar(ArrayList<JSONObject> object,int in,int fin,String text)
    {
        for(int i=in;i<fin;i++)
        {
        try
        {
        if(remplazar(objetos.get(i).getString("cancion")).toLowerCase().contains(text))
        {
            object.add(objetos.get(i));
            System.out.println("cancion :"+objetos.get(i).getString("cancion"));
        }
        }catch(Exception ex)
        {
        
        }
        }
    }
    
    private String remplazar(String texto)
    {
        texto=texto.replace("á","a").replace("à","a").replace("ä","a")
                .replace("é","e").replace("è","e").replace("ë","e")
                .replace("í","i").replace("ì","i").replace("ï","i")
                .replace("ó","o").replace("ò","o").replace("ö","o")
                .replace("ù", "u").replace("ú","u");
    return texto;
    }
  
   private void escribirArchivo(JSONArray array)
   {
        File f = new File("musica.db");
          
        try {
            FileOutputStream fos=new FileOutputStream(f);
            Writer writer=new OutputStreamWriter(fos,"utf-8");

            BufferedWriter escritor = new BufferedWriter(writer);
            if(array!=null){
                System.out.println(array.toString());
                writer.write(array.toString());
            }
            writer.flush();
            writer.close();
            escritor.flush();
            escritor.close();
        } catch (Exception ex) {
            Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
   }
   public void guardarSiguiente(int n,int next)
   {
    try {
        objetos.get(n).put("siguiente", next);               
    } catch (JSONException ex) {
        Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
    
                               
   }  
    guardarJson();  
   }        
   
   
   
   }
