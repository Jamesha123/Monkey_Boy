/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.Particle;
import entity.Projectile;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import main.GamePanel;
import main.ImageCache;

public class Entity {
    GamePanel gp;
    public BufferedImage up1;
    public BufferedImage up2;
    public BufferedImage down1;
    public BufferedImage down2;
    public BufferedImage left1;
    public BufferedImage left2;
    public BufferedImage left3;
    public BufferedImage right1;
    public BufferedImage right2;
    public BufferedImage right3;
    public BufferedImage attackUp1;
    public BufferedImage attackUp2;
    public BufferedImage attackDown1;
    public BufferedImage attackDown2;
    public BufferedImage attackLeft1;
    public BufferedImage attackLeft2;
    public BufferedImage attackRight1;
    public BufferedImage attackRight2;
    public BufferedImage guardUp;
    public BufferedImage guardDown;
    public BufferedImage guardLeft;
    public BufferedImage guardRight;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public String[][] dialogues = new String[20][50];
    public BufferedImage image;
    public BufferedImage image2;
    public BufferedImage image3;
    public boolean collision = false;
    public Entity attacker;
    public Entity linkedEntity;
    public boolean temp = false;
    public int worldX;
    public int worldY;
    public String direction = "down";
    public int spriteNum = 1;
    public int dialogueSet = 0;
    public int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean following = false;
    public boolean knockBack = false;
    public String knockBackDirection;
    public boolean guarding = false;
    public boolean transparent = false;
    public boolean offBalance = false;
    public Entity loot;
    public boolean opened = false;
    public boolean canContactDamage = true;
    public boolean inRage = false;
    public boolean sleep = false;
    public boolean drawing = true;
    public boolean placable = false;
    public boolean inInvinciblePhase = false;
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int compositeCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    public int hpBarCounter = 0;
    int knockBackCounter = 0;
    public int guardCounter = 0;
    int offBalanceCounter = 0;
    public int orcCounter = 0;
    public String name;
    public int defaultSpeed;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int rate;
    public int motion1_duration;
    public int motion2_duration;
    public int walkAnimationSpeed = 24;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentLight;
    public Projectile projectile;
    public Projectile projectile1;
    public Projectile projectile2;
    public Projectile projectile3;
    public Projectile projectile4;
    public boolean boss;
    public ArrayList<Entity> inventory = new ArrayList();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int price;
    public int knockBackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;
    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickup = 7;
    public final int type_obstacle = 8;
    public final int type_light = 9;
    public final int type_pickaxe = 10;
    public final int type_spell = 11;

    public Entity(GamePanel gamePanel) {
        this.gp = gamePanel;
    }

    public void update() {
        if (this.gp.entitiesFrozen && this.type != 0) {
            return;
        }
        if (!this.sleep) {
            if (this.knockBack) {
                this.checkCollision();
                if (this.collisionOn) {
                    this.knockBackCounter = 0;
                    this.knockBack = false;
                    this.speed = this.defaultSpeed;
                } else if (!this.collisionOn) {
                    switch (this.knockBackDirection) {
                        case "up": {
                            this.worldY -= this.speed;
                            break;
                        }
                        case "down": {
                            this.worldY += this.speed;
                            break;
                        }
                        case "left": {
                            this.worldX -= this.speed;
                            break;
                        }
                        case "right": {
                            this.worldX += this.speed;
                        }
                    }
                }
                ++this.knockBackCounter;
                if (this.knockBackCounter == 5) {
                    this.knockBackCounter = 0;
                    this.knockBack = false;
                    this.speed = this.defaultSpeed;
                }
            } else if (this.attacking) {
                this.attacking();
            } else {
                this.setAction();
                this.checkCollision();
                if (!this.collisionOn) {
                    switch (this.direction) {
                        case "up": {
                            this.worldY -= this.speed;
                            break;
                        }
                        case "down": {
                            this.worldY += this.speed;
                            break;
                        }
                        case "left": {
                            this.worldX -= this.speed;
                            break;
                        }
                        case "right": {
                            this.worldX += this.speed;
                        }
                    }
                }
                ++this.spriteCounter;
                if (this.spriteCounter > this.walkAnimationSpeed) {
                    if (this.spriteNum == 1) {
                        this.spriteNum = 2;
                    } else if (this.spriteNum == 2) {
                        this.spriteNum = 1;
                    }
                    this.spriteCounter = 0;
                }
            }
            if (this.invincible) {
                ++this.invincibleCounter;
                if (this.invincibleCounter > 20) {
                    this.invincible = false;
                    this.invincibleCounter = 0;
                }
            }
            if (this.shotAvailableCounter < 30) {
                ++this.shotAvailableCounter;
            }
            if (this.offBalance) {
                ++this.offBalanceCounter;
                if (this.offBalanceCounter > 60) {
                    this.offBalance = false;
                    this.offBalanceCounter = 0;
                }
            }
        }
    }

