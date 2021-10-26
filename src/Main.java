port java.time.LocalDateTime;

public class main {
  /*private void simpleTest() throws InterruptedException {
    Task t0 = new Task("study");
    t0.start();
    wait(12);
    t0.stop();
  }
  private void notSoSimpleTest() throws InterruptedException {
    Project p1 = new Project("Study");
    p1.CreateNewSubProject("Examen1");
    Task = t1 = p2.CreateNewTask("Tema1");
    t1.start();
    wait(1000);
    t1.stop();
    p1.TotalTime();
  }
  public static void main(String[] args) {
    simpleTest();
    notSoSimpleTest();
  }*/
  public static void main(String[] args) throws InterruptedException {

    SingletonClock clock = new SingletonClock(); //creamos un nuevo reloj

    clock.start(); //iniciamos el reloj
    Thread.sleep(1000); //lo paramos 1 segundo

    Project p1 = new Project("raiz"); //creamos nuevo proyecto 

    System.out.println("breakpoint 1"); //imprime un mensaje

    p1.CreateNewTask("Tasca1nivel1",clock); //creamos una nueva tasca partir del proyecto

    Thread.sleep(1000); //lo paramos 1 segundo
    System.out.println("breakpoint 2"); //imprime un mensaje
    Task taux = p1.getChildTaskName("Tasca1nivel1"); //recuperamos el nombre de la tasca
    taux.start(clock); //empezamos el reloj de la tasca
    p1.CreateNewSubProject("Proyecto1nivel1"); //creamos un subproyecto
    p1.CreateNewSubProject("Proyecto2nivel1"); //creamos un subproyecto
    Project aux= p1.getChildProjectName("Proyecto1nivel1"); //recuperamos el nombre del proyecto
    taux.displayTask(); //muestra nombre, fecha en que se creó la tarea, tiempo total de la tarea y fechas de inicio y finalizacion de los intervalos
    Thread.sleep(3000); //lo paramos 3 segundos
    System.out.println("breakpoint 3"); //imprime un mensaje
    taux.stop(clock); //paramos el reloj
    taux.displayTask(); //muestra nombre, fecha en que se creó la tarea, tiempo total de la tarea y fechas de inicio y finalizacion de los intervalos
    aux.CreateNewTask("Tasca1nivel2",clock); //creamos una nueva tasca
    aux.CreateNewTask("Tasca2nivel2",clock); //creamos una nueva tasca

  }

}
