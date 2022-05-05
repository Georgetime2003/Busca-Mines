package cat.sapa.uf5.Controlador;

import cat.sapa.uf5.Model.Model;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controlador {
    public static MouseListener clicjoc = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {

            JButton b = (JButton) e.getSource();

            if(e.getButton() == MouseEvent.BUTTON3){
                int bandera = Model.posarBandera(((int)b.getClientProperty("fila")),((int)b.getClientProperty("columna")));
                if(bandera == 0){
                    b.setText("B");
                }
                else if (bandera == 1){
                    b.setText("·");
                }
                else{
                    JOptionPane.showMessageDialog(null, "No pots posar bandera!");
                }


            }
            else {
                char fi = Model.trepitjar(((int)b.getClientProperty("fila")),((int)b.getClientProperty("columna")));
                if (fi == 'B'){
                    b.setText("B");
                    JOptionPane.showMessageDialog(null, "Has trepitjat una bomba, fi del joc.");
                }
                else if(fi == 'N'){
                    JOptionPane.showMessageDialog(null, "Aqui no pots trepitjar!");
                }

            }

            super.mousePressed(e);
            System.out.println(b.getClientProperty("fila") + "," + b.getClientProperty("columna"));

        }
    };
    public static MouseListener clicopcions = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
        }
    };
}
