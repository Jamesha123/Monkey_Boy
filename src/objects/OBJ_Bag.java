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
import java.util.Random;
import main.GamePanel;

public class OBJ_Bag
extends Entity {
    GamePanel gp;
    public static final String objName = "Bag";
    private int random = 0;

    public OBJ_Bag(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 6;
        this.name = objName;
        while (this.random < 30) {
            this.random = new Random().nextInt(100) + 1;
        }
        this.value = this.random;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/bag.png", 48, 48);
        this.description = "[" + this.name + "]\nContains " + this.value + " coins.";
        this.setDialogue();
    }

    public void setDialogue() {
        this.dialogues[0][0] = "You found " + this.value + " coins!";
    }

    public boolean use(Entity entity) {
        this.gp.playSE(1);
        this.startDialogue(this, 0);
        this.gp.player.coin += this.value;
        return true;
    }
}
