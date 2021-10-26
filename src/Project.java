import java.util.ArrayList;
import java.util.List;

public class Project extends Node{

  double TotalTime;
  int Num_Childs_Task;
  int Num_Childs_Project;
  

  List<Task> ChildsTask= new ArrayList();
  List<Project> ChildsProject= new ArrayList();

  float calculateTotalTime() {
    return 0;
  }

  Node getPadre(){ //Retorna el padre
    return padre;
  }



  Task getChildTaskName(String nombre){ //Retorna tarea hija en funcion del nombre

      for (Task x:ChildsTask){
        if (x.getName()== nombre){
          return x;
        }
      }
      return null;

  }
  Project getChildProjectName(String nombre){ //Retorna proyecto hijo en funcion del nombre

    for (Project x:ChildsProject){
      if (x.getName()== nombre){
        return x;
      }
    }
    return null;

  }
  void CreateNewTask(String name,Observable reloj) { //Crea nueva tarea hija
    Task task=new Task(name);
    ChildsTask.add(task);
    task.padre=this;
    Num_Childs_Task += 1;
    reloj.addObserver(task);
  }
  void CreateNewSubProject(String name) { //Crea nuevo proyecto hijo
    Project project=new Project(name);
    project.padre=this;
    ChildsProject.add(project);
    Num_Childs_Project += 1;
  }
  void DeleteTask(Node node){ //Elimina una tarea hija

    ChildsTask.remove(ChildsTask.indexOf(node));
    Num_Childs_Task -= 1;
  }
  void DeleteProyecto(Project proyecto){ //Elimina un proyecto hijo

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
