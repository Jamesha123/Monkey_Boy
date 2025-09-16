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

public class OBJ_Coin_Bronze
extends Entity {
    GamePanel gp;
    public static final String objName = "Bronze Coin";

    public OBJ_Coin_Bronze(GamePanel gamePanel) {
        super(gamePanel);
        int n;
        this.gp = gamePanel;
        this.type = 7;
        this.name = objName;
        this.value = n = new Random().nextInt(10) + 1;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/coin_bronze.png", 48, 48);
    }

    public boolean use(Entity entity) {
        this.gp.playSE(1);
        this.gp.ui.addMessage("Coin +" + this.value);
        this.gp.player.coin += this.value;
        return true;
    }
}
