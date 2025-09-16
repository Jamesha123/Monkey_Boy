/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import entity.Entity;
import java.awt.Color;
import main.GamePanel;
import tile_interactive.IT_Trunk;
import tile_interactive.InteractiveTile;

public class IT_DryTree
extends InteractiveTile {
    GamePanel gp;
    public static final String itName = "DryTree";

    public IT_DryTree(GamePanel gamePanel, int n, int n2) {
        super(gamePanel, n, n2);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * n;
        this.worldY = gamePanel.tileSize * n2;
        this.name = itName;
        this.down1 = this.setup("/Tiles_Interactive/drytree", gamePanel.tileSize, gamePanel.tileSize);
        this.destructible = true;
        this.life = 1;
    }

    @Override
    public boolean isCorrectItem(Entity entity) {
        boolean bl = false;
        if (entity.currentWeapon != null && entity.currentWeapon.type == 4) {
            bl = true;
        }
        return bl;
    }

    @Override
    public void playSE() {
        this.gp.playSE(11);
    }

    @Override
    public InteractiveTile getDestroyedForm() {
        IT_Trunk iT_Trunk = new IT_Trunk(this.gp, this.worldX / this.gp.tileSize, this.worldY / this.gp.tileSize);
        return iT_Trunk;
    }

    @Override
    public Color getParticleColor() {
        Color color = new Color(65, 50, 30);
        return color;
    }

    @Override
    public int getParticleSize() {
        int n = 6;
        return n;
    }

    @Override
    public int getParticleSpeed() {
        int n = 1;
        return n;
    }

    @Override
    public int getParticleMaxLife() {
        int n = 20;
        return n;
    }
}
