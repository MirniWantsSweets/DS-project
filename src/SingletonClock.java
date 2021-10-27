import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
class SingletonClock extends Observable {
  Timer clock;
  public SingletonClock() {
    clock = new Timer();
  }
  LocalDateTime localtime(){ //Retorna fecha actual
    return LocalDateTime.now();
  }
  public void start(){ //Inicia conteo del timer
    TimerTask timerTask = new TimerTask()
    {
      public void run()
      {
        
        notifyObservers();

      }
    };





    clock.scheduleAtFixedRate(timerTask, 0, 1000);
  }

}
