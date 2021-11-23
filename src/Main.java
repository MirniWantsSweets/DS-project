public class Main {

  public static void main(String[] args) throws InterruptedException {


    SingletonClock.startTimer();

    //Creamos arbol de proyectos y tareas
    Project p1 = new Project("raiz");
    p1.createNewTask("Tasca1nivel1", SingletonClock.getInstance());
    p1.createNewSubProject("Proyecto1nivel1");
    p1.createNewSubProject("Proyecto2nivel1");
    Project paux = p1.getChildProjectByName("Proyecto1nivel1");
    paux.createNewTask("Tasca1nivel2", SingletonClock.getInstance());
    paux.createNewTask("Tasca2nivel2", SingletonClock.getInstance());

    Task taux = p1.getChildTaskByName("Tasca1nivel1");
    taux.start(SingletonClock.getInstance());

    //Muestra nombre, fecha en que se creó la tarea, tiempo total de la tarea y
    // fechas de inicio y finalizacion de los intervalos
    taux.displayTask();

    //A continuacion realizamos varias pruebas de start y stop de tareas y se van llamando
    // displayTask para ver que los resultados son correctos
    Thread.sleep(3000);
    taux.stop(SingletonClock.getInstance());
    Thread.sleep(1000);

    //Muestra nombre, fecha en que se creó la tarea, tiempo total de la tarea y
    // fechas de inicio y finalizacion de los intervalos
    taux.displayTask();

    Task taux2 = paux.getChildTaskByName("Tasca1nivel2");
    Task taux3 = paux.getChildTaskByName("Tasca2nivel2");

    taux.start(SingletonClock.getInstance());
    taux2.start(SingletonClock.getInstance());
    taux3.start(SingletonClock.getInstance());

    Thread.sleep(2000); //lo paramos 2 segundos

    taux.stop(SingletonClock.getInstance());
    taux2.stop(SingletonClock.getInstance());
    taux3.stop(SingletonClock.getInstance());

    //Muestra nombre, fecha en que se creó la tarea, tiempo total de la tarea y
    // fechas de inicio y finalizacion de los intervalos
    taux.displayTask();

    Thread.sleep(2000);

    //Muestra nombre, fecha en que se creó la tarea, tiempo total de la tarea y
    // fechas de inicio y finalizacion de los intervalos
    taux2.displayTask();

    Thread.sleep(2000);

    p1.displayProject();
    paux.displayProject();

    //Test B parecido al anterior en el que creamos arbol, mediante start y stop
    //creamos intervalos para las tascas y comprovamos que los tiempos de estas
    //sean correctos
    /*System.out.println("/////////////////////////TEST B/////////////////////////////////////");
    Project test = new Project("TestB");
    System.out.println("Creating Task Transportation...");
    test.createNewTask("transportation", SingletonClock.getInstance());
    Task transportation = test.getChildTaskByName("transportation");
    System.out.println("Ok, proceding to start transportation...");
    transportation.start(SingletonClock.getInstance());
    System.out.println("Start Ok waiting 4 sec...");
    Thread.sleep(4000);
    System.out.println("Stoping transportation...");
    transportation.stop(SingletonClock.getInstance());
    System.out.println("Stop ok, printing task:");
    transportation.displayTask();
    Thread.sleep(2000);
    System.out.println("Creating Task FirstList...");
    test.createNewTask("first list", SingletonClock.getInstance());
    Task firstlist = test.getChildTaskByName("first list");
    System.out.println("Ok, proceding to start FirstList...");
    firstlist.start(SingletonClock.getInstance());
    System.out.println("Start Ok waiting 6 sec...");
    Thread.sleep(6000);
    System.out.println("Creating Task SecondList...");
    test.createNewTask("second list", SingletonClock.getInstance());
    Task secondlist = test.getChildTaskByName("second list");
    System.out.println("Ok, proceding to start SecondList...");
    secondlist.start(SingletonClock.getInstance());
    System.out.println("Start Ok waiting 4 sec...");
    Thread.sleep(4000);
    System.out.println("Stoping FirstList...");
    firstlist.stop(SingletonClock.getInstance());
    System.out.println("Stop ok, printing task:");
    firstlist.displayTask();
    Thread.sleep(2000);
    System.out.println("Stoping SecondList...");
    secondlist.stop(SingletonClock.getInstance());
    System.out.println("Stop ok, printing task:");
    secondlist.displayTask();
    Thread.sleep(2000);
    System.out.println("Proceding to start transportation again...");
    transportation.start(SingletonClock.getInstance());
    System.out.println("Start Ok waiting 4 sec...");
    Thread.sleep(4000);
    System.out.println("Stoping transportation...");
    transportation.stop(SingletonClock.getInstance());
    System.out.println("Stop ok, printing task:");
    transportation.displayTask();*/

    Iterator it = new Iterator(paux);
    it.next();
    it.next();
    it.next();
    it.next();
    it.next();
  }

}

