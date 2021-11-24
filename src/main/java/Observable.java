import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class Observable {

  Logger logger = LoggerFactory.getLogger("TimeTracker.Observable");

  //Lista de observadores que serán notificados por el Observable
  private static List<Observer> observers = new ArrayList();

  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  public void deleteObserver(Observer observer) {
    observers.remove(observers.indexOf(observers));
  }

  public void setChanged(){}

  // Notifica a TODOS los observadores con el nuevo estado del observable
  public static void notifyObservers() {
    for (int i = 0; i < observers.size(); i += 1) {
      Observer aux = observers.get(i);
      aux.update();
    }

  }
}
