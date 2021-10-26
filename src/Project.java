import java.util.ArrayList;
import java.util.List;

public class Project extends Node{

  double TotalTime;
  int Num_Childs_Task;
  int Num_Childs_Project;
  Project padre;

  List<Task> ChildsTask= new ArrayList();
  List<Project> ChildsProject= new ArrayList();

  float calculateTotalTime() {
    return 0;
  }

  Node getPadre(){
    return padre;
  }



  Task getChildTaskName(String nombre){

      for (Task x:ChildsTask){
        if (x.getName()== nombre){
          return x;
        }
      }
      return null;

  }
  Project getChildProjectName(String nombre){

    for (Project x:ChildsProject){
      if (x.getName()== nombre){
        return x;
      }
    }
    return null;

  }
  void CreateNewTask(String name,Observable reloj) {
    Task task=new Task(name);
    ChildsTask.add(task);
    Num_Childs_Task += 1;
    reloj.addObserver(task);
  }
  void CreateNewSubProject(String name) {
    Project proyecto=new Project(name);
    ChildsProject.add(proyecto);
    Num_Childs_Project += 1;
  }
  void DeleteTask(Node node){

    ChildsTask.remove(ChildsTask.indexOf(node));
    Num_Childs_Task -= 1;
  }
  void DeleteProyecto(Project proyecto){

    ChildsProject.remove(ChildsProject.indexOf(proyecto));
    Num_Childs_Project -= 1;
  }

  public Project(String nombre){
    this.TotalTime=0;
    this.Num_Childs_Task=0;
    this.Num_Childs_Project=0;
    this.name= nombre;

  }
  @Override
  public Project getInstance(){
    return this;
  }
}
