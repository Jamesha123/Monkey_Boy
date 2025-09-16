/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  entity.Entity
 *  main.GamePanel
 */
package objects;

import entity.Entity;
import java.util.Objects;
import main.GamePanel;

public class OBJ_Tent
extends Entity {
    GamePanel gp;
    public static final String objName = "Tent";

    public OBJ_Tent(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 6;
        this.name = objName;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/tent.png", 48, 48);
        this.description = "[" + this.name + "]\nYou can sleep the \nnight.";
        this.stackable = true;
        this.price = 200;
    }

    public boolean use(Entity entity) {
        block3: {
            block2: {
                int n = this.gp.eManager.lighting.dayState;
                Objects.requireNonNull(this.gp.eManager.lighting);
                if (n == 2) break block2;
                int n2 = this.gp.eManager.lighting.dayState;
                Objects.requireNonNull(this.gp.eManager.lighting);
                if (n2 != 1) break block3;
            }
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 8;
            this.gp.playSE(14);
            this.gp.player.getSleepingImage(this.down1);
            return true;
        }
        this.gp.ui.addMessage("Cannot sleep during the day!");
        Objects.requireNonNull(this.gp);
        this.gp.gameState = 3;
        return false;
    }
}
