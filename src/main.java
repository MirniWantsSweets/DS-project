
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
  public static void main(String[] args) {
    Project p1 = new Project("raiz");
    p1.CreateNewTask("Tasca1nivel1");
    p1.CreateNewSubProject("Proyecto1nivel1");
    p1.CreateNewSubProject("Proyecto2nivel1");
    Node aux= p1.getChildName("Proyecto1nivel1");
    Project paux = aux.getInstance();
    aux.CreateNewTask("Tasca1nivel2");
    aux.CreateNewTask("Tasca2nivel2");

  }

}