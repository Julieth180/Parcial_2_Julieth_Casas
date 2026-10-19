package red;

import sensores.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Nodo IoT que COMPONE sensores.
 * Los sensores se crean dentro del nodo (composición real).
 */
public class NodoIoT {

    private String id;
    private ArrayList<Sensor> sensores;

    public NodoIoT(String id) {
        this.id = id;
        this.sensores = new ArrayList<>();
    }

    /**
     * Crea y agrega un sensor del tipo indicado dentro del nodo.
     * Tipo: "TEMPERATURA", "HUMEDAD", "LUMINOSIDAD".
     * Retorna el sensor creado, o null si el tipo es inválido.
     */
    public Sensor agregarSensor(String tipo, String idSensor, int capacidad) {
        // TODO: implementar (polimorfismo + composición)
        if (tipo == null) {
            return null;
        }

        Sensor nuevoSensor = null;
        switch (tipo.toUpperCase()) {
            case "TEMPERATURA":
                nuevoSensor = new SensorTemperatura(idSensor, capacidad);
                break;
            case "HUMEDAD":
                nuevoSensor = new SensorHumedad(idSensor, capacidad);
                break;
            case "LUMINOSIDAD":
                nuevoSensor = new SensorLuminosidad(idSensor, capacidad);
                break;
            default:
                return null;
        }

        sensores.add(nuevoSensor);
        return nuevoSensor;
    }

    /**
     * Promedio de temperatura del nodo. Retorna 0 si no hay sensores de temperatura.
     * Pista: usar instanceof para filtrar.
     */
    public double promedioTemperatura() {
        // TODO: implementar
        double suma = 0;
        int contador = 0;

        for (Sensor s : sensores) {
            if (s instanceof SensorTemperatura) {
                suma += s.promedioUltimasLecturas();
                contador++;
            }
        }

        if (contador == 0) {
            return 0;
        }
        return suma / contador;
    }

    /**
     * Lista de sensores cuya última lectura está fuera de rango.
     */
    public List<Sensor> sensoresFueraDeRango() {
        // TODO: implementar
        List<Sensor> resultado = new ArrayList<>();

        for (Sensor s : sensores) {
            if (s.getCantidadLecturas() > 0) {
                int[] lecturas = s.getUltimasLecturas();
                // Si el arreglo está lleno, la última posición con datos depende del buffer circular,
                // pero si el buffer no se ha llenado o si tomamos la posición anterior al índice actual:
                int posUltima = (s.getIndiceActual() == 0) ? lecturas.length - 1 : s.getIndiceActual() - 1;
                int ultimaLectura = lecturas[posUltima];

                if (s.fueraDeRango(ultimaLectura)) {
                    resultado.add(s);
                }
            }
        }

        return resultado;
    }
    public ArrayList<Sensor> getSensores() {
        return sensores;
    }
    public String getId() {
        return id;
    }
}
