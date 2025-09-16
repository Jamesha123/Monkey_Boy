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

public class OBJ_Heart
extends Entity {
    GamePanel gp;
    public static final String objName = "Heart";

    public OBJ_Heart(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 7;
        this.name = objName;
        this.value = 2;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/heart_full.png", 48, 48);
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.image = this.setup("/Object/heart_full.png", 48, 48);
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.image2 = this.setup("/Object/heart_half.png", 48, 48);
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.image3 = this.setup("/Object/heart_blank.png", 48, 48);
    }

    public boolean use(Entity entity) {
        this.gp.playSE(2);
        this.gp.ui.addMessage("Life +" + this.value);
        entity.life += this.value;
        return true;
    }
}
