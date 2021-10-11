import java.util.ArrayList;
import java.util.List;

public class Project extends Node{

  double TotalTime;
  int Num_Hijos;

  List<Node> Hijos= new ArrayList();

  float calculateTotalTime() {
    return 0;
  }

  void CreateNewTask(int TaskType) {
    Task tasca=new Task(TaskType);
    Hijos.add(tasca);
    Num_Hijos += 1;
  }
  void CreateNewSubProject() {
    Project proyecto=new Project();
    Hijos.add(proyecto);
    Num_Hijos += 1;
  }
  void DeleteTask(Node nodo){

      Hijos.remove(Hijos.indexOf(nodo));
      Num_Hijos -= 1;
  }
  void DeleteProyecto(Project proyecto){

    Hijos.remove(Hijos.indexOf(proyecto));
    Num_Hijos -= 1;
  }

  public Project(){
    this.TotalTime=0;
    this.Num_Hijos=0;
  }
}
