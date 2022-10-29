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
 * Esta clase se usa para representar un juego de futoshiki.
 * @author Daniel Granados Retana y Diego Granados Retana
 */
public class Juego {
      private javax.swing.JButton [][] casillas;
      private Partida partida;
      private Stack<Jugada> jugadas;
      private Stack<Jugada> redoJugadas;
      private Marca marca;
      
      public Juego(Partida pPartida, String nombre, javax.swing.JButton [][] pCasillas){
            casillas = pCasillas;         
            jugadas = new Stack<>();
            marca = new Marca(nombre);
            partida = pPartida;
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
            int i = 0;
            for (Operacion constante : partida.getConstantes()){
                  if (constante.getIndiceFila() == fila && constante.getIndiceColumna() == columna){
                        JOptionPane.showMessageDialog(window, "JUGADA NO ES VÁLIDA PORQUE ESTE ES UN DÍGITO FIJO.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                        casillas[i][columna].setBackground(Color.red);
                        return;
                  }
            }
            
            OUTER:
            for (; i < partida.getOperaciones().size(); i ++) {
                  if (partida.getOperaciones().get(i).getIndiceColumna() == columna && partida.getOperaciones().get(i).getIndiceFila() == fila) {
                        switch (partida.getOperaciones().get(i).getTipo()) {
                              case 'a':
                                    try {
                                          if (Integer.valueOf(casillas[fila][columna].getText()) < Integer.valueOf(casillas[fila][columna+1].getText())) {
                                                break OUTER;
                                          } else {
                                                JOptionPane.showMessageDialog(window, "JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MENOR.", 
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                casillas[i][columna].setBackground(Color.red);
                                                return;
                                          }
                                    }
                                    catch (Exception e){
                                          break;
                                    }
                              case 'b':
                                    try {
                                          if (Integer.valueOf(casillas[fila][columna].getText()) > Integer.valueOf(casillas[fila][columna+1].getText())) {
                                                break OUTER;
                                          } else {
                                                JOptionPane.showMessageDialog(window, "JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MAYOR.", 
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                casillas[i][columna].setBackground(Color.red);
                                                return;
                                          }
                                    }
                                    catch (Exception e){
                                          break;
                                    }
                              case 'y':
                                    try {
                                          if (Integer.valueOf(casillas[fila][columna].getText()) > Integer.valueOf(casillas[fila+1][columna].getText())) {
                                                break OUTER;
                                          } else {
                                                JOptionPane.showMessageDialog(window, "JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MAYOR.", 
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                casillas[i][columna].setBackground(Color.red);
                                                return;
                                          }
                                    }
                                    catch (Exception e){
                                          break;
                                    }
                              case 'z':
                                    try {
                                          if (Integer.valueOf(casillas[fila][columna].getText()) < Integer.valueOf(casillas[fila+1][columna].getText())) {
                                                break OUTER;
                                          } else {
                                                JOptionPane.showMessageDialog(window, "JUGADA NO ES VÁLIDA PORQUE NO CUMPLE CON LA RESTRICCIÓN DE MENOR.", 
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                                casillas[i][columna].setBackground(Color.red);
                                                return;
                                          }
                                    }
                                    catch (Exception e){
                                          break;
                                    }
                              default:
                                    break;
                        }
                        
                  }
            }
            
            jugadas.push(new Jugada(fila,columna,numero)); // mete la jugada a la pila de jugadas para luego hacer el undo si se requiere
            if (!numero.equals("Borrar")){  // se altera el texto de la casilla
                  casillas[fila][columna].setText(numero);  
            } else {
                   
                  casillas[fila][columna].setText("");      
            }
            casillas[fila][columna].setBackground(Color.white);
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
            for (Operacion operacion : partida.getOperaciones()) {
                  switch (operacion.getTipo()) {
                        case 'a':
                              if (!(Integer.valueOf(casillas[operacion.getIndiceFila()][operacion.getIndiceColumna()].getText()) < Integer.valueOf(casillas[operacion.getIndiceFila()][operacion.getIndiceColumna()+1].getText()))) {
                                    return false;
                              }
                        case 'b':
                              if (!(Integer.valueOf(casillas[operacion.getIndiceFila()][operacion.getIndiceColumna()].getText()) > Integer.valueOf(casillas[operacion.getIndiceFila()][operacion.getIndiceColumna()+1].getText()))) {
                                    return false;
                              }
                        case 'y':
                              if (!(Integer.valueOf(casillas[operacion.getIndiceFila()][operacion.getIndiceColumna()].getText()) > Integer.valueOf(casillas[operacion.getIndiceFila()+1][operacion.getIndiceColumna()].getText()))) {
                                    return false;
                              }
                        case 'z':
                              if (!(Integer.valueOf(casillas[operacion.getIndiceFila()][operacion.getIndiceColumna()].getText()) < Integer.valueOf(casillas[operacion.getIndiceFila()+1][operacion.getIndiceColumna()].getText()))) {
                                    return false;
                              }
                        default:
                              break;
                  }
            }
            return true;
      }
      
      
}
