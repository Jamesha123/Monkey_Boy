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

public class OBJ_Sword_Epic
extends Entity {
    public static final String objName = "Epic Sword";

    public OBJ_Sword_Epic(GamePanel gamePanel) {
        super(gamePanel);
        this.type = 3;
        this.name = objName;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/sword_epic.png", 48, 48);
        this.attackValue = 10;
        this.attackArea.width = 48;
        this.attackArea.height = 48;
        this.description = "[" + this.name + "]\nA legendary blade.\nAttack " + this.attackValue;
        this.price = 500;
        this.knockBackPower = 5;
        this.motion1_duration = 10;
        this.motion2_duration = 25;
    }
}
