package umg.edu.progra.arboles;

/**
 * Arbol Binario de Busqueda (BST) implementado manualmente,
 * sin utilizar java.util ni librerias externas.
 *
 * Reglas del BST:
 *  - Para cada nodo N, todos los valores del subarbol izquierdo
 *    son MENORES que N.dato.
 *  - Para cada nodo N, todos los valores del subarbol derecho
 *    son MAYORES que N.dato.
 *  - No se permiten duplicados (se ignoran al insertar).
 *
 * @author Walter Cordova
 */
public class ArbolBinarioBusqueda {

    private Nodo raiz;
    private int tamanio;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
        this.tamanio = 0;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int tamanio() {
        return tamanio;
    }

    /**
     * Inserta un valor en el arbol respetando la propiedad del BST.
     * Si el valor ya existe se ignora (no se insertan duplicados).
     */
    public void insertar(int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            tamanio++;
            return;
        }
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            tamanio++;
            return new Nodo(valor);
        }
        if (valor < actual.dato) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.dato) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        }
        return actual;
    }

    /**
     * Busca un valor dentro del arbol. Devuelve el Nodo si existe
     * o null si no se encuentra.
     */
    public Nodo buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private Nodo buscarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor == actual.dato) {
            return actual;
        }
        if (valor < actual.dato) {
            return buscarRecursivo(actual.izquierdo, valor);
        }
        return buscarRecursivo(actual.derecho, valor);
    }

    public boolean contiene(int valor) {
        return buscar(valor) != null;
    }

    /**
     * Elimina un valor del arbol. Cubre los 3 casos clasicos:
     *  1. Nodo hoja (sin hijos)
     *  2. Nodo con un solo hijo
     *  3. Nodo con dos hijos (se reemplaza por el sucesor inorden:
     *     el menor del subarbol derecho).
     */
    public boolean eliminar(int valor) {
        int tamanioPrevio = tamanio;
        raiz = eliminarRecursivo(raiz, valor);
        return tamanio < tamanioPrevio;
    }

    private Nodo eliminarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor < actual.dato) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.dato) {
            actual.derecho = eliminarRecursivo(actual.derecho, valor);
        } else {
            // Nodo encontrado
            if (actual.izquierdo == null && actual.derecho == null) {
                tamanio--;
                return null;
            }
            if (actual.izquierdo == null) {
                tamanio--;
                return actual.derecho;
            }
            if (actual.derecho == null) {
                tamanio--;
                return actual.izquierdo;
            }
            // Nodo con dos hijos: se reemplaza con el sucesor inorden
            int sucesor = minimo(actual.derecho);
            actual.dato = sucesor;
            actual.derecho = eliminarRecursivo(actual.derecho, sucesor);
        }
        return actual;
    }

    /**
     * Devuelve el valor minimo del arbol (el nodo mas a la izquierda).
     */
    public int minimo() {
        if (raiz == null) {
            throw new IllegalStateException("El arbol esta vacio");
        }
        return minimo(raiz);
    }

    private int minimo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual.dato;
    }

    /**
     * Devuelve el valor maximo del arbol (el nodo mas a la derecha).
     */
    public int maximo() {
        if (raiz == null) {
            throw new IllegalStateException("El arbol esta vacio");
        }
        Nodo actual = raiz;
        while (actual.derecho != null) {
            actual = actual.derecho;
        }
        return actual.dato;
    }

    /**
     * Altura del arbol: cantidad de aristas del camino mas largo
     * desde la raiz hasta una hoja. Un arbol vacio tiene altura -1.
     * Un arbol con solo raiz tiene altura 0.
     */
    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        int izq = alturaRecursiva(nodo.izquierdo);
        int der = alturaRecursiva(nodo.derecho);
        return 1 + (izq > der ? izq : der);
    }

    /**
     * Cuenta cuantos nodos hoja (sin hijos) tiene el arbol.
     */
    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return 1;
        }
        return contarHojasRecursivo(nodo.izquierdo) + contarHojasRecursivo(nodo.derecho);
    }

    // ============================================================
    // RECORRIDOS DEL ARBOL
    // ============================================================

    /**
     * Recorrido InOrden: Izquierdo -> Raiz -> Derecho.
     * En un BST imprime los valores ordenados de menor a mayor.
     */
    public void inOrden() {
        inOrdenRecursivo(raiz);
        System.out.println();
    }

    private void inOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        inOrdenRecursivo(nodo.izquierdo);
        System.out.print(nodo.dato + " ");
        inOrdenRecursivo(nodo.derecho);
    }

    /**
     * Recorrido PreOrden: Raiz -> Izquierdo -> Derecho.
     * Util para clonar el arbol.
     */
    public void preOrden() {
        preOrdenRecursivo(raiz);
        System.out.println();
    }

    private void preOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        System.out.print(nodo.dato + " ");
        preOrdenRecursivo(nodo.izquierdo);
        preOrdenRecursivo(nodo.derecho);
    }

    /**
     * Recorrido PostOrden: Izquierdo -> Derecho -> Raiz.
     * Util para liberar/eliminar el arbol.
     */
    public void postOrden() {
        postOrdenRecursivo(raiz);
        System.out.println();
    }

    private void postOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        postOrdenRecursivo(nodo.izquierdo);
        postOrdenRecursivo(nodo.derecho);
        System.out.print(nodo.dato + " ");
    }

    /**
     * Recorrido por niveles (BFS) implementado con una cola casera
     * (sin usar java.util). Imprime el arbol por anchura.
     */
    public void recorridoPorNiveles() {
        if (raiz == null) {
            System.out.println();
            return;
        }
        ColaNodos cola = new ColaNodos();
        cola.encolar(raiz);
        while (!cola.estaVacia()) {
            Nodo actual = cola.desencolar();
            System.out.print(actual.dato + " ");
            if (actual.izquierdo != null) {
                cola.encolar(actual.izquierdo);
            }
            if (actual.derecho != null) {
                cola.encolar(actual.derecho);
            }
        }
        System.out.println();
    }

    /**
     * Imprime el arbol de forma jerarquica y visual en consola
     * (rotado 90 grados: la raiz queda a la izquierda).
     */
    public void imprimirArbol() {
        if (raiz == null) {
            System.out.println("(arbol vacio)");
            return;
        }
        imprimirArbolRecursivo(raiz, 0);
    }

    private void imprimirArbolRecursivo(Nodo nodo, int nivel) {
        if (nodo == null) {
            return;
        }
        imprimirArbolRecursivo(nodo.derecho, nivel + 1);
        for (int i = 0; i < nivel; i++) {
            System.out.print("     ");
        }
        System.out.println("-> " + nodo.dato);
        imprimirArbolRecursivo(nodo.izquierdo, nivel + 1);
    }

    // ============================================================
    // PROBLEMA 1 — Contar nodos recursivamente
    // ============================================================

    /**
     * Devuelve la cantidad total de nodos del arbol usando recursividad.
     * No utiliza el campo tamanio; lo cuenta recorriendo el arbol.
     */
    public int contarNodos() {
        return contarNodosRecursivo(raiz);
    }

    private int contarNodosRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodosRecursivo(nodo.izquierdo) + contarNodosRecursivo(nodo.derecho);
    }

    // ============================================================
    // PROBLEMA 2 — ¿Está balanceado?
    // ============================================================

    /**
     * Indica si el arbol esta balanceado en altura.
     * Un arbol esta balanceado si para cada nodo la diferencia
     * de altura entre su subarbol izquierdo y derecho es <= 1.
     */
    public boolean esBalanceado() {
        return alturaBalanceada(raiz) != -2;
    }

    /**
     * Devuelve la altura del subarbol si esta balanceado,
     * o -2 como centinela si en algun punto NO esta balanceado.
     * Esto evita recalcular la altura dos veces por nodo.
     */
    private int alturaBalanceada(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        int altIzq = alturaBalanceada(nodo.izquierdo);
        if (altIzq == -2) {
            return -2; // ya se detecto desbalance arriba
        }
        int altDer = alturaBalanceada(nodo.derecho);
        if (altDer == -2) {
            return -2;
        }
        int diferencia = altIzq - altDer;
        if (diferencia > 1 || diferencia < -1) {
            return -2; // desbalanceado en este nodo
        }
        return 1 + (altIzq > altDer ? altIzq : altDer);
    }

    // ============================================================
    // PROBLEMA 3 — Validar que sea un BST
    // ============================================================

    /**
     * Verifica que el arbol cumple la propiedad de BST:
     * todo el subarbol izquierdo es menor que la raiz y
     * todo el subarbol derecho es mayor que la raiz, en cada nivel.
     */
    public boolean esBSTValido() {
        return esBSTValidoRecursivo(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Recorre el arbol pasando el rango permitido (min, max) para
     * cada nodo. Si el dato del nodo cae fuera del rango, no es BST valido.
     */
    private boolean esBSTValidoRecursivo(Nodo nodo, int min, int max) {
        if (nodo == null) {
            return true;
        }
        if (nodo.dato <= min || nodo.dato >= max) {
            return false;
        }
        return esBSTValidoRecursivo(nodo.izquierdo, min, nodo.dato)
            && esBSTValidoRecursivo(nodo.derecho, nodo.dato, max);
    }

    // ============================================================
    // PROBLEMA 4 — Ancestro Comun Mas Bajo (LCA)
    // ============================================================

    /**
     * Devuelve el dato del nodo que es el Ancestro Comun Mas Bajo
     * (Lowest Common Ancestor) de los valores a y b.
     * Aprovecha la propiedad del BST para navegar eficientemente.
     *
     * @throws IllegalArgumentException si a o b no existen en el arbol.
     */
    public int ancestroComunMasBajo(int a, int b) {
        if (!contiene(a)) {
            throw new IllegalArgumentException("El valor " + a + " no existe en el arbol.");
        }
        if (!contiene(b)) {
            throw new IllegalArgumentException("El valor " + b + " no existe en el arbol.");
        }
        return lcaRecursivo(raiz, a, b);
    }

    private int lcaRecursivo(Nodo nodo, int a, int b) {
        if (nodo == null) {
            throw new IllegalStateException("No se encontro el LCA (arbol inconsistente).");
        }
        // Si ambos valores son menores, el LCA esta en el subarbol izquierdo
        if (a < nodo.dato && b < nodo.dato) {
            return lcaRecursivo(nodo.izquierdo, a, b);
        }
        // Si ambos valores son mayores, el LCA esta en el subarbol derecho
        if (a > nodo.dato && b > nodo.dato) {
            return lcaRecursivo(nodo.derecho, a, b);
        }
        // En cualquier otro caso (uno a cada lado, o uno es igual al nodo actual),
        // el nodo actual es el LCA
        return nodo.dato;
    }

    // ============================================================
    // PROBLEMA 5 — Espejo del arbol (inversion)
    // ============================================================

    /**
     * Invierte el arbol: intercambia los subarboles izquierdo y derecho
     * en todos los nodos (genera el espejo/reflejo del arbol original).
     */
    public void invertir() {
        invertirRecursivo(raiz);
    }

    private void invertirRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        // Intercambiar hijos
        Nodo temporal = nodo.izquierdo;
        nodo.izquierdo = nodo.derecho;
        nodo.derecho = temporal;
        // Invertir recursivamente cada subarbol
        invertirRecursivo(nodo.izquierdo);
        invertirRecursivo(nodo.derecho);
    }

    // ============================================================
    // EXTRA E1 — k-esimo menor
    // ============================================================

    /**
     * Devuelve el k-esimo valor mas pequenio del arbol (k=1 es el minimo).
     * Usa un recorrido inOrden contando nodos visitados.
     *
     * @throws IllegalArgumentException si k es menor que 1 o mayor que el tamanio.
     */
    public int kEsimoMenor(int k) {
        if (k < 1 || k > tamanio) {
            throw new IllegalArgumentException(
                "k debe estar entre 1 y " + tamanio + ". Valor recibido: " + k);
        }
        // Usamos un arreglo de un elemento como contador mutable en la recursion
        int[] contador = {0};
        int[] resultado = {Integer.MIN_VALUE};
        kEsimoMenorRecursivo(raiz, k, contador, resultado);
        return resultado[0];
    }

    private void kEsimoMenorRecursivo(Nodo nodo, int k, int[] contador, int[] resultado) {
        if (nodo == null || contador[0] >= k) {
            return;
        }
        kEsimoMenorRecursivo(nodo.izquierdo, k, contador, resultado);
        contador[0]++;
        if (contador[0] == k) {
            resultado[0] = nodo.dato;
            return;
        }
        kEsimoMenorRecursivo(nodo.derecho, k, contador, resultado);
    }

    // ============================================================
    // EXTRA E2 — Imprimir rango ordenado
    // ============================================================

    /**
     * Imprime en orden todos los valores del arbol que esten en el rango [min, max].
     * Optimizado: evita bajar por subarboles que no pueden contener valores en el rango.
     */
    public void imprimirRangoOrdenado(int min, int max) {
        imprimirRangoRecursivo(raiz, min, max);
        System.out.println();
    }

    private void imprimirRangoRecursivo(Nodo nodo, int min, int max) {
        if (nodo == null) {
            return;
        }
        // Solo ir a la izquierda si puede haber valores >= min
        if (nodo.dato > min) {
            imprimirRangoRecursivo(nodo.izquierdo, min, max);
        }
        // Imprimir este nodo si cae dentro del rango
        if (nodo.dato >= min && nodo.dato <= max) {
            System.out.print(nodo.dato + " ");
        }
        // Solo ir a la derecha si puede haber valores <= max
        if (nodo.dato < max) {
            imprimirRangoRecursivo(nodo.derecho, min, max);
        }
    }

    // ============================================================
    // EXTRA E3 — Diametro del arbol
    // ============================================================

    /**
     * Devuelve el diametro del arbol: el camino mas largo en aristas
     * entre dos nodos cualesquiera. El camino no necesariamente pasa
     * por la raiz.
     */
    public int diametro() {
        int[] maxDiametro = {0};
        diametroRecursivo(raiz, maxDiametro);
        return maxDiametro[0];
    }

    /**
     * Calcula la altura del subarbol y actualiza el diametro maximo
     * en cada nodo. El diametro que pasa por un nodo =
     * altura(izq) + altura(der) + 2.
     */
    private int diametroRecursivo(Nodo nodo, int[] maxDiametro) {
        if (nodo == null) {
            return -1;
        }
        int altIzq = diametroRecursivo(nodo.izquierdo, maxDiametro);
        int altDer = diametroRecursivo(nodo.derecho, maxDiametro);

        // Diametro que pasa por este nodo
        int diametroActual = altIzq + altDer + 2;
        if (diametroActual > maxDiametro[0]) {
            maxDiametro[0] = diametroActual;
        }

        // Devolver la altura de este subarbol
        return 1 + (altIzq > altDer ? altIzq : altDer);
    }

    // ============================================================
    // COLA INTERNA (lista enlazada simple) usada para BFS.
    // Se implementa aqui para NO depender de java.util.
    // ============================================================

    private static class NodoCola {
        Nodo valor;
        NodoCola siguiente;

        NodoCola(Nodo valor) {
            this.valor = valor;
        }
    }

    private static class ColaNodos {
        private NodoCola frente;
        private NodoCola fondo;

        boolean estaVacia() {
            return frente == null;
        }

        void encolar(Nodo n) {
            NodoCola nuevo = new NodoCola(n);
            if (frente == null) {
                frente = fondo = nuevo;
            } else {
                fondo.siguiente = nuevo;
                fondo = nuevo;
            }
        }

        Nodo desencolar() {
            if (frente == null) {
                throw new IllegalStateException("Cola vacia");
            }
            Nodo valor = frente.valor;
            frente = frente.siguiente;
            if (frente == null) {
                fondo = null;
            }
            return valor;
        }
    }
}