
package decksplayer;


import acciones.Controles;
import acciones.Buscador;
import static decksplayer.Principal.sertv;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.io.*;
import org.jvnet.substance.SubstanceLookAndFeel;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.net.*;
import java.util.ArrayList;
import static java.util.Collections.enumeration;
import java.util.Enumeration;
import javax.imageio.ImageIO;
import org.jvnet.substance.button.StandardButtonShaper;
import java.awt.Toolkit;
import java.awt.*;
import java.awt.image.BufferedImage;


public class ventanamixer extends javax.swing.JFrame implements KeyListener {
    int con=0;
   int i=0;
    int cont;
    int cont1=0;
    int value=0;
    int tam=0;
    int progreso=0;
    boolean reg=false;
    public ArrayList <String> listas ;//lista a crear
    private Controles buscar;
    float progressUpdate;
    double progressNow;
    private ImageIcon gr;
    String textox="";
    int numero =0;
    int posicion=0;//variable que me permite controlar la posicion en la tabla
    private boolean iseek=false;
    private boolean iseek1=false;
    private boolean swcontrol;//bandera para evento de la tecla control
    private boolean swShift; //bandera para la tecla shift
    private boolean swAlt;//bandera para la tecla alt
    public boolean isa=false;
    private File f=new File("skin.sk"),fs=new File("watemark.sk");
    private RandomAccessFile flow,file;
    private int m1=0,m2=0,s1=0,s2=0,h1=0,h2=0;//variables relacionadas con el tiempo
    public VisorFotos.Album vs;
    int posxw=0;//variable para aumentar el tamaño de la lista creada
    public float[] equalizer; 
    float[] eq = new float[32]; 
   private listener escucha1,escucha2;
   boolean n=false;//sirve para hacer una bandera cuando llamo a las 
   boolean verd;//bandera para saber en que bandeja ecualizar
   private Buscador cargar;//clase para cargar las canciones
   boolean automixer;//boolean  para mezclar en modo aleatorio
   boolean lista;//boolean para una lista comun
   boolean listaC;//boolean para crear una lista
   boolean activar;//bandera para poder activar los tipos de reproducciones
   boolean cAnt;
   int ct;
   boolean cargarCanci;
   int contA=0;
   int ss1=0;
   int x,y;
      public Timer cBand1=new javax.swing.Timer(1000,new ActionListener()// cronometro bandeja 1
   {
    public void actionPerformed(ActionEvent e)
    {
        jProgressBar3.setValue(escucha1.progresos());
   
    jProgressBar5.setValue(escucha1.progresos());
  contA++;
  if(contA==360)
  {
  contA=0;
  }
        s1++;//segundos
        System.out.println(escucha1.taman());
     if(s1==60)
     {
        m1++;//minutos
        s1=0;
      if(m1==60)
       {
        h1++;//horas
        m1=0;
       }
     }
    // System.out.print(buscar.getPlayer().getStatus());
        ti.setText(h1+":"+m1+":"+s1);//muestro el tiempo
        String c=buscar.getPlayer().getStatus()+"";
      if(c.endsWith("2"))
      {
          cBand1.stop();//para el tiempo
         
          if(automixer==true)
          {
           stopo1();   
           i=(int)(Math.random()*jTable1.getRowCount());
           posicion=i;
           play2();
          valor0();
          }
          if(lista==true)
          {
           stopo1();    
          posicion++;
          i=posicion;
         
          play2();
           valor0();
          }
            if(listaC==true)
        {
          stopo2();
          cAnt=false;
            String  trr=(String)listas.get(posicion);
           for ( int j=0; j < jTable1.getRowCount(); j++)
             {
                 if(buscar.getname(j).equalsIgnoreCase(trr)==true)//busco el nombre de la cancion
                {
                 try
                      {
                       
                       jTable1.setRowSelectionInterval(j, j);//selecciono la cancion
                        i=jTable1.getSelectedRow();
                        jTextField2.setText( buscar.getname(j));//escribo el nombre en la bandeja
                      }catch (Exception ex)
                     {
                          Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
                 }
               }
          
           if(posicion==listas.size())
           {
           posicion=0;
           } 
          
           play2();
            valor0();
            posicion++;
        }
      }
    
    jPanel8.updateUI();
   }}
   );
   public Timer cBand2=new javax.swing.Timer(1000,new ActionListener()//Cronometro bandeja 2
{
 public void actionPerformed(ActionEvent e)
 {
    
  jProgressBar4.setValue(escucha2.progresos());
    jProgressBar6.setValue(escucha2.progresos());
    s2++;//segundos
     
    if(s2==60)
    {
         m2++;//minutos
         s2=0;
     if(m2==60)
      {
         h2++;//horas
         m2=0;
         
      }
    }
    // System.out.print(buscar.getPlayer().getStatus());
    tir.setText(h2+":"+m2+":"+s2);
    String c=buscar.getPlayer1().getStatus()+"";
    if(c.endsWith("2"))//si se detiene la cancion
    {
        cBand2.stop();//detengo el tiempo  
         
        if(automixer==true)
        {
           stopo2();
           con=(int)(Math.random()*jTable1.getRowCount()); 
           posicion=con;
           
            play1d();
            valor1();
        }
        if(lista==true)
        { stopo2();
            posicion++;
        con=posicion;
        play1d();
        valor1();
        }
        if(listaC==true)
        {
          stopo2();
            String  trr=(String)listas.get(posicion);
            cAnt=false;
           for ( int j=0; j < jTable1.getRowCount(); j++)
             {
                 if(buscar.getname(j).equalsIgnoreCase(trr)==true)//busco el nombre de la cancion
                {
                 try
                      {
                         //paro la bandeja 2
                       jTable1.setRowSelectionInterval(j, j);//selecciono la cancion
                        con=jTable1.getSelectedRow();
                        jTextField2.setText( buscar.getname(j));//escribo el nombre en la bandeja
                      }catch (Exception ex)
                     {
                          Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
                 }
               }
           
              if(posicion==listas.size())
           {
           posicion=0;
           }
           play1d();
           valor1();
           posicion++;
        }
    }
   //muestro en la bandeja el tiempo
    jPanel15.updateUI();
 }
    }
);
 



    public ventanamixer() throws Exception 
    {
       initComponents();
         listas=new ArrayList<String>();//genero un arreglo para la lista
      gr=new ImageIcon( getClass().getResource("Decksplayer.png"));//agrego el logo a una foto
      this.setIconImage(gr.getImage());//establezco el logo del programa
      this.setTitle("DECKSPLAYER     \n Ip "+InetAddress.getLocalHost().getHostAddress());//pongo el titulo
      this.setLocationRelativeTo(null);//centro la ventana al medio
      Lista.setLocationRelativeTo(null);//centro la ventana de busqueda al medio
      tir.setText(h2+":"+m2+":"+s2);//muestro hora, minutos y segundos
      ti.setText(h1+":"+m1+":"+s1);//muestro hora, minutos y segundos
    escucha1=new listener();
    escucha2=new listener();
  
    try{
     
          buscar.getPlayer1().addBasicPlayerListener(escucha2); 
    
    }catch(Exception ex){}
         
      try  
      {   
          
          
           if(f.exists()){
            flow=new RandomAccessFile(f,"r");//genero un archivo
            flow.seek(flow.getFilePointer());//establezco el indice del archivo
            String tx=flow.readUTF();//leo la linea
            flow.close();//cierro el archivo
            file=new RandomAccessFile(fs,"r");//genero un archivo
            file.seek(file.getFilePointer());//establezco el indice del archivo
            String ts=file.readUTF();//leo la linea
            file.close();//cierro el archivo
            JFrame.setDefaultLookAndFeelDecorated(true);//permito decorar la ventana
          SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin."+tx);
          
           SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark."+ts);
            this.jButton1.putClientProperty(SubstanceLookAndFeel.BUTTON_SIDE_PROPERTY, new StandardButtonShaper());
         
           }
           else
           {
            JFrame.setDefaultLookAndFeelDecorated(true);
                    SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenSkin");
                   
           }
        
           
      }catch (Exception ex)
      {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
      }
      this.cargar=new Buscador();
    
           this.buscar = new Controles();//
       
            this.jTable1.setModel(cargar.devolverModelo());//genero el modelo de la primer tabla
            this.buscar.getPlayer();//obtengo la funcion para reproducir
            cont=volumenn.getValue();
             buscar.getPlayer().addBasicPlayerListener(escucha1);
              buscar.getPlayer1().addBasicPlayerListener(escucha2);
               System.out.println("Iniciando servidor\n");
       
    JDialog d=new JDialog();
        imagenes imag=new imagenes();
        jPanel8.add(imag).setLocation(0, 0);
        jPanel8.repaint();
           imagenes1 ima=new imagenes1();
        jPanel15.add(ima).setLocation(0, 0);
        jPanel15.repaint();
    }

    
    
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Lista = new javax.swing.JDialog();
        jComboBox1 = new javax.swing.JComboBox();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        Bandeja1 = new javax.swing.JMenuItem();
        Bandeja2 = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        AgregarCancion = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        normal = new javax.swing.JMenuItem();
        clasical = new javax.swing.JMenuItem();
        Dance = new javax.swing.JMenuItem();
        Club = new javax.swing.JMenuItem();
        FullBass = new javax.swing.JMenuItem();
        FullBassTreble = new javax.swing.JMenuItem();
        FullTreble = new javax.swing.JMenuItem();
        Laptop = new javax.swing.JMenuItem();
        Live = new javax.swing.JMenuItem();
        Party = new javax.swing.JMenuItem();
        Pop = new javax.swing.JMenuItem();
        Reggae = new javax.swing.JMenuItem();
        Rock = new javax.swing.JMenuItem();
        Techno = new javax.swing.JMenuItem();
        Jazz = new javax.swing.JMenuItem();
        jDialog1 = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        Parlante = new javax.swing.JSlider();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jLabel7 = new javax.swing.JLabel();
        js0 = new javax.swing.JSlider();
        js5 = new javax.swing.JSlider();
        js4 = new javax.swing.JSlider();
        js6 = new javax.swing.JSlider();
        js8 = new javax.swing.JSlider();
        js1 = new javax.swing.JSlider();
        js3 = new javax.swing.JSlider();
        js2 = new javax.swing.JSlider();
        js9 = new javax.swing.JSlider();
        js7 = new javax.swing.JSlider();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        preset1 = new javax.swing.JLabel();
        jDialog2 = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        jSlider2 = new javax.swing.JSlider();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSlider3 = new javax.swing.JSlider();
        jLabel16 = new javax.swing.JLabel();
        js10 = new javax.swing.JSlider();
        js11 = new javax.swing.JSlider();
        js12 = new javax.swing.JSlider();
        js13 = new javax.swing.JSlider();
        js14 = new javax.swing.JSlider();
        js15 = new javax.swing.JSlider();
        js16 = new javax.swing.JSlider();
        js17 = new javax.swing.JSlider();
        js18 = new javax.swing.JSlider();
        js19 = new javax.swing.JSlider();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        preset2 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jDialog3 = new javax.swing.JDialog();
        jSlider6 = new javax.swing.JSlider();
        jProgressBar5 = new javax.swing.JProgressBar();
        jLabel6 = new javax.swing.JLabel();
        jDialog4 = new javax.swing.JDialog();
        jSlider7 = new javax.swing.JSlider();
        jProgressBar6 = new javax.swing.JProgressBar();
        jLabel46 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox();
        jButton7 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        Escritorio = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JLabel();
        play1 = new javax.swing.JButton();
        next1 = new javax.swing.JButton();
        stop1 = new javax.swing.JButton();
        antes1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        ti = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JSlider();
        jProgressBar3 = new javax.swing.JProgressBar();
        jButton10 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JLabel();
        antes2 = new javax.swing.JButton();
        next2 = new javax.swing.JButton();
        stop2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        play2 = new javax.swing.JToggleButton();
        tir = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JSlider();
        jProgressBar4 = new javax.swing.JProgressBar();
        jLabel17 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox();
        jButton9 = new javax.swing.JButton();
        MarcasDeAgua = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel11 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        volumen2 = new javax.swing.JProgressBar();
        volumen1 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        volumenn = new javax.swing.JSlider();
        jToggleButton1 = new javax.swing.JToggleButton();
        jSlider5 = new javax.swing.JSlider();
        jSlider4 = new javax.swing.JSlider();

        Lista.setMinimumSize(new java.awt.Dimension(400, 200));
        Lista.setResizable(false);
        Lista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ListaKeyPressed(evt);
            }
        });
        Lista.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });
        Lista.getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, -1));

        jButton6.setText("Bandeja 1");
        jButton6.setBorder(null);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jButton6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton6KeyPressed(evt);
            }
        });
        Lista.getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jButton8.setText("Bandeja 2");
        jButton8.setBorder(null);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jButton8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton8KeyPressed(evt);
            }
        });
        Lista.getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        jButton13.setText("Cancelar");
        jButton13.setBorder(null);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        Lista.getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 110, -1));

        jButton5.setText("Agregar a la lista");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jButton5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton5KeyPressed(evt);
            }
        });
        Lista.getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jPopupMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPopupMenu1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPopupMenu1KeyPressed(evt);
            }
        });

        Bandeja1.setText("Bandeja1");
        Bandeja1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bandeja1ActionPerformed(evt);
            }
        });
        Bandeja1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Bandeja1KeyPressed(evt);
            }
        });
        jPopupMenu1.add(Bandeja1);

        Bandeja2.setText("Bandeja2");
        Bandeja2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bandeja2ActionPerformed(evt);
            }
        });
        Bandeja2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Bandeja2KeyPressed(evt);
            }
        });
        jPopupMenu1.add(Bandeja2);

        Eliminar.setText("Eliminar cancion");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        Eliminar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EliminarKeyPressed(evt);
            }
        });
        jPopupMenu1.add(Eliminar);

        AgregarCancion.setText("Agregar a la lista");
        AgregarCancion.setName(""); // NOI18N
        AgregarCancion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarCancionActionPerformed(evt);
            }
        });
        AgregarCancion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AgregarCancionKeyPressed(evt);
            }
        });
        jPopupMenu1.add(AgregarCancion);
        AgregarCancion.getAccessibleContext().setAccessibleDescription("");

        normal.setText("normal");
        normal.setToolTipText("");
        normal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                normalActionPerformed(evt);
            }
        });
        jPopupMenu2.add(normal);

        clasical.setText("clasico");
        clasical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clasicalActionPerformed(evt);
            }
        });
        jPopupMenu2.add(clasical);

        Dance.setText("Dance");
        Dance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DanceActionPerformed(evt);
            }
        });
        jPopupMenu2.add(Dance);

        Club.setText("Club");
        Club.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClubActionPerformed(evt);
            }
        });
        jPopupMenu2.add(Club);

        FullBass.setText("Bajos");
        FullBass.setToolTipText("");
        FullBass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FullBassActionPerformed(evt);
            }
        });
        jPopupMenu2.add(FullBass);

        FullBassTreble.setText("Agudos y bajos");
        FullBassTreble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FullBassTrebleActionPerformed(evt);
            }
        });
        jPopupMenu2.add(FullBassTreble);

        FullTreble.setText("Agudos");
        FullTreble.setToolTipText("");
        FullTreble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FullTrebleActionPerformed(evt);
            }
        });
        jPopupMenu2.add(FullTreble);

        Laptop.setText("potente");
        Laptop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaptopActionPerformed(evt);
            }
        });
        jPopupMenu2.add(Laptop);

        Live.setText("Directo");
        Live.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LiveActionPerformed(evt);
            }
        });
        jPopupMenu2.add(Live);

        Party.setText("Fiesta");
        Party.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PartyActionPerformed(evt);
            }
        });
        jPopupMenu2.add(Party);

        Pop.setText("Pop");
        Pop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PopActionPerformed(evt);
            }
        });
        jPopupMenu2.add(Pop);

        Reggae.setText("Reggae");
        Reggae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReggaeActionPerformed(evt);
            }
        });
        jPopupMenu2.add(Reggae);

        Rock.setText("Rock");
        Rock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RockActionPerformed(evt);
            }
        });
        jPopupMenu2.add(Rock);

        Techno.setText("Techno");
        Techno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TechnoActionPerformed(evt);
            }
        });
        jPopupMenu2.add(Techno);

        Jazz.setText("Jazz");
        Jazz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JazzActionPerformed(evt);
            }
        });
        jPopupMenu2.add(Jazz);

        jDialog1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialog1KeyPressed(evt);
            }
        });

        jPanel12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel12KeyPressed(evt);
            }
        });
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Parlante.setMinimum(-100);
        Parlante.setValue(0);
        Parlante.setFocusable(false);
        Parlante.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ParlanteStateChanged(evt);
            }
        });
        Parlante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ParlanteKeyPressed(evt);
            }
        });
        jPanel12.add(Parlante, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 211, -1, -1));

        jLabel10.setText("Izquierda");
        jPanel12.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 211, -1, 26));

        jLabel11.setText("Derecha");
        jPanel12.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, -1, 26));

        jSlider1.setMaximum(50);
        jSlider1.setToolTipText("");
        jSlider1.setValue(0);
        jSlider1.setFocusable(false);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        jSlider1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSlider1KeyPressed(evt);
            }
        });
        jPanel12.add(jSlider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 160, -1, 33));

        jLabel7.setText("Pitch");
        jPanel12.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 167, -1, -1));

        js0.setMinimum(-100);
        js0.setOrientation(javax.swing.JSlider.VERTICAL);
        js0.setValue(0);
        js0.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js0StateChanged(evt);
            }
        });
        js0.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                js0KeyPressed(evt);
            }
        });
        jPanel12.add(js0, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 20, 100));

        js5.setMinimum(-100);
        js5.setOrientation(javax.swing.JSlider.VERTICAL);
        js5.setValue(0);
        js5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js5StateChanged(evt);
            }
        });
        jPanel12.add(js5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 20, 100));

        js4.setMinimum(-100);
        js4.setOrientation(javax.swing.JSlider.VERTICAL);
        js4.setValue(0);
        js4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js4StateChanged(evt);
            }
        });
        jPanel12.add(js4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 20, 100));

        js6.setMinimum(-100);
        js6.setOrientation(javax.swing.JSlider.VERTICAL);
        js6.setValue(0);
        js6.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js6StateChanged(evt);
            }
        });
        jPanel12.add(js6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 20, 100));

        js8.setMinimum(-100);
        js8.setOrientation(javax.swing.JSlider.VERTICAL);
        js8.setValue(0);
        js8.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js8StateChanged(evt);
            }
        });
        jPanel12.add(js8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 20, 100));

        js1.setMinimum(-100);
        js1.setOrientation(javax.swing.JSlider.VERTICAL);
        js1.setValue(0);
        js1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js1StateChanged(evt);
            }
        });
        jPanel12.add(js1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 20, 100));

        js3.setMinimum(-100);
        js3.setOrientation(javax.swing.JSlider.VERTICAL);
        js3.setValue(0);
        js3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js3StateChanged(evt);
            }
        });
        jPanel12.add(js3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 20, 100));

        js2.setMinimum(-100);
        js2.setOrientation(javax.swing.JSlider.VERTICAL);
        js2.setValue(0);
        js2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js2StateChanged(evt);
            }
        });
        jPanel12.add(js2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 20, 100));

        js9.setMinimum(-100);
        js9.setOrientation(javax.swing.JSlider.VERTICAL);
        js9.setValue(0);
        js9.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js9StateChanged(evt);
            }
        });
        jPanel12.add(js9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 20, 100));

        js7.setMinimum(-100);
        js7.setOrientation(javax.swing.JSlider.VERTICAL);
        js7.setValue(0);
        js7.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js7StateChanged(evt);
            }
        });
        jPanel12.add(js7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 20, 100));

        jLabel19.setText("31");
        jPanel12.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jLabel20.setText("62");
        jPanel12.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jLabel21.setText("125");
        jPanel12.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setText("Graves");
        jPanel12.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 70, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setText("Medios");
        jPanel12.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 80, 20));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setText("Agudos");
        jPanel12.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 2, -1, 30));

        jLabel25.setText("250");
        jPanel12.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, -1, -1));

        jLabel26.setText("500");
        jPanel12.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, -1));

        jLabel27.setText("1KHZ");
        jPanel12.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, -1, -1));

        jLabel28.setText("    2");
        jPanel12.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 30, -1));

        jLabel29.setText("   4");
        jPanel12.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 30, -1));

        jLabel30.setText("8 ");
        jPanel12.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 140, 10, -1));

        jLabel31.setText("16KHZ");
        jPanel12.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 40, -1));

        preset1.setText("preset");
        preset1.setComponentPopupMenu(jPopupMenu2);
        preset1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                preset1MouseEntered(evt);
            }
        });
        jPanel12.add(preset1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, -1, -1));

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
        );

        jDialog2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialog2KeyPressed(evt);
            }
        });

        jPanel13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel13KeyPressed(evt);
            }
        });
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSlider2.setMinimum(-100);
        jSlider2.setValue(0);
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });
        jSlider2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSlider2KeyPressed(evt);
            }
        });
        jPanel13.add(jSlider2, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 213, -1, -1));

        jLabel12.setText("Izquierda");
        jPanel13.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 213, -1, 26));

        jLabel13.setText("Derecha");
        jPanel13.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(336, 213, -1, 26));

        jSlider3.setMaximum(50);
        jSlider3.setToolTipText("");
        jSlider3.setValue(0);
        jSlider3.setFocusable(false);
        jSlider3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider3StateChanged(evt);
            }
        });
        jSlider3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSlider3KeyPressed(evt);
            }
        });
        jPanel13.add(jSlider3, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 181, -1, -1));

        jLabel16.setText("pitch");
        jPanel13.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 181, -1, -1));

        js10.setMinimum(-100);
        js10.setOrientation(javax.swing.JSlider.VERTICAL);
        js10.setValue(0);
        js10.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js10StateChanged(evt);
            }
        });
        js10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                js10KeyPressed(evt);
            }
        });
        jPanel13.add(js10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 100));

        js11.setMinimum(-100);
        js11.setOrientation(javax.swing.JSlider.VERTICAL);
        js11.setValue(0);
        js11.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js11StateChanged(evt);
            }
        });
        jPanel13.add(js11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, 100));

        js12.setMinimum(-100);
        js12.setOrientation(javax.swing.JSlider.VERTICAL);
        js12.setValue(0);
        js12.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js12StateChanged(evt);
            }
        });
        jPanel13.add(js12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, 100));

        js13.setMinimum(-100);
        js13.setOrientation(javax.swing.JSlider.VERTICAL);
        js13.setValue(0);
        js13.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js13StateChanged(evt);
            }
        });
        jPanel13.add(js13, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 20, 100));

        js14.setMinimum(-100);
        js14.setOrientation(javax.swing.JSlider.VERTICAL);
        js14.setValue(0);
        js14.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js14StateChanged(evt);
            }
        });
        jPanel13.add(js14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 20, 100));

        js15.setMinimum(-100);
        js15.setOrientation(javax.swing.JSlider.VERTICAL);
        js15.setValue(0);
        js15.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js15StateChanged(evt);
            }
        });
        jPanel13.add(js15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 20, 100));

        js16.setMinimum(-100);
        js16.setOrientation(javax.swing.JSlider.VERTICAL);
        js16.setValue(0);
        js16.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js16StateChanged(evt);
            }
        });
        jPanel13.add(js16, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 20, 100));

        js17.setMinimum(-100);
        js17.setOrientation(javax.swing.JSlider.VERTICAL);
        js17.setValue(0);
        js17.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js17StateChanged(evt);
            }
        });
        jPanel13.add(js17, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 20, 100));

        js18.setMinimum(-100);
        js18.setOrientation(javax.swing.JSlider.VERTICAL);
        js18.setValue(0);
        js18.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js18StateChanged(evt);
            }
        });
        jPanel13.add(js18, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 20, 100));

        js19.setMinimum(-100);
        js19.setOrientation(javax.swing.JSlider.VERTICAL);
        js19.setValue(0);
        js19.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                js19StateChanged(evt);
            }
        });
        jPanel13.add(js19, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 20, 100));

        jLabel32.setText("125");
        jPanel13.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 140, 30, -1));

        jLabel33.setText("31");
        jPanel13.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 140, 20, -1));

        jLabel34.setText("62");
        jPanel13.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 140, 20, -1));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel35.setText("Graves");
        jPanel13.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 70, -1));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel36.setText("Agudos");
        jPanel13.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 2, -1, 30));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel37.setText("Medios");
        jPanel13.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 80, 20));

        jLabel38.setText("250");
        jPanel13.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 140, 30, -1));

        jLabel39.setText("500");
        jPanel13.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, -1, -1));

        jLabel40.setText("1KHZ");
        jPanel13.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, -1, -1));

        jLabel41.setText("    2");
        jPanel13.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 30, -1));

        jLabel42.setText("   4");
        jPanel13.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 30, -1));

        jLabel43.setText("8 ");
        jPanel13.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 140, 10, -1));

        jLabel44.setText("16KHZ");
        jPanel13.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 40, -1));

        preset2.setText("Preset");
        preset2.setComponentPopupMenu(jPopupMenu2);
        preset2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                preset2MouseEntered(evt);
            }
        });
        jPanel13.add(preset2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 40, -1));

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
            .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDialog2Layout.createSequentialGroup()
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
        );

        jLabel45.setText("jLabel45");

        jMenuItem1.setText("bandeja1 - informe");
        jMenuItem1.setToolTipText("");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu3.add(jMenuItem1);

        jMenuItem2.setText("bandeja 2 - informe");
        jMenuItem2.setToolTipText("");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu4.add(jMenuItem2);

        jDialog3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialog3KeyPressed(evt);
            }
        });

        jSlider6.setValue(0);
        jSlider6.setFocusable(false);
        jSlider6.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider6StateChanged(evt);
            }
        });
        jSlider6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSlider6KeyPressed(evt);
            }
        });

        jProgressBar5.setStringPainted(true);
        jProgressBar5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jProgressBar5KeyPressed(evt);
            }
        });

        jLabel6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel6KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSlider6, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jDialog4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDialog4KeyPressed(evt);
            }
        });

        jSlider7.setValue(0);
        jSlider7.setFocusable(false);
        jSlider7.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider7StateChanged(evt);
            }
        });
        jSlider7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSlider7KeyPressed(evt);
            }
        });

        jProgressBar6.setStringPainted(true);
        jProgressBar6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jProgressBar6KeyPressed(evt);
            }
        });

        jLabel46.setText(" ");
        jLabel46.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel46KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
        jDialog4.getContentPane().setLayout(jDialog4Layout);
        jDialog4Layout.setHorizontalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSlider7, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jDialog4Layout.setVerticalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog4Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jProgressBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSlider7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel14.setFocusable(false);
        jPanel14.setName(""); // NOI18N
        jPanel14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel14KeyPressed(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setRequestFocusEnabled(false);
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane1KeyPressed(evt);
            }
        });

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel5KeyPressed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTable1MouseExited(evt);
            }
        });
        jTable1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                jTable1ComponentMoved(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/carpeta.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jTextField3.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jTextField3InputMethodTextChanged(evt);
            }
        });
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jLabel4.setText("Buscar");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/lista1.png"))); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todas", "ListaCreada" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jComboBox3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox3KeyPressed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/aleatorio.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton7KeyPressed(evt);
            }
        });

        jTextPane1.setContentType("text/html"); // NOI18N
        jTextPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextPane1KeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addGap(54, 54, 54)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 280));

        jTabbedPane1.addTab("Musica", new javax.swing.ImageIcon(getClass().getResource("/decksplayer/musica2.png")), jPanel3); // NOI18N

        jTabbedPane4.setFocusable(false);
        jTabbedPane4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane4StateChanged(evt);
            }
        });
        jTabbedPane4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane4KeyPressed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.setFocusable(false);
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel2KeyPressed(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setPreferredSize(new java.awt.Dimension(64, 64));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 64, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 64, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 64, 64));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Adelantar");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 20));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 370, 20));

        play1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/play.png"))); // NOI18N
        play1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        play1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                play1ActionPerformed(evt);
            }
        });
        play1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                play1KeyPressed(evt);
            }
        });
        jPanel2.add(play1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 50, 50));

        next1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/siguiente.png"))); // NOI18N
        next1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        next1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                next1MousePressed(evt);
            }
        });
        next1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next1ActionPerformed(evt);
            }
        });
        next1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                next1KeyPressed(evt);
            }
        });
        jPanel2.add(next1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 50, 50));

        stop1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/stop.png"))); // NOI18N
        stop1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        stop1.setFocusCycleRoot(true);
        stop1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stop1ActionPerformed(evt);
            }
        });
        stop1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                stop1KeyPressed(evt);
            }
        });
        jPanel2.add(stop1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 50, 50));

        antes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/anterior.PNG"))); // NOI18N
        antes1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        antes1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/anterior.PNG"))); // NOI18N
        antes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                antes1ActionPerformed(evt);
            }
        });
        antes1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                antes1KeyPressed(evt);
            }
        });
        jPanel2.add(antes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 50, 50));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/pausa.png"))); // NOI18N
        jButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 50, 50));

        ti.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jPanel2.add(ti, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, 110, 40));

        jProgressBar1.setToolTipText("adelantar");
        jProgressBar1.setValue(0);
        jProgressBar1.setFocusable(false);
        jProgressBar1.setOpaque(false);
        jProgressBar1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jProgressBar1StateChanged(evt);
            }
        });
        jProgressBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jProgressBar1MouseClicked(evt);
            }
        });
        jProgressBar1.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jProgressBar1VetoableChange(evt);
            }
        });
        jPanel2.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 400, 30));

        jProgressBar3.setBackground(new java.awt.Color(0, 102, 153));
        jProgressBar3.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar3.setComponentPopupMenu(jPopupMenu3);
        jProgressBar3.setStringPainted(true);
        jPanel2.add(jProgressBar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 380, 60));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/ecualizador.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 60, 40));

        jTabbedPane4.addTab("Bandeja 1", jPanel2);

        jPanel4.setFocusable(false);
        jPanel4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel4KeyPressed(evt);
            }
        });
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });
        jPanel4.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 370, 20));

        antes2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/anterior_1.png"))); // NOI18N
        antes2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        antes2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                antes2ActionPerformed(evt);
            }
        });
        antes2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                antes2KeyPressed(evt);
            }
        });
        jPanel4.add(antes2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 50, 50));

        next2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/siguiente_1.png"))); // NOI18N
        next2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        next2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next2ActionPerformed(evt);
            }
        });
        next2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                next2KeyPressed(evt);
            }
        });
        jPanel4.add(next2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 50, 50));

        stop2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/stop.png"))); // NOI18N
        stop2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        stop2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stop2MouseClicked(evt);
            }
        });
        stop2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stop2ActionPerformed(evt);
            }
        });
        stop2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                stop2KeyPressed(evt);
            }
        });
        jPanel4.add(stop2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 50, 50));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/pausa.png"))); // NOI18N
        jButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jButton4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton4KeyPressed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 50, 50));

        play2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/play.png"))); // NOI18N
        play2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        play2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                play2StateChanged(evt);
            }
        });
        play2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                play2ActionPerformed(evt);
            }
        });
        play2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                play2KeyPressed(evt);
            }
        });
        jPanel4.add(play2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 50, 50));

        tir.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jPanel4.add(tir, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, 110, 40));

        jProgressBar2.setValue(0);
        jProgressBar2.setFocusable(false);
        jProgressBar2.setOpaque(false);
        jProgressBar2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jProgressBar2StateChanged(evt);
            }
        });
        jProgressBar2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jProgressBar2PropertyChange(evt);
            }
        });
        jProgressBar2.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jProgressBar2VetoableChange(evt);
            }
        });
        jPanel4.add(jProgressBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 400, 30));

        jProgressBar4.setComponentPopupMenu(jPopupMenu4);
        jProgressBar4.setStringPainted(true);
        jPanel4.add(jProgressBar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 380, 60));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Adelantar");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 20));

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/ecualizador.png"))); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 60, 40));

        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel15MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 70));

        jTabbedPane4.addTab("Bandeja 2", jPanel4);

        javax.swing.GroupLayout EscritorioLayout = new javax.swing.GroupLayout(Escritorio);
        Escritorio.setLayout(EscritorioLayout);
        EscritorioLayout.setHorizontalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EscritorioLayout.createSequentialGroup()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        EscritorioLayout.setVerticalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Bandejas", new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/bandeja.png")), jPanel1); // NOI18N

        jTabbedPane2.setToolTipText("");
        jTabbedPane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane2KeyPressed(evt);
            }
        });

        jPanel10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel10KeyPressed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AutumnSkin", "BusinessSkinn", "BusinessBlackSteelSkin", "BusinessBlueSteelSkin", "ChallengerDeepSkin", "CremeSkin", "CremeCoffeeSkin", "DustSkin", "DustCoffeeSkin", "EmeraldDuskSkin", "GeminiSkin", "GraphiteAquaSkin", "MagellanSkin", "MagmaSkin", "MistAquaSkin", "MistSilverSkin", "ModerateSkin", "NebulaSkin", "NebulaBrickWallSkin", "OfficeBlue2007Skin", "OfficeSilver2007Skin", "RavenSkin", "RavenGraphiteSkin", "RavenGraphiteGlassSkin", "SaharaSkin", "TwilightSkin" }));
        jComboBox2.setToolTipText("");
        jComboBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jComboBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox2MouseClicked(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
        });

        jButton9.setText("Aceptar");
        jButton9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jButton9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton9KeyPressed(evt);
            }
        });

        MarcasDeAgua.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SubstanceBubblesWatermark", "SubstanceBinaryWatermark", "SubstanceCopperplateEngravingWatermark", "SubstanceCrosshatchWatermark", "SubstanceFabricWatermark", "SubstanceGenericNoiseWatermark", "SubstanceImageWatermark", "SubstanceKatakanaWatermark", "SubstanceLatchWatermark", "SubstanceMagneticFieldWatermark", "SubstanceMarbleVeinWatermark", "SubstanceMazeWatermark", "SubstanceMetalWallWatermark", "SubstanceNoneWatermark", "SubstanceNullWatermark", "SubstancePlanktonWatermark", "SubstanceStripeWatermark", "SubstanceWoodWatermark" }));
        MarcasDeAgua.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel14.setText("Skin");

        jLabel15.setText("Marca de agua");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 141, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MarcasDeAgua, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(jLabel14))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jLabel15))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MarcasDeAgua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("configuracion de la apariencia", jPanel10);

        jPanel6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel6KeyPressed(evt);
            }
        });

        jLabel8.setText("Bandeja 2");

        jLabel9.setText("Bandeja 1");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("shift +\ni:Bandejas pequeñas\ne:ecualizador\nf: tema anterior\ng: poner play\nh :tema siguiente\np: pausar tema\ns:parar tema\nb :buscar para agreg\nar tema\nM: musica\nalt+2 \nbandeja 2");
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("ctrl +\ni :bandeja pequeñas \ne :Ecualizador\nf :tema anterior\ng :poner play\nh : tema siguiente\np :pausar tema\ns : parar tema\nb: buscar para agreg\nar musica\nalt + 1\nbandeja 1\nflechas izquierda y \nderecha sonido de las\nbandejas\n");
        jTextArea2.setToolTipText("\n");
        jTextArea2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea2KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(108, 108, 108))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Informacion", jPanel6);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Configuracion", new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/configuracion_1.png")), jPanel9); // NOI18N

        jTabbedPane5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane5KeyPressed(evt);
            }
        });

        jPanel7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel7KeyPressed(evt);
            }
        });

        jButton11.setText("Visor");
        jButton11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jButton11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton11KeyPressed(evt);
            }
        });

        jButton12.setText("Cerrar");
        jButton12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jButton12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton12KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton12)
                .addGap(64, 64, 64))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(jButton12))
                .addContainerGap())
        );

        jTabbedPane5.addTab("Ver Fotos", jPanel7);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane1.addTab("Fotos", new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/camara2.png")), jPanel11); // NOI18N

        volumen2.setMaximum(101);
        volumen2.setOrientation(1);
        volumen2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        volumen2.setMaximumSize(new java.awt.Dimension(17, 146));

        volumen1.setMaximum(101);
        volumen1.setOrientation(1);
        volumen1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        volumen1.setMaximumSize(new java.awt.Dimension(17, 146));

        jLabel3.setText("Bandeja 2");

        jLabel2.setText("Bandeja 1");

        volumenn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        volumenn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                volumennStateChanged(evt);
            }
        });
        volumenn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                volumennKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                volumennKeyTyped(evt);
            }
        });

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/window-close.png"))); // NOI18N
        jToggleButton1.setText("salir");
        jToggleButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToggleButton1.setBorderPainted(false);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jSlider5.setOrientation(javax.swing.JSlider.VERTICAL);
        jSlider5.setValue(0);
        jSlider5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider5StateChanged(evt);
            }
        });
        jSlider5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSlider5KeyPressed(evt);
            }
        });

        jSlider4.setOrientation(javax.swing.JSlider.VERTICAL);
        jSlider4.setToolTipText("");
        jSlider4.setValue(0);
        jSlider4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider4StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(volumenn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(volumen1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jSlider5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(volumen2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSlider5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(volumen2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(33, 33, 33))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(volumen1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volumenn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
    System.exit(0);//salgo del programa
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    cargarCancion((String)jComboBox1.getSelectedItem()); //metodo para cargar canciones en la bandeja 1
    jComboBox1.removeAllItems();//remuevo todos los elementos del archivo de busqueda
    }//GEN-LAST:event_jButton6ActionPerformed

        private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        cargarCancion2((String)jComboBox1.getSelectedItem()); //metodo para cargar canciones en la bandeja 2
        jComboBox1.removeAllItems();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void volumennKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_volumennKeyPressed
     keyPressed(evt);setFocusable(true);//evento para cuando se aprietan las teclas
    }//GEN-LAST:event_volumennKeyPressed

    private void volumennKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_volumennKeyTyped
     keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_volumennKeyTyped

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
     keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_formKeyPressed

    private void jPanel14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel14KeyPressed
    keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jPanel14KeyPressed

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
     keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jButton6KeyPressed

    private void jButton8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton8KeyPressed
     keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jButton8KeyPressed

    private void volumennStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_volumennStateChanged
   
     volumenn.setValue(volumenn.getValue());
//establezco el volumen
     value();//metodo para cambiar el volumen
    }//GEN-LAST:event_volumennStateChanged

    private void ListaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ListaKeyPressed
     keyPressed(evt);setFocusable(true);     // TODO add your handling code here:
    }//GEN-LAST:event_ListaKeyPressed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
     keyPressed(evt);setFocusable(true);    // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1KeyPressed

    private void jTabbedPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane1KeyPressed
     keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jTabbedPane1KeyPressed

    private void jTabbedPane4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane4KeyPressed
      keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jTabbedPane4KeyPressed

    private void jPanel4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel4KeyPressed
      keyPressed(evt);setFocusable(true);   // TODO add your handling code here:
    }//GEN-LAST:event_jPanel4KeyPressed

    private void play2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_play2KeyPressed
      keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_play2KeyPressed

    private void play2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_play2ActionPerformed
     
      
     
      play2();//inicio la cancion en la bandeja 2
      value();
    
    }//GEN-LAST:event_play2ActionPerformed

    private void play2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_play2StateChanged

    }//GEN-LAST:event_play2StateChanged

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
      keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jButton4KeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
   
       pausa2();//pauso la bandeja 2
    }//GEN-LAST:event_jButton4ActionPerformed

    private void stop2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stop2KeyPressed
      keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_stop2KeyPressed

    private void stop2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stop2ActionPerformed
   
        stopo2();//paro la bandeja 2
    }//GEN-LAST:event_stop2ActionPerformed

    private void stop2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stop2MouseClicked
       

    }//GEN-LAST:event_stop2MouseClicked

    private void next2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_next2KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_next2KeyPressed

    private void next2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next2ActionPerformed
    
  
           siguiente2();        //boton siguiente en la bandeja 2
        
    }//GEN-LAST:event_next2ActionPerformed

    private void antes2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_antes2KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_antes2KeyPressed

    private void antes2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_antes2ActionPerformed
      
        antes2();        //boton antes en la bandeja 2
       
    }//GEN-LAST:event_antes2ActionPerformed

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jPanel2KeyPressed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
   
        pausa1();//pauso la bandeja 1
    }//GEN-LAST:event_jButton3ActionPerformed

    private void antes1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_antes1KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_antes1KeyPressed

    private void antes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_antes1ActionPerformed
   antes();
    }//GEN-LAST:event_antes1ActionPerformed

    private void stop1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stop1KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_stop1KeyPressed

    private void stop1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stop1ActionPerformed
    
        
        stopo1(); 
       //se encarga de la funcion stop del programa boton de la bandeja 1
    }//GEN-LAST:event_stop1ActionPerformed

    private void next1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_next1KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_next1KeyPressed

    private void next1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next1ActionPerformed
     
         siguiente1();
        
    }//GEN-LAST:event_next1ActionPerformed

    private void play1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_play1KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_play1KeyPressed

    private void play1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_play1ActionPerformed
 
       
    imagenes im=new imagenes();
    jPanel8.add(im).setLocation(32, 32);
  jPanel8.repaint();
        play1d(); //Se encarga de realizar la funcion de play del programa boton de la bandeja 1
        value();
   
    
      
       
    }//GEN-LAST:event_play1ActionPerformed

    private void jTabbedPane2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane2KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jTabbedPane2KeyPressed

    private void jPanel6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel6KeyPressed
        keyPressed(evt);setFocusable(true);   // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6KeyPressed

    private void jTextArea2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea2KeyPressed
        keyPressed(evt);setFocusable(true);   // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea2KeyPressed

    private void jTextArea1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyPressed
        keyPressed(evt);setFocusable(true);    // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea1KeyPressed

    private void jPanel10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel10KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jPanel10KeyPressed

    private void jButton9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton9KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jButton9KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       //codigo para generar el skin y la marca de agua para el programa
        try {

            SwingUtilities.updateComponentTreeUI(ventanamixer.this);
            flow=new RandomAccessFile(f,"rw");
            flow.writeUTF((String)jComboBox2.getSelectedItem());
            flow.close();
            file=new RandomAccessFile(fs,"rw");
            file.writeUTF((String)MarcasDeAgua.getSelectedItem());
            file.close();

            SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin."+(String)jComboBox2.getSelectedItem());
            SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark."+(String)MarcasDeAgua.getSelectedItem());

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jComboBox2KeyPressed

    private void jComboBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseClicked

    }//GEN-LAST:event_jComboBox2MouseClicked

    private void jPanel5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel5KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jPanel5KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    
        try {
            //buscar los archivos
            // buscar.buscarArchivo();
            // BuscadorArchivos bbs=new BuscadorArchivos();
            cargar.buscar();
            jTable1.removeAll();
            jTable1.setModel(cargar.devolverModelo());
        } catch (Exception ex) {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
   
 
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void next1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_next1MousePressed
        siguiente1(); //paso a la siguiente cancion
    }//GEN-LAST:event_next1MousePressed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
      //cancelo la busqueda de temas 
      jComboBox1.removeAllItems();
      jTextField3.setText("");
      Lista.dispose();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void Bandeja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bandeja1ActionPerformed
  
      try
       {
           con=jTable1.getSelectedRow();
           jTextField1.setText(buscar.getname(jTable1.getSelectedRow()));
           cargarCancion(buscar.getname(jTable1.getSelectedRow()));
          
     
        
        }
      catch (Exception ex)
        {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Bandeja1ActionPerformed

    private void Bandeja2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bandeja2ActionPerformed
             
       try
        {
          i=jTable1.getSelectedRow();
          jTextField2.setText(buscar.getname(jTable1.getSelectedRow()));
          cargarCancion2(buscar.getname(jTable1.getSelectedRow()));
        
     
         
        }
       catch (Exception ex)
        {
          Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Bandeja2ActionPerformed

    private void jPopupMenu1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPopupMenu1KeyPressed
       keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jPopupMenu1KeyPressed

    private void jTextField3InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTextField3InputMethodTextChanged
    buscarr();
    }//GEN-LAST:event_jTextField3InputMethodTextChanged

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
       if (jTextField3.getText()!=null&&evt.getKeyCode()==KeyEvent.VK_ENTER) {
          Lista.dispose();
           jComboBox1.removeAllItems();
           buscarr();
        }
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTable1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseExited

    private void jTable1ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable1ComponentMoved
      
    }//GEN-LAST:event_jTable1ComponentMoved

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
     
         int k=jTable1.getSelectedRow();
     cargar.eliminar(k);
        jTable1.remove(k);
        jTable1.updateUI();
    }//GEN-LAST:event_EliminarActionPerformed

    private void jSlider4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider4StateChanged
        try {
            buscar.setVolumen((float)jSlider4.getValue()/jSlider4.getMaximum());
            volumen1.setValue(jSlider4.getValue());
        }catch(Exception ex)
        {
        
        }
    }//GEN-LAST:event_jSlider4StateChanged

    private void jSlider5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider5StateChanged
        try {
            buscar.setVolumen1((float)jSlider5.getValue()/jSlider5.getMaximum());
              volumen2.setValue(jSlider5.getValue());
        }catch(Exception ex)
        {
        
        }
    }//GEN-LAST:event_jSlider5StateChanged

    private void jSlider5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSlider5KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jSlider5KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   automixer=false;
        if(activar==false){
            if(jComboBox3.getSelectedIndex()==1){
                listaC=true; lista=false;
                posicion=0;
                ImageIcon gr1=new ImageIcon( getClass().getResource("/dj/Imagenes/lista3.png"));
                jButton2.setIcon(gr1);
                ImageIcon gr2=new ImageIcon( getClass().getResource("/dj/Imagenes/aleatorio.png"));
                jButton7.setIcon(gr2);
                String  trr=(String)listas.get(posicion);
                for ( int j=0; j < jTable1.getRowCount(); j++)
                {
                    if(buscar.getname(j).equalsIgnoreCase(trr)==true)//busco el nombre de la cancion
                    {
                        try
                        {
                            stopo2();//paro la bandeja 2
                            jTable1.setRowSelectionInterval(j, j);//selecciono la cancion
                            con=jTable1.getSelectedRow();
                            jTextField2.setText( buscar.getname(j));//escribo el nombre en la bandeja
                        }catch (Exception ex)
                        {
                            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
                play1d();
            }
            if(jComboBox3.getSelectedIndex()==0)
            {
                lista=true;
                listaC=false;
                con=posicion;
                play1d();
                ImageIcon gr1=new ImageIcon( getClass().getResource("/dj/Imagenes/lista2.png"));
                jButton2.setIcon(gr1);
                ImageIcon gr2=new ImageIcon( getClass().getResource("/dj/Imagenes/aleatorio.png"));
                jButton7.setIcon(gr2);
            }
            activar=true;
        }
        else
        {
            activar=false;
            ImageIcon gr1=new ImageIcon( getClass().getResource("/dj/Imagenes/lista1.png"));
            jButton2.setIcon(gr1);
        }     
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jButton2KeyPressed

    private void AgregarCancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarCancionActionPerformed
     
    
        listas.add(posxw,buscar.getname(jTable1.getSelectedRow()));
      posxw++;
     jTextPane1.setEditable(false);
      String ns="<td ><tr text-color=\"white\" >Lista Creada</tr>";
        for (int j = 0; j <listas.size(); j++) {
            ns+="<tr><p style= \"font-size:8 px;color:white;\" >"+listas.get(j)+"</p></tr>";
        }
        ns+="</td>";
      jTextPane1.setText("<html><body><table frame=\"below\">"+ns+"</table></body></html>");
        System.out.println(posxw);
    }//GEN-LAST:event_AgregarCancionActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
       
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        for (int j = 0; j < jTable1.getRowCount(); j++) 
        {
        String  trr=(String)jComboBox1.getSelectedItem();
      if(buscar.getname(j).equalsIgnoreCase(trr)==true)
      {
      try
       {
        jTable1.setRowSelectionInterval(j, j);
        listas.add(posxw,buscar.getname(jTable1.getSelectedRow()));
        posxw++;
        
            
        } catch (Exception ex) {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
           
      }}
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton5KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jButton5KeyPressed

    private void AgregarCancionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AgregarCancionKeyPressed
       keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_AgregarCancionKeyPressed

    private void EliminarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EliminarKeyPressed
       keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_EliminarKeyPressed

    private void Bandeja2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Bandeja2KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_Bandeja2KeyPressed

    private void Bandeja1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Bandeja1KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_Bandeja1KeyPressed

    private void jComboBox3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox3KeyPressed
       keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jComboBox3KeyPressed

    private void jProgressBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jProgressBar1MouseClicked
       
      
       
    }//GEN-LAST:event_jProgressBar1MouseClicked

    private void jProgressBar2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jProgressBar2StateChanged

       buscar.adelantar1(jProgressBar2.getValue());
    }//GEN-LAST:event_jProgressBar2StateChanged

    private void jProgressBar1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jProgressBar1StateChanged
      buscar.adelantar(jProgressBar1.getValue());
    }//GEN-LAST:event_jProgressBar1StateChanged

    private void jProgressBar1VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jProgressBar1VetoableChange
         buscar.adelantar(jProgressBar1.getValue());
    }//GEN-LAST:event_jProgressBar1VetoableChange

    private void jProgressBar2VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jProgressBar2VetoableChange
        buscar.adelantar1(jProgressBar2.getValue());
    }//GEN-LAST:event_jProgressBar2VetoableChange

    private void jProgressBar2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jProgressBar2PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jProgressBar2PropertyChange

    private void jTabbedPane5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane5KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jTabbedPane5KeyPressed

    private void jPanel12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel12KeyPressed
        keyPressed(evt);setFocusable(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel12KeyPressed

    private void js7StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js7StateChanged
        escucha1.setEq(7,js7.getValue());
    }//GEN-LAST:event_js7StateChanged

    private void js9StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js9StateChanged
        escucha1.setEq(8,js9.getValue());
    }//GEN-LAST:event_js9StateChanged

    private void js2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js2StateChanged
         escucha1.setEq(2,js2.getValue());
    }//GEN-LAST:event_js2StateChanged

    private void js3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js3StateChanged
         escucha1.setEq(3,js3.getValue());
    }//GEN-LAST:event_js3StateChanged

    private void js1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js1StateChanged
         escucha1.setEq(1,js1.getValue());
    }//GEN-LAST:event_js1StateChanged

    private void js8StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js8StateChanged
        escucha1.setEq(9,js8.getValue());
    }//GEN-LAST:event_js8StateChanged

    private void js6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js6StateChanged
        escucha1.setEq(6,js6.getValue());
    }//GEN-LAST:event_js6StateChanged

    private void js4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js4StateChanged
         escucha1.setEq(4,js4.getValue());
    }//GEN-LAST:event_js4StateChanged

    private void js0KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_js0KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_js0KeyPressed

    private void js0StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js0StateChanged
        escucha1.setEq(0,js0.getValue());
    }//GEN-LAST:event_js0StateChanged

    private void js5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js5StateChanged
        escucha1.setEq(5,js5.getValue());
    }//GEN-LAST:event_js5StateChanged

    private void jSlider1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSlider1KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jSlider1KeyPressed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
       buscar.pitch(jSlider1.getValue());
    }//GEN-LAST:event_jSlider1StateChanged

    private void ParlanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ParlanteKeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_ParlanteKeyPressed

    private void ParlanteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ParlanteStateChanged
        double sw=(double)(Parlante.getValue()/100);//cambio el paneo del programa
        buscar.setPan(sw);
    }//GEN-LAST:event_ParlanteStateChanged

    private void jPanel7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel7KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jPanel7KeyPressed

    private void jButton12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton12KeyPressed
        keyPressed(evt);setFocusable(true);  // TODO add your handling code here:
    }//GEN-LAST:event_jButton12KeyPressed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        //cierro el programa para ver fotos
        vs.setVisible(false);
        vs.dispose();
        n=false;
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton11KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jButton11KeyPressed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        //llamo al programa para ver fotos
        
        if(n==false){
        vs=new VisorFotos.Album();
        vs.setVisible(true);
        vs.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        n=true;
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jPanel13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel13KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jPanel13KeyPressed

    private void jSlider3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSlider3KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jSlider3KeyPressed

    private void jSlider3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider3StateChanged
        buscar.pitch1((jSlider3.getValue()));
    }//GEN-LAST:event_jSlider3StateChanged

    private void jSlider2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSlider2KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jSlider2KeyPressed

    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged
        double sw=(double)(jSlider2.getValue()/100);
        buscar.setPan1(sw);
    }//GEN-LAST:event_jSlider2StateChanged

    private void js10StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js10StateChanged
      escucha2.setEq(0,js10.getValue());
    }//GEN-LAST:event_js10StateChanged

    private void js10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_js10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_js10KeyPressed

    private void js11StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js11StateChanged
      escucha2.setEq(2,js11.getValue());
    }//GEN-LAST:event_js11StateChanged

    private void js12StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js12StateChanged
       escucha2.setEq(1,js12.getValue());
    }//GEN-LAST:event_js12StateChanged

    private void js13StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js13StateChanged
        escucha2.setEq(4,js13.getValue());
    }//GEN-LAST:event_js13StateChanged

    private void js14StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js14StateChanged
        escucha2.setEq(3,js14.getValue());
    }//GEN-LAST:event_js14StateChanged

    private void js15StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js15StateChanged
         escucha2.setEq(5,js15.getValue());
    }//GEN-LAST:event_js15StateChanged

    private void js16StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js16StateChanged
        escucha2.setEq(6,js16.getValue());
    }//GEN-LAST:event_js16StateChanged

    private void js17StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js17StateChanged
       escucha2.setEq(7,js17.getValue());
    }//GEN-LAST:event_js17StateChanged

    private void js18StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js18StateChanged
       escucha2.setEq(8,js18.getValue());
    }//GEN-LAST:event_js18StateChanged

    private void js19StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_js19StateChanged
         escucha2.setEq(9,js19.getValue());
    }//GEN-LAST:event_js19StateChanged

    private void normalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_normalActionPerformed
        valores(verd, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        if(verd==true)
        {
        preset2.setText("normal");}
        else
        {
          preset1.setText("normal");
        }
    }//GEN-LAST:event_normalActionPerformed

    private void FullBassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FullBassActionPerformed
     
       valores(verd, 80, 72, 64, 24, 0, -24, -40, -48, -64, -64);
        if(verd==true)
        {
        preset2.setText("Full Bass ");}
        else
        {
          preset1.setText("Full Bass");
        }
    }//GEN-LAST:event_FullBassActionPerformed

    private void FullBassTrebleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FullBassTrebleActionPerformed
      valores(verd, 80, 72, 64, 24, 0, 18, 72, 72, 72, 72);
        if(verd==true)
        {
        preset2.setText("Full Bass Treble");}
        else
        {
          preset1.setText("Full Bass Treble");
        }
    }//GEN-LAST:event_FullBassTrebleActionPerformed

    private void clasicalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clasicalActionPerformed
        valores(verd, 0, 0, 0, 0, 0, 0, -40, -40, -40, -48);
          if(verd==true)
        {
        preset2.setText("clasical ");}
        else
        {
          preset1.setText("clasical ");
        }
    }//GEN-LAST:event_clasicalActionPerformed

    private void DanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DanceActionPerformed
       valores(verd, 56,32 , 8, 0, 0, -24, -40, -40, 0, 0);
         if(verd==true)
        {
        preset2.setText("Dance ");}
        else
        {
          preset1.setText("Dance");
        }
    }//GEN-LAST:event_DanceActionPerformed

    private void ClubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClubActionPerformed
      valores(verd, 0, 0, 16, 40, 40, 40, 16, 0, 0, 0);
        if(verd==true)
        {
        preset2.setText("Club");}
        else
        {
          preset1.setText("Club");
        }
    }//GEN-LAST:event_ClubActionPerformed

    private void FullTrebleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FullTrebleActionPerformed
        valores(verd, -64, -64, -64, -32, 0, 18, 72, 72, 72, 72);
          if(verd==true)
        {
        preset2.setText("Full Treble ");}
        else
        {
          preset1.setText("Full Treble");
        }
    }//GEN-LAST:event_FullTrebleActionPerformed

    private void LaptopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaptopActionPerformed
        valores(verd, 64,56 , 32, -32, -16, 8, 48, 56, 72, 72);
          if(verd==true)
        {
        preset2.setText("Laptop");}
        else
        {
          preset1.setText("Laptop");
        }
    }//GEN-LAST:event_LaptopActionPerformed

    private void LiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LiveActionPerformed
        valores(verd, -24, 0, 32, 40, 40, 40, 16, 16, 8, 8);
          if(verd==true)
        {
        preset2.setText("Live");}
        else
        {
          preset1.setText("Live");
        }
    }//GEN-LAST:event_LiveActionPerformed

    private void PartyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PartyActionPerformed
        valores(verd,40 , 40, 0, 0, 0, 0, 0, 0, 40, 40);
          if(verd==true)
        {
        preset2.setText("Party ");}
        else
        {
          preset1.setText("Party");
        }
    }//GEN-LAST:event_PartyActionPerformed

    private void ReggaeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReggaeActionPerformed
       valores(verd, 0, 0, -8, -16, 8, 16, 16, 0, 0, 0);
         if(verd==true)
        {
        preset2.setText("Reggae");}
        else
        {
          preset1.setText("Reggae");
        }
    }//GEN-LAST:event_ReggaeActionPerformed

    private void RockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RockActionPerformed
        valores(verd, 32, 16, -16, -40, -16,16 , 40, 48, 48, 48);
          if(verd==true)
        {
        preset2.setText("Rock ");}
        else
        {
          preset1.setText("Rock");
        }
    }//GEN-LAST:event_RockActionPerformed

    private void TechnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TechnoActionPerformed
        valores(verd, 32, 16, -16, -40, -16,16 , 40, 48, 56,48 );
          if(verd==true)
        {
        preset2.setText("Techno");}
        else
        {
          preset1.setText("Techno");
        }
    }//GEN-LAST:event_TechnoActionPerformed

    private void preset1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_preset1MouseEntered
        verd=false;
    }//GEN-LAST:event_preset1MouseEntered

    private void preset2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_preset2MouseEntered
      verd=true;
    }//GEN-LAST:event_preset2MouseEntered

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    private void PopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PopActionPerformed
        valores(verd, -16,16, 32, 40, 32, -16, -24, -24, -24, -24);
          if(verd==true)
        {
        preset2.setText("Pop ");}
        else
        {
          preset1.setText("Pop");
        }
    }//GEN-LAST:event_PopActionPerformed

    private void JazzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JazzActionPerformed
        valores(verd, -16, 40, -64, 0, 64,56 , 48, 0, 0,0 );
          if(verd==true)
        {
        preset2.setText("Jazz ");}
        else
        {
          preset1.setText("Jazz");
        }
    }//GEN-LAST:event_JazzActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       lista=false;
        if(automixer==false){
            con=(int)(Math.random()*jTable1.getRowCount());
            posicion=con;
            play1d();
            automixer=true;
            volumenn.setValue(0);
            value();

            ImageIcon gr1=new ImageIcon( getClass().getResource("/dj/Imagenes/aleatorio1.png"));
            jButton7.setIcon(gr1);
            if(activar==true)
            {
                ImageIcon gr2=new ImageIcon( getClass().getResource("/dj/Imagenes/lista1.png"));
                jButton2.setIcon(gr2);
                activar=true;
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
         ImageIcon gr1;
        ImageIcon gr2;
        ImageIcon gr3;
        ImageIcon gr4;
        try{
            switch(jTabbedPane1.getSelectedIndex())
            {
                case 0: gr1=new ImageIcon( getClass().getResource("/dj/Imagenes/musica2.png"));
                gr2=new ImageIcon( getClass().getResource("/dj/Imagenes/bandeja.png"));
                gr3=new ImageIcon(getClass().getResource("/dj/imagenes/configuracion_1.png"));
                gr4=new ImageIcon(getClass().getResource("/dj/imagenes/camara2.png"));
                jTabbedPane1.setIconAt(0, gr1);
                jTabbedPane1.setIconAt(1, gr2);
                jTabbedPane1.setIconAt(2, gr3);
                jTabbedPane1.setIconAt(3, gr4);
                break;
                case 1: gr1=new ImageIcon( getClass().getResource("/dj/Imagenes/musica2.png"));
                gr2=new ImageIcon( getClass().getResource("/dj/Imagenes/bandeja1.png"));
                gr3=new ImageIcon(getClass().getResource("/dj/imagenes/configuracion_1.png"));
                gr4=new ImageIcon(getClass().getResource("/dj/imagenes/camara2.png"));
                jTabbedPane1.setIconAt(0, gr1);
                jTabbedPane1.setIconAt(1, gr2);
                jTabbedPane1.setIconAt(2, gr3);
                jTabbedPane1.setIconAt(3, gr4);
                break;
                case 2:
                gr1=new ImageIcon( getClass().getResource("/dj/Imagenes/musica2.png"));
                gr2=new ImageIcon( getClass().getResource("/dj/Imagenes/bandeja.png"));
                gr3=new ImageIcon(getClass().getResource("/dj/imagenes/configuracion2.png"));
                gr4=new ImageIcon(getClass().getResource("/dj/imagenes/camara2.png"));
                jTabbedPane1.setIconAt(0, gr1);
                jTabbedPane1.setIconAt(1, gr2);
                jTabbedPane1.setIconAt(2, gr3);
                jTabbedPane1.setIconAt(3, gr4);
                break;
                case 3:
                gr1=new ImageIcon( getClass().getResource("/dj/Imagenes/musica2.png"));
                gr2=new ImageIcon( getClass().getResource("/dj/Imagenes/bandeja.png"));
                gr3=new ImageIcon(getClass().getResource("/dj/imagenes/configuracion_1.png"));
                gr4=new ImageIcon(getClass().getResource("/dj/imagenes/camara.png"));
                jTabbedPane1.setIconAt(0, gr1);
                jTabbedPane1.setIconAt(1, gr2);
                jTabbedPane1.setIconAt(2, gr3);
                jTabbedPane1.setIconAt(3, gr4);

                break;
            }}catch(Exception ex)
            {

            }
        jPanel8.repaint();
        jPanel15.repaint();
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       jDialog1.setVisible(true);
       jDialog1.setSize(600,400);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
       jDialog2.setVisible(true);
       jDialog2.setSize(600,400);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        jDialog3.setVisible(true);
          jDialog3.setSize(415,150);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        jDialog4.setVisible(true);
        jDialog4.setSize(415, 150);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
           keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
           keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jDialog3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialog3KeyPressed
           keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jDialog3KeyPressed

    private void jProgressBar5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jProgressBar5KeyPressed
           keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jProgressBar5KeyPressed

    private void jSlider6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSlider6KeyPressed
           keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jSlider6KeyPressed

    private void jDialog4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialog4KeyPressed
           keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jDialog4KeyPressed

    private void jProgressBar6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jProgressBar6KeyPressed
           keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jProgressBar6KeyPressed

    private void jSlider7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSlider7KeyPressed
            keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jSlider7KeyPressed

    private void jLabel46KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel46KeyPressed
          keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jLabel46KeyPressed

    private void jLabel6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel6KeyPressed
            keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jLabel6KeyPressed

    private void jSlider7StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider7StateChanged
         buscar.adelantar1(jSlider7.getValue());
    }//GEN-LAST:event_jSlider7StateChanged

    private void jSlider6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider6StateChanged
        buscar.adelantar(jSlider6.getValue());
    }//GEN-LAST:event_jSlider6StateChanged

    private void jDialog1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialog1KeyPressed
       keyPressed(evt);setFocusable(true);  
    }//GEN-LAST:event_jDialog1KeyPressed

    private void jDialog2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialog2KeyPressed
        keyPressed(evt);setFocusable(true);  
    }//GEN-LAST:event_jDialog2KeyPressed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jButton7KeyPressed

    private void jTextPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPane1KeyPressed
       keyPressed(evt);setFocusable(true);
    }//GEN-LAST:event_jTextPane1KeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    evt.getWindow().addKeyListener(this);setFocusable(true);
    }//GEN-LAST:event_formWindowActivated

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked

    }//GEN-LAST:event_jPanel8MouseClicked

    private void jTabbedPane4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane4StateChanged
        jPanel8.repaint();
        jPanel15.repaint();
    }//GEN-LAST:event_jTabbedPane4StateChanged

    private void jPanel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseClicked
       x=(int)jPanel8.getMousePosition().getX();
      y=(int)jPanel8.getMousePosition().getY();
      jPanel8.repaint();
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel15MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ventanamixer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanamixer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanamixer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanamixer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            
                    
                
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JMenuItem AgregarCancion;
    javax.swing.JMenuItem Bandeja1;
    javax.swing.JMenuItem Bandeja2;
    javax.swing.JMenuItem Club;
    javax.swing.JMenuItem Dance;
    javax.swing.JMenuItem Eliminar;
    javax.swing.JPanel Escritorio;
    javax.swing.JMenuItem FullBass;
    javax.swing.JMenuItem FullBassTreble;
    javax.swing.JMenuItem FullTreble;
    javax.swing.JMenuItem Jazz;
    javax.swing.JMenuItem Laptop;
    javax.swing.JDialog Lista;
    javax.swing.JMenuItem Live;
    javax.swing.JComboBox MarcasDeAgua;
    javax.swing.JSlider Parlante;
    javax.swing.JMenuItem Party;
    javax.swing.JMenuItem Pop;
    javax.swing.JMenuItem Reggae;
    javax.swing.JMenuItem Rock;
    javax.swing.JMenuItem Techno;
    javax.swing.JButton antes1;
    javax.swing.JButton antes2;
    javax.swing.JMenuItem clasical;
    javax.swing.JButton jButton1;
    javax.swing.JButton jButton10;
    javax.swing.JButton jButton11;
    javax.swing.JButton jButton12;
    javax.swing.JButton jButton13;
    javax.swing.JButton jButton14;
    javax.swing.JButton jButton2;
    javax.swing.JButton jButton3;
    javax.swing.JButton jButton4;
    javax.swing.JButton jButton5;
    javax.swing.JButton jButton6;
    javax.swing.JButton jButton7;
    javax.swing.JButton jButton8;
    javax.swing.JButton jButton9;
    javax.swing.JComboBox jComboBox1;
    javax.swing.JComboBox jComboBox2;
    javax.swing.JComboBox jComboBox3;
    javax.swing.JDialog jDialog1;
    javax.swing.JDialog jDialog2;
    javax.swing.JDialog jDialog3;
    javax.swing.JDialog jDialog4;
    javax.swing.JLabel jLabel10;
    javax.swing.JLabel jLabel11;
    javax.swing.JLabel jLabel12;
    javax.swing.JLabel jLabel13;
    javax.swing.JLabel jLabel14;
    javax.swing.JLabel jLabel15;
    javax.swing.JLabel jLabel16;
    javax.swing.JLabel jLabel17;
    javax.swing.JLabel jLabel18;
    javax.swing.JLabel jLabel19;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel20;
    javax.swing.JLabel jLabel21;
    javax.swing.JLabel jLabel22;
    javax.swing.JLabel jLabel23;
    javax.swing.JLabel jLabel24;
    javax.swing.JLabel jLabel25;
    javax.swing.JLabel jLabel26;
    javax.swing.JLabel jLabel27;
    javax.swing.JLabel jLabel28;
    javax.swing.JLabel jLabel29;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel30;
    javax.swing.JLabel jLabel31;
    javax.swing.JLabel jLabel32;
    javax.swing.JLabel jLabel33;
    javax.swing.JLabel jLabel34;
    javax.swing.JLabel jLabel35;
    javax.swing.JLabel jLabel36;
    javax.swing.JLabel jLabel37;
    javax.swing.JLabel jLabel38;
    javax.swing.JLabel jLabel39;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel40;
    javax.swing.JLabel jLabel41;
    javax.swing.JLabel jLabel42;
    javax.swing.JLabel jLabel43;
    javax.swing.JLabel jLabel44;
    javax.swing.JLabel jLabel45;
    javax.swing.JLabel jLabel46;
    javax.swing.JLabel jLabel6;
    javax.swing.JLabel jLabel7;
    javax.swing.JLabel jLabel8;
    javax.swing.JLabel jLabel9;
    javax.swing.JMenuItem jMenuItem1;
    javax.swing.JMenuItem jMenuItem2;
    javax.swing.JPanel jPanel1;
    javax.swing.JPanel jPanel10;
    javax.swing.JPanel jPanel11;
    javax.swing.JPanel jPanel12;
    javax.swing.JPanel jPanel13;
    javax.swing.JPanel jPanel14;
    javax.swing.JPanel jPanel15;
    javax.swing.JPanel jPanel2;
    javax.swing.JPanel jPanel3;
    javax.swing.JPanel jPanel4;
    javax.swing.JPanel jPanel5;
    javax.swing.JPanel jPanel6;
    javax.swing.JPanel jPanel7;
    javax.swing.JPanel jPanel8;
    javax.swing.JPanel jPanel9;
    javax.swing.JPopupMenu jPopupMenu1;
    javax.swing.JPopupMenu jPopupMenu2;
    javax.swing.JPopupMenu jPopupMenu3;
    javax.swing.JPopupMenu jPopupMenu4;
    javax.swing.JSlider jProgressBar1;
    javax.swing.JSlider jProgressBar2;
    public transient javax.swing.JProgressBar jProgressBar3;
    public javax.swing.JProgressBar jProgressBar4;
    javax.swing.JProgressBar jProgressBar5;
    javax.swing.JProgressBar jProgressBar6;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JScrollPane jScrollPane2;
    javax.swing.JScrollPane jScrollPane3;
    javax.swing.JScrollPane jScrollPane4;
    javax.swing.JSlider jSlider1;
    javax.swing.JSlider jSlider2;
    javax.swing.JSlider jSlider3;
    javax.swing.JSlider jSlider4;
    javax.swing.JSlider jSlider5;
    javax.swing.JSlider jSlider6;
    javax.swing.JSlider jSlider7;
    javax.swing.JTabbedPane jTabbedPane1;
    javax.swing.JTabbedPane jTabbedPane2;
    javax.swing.JTabbedPane jTabbedPane4;
    javax.swing.JTabbedPane jTabbedPane5;
    javax.swing.JTable jTable1;
    javax.swing.JTextArea jTextArea1;
    javax.swing.JTextArea jTextArea2;
    javax.swing.JLabel jTextField1;
    javax.swing.JLabel jTextField2;
    javax.swing.JTextField jTextField3;
    javax.swing.JTextPane jTextPane1;
    javax.swing.JToggleButton jToggleButton1;
    javax.swing.JSlider js0;
    javax.swing.JSlider js1;
    javax.swing.JSlider js10;
    javax.swing.JSlider js11;
    javax.swing.JSlider js12;
    javax.swing.JSlider js13;
    javax.swing.JSlider js14;
    javax.swing.JSlider js15;
    javax.swing.JSlider js16;
    javax.swing.JSlider js17;
    javax.swing.JSlider js18;
    javax.swing.JSlider js19;
    javax.swing.JSlider js2;
    javax.swing.JSlider js3;
    javax.swing.JSlider js4;
    javax.swing.JSlider js5;
    javax.swing.JSlider js6;
    javax.swing.JSlider js7;
    javax.swing.JSlider js8;
    javax.swing.JSlider js9;
    javax.swing.JButton next1;
    javax.swing.JButton next2;
    javax.swing.JMenuItem normal;
    javax.swing.JButton play1;
    javax.swing.JToggleButton play2;
    javax.swing.JLabel preset1;
    javax.swing.JLabel preset2;
    javax.swing.JButton stop1;
    javax.swing.JButton stop2;
    javax.swing.JLabel ti;
    javax.swing.JLabel tir;
    javax.swing.JProgressBar volumen1;
    javax.swing.JProgressBar volumen2;
    javax.swing.JSlider volumenn;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {//evento para poder controlar el teclado
      if(e.VK_CONTROL==e.getKeyCode())
      { 
          swAlt=false;
          swShift=false;
      swcontrol=true;
      }
      if(e.VK_SHIFT==e.getKeyCode())
      {swAlt=false;
          swShift=true;
      swcontrol=false;
      }
      if(e.VK_ALT==e.getKeyCode())
      {
      swAlt=true;
      swShift=false;
      swcontrol=false;
      
      }   
        if(e.VK_RIGHT==e.getKeyCode())
    {    
       
    volumenn.setValue(volumenn.getValue()+1);
    value();
    }
    if(e.getKeyCode()==e.VK_LEFT)
    {
     
    volumenn.setValue(volumenn.getValue()-1);
    value();
    }
    if(cargarCanci==false)
    {jTable1.setRowSelectionInterval(0, 0);
        con=jTable1.getSelectedRow();
        i=jTable1.getSelectedRow();
        cargarCanci=true;
    }
    if(swcontrol==true)
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_F:
                antes();
        swcontrol=false;
                break;
            case KeyEvent.VK_G:
                play1d();
                swcontrol=false;
                break;
            case KeyEvent.VK_S:
                stopo1();
                swcontrol=false;
                break;
            case KeyEvent.VK_P:
                pausa1();swcontrol=false;
                break;
            case KeyEvent.VK_H:
                con=jTable1.getSelectedRow();
                siguiente1();
                swcontrol=false;
                break;
                case KeyEvent.VK_M:
                jTabbedPane1.setSelectedIndex(0);
                swcontrol=false;
                break;
                 case KeyEvent.VK_E:
                      swcontrol=false;
                jDialog1.setVisible(true);
                jDialog1.setSize(500,350);
                jDialog1.setTitle("Bandeja  1");
                  jDialog2.setVisible(true);
                jDialog2.setBounds(jDialog1.getX()+jDialog1.getWidth(), 0, 500, 350);
                jDialog2.setTitle("Bandeja  2");
            break;    
            case KeyEvent.VK_I:
                swShift=false;
                jDialog4.setVisible(true);
                jDialog4.setTitle("Bandeja 2");
                jDialog3.setVisible(true);
                jDialog3.setSize(400,150);
                jDialog3.setTitle("Bandeja  1");
                 jDialog4.setBounds(jDialog3.getX()+jDialog3.getWidth(), 0, 400, 150);
            break;
    }
    }
    if(swShift==true){
    switch(e.getKeyCode())
        {
            case KeyEvent.VK_F:
                antes2();
        swShift=false;
                break;
            case KeyEvent.VK_G:
                play2();
                swShift=false;
                break;
            case KeyEvent.VK_S:
                stopo2();
                swShift=false;
                break;
            case KeyEvent.VK_P:
                pausa2();
                swShift=false;
                break;
            case KeyEvent.VK_H:
               
                siguiente2();
                swShift=false;
                break;
            case KeyEvent.VK_M:
                jTabbedPane1.setSelectedIndex(0);
                swShift=false;
                break;
            case KeyEvent.VK_E:
                swShift=false;
                jDialog2.setVisible(true);
                jDialog2.setTitle("Bandeja 2");
                jDialog1.setVisible(true);
                jDialog1.setSize(500,350);
                jDialog1.setTitle("Bandeja  1");
                 jDialog2.setBounds(jDialog1.getX()+jDialog1.getWidth(), 0, 500, 350);
            break;
                case KeyEvent.VK_I:
                swShift=false;
                jDialog4.setVisible(true);
                jDialog4.setTitle("Bandeja 2");
                jDialog3.setVisible(true);
                jDialog3.setSize(400,150);
                jDialog3.setTitle("Bandeja  1");
                 jDialog4.setBounds(jDialog3.getX()+jDialog3.getWidth(), 0, 400, 150);
            break;
    }
    }
    if(swAlt==true)
    {
    switch(e.getKeyCode())
    {
        case KeyEvent.VK_NUMPAD1:
            jTabbedPane1.setSelectedIndex(1);
             jTabbedPane4.setSelectedIndex(0);
             swAlt = false;
            break;
            case KeyEvent.VK_NUMPAD2:
            jTabbedPane1.setSelectedIndex(1);
             jTabbedPane4.setSelectedIndex(1);
             swAlt=false;
            break;
                 case KeyEvent.VK_M:
            jTabbedPane1.setSelectedIndex(0);
            swAlt=false;
            break;
                     case KeyEvent.VK_1:
            jTabbedPane1.setSelectedIndex(1);
             jTabbedPane4.setSelectedIndex(0);
             swAlt=false;
            break;
            case KeyEvent.VK_2:
            jTabbedPane1.setSelectedIndex(1);
             jTabbedPane4.setSelectedIndex(1);
             swAlt=false;
            break;
                
    }
    }
    
    if((swShift==true ||swcontrol==true) &&e.getKeyCode()==e.VK_B)
    {
          try {
              cargar.buscar();
          } catch (IOException ex) {
              Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
          } catch (Exception ex) {
              Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
          }
    swShift=false;
    swcontrol=false;
    }
     if((swShift==true ||swcontrol==true) &&e.getKeyCode()==e.VK_Q)
    {
        System.exit(0);
   
    }
     
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
    
    }
