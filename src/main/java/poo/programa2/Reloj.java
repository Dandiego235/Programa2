/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa2;

/**
 * Esta clase implementa las funcionalidades para el reloj y el temporizador
 * @author Daniel Granados Retana, carné 2022104692, y Diego Granados Retana, carné 2022158363
 */
public class Reloj {
    private static int horas = 0;
    private static int minutos = 0;
    private static int segundos = 0;

    public static String getHoras() {
        return "" + horas;
    }

    public static void setHoras(String horasStr) {
        horas = Integer.parseInt(horasStr);
    }

    public static String getMinutos() {
        return "" + minutos;
    }

    public static void setMinutos(String minutosStr) {
        minutos = Integer.parseInt(minutosStr);
    }

    public static String getSegundos() {
        return "" + segundos;
    }

    public static void setSegundos(String segundosStr) {
        segundos = Integer.parseInt(segundosStr);
    }
    
    /**
     * Función para validar si una entrada es máxima para ser minutos, segundos u horas.
     * @param entrada con el string a convertir a entero.
     * @param mínimo del rango posible
     * @param máximo del rango posible
     * @throws NumberFormatException
     * @throws RangeException 
     */
    public static void validarEntrada(String entrada, int min, int max) throws NumberFormatException, RangeException{
        int number;
        try{
            System.out.println(entrada);
            number = Integer.parseInt(entrada);
            System.out.println(number);
        } catch (NumberFormatException e){
            throw new NumberFormatException();
        }
        if (min > number || max < number ){
            throw new RangeException("Número fuera de rango");
        }
    }
}
