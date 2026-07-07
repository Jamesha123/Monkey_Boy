/*
 * Decompiled with CFR 0.152.
 */
package entity;

import entity.Entity;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Objects;
import main.GamePanel;
import main.KeyHandler;
import objects.OBJ_Fireball;
import spellBook.snakeGame;

public class Player
extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCanceled = false;
    public boolean lightUpdated = false;
    private long lastManaRegen = 0L;
    private final long MANA_REGEN_INTERVAL = 8000L;
    public boolean speedBoostActive = false;
    public int speedBoostCounter = 0;
    private final int SPEED_BOOST_DURATION = 600;
    public Entity[] hotbar;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        super(gp);
        this.keyH = keyHandler;
        this.screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        this.screenY = gp.screenHeight / 2 - gp.tileSize / 2;
        this.solidArea = new Rectangle();
        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 30;
        this.solidArea.height = 30;
        this.setDefaultValues();
    }

    public void setDefaultValues() {
        this.speed = this.defaultSpeed = 4;
        this.direction = "down";
        this.level = 1;
        this.life = this.maxLife = 6;
        this.mana = this.maxMana = 0;
        this.ammo = 10;
        this.strength = 1;
        this.dexterity = 1;
        this.exp = 0;
        this.nextLevelExp = 5;
        this.coin = 0;
        this.currentWeapon = null;
        this.currentShield = null;
        this.currentLight = null;
        this.hotbar = new Entity[5];
        this.projectile = new OBJ_Fireball(this.gp, "normal");
        this.attack = this.getAttack();
        this.defense = this.getDefense();
        this.getImage();
        this.getAttackImage();
        this.getGuardImage();
        this.setItems();
        this.setDialogue();
    }

    private int findHotbarSlotForItem(Entity item) {
        if (item == null) {
            return -1;
        }
        for (int i = 0; i < this.hotbar.length; ++i) {
            if (this.hotbar[i] != item) continue;
            return i;
        }
        return -1;
    }

    private void assignItemToHotbarSlot(Entity item, int slotIndex) {
        if (slotIndex < 0 || slotIndex >= this.hotbar.length) {
            return;
        }
        int existingSlot = this.findHotbarSlotForItem(item);
        if (existingSlot != -1 && existingSlot != slotIndex) {
            this.hotbar[existingSlot] = null;
        }
        this.hotbar[slotIndex] = item;
    }

    public void setDefaultPositions(int mapIndex) {
        this.gp.currentMap = mapIndex;
        Objects.requireNonNull(this.gp);
        if (mapIndex == 0) {
            this.worldX = this.gp.tileSize * 17;
            this.worldY = this.gp.tileSize * 27;
            this.direction = "down";
            Objects.requireNonNull(this.gp);
            this.gp.currentArea = 2;
        } else {
            Objects.requireNonNull(this.gp);
            if (mapIndex == 1) {
                this.worldX = this.gp.tileSize * 29;
                this.worldY = this.gp.tileSize * 23;
                this.direction = "down";
                Objects.requireNonNull(this.gp);
                this.gp.currentArea = 2;
            } else {
                Objects.requireNonNull(this.gp);
                if (mapIndex == 2) {
                    this.worldX = this.gp.tileSize * 19;
                    this.worldY = this.gp.tileSize * 38;
                    this.direction = "down";
                    Objects.requireNonNull(this.gp);
                    this.gp.currentArea = 1;
                } else {
                    Objects.requireNonNull(this.gp);
                    if (mapIndex == 3) {
                        this.worldX = this.gp.tileSize * 25;
                        this.worldY = this.gp.tileSize * 41;
                        this.direction = "up";
                        Objects.requireNonNull(this.gp);
                        this.gp.currentArea = 1;
                    } else {
                        Objects.requireNonNull(this.gp);
                        if (mapIndex == 4) {
                            this.worldX = this.gp.tileSize * 9;
                            this.worldY = this.gp.tileSize * 12;
                            this.direction = "right";
                            Objects.requireNonNull(this.gp);
                            this.gp.currentArea = 1;
                        } else {
                            Objects.requireNonNull(this.gp);
                            if (mapIndex == 8) {
                                this.worldX = this.gp.tileSize * 41;
                                this.worldY = this.gp.tileSize * 8;
                                this.direction = "left";
                                Objects.requireNonNull(this.gp);
                                this.gp.currentArea = 1;
                            } else {
                                Objects.requireNonNull(this.gp);
                                if (mapIndex == 10) {
                                    this.worldX = this.gp.tileSize * 22;
                                    this.worldY = this.gp.tileSize * 39;
                                    this.direction = "up";
                                    Objects.requireNonNull(this.gp);
                                    this.gp.currentArea = 1;
                                } else {
                                    Objects.requireNonNull(this.gp);
                                    if (mapIndex == 5) {
                                        this.worldX = this.gp.tileSize * 9;
                                        this.worldY = this.gp.tileSize * 29;
                                        this.direction = "right";
                                        Objects.requireNonNull(this.gp);
                                        this.gp.currentArea = 1;
                                    } else {
                                        Objects.requireNonNull(this.gp);
                                        if (mapIndex == 6) {
                                            this.worldX = this.gp.tileSize * 12;
                                            this.worldY = this.gp.tileSize * 12;
                                            this.direction = "up";
                                            Objects.requireNonNull(this.gp);
                                            this.gp.currentArea = 2;
                                        } else {
                                            Objects.requireNonNull(this.gp);
                                            if (mapIndex == 7) {
                                                this.worldX = this.gp.tileSize * 11;
                                                this.worldY = this.gp.tileSize * 11;
                                                this.direction = "down";
                                                Objects.requireNonNull(this.gp);
                                                this.gp.currentArea = 2;
                                            } else {
                                                Objects.requireNonNull(this.gp);
                                                if (mapIndex == 9) {
                                                    this.worldX = this.gp.tileSize * 24;
                                                    this.worldY = this.gp.tileSize * 11;
                                                    this.direction = "down";
                                                    Objects.requireNonNull(this.gp);
                                                    this.gp.currentArea = 1;
                                                } else {
                                                    Objects.requireNonNull(this.gp);
                                                    if (mapIndex == 11) {
                                                        this.worldX = this.gp.tileSize * 9;
                                                        this.worldY = this.gp.tileSize * 42;
                                                        this.direction = "up";
                                                        Objects.requireNonNull(this.gp);
                                                        this.gp.currentArea = 3;
                                                    } else {
                                                        Objects.requireNonNull(this.gp);
                                                        if (mapIndex == 12) {
                                                            this.worldX = this.gp.tileSize * 28;
                                                            this.worldY = this.gp.tileSize * 42;
                                                            this.direction = "up";
                                                            Objects.requireNonNull(this.gp);
                                                            this.gp.currentArea = 3;
                                                        } else {
                                                            Objects.requireNonNull(this.gp);
                                                            if (mapIndex == 13) {
                                                                this.worldX = this.gp.tileSize * 25;
                                                                this.worldY = this.gp.tileSize * 42;
                                                                this.direction = "up";
                                                                Objects.requireNonNull(this.gp);
                                                                this.gp.currentArea = 3;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void setRetryPositions(int targetMap, int previousMap) {
        this.gp.currentMap = targetMap;
        this.gp.applyAreaForMap(targetMap);
        if (!this.applyRetrySpawn(targetMap, previousMap)) {
            this.setDefaultPositions(targetMap);
        }
    }

    private boolean applyRetrySpawn(int targetMap, int previousMap) {
        int tileSize = this.gp.tileSize;
        if (targetMap == this.gp.worldmap2 && previousMap == this.gp.worldmap3) {
            this.placeAt(tileSize * 38, tileSize * 12, "left");
            return true;
        }
        if (targetMap == this.gp.worldmap3) {
            if (previousMap == this.gp.village) {
                this.placeAt(tileSize * 37, tileSize * 37, "left");
                return true;
            }
            if (previousMap == this.gp.worldmap4) {
                this.placeAt(tileSize * 11, tileSize * 37, "right");
                return true;
            }
            if (previousMap == this.gp.worldmap5) {
                this.placeAt(tileSize * 35, tileSize * 9, "down");
                return true;
            }
        }
        if (targetMap == this.gp.worldmap4 && previousMap == this.gp.slimeBossRoom) {
            this.placeAt(tileSize * 21, tileSize * 42, "up");
            return true;
        }
        if (targetMap == this.gp.worldmap5 && previousMap == this.gp.dungeon01) {
            this.placeAt(tileSize * 36, tileSize * 9, "down");
            return true;
        }
        if (targetMap == this.gp.dungeon01 && previousMap == this.gp.dungeon02) {
            this.placeAt(tileSize * 12, tileSize * 8, "down");
            return true;
        }
        if (targetMap == this.gp.dungeon02 && previousMap == this.gp.skeletonLordRoom) {
            this.placeAt(tileSize * 38, tileSize * 9, "down");
            return true;
        }
        return false;
    }

    private void placeAt(int worldX, int worldY, String direction) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
    }

    public void setDialogue() {
        this.dialogues[0][0] = "You are level " + this.level + " now!\nYou feel stronger!";
    }

    public void restoreStatus() {
        this.life = this.maxLife;
        this.mana = this.maxMana;
        this.speed = this.defaultSpeed;
        this.invincible = false;
        this.transparent = false;
        this.attacking = false;
        this.guarding = false;
        this.knockBack = false;
        this.lightUpdated = true;
        this.speedBoostActive = false;
        this.speedBoostCounter = 0;
    }

    public void setItems() {
        this.inventory.clear();
    }

    public int getAttack() {
        if (this.currentWeapon == null) {
            this.attackArea = new Rectangle(0, 0, 0, 0);
            this.motion1_duration = 0;
            this.motion2_duration = 0;
            this.attack = 0;
            return 0;
        }
        this.attackArea = this.currentWeapon.attackArea;
        this.motion1_duration = this.currentWeapon.motion1_duration;
        this.motion2_duration = this.currentWeapon.motion2_duration;
        this.attack = this.strength + this.currentWeapon.attackValue;
        return this.attack;
    }

    public int getDefense() {
        if (this.currentShield == null) {
            this.defense = this.dexterity * 1;
            return this.defense;
        }
        if (this.currentShield.name.equals("Wood Shield")) {
            this.defense = this.dexterity + 1;
            return this.defense;
        }
        if (this.currentShield.name.equals("Blue Shield")) {
            this.defense = this.dexterity + 2;
            return this.defense;
        }
        this.defense = this.dexterity;
        return this.defense;
    }

    public int getCurrentWeaponSlot() {
        if (this.currentWeapon == null) {
            return -1;
        }
        for (int i = 0; i < this.inventory.size(); ++i) {
            if (this.inventory.get(i) != this.currentWeapon) continue;
            return i;
        }
        return 0;
    }

    public int getCurrentShieldSlot() {
        if (this.currentShield == null) {
            return -1;
        }
        for (int i = 0; i < this.inventory.size(); ++i) {
            if (this.inventory.get(i) != this.currentShield) continue;
            return i;
        }
        return 0;
    }

    public int getCurrentLightSlot() {
        if (this.currentLight == null) {
            return -1;
        }
        for (int i = 0; i < this.inventory.size(); ++i) {
            if (this.inventory.get(i) != this.currentLight) continue;
            return i;
        }
        return 0;
    }

    public void getImage() {
        this.up1 = this.setup("/Player/Walking sprites/boy_up_1", this.gp.tileSize, this.gp.tileSize);
        this.up2 = this.setup("/Player/Walking sprites/boy_up_2", this.gp.tileSize, this.gp.tileSize);
        this.down1 = this.setup("/Player/Walking sprites/boy_down_1", this.gp.tileSize, this.gp.tileSize);
        this.down2 = this.setup("/Player/Walking sprites/boy_down_2", this.gp.tileSize, this.gp.tileSize);
        this.left1 = this.setup("/Player/Walking sprites/boy_left_1", this.gp.tileSize, this.gp.tileSize);
        this.left2 = this.setup("/Player/Walking sprites/boy_left_2", this.gp.tileSize, this.gp.tileSize);
        this.left3 = this.setup("/Player/Walking sprites/boy_left_3", this.gp.tileSize, this.gp.tileSize);
        this.right1 = this.setup("/Player/Walking sprites/boy_right_1", this.gp.tileSize, this.gp.tileSize);
        this.right2 = this.setup("/Player/Walking sprites/boy_right_2", this.gp.tileSize, this.gp.tileSize);
        this.right3 = this.setup("/Player/Walking sprites/boy_right_3", this.gp.tileSize, this.gp.tileSize);
    }

    public void getSleepingImage(BufferedImage sleepingSprite) {
        this.up1 = sleepingSprite;
        this.up2 = sleepingSprite;
        this.down1 = sleepingSprite;
        this.down2 = sleepingSprite;
        this.left1 = sleepingSprite;
        this.left2 = sleepingSprite;
        this.right1 = sleepingSprite;
        this.right2 = sleepingSprite;
    }

    public void getAttackImage() {
        if (this.currentWeapon != null) {
            if (this.currentWeapon.type == 3) {
                if (this.currentWeapon.name.equals("Normal Sword")) {
                    this.attackUp1 = this.setup("/Player/Attacking sprites/boy_sword_normal_up_1", this.gp.tileSize, this.gp.tileSize * 2);
                    this.attackUp2 = this.setup("/Player/Attacking sprites/boy_sword_normal_up_2", this.gp.tileSize, this.gp.tileSize * 2);
                    this.attackDown1 = this.setup("/Player/Attacking sprites/boy_sword_normal_down_1", this.gp.tileSize, this.gp.tileSize * 2);
                    this.attackDown2 = this.setup("/Player/Attacking sprites/boy_sword_normal_down_2", this.gp.tileSize, this.gp.tileSize * 2);
                    this.attackLeft1 = this.setup("/Player/Attacking sprites/boy_sword_normal_left_1", this.gp.tileSize * 2, this.gp.tileSize);
                    this.attackLeft2 = this.setup("/Player/Attacking sprites/boy_sword_normal_left_2", this.gp.tileSize * 2, this.gp.tileSize);
                    this.attackRight1 = this.setup("/Player/Attacking sprites/boy_sword_normal_right_1", this.gp.tileSize * 2, this.gp.tileSize);
                    this.attackRight2 = this.setup("/Player/Attacking sprites/boy_sword_normal_right_2", this.gp.tileSize * 2, this.gp.tileSize);
                } else if (this.currentWeapon.name.equals("Epic Sword")) {
                    this.attackUp1 = this.setup("/Player/Attacking sprites/boy_sword_epic_up_1", this.gp.tileSize, this.gp.tileSize * 2);
                    this.attackUp2 = this.setup("/Player/Attacking sprites/boy_sword_epic_up_2", this.gp.tileSize, this.gp.tileSize * 2);
                    this.attackDown1 = this.setup("/Player/Attacking sprites/boy_sword_epic_down_1", this.gp.tileSize, this.gp.tileSize * 2);
                    this.attackDown2 = this.setup("/Player/Attacking sprites/boy_sword_epic_down_2", this.gp.tileSize, this.gp.tileSize * 2);
                    this.attackLeft1 = this.setup("/Player/Attacking sprites/boy_sword_epic_left_1", this.gp.tileSize * 2, this.gp.tileSize);
                    this.attackLeft2 = this.setup("/Player/Attacking sprites/boy_sword_epic_left_2", this.gp.tileSize * 2, this.gp.tileSize);
                    this.attackRight1 = this.setup("/Player/Attacking sprites/boy_sword_epic_right_1", this.gp.tileSize * 2, this.gp.tileSize);
                    this.attackRight2 = this.setup("/Player/Attacking sprites/boy_sword_epic_right_2", this.gp.tileSize * 2, this.gp.tileSize);
                }
            } else if (this.currentWeapon.type == 4) {
                this.attackUp1 = this.setup("/Player/Attacking sprites/boy_axe_up_1", this.gp.tileSize, this.gp.tileSize * 2);
                this.attackUp2 = this.setup("/Player/Attacking sprites/boy_axe_up_2", this.gp.tileSize, this.gp.tileSize * 2);
                this.attackDown1 = this.setup("/Player/Attacking sprites/boy_axe_down_1", this.gp.tileSize, this.gp.tileSize * 2);
                this.attackDown2 = this.setup("/Player/Attacking sprites/boy_axe_down_2", this.gp.tileSize, this.gp.tileSize * 2);
                this.attackLeft1 = this.setup("/Player/Attacking sprites/boy_axe_left_1", this.gp.tileSize * 2, this.gp.tileSize);
                this.attackLeft2 = this.setup("/Player/Attacking sprites/boy_axe_left_2", this.gp.tileSize * 2, this.gp.tileSize);
                this.attackRight1 = this.setup("/Player/Attacking sprites/boy_axe_right_1", this.gp.tileSize * 2, this.gp.tileSize);
                this.attackRight2 = this.setup("/Player/Attacking sprites/boy_axe_right_2", this.gp.tileSize * 2, this.gp.tileSize);
            } else if (this.currentWeapon.type == 10) {
                this.attackUp1 = this.setup("/Player/Attacking sprites/boy_pick_up_1", this.gp.tileSize, this.gp.tileSize * 2);
                this.attackUp2 = this.setup("/Player/Attacking sprites/boy_pick_up_2", this.gp.tileSize, this.gp.tileSize * 2);
                this.attackDown1 = this.setup("/Player/Attacking sprites/boy_pick_down_1", this.gp.tileSize, this.gp.tileSize * 2);
                this.attackDown2 = this.setup("/Player/Attacking sprites/boy_pick_down_2", this.gp.tileSize, this.gp.tileSize * 2);
                this.attackLeft1 = this.setup("/Player/Attacking sprites/boy_pick_left_1", this.gp.tileSize * 2, this.gp.tileSize);
                this.attackLeft2 = this.setup("/Player/Attacking sprites/boy_pick_left_2", this.gp.tileSize * 2, this.gp.tileSize);
                this.attackRight1 = this.setup("/Player/Attacking sprites/boy_pick_right_1", this.gp.tileSize * 2, this.gp.tileSize);
                this.attackRight2 = this.setup("/Player/Attacking sprites/boy_pick_right_2", this.gp.tileSize * 2, this.gp.tileSize);
            }
        }
    }

    public void getGuardImage() {
        if (this.currentShield == null) {
            this.guardUp = null;
            this.guardDown = null;
            this.guardLeft = null;
            this.guardRight = null;
        } else if (this.currentShield.name.equals("Wood Shield")) {
            this.guardUp = this.setup("/Player/Guarding sprites/boy_shield_wood_up", this.gp.tileSize, this.gp.tileSize);
            this.guardDown = this.setup("/Player/Guarding sprites/boy_shield_wood_down", this.gp.tileSize, this.gp.tileSize);
            this.guardLeft = this.setup("/Player/Guarding sprites/boy_shield_wood_left", this.gp.tileSize, this.gp.tileSize);
            this.guardRight = this.setup("/Player/Guarding sprites/boy_shield_wood_right", this.gp.tileSize, this.gp.tileSize);
        } else if (this.currentShield.name.equals("Blue Shield")) {
            this.guardUp = this.setup("/Player/Guarding sprites/boy_shield_blue_up", this.gp.tileSize, this.gp.tileSize);
            this.guardDown = this.setup("/Player/Guarding sprites/boy_shield_blue_down", this.gp.tileSize, this.gp.tileSize);
            this.guardLeft = this.setup("/Player/Guarding sprites/boy_shield_blue_left", this.gp.tileSize, this.gp.tileSize);
            this.guardRight = this.setup("/Player/Guarding sprites/boy_shield_blue_right", this.gp.tileSize, this.gp.tileSize);
        }
    }

    @Override
    public void update() {
        if (this.knockBack) {
            this.collisionOn = false;
            this.gp.cChecker.checkTile(this);
            this.gp.cChecker.checkObject(this, true);
            this.gp.cChecker.checkEntity(this, this.gp.npc);
            this.gp.cChecker.checkEntity(this, this.gp.monster);
            this.gp.cChecker.checkInteractiveTile(this);
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
        } else if (this.keyH.spacePressed) {
            this.guarding = true;
            ++this.guardCounter;
        } else if (this.keyH.keyPressed[49] || this.keyH.keyPressed[50] || this.keyH.keyPressed[51] || this.keyH.keyPressed[52] || this.keyH.keyPressed[53]) {
            this.useHotbarItem();
        } else if (this.keyH.upPressed || this.keyH.downPressed || this.keyH.leftPressed || this.keyH.rightPressed || this.keyH.enterPressed) {
            String previousDirection = this.direction;
            if (this.keyH.upPressed) {
                this.direction = "up";
            } else if (this.keyH.downPressed) {
                this.direction = "down";
            } else if (this.keyH.leftPressed) {
                this.direction = "left";
            } else if (this.keyH.rightPressed) {
                this.direction = "right";
            }
            if (!previousDirection.equals(this.direction)) {
                this.spriteNum = 1;
            }
            this.collisionOn = false;
            if (this.gp.debugManager == null || !this.gp.debugManager.collisionOff) {
                this.gp.cChecker.checkTile(this);
                int objectIndex = this.gp.cChecker.checkObject(this, true);
                this.pickUpObject(objectIndex);
                int npcIndex = this.gp.cChecker.checkEntity(this, this.gp.npc);
                this.interactNPC(npcIndex);
                int monsterIndex = this.gp.cChecker.checkEntity(this, this.gp.monster);
                this.contactMonster(monsterIndex);
                int tileIndex = this.gp.cChecker.checkInteractiveTile(this);
                this.interactInteractiveTile(tileIndex);
            }
            this.gp.eHandler.checkEvent();
            if (!this.collisionOn && !this.keyH.enterPressed) {
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
            if (this.keyH.enterPressed && !this.attackCanceled && this.currentWeapon != null) {
                this.gp.playSE(7);
                this.attacking = true;
                this.spriteCounter = 0;
            }
            this.attackCanceled = false;
            this.gp.keyH.enterPressed = false;
            this.guarding = false;
            this.guardCounter = 0;
            ++this.spriteCounter;
            if (this.spriteCounter > 12) {
                if (this.direction.equals("left") || this.direction.equals("right")) {
                    if (this.spriteNum == 1) {
                        this.spriteNum = 2;
                    } else if (this.spriteNum == 2) {
                        this.spriteNum = 3;
                    } else if (this.spriteNum == 3) {
                        this.spriteNum = 2;
                    }
                } else if (this.spriteNum == 1) {
                    this.spriteNum = 2;
                } else if (this.spriteNum == 2) {
                    this.spriteNum = 1;
                }
                this.spriteCounter = 0;
            }
        } else {
            ++this.standCounter;
            if (this.standCounter == 20) {
                this.spriteNum = 1;
                this.standCounter = 0;
            }
            this.guarding = false;
            this.guardCounter = 0;
        }
        if (this.gp.keyH.shotKeyPressed && !this.projectile.alive && this.shotAvailableCounter == 10 && this.projectile.haveResource(this)) {
            this.projectile.set(this.worldX, this.worldY, this.direction, true, this);
            this.projectile.subtractResource(this);
            for (int i = 0; i < this.gp.projectile[1].length; ++i) {
                if (this.gp.projectile[this.gp.currentMap][i] != null) continue;
                this.gp.projectile[this.gp.currentMap][i] = this.projectile;
                break;
            }
            this.shotAvailableCounter = 0;
            this.gp.playSE(10);
        }
        if (this.invincible) {
            ++this.invincibleCounter;
            if (this.invincibleCounter > 60) {
                this.invincible = false;
                this.transparent = false;
                this.invincibleCounter = 0;
            }
        }
        if (this.shotAvailableCounter < 10) {
            ++this.shotAvailableCounter;
        }
        if (this.speedBoostActive) {
            ++this.speedBoostCounter;
            if (this.speedBoostCounter >= 600) {
                this.speedBoostActive = false;
                this.speedBoostCounter = 0;
                this.speed = this.defaultSpeed;
            }
        }
        if (this.life > this.maxLife) {
            this.life = this.maxLife;
        }
        if (this.mana > this.maxMana) {
            this.mana = this.maxMana;
        }
        if (this.maxMana > 0 && this.mana != this.maxMana) {
            long currentTime = System.currentTimeMillis();
            if (this.lastManaRegen == 0L) {
                this.lastManaRegen = currentTime;
            }
            if (currentTime - this.lastManaRegen >= 8000L) {
                ++this.mana;
                this.lastManaRegen = currentTime;
            }
        }
        if (!this.keyH.godModeOn && this.life <= 0) {
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 5;
            this.gp.ui.commandNum = -1;
            this.gp.stopMusic();
            this.gp.playSE(12);
        }
    }

    public void pickUpObject(int objectIndex) {
        if (objectIndex != 999) {
            if (this.gp.obj[this.gp.currentMap][objectIndex].type == 7) {
                this.gp.obj[this.gp.currentMap][objectIndex].use(this);
                this.gp.obj[this.gp.currentMap][objectIndex] = null;
            } else if (this.gp.obj[this.gp.currentMap][objectIndex].type == 8) {
                if (this.keyH.enterPressed) {
                    this.attackCanceled = true;
                    this.gp.obj[this.gp.currentMap][objectIndex].interact();
                }
            } else {
                String message;
                if (this.canObtainItem(this.gp.obj[this.gp.currentMap][objectIndex])) {
                    this.gp.playSE(1);
                    message = "Got a " + this.gp.obj[this.gp.currentMap][objectIndex].name + "!";
                    this.gp.obj[this.gp.currentMap][objectIndex].pickUpObjectDialogue();
                } else {
                    message = "Inventory Full";
                }
                this.gp.ui.addMessage(message);
                this.gp.obj[this.gp.currentMap][objectIndex] = null;
            }
        }
    }

    public void interactNPC(int npcIndex) {
        if (npcIndex != 999) {
            if (this.gp.keyH.enterPressed) {
                this.attackCanceled = true;
                this.gp.npc[this.gp.currentMap][npcIndex].speak();
            }
            this.gp.npc[this.gp.currentMap][npcIndex].move(this.direction);
        }
    }

    public void interactInteractiveTile(int tileIndex) {
        if (tileIndex != 999 && this.gp.keyH.enterPressed && !this.gp.iTile[this.gp.currentMap][tileIndex].destructible) {
            this.attackCanceled = true;
            this.gp.iTile[this.gp.currentMap][tileIndex].interact();
        }
    }

    public void contactMonster(int monsterIndex) {
        if (monsterIndex != 999 && !this.invincible && !this.gp.monster[this.gp.currentMap][monsterIndex].dying && this.gp.monster[this.gp.currentMap][monsterIndex].canContactDamage) {
            this.gp.playSE(6);
            int damage = this.gp.monster[this.gp.currentMap][monsterIndex].attack - this.defense;
            if (damage < 1) {
                damage = 1;
            }
            this.life -= damage;
            this.invincible = true;
            this.transparent = true;
        }
    }

    public void damageMonster(int monsterIndex, Entity attacker, int attackPower, int knockBackPower, String knockBackDir) {
        if (monsterIndex != 999 && !this.gp.monster[this.gp.currentMap][monsterIndex].invincible) {
            int damage;
            this.gp.playSE(5);
            if (knockBackPower > 0) {
                this.setKnockBack(this.gp.monster[this.gp.currentMap][monsterIndex], attacker, knockBackPower, knockBackDir);
            }
            if (this.gp.monster[this.gp.currentMap][monsterIndex].offBalance) {
                attackPower += 5;
            }
            if (attacker != null && attacker.name != null && attacker.name.equals("Fireball")) {
                damage = attackPower;
            } else {
                damage = attackPower - this.gp.monster[this.gp.currentMap][monsterIndex].defense;
                if (damage < 0) {
                    damage = 0;
                }
            }
            this.gp.monster[this.gp.currentMap][monsterIndex].life -= damage;
            this.gp.ui.addMessage(damage + " damage!");
            this.gp.monster[this.gp.currentMap][monsterIndex].invincible = true;
            this.gp.monster[this.gp.currentMap][monsterIndex].damageReaction();
            if (this.gp.monster[this.gp.currentMap][monsterIndex].life <= 0) {
                this.gp.monster[this.gp.currentMap][monsterIndex].dying = true;
                this.gp.ui.addMessage("Killed the " + this.gp.monster[this.gp.currentMap][monsterIndex].name + "!");
                this.gp.ui.addMessage("Gained + " + this.gp.monster[this.gp.currentMap][monsterIndex].exp + " exp!");
                this.exp += this.gp.monster[this.gp.currentMap][monsterIndex].exp;
                this.checkLevelUp();
            }
        }
    }

    public void damageInteractiveTile(int tileIndex) {
        if (tileIndex != 999 && this.gp.iTile[this.gp.currentMap][tileIndex].destructible && this.gp.iTile[this.gp.currentMap][tileIndex].isCorrectItem(this) && !this.gp.iTile[this.gp.currentMap][tileIndex].invincible) {
            this.gp.iTile[this.gp.currentMap][tileIndex].playSE();
            --this.gp.iTile[this.gp.currentMap][tileIndex].life;
            this.gp.iTile[this.gp.currentMap][tileIndex].invincible = true;
            this.generateParticle(this.gp.iTile[this.gp.currentMap][tileIndex], this.gp.iTile[this.gp.currentMap][tileIndex]);
            if (this.gp.iTile[this.gp.currentMap][tileIndex].life == 0) {
                this.gp.iTile[this.gp.currentMap][tileIndex] = this.gp.iTile[this.gp.currentMap][tileIndex].getDestroyedForm();
            }
        }
    }

    public void damageProjectile(int projectileIndex) {
        if (projectileIndex != 999) {
            Entity projectile = this.gp.projectile[this.gp.currentMap][projectileIndex];
            projectile.alive = false;
            this.generateParticle(projectile, projectile);
        }
    }

    public void checkLevelUp() {
        if (this.exp >= this.nextLevelExp) {
            ++this.level;
            this.nextLevelExp *= 2;
            this.maxLife += 2;
            this.life = this.maxLife;
            if (this.maxMana > 0) {
                ++this.maxMana;
            }
            this.mana = this.maxMana;
            ++this.strength;
            ++this.dexterity;
            this.attack = this.getAttack();
            this.defense = this.getDefense();
            this.gp.playSE(8);
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 2;
            this.setDialogue();
            this.startDialogue(this, 0);
        }
    }

    public void selectItem() {
        int slotIndex = this.gp.ui.getItemIndexOnSlot(this.gp.ui.playerSlotCol, this.gp.ui.playerSlotRow);
        if (this.gp.ui.inHotbarAssignmentMode) {
            if (slotIndex < this.inventory.size()) {
                Entity item = (Entity)this.inventory.get(slotIndex);
                this.assignItemToHotbarSlot(item, this.gp.ui.hotbarSlot);
                this.gp.ui.inHotbarAssignmentMode = false;
            }
        } else if (slotIndex < this.inventory.size()) {
            Entity item = (Entity)this.inventory.get(slotIndex);
            if (item.type == 3 || item.type == 4 || item.type == 10) {
                this.currentWeapon = item;
                this.defense = this.getAttack();
                this.getAttackImage();
            }
            if (item.type == 5) {
                this.currentShield = item;
                this.defense = this.getDefense();
                this.getGuardImage();
            }
            if (item.type == 9) {
                if (item.placable) {
                    item.placeItem();
                    if (item.amount > 1) {
                        --item.amount;
                    } else {
                        this.inventory.remove(slotIndex);
                    }
                } else {
                    this.currentLight = this.currentLight == item ? null : item;
                }
                this.lightUpdated = true;
            }
            if (item.type == 6 && item.use(this)) {
                if (item.amount > 1) {
                    --item.amount;
                } else {
                    this.inventory.remove(slotIndex);
                }
            }
            if (item.type == 11) {
                String spellName = null;
                if (item.name == null) {
                    return;
                }
                if (item.name.contains("Fireball")) {
                    spellName = "fireball";
                }
                this.gp.snakeGame = new snakeGame(this.gp, this.gp.keyH, spellName);
                Objects.requireNonNull(this.gp);
                this.gp.gameState = 12;
            }
        }
    }

    public void dropItem() {
        int slotIndex = this.gp.ui.getItemIndexOnSlot(this.gp.ui.playerSlotCol, this.gp.ui.playerSlotRow);
        if (slotIndex < this.inventory.size()) {
            Entity item = (Entity)this.inventory.get(slotIndex);
            if (item == this.currentWeapon || item == this.currentShield || item == this.currentLight) {
                this.gp.ui.addMessage("Cannot drop equipped item!");
                return;
            }
            Entity droppedItem = this.gp.eGenerator.getObject(item.name);
            if (droppedItem != null) {
                droppedItem.solidArea.x = 0;
                droppedItem.solidArea.y = 0;
                droppedItem.solidArea.width = 48;
                droppedItem.solidArea.height = 48;
                droppedItem.solidAreaDefaultX = droppedItem.solidArea.x;
                droppedItem.solidAreaDefaultY = droppedItem.solidArea.y;
                if (item.stackable && item.amount > 1) {
                    droppedItem.amount = item.amount;
                }
                int dropWorldX = this.worldX;
                int dropWorldY = this.worldY;
                switch (this.direction) {
                    case "up": {
                        dropWorldY -= this.gp.tileSize;
                        break;
                    }
                    case "down": {
                        dropWorldY += this.gp.tileSize;
                        break;
                    }
                    case "left": {
                        dropWorldX -= this.gp.tileSize;
                        break;
                    }
                    case "right": {
                        dropWorldX += this.gp.tileSize;
                    }
                }
                droppedItem.worldX = dropWorldX;
                droppedItem.worldY = dropWorldY;
                for (int i = 0; i < this.gp.obj[1].length; ++i) {
                    if (this.gp.obj[this.gp.currentMap][i] != null) continue;
                    this.gp.obj[this.gp.currentMap][i] = droppedItem;
                    break;
                }
                this.inventory.remove(slotIndex);
                this.gp.ui.addMessage("Dropped " + item.name);
                this.gp.playSE(2);
            }
        }
    }

    public int searchItemInInventory(String itemName) {
        for (int i = 0; i < this.inventory.size(); ++i) {
            if (!((Entity)this.inventory.get((int)i)).name.equals(itemName)) continue;
            return i;
        }
        return 999;
    }

    private int findEmptyHotbarSlot() {
        for (int i = 0; i < this.hotbar.length; ++i) {
            if (this.hotbar[i] != null) continue;
            return i;
        }
        return -1;
    }

    private void autoFillHotbar(Entity item) {
        int emptySlot;
        if ((item.type == 3 || item.type == 4 || item.type == 10 || item.type == 5 || item.type == 9 || item.type == 6) && this.findHotbarSlotForItem(item) == -1 && (emptySlot = this.findEmptyHotbarSlot()) != -1) {
            this.assignItemToHotbarSlot(item, emptySlot);
        }
    }

    public boolean canObtainItem(Entity sourceItem) {
        boolean obtained = false;
        Entity inventoryItem = this.gp.eGenerator.getObject(sourceItem.name);
        if (inventoryItem.stackable) {
            int existingIndex = this.searchItemInInventory(inventoryItem.name);
            if (existingIndex != 999) {
                ((Entity)this.inventory.get((int)existingIndex)).amount += sourceItem.amount;
                obtained = true;
            } else if (this.inventory.size() != 20) {
                inventoryItem.amount = sourceItem.amount;
                this.inventory.add(inventoryItem);
                this.autoFillHotbar(inventoryItem);
                obtained = true;
            }
        } else if (this.inventory.size() != 20) {
            this.inventory.add(inventoryItem);
            this.autoFillHotbar(inventoryItem);
            obtained = true;
        }
        return obtained;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        BufferedImage currentSprite = null;
        int screenX = this.screenX;
        int screenY = this.screenY;
        switch (this.direction) {
            case "up": {
                if (!this.attacking) {
                    if (this.spriteNum == 1) {
                        currentSprite = this.up1;
                    } else if (this.spriteNum == 2) {
                        currentSprite = this.up2;
                    }
                } else if (this.attacking) {
                    screenY = this.screenY - this.gp.tileSize;
                    if (this.spriteNum == 1) {
                        currentSprite = this.attackUp1;
                    } else if (this.spriteNum == 2) {
                        currentSprite = this.attackUp2;
                    }
                }
                if (!this.guarding || this.guardUp == null) break;
                currentSprite = this.guardUp;
                break;
            }
            case "down": {
                if (!this.attacking) {
                    if (this.spriteNum == 1) {
                        currentSprite = this.down1;
                    } else if (this.spriteNum == 2) {
                        currentSprite = this.down2;
                    }
                } else if (this.attacking) {
                    if (this.spriteNum == 1) {
                        currentSprite = this.attackDown1;
                    } else if (this.spriteNum == 2) {
                        currentSprite = this.attackDown2;
                    }
                }
                if (!this.guarding || this.guardDown == null) break;
                currentSprite = this.guardDown;
                break;
            }
            case "left": {
                if (!this.attacking) {
                    if (this.spriteNum == 1) {
                        currentSprite = this.left1;
                    } else if (this.spriteNum == 2) {
                        currentSprite = this.left2;
                    } else if (this.spriteNum == 3) {
                        currentSprite = this.left3;
                    }
                } else if (this.attacking) {
                    screenX = this.screenX - this.gp.tileSize;
                    if (this.spriteNum == 1) {
                        currentSprite = this.attackLeft1;
                    } else if (this.spriteNum == 2) {
                        currentSprite = this.attackLeft2;
                    }
                }
                if (!this.guarding || this.guardLeft == null) break;
                currentSprite = this.guardLeft;
                break;
            }
            case "right": {
                if (!this.attacking) {
                    if (this.spriteNum == 1) {
                        currentSprite = this.right1;
                    } else if (this.spriteNum == 2) {
                        currentSprite = this.right2;
                    } else if (this.spriteNum == 3) {
                        currentSprite = this.right3;
                    }
                } else if (this.attacking) {
                    if (this.spriteNum == 1) {
                        currentSprite = this.attackRight1;
                    } else if (this.spriteNum == 2) {
                        currentSprite = this.attackRight2;
                    }
                }
                if (!this.guarding || this.guardRight == null) break;
                currentSprite = this.guardRight;
            }
        }
        if (this.transparent) {
            graphics2D.setComposite(AlphaComposite.getInstance(3, 0.3f));
            ++this.compositeCounter;
            if (this.compositeCounter > 20) {
                graphics2D.setComposite(AlphaComposite.getInstance(3, 0.8f));
                this.compositeCounter = 0;
            }
        }
        if (this.drawing) {
            graphics2D.drawImage((Image)currentSprite, screenX, screenY, null);
        }
        graphics2D.setComposite(AlphaComposite.getInstance(3, 1.0f));
    }

    private void useHotbarItem() {
        int hotbarSlot = -1;
        if (this.keyH.keyPressed[49]) {
            hotbarSlot = 0;
            this.keyH.keyPressed[49] = false;
        } else if (this.keyH.keyPressed[50]) {
            hotbarSlot = 1;
            this.keyH.keyPressed[50] = false;
        } else if (this.keyH.keyPressed[51]) {
            hotbarSlot = 2;
            this.keyH.keyPressed[51] = false;
        } else if (this.keyH.keyPressed[52]) {
            hotbarSlot = 3;
            this.keyH.keyPressed[52] = false;
        } else if (this.keyH.keyPressed[53]) {
            hotbarSlot = 4;
            this.keyH.keyPressed[53] = false;
        }
        if (hotbarSlot >= 0 && hotbarSlot < this.hotbar.length && this.hotbar[hotbarSlot] != null) {
            Entity item = this.hotbar[hotbarSlot];
            if (item.type == 3 || item.type == 4 || item.type == 10) {
                this.currentWeapon = item;
                this.defense = this.getAttack();
                this.getAttackImage();
            }
            if (item.type == 5) {
                this.currentShield = item;
                this.defense = this.getDefense();
                this.getGuardImage();
            }
            if (item.type == 9) {
                if (item.placable) {
                    item.placeItem();
                    if (item.amount > 1) {
                        --item.amount;
                    } else {
                        this.hotbar[hotbarSlot] = null;
                        for (int inventoryIndex = 0; inventoryIndex < this.inventory.size(); ++inventoryIndex) {
                            if (this.inventory.get(inventoryIndex) != item) continue;
                            this.inventory.remove(inventoryIndex);
                            break;
                        }
                    }
                } else {
                    this.currentLight = this.currentLight == item ? null : item;
                }
                this.lightUpdated = true;
            }
            if (item.type == 6 && item.use(this)) {
                if (item.amount > 1) {
                    --item.amount;
                } else {
                    this.hotbar[hotbarSlot] = null;
                    for (int inventoryIndex = 0; inventoryIndex < this.inventory.size(); ++inventoryIndex) {
                        if (this.inventory.get(inventoryIndex) != item) continue;
                        this.inventory.remove(inventoryIndex);
                        break;
                    }
                }
            }
            if (item.type == 11) {
                // empty if block
            }
        }
    }
}
