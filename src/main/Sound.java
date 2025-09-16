/*
 * Decompiled with CFR 0.152.
 */
package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sound() {
        this.soundURL[0] = this.getClass().getResource("/Sound/themeSong.wav");
        this.soundURL[1] = this.getClass().getResource("/Sound/coin.wav");
        this.soundURL[2] = this.getClass().getResource("/Sound/powerup.wav");
        this.soundURL[3] = this.getClass().getResource("/Sound/unlock.wav");
        this.soundURL[4] = this.getClass().getResource("/Sound/fanfare.wav");
        this.soundURL[5] = this.getClass().getResource("/Sound/hitmonster.wav");
        this.soundURL[6] = this.getClass().getResource("/Sound/receivedamage.wav");
        this.soundURL[7] = this.getClass().getResource("/Sound/swingweapon.wav");
        this.soundURL[8] = this.getClass().getResource("/Sound/levelup.wav");
        this.soundURL[9] = this.getClass().getResource("/Sound/cursor.wav");
        this.soundURL[10] = this.getClass().getResource("/Sound/burning.wav");
        this.soundURL[11] = this.getClass().getResource("/Sound/cuttree.wav");
        this.soundURL[12] = this.getClass().getResource("/Sound/gameover.wav");
        this.soundURL[13] = this.getClass().getResource("/Sound/stairs.wav");
        this.soundURL[14] = this.getClass().getResource("/Sound/sleep.wav");
        this.soundURL[15] = this.getClass().getResource("/Sound/blocked.wav");
        this.soundURL[16] = this.getClass().getResource("/Sound/parry.wav");
        this.soundURL[17] = this.getClass().getResource("/Sound/speak.wav");
        this.soundURL[18] = this.getClass().getResource("/Sound/Merchant.wav");
        this.soundURL[19] = this.getClass().getResource("/Sound/Dungeon.wav");
        this.soundURL[20] = this.getClass().getResource("/Sound/chipwall.wav");
        this.soundURL[21] = this.getClass().getResource("/Sound/dooropen.wav");
        this.soundURL[22] = this.getClass().getResource("/Sound/FinalBattle.wav");
    }

    public void setFile(int n) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.soundURL[n]);
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);
            this.fc = (FloatControl)this.clip.getControl(FloatControl.Type.MASTER_GAIN);
            this.checkVolume();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void play() {
        this.clip.start();
    }

    public void loop() {
        this.clip.loop(-1);
    }

    public void stop() {
        this.clip.stop();
    }

    public void checkVolume() {
        switch (this.volumeScale) {
            case 0: {
                this.volume = -80.0f;
                break;
            }
            case 1: {
                this.volume = -20.0f;
                break;
            }
            case 2: {
                this.volume = -12.0f;
                break;
            }
            case 3: {
                this.volume = -5.0f;
                break;
            }
            case 4: {
                this.volume = 1.0f;
                break;
            }
            case 5: {
                this.volume = 6.0f;
            }
        }
        this.fc.setValue(this.volume);
    }
}
