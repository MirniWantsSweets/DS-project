import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
class SingletonClock extends Observable {
  Timer clock;
  public SingletonClock() {
    clock = new Timer();
  }
  LocalDateTime localtime(){
    return LocalDateTime.now();
  }
  public void start(){
    TimerTask timerTask = new TimerTask()
    {
      public void run()
      {
        System.out.println("Task Timer on Fixed Rate");
        notifyObservers();

      }
    };





    clock.scheduleAtFixedRate(timerTask, 0, 1000);
  }

}