/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import java.util.ArrayList;
import javax.swing.JSlider;

/**
 *
 * @author familia
 */
public class equalizador {
    ArrayList<int[]> eqOpcion;
     private int[] gainValue = { 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50 };
    private int[] PRESET_NORMAL = {  0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    private int[] PRESET_CLASSICAL = { 0, 0, 0, 0, 0, 0, -40, -40, -40, -48 };
    private int[] PRESET_CLUB = {  0, 0, 16, 40, 40, 40, 16, 0, 0, 0 };
    private int[] PRESET_DANCE = {  56,32 , 8, 0, 0, -24, -40, -40, 0, 0 };
    private int[] PRESET_FULLBASS = { 80, 72, 64, 24, 0, -24, -40, -48, -64, -64 };
    private int[] PRESET_FULLBASSTREBLE = { 80, 72, 64, 24, 0, 18, 72, 72, 72, 72 };
    private int[] PRESET_FULLTREBLE = { -64, -64, -64, -32, 0, 18, 72, 72, 72, 72 };
    private int[] PRESET_LAPTOP = { 64,56 , 32, -32, -16, 8, 48, 56, 72, 72 };
    private int[] PRESET_LIVE = { -24, 0, 32, 40, 40, 40, 16, 16, 8, 8 };
    private int[] PRESET_PARTY = { 40 , 40, 0, 0, 0, 0, 0, 0, 40, 40};
    private int[] PRESET_POP = { -16,16, 32, 40, 32, -16, -24, -24, -24, -24 };
    private int[] PRESET_REGGAE = {  0, 0, -8, -16, 8, 16, 16, 0, 0, 0 };
    private int[] PRESET_ROCK = { 32, 16, -16, -40, -16,16 , 40, 48, 48, 48 };
    private int[] PRESET_TECHNO = {  32, 16, -16, -40, -16,16 , 40, 48, 56,48 };
    public equalizador()
    {
    }
    private  void setTipo(int[] pos,JSlider[] slider)
    {
    for(int i=0;i<slider.length;i++)
     {
      slider[i].setValue((int)pos[i]);
     }
    }
    public void cargarOpciones(int n,JSlider[] slider)
    {
    switch(n)
    {
      
        case 0:
             setTipo(PRESET_NORMAL, slider);
            break;
        case 1:
             setTipo(PRESET_CLASSICAL, slider);
            break;
        case 2:
             setTipo(PRESET_CLUB, slider);
            break;
        case 3:
             setTipo(PRESET_DANCE, slider);
            break;
        case 4:
             setTipo(PRESET_FULLBASS, slider);
            break;
        case 5:
             setTipo(PRESET_FULLBASSTREBLE, slider);
            break;
        case 6:
             setTipo(PRESET_FULLTREBLE, slider);
            break;
        case 7:
            setTipo(PRESET_LAPTOP, slider);
            break;
        case 8:
            setTipo(PRESET_LIVE, slider);
            break;
        case 9:
            setTipo(PRESET_NORMAL, slider);
            break;
        case 10:
            setTipo(PRESET_PARTY, slider);
            break;
        case 11:
           setTipo(PRESET_POP, slider);
            break;
        case 12:
          setTipo(PRESET_REGGAE, slider);
            break;
        case 13:
          setTipo(PRESET_ROCK, slider);
            break;
        case 14:
           setTipo(PRESET_TECHNO, slider);
            break;
        case -1:
        break;
            
    
    }
    }
}
