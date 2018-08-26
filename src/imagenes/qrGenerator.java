/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagenes;

//import com.google.zxing.common.BitMatrix;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import decksplayer.ventanamixer;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.EnumMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author familia
 */

public class qrGenerator {
   public qrGenerator()
   {
   
   }
   public void setQrImage(String data)
   {
      ImageIcon icon = new ImageIcon(generarMatriz(data, ventanamixer.getQrIcon().getHeight(), ventanamixer.getQrIcon().getWidth()));   
       ventanamixer.getQrIcon().setIcon(icon);
   }
   private BufferedImage generarMatriz(String data,int heigh, int width)
   {
       BitMatrix matrix;
      MultiFormatWriter writer=new MultiFormatWriter();
      String fileType="png";
      File file=new File("codigoQr");
      
       EnumMap<EncodeHintType,String> hint=new EnumMap<EncodeHintType,String>(EncodeHintType.class);
       hint.put(EncodeHintType.CHARACTER_SET,"UTF-8");
       try {
           matrix=writer.encode(data, BarcodeFormat.QR_CODE, width, heigh);
      BufferedImage image=new BufferedImage(width, heigh,BufferedImage.TYPE_INT_RGB);
                    image.createGraphics();
           Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, width, width);
			graphics.setColor(Color.BLACK);       
                        for (int i = 0; i < width; i++) {
				for (int j = 0; j < width; j++) {
					if (matrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
           try {
               ImageIO.write(image, fileType, file);
           } catch (IOException ex) {
               Logger.getLogger(qrGenerator.class.getName()).log(Level.SEVERE, null, ex);
           }
          
           try {  
               return ImageIO.read(file);
           } catch (IOException ex) {
               Logger.getLogger(qrGenerator.class.getName()).log(Level.SEVERE, null, ex);
           }
      
       } catch (WriterException ex) {
           Logger.getLogger(qrGenerator.class.getName()).log(Level.SEVERE, null, ex);
       }
       
   return null;
   }
}
