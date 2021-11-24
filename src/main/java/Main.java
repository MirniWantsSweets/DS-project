import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Main {
  /*
   public static void TestFita1(org.slf4j.Logger logger) throws InterruptedException {
     //Test B parecido al anterior en el que creamos arbol, mediante start y stop
     //creamos intervalos para las tascas y comprovamos que los tiempos de estas
     //sean correctos

     logger.info("/////////////////////////TEST FITA 1/////////////////////////////////////");
     Project test = new Project("TestB");
     logger.info("Creating Task Transportation...");
     test.createNewTask("transportation", SingletonClock.getInstance());
     Task transportation = test.getChildTaskByName("transportation");
     logger.info("Ok, proceding to start transportation...");
     transportation.start(SingletonClock.getInstance());
     logger.info("Start Ok waiting 4 sec...");
     Thread.sleep(4000);
     logger.info("Stoping transportation...");
     transportation.stop(SingletonClock.getInstance());
     logger.info("Stop ok, printing task:");
     transportation.displayTask();
     Thread.sleep(2000);
     logger.info("Creating Task FirstList...");
     test.createNewTask("first list", SingletonClock.getInstance());
     Task firstlist = test.getChildTaskByName("first list");
     logger.info("Ok, proceding to start FirstList...");
     firstlist.start(SingletonClock.getInstance());
     logger.info("Start Ok waiting 6 sec...");
     Thread.sleep(6000);
     logger.info("Creating Task SecondList...");
     test.createNewTask("second list", SingletonClock.getInstance());
     Task secondlist = test.getChildTaskByName("second list");
     logger.info("Ok, proceding to start SecondList...");
     secondlist.start(SingletonClock.getInstance());
     logger.info("Start Ok waiting 4 sec...");
     Thread.sleep(4000);
     logger.info("Stoping FirstList...");
     firstlist.stop(SingletonClock.getInstance());
     logger.info("Stop ok, printing task:");
     firstlist.displayTask();
     Thread.sleep(2000);
     logger.info("Stoping SecondList...");
     secondlist.stop(SingletonClock.getInstance());
     logger.info("Stop ok, printing task:");
     secondlist.displayTask();
     Thread.sleep(2000);
     logger.info("Proceding to start transportation again...");
     transportation.start(SingletonClock.getInstance());
     logger.info("Start Ok waiting 4 sec...");
     Thread.sleep(4000);
     logger.info("Stoping transportation...");
     transportation.stop(SingletonClock.getInstance());
     logger.info("Stop ok, printing task:");
     transportation.displayTask();
  }*/

  public static void testFita2(org.slf4j.Logger logger) throws InterruptedException {
    logger.info("/////////////////////////TEST FITA 2/////////////////////////////////////");
    SingletonClock.startTimer();
    List<String> tagsToAdd = new ArrayList();
    Project root = new Project("root", tagsToAdd);

    tagsToAdd.add("java");
    tagsToAdd.add("flutter");
    root.createNewSubProject("softwareDesign", tagsToAdd);
    tagsToAdd.clear();
    tagsToAdd.add("c++");
    tagsToAdd.add("java");
    tagsToAdd.add("python");
    root.createNewSubProject("softwareTesting", tagsToAdd);
    tagsToAdd.clear();
    tagsToAdd.add("SQL");
    tagsToAdd.add("python");
    tagsToAdd.add("c++");
    root.createNewSubProject("databases", tagsToAdd);
    tagsToAdd.clear();
    root.createNewSubProject("taskTransportation", tagsToAdd);
    Project swDesign = root.getChildProjectByName("softwareDesign");
    swDesign.createNewSubProject("problems", tagsToAdd);
    swDesign.createNewSubProject("projectTimeTracker", tagsToAdd);
    Project problems = swDesign.getChildProjectByName("problems");
    tagsToAdd.clear();
    tagsToAdd.add("java");
    problems.createNewTask("firstList", tagsToAdd, SingletonClock.getInstance());
    tagsToAdd.clear();
    tagsToAdd.add("dart");
    problems.createNewTask("secondList", tagsToAdd, SingletonClock.getInstance());

    tagsToAdd.clear();
    tagsToAdd.add("java");
    tagsToAdd.add("IntelliJ");
    Project projectTimeTracker = swDesign.getChildProjectByName("projectTimeTracker");
    projectTimeTracker.createNewTask("firstMileston", tagsToAdd, SingletonClock.getInstance());
    tagsToAdd.clear();
    projectTimeTracker.createNewTask("readHandout", tagsToAdd, SingletonClock.getInstance());
    Iterator it = new Iterator(root);
    it.searchTaskByTag("java");
  }

  public static void main(String[] args) throws InterruptedException {
    Logger loggerFita1 = LoggerFactory.getLogger("TimeTracker.MainFita1");
    Logger loggerFita2 = LoggerFactory.getLogger("TimeTracker.MainFita2");
    SingletonClock.startTimer();
    //Fita1
    //TestFita1(loggerFita1);
    //Fita2
    testFita2(loggerFita2);
  }
}

