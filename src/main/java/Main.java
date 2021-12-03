import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;


public class Main {
  static Logger loggerFita1 = LoggerFactory.getLogger("TimeTracker.MainFita1");
  static Logger loggerFita2 = LoggerFactory.getLogger("TimeTracker.MainFita2");
  static Logger logger = LoggerFactory.getLogger("TimeTracker.Main");

  public static void TestFita1() throws InterruptedException, IOException{
    //Test B parecido al anterior en el que creamos arbol, mediante start y stop
    //creamos intervalos para las tascas y comprovamos que los tiempos de estas
    //sean correctos

    loggerFita1.info("/////////////////////////TEST FITA 1/////////////////////////////////////");
    Project test = new Project("TestB", new ArrayList());
    loggerFita1.info("Creating Task Transportation...");
    test.createNewTask("transportation", new ArrayList(),SingletonClock.getInstance());
    Task transportation = test.getChildByName("transportation").getInstance();
    loggerFita1.info("Ok, proceding to start transportation...");
    transportation.start(SingletonClock.getInstance());
    loggerFita1.info("Start Ok waiting 4 sec...");
    Thread.sleep(4000);
    loggerFita1.info("Stoping transportation...");
    transportation.stop(SingletonClock.getInstance());
    loggerFita1.info("Stop ok, printing task:");
    transportation.display();
    Thread.sleep(2000);
    loggerFita1.info("Creating Task FirstList...");
    test.createNewTask("first list", new ArrayList(), SingletonClock.getInstance());
    Task firstlist = test.getChildByName("first list").getInstance();
    loggerFita1.info("Ok, proceding to start FirstList...");
    firstlist.start(SingletonClock.getInstance());
    loggerFita1.info("Start Ok waiting 6 sec...");
    Thread.sleep(6000);
    loggerFita1.info("Creating Task SecondList...");
    test.createNewTask("second list", new ArrayList(), SingletonClock.getInstance());
    Task secondlist = test.getChildByName("second list").getInstance();
    loggerFita1.info("Ok, proceding to start SecondList...");
    secondlist.start(SingletonClock.getInstance());
    loggerFita1.info("Start Ok waiting 4 sec...");
    Thread.sleep(4000);
    loggerFita1.info("Stoping FirstList...");
    firstlist.stop(SingletonClock.getInstance());
    loggerFita1.info("Stop ok, printing task:");
    firstlist.display();
    Thread.sleep(2000);
    loggerFita1.info("Stoping SecondList...");
    secondlist.stop(SingletonClock.getInstance());
    loggerFita1.info("Stop ok, printing task:");
    secondlist.display();
    Thread.sleep(2000);
    loggerFita1.info("Proceding to start transportation again...");
    transportation.start(SingletonClock.getInstance());
    loggerFita1.info("Start Ok waiting 4 sec...");
    Thread.sleep(4000);
    loggerFita1.info("Stoping transportation...");
    transportation.stop(SingletonClock.getInstance());
    loggerFita1.info("Stop ok, printing task:");
    transportation.display();
    Dump(test.getJSON());
  }

  public static void testFita2() throws InterruptedException, IOException{
    loggerFita2.info("/////////////////////////TEST FITA 2/////////////////////////////////////");
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
    Project swDesign = root.getChildByName("softwareDesign").getInstance();
    swDesign.createNewSubProject("problems", tagsToAdd);
    swDesign.createNewSubProject("projectTimeTracker", tagsToAdd);
    Project problems = swDesign.getChildByName("problems").getInstance();
    tagsToAdd.clear();
    tagsToAdd.add("java");
    problems.createNewTask("firstList", tagsToAdd, SingletonClock.getInstance());
    tagsToAdd.clear();
    tagsToAdd.add("dart");
    problems.createNewTask("secondList", tagsToAdd, SingletonClock.getInstance());

    tagsToAdd.clear();
    tagsToAdd.add("java");
    tagsToAdd.add("IntelliJ");
    Project projectTimeTracker = swDesign.getChildByName("projectTimeTracker").getInstance();
    projectTimeTracker.createNewTask("firstMileston", tagsToAdd, SingletonClock.getInstance());
    tagsToAdd.clear();
    projectTimeTracker.createNewTask("readHandout", tagsToAdd, SingletonClock.getInstance());
    Iterator it = new Iterator(root);
    it.searchTaskByTag("java");
  }

  public static void Dump(JSONObject object) throws IOException {
    Path file = Path.of("dump.json");
    if (!Files.exists(file)) {
      Files.writeString(file, object.toString());
      logger.info("Specified JSONObject dumped into ./dump.json successfully.");
    }
    else {
      if (!Files.isDirectory(file)) {
        Files.delete(file);
        Files.writeString(file, object.toString());
        logger.info("Specified JSONObject dumped into ./dump.json successfully.");
      }
      logger.error("Can't dump tree to JSON file: Specified filename is a directory.");
    }
  }

  public static JSONObject UnDump() throws IOException {
    Path file = Path.of("dump.json");
    if (Files.exists(file) && !Files.isDirectory(file))
      return new JSONObject(Files.readString(file));
    else if (!Files.exists(file)) {
      logger.error("Can't load tree from JSON file: Specified filename does not exist.");
    }
    else {
      logger.error("Can't load tree from JSON file: Specified filename is a directory.");
    }
    return new JSONObject();
  }

  public static void main(String[] args) throws InterruptedException, IOException {

    SingletonClock.startTimer();


    //UnDump()

    //Dump();

    Project project = new Project(UnDump());
    

    //Fita1
    //TestFita1();
    //Fita2
    //testFita2();
    System.exit(0);
   }
}

