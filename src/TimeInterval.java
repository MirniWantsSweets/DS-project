import java.time.LocalDateTime;


public class TimeInterval {
  LocalDateTime startTime = null;
  LocalDateTime endTime = null;

  public TimeInterval(SingletonClock clock) {
    startTime = clock.localtime();
  }

  public void endInterval(SingletonClock clock) {
    if (startTime != null && endTime == null) {
      endTime = clock.localtime();
    }
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }
  
  public LocalDateTime getEndTime() {
    return endTime;
  }

}
