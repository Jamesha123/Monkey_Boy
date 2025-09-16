/*
 * Decompiled with CFR 0.152.
 */
package main;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import main.GamePanel;

public class Main {
    public static JFrame window;

    public static void main(String[] stringArray) {
        window = new JFrame();
        window.setDefaultCloseOperation(3);
        window.setResizable(false);
        window.setTitle("Monkey Boy");
        new Main().setIcon();
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        gamePanel.config.loadConfig();
        if (gamePanel.fullScreenOn) {
            window.setUndecorated(true);
        }
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }

    public void setIcon() {
        try {
            BufferedImage bufferedImage = ImageIO.read(this.getClass().getResourceAsStream("/Player/Walking sprites/boy_right_1.png"));
            if (bufferedImage != null) {
                ImageIcon imageIcon = new ImageIcon(bufferedImage);
                window.setIconImage(imageIcon.getImage());
            }
        }
        catch (Exception exception) {
            System.out.println("Could not load window icon: " + exception.getMessage());
        }
    }
}
