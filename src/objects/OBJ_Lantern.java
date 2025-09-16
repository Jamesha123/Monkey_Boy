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

public class OBJ_Lantern
extends Entity {
    public static final String objName = "Lantern";

    public OBJ_Lantern(GamePanel gamePanel) {
        super(gamePanel);
        this.type = 9;
        this.name = objName;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/lantern.png", 48, 48);
        this.description = "[" + this.name + "]\nIlluminates your \nsurroundings.";
        this.price = 1000;
        this.lightRadius = 400;
    }
}
