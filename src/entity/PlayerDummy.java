/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.Entity;
import main.GamePanel;

public class PlayerDummy
extends Entity {
    public static final String npcName = "PlayerDummy";

    public PlayerDummy(GamePanel gamePanel) {
        super(gamePanel);
        this.name = npcName;
        this.type = 1;
        this.getImage();
        this.setDialogue();
    }

    public void setDialogue() {
        this.dialogues[0][0] = "What... happened?";
    }

    public void getImage() {
        this.up1 = this.setup("/Player/Walking sprites/boy_up_1", this.gp.tileSize, this.gp.tileSize);
        this.up2 = this.setup("/Player/Walking sprites/boy_up_2", this.gp.tileSize, this.gp.tileSize);
        this.down1 = this.setup("/Player/Walking sprites/boy_down_1", this.gp.tileSize, this.gp.tileSize);
        this.down2 = this.setup("/Player/Walking sprites/boy_down_2", this.gp.tileSize, this.gp.tileSize);
        this.left1 = this.setup("/Player/Walking sprites/boy_left_1", this.gp.tileSize, this.gp.tileSize);
        this.left2 = this.setup("/Player/Walking sprites/boy_left_2", this.gp.tileSize, this.gp.tileSize);
        this.right1 = this.setup("/Player/Walking sprites/boy_right_1", this.gp.tileSize, this.gp.tileSize);
        this.right2 = this.setup("/Player/Walking sprites/boy_right_2", this.gp.tileSize, this.gp.tileSize);
    }
}
