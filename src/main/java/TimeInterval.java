import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

public class TimeInterval {
  LocalDateTime startTime = null;
  LocalDateTime endTime = null;
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  Logger logger = LoggerFactory.getLogger("TimeTracker.TimeInterval");

  public TimeInterval(SingletonClock clock) {
    startTime = clock.localtime();
  }
  public TimeInterval(JSONObject json) {
    this.startTime = LocalDateTime.parse((String) json.get("initialTime"), this.formatter);
    this.endTime = LocalDateTime.parse((String) json.get("finalTime"), this.formatter);
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

  public JSONObject getJSON() {
    JSONObject json = new JSONObject();
    json.put("initialTime", startTime.format(formatter));
    json.put("finalTime", endTime.format(formatter));
    return json;
  }

}
