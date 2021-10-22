import java.util.ArrayList;
import java.util.List;

public class Project extends Node{

  double TotalTime;
  int Num_Childs;

  List<Node> Childs= new ArrayList();

  float calculateTotalTime() {
    return 0;
  }

  void CreateNewTask(int TaskType) {
    Task tasca=new Task(TaskType);
    Childs.add(tasca);
    Num_Childs += 1;
  }
  void CreateNewSubProject() {
    Project proyecto=new Project();
    Childs.add(proyecto);
    Num_Childs += 1;
  }
  void DeleteTask(Node nodo){

      Childs.remove(Childs.indexOf(nodo));
      Num_Childs -= 1;
  }
  void DeleteProyecto(Project proyecto){

    Childs.remove(Childs.indexOf(proyecto));
    Num_Childs -= 1;
  }

  public Project(){
    this.TotalTime=0;
    this.Num_Childs=0;
  }
}
