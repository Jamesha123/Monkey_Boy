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

    public Entity(GamePanel gp) {
        this.gp = gp;
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
        return this.worldX - this.gp.player.worldX + this.gp.player.screenX;
    }

    public int getScreenY() {
        return this.worldY - this.gp.player.worldY + this.gp.player.screenY;
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
        return this.worldX + this.left1.getWidth() / 2;
    }

    public int getCenterY() {
        return this.worldY + this.up1.getHeight() / 2;
    }

    public int getXDistance(Entity target) {
        return Math.abs(this.getCenterX() - target.getCenterX());
    }

    public int getYDistance(Entity target) {
        return Math.abs(this.getCenterY() - target.getCenterY());
    }

    public int getTileDistance(Entity target) {
        return (this.getXDistance(target) + this.getYDistance(target)) / this.gp.tileSize;
    }

    public int getGoalCol(Entity target) {
        return (target.worldX + target.solidArea.x) / this.gp.tileSize;
    }

    public int getGoalRow(Entity target) {
        return (target.worldY + target.solidArea.y) / this.gp.tileSize;
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

    public void setLoot(Entity loot) {
    }

    public void setAction() {
    }

    public void move(String direction) {
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

    public boolean use(Entity user) {
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

    public void startDialogue(Entity npc, int dialogueSet) {
        Objects.requireNonNull(this.gp);
        this.gp.gameState = 2;
        this.gp.ui.npc = npc;
        this.dialogueSet = dialogueSet;
    }

    public void dropItem(Entity item) {
        for (int i = 0; i < this.gp.obj[1].length; ++i) {
            if (this.gp.obj[this.gp.currentMap][i] != null) continue;
            this.worldX = this.worldX + this.solidArea.x + this.solidArea.width / 4;
            this.worldY = this.worldY + this.solidArea.y + this.solidArea.height / 4;
            this.gp.obj[this.gp.currentMap][i] = item;
            this.gp.obj[this.gp.currentMap][i].worldX = this.worldX;
            this.gp.obj[this.gp.currentMap][i].worldY = this.worldY;
            break;
        }
    }

    public Color getParticleColor() {
        return null;
    }

    public int getParticleSize() {
        return 0;
    }

    public int getParticleSpeed() {
        return 0;
    }

    public int getParticleMaxLife() {
        return 0;
    }

    public void generateParticle(Entity source, Entity target) {
        Color color = source.getParticleColor();
        int size = source.getParticleSize();
        int speed = source.getParticleSpeed();
        int maxLife = source.getParticleMaxLife();
        Particle particle = new Particle(this.gp, target, color, size, speed, maxLife, -2, -1);
        Particle particle2 = new Particle(this.gp, target, color, size, speed, maxLife, 2, -1);
        Particle particle3 = new Particle(this.gp, target, color, size, speed, maxLife, -2, 1);
        Particle particle4 = new Particle(this.gp, target, color, size, speed, maxLife, 2, 1);
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
        boolean playerContact = this.gp.cChecker.checkPlayer(this);
        if (this.type == 2 && playerContact) {
            this.damagePlayer(this.attack);
        }
    }

    public void checkMonsterAttack(int actionRate, int yRange, int xRange) {
        int roll;
        boolean inAttackRange = false;
        int xDist = this.getXDistance(this.gp.player);
        int yDist = this.getYDistance(this.gp.player);
        switch (this.direction) {
            case "up": {
                if (this.gp.player.getCenterY() >= this.getCenterY() || yDist >= yRange || xDist >= xRange) break;
                inAttackRange = true;
                break;
            }
            case "down": {
                if (this.gp.player.getCenterY() <= this.worldY || yDist >= yRange || xDist >= xRange) break;
                inAttackRange = true;
                break;
            }
            case "left": {
                if (this.gp.player.getCenterX() >= this.getCenterY() || yDist >= yRange || xDist >= xRange) break;
                inAttackRange = true;
                break;
            }
            case "right": {
                if (this.gp.player.getCenterX() <= this.getCenterX() || yDist >= yRange || xDist >= xRange) break;
                inAttackRange = true;
            }
        }
        if (inAttackRange && (roll = new Random().nextInt(actionRate)) == 0) {
            this.attacking = true;
            this.spriteNum = 1;
            this.spriteCounter = 0;
            this.shotAvailableCounter = 0;
        }
    }

    public void checkMonsterShoot(int actionRate, int cooldown) {
        int roll = new Random().nextInt(actionRate);
        if (roll == 0 && !this.projectile.alive && this.shotAvailableCounter == cooldown) {
            this.projectile.set(this.worldX, this.worldY, this.direction, true, this);
            for (int i = 0; i < this.gp.projectile[1].length; ++i) {
                if (this.gp.projectile[this.gp.currentMap][i] != null) continue;
                this.gp.projectile[this.gp.currentMap][i] = this.projectile;
                break;
            }
            this.shotAvailableCounter = 0;
        }
    }

    public void checkStartChasing(Entity target, int distance, int actionRate) {
        int roll;
        if (this.getTileDistance(target) < distance && (roll = new Random().nextInt(actionRate)) == 0) {
            this.onPath = true;
        }
    }

    public void checkStopChasing(Entity target, int distance, int actionRate) {
        int roll;
        if (this.getTileDistance(target) > distance && (roll = new Random().nextInt(actionRate)) == 0) {
            this.onPath = false;
        }
    }

    public void getRandomDirection(int interval) {
        ++this.actionLockCounter;
        if (this.actionLockCounter > interval) {
            Random random = new Random();
            int directionRoll = random.nextInt(100) + 1;
            if (directionRoll <= 25) {
                this.direction = "up";
            } else if (directionRoll > 25 && directionRoll <= 50) {
                this.direction = "down";
            } else if (directionRoll > 50 && directionRoll <= 75) {
                this.direction = "left";
            } else if (directionRoll > 75 && directionRoll <= 100) {
                this.direction = "right";
            }
            this.actionLockCounter = 0;
        }
    }

    public void moveTowardPlayer(int interval) {
        ++this.actionLockCounter;
        if (this.actionLockCounter > interval) {
            if (this.getXDistance(this.gp.player) > this.getYDistance(this.gp.player)) {
                this.direction = this.gp.player.getCenterX() < this.getCenterX() ? "left" : "right";
            } else if (this.getXDistance(this.gp.player) < this.getYDistance(this.gp.player)) {
                this.direction = this.gp.player.getCenterY() < this.getCenterY() ? "up" : "down";
            }
            this.actionLockCounter = 0;
        }
    }

    public String getOppositeDirection(String direction) {
        String oppositeDirection = "";
        switch (direction) {
            case "up": {
                oppositeDirection = "down";
                break;
            }
            case "down": {
                oppositeDirection = "up";
                break;
            }
            case "left": {
                oppositeDirection = "right";
                break;
            }
            case "right": {
                oppositeDirection = "left";
            }
        }
        return oppositeDirection;
    }

    public void attacking() {
        ++this.spriteCounter;
        if (this.spriteCounter <= this.motion1_duration) {
            this.spriteNum = 1;
        } else if (this.spriteCounter > this.motion1_duration && this.spriteCounter <= this.motion2_duration) {
            this.spriteNum = 2;
            int savedWorldX = this.worldX;
            int savedWorldY = this.worldY;
            int savedWidth = this.solidArea.width;
            int savedHeight = this.solidArea.height;
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
                int monsterIndex = this.gp.cChecker.checkEntity(this, this.gp.monster);
                int knockBackPower = this.currentWeapon != null ? this.currentWeapon.knockBackPower : 1;
                this.gp.player.damageMonster(monsterIndex, this, this.attack, knockBackPower, this.direction);
                int tileIndex = this.gp.cChecker.checkInteractiveTile(this);
                this.gp.player.damageInteractiveTile(tileIndex);
                int projectileIndex = this.gp.cChecker.checkEntity(this, this.gp.projectile);
                this.gp.player.damageProjectile(projectileIndex);
            }
            this.worldX = savedWorldX;
            this.worldY = savedWorldY;
            this.solidArea.width = savedWidth;
            this.solidArea.height = savedHeight;
        } else if (this.spriteCounter > this.motion2_duration) {
            this.spriteNum = 1;
            this.spriteCounter = 0;
            this.attacking = false;
        }
    }

    public void damagePlayer(int attackPower) {
        if (!this.gp.player.invincible) {
            String guardDirection = this.getOppositeDirection(this.direction);
            int damage = attackPower - this.gp.player.defense;
            if (this.gp.player.guarding && this.gp.player.direction.equals(guardDirection)) {
                if (this.gp.player.guardCounter < 30) {
                    damage = 0;
                    this.gp.playSE(16);
                    this.setKnockBack(this, this.gp.player, this.knockBackPower, this.gp.player.direction);
                    this.offBalance = true;
                    this.spriteCounter = -60;
                } else {
                    damage /= 3;
                    this.gp.playSE(15);
                }
            } else {
                this.gp.playSE(6);
                if (damage < 1) {
                    damage = 1;
                }
            }
            if (damage != 0) {
                this.gp.player.transparent = true;
                this.setKnockBack(this.gp.player, this, this.knockBackPower, this.direction);
            }
            this.gp.player.life -= damage;
            this.gp.player.invincible = true;
        }
    }

    public void setKnockBack(Entity target, Entity attacker, int knockBackPower, String direction) {
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }

    public boolean inCamera() {
        boolean visible;
        int marginFar;
        int marginNear;
        block5: {
            block4: {
                visible = false;
                marginFar = 5;
                marginNear = 1;
                int currentMap = this.gp.currentMap;
                Objects.requireNonNull(this.gp);
                if (currentMap == 5) break block4;
                int mapId = this.gp.currentMap;
                Objects.requireNonNull(this.gp);
                if (mapId == 1) break block4;
                int mapId2 = this.gp.currentMap;
                Objects.requireNonNull(this.gp);
                if (mapId2 != 2) break block5;
            }
            marginFar = 12;
            marginNear = 5;
        }
        if (this.worldX + this.gp.tileSize * marginFar > this.gp.player.worldX - this.gp.player.screenX && this.worldX - this.gp.tileSize * marginNear < this.gp.player.worldX + this.gp.player.screenX && this.worldY + this.gp.tileSize * marginFar > this.gp.player.worldY - this.gp.player.screenY && this.worldY - this.gp.tileSize * marginNear < this.gp.player.worldY + this.gp.player.screenY) {
            visible = true;
        }
        return visible;
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage currentSprite = null;
        if (this.inCamera()) {
            int screenX = this.getScreenX();
            int screenY = this.getScreenY();
            switch (this.direction) {
                case "up": {
                    if (!this.attacking) {
                        if (this.spriteNum == 1) {
                            currentSprite = this.up1;
                            break;
                        }
                        if (this.spriteNum != 2) break;
                        currentSprite = this.up2;
                        break;
                    }
                    if (!this.attacking) break;
                    screenY = this.getScreenY() - this.up1.getHeight();
                    if (this.spriteNum == 1) {
                        currentSprite = this.attackUp1;
                        break;
                    }
                    if (this.spriteNum != 2) break;
                    currentSprite = this.attackUp2;
                    break;
                }
                case "down": {
                    if (!this.attacking) {
                        if (this.spriteNum == 1) {
                            currentSprite = this.down1;
                            break;
                        }
                        if (this.spriteNum != 2) break;
                        currentSprite = this.down2;
                        break;
                    }
                    if (!this.attacking) break;
                    if (this.spriteNum == 1) {
                        currentSprite = this.attackDown1;
                        break;
                    }
                    if (this.spriteNum != 2) break;
                    currentSprite = this.attackDown2;
                    break;
                }
                case "left": {
                    if (!this.attacking) {
                        if (this.spriteNum == 1) {
                            currentSprite = this.left1;
                            break;
                        }
                        if (this.spriteNum != 2) break;
                        currentSprite = this.left2;
                        break;
                    }
                    if (!this.attacking) break;
                    screenX = this.getScreenX() - this.left1.getWidth();
                    if (this.spriteNum == 1) {
                        currentSprite = this.attackLeft1;
                        break;
                    }
                    if (this.spriteNum != 2) break;
                    currentSprite = this.attackLeft2;
                    break;
                }
                case "right": {
                    if (!this.attacking) {
                        if (this.spriteNum == 1) {
                            currentSprite = this.right1;
                            break;
                        }
                        if (this.spriteNum != 2) break;
                        currentSprite = this.right2;
                        break;
                    }
                    if (!this.attacking) break;
                    if (this.spriteNum == 1) {
                        currentSprite = this.attackRight1;
                        break;
                    }
                    if (this.spriteNum != 2) break;
                    currentSprite = this.attackRight2;
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
            graphics2D.drawImage((Image)currentSprite, screenX, screenY, null);
            this.changeAlpha(graphics2D, 1.0f);
        }
    }

    public void dyingAnimation(Graphics2D graphics2D) {
        ++this.dyingCounter;
        int flashInterval = 5;
        if (this.dyingCounter <= flashInterval) {
            this.changeAlpha(graphics2D, 0.0f);
        } else if (this.dyingCounter > flashInterval && this.dyingCounter <= flashInterval * 2) {
            this.changeAlpha(graphics2D, 1.0f);
        } else if (this.dyingCounter > flashInterval * 2 && this.dyingCounter <= flashInterval * 3) {
            this.changeAlpha(graphics2D, 0.0f);
        } else if (this.dyingCounter > flashInterval * 3 && this.dyingCounter <= flashInterval * 4) {
            this.changeAlpha(graphics2D, 1.0f);
        } else if (this.dyingCounter > flashInterval * 4 && this.dyingCounter <= flashInterval * 5) {
            this.changeAlpha(graphics2D, 0.0f);
        } else if (this.dyingCounter > flashInterval * 5 && this.dyingCounter <= flashInterval * 6) {
            this.changeAlpha(graphics2D, 1.0f);
        } else if (this.dyingCounter > flashInterval * 6 && this.dyingCounter <= flashInterval * 7) {
            this.changeAlpha(graphics2D, 0.0f);
        } else if (this.dyingCounter > flashInterval * 7 && this.dyingCounter <= flashInterval * 8) {
            this.changeAlpha(graphics2D, 1.0f);
        } else if (this.dyingCounter > flashInterval * 8) {
            this.alive = false;
        }
    }

    public void changeAlpha(Graphics2D graphics2D, float alpha) {
        graphics2D.setComposite(AlphaComposite.getInstance(3, alpha));
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        return ImageCache.getImage(imagePath, width, height);
    }

    public void searchPath(int goalCol, int goalRow) {
        int startCol = (this.worldX + this.solidArea.x) / this.gp.tileSize;
        int startRow = (this.worldY + this.solidArea.y) / this.gp.tileSize;
        this.gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
        if (this.gp.pFinder.search()) {
            int nextWorldX = this.gp.pFinder.pathList.get((int)0).col * this.gp.tileSize;
            int nextWorldY = this.gp.pFinder.pathList.get((int)0).row * this.gp.tileSize;
            int leftX = this.worldX + this.solidArea.x;
            int rightX = this.worldX + this.solidArea.x + this.solidArea.width;
            int topY = this.worldY + this.solidArea.y;
            int bottomY = this.worldY + this.solidArea.y + this.solidArea.height;
            if (topY > nextWorldY && leftX >= nextWorldX && rightX < nextWorldX + this.gp.tileSize) {
                this.direction = "up";
            } else if (topY < nextWorldY && leftX >= nextWorldX && rightX < nextWorldX + this.gp.tileSize) {
                this.direction = "down";
            } else if (topY >= nextWorldY && bottomY < nextWorldY + this.gp.tileSize) {
                if (leftX > nextWorldX) {
                    this.direction = "left";
                }
                if (leftX < nextWorldX) {
                    this.direction = "right";
                }
            } else if (topY > nextWorldY && leftX > nextWorldX) {
                this.direction = "up";
                this.checkCollision();
                if (this.collisionOn) {
                    this.direction = "left";
                }
            } else if (topY > nextWorldY && leftX < nextWorldX) {
                this.direction = "up";
                this.checkCollision();
                if (this.collisionOn) {
                    this.direction = "right";
                }
            } else if (topY < nextWorldY && leftX > nextWorldX) {
                this.direction = "down";
                this.checkCollision();
                if (this.collisionOn) {
                    this.direction = "left";
                }
            } else if (topY < nextWorldY && leftX < nextWorldX) {
                this.direction = "down";
                this.checkCollision();
                if (this.collisionOn) {
                    this.direction = "right";
                }
            }
            if (!this.following) {
                int nextCol = this.gp.pFinder.pathList.get((int)0).col;
                int nextRow = this.gp.pFinder.pathList.get((int)0).row;
                if (nextCol == goalCol && nextRow == goalRow) {
                    this.onPath = false;
                }
            }
        }
    }

    public int getDetected(Entity player, Entity[][] entities, String name) {
        int detectedIndex = 999;
        int checkX = player.getLeftX();
        int checkY = player.getTopY();
        switch (player.direction) {
            case "up": {
                checkY = player.getTopY() - this.gp.player.speed;
                break;
            }
            case "down": {
                checkY = player.getBottomY() + this.gp.player.speed;
                break;
            }
            case "left": {
                checkX = player.getLeftX() - this.gp.player.speed;
                break;
            }
            case "right": {
                checkX = player.getRightX() + this.gp.player.speed;
            }
        }
        int checkCol = checkX / this.gp.tileSize;
        int checkRow = checkY / this.gp.tileSize;
        for (int i = 0; i < entities[1].length; ++i) {
            if (entities[this.gp.currentMap][i] == null || !entities[this.gp.currentMap][i].name.equals(name)) continue;
            int entityCol = entities[this.gp.currentMap][i].getCol();
            int entityRow = entities[this.gp.currentMap][i].getRow();
            int entityWidth = entities[this.gp.currentMap][i].solidArea.width / this.gp.tileSize;
            int entityHeight = entities[this.gp.currentMap][i].solidArea.height / this.gp.tileSize;
            boolean collisionFound = false;
            for (int j = entityCol; j < entityCol + entityWidth; ++j) {
                for (int k = entityRow; k < entityRow + entityHeight; ++k) {
                    if (checkCol != j || checkRow != k) continue;
                    collisionFound = true;
                    break;
                }
                if (collisionFound) break;
            }
            if (!collisionFound) continue;
            detectedIndex = i;
            break;
        }
        return detectedIndex;
    }

    public void checkBossShoot(int actionRate, int cooldown) {
        int roll = new Random().nextInt(actionRate);
        if (roll == 0 && this.shotAvailableCounter >= cooldown) {
            int leftX = this.worldX + this.solidArea.x;
            int rightX = this.worldX + this.solidArea.x + this.solidArea.width;
            int topY = this.worldY + this.solidArea.y;
            int bottomY = this.worldY + this.solidArea.y + this.solidArea.height;
            int centerX = this.worldX + this.solidArea.x + this.solidArea.width / 2;
            int centerY = this.worldY + this.solidArea.y + this.solidArea.height / 2;
            int spawnX = centerX;
            int spawnY = centerY;
            switch (this.direction) {
                case "up": {
                    spawnY = topY - this.gp.tileSize;
                    spawnX = centerX - this.gp.tileSize / 2;
                    break;
                }
                case "down": {
                    spawnY = bottomY;
                    spawnX = centerX - this.gp.tileSize / 2;
                    break;
                }
                case "left": {
                    spawnX = leftX - this.gp.tileSize;
                    spawnY = centerY - this.gp.tileSize / 2;
                    break;
                }
                case "right": {
                    spawnX = rightX;
                    spawnY = centerY - this.gp.tileSize / 2;
                }
            }
            this.projectile.set(spawnX, spawnY, this.direction, true, this);
            for (int i = 0; i < this.gp.projectile[this.gp.currentMap].length; ++i) {
                if (this.gp.projectile[this.gp.currentMap][i] != null) continue;
                this.gp.projectile[this.gp.currentMap][i] = this.projectile;
                break;
            }
            this.shotAvailableCounter = 0;
        }
    }
}
