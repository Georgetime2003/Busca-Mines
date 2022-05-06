/**
 * Classe Configuració del Busca Mines
 * @author Jordi Palomino Escarrà
 * @version 1.0
 */
package cat.sapa.uf5.Vista;

import cat.sapa.uf5.Model.Model;

import javax.swing.*;
import java.awt.event.*;

/**
 * Classe de la finestra de configuració
 */
public class Configuració extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner files;
    private JSpinner columnes;
    private JSpinner bombes;

    /**
     * Classe de la finestra de configuració
     */
    public Configuració() {

        //Posem els valors actuals del tauler a les opcions de configuració
        files.setValue(Model.files - 2);
        columnes.setValue(Model.columnes - 2);
        bombes.setValue(Model.bombes);
        //Inicialitzem la finestra
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        buttonOK.addActionListener(new ActionListener() {
            /**
             * ActionListener quan es fa clic al botó ok i realitzar que els camps siguin correctes
             * @param e Paràmetre del botó OK
             */
            public void actionPerformed(ActionEvent e) {

                //Comprovar si el total de caselles no sigui més gran que el nombre de bombes
                if((int)files.getValue() * (int)files.getValue() < (int)bombes.getValue()){
                    JOptionPane.showMessageDialog(null,"El nombre de Files i Columnes no pot ser més petit que el nombre de bombes","Error",JOptionPane.ERROR_MESSAGE);
                }
                //Comprovar que un dels valors no sigui negatiu
                else if((int)files.getValue() < 0 || (int)files.getValue() < 0 || (int)bombes.getValue() < 0){
                    JOptionPane.showMessageDialog(null,"Un dels següents valors no pot ser negatiu","Error",JOptionPane.ERROR_MESSAGE);
                }
                //Agafem el metode per realitzar els canvis
                else{
                    onOK();
                }

            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Mètode per Reinicialitzar la Classe Vista amb les noves variables
     */
    private void onOK() {
        Vista.tauler.setVisible(false);
        Vista.tauler = new Vista((int)files.getValue(),(int)columnes.getValue(),(int)bombes.getValue());
        dispose();
    }

    private void onCancel() {

        dispose();
    }
}
