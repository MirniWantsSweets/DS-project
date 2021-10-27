import java.time.LocalDateTime;

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

    //SingletonClock clock = new SingletonClock(); //creamos un nuevo reloj
    SingletonClock.start(); //iniciamos el reloj

    Project p1 = new Project("raiz"); //creamos nuevo proyecto
    p1.CreateNewTask("Tasca1nivel1",SingletonClock.getInstance()); //creamos una nueva tasca a partir del proyecto
    p1.CreateNewSubProject("Proyecto1nivel1"); //creamos un subproyecto
    p1.CreateNewSubProject("Proyecto2nivel1"); //creamos un subproyecto
    Project paux= p1.getChildProjectName("Proyecto1nivel1"); //Creamos proyecto
    paux.CreateNewTask("Tasca1nivel2",SingletonClock.getInstance()); //creamos una nueva tasca para Proyecto1nivel1
    paux.CreateNewTask("Tasca2nivel2",SingletonClock.getInstance()); //creamos una nueva tasca para Proyecto1nivel1
    Task taux = p1.getChildTaskName("Tasca1nivel1"); //Almacenamos en taux el apuntador a Tasca1Nivel1
    Task taux2 = paux.getChildTaskName("Tasca1nivel2"); //Almacenamos en taux el apuntador a Tasca1Nivel2
    Task taux3 = paux.getChildTaskName("Tasca2nivel2"); //Almacenamos en taux el apuntador a Tasca2Nivel2

    taux.start(SingletonClock.getInstance()); //Indicamos que la tasca esta activa
    taux.displayTask(); //muestra nombre, fecha en que se creó la tarea, tiempo total de la tarea y fechas de inicio y finalizacion de los intervalos

    Thread.sleep(3000); //lo paramos 3 segundos
    taux.stop(SingletonClock.getInstance()); //Indicamos que la tasca ya no esta activa
    Thread.sleep(1000); //lo paramos 1 segundo
    taux.displayTask(); //muestra nombre, fecha en que se creó la tarea, tiempo total de la tarea y fechas de inicio y finalizacion de los intervalos

    taux.start(SingletonClock.getInstance()); //Indicamos que la tasca esta activa
    taux2.start(SingletonClock.getInstance()); //Indicamos que la tasca esta activa
    taux3.start(SingletonClock.getInstance()); //Indicamos que la tasca esta activa
    Thread.sleep(2000); //lo paramos 2 segundos

    taux.stop(SingletonClock.getInstance()); //Indicamos que la tasca ya no esta activa
    taux2.stop(SingletonClock.getInstance()); //Indicamos que la tasca ya no esta activa
    taux3.stop(SingletonClock.getInstance()); //Indicamos que la tasca ya no esta activa
    taux.displayTask(); //muestra nombre, fecha en que se creó la tarea, tiempo total de la tarea y fechas de inicio y finalizacion de los intervalos
    Thread.sleep(2000); //lo paramos 2 segundos
    taux2.displayTask(); //muestra nombre, fecha en que se creó la tarea, tiempo total de la tarea y fechas de inicio y finalizacion de los intervalos
    Thread.sleep(2000); //lo paramos 2 segundos
    p1.displayProject();
    paux.displayProject();
  }

}