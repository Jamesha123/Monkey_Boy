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

public class OBJ_Boots
extends Entity {
    public static final String objName = "Boots";
    GamePanel gp;

    public OBJ_Boots(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 7;
        this.name = objName;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/boots.png", 48, 48);
    }

    public boolean use(Entity entity) {
        this.gp.playSE(2);
        this.gp.ui.addMessage("Boots: speed up!");
        this.gp.player.speed = 6;
        this.gp.player.speedBoostActive = true;
        this.gp.player.speedBoostCounter = 0;
        return true;
    }
}