    public int getScreenX() {
        int n = this.worldX - this.gp.player.worldX + this.gp.player.screenX;
        return n;
    }

    public int getScreenY() {
        int n = this.worldY - this.gp.player.worldY + this.gp.player.screenY;
        return n;
    }

    public int getLeftX() {
        return this.worldX + this.solidArea.x;
    }

    public int getRightX() {
        return this.worldX + this.solidArea.x + this.solidArea.width;
    }

    public int getTopY() {
        return this.worldY + this.solidArea.y;
    }

    public int getBottomY() {
        return this.worldY + this.solidArea.y + this.solidArea.height;
    }

    public int getCol() {
        return (this.worldX + this.solidArea.x) / this.gp.tileSize;
    }

    public int getRow() {
        return (this.worldY + this.solidArea.y) / this.gp.tileSize;
    }

    public int getCenterX() {
        int n = this.worldX + this.left1.getWidth() / 2;
        return n;
    }

    public int getCenterY() {
        int n = this.worldY + this.up1.getHeight() / 2;
        return n;
    }

    public int getXDistance(Entity entity) {
        int n = Math.abs(this.getCenterX() - entity.getCenterX());
        return n;
    }

    public int getYDistance(Entity entity) {
        int n = Math.abs(this.getCenterY() - entity.getCenterY());
        return n;
    }

    public int getTileDistance(Entity entity) {
        int n = (this.getXDistance(entity) + this.getYDistance(entity)) / this.gp.tileSize;
        return n;
    }

    public int getGoalCol(Entity entity) {
        int n = (entity.worldX + entity.solidArea.x) / this.gp.tileSize;
        return n;
    }

    public int getGoalRow(Entity entity) {
        int n = (entity.worldY + entity.solidArea.y) / this.gp.tileSize;
        return n;
    }

    public void resetCounter() {
        this.spriteCounter = 0;
        this.actionLockCounter = 0;
        this.invincibleCounter = 0;
        this.compositeCounter = 0;
        this.shotAvailableCounter = 0;
        this.dyingCounter = 0;
        this.hpBarCounter = 0;
        this.knockBackCounter = 0;
        this.guardCounter = 0;
        this.offBalanceCounter = 0;
    }

    public void setLoot(Entity entity) {
    }

    public void setAction() {
    }

    public void move(String string) {
    }

    public void damageReaction() {
    }

    public void placeItem() {
    }

    public void speak() {
    }

    public void interact() {
    }

    public void pickUpObjectDialogue() {
    }

    public boolean use(Entity entity) {
        return false;
    }

    public void checkDrop() {
    }

    public void facePlayer() {
        switch (this.gp.player.direction) {
            case "up": {
                this.direction = "down";
                break;
            }
            case "down": {
                this.direction = "up";
                break;
            }
            case "left": {
                this.direction = "right";
                break;
            }
            case "right": {
                this.direction = "left";
            }
        }
    }

    public void startDialogue(Entity entity, int n) {
        Objects.requireNonNull(this.gp);
        this.gp.gameState = 2;
        this.gp.ui.npc = entity;
        this.dialogueSet = n;
    }

