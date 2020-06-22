package timer.fitnesse.fixtures;

import com.opencsv.CSVReader;
import timer.GUI.GUI;
import timer.base.TimerCommandLineApp;
import timer.fitnesse.StaticTimerApp;
import timer.report.SummaryReport;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateReport {

    private TimerCommandLineApp timerApp = new TimerCommandLineApp();

    private GUI gui = new GUI();
    private int timerIndex=0;


    public void setName(String name) {
        timerApp.createTimer(name);
        timerIndex++;
    }

    public void setStart(Boolean start){
        if(start){
            timerApp.startTimer(timerIndex);
        }
    }

    public void setWaitSec(int wait){

            long s = new Date().getTime();
            while (new Date().getTime() - s < wait*1000L) {        }

    }

    public void setStop(boolean stop){
        if(stop){
            timerApp.stopTimer(timerIndex);
        }
    }

    public String reportContent() throws IOException {

        return getReportContent();
    }

    public String getReportContent() throws IOException {

        SummaryReport report= new SummaryReport();
        ArrayList<ArrayList<String>> list = report.createReportContent(timerApp.getTimerRecords());

        StringBuilder html = new StringBuilder();
        html.append("<table>");
        for (ArrayList<String> lis: list) {
            html.append("<tr>");
            for (String li: lis) {
                html.append("<td>").append(li).append("</td>");
            }
            html.append("</tr>");
        }
        html.append("</table>");
        return html.toString();
    }


}
