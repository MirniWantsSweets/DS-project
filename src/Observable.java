import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Observable{
  private static List<Observer> Observers= new ArrayList(); //Lista de observadores que serán notificados por el Observable
  public void addObserver (Observer observer){ //Añadir observador a la lista de observadores

    Observers.add(observer);

  };
  public void deleteObserver (Observer observer){ //Eliminar observador de la lista de observadores

    Observers.remove(Observers.indexOf(Observers));

  };
  public void setChanged(){}; //**//
  public static void notifyObservers(){ // Notifica a TODOS los observadores con el nuevo estado del observable


    for (int i=0;i<Observers.size(); i+=1){
      Observer aux = Observers.get(i);
      aux.update();
    }

  };
}