    public void dropItem(Entity entity) {
        for (int i = 0; i < this.gp.obj[1].length; ++i) {
            if (this.gp.obj[this.gp.currentMap][i] != null) continue;
            this.worldX = this.worldX + this.solidArea.x + this.solidArea.width / 4;
            this.worldY = this.worldY + this.solidArea.y + this.solidArea.height / 4;
            this.gp.obj[this.gp.currentMap][i] = entity;
            this.gp.obj[this.gp.currentMap][i].worldX = this.worldX;
            this.gp.obj[this.gp.currentMap][i].worldY = this.worldY;
            break;
        }
    }

    public Color getParticleColor() {
        Color color = null;
        return color;
    }

    public int getParticleSize() {
        int n = 0;
        return n;
    }

    public int getParticleSpeed() {
        int n = 0;
        return n;
    }

    public int getParticleMaxLife() {
        int n = 0;
        return n;
    }

    public void generateParticle(Entity entity, Entity entity2) {
        Color color = entity.getParticleColor();
        int n = entity.getParticleSize();
        int n2 = entity.getParticleSpeed();
        int n3 = entity.getParticleMaxLife();
        Particle particle = new Particle(this.gp, entity2, color, n, n2, n3, -2, -1);
        Particle particle2 = new Particle(this.gp, entity2, color, n, n2, n3, 2, -1);
        Particle particle3 = new Particle(this.gp, entity2, color, n, n2, n3, -2, 1);
        Particle particle4 = new Particle(this.gp, entity2, color, n, n2, n3, 2, 1);
        this.gp.particleList.add(particle);
        this.gp.particleList.add(particle2);
        this.gp.particleList.add(particle3);
        this.gp.particleList.add(particle4);
    }

    public void checkCollision() {
        this.collisionOn = false;
        this.gp.cChecker.checkTile(this);
        this.gp.cChecker.checkObject(this, false);
        this.gp.cChecker.checkEntity(this, this.gp.npc);
        this.gp.cChecker.checkEntity(this, this.gp.monster);
        this.gp.cChecker.checkEntity(this, this.gp.iTile);
        boolean bl = this.gp.cChecker.checkPlayer(this);
        if (this.type == 2 && bl) {
            this.damagePlayer(this.attack);
        }
    }

    public void checkMonsterAttack(int n, int n2, int n3) {
        int n4;
        boolean bl = false;
        int n5 = this.getXDistance(this.gp.player);
        int n6 = this.getYDistance(this.gp.player);
        switch (this.direction) {
            case "up": {
                if (this.gp.player.getCenterY() >= this.getCenterY() || n6 >= n2 || n5 >= n3) break;
                bl = true;
                break;
            }
            case "down": {
                if (this.gp.player.getCenterY() <= this.worldY || n6 >= n2 || n5 >= n3) break;
                bl = true;
                break;
            }
            case "left": {
                if (this.gp.player.getCenterX() >= this.getCenterY() || n6 >= n2 || n5 >= n3) break;
                bl = true;
                break;
            }
            case "right": {
                if (this.gp.player.getCenterX() <= this.getCenterX() || n6 >= n2 || n5 >= n3) break;
                bl = true;
            }
        }
        if (bl && (n4 = new Random().nextInt(n)) == 0) {
            this.attacking = true;
            this.spriteNum = 1;
            this.spriteCounter = 0;
            this.shotAvailableCounter = 0;
        }
    }

    public void checkMonsterShoot(int n, int n2) {
        int n3 = new Random().nextInt(n);
        if (n3 == 0 && !this.projectile.alive && this.shotAvailableCounter == n2) {
            this.projectile.set(this.worldX, this.worldY, this.direction, true, this);
            for (int i = 0; i < this.gp.projectile[1].length; ++i) {
                if (this.gp.projectile[this.gp.currentMap][i] != null) continue;
                this.gp.projectile[this.gp.currentMap][i] = this.projectile;
                break;
            }
            this.shotAvailableCounter = 0;
        }
    }

