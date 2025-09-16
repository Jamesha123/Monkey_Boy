/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import entity.Entity;
import java.awt.Graphics2D;
import java.awt.Image;
import main.GamePanel;

public class InteractiveTile
extends Entity {
    GamePanel gp;
    public boolean destructible = false;
    public int tileType = 0;

    public InteractiveTile(GamePanel gamePanel, int n, int n2) {
        super(gamePanel);
        this.gp = gamePanel;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean bl = false;
        return bl;
    }

    public void playSE() {
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile interactiveTile = null;
        return interactiveTile;
    }

    @Override
    public void update() {
        if (this.invincible) {
            ++this.invincibleCounter;
            if (this.invincibleCounter > 20) {
                this.invincible = false;
                this.invincibleCounter = 0;
            }
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        int n = this.worldX - this.gp.player.worldX + this.gp.player.screenX;
        int n2 = this.worldY - this.gp.player.worldY + this.gp.player.screenY;
        if (this.inCamera()) {
            graphics2D.drawImage((Image)this.down1, n, n2, null);
        }
    }
}
