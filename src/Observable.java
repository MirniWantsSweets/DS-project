import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Observable{
  List<Observer> Observadores= new ArrayList();
  public void addObserver (Observer observer){

    Observadores.add(observer);

  };
  public void deleteObserver (Observer observer){

    Observadores.remove(Observadores.indexOf(Observadores));

  };
  public void setChanged(){};
  public void notifyObservers(Observer observer){


    for (int i=0;i<Observadores.size(); i+=1){
      Observer aux = Observadores.get(i);
      aux.update();
    }

  };
}