    public void checkStartChasing(Entity entity, int n, int n2) {
        int n3;
        if (this.getTileDistance(entity) < n && (n3 = new Random().nextInt(n2)) == 0) {
            this.onPath = true;
        }
    }

    public void checkStopChasing(Entity entity, int n, int n2) {
        int n3;
        if (this.getTileDistance(entity) > n && (n3 = new Random().nextInt(n2)) == 0) {
            this.onPath = false;
        }
    }

    public void getRandomDirection(int n) {
        ++this.actionLockCounter;
        if (this.actionLockCounter > n) {
            Random random = new Random();
            int n2 = random.nextInt(100) + 1;
            if (n2 <= 25) {
                this.direction = "up";
            } else if (n2 > 25 && n2 <= 50) {
                this.direction = "down";
            } else if (n2 > 50 && n2 <= 75) {
                this.direction = "left";
            } else if (n2 > 75 && n2 <= 100) {
                this.direction = "right";
            }
            this.actionLockCounter = 0;
        }
    }

    public void moveTowardPlayer(int n) {
        ++this.actionLockCounter;
        if (this.actionLockCounter > n) {
            if (this.getXDistance(this.gp.player) > this.getYDistance(this.gp.player)) {
                this.direction = this.gp.player.getCenterX() < this.getCenterX() ? "left" : "right";
            } else if (this.getXDistance(this.gp.player) < this.getYDistance(this.gp.player)) {
                this.direction = this.gp.player.getCenterY() < this.getCenterY() ? "up" : "down";
            }
            this.actionLockCounter = 0;
        }
    }

    public String getOppositeDirection(String string) {
        String string2 = "";
        switch (string) {
            case "up": {
                string2 = "down";
                break;
            }
            case "down": {
                string2 = "up";
                break;
            }
            case "left": {
                string2 = "right";
                break;
            }
            case "right": {
                string2 = "left";
            }
        }
        return string2;
    }

    public void attacking() {
        ++this.spriteCounter;
        if (this.spriteCounter <= this.motion1_duration) {
            this.spriteNum = 1;
        } else if (this.spriteCounter > this.motion1_duration && this.spriteCounter <= this.motion2_duration) {
            this.spriteNum = 2;
            int n = this.worldX;
            int n2 = this.worldY;
            int n3 = this.solidArea.width;
            int n4 = this.solidArea.height;
            switch (this.direction) {
                case "up": {
                    this.worldY -= this.attackArea.height;
                    break;
                }
                case "down": {
                    this.worldY += this.gp.tileSize;
                    break;
                }
                case "left": {
                    this.worldX -= this.attackArea.width;
                    break;
                }
                case "right": {
                    this.worldX += this.gp.tileSize;
                }
            }
            this.solidArea.width = this.attackArea.width;
            this.solidArea.height = this.attackArea.height;
            if (this.type == 2) {
                if (this.gp.cChecker.checkPlayer(this)) {
                    this.damagePlayer(this.attack);
                }
            } else {
                int n5 = this.gp.cChecker.checkEntity(this, this.gp.monster);
                int n6 = this.currentWeapon != null ? this.currentWeapon.knockBackPower : 1;
                this.gp.player.damageMonster(n5, this, this.attack, n6, this.direction);
                int n7 = this.gp.cChecker.checkInteractiveTile(this);
                this.gp.player.damageInteractiveTile(n7);
                int n8 = this.gp.cChecker.checkEntity(this, this.gp.projectile);
                this.gp.player.damageProjectile(n8);
            }
            this.worldX = n;
            this.worldY = n2;
            this.solidArea.width = n3;
            this.solidArea.height = n4;
        } else if (this.spriteCounter > this.motion2_duration) {
            this.spriteNum = 1;
            this.spriteCounter = 0;
            this.attacking = false;
        }
    }

