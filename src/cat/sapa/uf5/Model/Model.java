/**
 * Classe Model del Busca Mines
 * @author Pere Sánchez amb modificacions de Jordi Palomino Escarrà
 * @version 1.0
 */

package cat.sapa.uf5.Model;

import cat.sapa.uf5.*;
import cat.sapa.uf5.Vista.Vista;

import javax.swing.*;

/**
 * Classe principal del Model
 */
public class Model {
    /**
     *  Enter on es diuen el nombre de files de l'Array Bidimensional
     */
        public static int files;
    /**
     * Enter on es diuen el nombre de columnes de l'Array Bidimensional
     */
        public static int columnes;
    /**
     * Enter on es diuen el nombre de bombes de l'Array Bidimensional
     */
        public static int bombes;
    /**
     * ArrayBidimensional del Camp de Mines Ocult del joc
     */
        static char[][] campMines;
    /**
     * ArrayBidimensional del Camp de Mines Visible del joc
     */
        static char[][] jocCampMines;

    /**
     * Mètode per inicialitzar el Joc en el Model
     * @param f Enter amb el nombre de files
     * @param c Enter amb el nombre de columnes
     * @param b Enter amb el nombre de bombes
     */
        public static void iniciar(int f, int c, int b) {
            files = f + 2;
            columnes = c + 2;
            bombes = b;

            campMines = new char[files][columnes];
            jocCampMines = new char[files][columnes];

            inicialitzarCampMines(campMines, ' ');
            inicialitzarCampMines(jocCampMines, '·');

            posarBombes();
            comptarBombesProperes();

        }

    /**
     * Mètode per posar bandera a l'Array
     * @param fila Enter per saber la posició de la fila
     * @param columna Enter per saber la posició de la columna
     */
        public static void posarBandera(int fila, int columna) {
            if (jocCampMines[fila][columna] == '·') {
                jocCampMines[fila][columna] = 'B';
                for (int i = 1; i <= 10 - 1; ++i) {
                    for(int j = 1;j <= 10 - 1; ++j){
                        System.out.print(jocCampMines[i][j]);
                }
                    System.out.println("");
                }
                Vista.mostrarcamp(jocCampMines);

            } else if (jocCampMines[fila][columna] == 'B') {
                jocCampMines[fila][columna] = '·';
                Vista.mostrarcamp(jocCampMines);

            } else {
                JOptionPane.showMessageDialog(null,"Aqui no pots posar una bandera");
                return;
            }
            haGuanyat();
        }

    /**
     * Mètode per trepitjar una cel·la
     * @param fila Enter per saber la posició de la fila
     * @param columna Enter per saber la posició de la columna
     */
        public static void trepitjar(int fila, int columna) {
            char valor;
            if (jocCampMines[fila][columna] == '·') {
                if (campMines[fila][columna] == 'B') {
                    valor = 'B';

                    Vista.mostrarbombes(campMines);
                    JOptionPane.showMessageDialog(null, "Has trepitjat una bomba, fi del joc.");

                } else {
                    valor = campMines[fila][columna];
                    trepitjarRecursivament(fila, columna);
                    Vista.mostrarcamp(jocCampMines);
                    haGuanyat();

                }
            } else {
                JOptionPane.showMessageDialog(null, "Aqui no pots trepitjar!");
            }
        }

    /**
     * Mètode d'inicialitzacio del camp de Mines buit
     * @param cm Array bidimensional del Camp de mines Ocult o visible
     * @param c Caracter per posicionar a la cel·la
     */
    static void inicialitzarCampMines(char[][] cm, char c) {
            for (int fila = 0; fila < files; fila++) {
                for (int columna = 0; columna < columnes; columna++) {
                    cm[fila][columna] = c;
                }
            }
        }

