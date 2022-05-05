package cat.sapa.uf5.Vista;

import cat.sapa.uf5.Controlador.Controlador;
import cat.sapa.uf5.Model.Model;

import javax.swing.*;
import java.awt.*;

public class Vista  extends JFrame{
    private final int MIDA_CASELLA = 40;
    private static final JButton[][] caselles = new JButton[10][10];
    //private final JButton panell1;
    private int amplada;
    public Vista() {
        Model.iniciar(8,8,10);
        Container panell = getContentPane();
        setTitle("Busca Mines");
        JPanel joc = new JPanel();
            joc.setLayout(null);
            joc.setSize(400,300);
            joc.setPreferredSize(new Dimension((Model.columnes - 2) * MIDA_CASELLA,(Model.files - 2) * MIDA_CASELLA));
        JButton casella;

        for(int i = 1; i < Model.files - 1; i++) {
            for (int j = 1; j < Model.columnes - 1; j++) {
                casella = new JButton("");
                casella.setSize(25,25);
                casella.setMargin(new Insets(0,0,0,0));
                casella.setBounds((j - 1) * MIDA_CASELLA,(i - 1) * MIDA_CASELLA,MIDA_CASELLA,MIDA_CASELLA);
                casella.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(118, 173, 255)));
                casella.putClientProperty("fila", i);
                casella.putClientProperty("columna", j);
                casella.addMouseListener(Controlador.clicjoc);
                casella.setText("·");
                panell.add(casella, BorderLayout.CENTER);
                caselles[i][j] = casella;
            }
        }

        //panell1 = new JButton();
        //    panell1.setPreferredSize(new Dimension(30, 10));
        panell.add(joc, BorderLayout.CENTER);



        //Preparar per mostrar la interfície
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);



        //Tancar el programa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void mostrarMissatge(String missatge){
        JOptionPane.showMessageDialog(null, missatge);
    }

    public static void mostrarcamp(char camp[][]) {
        for(int i = 1; i < 10 - 1; i++) {
            for (int j = 1; j < 10 - 1; j++) {
                caselles[i][j].setText(Character.toString(camp[i][j]));
                if(!(caselles[i][j].getText().equals("·"))){
                    caselles[i][j].setEnabled(false);
                }
            }
        }
    }
}
