import java.time.LocalDateTime;

public class main {

  public static void main(String[] args) throws InterruptedException {

        SingletonClock clock = new SingletonClock(); //creamos un nuevo reloj

    clock.start(); //iniciamos el reloj
    Thread.sleep(1000); //lo paramos 1 segundo

    Project p1 = new Project("raiz"); //creamos nuevo proyecto 


    p1.CreateNewTask("Tasca1nivel1",clock); //creamos una nueva tasca partir del proyecto

    Thread.sleep(1000); //lo paramos 1 segundo
    Task taux = p1.getChildTaskName("Tasca1nivel1"); //Crea tarea
    taux.start(clock); //empezamos el reloj de la tasca
    p1.CreateNewSubProject("Proyecto1nivel1"); //creamos un subproyecto
    p1.CreateNewSubProject("Proyecto2nivel1"); //creamos un subproyecto
    Project aux= p1.getChildProjectName("Proyecto1nivel1"); //Creamos proyecto
    taux.displayTask(); //muestra nombre, fecha en que se creó la tarea, tiempo total de la tarea y fechas de inicio y finalizacion de los intervalos
    Thread.sleep(3000); //lo paramos 3 segundos
    taux.stop(clock); //pausamos la tarea
    taux.displayTask(); //muestra nombre, fecha en que se creó la tarea, tiempo total de la tarea y fechas de inicio y finalizacion de los intervalos
    aux.CreateNewTask("Tasca1nivel2",clock); //creamos una nueva tasca
    aux.CreateNewTask("Tasca2nivel2",clock); //creamos una nueva tasca
    p1.displayProject();
  }

}
