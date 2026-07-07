/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import entity.Entity;
import java.awt.Color;
import main.GamePanel;
import tile_interactive.InteractiveTile;

public class IT_DestructibleWall
extends InteractiveTile {
    public static final String itName = "Destructible Wall";
    GamePanel gp;

    public IT_DestructibleWall(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * col;
        this.worldY = gamePanel.tileSize * row;
        this.name = itName;
        this.down1 = this.setup("/Tiles_Interactive/destructiblewall", gamePanel.tileSize, gamePanel.tileSize);
        this.destructible = true;
        this.life = 3;
    }

    @Override
    public boolean isCorrectItem(Entity entity) {
        boolean hasCorrectItem = false;
        if (entity.currentWeapon != null && entity.currentWeapon.type == 10) {
            hasCorrectItem = true;
        }
        return hasCorrectItem;
    }

    @Override
    public void playSE() {
        this.gp.playSE(20);
    }

    @Override
    public InteractiveTile getDestroyedForm() {
        InteractiveTile destroyedForm = null;
        return destroyedForm;
    }

    @Override
    public Color getParticleColor() {
        Color particleColor = new Color(65, 65, 65);
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
