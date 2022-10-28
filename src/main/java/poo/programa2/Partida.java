/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.programa2;

import java.util.ArrayList;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * Esta clase contiene la configuración de la partida que se va a jugar.
 * @author Daniel Granados Retana, carné 2022104692 <dagranados@astudiantec.cr>
 */
public class Partida {
    private ArrayList<Operacion> operaciones; // ArrayList para guardar las desigualdades
    private ArrayList<Operacion> constantes; // ArrayList para guardar las constantes
    private static ArrayList<Partida> faciles = new ArrayList<>();
    private static ArrayList<Partida> intermedias = new ArrayList<>();
    private static ArrayList<Partida> dificiles = new ArrayList<>();
    private static HashMap<String, ArrayList<Partida>> partidasPorNivel = new HashMap<>();
    
    /**
     *
     */
    public Partida(){
        operaciones = new ArrayList<>();
        constantes = new ArrayList<>();
    }
        
    /**
     *
     * @return
     */
    public ArrayList<Operacion> getOperaciones() {
        return operaciones;
    }

    /**
     *
     * @param operaciones
     */
    public void setOperaciones(ArrayList<Operacion> operaciones) {
        this.operaciones = operaciones;
    }
    
    /**
     *
     */
    public static void leerPartidas(){
        partidasPorNivel.put("Fácil", faciles);
        partidasPorNivel.put("Intermedio", intermedias);
        partidasPorNivel.put("Difícil", dificiles);
        
        try{
            File partidasFile = new File("src\\main\\java\\poo\\programa2\\futoshiki2022partidas.xml");
            SAXReader reader = new SAXReader();
            Document document = reader.read(partidasFile);
            List<Node> nodosPartida = document.selectNodes("/partidasFutoshiki/partida");
            for (Node partidaNodo : nodosPartida){
                String nivel = partidaNodo.selectSingleNode("nivel").getText();
                List<Node> desigualdades = partidaNodo.selectNodes("des");
                Partida partida = new Partida();
                for (Node des : desigualdades){
                    String entry[] = des.getText().split(",");
                    System.out.println(entry[0] + " " + entry[1] + " " + entry[2]);
                    partida.getOperaciones().add(new Operacion(entry[0].charAt(0), 
                            Integer.parseInt(entry[1]), Integer.parseInt(entry[2])));
                }
                List<Node> constantesNodos = partidaNodo.selectNodes("cons");
                for (Node constante : constantesNodos){
                    String entry[] = constante.getText().split(",");
                    System.out.println(entry[0] + " " + entry[1] + " " + entry[2]);
                    partida.getConstantes().add(new Operacion(entry[0].charAt(0), 
                            Integer.parseInt(entry[1]), Integer.parseInt(entry[2])));
                }
                partidasPorNivel.get(nivel).add(partida);
            }
        } catch(DocumentException e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<Operacion> getConstantes() {
        return constantes;
    }

    /**
     *
     * @param constantes
     */
    public void setConstantes(ArrayList<Operacion> constantes) {
        this.constantes = constantes;
    }

    /**
     *
     * @return
     */
    public static ArrayList<Partida> getFaciles() {
        return faciles;
    }

    /**
     *
     * @return
     */
    public static ArrayList<Partida> getIntermedias() {
        return intermedias;
    }

    /**
     *
     * @return
     */
    public static ArrayList<Partida> getDificiles() {
        return dificiles;
    }

    /**
     *
     * @return
     */
    public static HashMap<String, ArrayList<Partida>> getPartidasPorNivel() {
        return partidasPorNivel;
    }
}
