/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import javax.swing.JPanel;

/**
 *
 * @author familia
 */
public class ImagenesProgreso extends JPanel{
    
        public ImagenesProgreso() {
            this.setSize(64, 64);
            if (this.getGraphics() != null) {
                paint(this.getGraphics());
            }
        }

    
}
