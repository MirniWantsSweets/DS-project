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

  public Project(String name){
    this.TotalTime=0;
    this.Num_Childs_Task=0;
    this.Num_Childs_Project=0;
    this.name= name;

  }
  void displayProject() //muestra informacion sobre los parametros del proyecto
  {
    System.out.println("**********");
    System.out.print("Project: " + getName());
    float totalTime = 0;
    if(ChildsTask.isEmpty()){System.out.println("This Project doesn't have any started task yet");}
    for(int i = 0; i < Num_Childs_Task; i++)
    {
      totalTime = totalTime + ChildsTask.get(i).calculateTotalTime();
    }

    if(!ChildsTask.isEmpty()) {System.out.println('\n' + "Tasks total time: " + totalTime);}
    TotalTime = totalTime;
    totalTime = 0;

    for(int i = 0; i < Num_Childs_Project; i++)
    {
      for(int n = 0; n < ChildsProject.get(i).Num_Childs_Task; n++)
      {
        totalTime = totalTime + ChildsProject.get(i).ChildsTask.get(n).calculateTotalTime();
      }
    }
    if(!ChildsProject.isEmpty()) {System.out.println("SubProjects total time: " + totalTime);}
    TotalTime = TotalTime + totalTime;
    System.out.println("**********");
  }


  @Override
  public Project getInstance(){
    return this;
  }
}

