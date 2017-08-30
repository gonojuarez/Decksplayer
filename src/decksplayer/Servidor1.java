/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decksplayer;

/**
 *
 * @author familia
 */
import acciones.BaseDatos;
import com.sun.corba.se.spi.activation.Server;
import java.io.* ;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor1 {
 static final int PUERTO=5000;
ServerSocket skServidor;

    
    BaseDatos bd=new BaseDatos();
public Servidor1( ventanamixer v) {
 try {
 skServidor= new ServerSocket(PUERTO);

System.out.println("Escucho el puerto " + PUERTO );

while(skServidor.isClosed()==false){
    Socket skCliente = skServidor.accept(); // Crea objeto
 InputStream aux = skCliente.getInputStream();
 OutputStream output = skCliente.getOutputStream();
         skCliente.getOutputStream().flush();
 DataOutputStream dat=new DataOutputStream(output);

 DataInputStream flujo= new DataInputStream(aux );
 String fluj=flujo.readUTF();
 
 v.recibirSms(fluj);
 if(fluj.contains("1"))
 {dat.writeUTF(bd.devolvercancion(v.devolverCancion1(),v.devolvernombre1()).getNombre());}
 else if(fluj.contains("2"))
 { dat.writeUTF(bd.devolvercancion(v.devolverCancion2(),v.devolvernombre2()).getNombre());}
skCliente.close();
  
  
}


 } catch(Exception e ) {
System.out.println( e.getMessage() );
 }
 
 }
 public static void main( String[] arg,ventanamixer v) {
try{
     new Servidor1(v);
}catch(Exception ex)
{} 
 }
 
}