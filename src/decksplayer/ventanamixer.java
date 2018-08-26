package decksplayer;

import Funciones.Listener;
import acciones.Control;
import acciones.Buscador;
import BaseDatos.Json;
import Funciones.Bandeja;
import Funciones.ProgresoBandeja;
import Funciones.equalizador;
import imagenes.eqImage;
import imagenes.qrGenerator;
import interfaz.bandejaChica;
import interfaz.uiEqualizador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import java.awt.event.*;
import java.io.*;
import org.jvnet.substance.SubstanceLookAndFeel;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import org.json.JSONException;
import org.json.JSONObject;


public class ventanamixer extends javax.swing.JFrame implements KeyListener {

    int bpm;
    int pos;
    int cont;
    int value = 0;
    int tam = 0;
    int progreso = 0;
    boolean reg = false;
    public static  Control controles,controles1;
    private ImageIcon gr;
    int numero = 0;
    private boolean swcontrol;//bandera para evento de la tecla control
    private boolean swShift; //bandera para la tecla shift
    private boolean swAlt;//bandera para la tecla alt
    public boolean isa = false;
    private File f = new File("skin.sk"), fs = new File("watemark.sk");
    private RandomAccessFile flow, file;
    private int m1 = 0, m2 = 0, s1 = 0, s2 = 0, h1 = 0, h2 = 0;//variables relacionadas con el tiempo
    public VisorFotos.Album vs;
    public float[] equalizer;
    float[] eq = new float[32];
    public static Listener escucha1, escucha2;
    boolean n = false;//sirve para hacer una bandera cuando llamo a las 
    boolean verd;//bandera para saber en que bandeja ecualizar
    private Buscador cargar;//clase para cargar las canciones
    boolean automixer;//boolean  para mezclar en modo aleatorio  
   
