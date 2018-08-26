/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package decksplayer;

import BaseDatos.Json;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import decksplayer.ventanamixer;
/**
 *
 * @author Gonzalo
 */
public class Principal {
   
    public   static ventanamixer v2 ;
    public   static  Servidor1 sertv;
    private static Json json;
    public static void main(String[]args)  {
    
        JFrame.setDefaultLookAndFeelDecorated(true);
  
      int num=0;
      json=new Json();
 
        try {
            v2=new ventanamixer(json);
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
           v2.setVisible(true);
    
       
   //  sertv=new Servidor1(); 
     
    }
   
    
    
       
       
       

    
}
