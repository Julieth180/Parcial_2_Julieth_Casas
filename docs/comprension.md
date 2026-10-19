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

> _Tu respuesta: Sensor es abstracta porque junta lo básico que tienen todos los sensores, pero no sirve para instanciar uno por sí solo. Obliga a las clases hijas a armar el método generarLectura() (y fueraDeRango()), lo que ayuda a tratar a cualquier sensor por igual con polimorfismo sin enredarse

---

### 2. Composición vs. agregación
Explica con tus palabras la diferencia entre `NodoIoT ◆— Sensor` (composición)
y `CentroMonitoreo ◇— NodoIoT` (agregación). Da un ejemplo de qué pasaría con
los sensores si se elimina el nodo, y qué pasaría con los nodos si se elimina
el centro.

> _Tu respuesta: La composición (◆) es cuando una cosa depende 100% de la otra; si se borra el nodo, los sensores se mueren con él. La agregación (◇) es más flexible, porque si se borra el centro de monitoreo, los nodos siguen ahí campantes y corrientes sin depender de eso para existir

---

### 3. Asociación
En `TecnicoMantenimiento — NodoIoT`, ¿quién es dueño de quién? ¿Por qué se
modela como N:M y no como composición?

> _Tu respuesta: Ninguno es dueño de nadie, solo hacen equipo. Se pone como N:M porque un técnico puede echarle ojo a varios nodos y un nodo también puede ser atendido por varios técnicos, sin que sus vidas estén atadas

---

### 4. Dependencia
¿Por qué `Lectura` **depende** de `ValidadorLectura` en lugar de **tenerlo**
como atributo? ¿Qué diferencia práctica hay entre pasar un `ValidadorLectura`
como parámetro versus guardarlo en un campo?

> _Tu respuesta: Lectura usa a ValidadorLectura solo cuando le toca validar un dato puntual, por eso se pasa como parámetro y ya, sin volverse un atributo fijo. Eso evita enredar la clase con cosas que no necesita guardar todo el tiempo

---

### 5. Listas
En este proyecto se usan dos estructuras de datos distintas:
- `int[] ultimasLecturas` en `Sensor`
- `ArrayList<Sensor>` en `NodoIoT`

Explica por qué en cada caso se eligió esa estructura y no la otra. ¿Qué
ventaja tiene el `int[]` con buffer circular frente a un `ArrayList` para el
historial de lecturas?

> _Tu respuesta:El int[] se usa para las lecturas porque como el tamaño es fijo, rinde mucho mejor y se evita gastar memoria con objetos extra, además de que con el buffer circular todo corre al pelo. El ArrayList<Sensor> se usa en el nodo porque ahí la cantidad de sensores sí puede cambiar y agregarse dinámicamente