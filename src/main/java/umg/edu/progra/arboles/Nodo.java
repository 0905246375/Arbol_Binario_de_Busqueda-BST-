package umg.edu.progra.arboles;

/**
 * Nodo de un arbol binario de busqueda (BST).
 *
 * Cada nodo guarda un dato entero y dos enlaces:
 *  - izquierdo: subarbol con valores menores
 *  - derecho:   subarbol con valores mayores
 *
 * @author Walter Cordova
 */
public class Nodo {

    int dato;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(int dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Nodo(int dato, Nodo izquierdo, Nodo derecho) {
        this.dato = dato;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    public int leerDato() {
        return dato;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    @Override
    public String toString() {
        return "(" + (izquierdo != null ? izquierdo.toString() : ".")
                + " " + dato + " "
                + (derecho != null ? derecho.toString() : ".") + ")";
    }
}
