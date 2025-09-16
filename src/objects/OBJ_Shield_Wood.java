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

public class OBJ_Shield_Wood
extends Entity {
    public static final String objName = "Wood Shield";

    public OBJ_Shield_Wood(GamePanel gamePanel) {
        super(gamePanel);
        this.type = 5;
        this.name = objName;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/shield_wood.png", 48, 48);
        this.defenseValue = 1;
        this.description = "[" + this.name + "]\nAn wooden shield.\nDefense " + this.defenseValue;
        this.price = 35;
    }
}
