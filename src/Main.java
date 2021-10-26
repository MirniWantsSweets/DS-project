package core;

public class Main {
  private static void simpleTest() {
    Task t0 = new Task("study");
    t0.start();
    wait(12);
    t0.stop();
  }
  private static void notSoSimpleTest() {
    Project p1 = new Project("Study");
    Project p2 = p1.CreateNewSubProject("Examen1");
    Task = t1 = p2.CreateNewTask("Tema1");
    t1.start();
    wait(1000);
    t1.stop();
    p1.TotalTime();
  }
  public static void main(String[] args) {
    simpleTest();
    notSoSimpleTest();
  }
}
