# Tarea: Árbol Binario de Búsqueda (BST) en Java

**Curso:** Programación 3  
**Tema:** Estructuras de datos no lineales — Árboles  
**Modalidad:** Individual  
**Entrega:** Repositorio Git con el código modificado + capturas de ejecución

---

## 1. Objetivo

Comprender, completar y extender una implementación **manual** de un Árbol Binario de Búsqueda (BST) en Java, **sin usar `java.util` ni librerías externas**, siguiendo el mismo estilo con el que ya implementamos `queue` y `listas` enlazadas en clase.

Al finalizar la tarea el estudiante debe ser capaz de:

- Explicar la propiedad fundamental de un BST.
- Implementar inserción, búsqueda, eliminación y recorridos de forma recursiva.
- Reconocer los 3 casos de eliminación de un nodo en un BST.
- Implementar algoritmos clásicos sobre árboles (altura, balanceo, validación, LCA, espejo, etc.).

---

## 2. Proyecto base

El proyecto Maven `arboles/` ya contiene una base funcional:

```
arboles/
├── pom.xml
└── src/main/java/umg/edu/progra/arboles/
    ├── Nodo.java
    ├── ArbolBinarioBusqueda.java
    └── Principal.java
```

### Lo que YA está implementado

- `Nodo` con `dato`, `izquierdo` y `derecho`.
- `ArbolBinarioBusqueda` con:
  - `insertar(int)`
  - `buscar(int)` / `contiene(int)`
  - `eliminar(int)` cubriendo los 3 casos clásicos.
  - `minimo()`, `maximo()`, `altura()`, `tamanio()`, `contarHojas()`.
  - Recorridos: `inOrden()`, `preOrden()`, `postOrden()`, `recorridoPorNiveles()` (BFS con una cola casera, **sin `java.util`**).
  - `imprimirArbol()` (impresión visual rotada 90°).
- `Principal` con un ejemplo completo de uso.

---

## 3. Cómo ejecutar el proyecto

Desde la carpeta `arboles/`:

```bash
mvn compile
java -cp target/classes umg.edu.progra.arboles.Principal
```

O abriéndolo como proyecto Maven en Eclipse y ejecutando la clase `Principal`.

Salida esperada (resumen):

```
===== Arbol Binario de Busqueda =====
Tamanio: 8
Altura:  3
Minimo:  10
Maximo:  80
Hojas:   4
...
InOrden    (ascendente): 10 20 30 40 50 60 70 80
PreOrden   (raiz primero): 50 30 20 10 40 70 60 80
PostOrden  (raiz al final): 10 20 40 30 60 80 70 50
Por niveles (BFS):         50 30 70 20 40 60 80 10
```

---

## 4. Reglas obligatorias

> El incumplimiento de cualquiera de estas reglas invalida la tarea.

1. **Prohibido usar `java.util.*`** (ni `ArrayList`, ni `LinkedList`, ni `Queue`, ni `Stack`, ni `HashMap`, ni `Arrays`, etc.).
2. **Prohibido usar cualquier librería externa** para la estructura del árbol.
3. Si necesitan una estructura auxiliar (cola, pila, lista), deben implementarla manualmente como ya se hizo con `ColaNodos` dentro de `ArbolBinarioBusqueda`.
4. Toda la lógica nueva debe estar en la clase `ArbolBinarioBusqueda` (o en clases auxiliares dentro del mismo paquete `umg.edu.progra.arboles`).
5. Cada método nuevo debe probarse desde la clase `Principal`.
6. El código debe compilar con `mvn compile` sin errores ni warnings.

---

## 5. Problemas a resolver

Implementar los siguientes métodos en la clase `ArbolBinarioBusqueda` y demostrar su funcionamiento desde `Principal`.

### Problema 1 — Contar nodos recursivamente

Implementar:

```java
public int contarNodos();
```

- Debe devolver la cantidad total de nodos del árbol **usando recursividad**.
- **NO** puede usar el campo `tamanio` ya existente.
- Validar que su resultado coincida con `tamanio()` antes y después de insertar/eliminar.

### Problema 2 — ¿Está balanceado?

Implementar:

```java
public boolean esBalanceado();
```

- Un árbol está balanceado si, para **cada nodo**, la diferencia de altura entre su subárbol izquierdo y derecho es `<= 1`.
- Probarlo con un árbol balanceado y con uno claramente desbalanceado (por ejemplo, insertando 1, 2, 3, 4, 5 en ese orden).

