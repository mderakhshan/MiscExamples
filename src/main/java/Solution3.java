import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Mir on 12/10/2016.
 */
public class Solution3 {
    static final int HIGH_TARIFF_COST_PER_SECOND = 3;
    static final int LOW_TARIFF_COST_PER_MIN = 150;
    static final int LOW_TARIF_START_THRESHOLD = 5;

    public static class CallLog {
        int duration;  // in seconds
        String  phoneNo;

        public CallLog (String logEntry) {
            String[] temp = logEntry.split(",");
            this.phoneNo = temp[1].trim();
            String duration = temp[0].trim();
            String[] durationComponents = temp[0].split(":");
            int hours = Integer.parseInt (durationComponents[0].trim());
            int minutes = Integer.parseInt (durationComponents[1].trim());
            int seconds = Integer.parseInt (durationComponents[2].trim());
            this.duration = hours * 3600 + minutes * 60 + seconds;
        }

        public int getTotalMinutes() {
            return (duration / 60);
        }

        public int getSecondsPart() {
            return (duration / 60) % 60;
        }
    }

    public static float solution(String logs) {
        String[]  logsArray = logs.split("\n");
        Map<String, CallLog> logMap = new HashMap<>();
        for (int i = 0; i < logsArray.length; ++i) {
            CallLog log = new CallLog(logsArray[i]);
            if (logMap.containsKey(log.phoneNo)) {
                logMap.get(log.phoneNo).duration += log.duration;
            }
            else {
                logMap.put (log.phoneNo, log);
            }
        }

        Iterator iterator = logMap.entrySet().iterator();
        Map.Entry<String, CallLog> firstEntry = (Map.Entry<String, CallLog>) iterator.next();
        int maxDuration = firstEntry.getValue().duration;
        String maxPhoneNo = firstEntry.getKey();
        while (iterator.hasNext()) {
            Map.Entry<String, CallLog> entry =  (Map.Entry<String, CallLog>) iterator.next();
            int totalSeconds = entry.getValue().duration;
            if (totalSeconds >= maxDuration) {
                if (totalSeconds > maxDuration ||
                        phoneNumericalValue(entry.getKey()) <  phoneNumericalValue (maxPhoneNo)) {
                    maxDuration =  entry.getValue().duration;
                    maxPhoneNo = entry.getKey();
                }
            }
        }

        iterator = logMap.entrySet().iterator();
        float totalCost = 0.0f;
        while (iterator.hasNext()) {
            Map.Entry<String, CallLog> entry =  (Map.Entry<String, CallLog>) iterator.next();
            if (!entry.getKey().equals (maxPhoneNo)) {
                totalCost += calcCallCost (entry.getValue());
            }
        }
        return totalCost;
    }

    private static double calcCallCost (CallLog call) {
        float cost = 0f;
        if (call.getTotalMinutes() < LOW_TARIF_START_THRESHOLD) {
            cost = call.duration * HIGH_TARIFF_COST_PER_SECOND;
        } else {
            int totalMinutes = call.getTotalMinutes();
            if (call.getSecondsPart() > 0) {
                ++totalMinutes;
            }
            cost = totalMinutes * LOW_TARIFF_COST_PER_MIN;
        }
        return cost;
    }

    private static long phoneNumericalValue (String phone) {
        String digitsStr = phone.replace("-", "");
        return Long.valueOf (digitsStr);
    }

    public static void main (String[] args) {
        String log = "00:01:07,400-234-090\n" +
                "00:05:01, 701-080-080\n" +
                "00:05:00,400-234-090";

        float cost = solution(log);
        System.out.println("cost=" + cost);

        boolean test = 5 > 3;
        System.out.println ("test=" + test);
    }
}
