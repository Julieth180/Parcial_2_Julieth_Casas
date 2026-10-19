package sensores;

import java.util.Random;

/**
 * Sensor de temperatura. Rango válido: [-10, 50] °C.
 */
public class SensorTemperatura extends Sensor {

    public static final int MIN = -10;
    public static final int MAX = 50;
    private static final Random RND = new Random();

    public SensorTemperatura(String id, int capacidad) {
        super(id, "C", capacidad);
    }

    @Override
    // TODO: generar valor aleatorio en [MIN-5, MAX+5],
    // llamar a registrarLectura(valor) y retornarlo.
    public int generarLectura() {
        int valor = (MIN - 5) + RND.nextInt((MAX + 5) - (MIN - 5) + 1);
        registrarLectura(valor);
        return valor;
    }

    @Override
    public boolean fueraDeRango(int valor) {
        // TODO: implementar
        return valor < MIN || valor > MAX;
    }

}