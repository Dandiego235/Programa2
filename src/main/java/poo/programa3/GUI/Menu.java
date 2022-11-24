/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package poo.programa3.GUI;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Ventana para el menú principal
 * @author Daniel Granados Retana y Diego Granados Retana
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
      // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
      private void initComponents() {

            jLabel1 = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            jugarButton = new javax.swing.JButton();
            ConfigurarButton = new javax.swing.JButton();
            jButton1 = new javax.swing.JButton();
            jButton2 = new javax.swing.JButton();
            jButton3 = new javax.swing.JButton();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
            jLabel1.setText("Menu Principal");

            jLabel2.setBackground(new java.awt.Color(255, 102, 102));
            jLabel2.setFont(new java.awt.Font("Ebrima", 1, 48)); // NOI18N
            jLabel2.setText("Futoshiki");

            jugarButton.setBackground(new java.awt.Color(204, 255, 204));
            jugarButton.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
            jugarButton.setText("Jugar");
            jugarButton.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jugarButtonActionPerformed(evt);
                  }
            });

            ConfigurarButton.setBackground(new java.awt.Color(204, 204, 255));
            ConfigurarButton.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
            ConfigurarButton.setText("Configurar");
            ConfigurarButton.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        ConfigurarButtonActionPerformed(evt);
                  }
            });

            jButton1.setBackground(new java.awt.Color(204, 255, 255));
            jButton1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
            jButton1.setText("Ayuda");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton1ActionPerformed(evt);
                  }
            });

            jButton2.setBackground(new java.awt.Color(255, 204, 153));
            jButton2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
            jButton2.setText("Acerca de");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton2ActionPerformed(evt);
                  }
            });

            jButton3.setBackground(new java.awt.Color(255, 102, 102));
            jButton3.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
            jButton3.setText("Salir");
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton3ActionPerformed(evt);
                  }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jugarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(ConfigurarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(56, 56, 56))
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(172, 172, 172))
                  .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(layout.createSequentialGroup()
                                    .addGap(109, 109, 109)
                                    .addComponent(jButton2)
                                    .addGap(85, 85, 85)
                                    .addComponent(jButton3))
                              .addGroup(layout.createSequentialGroup()
                                    .addGap(154, 154, 154)
                                    .addComponent(jLabel2)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(jugarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(ConfigurarButton)
                              .addComponent(jButton1))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(jButton2)
                              .addComponent(jButton3))
                        .addContainerGap(35, Short.MAX_VALUE))
            );

            pack();
      }// </editor-fold>//GEN-END:initComponents

    private void jugarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugarButtonActionPerformed
        // TODO add your handling code here:
          dispose();
          JugarFrame window = new JugarFrame();
          window.setVisible(true);
    }//GEN-LAST:event_jugarButtonActionPerformed

    private void ConfigurarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfigurarButtonActionPerformed
        // TODO add your handling code here:
        dispose();
        ConfiguracionGUI config = new ConfiguracionGUI();
        config.setVisible(true);
    }//GEN-LAST:event_ConfigurarButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (Desktop.isDesktopSupported()) { // se abre el archivo del manual de usuario
            try {
                File myFile = new File("src\\main\\java\\poo\\programa3\\programa2_manual_de_usuario.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                  JOptionPane.showMessageDialog(this, "NO SE PUDO ABRIR LA AYUDA.", 
                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
          dispose();
          AcercaDe window = new AcercaDe();
          window.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

      // Variables declaration - do not modify//GEN-BEGIN:variables
      private javax.swing.JButton ConfigurarButton;
      private javax.swing.JButton jButton1;
      private javax.swing.JButton jButton2;
      private javax.swing.JButton jButton3;
      private javax.swing.JLabel jLabel1;
      private javax.swing.JLabel jLabel2;
      private javax.swing.JButton jugarButton;
      // End of variables declaration//GEN-END:variables
}