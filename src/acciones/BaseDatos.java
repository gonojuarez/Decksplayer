/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

/**
 *
 * @author familia
 */
import clases.Cancion;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
public class BaseDatos {
  private int j=0;
    public BaseDatos()
    {
    Connection conect=null;
    try
    {
        conect=DriverManager.getConnection("jdbc:sqlite:music.db");
        Statement state=conect.createStatement();
        state.setQueryTimeout(3);
        state.executeUpdate("create table if not exists musica(id integer,nombre string,taman long,direc string)");
  
    }catch(Exception ex)
    {
        System.out.print(ex.getMessage());
    }
     finally
        {
          try
          {
            if(conect!= null)
              conect.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e);
          }
        }
    }
    public void agregar(Cancion c)throws Exception
    {
    Connection conect=null;
    conect=DriverManager.getConnection("jdbc:sqlite:music.db");
        Statement state=conect.createStatement();
     
        state.executeUpdate("insert into musica values("+c.getId()+",'"+c.getNombre()+"',"+c.getTamaño()+",'"+c.getDireccion()+"')");
    
       
        conect.close();
    }
    public  Cancion devolvercancion(long i)throws Exception
    {
       Cancion c1 = null;
     Connection conect = null;
            conect = DriverManager.getConnection("jdbc:sqlite:music.db");
          Statement state = conect.createStatement();
          ResultSet rs=state.executeQuery("select * from musica order by nombre asc");
        int c=0;
          while(rs.next()){
        if(c==i)
             c1=new Cancion(rs.getInt("id"),rs.getString("nombre"),rs.getLong("taman"),rs.getString("direc"));
       c++;

          }
          
         rs.close();
          conect.close();
          
    return c1;
    }
    public int tam()throws  Exception
    {
     Connection conect = null;
            conect = DriverManager.getConnection("jdbc:sqlite:music.db");
          Statement statement = conect.createStatement();
          ResultSet rs=statement.executeQuery("select id from musica");
        int i=0;
        while(rs.next()){
       i=rs.getInt("id");
        }
        rs.close();
        statement.close();
      conect.close();
          return i;
    }
 public DefaultTableModel DevolverListado(String tipo)throws Exception
 {
    DefaultTableModel model = new DefaultTableModel() {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    model.addColumn("Nombre");
   
       Cancion c1 = null;
     Connection conect = null;
            conect = DriverManager.getConnection("jdbc:sqlite:music.db");
          Statement state = conect.createStatement();
          ResultSet rs=state.executeQuery("select * from musica order by nombre "+tipo);
         while(rs.next()){
        
             c1=new Cancion(rs.getInt("id"),rs.getString("nombre"),rs.getLong("taman"),rs.getString("direc"));
          String nombre[]={c1.getNombre().replace(".mp3","").replace(".ogg","").replace(".wav", "").replace("{{", "'")};
             model.addRow(nombre);
          }
          
         rs.close();
          conect.close();
 return model;
 }
 public void eliminar(long i)throws Exception
 {
 
        Connection connection = null;
            connection = DriverManager.getConnection("jdbc:sqlite:music.db");
          Statement statement = connection.createStatement();
            ResultSet rs=statement.executeQuery("select * from musica order by nombre asc");
        int c=0;
          while(rs.next()){
        if(c==i){
         Cancion  c1=new Cancion(rs.getInt("id"),rs.getString("nombre"),rs.getLong("taman"),rs.getString("direc"));
          statement.executeUpdate("delete from musica where id="+c1.getId());
        }
       c++;

          }
          
          connection.close();
 }
}
