/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poo.programa2.GUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import poo.programa2.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Daniel Granados Retana, carné 2022104692, y Diego Granados Retana, carné 2022158363
 */
public class JugarFrame extends javax.swing.JFrame {
    private boolean started = false; // indica si ya empezó el juego.
    boolean win = false; // indica si ya se ganó el juego
    private Juego juego; // se usa para guardar el juego
    private Partida partida; // se usa para establecer la partida.
    private ArrayList<javax.swing.JLabel> labelsHorizontales = new ArrayList<>(); //  Contienen los labels de la operación
    private ArrayList<javax.swing.JLabel> labelsVerticales = new ArrayList<>(); 
    private javax.swing.JButton [][] casillas; // contienen todos los botones del tablero.
    private int index; // indice de la partida.
    private String horassave; // se usa para salvar el tiempo del timer antes de empezar el juego.
    private String minutossave;
    private String segundossave;
    
    public static Timer stopwatch;

    int count = 0; // se usa para el contador del timer
    int delay = 1000; // este delay se usa para que el timer cambie cada segundo.
      
   // Esta función restablece el tablero a vacío
    private void resetTablero(){
        for (javax.swing.JLabel label : labelsHorizontales){ // itera por todos los labels y botones de casillas
          label.setText("");
        }
        for (javax.swing.JLabel label : labelsVerticales){
          label.setText("");
        }
        for (javax.swing.JButton[] fila : casillas){
            for (javax.swing.JButton button : fila){
                button.setText("");
            }
        }
    }
    
   
    // esta función establece el tablero con respecto a la partida escogida.
    private void establecerTablero(){
        for(Operacion desigualdad : partida.getOperaciones()){
            switch(desigualdad.getTipo()){
                case 'a': // pone los labels con el símbolo correcto
                    labelsHorizontales.get(4 * desigualdad.getIndiceFila() + desigualdad.getIndiceColumna()).setText(">");
                    break;
                case 'b':
                    labelsHorizontales.get(4 * desigualdad.getIndiceFila() + desigualdad.getIndiceColumna()).setText("<");
                    break;
                case 'y':
                    labelsVerticales.get(5*desigualdad.getIndiceFila() + desigualdad.getIndiceColumna()).setText("∨");
                    break;
                case 'z':
                    labelsVerticales.get(5*desigualdad.getIndiceFila() + desigualdad.getIndiceColumna()).setText("∧");  
            }
        }

        // llena las casillas de constantes
        for(Operacion constante : partida.getConstantes()){
            casillas[constante.getIndiceFila()][constante.getIndiceColumna()].setText(Character.toString(constante.getTipo()));
        }
    }
    
