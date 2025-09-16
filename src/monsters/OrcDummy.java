/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  entity.Entity
 *  main.GamePanel
 */
package monsters;

import entity.Entity;
import java.util.Objects;
import main.GamePanel;

public class OrcDummy
extends Entity {
    public static final String npcName = "OrcDummy";
    GamePanel gp;

    public OrcDummy(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.name = npcName;
        this.type = 2;
        this.getImage();
        this.setDialogue();
    }

    public void getImage() {
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up1 = this.setup("/Monster/orc_up_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.up2 = this.setup("/Monster/orc_up_2.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down1 = this.setup("/Monster/orc_down_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.down2 = this.setup("/Monster/orc_down_2.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left1 = this.setup("/Monster/orc_left_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.left2 = this.setup("/Monster/orc_left_2.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right1 = this.setup("/Monster/orc_right_1.png", 48, 48);
        Objects.requireNonNull(this.gp);
        Objects.requireNonNull(this.gp);
        this.right2 = this.setup("/Monster/orc_right_2.png", 48, 48);
    }

    public void setDialogue() {
        this.dialogues[0][0] = "ME WANT YOU SUGAR!";
        this.dialogues[0][1] = "WE TAKE SUGAR NOW!!!";
    }
}
