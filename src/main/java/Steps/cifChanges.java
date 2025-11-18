package Steps;

import Helpers.ExcelHelper;
import Helpers.ScreenRecorder;
import Helpers.functions;
import io.cucumber.java.en.And;

import java.io.IOException;

public class cifChanges {
    private final functions func = new functions();
    public ScreenRecorder screenRecorder;

        @And("INIT")
    public void init() throws IOException {
            ExcelHelper.init("C:\\Users\\Shahroze.Janjua\\Desktop\\t24.xlsx");

        }
    @And("Perform Test")
    public void performTest() throws InterruptedException, IOException {
        screenRecorder = new ScreenRecorder();
        screenRecorder.startRecording("output.avi");

        func.perform();

        screenRecorder.stopRecording();
    }

       @And("Close it")
    public void close_it() {
           func.close();

       }
}



