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

public class OBJ_Key
extends Entity {
    GamePanel gp;
    public static final String objName = "Key";

    public OBJ_Key(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 6;
        this.name = objName;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/key.png", 48, 48);
        this.description = "[" + this.name + "]\nUse to open doors.";
        this.price = 100;
        this.stackable = true;
        this.setDialogue();
    }

    public void setDialogue() {
        this.dialogues[0][0] = "You use the " + this.name + " and open the door.";
        this.dialogues[1][0] = "PLAYER: This must be the golden key to the dungeon. \nI feel bad for those adventurers.";
        this.dialogues[1][1] = "PLAYER: I need to find the dungeon now. \nThe old wizard probably knows where it is. \nPlus I can go restock on some supplies.";
    }

    public void pickUpObjectDialogue() {
        this.startDialogue(this, 1);
    }

    public boolean use(Entity entity) {
        int n = this.getDetected(entity, this.gp.obj, "Door");
        if (n != 999) {
            this.startDialogue(this, 0);
            this.gp.playSE(3);
            this.gp.obj[this.gp.currentMap][n] = null;
            return true;
        }
        return false;
    }
}