    public void damagePlayer(int n) {
        if (!this.gp.player.invincible) {
            String string = this.getOppositeDirection(this.direction);
            int n2 = n - this.gp.player.defense;
            if (this.gp.player.guarding && this.gp.player.direction.equals(string)) {
                if (this.gp.player.guardCounter < 30) {
                    n2 = 0;
                    this.gp.playSE(16);
                    this.setKnockBack(this, this.gp.player, this.knockBackPower, this.gp.player.direction);
                    this.offBalance = true;
                    this.spriteCounter = -60;
                } else {
                    n2 /= 3;
                    this.gp.playSE(15);
                }
            } else {
                this.gp.playSE(6);
                if (n2 < 1) {
                    n2 = 1;
                }
            }
            if (n2 != 0) {
                this.gp.player.transparent = true;
                this.setKnockBack(this.gp.player, this, this.knockBackPower, this.direction);
            }
            this.gp.player.life -= n2;
            this.gp.player.invincible = true;
        }
    }

    public void setKnockBack(Entity entity, Entity entity2, int n, String string) {
        this.attacker = entity2;
        entity.knockBackDirection = entity2.direction;
        entity.speed += n;
        entity.knockBack = true;
    }

    public boolean inCamera() {
        int n;
        int n2;
        boolean bl;
        block5: {
            block4: {
                bl = false;
                n2 = 5;
                n = 1;
                int n3 = this.gp.currentMap;
                Objects.requireNonNull(this.gp);
                if (n3 == 5) break block4;
                int n4 = this.gp.currentMap;
                Objects.requireNonNull(this.gp);
                if (n4 == 1) break block4;
                int n5 = this.gp.currentMap;
                Objects.requireNonNull(this.gp);
                if (n5 != 2) break block5;
            }
            n2 = 12;
            n = 5;
        }
        if (this.worldX + this.gp.tileSize * n2 > this.gp.player.worldX - this.gp.player.screenX && this.worldX - this.gp.tileSize * n < this.gp.player.worldX + this.gp.player.screenX && this.worldY + this.gp.tileSize * n2 > this.gp.player.worldY - this.gp.player.screenY && this.worldY - this.gp.tileSize * n < this.gp.player.worldY + this.gp.player.screenY) {
            bl = true;
        }
        return bl;
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage bufferedImage = null;
        if (this.inCamera()) {
            int n = this.getScreenX();
            int n2 = this.getScreenY();
            switch (this.direction) {
                case "up": {
                    if (!this.attacking) {
                        if (this.spriteNum == 1) {
                            bufferedImage = this.up1;
                            break;
                        }
                        if (this.spriteNum != 2) break;
                        bufferedImage = this.up2;
                        break;
                    }
                    if (!this.attacking) break;
                    n2 = this.getScreenY() - this.up1.getHeight();
                    if (this.spriteNum == 1) {
                        bufferedImage = this.attackUp1;
                        break;
                    }
                    if (this.spriteNum != 2) break;
                    bufferedImage = this.attackUp2;
                    break;
                }
                case "down": {
                    if (!this.attacking) {
                        if (this.spriteNum == 1) {
                            bufferedImage = this.down1;
                            break;
                        }
                        if (this.spriteNum != 2) break;
                        bufferedImage = this.down2;
                        break;
                    }
                    if (!this.attacking) break;
                    if (this.spriteNum == 1) {
                        bufferedImage = this.attackDown1;
                        break;
                    }
                    if (this.spriteNum != 2) break;
                    bufferedImage = this.attackDown2;
                    break;
                }
                case "left": {
                    if (!this.attacking) {
                        if (this.spriteNum == 1) {
                            bufferedImage = this.left1;
                            break;
                        }
                        if (this.spriteNum != 2) break;
                        bufferedImage = this.left2;
                        break;
                    }
                    if (!this.attacking) break;
                    n = this.getScreenX() - this.left1.getWidth();
                    if (this.spriteNum == 1) {
                        bufferedImage = this.attackLeft1;
                        break;
                    }
                    if (this.spriteNum != 2) break;
                    bufferedImage = this.attackLeft2;
                    break;
                }
                case "right": {
                    if (!this.attacking) {
                        if (this.spriteNum == 1) {
                            bufferedImage = this.right1;
                            break;
                        }
                        if (this.spriteNum != 2) break;
                        bufferedImage = this.right2;
                        break;
                    }
                    if (!this.attacking) break;
                    if (this.spriteNum == 1) {
                        bufferedImage = this.attackRight1;
                        break;
                    }
                    if (this.spriteNum != 2) break;
                    bufferedImage = this.attackRight2;
                }
            }
            if (this.invincible && !this.sleep) {
                this.hpBarOn = true;
                this.hpBarCounter = 0;
                this.changeAlpha(graphics2D, 0.4f);
                ++this.compositeCounter;
                if (this.compositeCounter > 20) {
                    this.changeAlpha(graphics2D, 0.8f);
                    this.compositeCounter = 0;
                }
            }
            if (this.dying) {
                this.dyingAnimation(graphics2D);
            }
            graphics2D.drawImage((Image)bufferedImage, n, n2, null);
            this.changeAlpha(graphics2D, 1.0f);
        }
    }

