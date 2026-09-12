package pe.edu.practica03;

import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/** Gestiona tareas en memoria y exporta su estado a JSON. */
public class GestorTareas {
    private final List<Tarea> tareas = new ArrayList<>();
    private int siguienteId = 1;

    public Tarea agregar(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El titulo no puede estar vacio");
        }
        Tarea tarea = new Tarea(siguienteId++, titulo.strip(), false);
        tareas.add(tarea);
        return tarea;
    }

    public void completar(int id) {
        for (int i = 0; i < tareas.size(); i++) {
            Tarea tarea = tareas.get(i);
            if (tarea.id() == id) {
                tareas.set(i, new Tarea(id, tarea.titulo(), true));
                return;
            }
        }
        throw new NoSuchElementException("No existe la tarea " + id);
    }

    public List<Tarea> listar() {
        return List.copyOf(tareas);
    }

    public String exportarJson() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(tareas);
    }
}
