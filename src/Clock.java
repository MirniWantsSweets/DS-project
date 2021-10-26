import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDateTime;
class SingletonClock implements Observable {

    Date localtime(){
        return LocalDateTime.now();
    }
  void start(){
        TimerTask timerTask = new TimerTask()
        {
            public void run() 
            {
                notifyObservers(this);
            }
        };
            


            Timer clock = new Timer();
            
            clock.scheduleAtFixedRate(timerTask, 0, 200);
    }
     
}