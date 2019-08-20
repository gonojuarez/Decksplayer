/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import decksplayer.Servidor1;
import decksplayer.bandeja;
import javax.swing.JButton;

/**
 *
 * @author gonzalo
 */
public class ControlServidor {
    private boolean estado=false;
    private  Thread hiloServidor;
    
    public ControlServidor(bandeja ventana){
        hiloServidor=new Thread(new Runnable() {
               @Override
               public void run() {
                   Servidor1 sv1=new Servidor1(ventana);
                 
               }
           });
    }
    public void setTexto(JButton boton){
        boton.setText(devolverEstado());
    }
    private String devolverEstado(){
        estado=!estado;
        activarDesactivarServidor();
          if(estado){
              return "Activado";
          }
          return "Desactivado";
    }
    private void activarDesactivarServidor(){
       
        if(!estado)
            hiloServidor.suspend();
        else{
            try{
                hiloServidor.start();
           }catch(Exception ex)
           {
            hiloServidor.resume();
           } 
        } 
           
                    
            
    }
    
}
