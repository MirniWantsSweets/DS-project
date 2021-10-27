import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
class SingletonClock extends Observable {
  private Timer clock = null;
  private static SingletonClock instance = null;

  private SingletonClock() {
    clock = new Timer();
  }
  LocalDateTime localtime(){ //Retorna fecha actual
    return LocalDateTime.now();
  }
  public static void start(){ //Inicia conteo del timer
    if (instance == null) instance = new SingletonClock();

    TimerTask timerTask = new TimerTask()
    {
      public void run()
      {
        
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
