java.util.Timer
class Reloj implements Observable {

 
     TimerTask timerTask = new TimerTask()
     {
         public void run() 
         {
             notifyObservers();
         }
     };
    


     Timer Reloj = new Timer();
     
     Reloj.scheduleAtFixedRate(timerTask, 0, 200);

}