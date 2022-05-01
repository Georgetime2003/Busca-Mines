package cat.sapa.uf5.Controlador;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controlador {
    public static MouseListener clicjoc = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {

            JButton b = (JButton) e.getSource();

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
