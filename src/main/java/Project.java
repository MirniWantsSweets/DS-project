import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Project extends Node {

  float totalTime;
  int numChildsTask;
  int numChildsProject;
  Logger logger = LoggerFactory.getLogger("TimeTracker.Project");

  //List<Task> childsTask = new ArrayList();
  //List<Project> childsProject = new ArrayList();
  List<Node> childs = new ArrayList<Node>();
  
  public Project(String name, List<String> tag) {
    this.totalTime = 0;
    this.numChildsTask = 0;
    this.numChildsProject = 0;
    this.name = name;
    this.father = null;
    this.startDate = LocalDateTime.now();
    this.getID();
    tags = new ArrayList();
    for (int i = 0; i < tag.size(); i++) {
      tags.add(tag.get(i));
    }
  }

  public Project(JSONObject json) {
    loadJSON(json);
  }
  
  // Task getChildTaskByName(String nombre) {
  //   for (Task x : childsTask) {
  //     if (x.getName() == nombre) {
  //       return x;
  //     }
  //   }
  //   return null;

  // }

  Node getChildByName(String name) {
    for (Node x : childs) {
      if (x.getName() == name) {
        return x;
      }
    }
    return null;
  }

  // Project getChildProjectByName(String nombre) {

  //   for (Project x : childsProject) {
  //     if (x.getName() == nombre) {
  //       return x;
  //     }
  //   }
  //   return null;

  // }

  void createNewTask(String name, List<String> tag, Observable reloj) {
    Task task = new Task(name, tag);
    //childsTask.add(task);
    childs.add(task);
    task.father = this;
    numChildsTask += 1;
    reloj.addObserver(task);
  }

  void createNewSubProject(String name, List<String> tag) {
    Project project = new Project(name, tag);
    project.father = this;
    childs.add(project);
    //childsProject.add(project);
    numChildsProject += 1;
  }

  // void deleteTask(Node node) {

  //   childsTask.remove(childsTask.indexOf(node));
  //   numChildsTask -= 1;
  // }

  // void deleteProject(Project proyecto) {

  //   childsProject.remove(childsProject.indexOf(proyecto));
  //   numChildsProject -= 1;
  // }

  void deleteChild(Node node) {
    childs.remove(childs.indexOf(node));
    if (node.getInstance() instanceof Project) {
      numChildsProject -= 1;
    }
    else if (node.getInstance() instanceof Task) {
      numChildsTask -= 1;
    }
    else {
      logger.error("DeleteChild received unexpected class type");
    }
  }

  //muestra informacion sobre los parametros del proyecto
  @Override
  void display() {
    logger.info("Project: " + getName());
    this.calculateTotalTime();
    if (childs.size() == 0) {
      logger.info("This project doesn't have any started task yet.");
    }
    else {
      for (int i = 0; i < childs.size(); i++ )
        childs.get(i).display();
    }
    
    logger.info("Project total time: " + this.totalTime);

    // if (numChildsTask > 0) {
    //   logger.info('\n' + "Tasks total time: " + totalTaskTime);
    // }

    // if (numChildsProject > 0) {
    //   logger.info("SubProjects total time: " + totalSubProjectTime);
    // }


    if (!tags.isEmpty()) {
      logger.info("Tags:");
      for (int i = 0; i < tags.size(); i++) {
        logger.info(" " + tags.get(i));
      }
    }
  }

  @Override
  float calculateTotalTime() {
    float totalTime = 0;
    //float aux = 0, totalProjectTime = 0, totalTaskTime = 0;
    
    for (int i = 0; i < childs.size(); i++)
      // if (childs.get(i) instanceof Project) {
      //   aux = childs.get(i).calculateTotalTime();
      //   totalTime += aux; 
      //   totalProjectTime += aux; 
      // }
      // else if(childs.get(i) instanceof Task) {
      //   aux = childs.get(i).calculateTotalTime();
      //   totalTime += aux;
      //   totalTaskTime += aux;
      // }
      // else {
      //   logger.error("calculateTotalTIme received unexpected class type");
      // }
      totalTime += childs.get(i).calculateTotalTime();

    return this.totalTime = totalTime;
  }

  @Override
  public Project getInstance() {
    return this;
  }

  @Override
  public LocalDateTime calculateFinalDateTime() {
    LocalDateTime finalDate = this.startDate;
    LocalDateTime aux;
    for ( int i = 0; i < childs.size(); i++ ) {
      if((aux = childs.get(i).calculateFinalDateTime()) != null && aux.isAfter(finalDate)) {
        finalDate = aux;
      }
    }

    return this.endDate = finalDate;
  }

  @Override
  public JSONObject getJSON() {
    JSONObject json = new JSONObject();
    
    json.put("name", this.name);
    json.put("id", this.id);
    json.put("class", "project");
    json.put("numChildsProject", this.numChildsProject);
    json.put("numChildsTask", this.numChildsTask);
    json.put("initialDate", this.startDate.format(this.formatter));
    calculateFinalDateTime();
    json.put("finalDate", this.endDate.format(this.formatter));
    json.put("duration", this.totalTime);
    json.put("tags", new JSONArray(this.tags));
    JSONArray array = new JSONArray();
    
    for (int i = 0; i < this.childs.size(); i++) {
      array.put(this.childs.get(i).getJSON());
    }

    json.put("activities", array);
    
    //json.put("startTime", startTime);
    //json.put("endTime", endTime);
    //json.put("workingTime", workingTime);

    return json;
  }

  @Override
  public void loadJSON(JSONObject json) {
    this.name = (String) json.get("name");
    this.id = (int) json.get("id");
    this.numChildsProject = (int) json.get("numChildsProject");
    this.numChildsTask = (int) json.get("numChildsTask");
    this.startDate = LocalDateTime.parse((String) json.get("initialDate"), this.formatter);
    this.endDate = LocalDateTime.parse((String) json.get("finalDate"), this.formatter);
    this.totalTime = (int) json.get("duration");
    JSONArray array = (JSONArray) json.get("activities");
    Node node;
    for ( int i = 0; i < array.length(); i++ ) {
      if(((String) ((JSONObject) array.get(i)).get("class")).equals("project")) {
        this.childs.add(node = new Project((JSONObject) array.get(i)));
      }
      else if (((String) ((JSONObject) array.get(i)).get("class")).equals("task")) {
        this.childs.add(node = new Task((JSONObject) array.get(i)));
      }
    }

    array = (JSONArray) json.get("tags");
    this.tags = new ArrayList();
    for ( int i = 0; i < array.length(); i++ ) {
      this.tags.add((String) array.get(i));
    }
  }
}

