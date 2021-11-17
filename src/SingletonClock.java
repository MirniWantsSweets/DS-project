import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

class SingletonClock extends Observable {
  private Timer clock = null;
  private static SingletonClock instance = null;

  //Constructor privado para usar patron Singleton
  private SingletonClock() {
    clock = new Timer();
  }

  //Retorna fecha actual
  LocalDateTime localtime() {
    return LocalDateTime.now();
  }


  public static void startTimer() {
    if (instance == null) {
      instance = new SingletonClock();
    }

    TimerTask timerTask = new TimerTask() {
      public void run() {
        notifyObservers();
      }
    };
    instance.getClock().scheduleAtFixedRate(timerTask, 0, 200);
  }

  public static SingletonClock getInstance() {
    return instance;
  }

  public Timer getClock() {
    return clock;
  }

}