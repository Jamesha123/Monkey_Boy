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

    public IT_DestructibleWall(GamePanel gamePanel, int n, int n2) {
        super(gamePanel, n, n2);
        this.gp = gamePanel;
        this.worldX = gamePanel.tileSize * n;
        this.worldY = gamePanel.tileSize * n2;
        this.name = itName;
        this.down1 = this.setup("/Tiles_Interactive/destructiblewall", gamePanel.tileSize, gamePanel.tileSize);
        this.destructible = true;
        this.life = 3;
    }

    @Override
    public boolean isCorrectItem(Entity entity) {
        boolean bl = false;
        if (entity.currentWeapon != null && entity.currentWeapon.type == 10) {
            bl = true;
        }
        return bl;
    }

    @Override
    public void playSE() {
        this.gp.playSE(20);
    }

    @Override
    public InteractiveTile getDestroyedForm() {
        InteractiveTile interactiveTile = null;
        return interactiveTile;
    }

    @Override
    public Color getParticleColor() {
        Color color = new Color(65, 65, 65);
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
