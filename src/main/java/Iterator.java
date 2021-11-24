import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Iterator {

  Project project;
  Task task;
  List<Task> taskList;
  List<Project> projectList;
  Project root;
  Logger logger = LoggerFactory.getLogger("TimeTracker.Iterator");

  public Iterator(Task node) {
    task = node;
    project = null;
    Project root = null;
    taskList = new ArrayList();
    projectList = new ArrayList();
    root();

  }

  public Iterator(Project node) {
    project = node;
    task = null;
    Project root = null;
    taskList = new ArrayList();
    projectList = new ArrayList();
    root();

  }

 // guarda raiz del arbol
  void root() {

    if (project != null) {
      root = project;
    } else {
      root = task.getFather();
    }

    while (root.getFather() != null) {
      root = root.getFather();
    }

  }

 //Mediante el algoritmo dfs recorre todo el arbol y guarda sus elementos de forma ordenada
  void dfs(Project projectForSearch) {

    for (int j = 0; j < projectForSearch.numChildsTask; j++) {
      taskList.add(projectForSearch.childsTask.get(j));
    }

    for (int i = 0; i < projectForSearch.numChildsProject; i++) {
      projectList.add(projectForSearch.childsProject.get(i));

      dfs(projectForSearch.childsProject.get(i));
    }

  }

  void next() {

    boolean found = false;
    if (projectList.isEmpty()) {
      projectList.add(root);
      dfs(root);
    }

    if (project != null) {
      for (int i = 0; i < projectList.size(); i++) {
        if (projectList.get(i).name == project.name && ! found) {
          found = true;
          if (i == projectList.size() - 1) {
            project = null;
            task = taskList.get(0);
          } else {
            project = projectList.get(i + 1);
            task = null;
          }

        }
      }
    } else {
      for (int i = 0; i < taskList.size(); i++) {
        if (taskList.get(i).name == task.name && !found)  {
          if (i == taskList.size() - 1) {
            project = null;
            task = null;
            found = true;
          } else {
            task = taskList.get(i + 1);
            found = true;
          }

        }
      }
    }


  }



  Boolean hasNext() {
    boolean hasNext = false;
    if (projectList.isEmpty()) {
      projectList.add(root);
      dfs(root);
    }

    if (project != null && !taskList.isEmpty()) {
      hasNext = true;
    } else {
      for (int i = 0; i < taskList.size(); i++) {
        if (taskList.get(i).name == task.name)  {
          if (i != taskList.size() - 1) {
            hasNext = true;
          }

        }
      }
    }
    return hasNext;
  }

}
