import java.util.Timer;
import java.util.TimerTask;
class SingletonClock implements Observable {

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