public void value()
{
  volumen1.setValue(100-volumenn.getValue());
  volumen2.setValue(volumenn.getValue());
        try {int gainValue = volumen1.getValue();
            int gainValue1 = volumen2.getValue();
            int maxGain = volumenn.getMaximum();
            buscar.setVolumen((float) ((double) gainValue / (double) maxGain));
            buscar.setVolumen1((float) ((double) gainValue1 / (double) maxGain));
        } catch (BasicPlayerException ex) {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
}
  
private void play1d()
{  
   
  try {
            if (iseek==false) {
               
                try {
                    
                    jTextField1.setText(buscar.getname(con).replace(".mp3","").replace(".ogg","").replace(".wav", "").replace("{{","'"));
                    jLabel6.setText(jTextField1.getText());
                    cBand1.start();
                      buscar.playSong(con);
                      iseek=true;
                      value();
               
                   
                } catch (BasicPlayerException ex) {
                }
               
            } 
             
           
        if (iseek==true) {
                buscar.Continuar();
              cBand1.restart();
              
            }
            // TODO add your handling code here:
        } catch (Exception ex) {
       Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
  buscar.getPlayer().addBasicPlayerListener(escucha1);
  jProgressBar3.setMaximum(escucha1.taman());
  jProgressBar1.setMaximum(escucha1.taman());
   jProgressBar5.setMaximum(escucha1.taman());
     jSlider6.setMaximum(escucha1.taman());

}

private void antes(){
  
      
        try 
        {if(listaC==true)
            {
                if(cAnt==true)
            {
            posicion=posicion-1;
            }   
            else
            {
            posicion=posicion-2;
            cAnt=true;
            }
        
               if(posicion==-1)
           {
           posicion=listas.size()-1;
           } 
           String  trr=(String)listas.get(posicion);
           for ( int j=0; j < jTable1.getRowCount(); j++)
             {
                 if(buscar.getname(j).equalsIgnoreCase(trr)==true)//busco el nombre de la cancion
                {
                 try
                      {
                       
                       jTable1.setRowSelectionInterval(j, j);//selecciono la cancion
                        con=jTable1.getSelectedRow();
                        jTextField1.setText( buscar.getname(j).replace(".mp3", "").replace(".ogg", "").replace(".wav",""));//escribo el nombre en la bandeja
                        jLabel6.setText(jTextField1.getText());
                      }catch (Exception ex)
                     {
                          Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
                 }
               }
          
        
            }else{
             con=con-1;
             if(con==-1)
             {
             con=jTable1.getRowCount()-1;
             }
             posicion=con;
        }  buscar.playSong(con);
          try
             {
              buscar.Pausa();
             } 
          catch (Exception ex)
          {
              Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
          }
              cBand1.stop();
              s1=0;m1=0;
              jTable1.setRowSelectionInterval(con, con);
              jTextField1.setText("" + buscar.getname(con).replace(".mp3","").replace(".ogg","").replace(".wav", ""));
              jLabel6.setText(jTextField1.getText());
        }
        catch (BasicPlayerException ex)
        {
        }
 }
 private void stopo1()
 {
  try
        {
            
            s1=0;
            m1=0;
            iseek=false;
            buscar.Stop();
        // buscar.getPlayer().removeBasicPlayerListener(escucha1);
            jProgressBar1.setValue(0);
            cBand1.stop();
            ti.setText(h1+":"+m1+":"+s1);
            
        } catch (Exception ex) {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
  private void pausa1()
 {
     try {
            buscar.Pausa();
            cBand1.stop();
         
        } catch (Exception ex) {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
private void siguiente1()
 {

        
      try 
       {if(listaC==true)
       {
        
        
           if(posicion==listas.size())
           {
           posicion=0;
           } 
           String  trr=(String)listas.get(posicion);
           for ( int j=0; j < jTable1.getRowCount(); j++)
             {
                 if(buscar.getname(j).equalsIgnoreCase(trr)==true)//busco el nombre de la cancion
                {
                 try
                      {
                       
                       jTable1.setRowSelectionInterval(j, j);//selecciono la cancion
                        con=jTable1.getSelectedRow();
                        jTextField2.setText( buscar.getname(j));//escribo el nombre en la bandeja
                      }catch (Exception ex)
                     {
                          Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
                 }
               }
          
        posicion=posicion+1;
         
           
       }else{
            con=con+1;
            posicion=con;
            if(con==jTable1.getRowCount())
            {
            con=0;
            }
            
       }
            buscar.playSong(con);
        try 
         {
             buscar.Pausa();
         }
        catch (Exception ex)
        {
         Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
             cBand1.stop();
             s1=0;m1=0;
             jTable1.setRowSelectionInterval(con , con);
             jTextField1.setText("" + buscar.getname(con).replace(".mp3","").replace(".ogg","").replace(".wav",""));
             jLabel6.setText(jTextField1.getText());
       }
      catch (BasicPlayerException ex)
      {
      }
 }
 private void antes2()
 {
        
        cBand2.stop();
        m2=0;
        s2=0;
        try
        {
            if(listaC==true)
            {
              if(cAnt==true)
              {
            posicion--;
              }
              else
              {
               posicion=posicion-2;   
              cAnt=true;
              }
              if(posicion-1==-1)
           {
           posicion=listas.size()-1;
           } 
           String  trr=(String)listas.get(posicion);
           for ( int j=0; j < jTable1.getRowCount(); j++)
             {
                 if(buscar.getname(j).equalsIgnoreCase(trr)==true)//busco el nombre de la cancion
                {
                 try
                      {
                       
                       jTable1.setRowSelectionInterval(j, j);//selecciono la cancion
                        i=jTable1.getSelectedRow();
                        jTextField2.setText( buscar.getname(j).replace(".mp3","").replace(".ogg", "").replace(".wav",""));//escribo el nombre en la bandeja
                        jLabel46.setText(jTextField2.getText());
                      }catch (Exception ex)
                     {
                          Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
                 }
               }
          
         
            }
            else{
            i=i-1;
            
            if(i==-1)
            {
            i=jTable1.getRowCount()-1;
            }
            posicion=i;
            }
            buscar.playsSong(i);
       try {
           buscar.Pausa1();
       } catch (Exception ex) {
           Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
       }
            jTable1.setRowSelectionInterval(i, i);
           jTextField2.setText(buscar.getname(i).replace(".mp3","").replace(".ogg","").replace(".wav", ""));
                    jLabel46.setText(jTextField2.getText());
        } catch (BasicPlayerException ex) {
        }
 }
 private void stopo2() 
 {
     
      try 
      {
        i=i;
       cBand2.stop(); 
        h2=0;  
        m2=0;
        s2=0;
        
        buscar.Stop1();      
      //  buscar.getPlayer1().removeBasicPlayerListener(escucha2);
       jProgressBar2.setValue(0);
      
        tir.setText(h2+":"+m2+":"+s2);
        iseek1=false;
         
      } catch (Exception ex) {
          Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
      }
            
       
 }
 private void  pausa2()
 {
  try {
            buscar.Pausa1();
            cBand2.stop();
       
        } catch (Exception ex) {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        } 
 }
 private void play2()
 {  
   
      try {
            
            if (iseek1==false) {
             // buscar.Play1();
                       
                try {
                
                 
                     cBand2.start();
                        buscar.playsSong(i);
                    jTextField2.setText(buscar.getname(i).replace(".mp3","").replace(".ogg","").replace(".wav", "").replace("{{","'"));
                    jLabel46.setText(jTextField2.getText());
                    value();
                  
                } catch (BasicPlayerException ex) {
                }
                
              
             
                iseek1=true;
            } 
            
            if (iseek1==true) {
                buscar.Continuar1();
                cBand2.restart();
             
            }
           
        } catch (Exception ex) {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     jProgressBar2.setMaximum(escucha2.taman());
     jProgressBar4.setMaximum(escucha2.taman());
     jSlider7.setMaximum(escucha2.taman());
     jProgressBar6.setMaximum(escucha2.taman());
    
 }
 private void siguiente2()
 {
       
        try {
            if(listaC==true)
            {
          
              if(posicion==listas.size())
           {
           posicion=0;
           } 
           String  trr=(String)listas.get(posicion);
           for ( int j=0; j < jTable1.getRowCount(); j++)
             {
                 if(buscar.getname(j).equalsIgnoreCase(trr)==true)//busco el nombre de la cancion
                {
                 try
                      {
                       
                       jTable1.setRowSelectionInterval(j, j);//selecciono la cancion
                        i=jTable1.getSelectedRow();
                        jTextField2.setText( buscar.getname(j));//escribo el nombre en la bandeja
                      }catch (Exception ex)
                     {
                          Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
                 }
               }
           posicion=posicion+1;
          
            }else
            {    
            i=i+1;
            posicion=i;
            if(i==jTable1.getRowCount())
            {
            i=0;
            }
            }
            buscar.playsSong(i);
            try {
                buscar.Pausa1();
            } catch (Exception ex) {
                Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
            }
             cBand2.stop();m2=0;s2=0;
            jTable1.setRowSelectionInterval(i, i);
            jTextField2.setText(buscar.getname(i).replace(".mp3", "").replace(".ogg","").replace(".wav",""));
            jLabel46.setText(jTextField2.getText());
        } catch (BasicPlayerException ex) {
        }
 }
 private void buscarr()
 {
 String ttt = "";

        for ( int j=0; j < jTable1.getRowCount(); j++) {

            if ((buscar.getname(j).toLowerCase().contains(jTextField3.getText()) == true
                    || buscar.getname(j).contains(jTextField3.getText()) == true)&&jTextField3.getText().isEmpty()==false) {
                ttt = buscar.getname(j);
                jComboBox1.addItem(ttt);
            }
            
        }
        if (ttt =="")
        {
            JOptionPane.showMessageDialog(null, "No se encontro ningun tema con ese nombre / o autor");
        } else {
            Lista.setVisible(true);
            Lista.setSize(200, 300);

        }
 }
 private void cargarCancion(String trr)
 {
        
           s1=0;m1=0;
           cBand1.start();
           cBand1.stop();
        for (int j = 0; j < jTable1.getRowCount(); j++) 
        {
        
            System.out.println(trr);
          
      if(buscar.getname(j).equalsIgnoreCase(trr)==true)
      { try {
          stopo1();
         jTable1.setRowSelectionInterval(j, j);
         con=jTable1.getSelectedRow();
         jTextField1.setText( buscar.getname(j));
            
        } catch (Exception ex) {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
           
      }}
     
     
      Lista.dispose();
       
 }
 private void cargarCancion2(String trr)
 {
       m2=0;s2=0;
       cBand2.start();
       cBand2.stop();
       
       for (int j = 0; j < jTable1.getRowCount(); j++) 
        {
       
            System.out.println(trr);
           
      if(buscar.getname(j).equalsIgnoreCase(trr)==true)
      { try {
          stopo2();
            jTable1.setRowSelectionInterval(j, j);
            i=jTable1.getSelectedRow();
            jTextField2.setText( buscar.getname(j));
        } catch (Exception ex) {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
           
      }
        } 
          
          Lista.dispose();
      
 }

   public void valores(boolean t,int v0,int v1,int v2,int v3,int v4,int v5,int v6,int v7,int v8,int v9)
   {
       if(t==false){
   js0.setValue(v0);
       js1.setValue(v1);
       js2.setValue(v2);
       js3.setValue(v3);
       js4.setValue(v4);
       js5.setValue(v5);
       js6.setValue(v6);
       js7.setValue(v7);
       js8.setValue(v9);
       js9.setValue(v8);
        escucha1.setEq(0,js0.getValue());
        escucha1.setEq(1,js1.getValue());
        escucha1.setEq(2,js2.getValue());
        escucha1.setEq(3,js3.getValue());
        escucha1.setEq(4,js4.getValue());
        escucha1.setEq(5,js5.getValue());
        escucha1.setEq(6,js6.getValue());
        escucha1.setEq(7,js7.getValue());
        escucha1.setEq(8,js9.getValue());
        escucha1.setEq(9,js8.getValue()); 
   }
       if(t==true)
       {
       js10.setValue(v0);
       js11.setValue(v2);
       js12.setValue(v1);
       js13.setValue(v4);
       js14.setValue(v3);
       js15.setValue(v5);
       js16.setValue(v6);
       js17.setValue(v7);
       js18.setValue(v8);
       js19.setValue(v9);
       escucha2.setEq(0,js10.getValue());
        escucha2.setEq(1,js12.getValue());
        escucha2.setEq(2,js11.getValue());
        escucha2.setEq(3,js14.getValue());
        escucha2.setEq(4,js13.getValue());
        escucha2.setEq(5,js15.getValue());
        escucha2.setEq(6,js16.getValue());
        escucha2.setEq(7,js17.getValue());
        escucha2.setEq(8,js18.getValue());
        escucha2.setEq(9,js19.getValue()); 
       }
   }
 private void valor0()
 {
  volumenn.setValue(100);
   value();
 }
 private void valor1()
 {
   volumenn.setValue(0);
  value();
 }
 public void recibirSms(String s)
 {
     System.out.println(s);
     switch(s)
     {
         case "play1": play1d();break;
         case "stop1":stopo1();break;
         case "pausa1":pausa1();break;
         case "siguiente1":if(i==0){jTable1.setRowSelectionInterval(1, 1); i=jTable1.getSelectedRow();}siguiente1();break;
         case "anterior1" :if(i==0){jTable1.setRowSelectionInterval(1, 1); i=jTable1.getSelectedRow();} antes();break;   
         case "play2": play2();break;
         case "stop2":stopo2();break;
         case "pausa2":pausa2();break;
         case "siguiente2":if(con==0){jTable1.setRowSelectionInterval(1, 1); con=jTable1.getSelectedRow();}siguiente2();break;
         case "anterior2" :if(i==0){jTable1.setRowSelectionInterval(1, 1); i=jTable1.getSelectedRow();}antes2();break;       
     }
     if(s.contains("valor"))
     {String ss=s.replace("valor","");
     int vw=Integer.parseInt(ss);
     volumenn.setValue(vw);
         value();
     }
     
 }
    public int devolverCancion1()
    {   
      
    return con;
    }
      public int devolverCancion2()
    {   
       
    return i;
    }
 public class imagenes extends JPanel
    {
        public imagenes()
        {
            this.setSize(64,64);
        }
        @Override
        public void paint(Graphics g)
        {
            super.paint(g);
            Dimension heigh=getSize();
            
            Image image;
            Toolkit t1=Toolkit.getDefaultToolkit(); 
                image=t1.getImage(getClass().getResource("bandeja3.png"));
               //  ImageIcon img=new ImageIcon(image);
                 // g.drawImage(image ,0,0,this);
                 double r=0;
      
      if(x!=0&&y!=0)
      {
          r= Math.toRadians(jProgressBar3.getPercentComplete()*360-(x*360/y));
      }
      else{
      r= Math.toRadians(jProgressBar3.getPercentComplete()*360);
            System.out.println(r);
      }
       AffineTransform at=new AffineTransform();
    at.rotate(r, this.getWidth()/2,this.getHeight()/2); 
   ( (Graphics2D)g).setTransform(at); 
      image.setAccelerationPriority(1);
       g.drawImage(image, 0,0,this.getWidth(), this.getHeight(),this);
      
     }
           
           
        }
    public class imagenes1 extends JPanel
    {
        public imagenes1()
        {
            this.setSize(64,64);
        }
        @Override
        public void paint(Graphics g)
        {
            super.paint(g);
            Dimension heigh=getSize();
            
            Image image;
            Toolkit t1=Toolkit.getDefaultToolkit(); 
                image=t1.getImage(getClass().getResource("bandeja3.png"));
               //  ImageIcon img=new ImageIcon(image);
                 // g.drawImage(image ,0,0,this);
      
       double r = Math.toRadians(jProgressBar4.getPercentComplete()*360);
        AffineTransform at=new AffineTransform();
            System.out.println(r);
  
    at.rotate(r, this.getWidth()/2,this.getHeight()/2); 
   ( (Graphics2D)g).setTransform(at); 
      image.setAccelerationPriority(1);
      
   
     
      g.drawImage(image, 0,0,this.getWidth(), this.getHeight(),this);
      
     }
           
           
        }
}
