/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decksplayer;

import java.io.File;
import java.io.RandomAccessFile;
import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.StandardButtonShaper;

/**
 *
 * @author familia
 */
public class lookAndFeel {
    
 public lookAndFeel(){}
 public void setSkin(File f1,File f2,JFrame frame)throws Exception
 {
      if (f1.exists()) {
                RandomAccessFile flow = new RandomAccessFile(f1, "r");//genero un archivo
                flow.seek(flow.getFilePointer());//establezco el indice del archivo
                String tx = flow.readUTF();//leo la linea
                flow.close();//cierro el archivo
                RandomAccessFile file = new RandomAccessFile(f2, "r");//genero un archivo
                file.seek(file.getFilePointer());//establezco el indice del archivo
                String ts = file.readUTF();//leo la linea
                file.close();//cierro el archivo
                frame.setDefaultLookAndFeelDecorated(true);
              
           
                
                JFrame.setDefaultLookAndFeelDecorated(true);//permito decorar la ventana
                SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin." + tx);

                SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark." + ts);

            } else {
                JFrame.setDefaultLookAndFeelDecorated(true);
                SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenSkin");

            }
 }
 
}