    int ct;
    boolean cargarCanci;
    int ss1 = 0;
    int x, y;
    double porc1 = 0, porc2 = 0;
    int cant = 0;
    private Json json;
    public static ProgresoBandeja timerBandeja,timerBandeja2;
    public static Bandeja b1;
    public static Bandeja b2;
    public static JProgressBar auxBar;
    private boolean drag;
    private Thread ipService;
    private boolean servidorActivo;
    private String qrDireccion;
    private static int idTemaB1;
    private static int idTemaB2;
    private JSlider[] eq1,eq2;
    private equalizador equalAudio;
    private bandejaChica r1,r2;
    private equalizador eql1,eql2;
    private eqImage imageEq;
   public ventanamixer(Json json) throws Exception {
        initComponents();
        this.json=json;
        gr = new ImageIcon(getClass().getResource("/dj/Imagenes/Decksplayer.png"));//agrego el logo a una foto
        this.setIconImage(gr.getImage());//establezco el logo del programa
        this.setTitle("DECKSPLAYER" );//pongo el titulo
        qrGenerator qrGenerator1=new qrGenerator();
        qrGenerator1.setQrImage(getCurrentIp().toString().replace("/","ip:"));
        this.setLocationRelativeTo(null);//centro la ventana al medio
//      Lista.setLocationRelativeTo(null);//centro la ventana de busqueda al medio
        tir.setText(h2 + ":" + m2 + ":" + s2);//muestro hora, minutos y segundos
        ti.setText(h1 + ":" + m1 + ":" + s1);//muestro hora, minutos y segundos
        escucha1 = new Listener(ti);
        escucha2 = new Listener(tir);

        try {
            lookAndFeel look=new lookAndFeel();
            look.setSkin(f, fs, this);

        } catch (Exception ex) {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.cargar = new Buscador(json);

        this.controles = new Control(json);//
        this.controles1=new Control(json);

        this.jTable1.setModel( json.tableModel(jTextField3.getText()));//genero el modelo de la primer tabla
        this.controles.getPlayer();//obtengo la funcion para reproducir
        cont = volumenn.getValue();
        controles.getPlayer().addBasicPlayerListener(escucha1);
        controles1.getPlayer().addBasicPlayerListener(escucha2);
        System.out.println("Iniciando servidor\n");
        b1=new Bandeja(controles);
        b2=new Bandeja(controles1);
        eq1=new JSlider[10];
        eq2=new JSlider[10];
        equalAudio=new equalizador();
        
        
        JDialog d = new JDialog();

        timerBandeja=new ProgresoBandeja(1000,1,jProgressBar3,ti,escucha1,volumenn,jTextField1);
        timerBandeja2=new ProgresoBandeja(1000,2,jProgressBar4,tir,escucha2,volumenn,jTextField2);
        timerBandeja.pintarPanel();
        timerBandeja2.pintarPanel();
        jPanel8.add(timerBandeja);
        jPanel8.updateUI();
        jPanel15.add(timerBandeja2);
        jPanel15.updateUI();
/*         imageEq=new eqImage();
        imageEq.pintarPanel();
       jPanel3.add(imageEq);
       jPanel3.updateUI();*/

       
       
        ipService=new Thread(new Runnable() {
               @Override
               public void run() {
                   Servidor1 sv1=new Servidor1();
                 
               }
           });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        Bandeja1 = new javax.swing.JMenuItem();
        Bandeja2 = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        AgregarCancion = new javax.swing.JMenuItem();
        jLabel45 = new javax.swing.JLabel();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jDialog3 = new javax.swing.JDialog();
        jSlider6 = new javax.swing.JSlider();
        jProgressBar5 = new javax.swing.JProgressBar();
        jLabel6 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
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
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        Escritorio = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
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
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        qrIcon = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPequalizador = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        volumen2 = new javax.swing.JProgressBar();
        volumen1 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        volumenn = new javax.swing.JSlider();
        jToggleButton1 = new javax.swing.JToggleButton();
        jSlider5 = new javax.swing.JSlider();
        jSlider4 = new javax.swing.JSlider();
        jPanel15 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jProgressBar3 = new javax.swing.JProgressBar();
        jProgressBar4 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JSlider();
        jProgressBar1 = new javax.swing.JSlider();
        ti = new javax.swing.JLabel();
        tir = new javax.swing.JLabel();
        antes1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        stop1 = new javax.swing.JButton();
        play1 = new javax.swing.JButton();
        next1 = new javax.swing.JButton();
        antes2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        stop2 = new javax.swing.JButton();
        play2 = new javax.swing.JToggleButton();
        next2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

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
                .addContainerGap(83, Short.MAX_VALUE))
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/ecualizador.png"))); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jButton14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton14KeyPressed(evt);
            }
        });
        getContentPane().add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 110, 60, 40));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/ecualizador.png"))); // NOI18N
        jButton10.setBorder(null);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jButton10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton10KeyPressed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 60, 40));

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
        jTabbedPane1.setToolTipText("");
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
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.setDragEnabled(true);
        jTable1.setDropMode(javax.swing.DropMode.ON);
        jTable1.setOpaque(false);
        jTable1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jTable1MouseDragged(evt);
            }
        });
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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
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
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/carpeta.png"))); // NOI18N
        jButton1.setBorder(null);
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

        jTextField3.setVerifyInputWhenFocusTarget(false);
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
        jButton2.setBorder(null);
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
        jButton7.setBorder(null);
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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "nombre"
            }
        ));
        jScrollPane4.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(jButton7)
                                .addGap(54, 54, 54)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(117, 117, 117)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(230, 230, 230)
                                .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 250));

        jTabbedPane1.addTab("", new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/musica2_1.png")), jPanel3); // NOI18N

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

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/sirena.png"))); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jButton17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton17KeyPressed(evt);
            }
        });

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/alarma.png"))); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jButton18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton18KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jButton18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton17)
                .addContainerGap(378, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton18)
                    .addComponent(jButton17))
                .addContainerGap(184, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("efectos ", jPanel2);

        javax.swing.GroupLayout EscritorioLayout = new javax.swing.GroupLayout(Escritorio);
        Escritorio.setLayout(EscritorioLayout);
        EscritorioLayout.setHorizontalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EscritorioLayout.createSequentialGroup()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );
        EscritorioLayout.setVerticalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
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

        jTabbedPane1.addTab("", new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/bandeja.png")), jPanel1); // NOI18N

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

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "EmeraldDuskSkin", "FindingNemoSkin", "GreenMagicSkin", "MagmaSkin", "MangoSkin", "RavenGraphiteGlassSkin", "RavenGraphiteSkin", "RavenSkin" }));
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

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Skin");

        jLabel15.setText("Marca de agua");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MarcasDeAgua, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MarcasDeAgua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Informacion", jPanel6);

        jLabel18.setText("Estado:");

        jButton5.setText("Desactivado");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(qrIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(338, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(qrIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Servidor", jPanel4);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("", new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/configuracion_1.png")), jPanel9); // NOI18N

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 411, Short.MAX_VALUE)
                .addComponent(jButton12)
                .addGap(64, 64, 64))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(204, Short.MAX_VALUE)
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
            .addComponent(jTabbedPane5)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane1.addTab("", new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/camara2.png")), jPanel11); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 143, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPequalizadorLayout = new javax.swing.GroupLayout(jPequalizador);
        jPequalizador.setLayout(jPequalizadorLayout);
        jPequalizadorLayout.setHorizontalGroup(
            jPequalizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPequalizadorLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
        );
        jPequalizadorLayout.setVerticalGroup(
            jPequalizadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPequalizadorLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("equalizador", jPequalizador);

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
        jToggleButton1.setBorder(null);
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
        jSlider4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jSlider4KeyReleased(evt);
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
                        .addComponent(jTabbedPane1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jSlider5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(volumen2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(volumen1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addGap(20, 20, 20)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(volumenn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSlider5, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(volumen2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(25, 25, 25)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 149, 790, 340));

        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel15MouseClicked(evt);
            }
        });
        jPanel15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel15KeyPressed(evt);
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
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 70, 70));

        jPanel8.setPreferredSize(new java.awt.Dimension(64, 64));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });
        jPanel8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel8KeyPressed(evt);
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
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 64, 64));

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField1.setOpaque(false);
        jTextField1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jTextField1MouseDragged(evt);
            }
        });
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField1MouseEntered(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 180, -1));

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField2.setOpaque(false);
        jTextField2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jTextField2MouseDragged(evt);
            }
        });
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextField2MouseExited(evt);
            }
        });
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 180, -1));

        jProgressBar3.setBackground(new java.awt.Color(0, 102, 153));
        jProgressBar3.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar3.setComponentPopupMenu(jPopupMenu3);
        jProgressBar3.setStringPainted(true);
        getContentPane().add(jProgressBar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 180, 60));

        jProgressBar4.setComponentPopupMenu(jPopupMenu4);
        jProgressBar4.setStringPainted(true);
        getContentPane().add(jProgressBar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 180, 60));

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
        getContentPane().add(jProgressBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 180, 30));

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
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 180, 30));

        ti.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        ti.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ti.setText("0:0:0");
        getContentPane().add(ti, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 60, 20));

        tir.setFont(new java.awt.Font("Trebuchet MS", 0, 10)); // NOI18N
        tir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tir.setText("0:0:0");
        tir.setToolTipText("");
        getContentPane().add(tir, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, 60, 20));

        antes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/anterior_1.png"))); // NOI18N
        antes1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        antes1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        antes1.setMaximumSize(new java.awt.Dimension(38, 38));
        antes1.setMinimumSize(new java.awt.Dimension(38, 38));
        antes1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/anterior_1.png"))); // NOI18N
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
        getContentPane().add(antes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 38, 38));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/pausa.png"))); // NOI18N
        jButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton3.setMaximumSize(new java.awt.Dimension(38, 38));
        jButton3.setMinimumSize(new java.awt.Dimension(38, 38));
        jButton3.setPreferredSize(new java.awt.Dimension(38, 38));
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
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 38, 38));

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
        getContentPane().add(stop1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 38, 38));

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
        getContentPane().add(play1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 38, 38));

        next1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dj/Imagenes/siguiente_1.png"))); // NOI18N
        next1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        next1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        next1.setMaximumSize(new java.awt.Dimension(38, 38));
        next1.setMinimumSize(new java.awt.Dimension(38, 38));
        next1.setPreferredSize(new java.awt.Dimension(38, 38));
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
        getContentPane().add(next1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 38, 38));

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
        getContentPane().add(antes2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 38, 38));

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
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, 38, 38));

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
        getContentPane().add(stop2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 38, 38));

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
        getContentPane().add(play2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 38, 38));

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
        getContentPane().add(next2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 110, 38, 38));

        jLabel1.setText("Bpm :0");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 60, 20));

        jLabel5.setText("Bpm :0");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 60, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        System.exit(0);//salgo del programa
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void volumennKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_volumennKeyPressed
        keyPressed(evt);
        setFocusable(true);//evento para cuando se aprietan las teclas
    }//GEN-LAST:event_volumennKeyPressed

    private void volumennKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_volumennKeyTyped
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_volumennKeyTyped

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_formKeyPressed

    private void jPanel14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel14KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jPanel14KeyPressed

    private void volumennStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_volumennStateChanged

        volumenn.setValue(volumenn.getValue());
