/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.prefs.Preferences;

/**
 *
 * @author familia
 */
public class Preferencias {
       private Preferences prefs;
    public Preferencias()
    {
      this.prefs= Preferences.userNodeForPackage(decksplayer.Principal.class);
    }
    public Preferences crearPreferencia()
    {
    return this.prefs;
    }
    public int devolvernumero(String name,int vDef)
    {
       return prefs.getInt(name,vDef);
              
    }
    public String devolverTexto(String key,String def)
    {
    return prefs.get(key, def);
    }
    public void agregarNumero(String key,int n)
    {
     prefs.putInt(key, n);
    }
    public void agregarTexto(String key,String value)
    {
        prefs.put(key, value);
    }
}
