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

    SingletonClock clock = new SingletonClock();

    clock.start();
    Thread.sleep(1000);

    Project p1 = new Project("raiz");

    System.out.println("breakpoint 1");

    p1.CreateNewTask("Tasca1nivel1",clock);

    Thread.sleep(1000);
    System.out.println("breakpoint 2");
    Task taux = p1.getChildTaskName("Tasca1nivel1");
    taux.start(clock);
    p1.CreateNewSubProject("Proyecto1nivel1");
    p1.CreateNewSubProject("Proyecto2nivel1");
    Project aux= p1.getChildProjectName("Proyecto1nivel1");

    Thread.sleep(3000);
    System.out.println("breakpoint 3");
    taux.stop(clock);

    aux.CreateNewTask("Tasca1nivel2",clock);
    aux.CreateNewTask("Tasca2nivel2",clock);

  }

}