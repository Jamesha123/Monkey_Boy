/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  entity.Entity
 *  main.GamePanel
 *  tile_interactive.IT_Torch
 */
package objects;

import entity.Entity;
import java.util.Objects;
import main.GamePanel;
import tile_interactive.IT_Torch;

public class OBJ_Torch
extends Entity {
    public static final String objName = "Torch";
    GamePanel gp;

    public OBJ_Torch(GamePanel gamePanel) {
        super(gamePanel);
        this.gp = gamePanel;
        this.type = 9;
        this.name = objName;
        Objects.requireNonNull(gamePanel);
        Objects.requireNonNull(gamePanel);
        this.down1 = this.setup("/Object/torch1.png", 48, 48);
        this.description = "[" + this.name + "]\nPlaceable light source.\nBreakable by weapons.";
        this.price = 5;
        this.placable = true;
        this.stackable = true;
    }

    public void placeItem() {
        for (int i = 0; i < this.gp.iTile[1].length; ++i) {
            if (this.gp.iTile[this.gp.currentMap][i] != null) continue;
            switch (this.gp.player.direction) {
                case "up": {
                    this.gp.iTile[this.gp.currentMap][i] = new IT_Torch(this.gp, this.gp.player.getCol(), this.gp.player.getRow() - 1, this.gp.playerLight);
                    break;
                }
                case "down": {
                    this.gp.iTile[this.gp.currentMap][i] = new IT_Torch(this.gp, this.gp.player.getCol(), this.gp.player.getRow() + 1, this.gp.playerLight);
                    break;
                }
                case "left": {
                    this.gp.iTile[this.gp.currentMap][i] = new IT_Torch(this.gp, this.gp.player.getCol() - 1, this.gp.player.getRow(), this.gp.playerLight);
                    break;
                }
                case "right": {
                    this.gp.iTile[this.gp.currentMap][i] = new IT_Torch(this.gp, this.gp.player.getCol() + 1, this.gp.player.getRow(), this.gp.playerLight);
                }
            }
            break;
        }
    }
}
