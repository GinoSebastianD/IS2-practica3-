package pe.edu.practica03;

import com.google.gson.JsonParser;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GestorTareasTest {
    @Test void iniciaSinTareas() {
        assertTrue(new GestorTareas().listar().isEmpty());
    }
    @Test void agregaTareaConIdYNormalizaTitulo() {
        GestorTareas gestor = new GestorTareas();
        assertEquals(new Tarea(1, "Estudiar Maven", false), gestor.agregar("  Estudiar Maven  "));
        assertEquals(2, gestor.agregar("Preparar informe").id());
    }
    @Test void rechazaTituloVacio() {
        assertThrows(IllegalArgumentException.class, () -> new GestorTareas().agregar("   "));
    }
    @Test void rechazaTituloNulo() {
        assertThrows(IllegalArgumentException.class, () -> new GestorTareas().agregar(null));
    }
    @Test void completaUnaTareaExistente() {
        GestorTareas gestor = new GestorTareas();
        gestor.agregar("Compilar");
        gestor.completar(1);
        assertTrue(gestor.listar().get(0).completada());
    }
    @Test void rechazaIdInexistente() {
        assertThrows(NoSuchElementException.class, () -> new GestorTareas().completar(99));
    }
    @Test void protegeLaListaInterna() {
        GestorTareas gestor = new GestorTareas();
        gestor.agregar("Probar");
        assertThrows(UnsupportedOperationException.class, () -> gestor.listar().clear());
        assertEquals(1, gestor.listar().size());
    }
    @Test void exportaJsonConTituloYEstado() {
        GestorTareas gestor = new GestorTareas();
        gestor.agregar("Revisar \"informe\" y documentación");
        gestor.completar(1);
        var json = JsonParser.parseString(gestor.exportarJson()).getAsJsonArray();
        assertEquals(1, json.size());
        assertEquals("Revisar \"informe\" y documentación", json.get(0).getAsJsonObject().get("titulo").getAsString());
        assertTrue(json.get(0).getAsJsonObject().get("completada").getAsBoolean());
    }
}
