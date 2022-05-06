/**
 * Classe Main del Busca Mines
 * @author Jordi Palomino Escarrà
 * @version 1.0
 */
package cat.sapa.uf5;

import cat.sapa.uf5.Vista.Vista;

/**
 * Classe Main del Programa
 */
public class Main {
    /**
     * Inicialització del programa
     * @param args .
     */
    public static void main(String[] args) {
        Vista.tauler = new Vista(8,8,10);
    }
}
