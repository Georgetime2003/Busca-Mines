package cat.sapa.uf5.Model;

import cat.sapa.uf5.*;
import cat.sapa.uf5.Vista.Vista;

public class Model {
        public static int files;
        public static int columnes;
        public static int bombes;
        static char[][] campMines;
        static char[][] jocCampMines;
        static boolean fi;
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

            //Vista.mostrarCampMines(campMines);
            //Vista.mostrarCampMines(jocCampMines);

            fi = false;
        }
        public static int posarBandera(int fila, int columna) {
            if (jocCampMines[fila][columna] == '·') {
                jocCampMines[fila][columna] = 'B';
                for (int i = 1; i <= 10 - 1; ++i) {
                    for(int j = 1;j <= 10 - 1; ++j){
                        System.out.print(jocCampMines[i][j]);
                }
                    System.out.println("");
                }
                return 0;
                //Vista.mostrarCampMines(jocCampMines);
            } else if (jocCampMines[fila][columna] == 'B') {
                jocCampMines[fila][columna] = '·';
                return 1;
                //Vista.mostrarCampMines(jocCampMines);
            } else {
                System.out.println("Aqui no pots posar una bandera");
            }
            haGuanyat();
            return 2;
        }
        public static char trepitjar(int fila, int columna) {
            char valor;
            if (jocCampMines[fila][columna] == '·') {
                if (campMines[fila][columna] == 'B') {
                    fi = true;
                    valor = 'B';
                    //mostrarBombes();

                    //Vista.mostrarCampMines(jocCampMines);
                    //Vista.mostrarMissatge("HAS TREPITJAT UNA BOMBA !!!");
                    return valor;
                } else {
                    valor = campMines[fila][columna];
                    //trepitjarVoltant(fila, columna);
                    trepitjarRecursivament(fila, columna);
                    Vista.mostrarcamp(jocCampMines);
                    //Vista.mostrarCampMines(jocCampMines);
                    haGuanyat();
                    return valor;
                }
            } else {
                System.out.println("Aqui no pots trepitjar");
                return 'N';
            }
        }
        public static boolean haAcabat() {
            return fi;
        }

        static void inicialitzarCampMines(char[][] cm, char c) {
            for (int fila = 0; fila < files; fila++) {
                for (int columna = 0; columna < columnes; columna++) {
                    cm[fila][columna] = c;
                }
            }
        }


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
         * Comptar les bombes del voltant utilitzant bucles
         * @param fila Fila central
         * @param columna Columna central
         * @return Nombre de bombes que hi ha al voltant
         */
        static int comptar2(int fila, int columna) {
            int nBombes = 0;

            for (int f = fila - 1; f <= fila + 1; ++f) {
                for (int c = columna - 1; c <= columna + 1; ++c) {
                    if (campMines[f][c] == 'B') nBombes++;
                }
            }

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
            fi = true;
            Vista.mostrarMissatge("HAS GUANYAT !!!");
        }


        static void mostrarBombes() {
            for (int fila = 1; fila < files - 1; fila++) {
                for (int columna = 1; columna < columnes - 1; columna++) {
                    if (campMines[fila][columna] == 'B' && jocCampMines[fila][columna] != 'B')
                        jocCampMines[fila][columna] = '*';
                    if (jocCampMines[fila][columna] == 'B' && campMines[fila][columna] != 'B')
                        jocCampMines[fila][columna] = '/';
                }
            }
        }


        static void trepitjarVoltant(int fila, int columna) {
            if (jocCampMines[fila][columna] != '·') return;

            destapar(fila, columna);

            if (campMines[fila][columna] == ' ') {
                destapar(fila - 1, columna - 1);
                destapar(fila - 1, columna);
                destapar(fila - 1, columna + 1);
                destapar(fila, columna - 1);
                destapar(fila, columna + 1);
                destapar(fila + 1, columna - 1);
                destapar(fila + 1, columna);
                destapar(fila + 1, columna + 1);
            }
        }


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


        static void destapar(int fila, int columna) {
            jocCampMines[fila][columna] = campMines[fila][columna];
        }


        public static boolean verificarFilaColumna(int fila, int columna) {
            return fila >= 1 && fila < files - 1 && columna >= 1 && columna < columnes - 1;

        }

    }
