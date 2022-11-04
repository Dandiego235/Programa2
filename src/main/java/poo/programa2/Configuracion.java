/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa2;

/**
 * Clase para guardar los parámetros de la configuración.
 * @author Daniel Granados Retana y Diego Granados Retana
 */
public class Configuracion {
    private static String nivel = "Fácil";
    private static String reloj = "Sí";
    private static boolean lado = true; // true es derecha e izquierda es false

    public static String getNivel() {
        return nivel;
    }

    public static void setNivel(String nivel) {
        Configuracion.nivel = nivel;
    }

    public static String getReloj() {
        return reloj;
    }

    public static void setReloj(String reloj) {
        Configuracion.reloj = reloj;
    }

    public static boolean getLado() {
        return lado;
    }

    public static void setLado(boolean lado) {
        Configuracion.lado = lado;
    }
}