    // Esta función establece la partida de acuerdo con el nivel. 
    private void establecerPartida(){
          if (Partida.getPartidasPorNivel().get(Configuracion.getNivel()).isEmpty()){ // si no hay partidas disponibles, se da un mensaje de error.
                  JOptionPane.showMessageDialog(this, "NO HAY PARTIDAS DE ESE NIVEL.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                  dispose();
                  Menu window = new Menu();
                  window.setVisible(true);
          }
          // se establece la partida de acuerdo con el nivel.
        index = Partida.getIndex(Configuracion.getNivel());
        partida = Partida.getPartidasPorNivel().get(Configuracion.getNivel()).get(index);
        resetTablero();
        establecerTablero();
    }
      
    // esta función mueve el panel de botones de acuerdo con el lado establecido en la configuración
     public void moveButtonPanel(){
           if (!Configuracion.getLado()){
                 NumbersPanel.setLocation(57, 100);
            } else {
                  NumbersPanel.setLocation(710, 60);
            }
     }
      /**
       * Creates new form JugarFrame
       */
     // Esta función crea el frame de esta pestaña
      public JugarFrame() {
            initComponents();
            this.setLocationRelativeTo(null); // se centra la ventana
            NivelTXT.setText(Configuracion.getNivel()); // se establece el ninvel
            setLayout(null); // esto se usa para poder mover el panel de botones.
            moveButtonPanel();
            if (Configuracion.getReloj().equals("No")){ //  si no hay un reloj, se quita de la pantalla
                  relojFrame.setVisible(false);
             } else if (Configuracion.getReloj().equals("Timer")) { // si hay un timer, se deja editar el tiempo
                  
                  HorasTXT.setEditable(true);
                  MinutosTXT.setEditable(true);
                  SegundosTXT.setEditable(true);
                  HorasTXT.setText(Reloj.getHoras()); // se establecen los tiempos de la configuración
                  MinutosTXT.setText(Reloj.getMinutos());
                  SegundosTXT.setText(Reloj.getSegundos());
            }
            // se añaden los labels a las listas
            labelsHorizontales.add(Op001);
            labelsHorizontales.add(Op012);
            labelsHorizontales.add(Op023);
            labelsHorizontales.add(Op034);
            labelsHorizontales.add(Op101);
            labelsHorizontales.add(Op112);
            labelsHorizontales.add(Op123);
            labelsHorizontales.add(Op134);
            labelsHorizontales.add(Op201);
            labelsHorizontales.add(Op212);
            labelsHorizontales.add(Op223);
            labelsHorizontales.add(Op234);
            labelsHorizontales.add(Op301);
            labelsHorizontales.add(Op312);
            labelsHorizontales.add(Op323);
            labelsHorizontales.add(Op334);
            labelsHorizontales.add(Op401);
            labelsHorizontales.add(Op412);
            labelsHorizontales.add(Op423);
            labelsHorizontales.add(Op434);
            
            labelsVerticales.add(Op00V);
            labelsVerticales.add(Op01V);
            labelsVerticales.add(Op02V);
            labelsVerticales.add(Op03V);
            labelsVerticales.add(Op04V);
            labelsVerticales.add(Op10V);
            labelsVerticales.add(Op11V);
            labelsVerticales.add(Op12V);
            labelsVerticales.add(Op13V);
            labelsVerticales.add(Op14V);
            labelsVerticales.add(Op20V);
            labelsVerticales.add(Op21V);
            labelsVerticales.add(Op22V);
            labelsVerticales.add(Op23V);
            labelsVerticales.add(Op24V);
            labelsVerticales.add(Op30V);
            labelsVerticales.add(Op31V);
            labelsVerticales.add(Op32V);
            labelsVerticales.add(Op33V);
            labelsVerticales.add(Op34V);

            casillas = new javax.swing.JButton[5][5];
            casillas[0][0] = Btn00;
            casillas[0][1] = Btn01;
            casillas[0][2] = Btn02;
            casillas[0][3] = Btn03;
            casillas[0][4] = Btn04;
            casillas[1][0] = Btn10;
            casillas[1][1] = Btn11;
            casillas[1][2] = Btn12;
            casillas[1][3] = Btn13;
            casillas[1][4] = Btn14;
            casillas[2][0] = Btn20;
            casillas[2][1] = Btn21;
            casillas[2][2] = Btn22;
            casillas[2][3] = Btn23;
            casillas[2][4] = Btn24;
            casillas[3][0] = Btn30;
            casillas[3][1] = Btn31;
            casillas[3][2] = Btn32;
            casillas[3][3] = Btn33;
            casillas[3][4] = Btn34;
            casillas[4][0] = Btn40;
            casillas[4][1] = Btn41;
            casillas[4][2] = Btn42;
            casillas[4][3] = Btn43;
            casillas[4][4] = Btn44;
            establecerPartida();
            GuardarJuego.setEnabled(false);
            RehacerJugada.setEnabled(false);
            BorrarJugada.setEnabled(false);
            TerminarJuego.setEnabled(false);
            BorrarJuego.setEnabled(false);
            
        }
      /**
       * This method is called from within the constructor to initialize the
       * form. WARNING: Do NOT modify this code. The content of this method is
       * always regenerated by the Form Editor.
       */
      @SuppressWarnings("unchecked")
      // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
      private void initComponents() {

            Opciones = new javax.swing.ButtonGroup();
            jLabel2 = new javax.swing.JLabel();
            jLabel1 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            NivelTXT = new javax.swing.JLabel();
            NombreTXT = new javax.swing.JTextField();
            NumbersPanel = new javax.swing.JPanel();
            Btn2 = new javax.swing.JToggleButton();
            Btn3 = new javax.swing.JToggleButton();
            Btn1 = new javax.swing.JToggleButton();
            Btn5 = new javax.swing.JToggleButton();
            Btn4 = new javax.swing.JToggleButton();
            Btn6 = new javax.swing.JToggleButton();
            IniciarJuego = new javax.swing.JButton();
            BorrarJugada = new javax.swing.JButton();
            RehacerJugada = new javax.swing.JButton();
            jPanel1 = new javax.swing.JPanel();
            Btn03 = new javax.swing.JButton();
            Btn04 = new javax.swing.JButton();
            Btn10 = new javax.swing.JButton();
            Btn11 = new javax.swing.JButton();
            Btn12 = new javax.swing.JButton();
            Btn13 = new javax.swing.JButton();
            Btn14 = new javax.swing.JButton();
            Btn00 = new javax.swing.JButton();
            Btn01 = new javax.swing.JButton();
            Btn02 = new javax.swing.JButton();
            Btn20 = new javax.swing.JButton();
            Btn21 = new javax.swing.JButton();
            Btn22 = new javax.swing.JButton();
            Btn23 = new javax.swing.JButton();
            Btn24 = new javax.swing.JButton();
            Btn44 = new javax.swing.JButton();
            Btn30 = new javax.swing.JButton();
            Btn31 = new javax.swing.JButton();
            Btn32 = new javax.swing.JButton();
            Btn33 = new javax.swing.JButton();
            Btn34 = new javax.swing.JButton();
            Btn40 = new javax.swing.JButton();
            Btn41 = new javax.swing.JButton();
            Btn42 = new javax.swing.JButton();
            Btn43 = new javax.swing.JButton();
            Op01V = new javax.swing.JLabel();
            Op04V = new javax.swing.JLabel();
            Op11V = new javax.swing.JLabel();
            Op21V = new javax.swing.JLabel();
            Op31V = new javax.swing.JLabel();
            panelgrid = new javax.swing.JPanel();
            Op101 = new javax.swing.JLabel();
            Op201 = new javax.swing.JLabel();
            Op301 = new javax.swing.JLabel();
            Op401 = new javax.swing.JLabel();
            Op001 = new javax.swing.JLabel();
            Op12V = new javax.swing.JLabel();
            jPanel3 = new javax.swing.JPanel();
            Op012 = new javax.swing.JLabel();
            Op112 = new javax.swing.JLabel();
            Op212 = new javax.swing.JLabel();
            Op312 = new javax.swing.JLabel();
            Op412 = new javax.swing.JLabel();
            Op13V = new javax.swing.JLabel();
            Op23V = new javax.swing.JLabel();
            Op33V = new javax.swing.JLabel();
            jPanel4 = new javax.swing.JPanel();
            Op023 = new javax.swing.JLabel();
            Op123 = new javax.swing.JLabel();
            Op223 = new javax.swing.JLabel();
            Op323 = new javax.swing.JLabel();
            Op423 = new javax.swing.JLabel();
            Op14V = new javax.swing.JLabel();
            Op24V = new javax.swing.JLabel();
            Op34V = new javax.swing.JLabel();
            jPanel6 = new javax.swing.JPanel();
            Op034 = new javax.swing.JLabel();
            Op134 = new javax.swing.JLabel();
            Op234 = new javax.swing.JLabel();
            Op334 = new javax.swing.JLabel();
            Op434 = new javax.swing.JLabel();
            Op00V = new javax.swing.JLabel();
            Op02V = new javax.swing.JLabel();
            Op22V = new javax.swing.JLabel();
            Op10V = new javax.swing.JLabel();
            Op20V = new javax.swing.JLabel();
            Op32V = new javax.swing.JLabel();
            Op30V = new javax.swing.JLabel();
            Op03V = new javax.swing.JLabel();
            TerminarJuego = new javax.swing.JButton();
            Top10 = new javax.swing.JButton();
            BorrarJuego = new javax.swing.JButton();
            GuardarJuego = new javax.swing.JButton();
            CargarJuego = new javax.swing.JButton();
            CerrarButton = new javax.swing.JButton();
            relojFrame = new javax.swing.JPanel();
            jLabel5 = new javax.swing.JLabel();
            jLabel7 = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            HorasTXT = new javax.swing.JTextField();
            MinutosTXT = new javax.swing.JTextField();
            SegundosTXT = new javax.swing.JTextField();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setBackground(new java.awt.Color(255, 255, 255));
            getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel2.setBackground(new java.awt.Color(153, 0, 0));
            jLabel2.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
            jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel2.setText("Futoshiki");
            jLabel2.setOpaque(true);
            getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 110, -1));

            jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            jLabel1.setText("NIVEL");
            getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 51, -1, -1));

            jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            jLabel3.setText("Nombre del jugador");
            getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 101, -1, -1));

            NivelTXT.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            NivelTXT.setText("a");
            NivelTXT.setToolTipText("");
            NivelTXT.setVerticalAlignment(javax.swing.SwingConstants.TOP);
            getContentPane().add(NivelTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 190, 30));

            NombreTXT.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
            NombreTXT.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        NombreTXTActionPerformed(evt);
                  }
            });
            getContentPane().add(NombreTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 270, -1));

            Opciones.add(Btn2);
            Btn2.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Btn2.setText("2");

            Opciones.add(Btn3);
            Btn3.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Btn3.setText("3");

            Opciones.add(Btn1);
            Btn1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Btn1.setText("1");
            Btn1.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn1ActionPerformed(evt);
                  }
            });

            Opciones.add(Btn5);
            Btn5.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Btn5.setText("5");

            Opciones.add(Btn4);
            Btn4.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Btn4.setText("4");
            Btn4.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn4ActionPerformed(evt);
                  }
            });

            Opciones.add(Btn6);
            Btn6.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Btn6.setText("Borrar");

            javax.swing.GroupLayout NumbersPanelLayout = new javax.swing.GroupLayout(NumbersPanel);
            NumbersPanel.setLayout(NumbersPanelLayout);
            NumbersPanelLayout.setHorizontalGroup(
                  NumbersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(NumbersPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(Btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addGroup(NumbersPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(Btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addGroup(NumbersPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(Btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addGroup(NumbersPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(Btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addGroup(NumbersPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(Btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addGroup(NumbersPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(Btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            NumbersPanelLayout.setVerticalGroup(
                  NumbersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(NumbersPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(Btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(Btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(Btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(Btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            getContentPane().add(NumbersPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, -1, 470));

            IniciarJuego.setBackground(new java.awt.Color(204, 0, 0));
            IniciarJuego.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            IniciarJuego.setText("Iniciar Juego");
            IniciarJuego.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        IniciarJuegoActionPerformed(evt);
                  }
            });
            getContentPane().add(IniciarJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 520, -1, 52));

            BorrarJugada.setBackground(new java.awt.Color(0, 204, 255));
            BorrarJugada.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            BorrarJugada.setText("Borrar Jugada");
            BorrarJugada.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        BorrarJugadaActionPerformed(evt);
                  }
            });
            getContentPane().add(BorrarJugada, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 520, -1, 52));

            RehacerJugada.setBackground(new java.awt.Color(255, 153, 0));
            RehacerJugada.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            RehacerJugada.setText("Rehacer Jugada");
            RehacerJugada.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        RehacerJugadaActionPerformed(evt);
                  }
            });
            getContentPane().add(RehacerJugada, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 520, -1, 52));

            jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            Btn03.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn03ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn03, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 55, 47));

            Btn04.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn04ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn04, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 27, 55, 47));

            Btn10.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn10ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 50, 50));

            Btn11.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn11ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn11, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 119, 55, 47));

            Btn12.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn12ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn12, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 55, 47));

            Btn13.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn13ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn13, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 119, 55, 47));

            Btn14.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn14ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn14, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 119, 55, 47));

            Btn00.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn00ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn00, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 27, 55, 47));

            Btn01.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn01ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn01, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 27, 55, 47));

            Btn02.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn02ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn02, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 27, 55, 47));

            Btn20.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn20ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 55, 47));

            Btn21.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn21ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn21, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 55, 47));

            Btn22.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn22ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn22, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 55, 47));

            Btn23.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn23ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn23, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 50, 50));

            Btn24.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn24ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn24, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, 55, 47));

            Btn44.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn44ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn44, new org.netbeans.lib.awtextra.AbsoluteConstraints(434, 352, 55, 50));

            Btn30.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn30ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn30, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 276, 55, 47));

            Btn31.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn31ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn31, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 276, 55, 47));

            Btn32.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn32ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn32, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 55, 50));

            Btn33.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn33ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn33, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 270, 50, 50));

            Btn34.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn34ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn34, new org.netbeans.lib.awtextra.AbsoluteConstraints(434, 276, 55, 47));

            Btn40.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn40ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn40, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 55, 47));

            Btn41.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn41ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn41, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 50, 50));

            Btn42.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn42ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn42, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 55, 50));

            Btn43.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Btn43ActionPerformed(evt);
                  }
            });
            jPanel1.add(Btn43, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 350, 55, 47));

            Op01V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op01V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op01V.setText("∧");
            jPanel1.add(Op01V, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

            Op04V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op04V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op04V.setText("∧");
            jPanel1.add(Op04V, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, -1, -1));

            Op11V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op11V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op11V.setText("∧");
            jPanel1.add(Op11V, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

            Op21V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op21V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op21V.setText("∧");
            jPanel1.add(Op21V, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, -1, -1));

            Op31V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op31V.setText("∧");
            jPanel1.add(Op31V, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, -1, -1));

            panelgrid.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            Op101.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op101.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op101.setText(">");
            panelgrid.add(Op101, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 20, -1));

            Op201.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op201.setText(">");
            panelgrid.add(Op201, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

            Op301.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op301.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op301.setText(">");
            panelgrid.add(Op301, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

            Op401.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op401.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op401.setText(">");
            Op401.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            panelgrid.add(Op401, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, -1));

            Op001.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op001.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op001.setText(">");
            Op001.setFocusable(false);
            Op001.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            panelgrid.add(Op001, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

            jPanel1.add(panelgrid, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 0, -1, 402));

            Op12V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op12V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op12V.setText("∧");
            jPanel1.add(Op12V, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, -1));

            jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            Op012.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op012.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op012.setText(">");
            jPanel3.add(Op012, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

            Op112.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op112.setText(">");
            jPanel3.add(Op112, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

            Op212.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op212.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op212.setText(">");
            Op212.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel3.add(Op212, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

            Op312.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op312.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op312.setText(">");
            jPanel3.add(Op312, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

            Op412.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op412.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op412.setText(">");
            Op412.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jPanel3.add(Op412, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

            jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 50, 400));

            Op13V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op13V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op13V.setText("∧");
            jPanel1.add(Op13V, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, -1, -1));

            Op23V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op23V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op23V.setText("∧");
            jPanel1.add(Op23V, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, -1, -1));

            Op33V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op33V.setText("∧");
            jPanel1.add(Op33V, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

            jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            Op023.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op023.setText(">");
            jPanel4.add(Op023, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 46, -1));

            Op123.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op123.setText(">");
            jPanel4.add(Op123, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

            Op223.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op223.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op223.setText(">");
            jPanel4.add(Op223, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

            Op323.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op323.setText(">");
            jPanel4.add(Op323, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

            Op423.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op423.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op423.setText(">");
            jPanel4.add(Op423, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

            jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 40, 400));

            Op14V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op14V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op14V.setText("∧");
            jPanel1.add(Op14V, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, -1, -1));

            Op24V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op24V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op24V.setText("∧");
            jPanel1.add(Op24V, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, -1, -1));

            Op34V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op34V.setText("∧");
            jPanel1.add(Op34V, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, -1, -1));

            jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            Op034.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op034.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op034.setText(">");
            jPanel6.add(Op034, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 50, -1));

            Op134.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op134.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op134.setText(">");
            jPanel6.add(Op134, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

            Op234.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op234.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op234.setText(">");
            jPanel6.add(Op234, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

            Op334.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op334.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op334.setText(">");
            jPanel6.add(Op334, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

            Op434.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
            Op434.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op434.setText(">");
            jPanel6.add(Op434, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

            jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 50, 390));

            Op00V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op00V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op00V.setText("∧");
            jPanel1.add(Op00V, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

            Op02V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op02V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op02V.setText("∧");
            jPanel1.add(Op02V, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

            Op22V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op22V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op22V.setText("∧");
            jPanel1.add(Op22V, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, -1, -1));

            Op10V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op10V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op10V.setText("∧");
            jPanel1.add(Op10V, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

            Op20V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op20V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op20V.setText("∧");
            jPanel1.add(Op20V, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

            Op32V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op32V.setText("∧");
            jPanel1.add(Op32V, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, -1, -1));

            Op30V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op30V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op30V.setText("∧");
            jPanel1.add(Op30V, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, -1));

            Op03V.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Op03V.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            Op03V.setText("∧");
            jPanel1.add(Op03V, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, -1, -1));

            getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 500, 410));

            TerminarJuego.setBackground(new java.awt.Color(0, 153, 0));
            TerminarJuego.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            TerminarJuego.setText("Terminar Juego");
            TerminarJuego.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        TerminarJuegoActionPerformed(evt);
                  }
            });
            getContentPane().add(TerminarJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 580, -1, 52));

            Top10.setBackground(new java.awt.Color(255, 255, 0));
            Top10.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            Top10.setText("Top 10");
            Top10.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        Top10ActionPerformed(evt);
                  }
            });
            getContentPane().add(Top10, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 530, -1, 52));

            BorrarJuego.setBackground(new java.awt.Color(153, 204, 255));
            BorrarJuego.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            BorrarJuego.setText("Borrar Juego");
            BorrarJuego.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        BorrarJuegoActionPerformed(evt);
                  }
            });
            getContentPane().add(BorrarJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 580, -1, 52));

            GuardarJuego.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            GuardarJuego.setText("Guardar Juego");
            GuardarJuego.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        GuardarJuegoActionPerformed(evt);
                  }
            });
            getContentPane().add(GuardarJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 590, -1, -1));

            CargarJuego.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
            CargarJuego.setText("Cargar Juego");
            CargarJuego.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        CargarJuegoActionPerformed(evt);
                  }
            });
            getContentPane().add(CargarJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 630, 150, -1));

            CerrarButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
            CerrarButton.setText("Cerrar");
            CerrarButton.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        CerrarButtonActionPerformed(evt);
                  }
            });
            getContentPane().add(CerrarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 650, 100, 30));

            relojFrame.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            relojFrame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
            jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel5.setText("Horas");
            jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            relojFrame.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 67, 24));

            jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
            jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel7.setText("Segundos");
            jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            relojFrame.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 0, 69, 24));

            jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
            jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel6.setText("Minutos");
            jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            relojFrame.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 0, 65, 24));

            HorasTXT.setText("0");
            HorasTXT.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        HorasTXTActionPerformed(evt);
                  }
            });
            relojFrame.add(HorasTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 30, -1));

            MinutosTXT.setText("0");
            relojFrame.add(MinutosTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 30, -1));

            SegundosTXT.setText("0");
            relojFrame.add(SegundosTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 30, -1));

            getContentPane().add(relojFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 270, 60));

            pack();
      }// </editor-fold>//GEN-END:initComponents

      // Esta función inicia el reloj
      public void startClock(Component window){
            ActionListener action = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                  int tiempo = count;
                  int hours = tiempo / 3600;
                  tiempo %= 3600;
                  int minutes = tiempo / 60;
                  int seconds = tiempo % 60;
                  HorasTXT.setText(Integer.toString(hours)); // calcula el número de horas, minutos y segundos que deben ser desplegados
                  MinutosTXT.setText(Integer.toString(minutes));
                  SegundosTXT.setText(Integer.toString(seconds));
                  count ++; // incrementa el valor del contador cada segundo
            }
        };
        stopwatch = new Timer(delay, action);
        stopwatch.setInitialDelay(0);
        stopwatch.start(); // empieza el reloj
      }
      
      // esta función inicia el timer
      public void startTimer(Component window){
        ActionListener action = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(count == 0){ // si el timer llega a 0, debe pedir si se quiere continuar con el juego
                        stopwatch.stop(); // se detiene el stopwatch
                        
                        // se pide la confirmación
                        int input = JOptionPane.showConfirmDialog(null, "¿Desea continuar?", "EL TIEMPO HA EXPIRADO.",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                  // 0=sí, 1=no, 
                        if (input == 0){  // si es sí, se continúa con el timer.
                              HorasTXT.setText(horassave);
                              MinutosTXT.setText(minutossave);
                              SegundosTXT.setText(segundossave);
                              count = Integer.valueOf(HorasTXT.getText()) * 3600 + Integer.valueOf(MinutosTXT.getText()) * 60 + Integer.valueOf(SegundosTXT.getText());
                              startClock(window);
                        } else{
                              started = false;
                        }
                } else { // si no ha terminado el timer, se despliegan los valores correctos y se decrementa el contador
                        int tiempo = count;
                        int hours = tiempo / 3600;
                        tiempo %= 3600;
                        int minutes = tiempo / 60;
                        int seconds = tiempo % 60;
                        HorasTXT.setText(Integer.toString(hours));
                        MinutosTXT.setText(Integer.toString(minutes));
                        SegundosTXT.setText(Integer.toString(seconds));
                        count --;
                }
            }
        };
        stopwatch = new Timer(delay, action);
        stopwatch.setInitialDelay(0);
        stopwatch.start();
    }
      
      private void Btn01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn01ActionPerformed
            // TODO add your handling code here:
            if (!started) {// si el juego no ha empezado, los botones no hacen nada
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 0,1, this); // verifica si ya se ganó el juego
            if (win){
                  RegisterWin(); // si sí, llama la función para detener el juego y registrarlo en el top 10
            }
      }//GEN-LAST:event_Btn01ActionPerformed

      private void Btn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn10ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 1,0, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn10ActionPerformed

      private void Btn00ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn00ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 0,0, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn00ActionPerformed

      
      public String getSelectedButtonText() {
        for (Enumeration<AbstractButton> buttons = Opciones.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
      // está función inicia el juego
      private void IniciarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarJuegoActionPerformed
            // TODO add your handling code here:
            if (!started) { // si no ha empezado el juego
                  if (NombreTXT.getText().length() < 1 || NombreTXT.getText().length() > 20){ // se valida el nombre del jugador
                      JOptionPane.showMessageDialog(this, "El nombre debe tener una longitud entre 1 y 20 caracteres.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                      return;
                  }
                  started = true; // se empieza 
                  juego = new Juego(partida, NombreTXT.getText(), casillas); // se crea el nuevo juego
                  
                  NombreTXT.setEditable(false);
                  // si hay timer
                  
                  Btn1.setSelected(true); // se selecciona el 1 como número para poner predeterminado 
                  IniciarJuego.setEnabled(false); // se desactiva este botón y se activan los demás
                  TerminarJuego.setEnabled(true);
                  GuardarJuego.setEnabled(true);
                  RehacerJugada.setEnabled(true);
                  BorrarJugada.setEnabled(true);
                  TerminarJuego.setEnabled(true);
                  BorrarJuego.setEnabled(true);
                  if (Configuracion.getReloj().equals("Timer")){ // si hay un timer, se empieza a correr
                        HorasTXT.setEditable(false);
                        MinutosTXT.setEditable(false);
                        SegundosTXT.setEditable(false);
                        horassave = HorasTXT.getText();
                        minutossave = MinutosTXT.getText();
                        segundossave = SegundosTXT.getText();
                        count = Integer.valueOf(HorasTXT.getText()) * 3600 + Integer.valueOf(MinutosTXT.getText()) * 60 + Integer.valueOf(SegundosTXT.getText()); // se calcula la cantidad de segundos totales con los que se pueden jugar.
                        startTimer(this);
                  } else if (Configuracion.getReloj().equals("Sí")){ // si hay un reloj, se empieza a correr
                        HorasTXT.setEditable(false);
                        MinutosTXT.setEditable(false);
                        SegundosTXT.setEditable(false);
                        startClock(this);
                  }
            }
      }//GEN-LAST:event_IniciarJuegoActionPerformed

      private void Btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn1ActionPerformed
            // TODO add your handling code here:
            
      }//GEN-LAST:event_Btn1ActionPerformed

      private void Btn02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn02ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 0,2, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn02ActionPerformed

      private void Btn03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn03ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 0,3, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn03ActionPerformed

      private void Btn04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn04ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 0,4, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn04ActionPerformed

      private void Btn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn11ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 1,1, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn11ActionPerformed

      private void Btn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn12ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 1,2, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn12ActionPerformed

      private void Btn13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn13ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 1,3, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn13ActionPerformed

      private void Btn14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn14ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 1,4, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn14ActionPerformed

      private void Btn20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn20ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 2,0, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn20ActionPerformed

      private void Btn21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn21ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 2,1, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn21ActionPerformed

      private void Btn22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn22ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 2,2, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn22ActionPerformed

      private void Btn23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn23ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 2,3, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn23ActionPerformed

      private void Btn24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn24ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 2,4, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn24ActionPerformed

      private void Btn30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn30ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 3,0, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn30ActionPerformed

      private void Btn31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn31ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 3,1, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn31ActionPerformed

      private void Btn32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn32ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 3,2, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn32ActionPerformed

      private void Btn33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn33ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 3,3, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn33ActionPerformed

      private void Btn34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn34ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 3,4, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn34ActionPerformed

      private void Btn40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn40ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 4,0, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn40ActionPerformed

      private void Btn41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn41ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 4,1, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn41ActionPerformed

      private void Btn42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn42ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 4,2, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn42ActionPerformed

      private void Btn43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn43ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 4,3, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn43ActionPerformed

      private void Btn44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn44ActionPerformed
            // TODO add your handling code here:
            if (!started) {
                  return;             
            }
            win = juego.añadirNumero(getSelectedButtonText(), 4,4, this);
            if (win){
                  RegisterWin();
            }
      }//GEN-LAST:event_Btn44ActionPerformed

      // esta función llama a la función de borrar jugada
      private void BorrarJugadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarJugadaActionPerformed
            // TODO add your handling code here:
            juego.borrarJugada(this);
      }//GEN-LAST:event_BorrarJugadaActionPerformed

       // esta función llama a la función de rehacer jugada
      private void RehacerJugadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RehacerJugadaActionPerformed
            // TODO add your handling code here:
            juego.rehacerJugada(this);
      }//GEN-LAST:event_RehacerJugadaActionPerformed

      private void Btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn4ActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_Btn4ActionPerformed

     // esta función termina el juego y empieza otro
    private void TerminarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TerminarJuegoActionPerformed
        // TODO add your handling code here:
        Object[] options = {"Sí",
                            "No"};
        int respuesta = JOptionPane.showOptionDialog(this,
            "¿Está seguro de terminar el juego (Sí o No)?",
            "Terminar juego",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,     //do not use a custom Icon
            options,  //the titles of buttons
            options[0]); //default button title
        if (respuesta == JOptionPane.YES_OPTION){
            IniciarJuego.setEnabled(true); // se reactiva el botón de iniciar juego
            TerminarJuego.setEnabled(false); // se desactiva este botón
            resetTablero(); // se borrar todo el tablero
            establecerPartida(); // se establece una nueva partida
            establecerTablero(); // se establece el tablero con la nueva partida
            NombreTXT.setEditable(true);
            started = false;
            if (Configuracion.getReloj().equals("Timer")) { // si había un timer o reloj, se detiene y reinicia el contador
                  stopwatch.stop();
                  HorasTXT.setEditable(true);
                  MinutosTXT.setEditable(true);
                  SegundosTXT.setEditable(true);
                  count = Reloj.getHorasInt() * 3600 + Reloj.getMinutosInt() * 60 + Reloj.getSegundosInt();
            } else if(Configuracion.getReloj().equals("Sí")){
                  stopwatch.stop();
                  count = 0;
            }
            // se desactivan todos los otros botones
            GuardarJuego.setEnabled(false);
            RehacerJugada.setEnabled(false);
            BorrarJugada.setEnabled(false);
            TerminarJuego.setEnabled(false);
            BorrarJuego.setEnabled(false);
        }
    }//GEN-LAST:event_TerminarJuegoActionPerformed

    // esta función cierra la pestaña del juego
      private void CerrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarButtonActionPerformed
            // TODO add your handling code here:
            int input = JOptionPane.showConfirmDialog(null, "¿Desea cerrar el juego y perder el progreso?", "Selecione una opción...",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            // 0=sí, 1=no, 
            if (input == 0){ // cierra la pestaña
                  started = false;
                  if (!("No".equals(Configuracion.getReloj())) && stopwatch != null){
                        stopwatch.stop();
                  }
                  dispose();
                  Menu window = new Menu();
                  window.setVisible(true);
            }
      }//GEN-LAST:event_CerrarButtonActionPerformed

      // esta función borra el juego
      private void BorrarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarJuegoActionPerformed
            // TODO add your handling code here:
            int input = JOptionPane.showConfirmDialog(null, "¿Desea borrar el juego?", "Selecione una opción...", 
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE); // pide confirmación de borrar el juego
            // 0=sí, 1=no, 
            if (input == 0){ // si sí,                  
                  resetTablero();
                  establecerTablero();
                  if (Configuracion.getReloj().equals("Sí")){
                        stopwatch.stop(); // se detiene el reloj
                        HorasTXT.setText("0"); // se restablecen todos los valores
                        MinutosTXT.setText("0");
                        SegundosTXT.setText("0");
                        count = 0;
                        stopwatch.start(); // se reinicia el reloj
                  } else if (Configuracion.getReloj().equals("Timer")){
                        stopwatch.stop(); // 
                        HorasTXT.setText(horassave);
                        MinutosTXT.setText(minutossave);
                        SegundosTXT.setText(segundossave);
                        count = Integer.valueOf(HorasTXT.getText()) * 3600 + Integer.valueOf(MinutosTXT.getText()) * 60 + Integer.valueOf(SegundosTXT.getText());
                        stopwatch.start();
                  }

                  JOptionPane.showMessageDialog(this, "Juego eliminado existosamente."); // se da un mensaje de éxito
                  
            }
      }//GEN-LAST:event_BorrarJuegoActionPerformed

      // esta función abre la pestaña de top 10. No cierra la del juego para que no se pierda el progreso.
      private void Top10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Top10ActionPerformed
            // TODO add your handling code here:
            Top10Frame window = new Top10Frame();
            window.setVisible(true);
      }//GEN-LAST:event_Top10ActionPerformed

      private void HorasTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HorasTXTActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_HorasTXTActionPerformed

      private void NombreTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreTXTActionPerformed
            // TODO add your handling code here:
      }//GEN-LAST:event_NombreTXTActionPerformed

      // esta función registra la victoria del juego y la escribe en la lista de top 10
      private void RegisterWin(){
            started = false; // detiene el juego
            GuardarJuego.setEnabled(false); // desactiva todos los botones
            RehacerJugada.setEnabled(false);
            BorrarJugada.setEnabled(false);
            TerminarJuego.setEnabled(false);
            BorrarJuego.setEnabled(false);
            if (Configuracion.getReloj().equals("Sí") || Configuracion.getReloj().equals("Timer")){
                  stopwatch.stop(); // detiene el contador
                  int horas; // obtiene el tiempo actual
                  int minutos;
                  int segundos;
                  int tiemponow;
                  horas = Integer.valueOf(HorasTXT.getText());
                  minutos = Integer.valueOf(MinutosTXT.getText());
                  segundos = Integer.valueOf(SegundosTXT.getText());
                  tiemponow = horas *3600 + minutos * 60 + segundos;
                  if (Configuracion.getReloj().equals("Timer")){ // si el reloj era un timer, el reloj tiempo actual es lo que falta, no lo que se duró
                        int horasog = Integer.valueOf(horassave);
                        int minutosog = Integer.valueOf(minutossave);
                        int segundosog = Integer.valueOf(segundossave);
                        int tiempoog = horasog * 3600 + minutosog + segundosog;
                        tiemponow  = tiempoog - tiemponow; // se obtiene el tiempo que se duró al restar el original con lo que queda.
                  }

                  // se crea una marca nueva
                  Marca marca = new Marca(NombreTXT.getText(), tiemponow, NivelTXT.getText());
                  int i = 0; // contador para iterar por la lista del nivel
                  for (; i < Marca.marcas.get(marca.getNivel()).size() ; i++){
                        if (Marca.marcas.get(marca.getNivel()).get(i).getTiempo() > marca.getTiempo()  && !(Marca.marcas.get(marca.getNivel()).contains(marca))){ // si la marca que se está revisando es mayor a la que se obtuvo en este juego
                              int index = Marca.marcas.get(marca.getNivel()).indexOf(Marca.marcas.get(marca.getNivel()).get(i)); // se añade el elemento nuevo en esa posición
                              Marca.marcas.get(marca.getNivel()).add(index, marca);
                        }
                  }
                  // si la marca no era menor que alguna en la lista, pero está todavía no está completa o está vacía
                  if (Marca.marcas.get(marca.getNivel()).isEmpty() ||  i < 10){
                        Marca.marcas.get(marca.getNivel()).add(marca); // la añade al final
                  }
                  if (Marca.marcas.get(marca.getNivel()).size() > 10) { // si el tamaño de la lista 
                        Marca.marcas.get(marca.getNivel()).remove(10);
                  }
                  
                 FileWriter fw; // se crea un file writer para escribir en el archivo
                 try { // se usa un try porque puede darse un error
                       fw = new FileWriter("src\\main\\java\\poo\\programa2\\futoshiki2022top10.dat");
                       String nivel = "Fácil";
                       String str ;
                       for (int y = 0; y < 3; y++){ // se itera por cada nivel
                             for (i = 0; i < 10; i++){ // se itera por cada marca del nivel
                                   for (int x = 0; x < 3; x++){ // se escribe cada parte de la marca: el nombre, el tiempo y el nivel
                                         if (Marca.marcas.get(nivel).size() <= i) { // si la lista de marcas es menor a 10, se llena los espacios con falta con N de nulo
                                               str = "N";
                                         } else{
                                               str = switch (x) { // se usa un switch para ver qué se tiene escribir.
                                                     case 0 -> Marca.marcas.get(nivel).get(i).getNombre();
                                                     case 1 -> Integer.toString(Marca.marcas.get(nivel).get(i).getTiempo());
                                                     default -> nivel;
                                               };
                                         }
                                           for (int j = 0; j < str.length(); j++){ // se escriben todos los caracteres presentes en el string que se está escribiendo
                                                 fw.write(str.charAt(j));
                                           }
                                           if (x < 2) { // si el elemento a escribir no es el nivel, se pone un espacio después para separarlos
                                                 fw.write(" ");
                                           }
                                   }
                                   if (i != 9 || y != 2){ // si el elemento a escribir no es el último del nivel difícil, se pone un cambio de línea
                                          fw.write("\n");
                                   }
                              }
                             if (y == 0){ // se establece el nivel al siguiente
                                   nivel = "Intermedio";
                             } else {
                                   nivel = "Difícil";
                             }
                       }
                       fw.close(); // se cierra el archivo
                       
                 }
                 catch (IOException except){
                       
                 }
            }
      }    
    
    private void GuardarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarJuegoActionPerformed
        // TODO add your handling code here:
        try{
            FileWriter save = new FileWriter("futoshiki2022juegoactual.dat"); // se escribe en el archivo
            save.write(Configuracion.getNivel() + "\n"); // se salva el nivel
            save.write(index + "\n"); // se salva el índice de la partida para volverla a desplegar luego
            save.write(Configuracion.getReloj() + "\n"); // se salva si hay reloj o no
            save.write(Reloj.getHoras() + "\n"); // se salvan las horas, minutos y segundos
            save.write(Reloj.getMinutos() + "\n");
            save.write(Reloj.getSegundos() + "\n");
            save.write(Boolean.toString(Configuracion.getLado()) + "\n"); // se salva el lado del panel
            save.write(NombreTXT.getText() + "\n"); // se salva el nombre del jugador
            for (javax.swing.JButton[] fila : casillas){ // se salvan todas las casillas
                for (javax.swing.JButton casilla : fila){
                    save.write(casilla.getText() + "\n");
                }
            }
            save.close(); // se cierra el archivo
            JOptionPane.showMessageDialog(this, "Partida guardada con éxito"); // se da un mensaje de éxito
        } catch (IOException e){
            JOptionPane.showMessageDialog(this, "No se pudo grabar la partida.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_GuardarJuegoActionPerformed

    private void CargarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarJuegoActionPerformed
        // TODO add your handling code here:
        if (started){ // si ya hay se está jugando, no se deja cargar un juevo
              JOptionPane.showMessageDialog(this, "Ya hay una partida en juego. Termine su partida y vuelva a intentar.", 
                "Error", JOptionPane.ERROR_MESSAGE);
              return;
        }
        try{ // se abre el archivo
            List<String> lineas = Files.readAllLines(Paths.get("futoshiki2022juegoactual.dat"));
            Configuracion.setNivel(lineas.get(0)); // se establece el nivel
            NivelTXT.setText(lineas.get(0)); 
            partida = Partida.getPartidasPorNivel().get(lineas.get(0)).get(Integer.parseInt(lineas.get(1))); // se obtiene la partida
            resetTablero(); // se borra el tablero
            establecerTablero(); // se vuelve a establecer el tablero con la partida recuperada
            Configuracion.setReloj(lineas.get(2)); // se obtiene el reloj
            Reloj.setHoras(lineas.get(3)); // se obtienen las horas, minutos y segundos
            Reloj.setMinutos(lineas.get(4));
            Reloj.setSegundos(lineas.get(5));
            HorasTXT.setText(lineas.get(3));
            MinutosTXT.setText(lineas.get(4));
            SegundosTXT.setText(lineas.get(5));
            Configuracion.setLado(Boolean.parseBoolean(lineas.get(6))); // se obtiene el lado del 
            moveButtonPanel();
            NombreTXT.setText(lineas.get(7)); // se vuelve a poner el nombre
            for (int casilla = 0; casilla < 25; casilla++){ // se vuelven a poner los valores de las casillas
                casillas[casilla / 5][casilla % 5].setText(lineas.get(8 + casilla));
            }
            IniciarJuegoActionPerformed(evt); // se inicia el juego otra vez
        } catch (IOException e){
            JOptionPane.showMessageDialog(this, "La partida no se pudo cargar porque el archivo no existe.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_CargarJuegoActionPerformed

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
                        if ("Nimbus".equals(info.getName())) {
                              javax.swing.UIManager.setLookAndFeel(info.getClassName());
                              break;
                        }
                  }
            } catch (ClassNotFoundException ex) {
                  java.util.logging.Logger.getLogger(JugarFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                  java.util.logging.Logger.getLogger(JugarFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                  java.util.logging.Logger.getLogger(JugarFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                  java.util.logging.Logger.getLogger(JugarFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                        new JugarFrame().setVisible(true);
                  }
            });
      }

      // Variables declaration - do not modify//GEN-BEGIN:variables
      private javax.swing.JButton BorrarJuego;
      private javax.swing.JButton BorrarJugada;
      private javax.swing.JButton Btn00;
      private javax.swing.JButton Btn01;
      private javax.swing.JButton Btn02;
      private javax.swing.JButton Btn03;
      private javax.swing.JButton Btn04;
      private javax.swing.JToggleButton Btn1;
      private javax.swing.JButton Btn10;
      private javax.swing.JButton Btn11;
      private javax.swing.JButton Btn12;
      private javax.swing.JButton Btn13;
      private javax.swing.JButton Btn14;
      private javax.swing.JToggleButton Btn2;
      private javax.swing.JButton Btn20;
      private javax.swing.JButton Btn21;
      private javax.swing.JButton Btn22;
      private javax.swing.JButton Btn23;
      private javax.swing.JButton Btn24;
      private javax.swing.JToggleButton Btn3;
      private javax.swing.JButton Btn30;
      private javax.swing.JButton Btn31;
      private javax.swing.JButton Btn32;
      private javax.swing.JButton Btn33;
      private javax.swing.JButton Btn34;
      private javax.swing.JToggleButton Btn4;
      private javax.swing.JButton Btn40;
      private javax.swing.JButton Btn41;
      private javax.swing.JButton Btn42;
      private javax.swing.JButton Btn43;
      private javax.swing.JButton Btn44;
      private javax.swing.JToggleButton Btn5;
      private javax.swing.JToggleButton Btn6;
      private javax.swing.JButton CargarJuego;
      private javax.swing.JButton CerrarButton;
      private javax.swing.JButton GuardarJuego;
      private javax.swing.JTextField HorasTXT;
      private javax.swing.JButton IniciarJuego;
      private javax.swing.JTextField MinutosTXT;
      private javax.swing.JLabel NivelTXT;
      private javax.swing.JTextField NombreTXT;
      private javax.swing.JPanel NumbersPanel;
      private javax.swing.JLabel Op001;
      private javax.swing.JLabel Op00V;
      private javax.swing.JLabel Op012;
      private javax.swing.JLabel Op01V;
      private javax.swing.JLabel Op023;
      private javax.swing.JLabel Op02V;
      private javax.swing.JLabel Op034;
      private javax.swing.JLabel Op03V;
      private javax.swing.JLabel Op04V;
      private javax.swing.JLabel Op101;
      private javax.swing.JLabel Op10V;
      private javax.swing.JLabel Op112;
      private javax.swing.JLabel Op11V;
      private javax.swing.JLabel Op123;
      private javax.swing.JLabel Op12V;
      private javax.swing.JLabel Op134;
      private javax.swing.JLabel Op13V;
      private javax.swing.JLabel Op14V;
      private javax.swing.JLabel Op201;
      private javax.swing.JLabel Op20V;
      private javax.swing.JLabel Op212;
      private javax.swing.JLabel Op21V;
      private javax.swing.JLabel Op223;
      private javax.swing.JLabel Op22V;
      private javax.swing.JLabel Op234;
      private javax.swing.JLabel Op23V;
      private javax.swing.JLabel Op24V;
      private javax.swing.JLabel Op301;
      private javax.swing.JLabel Op30V;
      private javax.swing.JLabel Op312;
      private javax.swing.JLabel Op31V;
      private javax.swing.JLabel Op323;
      private javax.swing.JLabel Op32V;
      private javax.swing.JLabel Op334;
      private javax.swing.JLabel Op33V;
      private javax.swing.JLabel Op34V;
      private javax.swing.JLabel Op401;
      private javax.swing.JLabel Op412;
      private javax.swing.JLabel Op423;
      private javax.swing.JLabel Op434;
      private javax.swing.ButtonGroup Opciones;
      private javax.swing.JButton RehacerJugada;
      private javax.swing.JTextField SegundosTXT;
      private javax.swing.JButton TerminarJuego;
      private javax.swing.JButton Top10;
      private javax.swing.JLabel jLabel1;
      private javax.swing.JLabel jLabel2;
      private javax.swing.JLabel jLabel3;
      private javax.swing.JLabel jLabel5;
      private javax.swing.JLabel jLabel6;
      private javax.swing.JLabel jLabel7;
      private javax.swing.JPanel jPanel1;
      private javax.swing.JPanel jPanel3;
      private javax.swing.JPanel jPanel4;
      private javax.swing.JPanel jPanel6;
      private javax.swing.JPanel panelgrid;
      private javax.swing.JPanel relojFrame;
      // End of variables declaration//GEN-END:variables
}
