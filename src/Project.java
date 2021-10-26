import java.util.ArrayList;
import java.util.List;

public class Project extends Node{

  double TotalTime;
  int Num_Childs;
  Node padre;

  List<Node> Childs= new ArrayList();

  float calculateTotalTime() {
    return 0;
  }

  Node getPadre(){
    return padre;
  }

  Node getChildIndex(int i){
    if (i<=Num_Childs){
      return Childs.get(i);
    }else{
      return null;
    }
  }

  Node getChildName(String nombre){

      for (Node x:Childs){
        if (x.getName()== nombre){
          return x;
        }
      }
      return null;

  }
  void CreateNewTask(String name) {
    Task tasca=new Task(name);
    Childs.add(tasca);
    Num_Childs += 1;
  }
  void CreateNewSubProject(String name) {
    Project proyecto=new Project(name);
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

  public Project(String nombre){
    this.TotalTime=0;
    this.Num_Childs=0;
    this.name= nombre;
    this.type=true;
  }
  @Override
  public Project getInstance(){
    return this;
  }
}
