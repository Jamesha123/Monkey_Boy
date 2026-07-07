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

    public InteractiveTile(GamePanel gamePanel, int col, int row) {
        super(gamePanel);
        this.gp = gamePanel;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean hasCorrectItem = false;
        return hasCorrectItem;
    }

    public void playSE() {
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile destroyedForm = null;
        return destroyedForm;
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
        int screenX = this.worldX - this.gp.player.worldX + this.gp.player.screenX;
        int screenY = this.worldY - this.gp.player.worldY + this.gp.player.screenY;
        if (this.inCamera()) {
            graphics2D.drawImage((Image)this.down1, screenX, screenY, null);
        }
    }
}
