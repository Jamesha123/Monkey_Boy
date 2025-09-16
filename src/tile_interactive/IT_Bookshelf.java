/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import main.GamePanel;
import tile_interactive.InteractiveTile;

public class IT_Bookshelf
extends InteractiveTile {
    GamePanel gp;
    public static final String itName = "Bookself";

    public IT_Bookshelf(GamePanel gamePanel, int n, int n2) {
        super(gamePanel, n, n2);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * n;
        this.worldY = gamePanel.tileSize * n2 - 24;
        this.name = itName;
        this.down1 = this.setup("/Tiles_Interactive/bookshelf", gamePanel.tileSize * 2, gamePanel.tileSize * 3);
        this.solidArea.x = 8;
        this.solidArea.y = 0;
        this.solidArea.width = 80;
        this.solidArea.height = 100;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }
}
