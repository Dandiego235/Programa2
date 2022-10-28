/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa2;

/**
 *
 * @author dandi
 */
public class Marca {
      private String nombre;
      private double tiempo; // SUBJECT TO CHANGE
      public Marca (String pNombre){
            nombre = pNombre;
            tiempo = 0;
      }

      public String getNombre() {
            return nombre;
      }

      public double getTiempo() {
            return tiempo;
      }

      public void setNombre(String nombre) {
            this.nombre = nombre;
      }

      public void setTiempo(double tiempo) {
            this.tiempo = tiempo;
      }
      
      
}
