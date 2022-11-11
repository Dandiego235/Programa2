/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package poo.programa2;
import poo.programa2.GUI.Menu;
   
/**
 * Programa 2 de POO. Futushiki
 * @author Daniel Granados Retana, carné 2022104692, y Diego Granados Retana, carné 2022158363
 */
public class Programa2 {

    public static void main(String[] args) {
        Partida.leerPartidas();
        Marca.createHashMap();
        new Menu().setVisible(true);
    }
}
