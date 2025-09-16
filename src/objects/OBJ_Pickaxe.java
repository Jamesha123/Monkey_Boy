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

public class OBJ_Pickaxe
extends Entity {
    public static final String objName = "Pickaxe";

    public OBJ_Pickaxe(GamePanel gamePanel) {
        super(gamePanel);
        this.type = 10;
        this.name = objName;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/pickaxe.png", 48, 48);
        this.attackValue = 2;
        this.attackArea.width = 25;
        this.attackArea.height = 28;
        this.description = "[" + this.name + "]\nCan break cobblestone.\nAttack " + this.attackValue;
        this.price = 75;
        this.knockBackPower = 5;
        this.motion1_duration = 10;
        this.motion2_duration = 30;
    }
}
