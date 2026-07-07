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

    public IT_DryTree(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * col;
        this.worldY = gamePanel.tileSize * row;
        this.name = itName;
        this.down1 = this.setup("/Tiles_Interactive/drytree", gamePanel.tileSize, gamePanel.tileSize);
        this.destructible = true;
        this.life = 1;
    }

    @Override
    public boolean isCorrectItem(Entity entity) {
        boolean hasCorrectItem = false;
        if (entity.currentWeapon != null && entity.currentWeapon.type == 4) {
            hasCorrectItem = true;
        }
        return hasCorrectItem;
    }

    @Override
    public void playSE() {
        this.gp.playSE(11);
    }

    @Override
    public InteractiveTile getDestroyedForm() {
        IT_Trunk trunk = new IT_Trunk(this.gp, this.worldX / this.gp.tileSize, this.worldY / this.gp.tileSize);
        return trunk;
    }

    @Override
    public Color getParticleColor() {
        Color particleColor = new Color(65, 50, 30);
        return particleColor;
    }

    @Override
    public int getParticleSize() {
        int particleSize = 6;
        return particleSize;
    }

    @Override
    public int getParticleSpeed() {
        int particleSpeed = 1;
        return particleSpeed;
    }

    @Override
    public int getParticleMaxLife() {
        int particleMaxLife = 20;
        return particleMaxLife;
    }
}
