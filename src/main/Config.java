/*
 * Decompiled with CFR 0.152.
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import main.GamePanel;

public class Config {
    GamePanel gp;

    public Config(GamePanel gamePanel) {
        this.gp = gamePanel;
    }

    public void saveConfig() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("config.txt"));
            if (this.gp.fullScreenOn) {
                bufferedWriter.write("On");
            }
            if (!this.gp.fullScreenOn) {
                bufferedWriter.write("Off");
            }
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(this.gp.music.volumeScale));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(this.gp.se.volumeScale));
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void loadConfig() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("config.txt"));
            String string = bufferedReader.readLine();
            if (string.equals("On")) {
                this.gp.fullScreenOn = true;
            }
            if (string.equals("Off")) {
                this.gp.fullScreenOn = false;
            }
            string = bufferedReader.readLine();
            this.gp.music.volumeScale = Integer.parseInt(string);
            string = bufferedReader.readLine();
            this.gp.se.volumeScale = Integer.parseInt(string);
            bufferedReader.close();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
