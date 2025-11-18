package Helpers;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ScreenRecorder {

    private Process process;
    private String ffmpegPath = "C:\\Users\\Shahroze.Janjua\\Desktop\\ffmpeg-master-latest-win64-gpl-shared\\ffmpeg-master-latest-win64-gpl-shared\\bin\\ffmpeg.exe"; // Path to FFmpeg executable (make sure it's in your PATH)

    public void startRecording(String outputFile) throws IOException, InterruptedException {
        String command = String.format("%s -f gdigrab -framerate 120 -i desktop %s", ffmpegPath, outputFile);
        process = new ProcessBuilder(command.split(" ")).start();

        System.out.println("Recording started");
    }

    public void stopRecording() throws IOException {
        if (process != null) {
            process.destroy();
            System.out.println("Recording stopped");
        }
    }
}
