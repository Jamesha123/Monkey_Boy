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

public class OBJ_Sword_Normal
extends Entity {
    public static final String objName = "Normal Sword";

    public OBJ_Sword_Normal(GamePanel gamePanel) {
        super(gamePanel);
        this.type = 3;
        this.name = objName;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/sword_normal.png", 48, 48);
        this.attackValue = 1;
        this.attackArea.width = 36;
        this.attackArea.height = 36;
        this.description = "[" + this.name + "]\nAn old sword.\nAttack " + this.attackValue;
        this.price = 20;
        this.knockBackPower = 3;
        this.motion1_duration = 5;
        this.motion2_duration = 25;
    }
}
