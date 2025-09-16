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

public class OBJ_Door_Iron
extends Entity {
    GamePanel gp;
    public static final String objName = "Iron Door";

    public OBJ_Door_Iron(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 8;
        this.name = objName;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/door_iron.png", 48 * 2, 48 * 2);
        this.collision = true;
        this.solidArea.x = 0;
        this.solidArea.y = 0;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        Objects.requireNonNull(gamePanel);
        this.solidArea.width = 48 * 2;
        Objects.requireNonNull(gamePanel);
        this.solidArea.height = 48 * 2;
        this.setDialogue();
    }

    public void setDialogue() {
        this.dialogues[0][0] = "PLAYER: It won't budge. There must be a way to open \nit.";
    }

    public void interact() {
        this.startDialogue(this, 0);
    }
}
