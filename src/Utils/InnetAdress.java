/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import decksplayer.bandeja;
import interfaces.verificarConexion;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 *
 * @author gonzalo
 */
public class InnetAdress {
        private boolean tieneDireccionIp=false;
        private verificarConexion tieneConexion;
        public InnetAdress(verificarConexion tieneConexion){
            getCurrentIp();
                    
        }
        public boolean verificarConexion(){
            return tieneConexionInternet();
                    
        }
        public InetAddress getIp(){
            return getCurrentIp();
        }
        private InetAddress getCurrentIp() {
            try {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface
                        .getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface ni = (NetworkInterface) networkInterfaces
                            .nextElement();
                    Enumeration<InetAddress> nias = ni.getInetAddresses();
                    while(nias.hasMoreElements()) {
                        InetAddress ia= (InetAddress) nias.nextElement();
                        if (!ia.isLinkLocalAddress() 
                         && !ia.isLoopbackAddress()
                         && ia instanceof Inet4Address) {
                           
                            return ia;
                        }
                    }
                }
            } catch (SocketException e) {

            }
            return null;
        }
        
        private boolean tieneConexionInternet(){
        return getIp()!=null;
        }

   
}