    public void dyingAnimation(Graphics2D graphics2D) {
        ++this.dyingCounter;
        int n = 5;
        if (this.dyingCounter <= n) {
            this.changeAlpha(graphics2D, 0.0f);
        } else if (this.dyingCounter > n && this.dyingCounter <= n * 2) {
            this.changeAlpha(graphics2D, 1.0f);
        } else if (this.dyingCounter > n * 2 && this.dyingCounter <= n * 3) {
            this.changeAlpha(graphics2D, 0.0f);
        } else if (this.dyingCounter > n * 3 && this.dyingCounter <= n * 4) {
            this.changeAlpha(graphics2D, 1.0f);
        } else if (this.dyingCounter > n * 4 && this.dyingCounter <= n * 5) {
            this.changeAlpha(graphics2D, 0.0f);
        } else if (this.dyingCounter > n * 5 && this.dyingCounter <= n * 6) {
            this.changeAlpha(graphics2D, 1.0f);
        } else if (this.dyingCounter > n * 6 && this.dyingCounter <= n * 7) {
            this.changeAlpha(graphics2D, 0.0f);
        } else if (this.dyingCounter > n * 7 && this.dyingCounter <= n * 8) {
            this.changeAlpha(graphics2D, 1.0f);
        } else if (this.dyingCounter > n * 8) {
            this.alive = false;
        }
    }

    public void changeAlpha(Graphics2D graphics2D, float f) {
        graphics2D.setComposite(AlphaComposite.getInstance(3, f));
    }

    public BufferedImage setup(String string, int n, int n2) {
        return ImageCache.getImage(string, n, n2);
    }

    public void searchPath(int n, int n2) {
        int n3 = (this.worldX + this.solidArea.x) / this.gp.tileSize;
        int n4 = (this.worldY + this.solidArea.y) / this.gp.tileSize;
        this.gp.pFinder.setNodes(n3, n4, n, n2, this);
        if (this.gp.pFinder.search()) {
            int n5 = this.gp.pFinder.pathList.get((int)0).col * this.gp.tileSize;
            int n6 = this.gp.pFinder.pathList.get((int)0).row * this.gp.tileSize;
            int n7 = this.worldX + this.solidArea.x;
            int n8 = this.worldX + this.solidArea.x + this.solidArea.width;
            int n9 = this.worldY + this.solidArea.y;
            int n10 = this.worldY + this.solidArea.y + this.solidArea.height;
            if (n9 > n6 && n7 >= n5 && n8 < n5 + this.gp.tileSize) {
                this.direction = "up";
            } else if (n9 < n6 && n7 >= n5 && n8 < n5 + this.gp.tileSize) {
                this.direction = "down";
            } else if (n9 >= n6 && n10 < n6 + this.gp.tileSize) {
                if (n7 > n5) {
                    this.direction = "left";
                }
                if (n7 < n5) {
                    this.direction = "right";
                }
            } else if (n9 > n6 && n7 > n5) {
                this.direction = "up";
                this.checkCollision();
                if (this.collisionOn) {
                    this.direction = "left";
                }
            } else if (n9 > n6 && n7 < n5) {
                this.direction = "up";
                this.checkCollision();
                if (this.collisionOn) {
                    this.direction = "right";
                }
            } else if (n9 < n6 && n7 > n5) {
                this.direction = "down";
                this.checkCollision();
                if (this.collisionOn) {
                    this.direction = "left";
                }
            } else if (n9 < n6 && n7 < n5) {
                this.direction = "down";
                this.checkCollision();
                if (this.collisionOn) {
                    this.direction = "right";
                }
            }
            if (!this.following) {
                int n11 = this.gp.pFinder.pathList.get((int)0).col;
                int n12 = this.gp.pFinder.pathList.get((int)0).row;
                if (n11 == n && n12 == n2) {
                    this.onPath = false;
                }
            }
        }
    }

