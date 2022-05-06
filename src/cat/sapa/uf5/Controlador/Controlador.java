/**
 * Classe Controlador del Busca Mines
 * @author Jordi Palomino Escarrà
 * @version 1.0
 */
package cat.sapa.uf5.Controlador;

import cat.sapa.uf5.Model.Model;
import cat.sapa.uf5.Vista.Configuració;
import cat.sapa.uf5.Vista.Vista;

import javax.swing.*;
import java.awt.event.*;

/**
 * Classe principal del Controlador
 */
public class Controlador {
    /**
     * Mètode per quan es fa clic en alguna casella del joc
     */
    public static MouseListener clicjoc = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            //Agafem l'origen del botó
            JButton b = (JButton) e.getSource();


            //Comprovem si s'ha fet clic dret o no per afegir bandera o trepitjar
            if(e.getButton() == MouseEvent.BUTTON3){
                //Enviem la posició al Model
                Model.posarBandera(((int)b.getClientProperty("fila")),((int)b.getClientProperty("columna")));
            }

            else {
                Model.trepitjar(((int)b.getClientProperty("fila")),((int)b.getClientProperty("columna")));

            }
        }
    };

    /**
     * Mètode per la configuració del joc
     */
    public static ActionListener configuracio = new ActionListener() {
        @Override
        /**
         * Mètode per comprovar quin botó s'ha seleccionat
         */
        public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                switch (b.getText()){
                    //Segons el text de la casella reinicialitzem la clase Vista amb les noves variables
                    case "8x8":
                        Vista.tauler.setVisible(false);
                        Vista.tauler = new Vista(8,8,10);
                        break;
                    case "16x16":
                        Vista.tauler.setVisible(false);
                        Vista.tauler = new Vista(16,16,40);
                        break;
                    case "16x30":
                        Vista.tauler.setVisible(false);
                        Vista.tauler = new Vista(16,30,99);
                        break;

                    //Cridem la classe Configuració per mostrar la finestra
                    default:
                        Configuració conf = new Configuració();
                        conf.pack();
                        conf.setResizable(false);
                        conf.setLocationRelativeTo(null);
                        conf.setVisible(true);
            }
        }
    };


}