### Problema 3 — Validar que sea un BST

Implementar:

```java
public boolean esBSTValido();
```

- Debe verificar que el árbol cumple la propiedad de BST (todo el subárbol izquierdo `<` raíz, todo el subárbol derecho `>` raíz).
- **Pista:** una solución limpia es pasar un rango `(min, max)` permitido en cada llamada recursiva.
- Probarlo en el árbol generado por `Principal`. Debe retornar `true`.
- Para demostrar el caso `false`, construir manualmente un árbol "roto" (modificando nodos directamente) y validar que devuelve `false`.

### Problema 4 — Ancestro común más bajo (LCA)

Implementar:

```java
public int ancestroComunMasBajo(int a, int b);
```

- Debe devolver el dato del nodo que es el **ancestro común más bajo** (Lowest Common Ancestor) de los valores `a` y `b`.
- Aprovechar la propiedad del BST: si ambos valores son menores que el actual → ir a la izquierda; si ambos son mayores → ir a la derecha; en caso contrario, el nodo actual es el LCA.
- Si `a` o `b` no existen en el árbol, lanzar `IllegalArgumentException`.
- Ejemplo con el árbol de `Principal`:
  - `lca(10, 40)` → `30`
  - `lca(10, 80)` → `50`
  - `lca(60, 80)` → `70`

### Problema 5 — Espejo del árbol (inversión)

Implementar:

```java
public void invertir();
```

- Debe intercambiar `izquierdo` y `derecho` en **todos** los nodos del árbol (reflejo / espejo).
- Antes de invertir, mostrar el árbol con `imprimirArbol()` e `inOrden()`.
- Después de invertir, volver a mostrarlos. El `inOrden` original ya **no** estará ordenado (se invierte).

---

## 6. Ejercicios extra (opcionales, suman puntos)

Solo cuentan si los 5 problemas anteriores están correctos.

- **E1.** `int kEsimoMenor(int k)` — devuelve el k-ésimo valor más pequeño usando inOrden.
- **E2.** `void imprimirRangoOrdenado(int min, int max)` — imprime en orden todos los valores en el rango `[min, max]` recorriendo lo menos posible el árbol.
- **E3.** `int diametro()` — el camino más largo (en aristas) entre dos nodos cualesquiera del árbol.
- **E4.** Construir un BST a partir de un arreglo `int[]` recibido por la consola (`args`).

---

## 7. Entregables

1. **Repositorio Git** (GitHub / GitLab) con el proyecto modificado.
2. **Capturas** de la salida en consola que demuestren cada problema resuelto.
3. **README.md propio** del estudiante (puede partir de este) explicando:
   - Cómo compilar y ejecutar.
   - Qué hace cada método nuevo.
   - Un ejemplo de entrada y salida por cada problema.
4. Commits descriptivos por cada problema (por ejemplo: `feat: problema 1 contarNodos recursivo`).

---

## 8. Rúbrica de evaluación (100 pts)

| Criterio                                            | Puntos |
| --------------------------------------------------- | -----: |
| Problema 1 — `contarNodos` recursivo                | 10     |
| Problema 2 — `esBalanceado`                         | 15     |
| Problema 3 — `esBSTValido`                          | 15     |
| Problema 4 — `ancestroComunMasBajo` (LCA)           | 20     |
| Problema 5 — `invertir` (espejo)                    | 15     |
| Pruebas claras en `Principal` (cada método se ve)   | 10     |
| Código limpio, recursivo cuando aplica, sin `util`  | 10     |
| Commits, README propio y capturas                   | 5      |
| **Ejercicios extra (E1–E4)**                        | +10    |

---

## 9. Recomendaciones

- **Piensen recursivamente.** Casi todo en árboles se resuelve definiendo el caso base (nodo `null`) y combinando el resultado de los subárboles izquierdo y derecho.
- **Dibujen el árbol antes de codificar.** Especialmente para `eliminar`, `LCA` e `invertir`.
- **Prueben con árboles vacíos** (`raiz == null`) y con árboles de un solo nodo. La mitad de los errores aparecen en esos bordes.
- **No copien código de internet sin entenderlo.** En la defensa oral se pregunta sobre cualquier línea.

---

> "Un BST bien implementado es más rápido que muchas estructuras prediseñadas… si entienden el porqué."
