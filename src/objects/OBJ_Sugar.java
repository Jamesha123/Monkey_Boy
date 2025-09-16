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

public class OBJ_Sugar
extends Entity {
    public static final String objName = "Sugar";

    public OBJ_Sugar(GamePanel gamePanel) {
        super(gamePanel);
        this.type = 7;
        this.name = objName;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/sugar.png", 48, 48);
        this.description = "[" + this.name + "]\nLast bag of sugar for \nthe season.";
        this.price = 25;
        this.stackable = true;
    }
}