//establezco el volumen
        value();//metodo para cambiar el volumen
    }//GEN-LAST:event_volumennStateChanged

    private void jTabbedPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane1KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jTabbedPane1KeyPressed

    private void jTabbedPane4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane4KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jTabbedPane4KeyPressed

    private void play2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_play2KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_play2KeyPressed

    private void play2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_play2ActionPerformed

        actionPlay(1);
       value();

    }//GEN-LAST:event_play2ActionPerformed

    private void play2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_play2StateChanged

    }//GEN-LAST:event_play2StateChanged

    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jButton4KeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        actionPausa(1);
    
    }//GEN-LAST:event_jButton4ActionPerformed

    private void stop2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stop2KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_stop2KeyPressed

    private void stop2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stop2ActionPerformed
        actionStop(1);
      
    }//GEN-LAST:event_stop2ActionPerformed

    private void stop2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stop2MouseClicked


    }//GEN-LAST:event_stop2MouseClicked

    private void next2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_next2KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_next2KeyPressed

    private void next2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next2ActionPerformed
        siguiente(1);
     

    }//GEN-LAST:event_next2ActionPerformed

    private void antes2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_antes2KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_antes2KeyPressed

    private void antes2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_antes2ActionPerformed
    anterior(1);
 
    }//GEN-LAST:event_antes2ActionPerformed

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jPanel2KeyPressed

    private void jTabbedPane2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane2KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jTabbedPane2KeyPressed

    private void jPanel6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel6KeyPressed
        keyPressed(evt);
        setFocusable(true);   // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6KeyPressed

    private void jTextArea2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea2KeyPressed
        keyPressed(evt);
        setFocusable(true);   // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea2KeyPressed

    private void jTextArea1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyPressed
        keyPressed(evt);
        setFocusable(true);    // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea1KeyPressed

    private void jPanel10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel10KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jPanel10KeyPressed

    private void jButton9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton9KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jButton9KeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        //codigo para generar el skin y la marca de agua para el programa
        try {

            SwingUtilities.updateComponentTreeUI(this);
           
            
            flow = new RandomAccessFile(f, "rw");
            flow.writeUTF((String) jComboBox2.getSelectedItem());
            flow.close();
            file = new RandomAccessFile(fs, "rw");
            file.writeUTF((String) MarcasDeAgua.getSelectedItem());
            file.close();

            SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin." + (String) jComboBox2.getSelectedItem());
            SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark." + (String) MarcasDeAgua.getSelectedItem());

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jComboBox2KeyPressed

    private void jComboBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseClicked

    }//GEN-LAST:event_jComboBox2MouseClicked

    private void jPanel5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel5KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jPanel5KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            //buscar los archivos
            // controles.buscarArchivo();
            // BuscadorArchivos bbs=new BuscadorArchivos();
            cargar.buscar();
            jTable1.removeAll();
              jTable1.setModel(json.tableModel(jTextField3.getText()));
          //  jTable1.setModel(cargar.devolverModelo(""));
        } catch (Exception ex) {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        pos=jTable1.getSelectedRow();
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
       // json.getCanciones(jTextField3.getText());
        
     
      
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void Bandeja1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bandeja1ActionPerformed
        b1.setPosition(jTable1.getSelectedRow());
        setNombre(0);
    }//GEN-LAST:event_Bandeja1ActionPerformed

    private void Bandeja2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bandeja2ActionPerformed
         b2.setPosition(jTable1.getSelectedRow());
        setNombre(1);
    }//GEN-LAST:event_Bandeja2ActionPerformed

    private void jPopupMenu1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPopupMenu1KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jPopupMenu1KeyPressed

    private void jTextField3InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTextField3InputMethodTextChanged
 
    }//GEN-LAST:event_jTextField3InputMethodTextChanged

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased

         try {
           
            jTable1.setModel(json.tableModel(jTextField3.getText()));
        } catch (JSONException ex) {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTable1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseExited

    private void jTable1ComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable1ComponentMoved

    }//GEN-LAST:event_jTable1ComponentMoved

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed

        int k = jTable1.getSelectedRow();
        cargar.objetos().remove(k);
        json.guardarJson();
        try {
            jTable1.setModel(json.tableModel(jTextField3.getText()));
        } catch (JSONException ex) {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EliminarActionPerformed

    private void jSlider4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider4StateChanged
        try {
            controles.setVolumen(controles.getPlayer(),(float) jSlider4.getValue() / jSlider4.getMaximum());
            volumen1.setValue(jSlider4.getValue());
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jSlider4StateChanged

    private void jSlider5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider5StateChanged
        try {
            controles1.setVolumen(controles1.getPlayer(),(float) jSlider5.getValue() / jSlider5.getMaximum());
            volumen2.setValue(jSlider5.getValue());
        } catch (Exception ex) {

        }
    }//GEN-LAST:event_jSlider5StateChanged

    private void jSlider5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSlider5KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jSlider5KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jButton2KeyPressed

    private void AgregarCancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarCancionActionPerformed

    }//GEN-LAST:event_AgregarCancionActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed

    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void AgregarCancionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AgregarCancionKeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_AgregarCancionKeyPressed

    private void EliminarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EliminarKeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_EliminarKeyPressed

    private void Bandeja2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Bandeja2KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_Bandeja2KeyPressed

    private void Bandeja1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Bandeja1KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_Bandeja1KeyPressed

    private void jComboBox3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox3KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jComboBox3KeyPressed

    private void jProgressBar2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jProgressBar2StateChanged

        controles1.adelantar(controles1.getPlayer(),jProgressBar2.getValue());
    }//GEN-LAST:event_jProgressBar2StateChanged

    private void jProgressBar2VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jProgressBar2VetoableChange
        controles1.adelantar(controles1.getPlayer(),jProgressBar2.getValue());
    }//GEN-LAST:event_jProgressBar2VetoableChange

    private void jProgressBar2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jProgressBar2PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jProgressBar2PropertyChange

    private void jTabbedPane5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane5KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jTabbedPane5KeyPressed

    private void jPanel7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel7KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jPanel7KeyPressed

    private void jButton12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton12KeyPressed
        keyPressed(evt);
        setFocusable(true);  // TODO add your handling code here:
    }//GEN-LAST:event_jButton12KeyPressed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        //cierro el programa para ver fotos
        vs.setVisible(false);
        vs.dispose();
        n = false;
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton11KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jButton11KeyPressed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        //llamo al programa para ver fotos

        if (n == false) {
            vs = new VisorFotos.Album();
            vs.setVisible(true);
            vs.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
            n = true;
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        timerBandeja.setAutomixer(true);
        timerBandeja2.setAutomixer(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged

        jPanel8.repaint();
        jPanel15.repaint();
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
uiEqualizador ui=new uiEqualizador(this, false);
      ui.setEscucha(escucha2);
      ui.setControl(controles1);
      ui.setVisible(true);       
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        jDialog3.setVisible(true);
        jDialog3.setSize(415, 150);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
      
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jDialog3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDialog3KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jDialog3KeyPressed

    private void jProgressBar5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jProgressBar5KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jProgressBar5KeyPressed

    private void jSlider6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSlider6KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jSlider6KeyPressed

    private void jLabel6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel6KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jLabel6KeyPressed

    private void jSlider6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider6StateChanged
        controles.adelantar(controles.getPlayer(),jSlider6.getValue());
    }//GEN-LAST:event_jSlider6StateChanged

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jButton7KeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        evt.getWindow().addKeyListener(this);
        setFocusable(true);
    }//GEN-LAST:event_formWindowActivated

    private void jTabbedPane4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane4StateChanged
        jPanel8.repaint();
        jPanel15.repaint();
    }//GEN-LAST:event_jTabbedPane4StateChanged

    private void jPanel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseClicked
        x = (int) jPanel8.getMousePosition().getX();
        y = (int) jPanel8.getMousePosition().getY();
        jPanel8.repaint();
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel15MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed

