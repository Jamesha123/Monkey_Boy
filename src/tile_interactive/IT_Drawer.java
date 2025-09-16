/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import main.GamePanel;
import tile_interactive.InteractiveTile;

public class IT_Drawer
extends InteractiveTile {
    GamePanel gp;
    public static final String itName = "Drawer";

    public IT_Drawer(GamePanel gamePanel, int n, int n2) {
        super(gamePanel, n, n2);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * n;
        this.worldY = gamePanel.tileSize * n2;
        this.name = itName;
        this.down1 = this.setup("/Tiles_Interactive/drawer", gamePanel.tileSize, gamePanel.tileSize * 2);
        this.solidArea.x = 1;
        this.solidArea.y = 1;
        this.solidArea.width = 40;
        this.solidArea.height = 70;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.collision = true;
    }
}
