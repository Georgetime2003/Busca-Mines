/**
 * Classe Vista del Busca Mines
 * @author Jordi Palomino Escarrà
 * @version 1.0
 */
package cat.sapa.uf5.Vista;

import cat.sapa.uf5.Controlador.Controlador;
import cat.sapa.uf5.Model.Model;

import javax.swing.*;
import java.awt.*;

/**
 * Classe principal de la Vista del joc
 */
public class Vista  extends JFrame{
    /**
     * Marquem la Mida de la casella
     */
    private final int MIDA_CASELLA = 40;

    /**
     * Icona de la bomba
     */
    private static ImageIcon bomba = new ImageIcon("src/cat/sapa/uf5/imatges/bomb.png");

    /**
     * Array Bidimensional on guarda la posició de cada JButton
     */

    public static JButton[][] caselles;

    /**
     * Crear el mètode de la classe per quan sigui necessari reinicialitzar-se
     */
    public static Vista tauler;

    /**
     * Inicialització de la classe Vista del Joc
     * @param fila Enter per indicar les files de la taula del joc
     * @param columna Enter per indicar les columnes de la taula del joc
     * @param bombes Enter per indicar les bombes de la taula del joc
     */
    public Vista(int fila, int columna, int bombes) {

        caselles = new JButton[fila + 2][columna + 2];

        //Iniciem l'array des de Model
        Model.iniciar(fila,columna,bombes);

        //Creem la base de l'intefície gràfica
        Container panell = getContentPane();
        setTitle("Busca Mines");
        JPanel joc = new JPanel();
            joc.setLayout(null);
            joc.setSize(400,300);
            joc.setPreferredSize(new Dimension((Model.columnes - 2) * MIDA_CASELLA,(Model.files - 2) * MIDA_CASELLA));

        //Bucle per crear les caselles del joc
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
                joc.add(casella, BorderLayout.CENTER);
                caselles[i][j] = casella;
            }
        }
        panell.add(joc, BorderLayout.PAGE_START);

        //Crear els botons de configuració del joc
        JPanel pc = new JPanel(new GridLayout(2,2));
        JButton c1 = new JButton("8x8");
        c1.addActionListener(Controlador.configuracio);
        JButton c2 = new JButton("16x16");
        c2.addActionListener(Controlador.configuracio);
        JButton c3 = new JButton("16x30");
        c3.addActionListener(Controlador.configuracio);
        JButton c4 = new JButton("Configuracio");
        c4.addActionListener(Controlador.configuracio);
        pc.add(c1);
        pc.add(c2);
        pc.add(c3);
        pc.add(c4);
        panell.add(pc, BorderLayout.PAGE_END);





        //Preparar per mostrar la interfície
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);



        //Tancar el programa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Mètode per actualitzar el camp mentres no trepitgem una bomba
     * @param camp Array Bidimensional de la part visible del joc
     */
    public static void mostrarcamp(char camp[][]) {
        for(int i = 1; i < Model.files - 1; i++) {
            for (int j = 1; j < Model.columnes - 1; j++) {
                caselles[i][j].setText(Character.toString(camp[i][j]));
                if(!(caselles[i][j].getText().equals("·")||(caselles[i][j].getText().equals("B")))){
                    caselles[i][j].setEnabled(false);
                }
            }
        }
    }


    /**
     * Mètode per finalitzar el joc quan es perd la partida
     * @param camp Array Bidimensional on es situen les bombes
     */
    public static void mostrarbombes(char camp[][]) {
        for(int i = 1; i < Model.files - 1; i++) {
            for (int j = 1; j < Model.columnes - 1; j++) {
                if(camp[i][j] == 'B'){
                    if(caselles[i][j].getText().equals("B")){
                        caselles[i][j].setText("F");
                    }
                    else{
                        caselles[i][j].setText("");
                        caselles[i][j].setIcon(bomba);
                    }

                }
                else{
                    if(caselles[i][j].getText().equals("B")){
                        caselles[i][j].setText("·");
                    }
                    caselles[i][j].setEnabled(false);
                }
            }
        }
    }
}
