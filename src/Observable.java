import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Observable{
  List<Observer> Observers= new ArrayList();
  public void addObserver (Observer observer){

    Observers.add(observer);

  };
  public void deleteObserver (Observer observer){

    Observers.remove(Observers.indexOf(Observers));

  };
  public void setChanged(){};
  public void notifyObservers(SingletonClock clock){


    for (int i=0;i<Observers.size(); i+=1){
      Observer aux = Observers.get(i);
      aux.update(clock);
    }

  };
}