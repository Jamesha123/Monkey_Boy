/*
 * Decompiled with CFR 0.152.
 */
package tile_interactive;

import entity.Entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import objects.OBJ_Torch;
import tile_interactive.InteractiveTile;

public class IT_Torch
extends InteractiveTile {
    public static final String itName = "Torch";
    GamePanel gp;

    public IT_Torch(GamePanel gamePanel, int col, int row, int tileType) {
        super(gamePanel, col, row);
        this.gp = gamePanel;
        this.tileType = tileType;
        if (tileType == gamePanel.playerLight) {
            this.lightRadius = 120;
            this.destructible = true;
        } else if (tileType == gamePanel.environmentLight) {
            this.lightRadius = 240;
            this.destructible = false;
        } else if (tileType == gamePanel.bossLight) {
            this.lightRadius = 500;
            this.destructible = false;
        }
        this.worldX = gamePanel.tileSize * col;
        this.worldY = gamePanel.tileSize * row;
        this.name = itName;
        this.life = 1;
        this.solidArea = new Rectangle();
        this.solidArea.x = 16;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 10;
        this.solidArea.height = 10;
        this.getImage();
    }

    @Override
    public boolean isCorrectItem(Entity entity) {
        boolean hasCorrectItem = false;
        if (entity.currentWeapon != null && (entity.currentWeapon.type == 4 || entity.currentWeapon.type == 3 || entity.currentWeapon.type == 10)) {
            hasCorrectItem = true;
        }
        return hasCorrectItem;
    }

    @Override
    public void playSE() {
        this.gp.playSE(11);
    }

    public void getImage() {
        this.up1 = this.setup("/Object/torch1", this.gp.tileSize, this.gp.tileSize);
        this.up2 = this.setup("/Object/torch2", this.gp.tileSize, this.gp.tileSize);
        this.down1 = this.setup("/Object/torch1", this.gp.tileSize, this.gp.tileSize);
        this.down2 = this.setup("/Object/torch2", this.gp.tileSize, this.gp.tileSize);
        this.left1 = this.setup("/Object/torch1", this.gp.tileSize, this.gp.tileSize);
        this.left2 = this.setup("/Object/torch2", this.gp.tileSize, this.gp.tileSize);
        this.right1 = this.setup("/Object/torch1", this.gp.tileSize, this.gp.tileSize);
        this.right2 = this.setup("/Object/torch2", this.gp.tileSize, this.gp.tileSize);
    }

    @Override
    public InteractiveTile getDestroyedForm() {
        this.gp.eManager.lighting.removeLight(this.gp.currentMap, this.worldX / this.gp.tileSize, this.worldY / this.gp.tileSize);
        this.gp.player.canObtainItem(new OBJ_Torch(this.gp));
        return null;
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

    @Override
    public void update() {
        super.update();
        ++this.spriteCounter;
        if (this.spriteCounter > 20) {
            if (this.spriteNum == 1) {
                this.spriteNum = 2;
            } else if (this.spriteNum == 2) {
                this.spriteNum = 1;
            }
            this.spriteCounter = 0;
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        int screenX = this.worldX - this.gp.player.worldX + this.gp.player.screenX;
        int screenY = this.worldY - this.gp.player.worldY + this.gp.player.screenY;
        if (this.inCamera()) {
            BufferedImage spriteImage = null;
            if (this.spriteNum == 1) {
                spriteImage = this.down1;
            } else if (this.spriteNum == 2) {
                spriteImage = this.down2;
            }
            if (spriteImage != null) {
                graphics2D.drawImage((Image)spriteImage, screenX, screenY, null);
            }
        }
    }
}
