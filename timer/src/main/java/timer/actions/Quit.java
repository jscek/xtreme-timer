package timer.actions;

import timer.base.TimerApp;
import timer.utils.PropertiesReader;

public class Quit extends Actions {

    public Quit() {
        this.action = this.getClass().getSimpleName().toLowerCase();
        this.help="Quit";
    }

    @Override
    protected void perform(String[] input, TimerApp app) {
        app.shouldFinish = true;
        saveToStorage(app);
    }

    private void saveToStorage(TimerApp app) {
        String filename = PropertiesReader.getInstance().getProperty("storage", "storage");
        filename = filename.substring(0, filename.indexOf("."));
        if (shouldSave()) {
            app.saveTimerRecords(filename);
        }
    }

    private boolean shouldSave() {
        return PropertiesReader.getInstance().getProperty("saveOnExit", "false").equals("true");
    }
}
