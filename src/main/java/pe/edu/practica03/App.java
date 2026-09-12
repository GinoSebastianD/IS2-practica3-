package pe.edu.practica03;

public class App {
    public static void main(String[] args) {
        GestorTareas gestor = new GestorTareas();
        gestor.agregar("Configurar Maven");
        gestor.agregar("Construir y probar el proyecto");
        gestor.agregar("Documentar los resultados");
        gestor.completar(1);
        gestor.completar(2);
        System.out.println("GESTOR DE TAREAS | Practica 03 | v1.0.0");
        System.out.println(gestor.exportarJson());
        System.out.println("Total de tareas: " + gestor.listar().size());
    }
}
