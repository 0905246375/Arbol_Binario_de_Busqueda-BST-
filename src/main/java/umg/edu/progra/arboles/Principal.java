package umg.edu.progra.arboles;

/**
 * Clase principal que demuestra el uso del Arbol Binario de Busqueda (BST)
 * implementado manualmente, sin usar librerias como java.util.
 *
 * Ejecucion sugerida:
 *   1. mvn compile
 *   2. mvn exec:java -Dexec.mainClass="umg.edu.progra.arboles.Principal"
 *
 * @author Walter Cordova
 */
public class Principal {

    public static void main(String[] args) {

        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        /*
         * Insertamos estos valores para formar el siguiente BST:
         *
         *               50
         *              /  \
         *            30    70
         *           /  \   / \
         *          20  40 60  80
         *         /
         *        10
         */
        int[] valores = { 50, 30, 70, 20, 40, 60, 80, 10 };
        for (int v : valores) {
            arbol.insertar(v);
        }
        System.out.println("===== Arbol Binario de Busqueda =====");
        System.out.println("Tamanio: " + arbol.tamanio());
        System.out.println("Altura:  " + arbol.altura());
        System.out.println("Minimo:  " + arbol.minimo());
        System.out.println("Maximo:  " + arbol.maximo());
        System.out.println("Hojas:   " + arbol.contarHojas());
 
        System.out.println("\n--- Representacion visual (rotada 90 grados) ---");
        arbol.imprimirArbol();
 
        System.out.println("\n--- Recorridos ---");
        System.out.print("InOrden    (ascendente): ");
        arbol.inOrden();
 
        System.out.print("PreOrden   (raiz primero): ");
        arbol.preOrden();
 
        System.out.print("PostOrden  (raiz al final): ");
        arbol.postOrden();
 
        System.out.print("Por niveles (BFS):         ");
        arbol.recorridoPorNiveles();
 
        System.out.println("\n--- Busquedas ---");
        System.out.println("Contiene 40? " + arbol.contiene(40));
        System.out.println("Contiene 99? " + arbol.contiene(99));
 
        System.out.println("\n--- Eliminacion ---");
        System.out.println("Eliminando 20 (nodo con 1 hijo)...");
        arbol.eliminar(20);
        System.out.print("InOrden tras eliminar 20: ");
        arbol.inOrden();
 
        System.out.println("Eliminando 30 (nodo con 2 hijos)...");
        arbol.eliminar(30);
        System.out.print("InOrden tras eliminar 30: ");
        arbol.inOrden();
 
        System.out.println("Eliminando 50 (raiz)...");
        arbol.eliminar(50);
        System.out.print("InOrden tras eliminar la raiz: ");
        arbol.inOrden();
 
        System.out.println("\n--- Estado final ---");
        arbol.imprimirArbol();
        System.out.println("Tamanio final: " + arbol.tamanio());
        System.out.println("Altura final:  " + arbol.altura());
 
        // ============================================================
        // Reconstruimos el arbol original para los problemas
        // ============================================================
        ArbolBinarioBusqueda bst = new ArbolBinarioBusqueda();
        int[] originales = { 50, 30, 70, 20, 40, 60, 80, 10 };
        for (int v : originales) {
            bst.insertar(v);
        }
 
        // ============================================================
        // PROBLEMA 1 — Contar nodos recursivamente
        // ============================================================
        System.out.println("\n========================================");
        System.out.println("  PROBLEMA 1: contarNodos recursivo");
        System.out.println("========================================");
        System.out.println("Nodos contados recursivamente : " + bst.contarNodos());
        System.out.println("Tamanio segun campo tamanio() : " + bst.tamanio());
        System.out.println("Coinciden? " + (bst.contarNodos() == bst.tamanio()));
 
        // Insertar y eliminar para verificar que sigue coincidiendo
        bst.insertar(25);
        System.out.println("\nDespues de insertar 25:");
        System.out.println("  contarNodos() = " + bst.contarNodos());
        System.out.println("  tamanio()     = " + bst.tamanio());
        System.out.println("  Coinciden?    " + (bst.contarNodos() == bst.tamanio()));
 
        bst.eliminar(25);
        System.out.println("\nDespues de eliminar 25:");
        System.out.println("  contarNodos() = " + bst.contarNodos());
        System.out.println("  tamanio()     = " + bst.tamanio());
        System.out.println("  Coinciden?    " + (bst.contarNodos() == bst.tamanio()));
 
        // ============================================================
        // PROBLEMA 2 — ¿Está balanceado?
        // ============================================================
        System.out.println("\n========================================");
        System.out.println("  PROBLEMA 2: esBalanceado");
        System.out.println("========================================");
 
        System.out.println("Arbol {50,30,70,20,40,60,80,10} (balanceado):");
        System.out.print("  InOrden: ");
        bst.inOrden();
        System.out.println("  esBalanceado() = " + bst.esBalanceado()); // true
 
        // Arbol desbalanceado: insertar en orden ascendente
        ArbolBinarioBusqueda desbalanceado = new ArbolBinarioBusqueda();
        int[] secuencia = { 1, 2, 3, 4, 5 };
        for (int v : secuencia) {
            desbalanceado.insertar(v);
        }
        System.out.println("\nArbol {1,2,3,4,5} en orden (desbalanceado - lista hacia la derecha):");
        desbalanceado.imprimirArbol();
        System.out.println("  esBalanceado() = " + desbalanceado.esBalanceado()); // false
 
        // ============================================================
        // PROBLEMA 3 — Validar que sea un BST
        // ============================================================
        System.out.println("\n========================================");
        System.out.println("  PROBLEMA 3: esBSTValido");
        System.out.println("========================================");
 
        System.out.println("Arbol generado correctamente por insertar():");
        System.out.println("  esBSTValido() = " + bst.esBSTValido()); // true
 
        // Arbol "roto": construimos manualmente un arbol que viola la propiedad BST
        // Nodo raiz = 50, hijo izquierdo = 80 (viola: 80 > 50, no deberia ir a la izquierda)
        Nodo raizRota = new Nodo(50);
        raizRota.izquierdo = new Nodo(80); // VIOLACION: 80 > 50
        raizRota.derecho = new Nodo(70);
        ArbolBinarioBusqueda bstRoto = new ArbolBinarioBusqueda();
        // Inyectamos la raiz directamente para saltarnos el insertar()
        // (insertar() mantendria la propiedad automaticamente)
        // Usamos un arbol vacio y modificamos via getRaiz() no disponible para set,
        // asi que construimos el arbol roto insertando y luego rompemos un nodo:
        bstRoto.insertar(50);
        bstRoto.insertar(30);
        bstRoto.insertar(70);
        // Rompemos la propiedad: cambiamos el dato del hijo izquierdo a 99
        // (que es mayor que la raiz 50, violando BST)
        bstRoto.getRaiz().izquierdo.dato = 99;
 
        System.out.println("\nArbol roto (hijo izquierdo de 50 tiene dato 99, viola BST):");
        bstRoto.imprimirArbol();
        System.out.println("  esBSTValido() = " + bstRoto.esBSTValido()); // false
 
        // ============================================================
        // PROBLEMA 4 — Ancestro Comun Mas Bajo (LCA)
        // ============================================================
        System.out.println("\n========================================");
        System.out.println("  PROBLEMA 4: ancestroComunMasBajo (LCA)");
        System.out.println("========================================");
        System.out.println("Arbol usado: {50,30,70,20,40,60,80,10}");
        System.out.print("  InOrden: ");
        bst.inOrden();
 
        System.out.println("  LCA(10, 40) = " + bst.ancestroComunMasBajo(10, 40)); // 30
        System.out.println("  LCA(10, 80) = " + bst.ancestroComunMasBajo(10, 80)); // 50
        System.out.println("  LCA(60, 80) = " + bst.ancestroComunMasBajo(60, 80)); // 70
        System.out.println("  LCA(10, 10) = " + bst.ancestroComunMasBajo(10, 10)); // 10 (mismo nodo)
 
        // Caso con valor inexistente -> IllegalArgumentException
        System.out.print("  LCA(10, 99) = ");
        try {
            System.out.println(bst.ancestroComunMasBajo(10, 99));
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
        }
 
        // ============================================================
        // PROBLEMA 5 — Espejo del arbol (inversion)
        // ============================================================
        System.out.println("\n========================================");
        System.out.println("  PROBLEMA 5: invertir (espejo)");
        System.out.println("========================================");
 
        ArbolBinarioBusqueda bstEspejo = new ArbolBinarioBusqueda();
        for (int v : originales) {
            bstEspejo.insertar(v);
        }
 
        System.out.println("ANTES de invertir:");
        bstEspejo.imprimirArbol();
        System.out.print("  InOrden (ascendente): ");
        bstEspejo.inOrden();
 
        bstEspejo.invertir();
 
        System.out.println("\nDESPUES de invertir (espejo):");
        bstEspejo.imprimirArbol();
        System.out.print("  InOrden (ahora descendente): ");
        bstEspejo.inOrden();
 
        // ============================================================
        // EXTRA E1 — k-esimo menor
        // ============================================================
        System.out.println("\n========================================");
        System.out.println("  EXTRA E1: kEsimoMenor");
        System.out.println("========================================");
        System.out.println("Arbol: {50,30,70,20,40,60,80,10}");
        System.out.print("  InOrden: ");
        bst.inOrden();
        System.out.println("  kEsimoMenor(1) = " + bst.kEsimoMenor(1)); // 10
        System.out.println("  kEsimoMenor(3) = " + bst.kEsimoMenor(3)); // 30
        System.out.println("  kEsimoMenor(8) = " + bst.kEsimoMenor(8)); // 80
 
        System.out.print("  kEsimoMenor(9) = ");
        try {
            System.out.println(bst.kEsimoMenor(9));
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
        }
 
        // ============================================================
        // EXTRA E2 — Imprimir rango ordenado
        // ============================================================
        System.out.println("\n========================================");
        System.out.println("  EXTRA E2: imprimirRangoOrdenado");
        System.out.println("========================================");
        System.out.println("Arbol: {50,30,70,20,40,60,80,10}");
        System.out.print("  Rango [20, 60]: ");
        bst.imprimirRangoOrdenado(20, 60); // 20 30 40 50 60
        System.out.print("  Rango [1,  100]: ");
        bst.imprimirRangoOrdenado(1, 100);  // todos
        System.out.print("  Rango [35, 65]: ");
        bst.imprimirRangoOrdenado(35, 65);  // 40 50 60
 
        // ============================================================
        // EXTRA E3 — Diametro
        // ============================================================
        System.out.println("\n========================================");
        System.out.println("  EXTRA E3: diametro");
        System.out.println("========================================");
        System.out.println("Arbol {50,30,70,20,40,60,80,10}:");
        System.out.println("  diametro() = " + bst.diametro()); // 6 (10->20->30->50->70->80 o similar)
 
        ArbolBinarioBusqueda bstLineal = new ArbolBinarioBusqueda();
        for (int v : secuencia) {
            bstLineal.insertar(v);
        }
        System.out.println("Arbol lineal {1,2,3,4,5}:");
        System.out.println("  diametro() = " + bstLineal.diametro()); // 4
 
        // ============================================================
        // EXTRA E4 — Construir BST desde argumentos de consola
        // ============================================================
        System.out.println("\n========================================");
        System.out.println("  EXTRA E4: BST desde args");
        System.out.println("========================================");
        if (args.length > 0) {
            ArbolBinarioBusqueda bstArgs = new ArbolBinarioBusqueda();
            System.out.print("  Insertando desde args: ");
            for (String arg : args) {
                try {
                    int num = Integer.parseInt(arg);
                    bstArgs.insertar(num);
                    System.out.print(num + " ");
                } catch (NumberFormatException e) {
                    System.out.print("['" + arg + "' ignorado, no es entero] ");
                }
            }
            System.out.println();
            System.out.println("  Arbol construido:");
            bstArgs.imprimirArbol();
            System.out.print("  InOrden: ");
            bstArgs.inOrden();
            System.out.println("  Tamanio: " + bstArgs.tamanio());
            System.out.println("  Altura:  " + bstArgs.altura());
        } else {
            System.out.println("  (No se pasaron argumentos. Ejecuta con: java -cp target/classes");
            System.out.println("   umg.edu.progra.arboles.Principal 15 25 5 35 10)");
        }
    }
}
 

       