    /**
     * Mètode per col·locar les bombes al camp de Mines Ocult
     */
    static void posarBombes() {
            int fila, columna;

            for (int i = 0; i < bombes; i++) {
                do {
                    fila = (int) (Math.random() * (files - 2) + 1);
                    columna = (int) (Math.random() * (columnes - 2) + 1);
                } while (campMines[fila][columna] == 'B');
                campMines[fila][columna] = 'B';
            }
        }

    /**
     * Mètode per posar el número de bombes que hi ha al cantó d'una cel·la
     */
    static void comptarBombesProperes() {
            int nBombes;

            for (int fila = 1; fila < files - 1; fila++) {
                for (int columna = 1; columna < columnes - 1; columna++) {
                    if (campMines[fila][columna] != 'B') {
                        nBombes = comptar1(fila, columna);
                        //nBombes = comptar2(fila, columna);
                        if (nBombes > 0) campMines[fila][columna] = (char)(nBombes + '0');
                    }
                }
            }
        }

    /**
     * Metòde per comptar el nombre de bombes
     * @param fila Enter amb la posició de la fila de la cel·la
     * @param columna Enter amb la posició de la columna de la cel·la
     * @return Enter amb el nombre de bombes que té al voltant
     */
        static int comptar1(int fila, int columna) {
            int nBombes = 0;

            if (campMines[fila][columna-1] == 'B') nBombes++;
            if (campMines[fila-1][columna-1] == 'B') nBombes++;
            if (campMines[fila+1][columna-1] == 'B') nBombes++;
            if (campMines[fila][columna+1] == 'B') nBombes++;
            if (campMines[fila+1][columna+1] == 'B') nBombes++;
            if (campMines[fila-1][columna+1] == 'B') nBombes++;
            if (campMines[fila+1][columna] == 'B') nBombes++;
            if (campMines[fila-1][columna] == 'B') nBombes++;

            return nBombes;
        }



        /**
         * Comprovar si ha trobat totes les bombes i caselles lliures.
         */
        static void haGuanyat() {
            for (int fila = 1; fila < files - 1; fila++) {
                for (int columna = 1; columna < columnes - 1; columna++) {
                    if (jocCampMines[fila][columna] != campMines[fila][columna]) return;
                }
            }
            JOptionPane.showMessageDialog(null,"Has Guanyat!");
        }


    /**
     * Mètode per buscar recursivament fins que es trobi amb un valor enter
     * @param fila Enter amb la posició de la fila de la cel·la
     * @param columna Enter amb la posició de la columna de la cel·la
     */
    static void trepitjarRecursivament(int fila, int columna) {
            if(!verificarFilaColumna(fila, columna)) return;
            if (jocCampMines[fila][columna] != '·') return;
            destapar(fila, columna);
            if (jocCampMines[fila][columna] != ' ') return;
            trepitjarRecursivament(fila - 1, columna - 1);
            trepitjarRecursivament(fila - 1, columna);
            trepitjarRecursivament(fila - 1, columna + 1);
            trepitjarRecursivament(fila, columna - 1);
            trepitjarRecursivament(fila, columna + 1);
            trepitjarRecursivament(fila + 1, columna - 1);
            trepitjarRecursivament(fila + 1, columna);
            trepitjarRecursivament(fila + 1, columna + 1);
        }

    /**
     * Mètode per destapar la casella que es trepitja
     * @param fila Enter amb la posició de la fila de la cel·la
     * @param columna Enter amb la posició de la columna de la cel·la
     */
    static void destapar(int fila, int columna) {
            jocCampMines[fila][columna] = campMines[fila][columna];
        }

    /**
     * Verifica la posició trepitjada
     * @param fila Enter amb la posició de la fila de la cel·la
     * @param columna Enter amb la posició de la columna de la cel·la
     * @return true si la comprovació és correcte o false si és el contrari
     */
        public static boolean verificarFilaColumna(int fila, int columna) {
            return fila >= 1 && fila < files - 1 && columna >= 1 && columna < columnes - 1;

        }

    }
