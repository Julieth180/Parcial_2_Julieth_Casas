# Comprensión del diseño — Enunciado A
**Nombre:** Julieth Caterine Casas 
**Fecha:** 19/10/26

Responde con tus palabras, basándote en `docs/UML-final.md`. Respuestas
concisas (2–4 líneas por pregunta). Este documento evidencia que entendiste el
contrato de diseño **antes** de programar.

---

### 1. Herencia y polimorfismo
¿Por qué `Sensor` es una clase abstracta y no una clase concreta? Menciona
qué método obliga a cada subclase a implementar y qué gana el sistema con eso.

> _Tu respuesta: Sensor es abstracta porque define el contrato y atributos comunes, impidiendo instanciar sensores genéricos sin naturaleza definida. Obliga a las subclases a implementar generarLectura() y fueraDeRango(), permitiendo al sistema tratar colecciones de sensores diversos de forma unificada y dinámica mediante polimorfismo.
---

### 2. Composición vs. agregación
Explica con tus palabras la diferencia entre `NodoIoT ◆— Sensor` (composición)
y `CentroMonitoreo ◇— NodoIoT` (agregación). Da un ejemplo de qué pasaría con
los sensores si se elimina el nodo, y qué pasaría con los nodos si se elimina
el centro.

> _Tu respuesta: La composición (◆) vincula el ciclo de vida: si se elimina el NodoIoT, sus Sensor internos mueren con él. La agregación (◇) es independiente: si se elimina el CentroMonitoreo, los nodos subsisten intactos ya que se crean por fuera y solo se asocian.

---

### 3. Asociación
En `TecnicoMantenimiento — NodoIoT`, ¿quién es dueño de quién? ¿Por qué se
modela como N:M y no como composición?

> _Tu respuesta: No existe relación de propiedad; se modela como N:M porque un TecnicoMantenimiento puede supervisar varios nodos y un nodo puede ser atendido por múltiples técnicos, manteniendo sus ciclos de vida totalmente autónomos.

---

### 4. Dependencia
¿Por qué `Lectura` **depende** de `ValidadorLectura` en lugar de **tenerlo**
como atributo? ¿Qué diferencia práctica hay entre pasar un `ValidadorLectura`
como parámetro versus guardarlo en un campo?

> _Tu respuesta: Lectura depende de ValidadorLectura porque este último funciona como un servicio puntual sin estado. Pasarlo por parámetro en lugar de guardarlo como atributo evita acoplar innecesariamente las clases y reduce el uso de memoria.
---

### 5. Listas
En este proyecto se usan dos estructuras de datos distintas:
- `int[] ultimasLecturas` en `Sensor`
- `ArrayList<Sensor>` en `NodoIoT`

Explica por qué en cada caso se eligió esa estructura y no la otra. ¿Qué
ventaja tiene el `int[]` con buffer circular frente a un `ArrayList` para el
historial de lecturas?

> _Tu respuesta:El arreglo int[] se eligió para las lecturas porque maneja un historial de tamaño fijo, optimizando el rendimiento en memoria al almacenar tipos primitivos sin el coste de objetos (boxing). Su ventaja radica en el búfer circular controlado por el operador módulo (%), que permite sobrescribir los datos más antiguos en tiempo constante $O(1)$ sin desplazar elementos ni redimensionar memoria. Por el contrario, el ArrayList<Sensor> se implementó en el nodo porque la cantidad de sensores agregados es dinámica y varía en tiempo de ejecución, requiriendo una estructura capaz de crecer de forma flexible.