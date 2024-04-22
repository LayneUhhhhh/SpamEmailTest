import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class EmailScheduler {
    private Timer timer;

    public EmailScheduler() {
        timer = new Timer();
    }

    public void dailyEmailRun(int targetHourOfDay, int targetMinuteOfHour) {

        Calendar timeNow = Calendar.getInstance();
        Calendar targetTime = Calendar.getInstance();
        targetTime.set(Calendar.HOUR_OF_DAY, targetHourOfDay);
        targetTime.set(Calendar.MINUTE, targetMinuteOfHour);
        targetTime.set(Calendar.SECOND, 0);

        if (targetTime.before(timeNow)) {
            targetTime.add(Calendar.DAY_OF_MONTH, 1); //schedule for the next day if the time has already passed
        }

        //display scheduled time logic
        String AMOrPM = targetHourOfDay > 12 ? "PM" : "AM";
        int displayHour = targetHourOfDay > 12 ? targetHourOfDay - 12 : targetHourOfDay;
        String extra0 = targetMinuteOfHour < 10 ? "0" : "";
        System.out.println("Set email to send at " + displayHour + ":" + extra0 + targetMinuteOfHour + AMOrPM + " every day");

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                EmailSenderMain.sendEmails();
                System.out.println(" at " + targetTime.getTime() + "\n");
            }
        },
                targetTime.getTime(), 24 * 60 * 60 * 1000); //repeat new email every day
    }
}
