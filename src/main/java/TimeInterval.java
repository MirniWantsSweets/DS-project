import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeInterval {
  LocalDateTime startTime = null;
  LocalDateTime endTime = null;

  Logger logger = LoggerFactory.getLogger("TimeTracker.TimeInterval");

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
