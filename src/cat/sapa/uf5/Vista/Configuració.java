package cat.sapa.uf5.Vista;

import cat.sapa.uf5.Model.Model;

import javax.swing.*;
import java.awt.event.*;

public class Configuració extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner files;
    private JSpinner columnes;
    private JSpinner bombes;

    public Configuració() {
        files.setValue(Model.files);
        columnes.setValue(Model.columnes);
        bombes.setValue(Model.bombes);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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

    private void onOK() {
        Model.bombes = (int) bombes.getValue();
        Model.columnes = (int) columnes.getValue();
        Model.files = (int) files.getValue();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Configuració dialog = new Configuració();
        dialog.pack();
        dialog.setVisible(true);

        System.exit(0);
    }
}
