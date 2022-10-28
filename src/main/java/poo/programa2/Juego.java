/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa2;

import java.awt.Color;
import java.awt.Component;
import java.util.*;
import javax.swing.JOptionPane;
/**
 *
 * @author dandi
 */
public class Juego {
      private javax.swing.JButton[][] casillas;
      private Partida partida;
      private Stack<Jugada> jugadas;
      private Stack<Jugada> redoJugadas;
      private Marca marca;
      public Juego(Partida partida, String nombre, javax.swing.JButton button00, javax.swing.JButton button01, javax.swing.JButton button02, javax.swing.JButton button03, javax.swing.JButton button04,javax.swing.JButton button10, javax.swing.JButton button11, javax.swing.JButton button12, javax.swing.JButton button13, javax.swing.JButton button14,javax.swing.JButton button20, javax.swing.JButton button21, javax.swing.JButton button22, javax.swing.JButton button23, javax.swing.JButton button24,javax.swing.JButton button30, javax.swing.JButton button31, javax.swing.JButton button32, javax.swing.JButton button33, javax.swing.JButton button34,javax.swing.JButton button40, javax.swing.JButton button41, javax.swing.JButton button42, javax.swing.JButton button43, javax.swing.JButton button44){
            casillas = new javax.swing.JButton[5][5];
            casillas[0][0] = button00;
            casillas[0][1] = button01;
            casillas[0][2] = button02;
            casillas[0][3] = button03;
            casillas[0][4] = button04;
            casillas[1][0] = button00;
            casillas[1][1] = button11;
            casillas[1][2] = button12;
            casillas[1][3] = button13;
            casillas[1][4] = button14;
            casillas[2][0] = button20;
            casillas[2][1] = button21;
            casillas[2][2] = button22;
            casillas[2][3] = button23;
            casillas[2][4] = button24;
            casillas[3][0] = button30;
            casillas[3][1] = button31;
            casillas[3][2] = button32;
            casillas[3][3] = button33;
            casillas[3][4] = button34;
            casillas[4][0] = button40;
            casillas[4][1] = button41;
            casillas[4][2] = button42;
            casillas[4][3] = button43;
            casillas[4][4] = button44;            
            jugadas = new Stack<>();
            marca = new Marca(nombre);
      }
      
      public void añadirNumero(String numero, int fila, int columna,Component window){ // recibe las coordenadas de la casilla a donde se va a poner el número
            // Se validan las restricciones
            for (javax.swing.JButton buttonIter : casillas[fila]){
                  if (buttonIter.getText().equals(numero)){
                        JOptionPane.showMessageDialog(window, "JUGADA NO ES VÁLIDA PORQUE EL ELEMENTO YA ESTÁ EN LA FILA.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        buttonIter.setBackground(Color.red);
                        return;
                 }
            }
            for (int i = 0; i < 5; i++){
                  if (casillas[i][columna].getText().equals(numero)){
                        JOptionPane.showMessageDialog(window, "JUGADA NO ES VÁLIDA PORQUE EL ELEMENTO YA ESTÁ EN LA COLUMNA.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        casillas[i][columna].setBackground(Color.red);
                        return;
                  }
            }
            
            // TO DO: REVISAR SI ES FIJO Y LA RESTRICCION DE MAYOR O MENOR
            
            jugadas.push(new Jugada(fila,columna,numero)); // mete la jugada a la pila de jugadas para luego hacer el undo si se requiere
            if (!numero.equals("Borrar")){  // se altera el texto de la casilla
                  casillas[fila][columna].setText(numero);  
            } else {
                   
                  casillas[fila][columna].setText("");      
            }
            casillas[fila][columna].setBackground(Color.red);
            redoJugadas = new Stack<>();            // Se crea la pila para poner las jugadas por rehacer.
      }
      
      // Saca la última jugada
      public void borrarJugada(Component window){
            if (!jugadas.empty()){ // si la pila no está vacía
                  Jugada borrada = jugadas.pop(); // saca el último elemento
                  redoJugadas.push(borrada); // lo mete a la pila de rehacer jugada
                  casillas[borrada.getFila()][borrada.getColumna()].setText(""); // restaura la casilla
            } else {
                  JOptionPane.showMessageDialog(window, "YA NO HAY MÁS JUGADAS POR BORRAR.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
            }
      }
      
      
      public void rehacerJugada(Component window){
            if (!redoJugadas.empty()){ // si la pila no está vacía
                  Jugada restored = redoJugadas.pop(); // saca el último elemento
                  if (!restored.getNumero().equals("Borrar")){
                        casillas[restored.getFila()][restored.getColumna()].setText(restored.getNumero()); // restaura la casilla al valor que tenía antes
                  } else {
                         casillas[restored.getFila()][restored.getColumna()].setText(""); // restaura la casilla al valor que tenía antes

                  }
            } else {
                  JOptionPane.showMessageDialog(window, "YA NO HAY MÁS JUGADAS POR BORRAR.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
            }
      }
      
      public boolean checkWin(){
            for (int i = 0; i < 5; i++){
                  SortedSet<String> conjunto = new TreeSet<String>();
                  for (int j = 0; j < 5; j++){
                        if (!casillas[i][j].getText().equals("")){
                              conjunto.add(casillas[i][j].getText());
                        }
                  }
                  if (conjunto.size() != 5){
                        return false;
                  }
            }
            for (int i = 0; i < 5; i++){
                  SortedSet<String> conjunto = new TreeSet<String>();
                  for (int j = 0; j < 5; j++){
                        if (!casillas[j][i].getText().equals("")){
                              conjunto.add(casillas[j][i].getText());
                        }
                  }
                  if (conjunto.size() != 5){
                        return false;
                  }
            }
            
            // REVISAR LAS OPERACIONES
            return true;
      }
      
      
}
