/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa2;

/**
 *
 * @author dandi
 */
public class Jugada {
      private int fila;
      private int columna;
      private String numero;
      public Jugada(int pFila, int pColumna, String pNumero){
            fila = pFila;
            columna = pColumna;
            numero = pNumero;
      }

      public int getFila() {
            return fila;
      }

      public int getColumna() {
            return columna;
      }

      public String getNumero() {
            return numero;
      }
      
      
}
