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

    public IT_Torch(GamePanel gamePanel, int n, int n2, int n3) {
        super(gamePanel, n, n2);
        this.gp = gamePanel;
        this.tileType = n3;
        if (n3 == gamePanel.playerLight) {
            this.lightRadius = 120;
            this.destructible = true;
        } else if (n3 == gamePanel.environmentLight) {
            this.lightRadius = 240;
            this.destructible = false;
        } else if (n3 == gamePanel.bossLight) {
            this.lightRadius = 500;
            this.destructible = false;
        }
        this.worldX = gamePanel.tileSize * n;
        this.worldY = gamePanel.tileSize * n2;
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
        boolean bl = false;
        if (entity.currentWeapon != null && (entity.currentWeapon.type == 4 || entity.currentWeapon.type == 3 || entity.currentWeapon.type == 10)) {
            bl = true;
        }
        return bl;
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
        int n = this.worldX - this.gp.player.worldX + this.gp.player.screenX;
        int n2 = this.worldY - this.gp.player.worldY + this.gp.player.screenY;
        if (this.inCamera()) {
            BufferedImage bufferedImage = null;
            if (this.spriteNum == 1) {
                bufferedImage = this.down1;
            } else if (this.spriteNum == 2) {
                bufferedImage = this.down2;
            }
            if (bufferedImage != null) {
                graphics2D.drawImage((Image)bufferedImage, n, n2, null);
            }
        }
    }
}
