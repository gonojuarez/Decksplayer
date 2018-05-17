package decksplayer;


import decksplayer.ventanamixer;
import java.io.* ;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.CORBA.DomainManager;



public class Servidor1 {
 static final int PUERTO=5000;
 String filtro="";
int tipo;
ServerSocket skServidor;
  
public Servidor1(ventanamixer v)
{
 new Servidor1(v,PUERTO);
    

}
    
   
public Servidor1( ventanamixer v,int puerto) {
 try {
 skServidor= new ServerSocket(puerto);
System.out.println("Escucho el puerto " + puerto );
    
     
while(true)
{
    Socket skCliente = skServidor.accept(); // Crea objeto
 InputStream aux = skCliente.getInputStream();
 OutputStream output = skCliente.getOutputStream();
         skCliente.getOutputStream().flush();
 DataOutputStream dat=new DataOutputStream(output);

 DataInputStream flujo= new DataInputStream(aux );
 JSONObject objeto=new JSONObject(flujo.readUTF());
  String accion=objeto.getString("accion");
    System.out.println("json"+objeto.toString());
  if(accion.equals("filtro"))
  {
  filtro=objeto.getString("filtro");
    ArrayList<JSONObject> canciones1=new ArrayList<>();
    if(filtro.equals(""))
    {
    filtro="";
    }
    ArrayList<JSONObject> listado=ventanamixer.controles.devolverJson().getCanciones(filtro);
    
    for(int i=0;i<listado.size();i++)
    {
    JSONObject objeto1=new JSONObject();
    objeto1.put("id",i);
    
    objeto1.put("nombre",ventanamixer.controles.devolverJson().getCanciones(filtro).get(i).getString("cancion") );
    canciones1.add(objeto1);
    }
    
 dat.writeUTF(canciones1.toString());
  }else
  {
  v.recibirSms(accion);
  }
  
 if(accion.contains("1"))
 {
     dat.writeUTF(ventanamixer.b1.devolverNombre(filtro));
        //dat.writeUTF(bd.devolvercancion(v.devolverCancion1(),v.devolvernombre1()).getNombre());}
 }else if(accion.contains("2"))
 { 
 dat.writeUTF(ventanamixer.b2.devolverNombre(filtro));
 }
skCliente.close();
  
} 



 } catch(Exception e ) {
System.out.println( e.getMessage() );
 }
 
 }
 public static void main( String[] arg,ventanamixer v) {
try{
     new Servidor1(v,PUERTO);
    
}catch(Exception ex)
{} 
 }
 
}