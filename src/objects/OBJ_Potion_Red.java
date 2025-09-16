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

public class OBJ_Potion_Red
extends Entity {
    GamePanel gp;
    public static final String objName = "Red Potion";

    public OBJ_Potion_Red(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 6;
        this.name = objName;
        this.value = 5;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/potion_red.png", 48, 48);
        this.description = "[" + this.name + "]\nHeals your health by " + this.value + ".";
        this.price = 25;
        this.stackable = true;
    }

    public boolean use(Entity entity) {
        entity.life += this.value;
        this.gp.playSE(2);
        return true;
    }
}
