package timer.actions;

import timer.base.TimerApp;
import timer.report.ReportSender;

public class SendEmail extends Actions {

    public SendEmail() {
        this.action = this.getClass().getSimpleName().toLowerCase();
    }

    @Override
    protected void perform(String[] input, TimerApp app) {
        sendEmail(app, input);
    }

    private void sendEmail(TimerApp app, String[] input) {
        if (input.length == 1) {
            ReportSender.send("extremetimerPE2020@wp.pl", "Test3", "This is the report", app.createReport(null, null, "report"));
        } else if (input.length == 4) {
            ReportSender.send(input[1], input[2], input[3], app.createReport(null, null, "report"));
        } else {
            System.out.println("Wrong parameters provided");
        }
    }
}
