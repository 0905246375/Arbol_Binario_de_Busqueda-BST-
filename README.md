# Tarea: Árbol Binario de Búsqueda (BST) en Java
**Curso:** Programación 3  
**Estudiante:** Tahly Jiménez 
**Universidad:** UMG  

---

## ¿Cómo compilar y ejecutar?

### Requisitos
- Java JDK 8 o superior
- Apache Maven

### Compilar
Desde la carpeta `arboles/`:
```bash
mvn compile
```

### Ejecutar
```bash
java -cp target/classes umg.edu.progra.arboles.Principal
```

### Ejecutar con argumentos (Extra E4)
```bash
java -cp target/classes umg.edu.progra.arboles.Principal 15 25 5 35 10
```

---

## Métodos nuevos implementados

### Problema 1 — `contarNodos()`
Cuenta recursivamente todos los nodos del árbol **sin usar** el campo `tamanio` ya existente.  
Caso base: si el nodo es `null` retorna `0`.  
Caso recursivo: `1 + contarNodos(izquierdo) + contarNodos(derecho)`.

**Ejemplo:**
```
Árbol: {50, 30, 70, 20, 40, 60, 80, 10}
contarNodos() = 8
tamanio()     = 8
Coinciden?    true
```

---

### Problema 2 — `esBalanceado()`
Verifica si el árbol está balanceado en altura: para **cada nodo**, la diferencia de altura entre su subárbol izquierdo y derecho debe ser `<= 1`.  
Usa un helper privado `alturaBalanceada()` que devuelve `-2` como centinela si detecta desbalance, evitando recalcular alturas dos veces.

**Ejemplo:**
```
Árbol {50,30,70,20,40,60,80,10}:
  esBalanceado() = true

Árbol {1,2,3,4,5} (lista hacia la derecha):
  esBalanceado() = false
```

---

### Problema 3 — `esBSTValido()`
Verifica que el árbol cumple la propiedad de BST en todos sus niveles (no solo entre padre e hijo directo).  
Pasa un rango `(min, max)` permitido en cada llamada recursiva: al ir a la izquierda el `max` se vuelve el dato del nodo actual; al ir a la derecha el `min` se vuelve el dato del nodo actual.

**Ejemplo:**
```
Árbol generado correctamente con insertar():
  esBSTValido() = true

Árbol roto (hijo izquierdo de 50 tiene dato 99):
  esBSTValido() = false
```

---

### Problema 4 — `ancestroComunMasBajo(int a, int b)`
Devuelve el dato del nodo que es el **Ancestro Común Más Bajo** (LCA) de los valores `a` y `b`.  
Aprovecha la propiedad del BST:
- Si ambos son menores que el nodo actual → ir a la izquierda
- Si ambos son mayores → ir a la derecha
- En cualquier otro caso → el nodo actual **es** el LCA

Lanza `IllegalArgumentException` si alguno de los valores no existe en el árbol.

**Ejemplo:**
```
Árbol: {50, 30, 70, 20, 40, 60, 80, 10}
  LCA(10, 40) = 30
  LCA(10, 80) = 50
  LCA(60, 80) = 70
  LCA(10, 99) = IllegalArgumentException: El valor 99 no existe en el arbol.
```

---

### Problema 5 — `invertir()`
Genera el **espejo/reflejo** del árbol: intercambia los subárboles izquierdo y derecho en todos los nodos recursivamente.  
Después de invertir, el recorrido inOrden queda en orden **descendente** (al revés del original).

**Ejemplo:**
```
ANTES de invertir:
  InOrden: 10 20 30 40 50 60 70 80

DESPUÉS de invertir:
  InOrden: 80 70 60 50 40 30 20 10
```

---

## Ejercicios Extra

### E1 — `kEsimoMenor(int k)`
Devuelve el k-ésimo valor más pequeño del árbol usando recorrido inOrden.  
`k=1` es el mínimo. Lanza `IllegalArgumentException` si `k` está fuera del rango.

**Ejemplo:**
```
Árbol: {50, 30, 70, 20, 40, 60, 80, 10}
  kEsimoMenor(1) = 10
  kEsimoMenor(3) = 30
  kEsimoMenor(8) = 80
  kEsimoMenor(9) = IllegalArgumentException
```

---

### E2 — `imprimirRangoOrdenado(int min, int max)`
Imprime en orden todos los valores en el rango `[min, max]`.  
Optimizado: no baja por subárboles que no pueden contener valores dentro del rango.

**Ejemplo:**
```
Árbol: {50, 30, 70, 20, 40, 60, 80, 10}
  Rango [20, 60]: 20 30 40 50 60
  Rango [35, 65]: 40 50 60
```

---

### E3 — `diametro()`
Devuelve el camino más largo en aristas entre dos nodos cualesquiera del árbol.  
El camino no necesariamente pasa por la raíz.  
Para cada nodo calcula: `altura(izq) + altura(der) + 2` y guarda el máximo global.

**Ejemplo:**
```
Árbol {50,30,70,20,40,60,80,10}:
  diametro() = 6

Árbol lineal {1,2,3,4,5}:
  diametro() = 4
```

---

### E4 — BST desde argumentos de consola
Si se pasan argumentos al ejecutar el programa, se construye un BST con esos valores.  
Los valores que no sean enteros se ignoran con un aviso.

**Ejemplo:**
```bash
java -cp target/classes umg.edu.progra.arboles.Principal 15 25 5 35 10
```
```
Insertando desde args: 15 25 5 35 10
InOrden: 5 10 15 25 35
Tamanio: 5
Altura:  2
```

---

## Reglas seguidas
- **Prohibido** `java.util.*` — no se usa ninguna clase de la librería estándar de Java.
- Las estructuras auxiliares (cola para BFS) se implementaron manualmente con la clase interna `ColaNodos`.
- Toda la lógica está en `ArbolBinarioBusqueda.java`.
- Cada método se prueba desde `Principal.java` con salida visible en consola.