//      jPanel1.setTransferHandler(new TransferHandler(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), jTable1.getSelectedRow()).toString()));
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        sirenas(2);
// TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        sirenas(3);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton18KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton18KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jButton18KeyPressed

    private void jButton17KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton17KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jButton17KeyPressed

    private void jPanel15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel15KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jPanel15KeyPressed

    private void jButton14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton14KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jButton14KeyPressed

    private void jTable1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseDragged

        jTextField1.setEditable(true);
        jTextField2.setEditable(true);
        jTextField3.setEditable(false);
        drag=true;
        if(b1.getEstado()==1)
        {
            json.guardarSiguiente(b1.getPos(),jTable1.getSelectedRow());
               }
        else if(b2.getEstado()==1)
        {
             json.guardarSiguiente(b2.getPos(),jTable1.getSelectedRow());
        }
      


    }//GEN-LAST:event_jTable1MouseDragged

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseDragged
      
        // jTextField2.setText(jTextField2.getText().replace(".mp3","").replace(".ogg","").replace(".WAV",""));
    }//GEN-LAST:event_jTextField2MouseDragged

    private void jTextField2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseEntered
    if(drag==true)
        {
        if(b2.getEstado()==1)
        {
        int select=JOptionPane.showConfirmDialog(this,"Quiere cambiar el tema");
        if(select==JOptionPane.OK_OPTION)
        {
           b2.setPosition(pos);
           b2.stop();
          
        }
        }
        else
        {
                   b2.setPosition(pos);

        }
        drag=false;
        jTextField3.setEditable(true);
        }
    }//GEN-LAST:event_jTextField2MouseEntered

    private void jTextField2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2MouseExited

    private void jSlider4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSlider4KeyReleased
        keyPressed(evt);
        setFocusable(true);
