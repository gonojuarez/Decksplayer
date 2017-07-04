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
import java.io.* ;
import java.net.* ;
class Servidor1 {
 static final int PUERTO=5000;
 
ventanamixer v1=null;
ServerSocket skServidor;
 public Servidor1( ventanamixer v) {
 try {
    
 skServidor= new ServerSocket(PUERTO);

System.out.println("Escucho el puerto " + PUERTO );

while(skServidor.isClosed()==false){

    Socket skCliente = skServidor.accept(); // Crea objeto

 InputStream aux = skCliente.getInputStream();
  OutputStream outs=skCliente.getOutputStream();
  DataOutputStream dat=new DataOutputStream(outs);
  dat.writeUTF(v.devolverCancion1()+"");
 DataInputStream flujo= new DataInputStream(aux );
  v.recibirSms(flujo.readUTF());
 

skCliente.close();

}


 } catch(IOException e ) {
System.out.println( e.getMessage() );
 }
 
 }
 public static void main( String[] arg,ventanamixer v) {

     new Servidor1(v);
     
 }
 
}