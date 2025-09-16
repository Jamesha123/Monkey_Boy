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

public class OBJ_Axe
extends Entity {
    public static final String objName = "Woodcutter's Axe";

    public OBJ_Axe(GamePanel gamePanel) {
        super(gamePanel);
        this.type = 4;
        this.name = objName;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/axe.png", 48, 48);
        this.attackValue = 2;
        this.attackArea.width = 25;
        this.attackArea.height = 28;
        this.description = "[" + this.name + "]\nCan chop wood.\nAttack " + this.attackValue;
        this.price = 75;
        this.knockBackPower = 10;
        this.motion1_duration = 20;
        this.motion2_duration = 40;
    }
}
