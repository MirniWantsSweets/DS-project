import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Project extends Node {

  double totalTime;
  int numChildsTask;
  int numChildsProject;
  Logger logger = LoggerFactory.getLogger("TimeTracker.Project");

  List<Task> childsTask = new ArrayList();
  List<Project> childsProject = new ArrayList();

  public Project(String name) {
    this.totalTime = 0;
    this.numChildsTask = 0;
    this.numChildsProject = 0;
    this.name = name;
    this.father = null;
  }




  Task getChildTaskByName(String nombre) {
    for (Task x : childsTask) {
      if (x.getName() == nombre) {
        return x;
      }
    }
    return null;

  }

  Project getChildProjectByName(String nombre) {

    for (Project x : childsProject) {
      if (x.getName() == nombre) {
        return x;
      }
    }
    return null;

  }

  void createNewTask(String name, Observable reloj) {
    Task task = new Task(name);
    childsTask.add(task);
    task.father = this;
    numChildsTask += 1;
    reloj.addObserver(task);
  }

  void createNewSubProject(String name) {
    Project project = new Project(name);
    project.father = this;
    childsProject.add(project);
    numChildsProject += 1;
  }

  void deleteTask(Node node) {

    childsTask.remove(childsTask.indexOf(node));
    numChildsTask -= 1;
  }

  void deleteProyecto(Project proyecto) {

    childsProject.remove(childsProject.indexOf(proyecto));
    numChildsProject -= 1;
  }

  //muestra informacion sobre los parametros del proyecto
  void displayProject() {
    System.out.println("**********");
    System.out.print("Project: " + getName());
    float totalTime = 0;
    if (childsTask.isEmpty()) {
      System.out.println("This Project doesn't have any started task yet");
    }
    for (int i = 0; i < numChildsTask; i++) {
      totalTime = totalTime + childsTask.get(i).calculateTotalTime();
    }

    if (!childsTask.isEmpty()) {
      System.out.println('\n' + "Tasks total time: " + totalTime);
    }
    this.totalTime = totalTime;
    totalTime = 0;

    for (int i = 0; i < numChildsProject; i++) {
      for (int n = 0; n < childsProject.get(i).numChildsTask; n++) {
        totalTime = totalTime + childsProject.get(i).childsTask.get(n).calculateTotalTime();
      }
    }
    if (!childsProject.isEmpty()) {
      System.out.println("SubProjects total time: " + totalTime);
    }
    this.totalTime = this.totalTime + totalTime;
    System.out.println("**********");
  }

  float calculateTotalTime() {
    return 0;
  }

  @Override
  public Project getInstance() {
    return this;
  }
}

