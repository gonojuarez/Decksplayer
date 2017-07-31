/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package decksplayer;

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
public class main {
   
       static ventanamixer v2 ;
    public static void main(String[]args) throws Exception{
        JFrame.setDefaultLookAndFeelDecorated(true);
  
      int num=0;
     
                v2=new ventanamixer();
           v2.setVisible(true);
    
    }

    
    
       
       
       

    
}
