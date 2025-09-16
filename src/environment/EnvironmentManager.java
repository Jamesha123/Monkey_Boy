/*
 * Decompiled with CFR 0.152.
 */
package environment;

import environment.Lighting;
import java.awt.Graphics2D;
import main.GamePanel;

public class EnvironmentManager {
    GamePanel gp;
    public Lighting lighting;

    public EnvironmentManager(GamePanel gamePanel) {
        this.gp = gamePanel;
    }

    public void setup() {
        this.lighting = new Lighting(this.gp);
    }

    public void update() {
        this.lighting.update();
    }

    public void draw(Graphics2D graphics2D) {
        this.lighting.draw(graphics2D);
    }
}