// TODO add your handling code here:
    }//GEN-LAST:event_jSlider4KeyReleased

    private void jPanel8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel8KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jPanel8KeyPressed

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked

    }//GEN-LAST:event_jPanel8MouseClicked

    private void jProgressBar1VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jProgressBar1VetoableChange
        controles.adelantar(controles.getPlayer(),jProgressBar1.getValue());
    }//GEN-LAST:event_jProgressBar1VetoableChange

    private void jProgressBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jProgressBar1MouseClicked

    }//GEN-LAST:event_jProgressBar1MouseClicked

    private void jProgressBar1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jProgressBar1StateChanged
        controles.adelantar(controles.getPlayer(),jProgressBar1.getValue());
    }//GEN-LAST:event_jProgressBar1StateChanged

    private void next1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_next1KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_next1KeyPressed

    private void next1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next1ActionPerformed
        siguiente(0);
    }//GEN-LAST:event_next1ActionPerformed

    private void next1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_next1MousePressed

    }//GEN-LAST:event_next1MousePressed

    private void jButton10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton10KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jButton10KeyPressed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
      uiEqualizador ui=new uiEqualizador(this, false);
      ui.setEscucha(escucha1);
      ui.setControl(controles);
      ui.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void antes1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_antes1KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_antes1KeyPressed

    private void antes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_antes1ActionPerformed
        anterior(0);
    }//GEN-LAST:event_antes1ActionPerformed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        actionPausa(0);
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void play1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_play1KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_play1KeyPressed

    private void play1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_play1ActionPerformed
        actionPlay(0);
        value();
       
        
    }//GEN-LAST:event_play1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        keyPressed(evt);
        setFocusable(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseEntered
        if(drag==true)
        {
        if(b1.getEstado()==1)
        {
        int select=JOptionPane.showConfirmDialog(this,"Quiere cambiar el tema");
        if(select==JOptionPane.OK_OPTION)
        {
           b1.setPosition(pos);
            actionStop(0);
           
        }
        }
        else
        {
              b1.setPosition(pos);
        }
        
        drag=false;
        jTextField3.setEditable(true);
        }
    }//GEN-LAST:event_jTextField1MouseEntered

    private void jTextField1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseDragged
     
    }//GEN-LAST:event_jTextField1MouseDragged

    private void stop1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stop1KeyPressed
        keyPressed(evt);
        setFocusable(true);
    }//GEN-LAST:event_stop1KeyPressed

    private void stop1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stop1ActionPerformed
        actionStop(0);
        //se encarga de la funcion stop del programa boton de la bandeja 1
    }//GEN-LAST:event_stop1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      if(!servidorActivo)
        {  //ipService.resume();
           try{
            ipService.start();
           }catch(Exception ex)
           {
           ipService.resume();
           }        
        servidorActivo=true;
            jButton5.setText("Activado");
        }
        else
        {  ipService.suspend();
        servidorActivo=false;
        jButton5.setText("Desactivado");

        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
    javax.swing.JMenuItem Eliminar;
    javax.swing.JPanel Escritorio;
    javax.swing.JComboBox MarcasDeAgua;
    javax.swing.JButton antes1;
    javax.swing.JButton antes2;
    javax.swing.JButton jButton1;
    javax.swing.JButton jButton10;
    javax.swing.JButton jButton11;
    javax.swing.JButton jButton12;
    javax.swing.JButton jButton14;
    javax.swing.JButton jButton17;
    javax.swing.JButton jButton18;
    javax.swing.JButton jButton2;
    javax.swing.JButton jButton3;
    javax.swing.JButton jButton4;
    javax.swing.JButton jButton5;
    javax.swing.JButton jButton7;
    javax.swing.JButton jButton9;
    javax.swing.JComboBox jComboBox2;
    javax.swing.JComboBox jComboBox3;
    javax.swing.JDialog jDialog3;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel14;
    javax.swing.JLabel jLabel15;
    javax.swing.JLabel jLabel18;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel45;
    javax.swing.JLabel jLabel5;
    javax.swing.JLabel jLabel6;
    javax.swing.JLabel jLabel8;
    javax.swing.JLabel jLabel9;
    javax.swing.JMenuItem jMenuItem1;
    javax.swing.JMenuItem jMenuItem2;
    javax.swing.JPanel jPanel1;
    javax.swing.JPanel jPanel10;
    javax.swing.JPanel jPanel11;
    private static javax.swing.JPanel jPanel13;
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
    public static javax.swing.JPanel jPequalizador;
    javax.swing.JPopupMenu jPopupMenu1;
    javax.swing.JPopupMenu jPopupMenu3;
    javax.swing.JPopupMenu jPopupMenu4;
    javax.swing.JSlider jProgressBar1;
    javax.swing.JSlider jProgressBar2;
    public transient javax.swing.JProgressBar jProgressBar3;
    public javax.swing.JProgressBar jProgressBar4;
    javax.swing.JProgressBar jProgressBar5;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JScrollPane jScrollPane2;
    javax.swing.JScrollPane jScrollPane3;
    javax.swing.JScrollPane jScrollPane4;
    javax.swing.JSlider jSlider4;
    javax.swing.JSlider jSlider5;
    javax.swing.JSlider jSlider6;
    javax.swing.JTabbedPane jTabbedPane1;
    javax.swing.JTabbedPane jTabbedPane2;
    javax.swing.JTabbedPane jTabbedPane4;
    javax.swing.JTabbedPane jTabbedPane5;
    javax.swing.JTable jTable1;
    javax.swing.JTable jTable2;
    javax.swing.JTextArea jTextArea1;
    javax.swing.JTextArea jTextArea2;
    javax.swing.JTextField jTextField1;
    javax.swing.JTextField jTextField2;
    javax.swing.JTextField jTextField3;
    javax.swing.JToggleButton jToggleButton1;
    javax.swing.JButton next1;
    javax.swing.JButton next2;
    javax.swing.JButton play1;
    javax.swing.JToggleButton play2;
    static javax.swing.JLabel qrIcon;
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
        if (e.VK_CONTROL == e.getKeyCode()) {
            swAlt = false;
            swShift = false;
            swcontrol = true;
        }
        if (e.VK_SHIFT == e.getKeyCode()) {
            swAlt = false;
            swShift = true;
            swcontrol = false;
        }
        if (e.VK_ALT == e.getKeyCode()) {
            swAlt = true;
            swShift = false;
            swcontrol = false;

        }
        if (e.VK_RIGHT == e.getKeyCode()) {

            volumenn.setValue(volumenn.getValue() + 1);
            value();
        }
        if (e.getKeyCode() == e.VK_LEFT) {

            volumenn.setValue(volumenn.getValue() - 1);
            value();
        }
      
        if (swcontrol == true) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_F:
                   
                    swcontrol = false;
                    break;
                case KeyEvent.VK_G:
                  
                    swcontrol = false;
                    break;
                case KeyEvent.VK_S:
               
                    swcontrol = false;
                    break;
                case KeyEvent.VK_P:
                   
                    swcontrol = false;
                    break;
                case KeyEvent.VK_H:
                  
                 
                    swcontrol = false;
                    break;
                case KeyEvent.VK_M:
                    jTabbedPane1.setSelectedIndex(0);
                    swcontrol = false;
                    break;
                case KeyEvent.VK_E:
                    swcontrol = false;
                   //ver aca
                    break;
                case KeyEvent.VK_I:
                    swShift = false;
                    //ver aca
                    break;
            }
        }
        if (swShift == true) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_F:
                                       swShift = false;
                    break;
                case KeyEvent.VK_G:
                    
                    swShift = false;
                    break;
                case KeyEvent.VK_S:
                   
                    swShift = false;
                    break;
                case KeyEvent.VK_P:
                  
                    swShift = false;
                    break;
                case KeyEvent.VK_H:

                  
                    swShift = false;
                    break;
                case KeyEvent.VK_M:
                    jTabbedPane1.setSelectedIndex(0);
                    swShift = false;
                    break;
                case KeyEvent.VK_E:
                    swShift = false;
                    //ver aca
                    break;
                case KeyEvent.VK_I:
                    swShift = false;
                //ver aca
                    break;
            }
        }
        if (swAlt == true) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_NUMPAD1:
                    jTabbedPane1.setSelectedIndex(1);
                    jTabbedPane4.setSelectedIndex(0);
                    swAlt = false;
                    break;
                case KeyEvent.VK_NUMPAD2:
                    jTabbedPane1.setSelectedIndex(1);
                    jTabbedPane4.setSelectedIndex(1);
                    swAlt = false;
                    break;
                case KeyEvent.VK_M:
                    jTabbedPane1.setSelectedIndex(0);
                    swAlt = false;
                    break;
                case KeyEvent.VK_1:
                    jTabbedPane1.setSelectedIndex(1);
                    jTabbedPane4.setSelectedIndex(0);
                    swAlt = false;
                    break;

            }
        }

        if ((swShift == true || swcontrol == true) && e.getKeyCode() == e.VK_B) {
            try {
                cargar.buscar();
            } catch (IOException ex) {
                Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
            }
            swShift = false;
            swcontrol = false;
        }
        if ((swShift == true || swcontrol == true) && e.getKeyCode() == e.VK_Q) {
            System.exit(0);

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void value() {
        volumen1.setValue(100 - volumenn.getValue());
        volumen2.setValue(volumenn.getValue());
        try {
            int gainValue = volumen1.getValue();
            int gainValue1 = volumen2.getValue();
            int maxGain = volumenn.getMaximum();
            controles.setVolumen(controles.getPlayer(),(float) ((double) gainValue / (double) maxGain));
            controles1.setVolumen(controles1.getPlayer(),(float) ((double) gainValue1 / (double) maxGain));
        } catch (BasicPlayerException ex) {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
  
//ecualizado
   
//funciones del control remoto

    private void valor0() {
        volumenn.setValue(100);
        value();
    }

    private void valor1() {
        volumenn.setValue(0);
        value();
    }
    //funciones del control remotoq

    public void recibirSms(String s) {
       // System.out.println(s);
        switch (s) {
            case "play1":
                actionPlay(0);
                break;
            case "stop1":
                actionStop(0);
                break;
            case "pausa1":
                actionPausa(0);
                
                break;
            case "siguiente1":
                siguiente(0);
                actionStop(0);
                
                break;
            case "anterior1":
                anterior(0);
                actionStop(0);
               
                break;
            case "play2":
                actionPlay(1);
                break;
            case "stop2":
                actionStop(1);
                break;
            case "pausa2":
                 actionPausa(1);
                break;
            case "siguiente2":
                 siguiente(1);
                 actionStop(1);
              
                break;
            case "anterior2":
                 anterior(1);
                 actionStop(1);
               
                break;
          
        }
        if (s.contains("valor")) {
            String ss = s.replace("valor", "");
            int vw = Integer.parseInt(ss);
            volumenn.setValue(vw);
            value();
        }
        if(s.contains("filtro"))
        {
        
                         try {
           jTextField3.setText(s.replaceFirst("filtro",""));
            jTable1.setModel(json.tableModel(jTextField3.getText()));
        } catch (JSONException ex) {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    }

  

    public String devolvernombre1() {

        return jTextField3.getText();
    }

    public String devolvernombre2() {

        return jTextField3.getText();
    }
    //funciones para dibujar en pantalla las bandejas

    

    //efectos
    private void sirenas(int n) {
        try {
            controles.eff(n);        // TODO add your handling code here:
        } catch (BasicPlayerException ex) {
            Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



   
    public ArrayList<JSONObject> canciones()
    {
     return cargar.objetos();
    }
    public Buscador devolverBuscador()
    {
    return cargar;
    }
    private void siguiente(int tipo)
    {
      switch (tipo)
      { case 0:     
        int n=b1.getPos();
            n=b1.siguiente(jTable1.getSelectedRow());
            jTable1.setRowSelectionInterval(n, n);
            setNombre(0);
            break;
      case  1:
        int n1=b2.getPos();
            n1=b2.siguiente(jTable1.getSelectedRow());
            jTable1.setRowSelectionInterval(n1, n1);  
            setNombre(1);
          break;
      }
    }
    private void anterior(int tipo)
    {
      switch (tipo)
      { case 0:     
        int n=b1.getPos();
            n=b1.anterior(jTable1.getSelectedRow());
             jTable1.setRowSelectionInterval(n, n);
             setNombre(0);
            break;
      case  1:
        int n1=b2.getPos();
            n1=b2.anterior(jTable1.getSelectedRow());
            jTable1.setRowSelectionInterval(n1, n1);  
            setNombre(1);
          break;
      }
     
    }
    
    private void setNombre(int tipo)
    {
    switch(tipo)
    {
        case 0:
             {
          try {
              jTextField1.setText(b1.devolverNombre(jTextField3.getText()));
          } catch (Exception ex) {
              Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
            break;
        case 1:
             {
          try {
              jTextField2.setText(b2.devolverNombre(jTextField3.getText()));
          } catch (Exception ex) {
              Logger.getLogger(ventanamixer.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
             break;
    }
    }
    private void actionPlay(int tipo)
    {
        switch(tipo){
            case 0:
                setNombre(0);
           
       
            
               
                b1.play(jTextField3.getText());
                controles.getPlayer().addBasicPlayerListener(escucha1);
                 jProgressBar1.setMaximum(escucha1.taman());
                jProgressBar3.setMaximum(escucha1.taman());
                jProgressBar3.setValue(escucha1.progresos());
                jProgressBar1.setValue(escucha1.progresos());
                timerBandeja.start();
             
              
                break;
            case 1:
            b2.play(jTextField3.getText());
                setNombre(1);
             controles1.getPlayer().addBasicPlayerListener(escucha2);    
             jProgressBar2.setMaximum(escucha2.taman());
             jProgressBar4.setMaximum(escucha2.taman());
             jProgressBar4.setValue(escucha2.progresos());
              jProgressBar2.setValue(escucha2.progresos());
            timerBandeja2.start();
                break;
        }
    }
    private void actionStop(int tipo)
    {
      switch(tipo)
      {
          case 0:
              b1.stop();
              jProgressBar3.setValue(0);
              jProgressBar1.setValue(0);
              timerBandeja.stop();
              
              

              break;
          case 1:
              b2.stop();
              jProgressBar4.setValue(0);
              jProgressBar2.setValue(0);
              timerBandeja2.stop();
              break;
      }
      
    }
   private void actionPausa(int tipo)
   {
   switch(tipo)
   {
       case 0:
           b1.pausa(0);
        timerBandeja.stop();
        break;
       case 1:
           b2.pausa(1);
        timerBandeja2.stop();
        break;
   }
   }
   public static JLabel getQrIcon()
   {
   return qrIcon;
   }
   
    public InetAddress getCurrentIp() {
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
  
}
