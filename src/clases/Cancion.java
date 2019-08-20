/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;


/**
 *
 * @author Administrador
 */
public class Cancion {
   private int id;
    private String nombre;
    private long tamaño;
    private String direccion; 
    private int progreso;
    private BasicPlayerActions basicPlayerActions;
    public Cancion(int id, String nombre, long tamaño, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.direccion = direccion;
        this.basicPlayerActions=new BasicPlayerActions();

    }
     public Cancion(int id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.basicPlayerActions=new BasicPlayerActions();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; 
    }

    public long getTamaño() {
        return tamaño;
    }

    public void setTamaño(long tamaño) {
        this.tamaño = tamaño;
    }
  public void setProgreso(int progress){
      this.progreso=progress;
  }

    public int getProgreso() {
        return progreso;
    }
    
    public BasicPlayerActions getActions(){
        return basicPlayerActions;
    }
    
    
  
    
  
 
}
