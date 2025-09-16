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

public class OBJ_Door
extends Entity {
    GamePanel gp;
    public static final String objName = "Door";

    public OBJ_Door(GamePanel gamePanel, int n) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 8;
        this.name = objName;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/door.png", 48 * n, 48 * n);
        this.collision = true;
        this.solidArea.x = 0;
        this.solidArea.y = 0;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        Objects.requireNonNull(gamePanel);
        this.solidArea.width = 48 * n;
        Objects.requireNonNull(gamePanel);
        this.solidArea.height = 48 * n;
        this.setDialogue();
    }

    public void setDialogue() {
        this.dialogues[0][0] = "PLAYER: The door is locked. I need a key to open it.";
        this.dialogues[1][0] = "You use a Key and open the door.";
    }

    public void interact() {
        int n = this.gp.player.searchItemInInventory("Key");
        if (n != 999) {
            int n2 = this.getDetected((Entity)this.gp.player, this.gp.obj, objName);
            if (n2 != 999) {
                if (((Entity)this.gp.player.inventory.get((int)n)).amount > 1) {
                    --((Entity)this.gp.player.inventory.get((int)n)).amount;
                } else {
                    this.gp.player.inventory.remove(n);
                }
                this.startDialogue(this, 1);
                this.gp.playSE(3);
                this.gp.obj[this.gp.currentMap][n2] = null;
            }
        } else {
            this.startDialogue(this, 0);
        }
    }
}
