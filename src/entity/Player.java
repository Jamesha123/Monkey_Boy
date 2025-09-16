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

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.keyH = keyHandler;
        this.screenX = gamePanel.screenWidth / 2 - gamePanel.tileSize / 2;
        this.screenY = gamePanel.screenHeight / 2 - gamePanel.tileSize / 2;
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

    private int findHotbarSlotForItem(Entity entity) {
        if (entity == null) {
            return -1;
        }
        for (int i = 0; i < this.hotbar.length; ++i) {
            if (this.hotbar[i] != entity) continue;
            return i;
        }
        return -1;
    }

    private void assignItemToHotbarSlot(Entity entity, int n) {
        if (n < 0 || n >= this.hotbar.length) {
            return;
        }
        int n2 = this.findHotbarSlotForItem(entity);
        if (n2 != -1 && n2 != n) {
            this.hotbar[n2] = null;
        }
        this.hotbar[n] = entity;
    }

    public void setDefaultPositions(int n) {
        this.gp.currentMap = n;
        Objects.requireNonNull(this.gp);
        if (n == 0) {
            this.worldX = this.gp.tileSize * 17;
            this.worldY = this.gp.tileSize * 27;
            this.direction = "down";
            Objects.requireNonNull(this.gp);
            this.gp.currentArea = 2;
        } else {
            Objects.requireNonNull(this.gp);
            if (n == 1) {
                this.worldX = this.gp.tileSize * 29;
                this.worldY = this.gp.tileSize * 23;
                this.direction = "down";
                Objects.requireNonNull(this.gp);
                this.gp.currentArea = 2;
            } else {
                Objects.requireNonNull(this.gp);
                if (n == 2) {
                    this.worldX = this.gp.tileSize * 19;
                    this.worldY = this.gp.tileSize * 38;
                    this.direction = "down";
                    Objects.requireNonNull(this.gp);
                    this.gp.currentArea = 1;
                } else {
                    Objects.requireNonNull(this.gp);
                    if (n == 3) {
                        this.worldX = this.gp.tileSize * 25;
                        this.worldY = this.gp.tileSize * 41;
                        this.direction = "up";
                        Objects.requireNonNull(this.gp);
                        this.gp.currentArea = 1;
                    } else {
                        Objects.requireNonNull(this.gp);
                        if (n == 4) {
                            this.worldX = this.gp.tileSize * 9;
                            this.worldY = this.gp.tileSize * 12;
                            this.direction = "right";
                            Objects.requireNonNull(this.gp);
                            this.gp.currentArea = 1;
                        } else {
                            Objects.requireNonNull(this.gp);
                            if (n == 8) {
                                this.worldX = this.gp.tileSize * 41;
                                this.worldY = this.gp.tileSize * 8;
                                this.direction = "left";
                                Objects.requireNonNull(this.gp);
                                this.gp.currentArea = 1;
                            } else {
                                Objects.requireNonNull(this.gp);
                                if (n == 10) {
                                    this.worldX = this.gp.tileSize * 22;
                                    this.worldY = this.gp.tileSize * 39;
                                    this.direction = "up";
                                    Objects.requireNonNull(this.gp);
                                    this.gp.currentArea = 1;
                                } else {
                                    Objects.requireNonNull(this.gp);
                                    if (n == 5) {
                                        this.worldX = this.gp.tileSize * 9;
                                        this.worldY = this.gp.tileSize * 29;
                                        this.direction = "right";
                                        Objects.requireNonNull(this.gp);
                                        this.gp.currentArea = 1;
                                    } else {
                                        Objects.requireNonNull(this.gp);
                                        if (n == 6) {
                                            this.worldX = this.gp.tileSize * 12;
                                            this.worldY = this.gp.tileSize * 12;
                                            this.direction = "up";
                                            Objects.requireNonNull(this.gp);
                                            this.gp.currentArea = 2;
                                        } else {
                                            Objects.requireNonNull(this.gp);
                                            if (n == 7) {
                                                this.worldX = this.gp.tileSize * 11;
                                                this.worldY = this.gp.tileSize * 11;
                                                this.direction = "down";
                                                Objects.requireNonNull(this.gp);
                                                this.gp.currentArea = 2;
                                            } else {
                                                Objects.requireNonNull(this.gp);
                                                if (n == 9) {
                                                    this.worldX = this.gp.tileSize * 24;
                                                    this.worldY = this.gp.tileSize * 11;
                                                    this.direction = "down";
                                                    Objects.requireNonNull(this.gp);
                                                    this.gp.currentArea = 1;
                                                } else {
                                                    Objects.requireNonNull(this.gp);
                                                    if (n == 11) {
                                                        this.worldX = this.gp.tileSize * 9;
                                                        this.worldY = this.gp.tileSize * 42;
                                                        this.direction = "up";
                                                        Objects.requireNonNull(this.gp);
                                                        this.gp.currentArea = 3;
                                                    } else {
                                                        Objects.requireNonNull(this.gp);
                                                        if (n == 12) {
                                                            this.worldX = this.gp.tileSize * 28;
                                                            this.worldY = this.gp.tileSize * 42;
                                                            this.direction = "up";
                                                            Objects.requireNonNull(this.gp);
                                                            this.gp.currentArea = 3;
                                                        } else {
                                                            Objects.requireNonNull(this.gp);
                                                            if (n == 13) {
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

    /*
     * Unable to fully structure code
     */
    public void setRetryPositions(int var1_1, int var2_2) {
        block32: {
            block31: {
                block30: {
                    this.gp.currentMap = var1_1;
                    Objects.requireNonNull(this.gp);
                    if (var1_1 == 0) break block30;
                    Objects.requireNonNull(this.gp);
                    if (var1_1 == 1) break block30;
                    Objects.requireNonNull(this.gp);
                    if (var1_1 == 6) break block30;
                    Objects.requireNonNull(this.gp);
                    if (var1_1 != 7) break block31;
                }
                Objects.requireNonNull(this.gp);
                this.gp.currentArea = 2;
                break block32;
            }
            Objects.requireNonNull(this.gp);
            if (var1_1 == 11 || var1_1 == 12 || var1_1 == 13) {
            // 3 sources

            {
                Objects.requireNonNull(this.gp);
                this.gp.currentArea = 3;
            } else {
                Objects.requireNonNull(this.gp);
                this.gp.currentArea = 1;
            }
        }
        Objects.requireNonNull(this.gp);
        if (var1_1 == 3) {
            Objects.requireNonNull(this.gp);
            if (var2_2 == 4) {
                this.worldX = this.gp.tileSize * 38;
                this.worldY = this.gp.tileSize * 12;
                this.direction = "left";
            } else {
                this.setDefaultPositions(var1_1);
            }
        } else {
            Objects.requireNonNull(this.gp);
            if (var1_1 == 4) {
                Objects.requireNonNull(this.gp);
                if (var2_2 == 5) {
                    this.worldX = this.gp.tileSize * 37;
                    this.worldY = this.gp.tileSize * 37;
                    this.direction = "left";
                } else {
                    Objects.requireNonNull(this.gp);
                    if (var2_2 == 8) {
                        this.worldX = this.gp.tileSize * 11;
                        this.worldY = this.gp.tileSize * 37;
                        this.direction = "right";
                    } else {
                        Objects.requireNonNull(this.gp);
                        if (var2_2 == 10) {
                            this.worldX = this.gp.tileSize * 35;
                            this.worldY = this.gp.tileSize * 9;
                            this.direction = "down";
                        } else {
                            this.setDefaultPositions(var1_1);
                        }
                    }
                }
            } else {
                Objects.requireNonNull(this.gp);
                if (var1_1 == 8) {
                    Objects.requireNonNull(this.gp);
                    if (var2_2 == 9) {
                        this.worldX = this.gp.tileSize * 21;
                        this.worldY = this.gp.tileSize * 42;
                        this.direction = "up";
                    } else {
                        this.setDefaultPositions(var1_1);
                    }
                } else {
                    Objects.requireNonNull(this.gp);
                    if (var1_1 == 10) {
                        Objects.requireNonNull(this.gp);
                        if (var2_2 == 11) {
                            this.worldX = this.gp.tileSize * 36;
                            this.worldY = this.gp.tileSize * 9;
                            this.direction = "down";
                        } else {
                            this.setDefaultPositions(var1_1);
                        }
                    } else {
                        Objects.requireNonNull(this.gp);
                        if (var1_1 == 11) {
                            Objects.requireNonNull(this.gp);
                            if (var2_2 == 12) {
                                this.worldX = this.gp.tileSize * 12;
                                this.worldY = this.gp.tileSize * 8;
                                this.direction = "down";
                            } else {
                                this.setDefaultPositions(var1_1);
                            }
                        } else {
                            Objects.requireNonNull(this.gp);
                            if (var1_1 == 12) {
                                Objects.requireNonNull(this.gp);
                                if (var2_2 == 13) {
                                    this.worldX = this.gp.tileSize * 38;
                                    this.worldY = this.gp.tileSize * 9;
                                    this.direction = "down";
                                } else {
                                    this.setDefaultPositions(var1_1);
                                }
                            } else {
                                this.setDefaultPositions(var1_1);
                            }
                        }
                    }
                }
            }
        }
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
        int n = 0;
        for (int i = 0; i < this.inventory.size(); ++i) {
            if (this.inventory.get(i) != this.currentWeapon) continue;
            n = i;
        }
        return n;
    }

    public int getCurrentShieldSlot() {
        if (this.currentShield == null) {
            return -1;
        }
        int n = 0;
        for (int i = 0; i < this.inventory.size(); ++i) {
            if (this.inventory.get(i) != this.currentShield) continue;
            n = i;
        }
        return n;
    }

    public int getCurrentLightSlot() {
        if (this.currentLight == null) {
            return -1;
        }
        int n = 0;
        for (int i = 0; i < this.inventory.size(); ++i) {
            if (this.inventory.get(i) != this.currentLight) continue;
            n = i;
        }
        return n;
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

    public void getSleepingImage(BufferedImage bufferedImage) {
        this.up1 = bufferedImage;
        this.up2 = bufferedImage;
        this.down1 = bufferedImage;
        this.down2 = bufferedImage;
        this.left1 = bufferedImage;
        this.left2 = bufferedImage;
        this.right1 = bufferedImage;
        this.right2 = bufferedImage;
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
            String string = this.direction;
            if (this.keyH.upPressed) {
                this.direction = "up";
            } else if (this.keyH.downPressed) {
                this.direction = "down";
            } else if (this.keyH.leftPressed) {
                this.direction = "left";
            } else if (this.keyH.rightPressed) {
                this.direction = "right";
            }
            if (!string.equals(this.direction)) {
                this.spriteNum = 1;
            }
            this.collisionOn = false;
            if (this.gp.debugManager == null || !this.gp.debugManager.collisionOff) {
                this.gp.cChecker.checkTile(this);
                int n = this.gp.cChecker.checkObject(this, true);
                this.pickUpObject(n);
                int n2 = this.gp.cChecker.checkEntity(this, this.gp.npc);
                this.interactNPC(n2);
                int n3 = this.gp.cChecker.checkEntity(this, this.gp.monster);
                this.contactMonster(n3);
                int n4 = this.gp.cChecker.checkInteractiveTile(this);
                this.interactInteractiveTile(n4);
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
            long l = System.currentTimeMillis();
            if (this.lastManaRegen == 0L) {
                this.lastManaRegen = l;
            }
            if (l - this.lastManaRegen >= 8000L) {
                ++this.mana;
                this.lastManaRegen = l;
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

    public void pickUpObject(int n) {
        if (n != 999) {
            if (this.gp.obj[this.gp.currentMap][n].type == 7) {
                this.gp.obj[this.gp.currentMap][n].use(this);
                this.gp.obj[this.gp.currentMap][n] = null;
            } else if (this.gp.obj[this.gp.currentMap][n].type == 8) {
                if (this.keyH.enterPressed) {
                    this.attackCanceled = true;
                    this.gp.obj[this.gp.currentMap][n].interact();
                }
            } else {
                Object object;
                if (this.canObtainItem(this.gp.obj[this.gp.currentMap][n])) {
                    this.gp.playSE(1);
                    object = "Got a " + this.gp.obj[this.gp.currentMap][n].name + "!";
                    this.gp.obj[this.gp.currentMap][n].pickUpObjectDialogue();
                } else {
                    object = "Inventory Full";
                }
                this.gp.ui.addMessage((String)object);
                this.gp.obj[this.gp.currentMap][n] = null;
            }
        }
    }

    public void interactNPC(int n) {
        if (n != 999) {
            if (this.gp.keyH.enterPressed) {
                this.attackCanceled = true;
                this.gp.npc[this.gp.currentMap][n].speak();
            }
            this.gp.npc[this.gp.currentMap][n].move(this.direction);
        }
    }

    public void interactInteractiveTile(int n) {
        if (n != 999 && this.gp.keyH.enterPressed && !this.gp.iTile[this.gp.currentMap][n].destructible) {
            this.attackCanceled = true;
            this.gp.iTile[this.gp.currentMap][n].interact();
        }
    }

    public void contactMonster(int n) {
        if (n != 999 && !this.invincible && !this.gp.monster[this.gp.currentMap][n].dying && this.gp.monster[this.gp.currentMap][n].canContactDamage) {
            this.gp.playSE(6);
            int n2 = this.gp.monster[this.gp.currentMap][n].attack - this.defense;
            if (n2 < 1) {
                n2 = 1;
            }
            this.life -= n2;
            this.invincible = true;
            this.transparent = true;
        }
    }

    public void damageMonster(int n, Entity entity, int n2, int n3, String string) {
        if (n != 999 && !this.gp.monster[this.gp.currentMap][n].invincible) {
            int n4;
            this.gp.playSE(5);
            if (n3 > 0) {
                this.setKnockBack(this.gp.monster[this.gp.currentMap][n], entity, n3, string);
            }
            if (this.gp.monster[this.gp.currentMap][n].offBalance) {
                n2 += 5;
            }
            if (entity != null && entity.name != null && entity.name.equals("Fireball")) {
                n4 = n2;
            } else {
                n4 = n2 - this.gp.monster[this.gp.currentMap][n].defense;
                if (n4 < 0) {
                    n4 = 0;
                }
            }
            this.gp.monster[this.gp.currentMap][n].life -= n4;
            this.gp.ui.addMessage(n4 + " damage!");
            this.gp.monster[this.gp.currentMap][n].invincible = true;
            this.gp.monster[this.gp.currentMap][n].damageReaction();
            if (this.gp.monster[this.gp.currentMap][n].life <= 0) {
                this.gp.monster[this.gp.currentMap][n].dying = true;
                this.gp.ui.addMessage("Killed the " + this.gp.monster[this.gp.currentMap][n].name + "!");
                this.gp.ui.addMessage("Gained + " + this.gp.monster[this.gp.currentMap][n].exp + " exp!");
                this.exp += this.gp.monster[this.gp.currentMap][n].exp;
                this.checkLevelUp();
            }
        }
    }

    public void damageInteractiveTile(int n) {
        if (n != 999 && this.gp.iTile[this.gp.currentMap][n].destructible && this.gp.iTile[this.gp.currentMap][n].isCorrectItem(this) && !this.gp.iTile[this.gp.currentMap][n].invincible) {
            this.gp.iTile[this.gp.currentMap][n].playSE();
            --this.gp.iTile[this.gp.currentMap][n].life;
            this.gp.iTile[this.gp.currentMap][n].invincible = true;
            this.generateParticle(this.gp.iTile[this.gp.currentMap][n], this.gp.iTile[this.gp.currentMap][n]);
            if (this.gp.iTile[this.gp.currentMap][n].life == 0) {
                this.gp.iTile[this.gp.currentMap][n] = this.gp.iTile[this.gp.currentMap][n].getDestroyedForm();
            }
        }
    }

    public void damageProjectile(int n) {
        if (n != 999) {
            Entity entity = this.gp.projectile[this.gp.currentMap][n];
            entity.alive = false;
            this.generateParticle(entity, entity);
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
        int n = this.gp.ui.getItemIndexOnSlot(this.gp.ui.playerSlotCol, this.gp.ui.playerSlotRow);
        if (this.gp.ui.inHotbarAssignmentMode) {
            if (n < this.inventory.size()) {
                Entity entity = (Entity)this.inventory.get(n);
                this.assignItemToHotbarSlot(entity, this.gp.ui.hotbarSlot);
                this.gp.ui.inHotbarAssignmentMode = false;
            }
        } else if (n < this.inventory.size()) {
            Entity entity = (Entity)this.inventory.get(n);
            if (entity.type == 3 || entity.type == 4 || entity.type == 10) {
                this.currentWeapon = entity;
                this.defense = this.getAttack();
                this.getAttackImage();
            }
            if (entity.type == 5) {
                this.currentShield = entity;
                this.defense = this.getDefense();
                this.getGuardImage();
            }
            if (entity.type == 9) {
                if (entity.placable) {
                    entity.placeItem();
                    if (entity.amount > 1) {
                        --entity.amount;
                    } else {
                        this.inventory.remove(n);
                    }
                } else {
                    this.currentLight = this.currentLight == entity ? null : entity;
                }
                this.lightUpdated = true;
            }
            if (entity.type == 6 && entity.use(this)) {
                if (entity.amount > 1) {
                    --entity.amount;
                } else {
                    this.inventory.remove(n);
                }
            }
            if (entity.type == 11) {
                String string = null;
                if (entity.name == null) {
                    return;
                }
                if (entity.name.contains("Fireball")) {
                    string = "fireball";
                }
                this.gp.snakeGame = new snakeGame(this.gp, this.gp.keyH, string);
                Objects.requireNonNull(this.gp);
                this.gp.gameState = 12;
            }
        }
    }

    public void dropItem() {
        int n = this.gp.ui.getItemIndexOnSlot(this.gp.ui.playerSlotCol, this.gp.ui.playerSlotRow);
        if (n < this.inventory.size()) {
            Entity entity = (Entity)this.inventory.get(n);
            if (entity == this.currentWeapon || entity == this.currentShield || entity == this.currentLight) {
                this.gp.ui.addMessage("Cannot drop equipped item!");
                return;
            }
            Entity entity2 = this.gp.eGenerator.getObject(entity.name);
            if (entity2 != null) {
                entity2.solidArea.x = 0;
                entity2.solidArea.y = 0;
                entity2.solidArea.width = 48;
                entity2.solidArea.height = 48;
                entity2.solidAreaDefaultX = entity2.solidArea.x;
                entity2.solidAreaDefaultY = entity2.solidArea.y;
                if (entity.stackable && entity.amount > 1) {
                    entity2.amount = entity.amount;
                }
                int n2 = this.worldX;
                int n3 = this.worldY;
                switch (this.direction) {
                    case "up": {
                        n3 -= this.gp.tileSize;
                        break;
                    }
                    case "down": {
                        n3 += this.gp.tileSize;
                        break;
                    }
                    case "left": {
                        n2 -= this.gp.tileSize;
                        break;
                    }
                    case "right": {
                        n2 += this.gp.tileSize;
                    }
                }
                entity2.worldX = n2;
                entity2.worldY = n3;
                for (int i = 0; i < this.gp.obj[1].length; ++i) {
                    if (this.gp.obj[this.gp.currentMap][i] != null) continue;
                    this.gp.obj[this.gp.currentMap][i] = entity2;
                    break;
                }
                this.inventory.remove(n);
                this.gp.ui.addMessage("Dropped " + entity.name);
                this.gp.playSE(2);
            }
        }
    }

    public int searchItemInInventory(String string) {
        int n = 999;
        for (int i = 0; i < this.inventory.size(); ++i) {
            if (!((Entity)this.inventory.get((int)i)).name.equals(string)) continue;
            n = i;
            break;
        }
        return n;
    }

    private int findEmptyHotbarSlot() {
        for (int i = 0; i < this.hotbar.length; ++i) {
            if (this.hotbar[i] != null) continue;
            return i;
        }
        return -1;
    }

    private void autoFillHotbar(Entity entity) {
        int n;
        if ((entity.type == 3 || entity.type == 4 || entity.type == 10 || entity.type == 5 || entity.type == 9 || entity.type == 6) && this.findHotbarSlotForItem(entity) == -1 && (n = this.findEmptyHotbarSlot()) != -1) {
            this.assignItemToHotbarSlot(entity, n);
        }
    }

    public boolean canObtainItem(Entity entity) {
        boolean bl = false;
        Entity entity2 = this.gp.eGenerator.getObject(entity.name);
        if (entity2.stackable) {
            int n = this.searchItemInInventory(entity2.name);
            if (n != 999) {
                ((Entity)this.inventory.get((int)n)).amount += entity.amount;
                bl = true;
            } else if (this.inventory.size() != 20) {
                entity2.amount = entity.amount;
                this.inventory.add(entity2);
                this.autoFillHotbar(entity2);
                bl = true;
            }
        } else if (this.inventory.size() != 20) {
            this.inventory.add(entity2);
            this.autoFillHotbar(entity2);
            bl = true;
        }
        return bl;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        BufferedImage bufferedImage = null;
        int n = this.screenX;
        int n2 = this.screenY;
        switch (this.direction) {
            case "up": {
                if (!this.attacking) {
                    if (this.spriteNum == 1) {
                        bufferedImage = this.up1;
                    } else if (this.spriteNum == 2) {
                        bufferedImage = this.up2;
                    }
                } else if (this.attacking) {
                    n2 = this.screenY - this.gp.tileSize;
                    if (this.spriteNum == 1) {
                        bufferedImage = this.attackUp1;
                    } else if (this.spriteNum == 2) {
                        bufferedImage = this.attackUp2;
                    }
                }
                if (!this.guarding || this.guardUp == null) break;
                bufferedImage = this.guardUp;
                break;
            }
            case "down": {
                if (!this.attacking) {
                    if (this.spriteNum == 1) {
                        bufferedImage = this.down1;
                    } else if (this.spriteNum == 2) {
                        bufferedImage = this.down2;
                    }
                } else if (this.attacking) {
                    if (this.spriteNum == 1) {
                        bufferedImage = this.attackDown1;
                    } else if (this.spriteNum == 2) {
                        bufferedImage = this.attackDown2;
                    }
                }
                if (!this.guarding || this.guardDown == null) break;
                bufferedImage = this.guardDown;
                break;
            }
            case "left": {
                if (!this.attacking) {
                    if (this.spriteNum == 1) {
                        bufferedImage = this.left1;
                    } else if (this.spriteNum == 2) {
                        bufferedImage = this.left2;
                    } else if (this.spriteNum == 3) {
                        bufferedImage = this.left3;
                    }
                } else if (this.attacking) {
                    n = this.screenX - this.gp.tileSize;
                    if (this.spriteNum == 1) {
                        bufferedImage = this.attackLeft1;
                    } else if (this.spriteNum == 2) {
                        bufferedImage = this.attackLeft2;
                    }
                }
                if (!this.guarding || this.guardLeft == null) break;
                bufferedImage = this.guardLeft;
                break;
            }
            case "right": {
                if (!this.attacking) {
                    if (this.spriteNum == 1) {
                        bufferedImage = this.right1;
                    } else if (this.spriteNum == 2) {
                        bufferedImage = this.right2;
                    } else if (this.spriteNum == 3) {
                        bufferedImage = this.right3;
                    }
                } else if (this.attacking) {
                    if (this.spriteNum == 1) {
                        bufferedImage = this.attackRight1;
                    } else if (this.spriteNum == 2) {
                        bufferedImage = this.attackRight2;
                    }
                }
                if (!this.guarding || this.guardRight == null) break;
                bufferedImage = this.guardRight;
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
            graphics2D.drawImage((Image)bufferedImage, n, n2, null);
        }
        graphics2D.setComposite(AlphaComposite.getInstance(3, 1.0f));
    }

    private void useHotbarItem() {
        int n = -1;
        if (this.keyH.keyPressed[49]) {
            n = 0;
            this.keyH.keyPressed[49] = false;
        } else if (this.keyH.keyPressed[50]) {
            n = 1;
            this.keyH.keyPressed[50] = false;
        } else if (this.keyH.keyPressed[51]) {
            n = 2;
            this.keyH.keyPressed[51] = false;
        } else if (this.keyH.keyPressed[52]) {
            n = 3;
            this.keyH.keyPressed[52] = false;
        } else if (this.keyH.keyPressed[53]) {
            n = 4;
            this.keyH.keyPressed[53] = false;
        }
        if (n >= 0 && n < this.hotbar.length && this.hotbar[n] != null) {
            int n2;
            Entity entity = this.hotbar[n];
            if (entity.type == 3 || entity.type == 4 || entity.type == 10) {
                this.currentWeapon = entity;
                this.defense = this.getAttack();
                this.getAttackImage();
            }
            if (entity.type == 5) {
                this.currentShield = entity;
                this.defense = this.getDefense();
                this.getGuardImage();
            }
            if (entity.type == 9) {
                if (entity.placable) {
                    entity.placeItem();
                    if (entity.amount > 1) {
                        --entity.amount;
                    } else {
                        this.hotbar[n] = null;
                        for (n2 = 0; n2 < this.inventory.size(); ++n2) {
                            if (this.inventory.get(n2) != entity) continue;
                            this.inventory.remove(n2);
                            break;
                        }
                    }
                } else {
                    this.currentLight = this.currentLight == entity ? null : entity;
                }
                this.lightUpdated = true;
            }
            if (entity.type == 6 && entity.use(this)) {
                if (entity.amount > 1) {
                    --entity.amount;
                } else {
                    this.hotbar[n] = null;
                    for (n2 = 0; n2 < this.inventory.size(); ++n2) {
                        if (this.inventory.get(n2) != entity) continue;
                        this.inventory.remove(n2);
                        break;
                    }
                }
            }
            if (entity.type == 11) {
                // empty if block
            }
        }
    }
}