    public int getDetected(Entity entity, Entity[][] entityArray, String string) {
        int n = 999;
        int n2 = entity.getLeftX();
        int n3 = entity.getTopY();
        switch (entity.direction) {
            case "up": {
                n3 = entity.getTopY() - this.gp.player.speed;
                break;
            }
            case "down": {
                n3 = entity.getBottomY() + this.gp.player.speed;
                break;
            }
            case "left": {
                n2 = entity.getLeftX() - this.gp.player.speed;
                break;
            }
            case "right": {
                n2 = entity.getRightX() + this.gp.player.speed;
            }
        }
        int n4 = n2 / this.gp.tileSize;
        int n5 = n3 / this.gp.tileSize;
        for (int i = 0; i < entityArray[1].length; ++i) {
            if (entityArray[this.gp.currentMap][i] == null || !entityArray[this.gp.currentMap][i].name.equals(string)) continue;
            int n6 = entityArray[this.gp.currentMap][i].getCol();
            int n7 = entityArray[this.gp.currentMap][i].getRow();
            int n8 = entityArray[this.gp.currentMap][i].solidArea.width / this.gp.tileSize;
            int n9 = entityArray[this.gp.currentMap][i].solidArea.height / this.gp.tileSize;
            boolean bl = false;
            for (int j = n6; j < n6 + n8; ++j) {
                for (int k = n7; k < n7 + n9; ++k) {
                    if (n4 != j || n5 != k) continue;
                    bl = true;
                    break;
                }
                if (bl) break;
            }
            if (!bl) continue;
            n = i;
            break;
        }
        return n;
    }

    public void checkBossShoot(int n, int n2) {
        int n3 = new Random().nextInt(n);
        if (n3 == 0 && this.shotAvailableCounter >= n2) {
            int n4 = this.worldX + this.solidArea.x;
            int n5 = this.worldX + this.solidArea.x + this.solidArea.width;
            int n6 = this.worldY + this.solidArea.y;
            int n7 = this.worldY + this.solidArea.y + this.solidArea.height;
            int n8 = this.worldX + this.solidArea.x + this.solidArea.width / 2;
            int n9 = this.worldY + this.solidArea.y + this.solidArea.height / 2;
            int n10 = n8;
            int n11 = n9;
            switch (this.direction) {
                case "up": {
                    n11 = n6 - this.gp.tileSize;
                    n10 = n8 - this.gp.tileSize / 2;
                    break;
                }
                case "down": {
                    n11 = n7;
                    n10 = n8 - this.gp.tileSize / 2;
                    break;
                }
                case "left": {
                    n10 = n4 - this.gp.tileSize;
                    n11 = n9 - this.gp.tileSize / 2;
                    break;
                }
                case "right": {
                    n10 = n5;
                    n11 = n9 - this.gp.tileSize / 2;
                }
            }
            this.projectile.set(n10, n11, this.direction, true, this);
            for (int i = 0; i < this.gp.projectile[this.gp.currentMap].length; ++i) {
                if (this.gp.projectile[this.gp.currentMap][i] != null) continue;
                this.gp.projectile[this.gp.currentMap][i] = this.projectile;
                break;
            }
            this.shotAvailableCounter = 0;
        }
    }
}
