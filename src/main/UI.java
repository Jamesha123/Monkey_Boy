/*
 * Decompiled with CFR 0.152.
 */
package main;

import entity.Entity;
import entity.NPC_Mom;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import main.GamePanel;
import main.ImageCache;
import objects.OBJ_Coin_Bronze;
import objects.OBJ_Heart;
import objects.OBJ_ManaCrystal;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    BufferedImage heart_full;
    BufferedImage heart_half;
    BufferedImage heart_blank;
    BufferedImage crystal_full;
    BufferedImage crystal_blank;
    BufferedImage coin;
    public Font fontBold16;
    public Font fontBold18;
    public Font fontBold20;
    public Font fontBold22;
    public Font fontBold24;
    public Font fontBold32;
    public Font fontBold50;
    public Font fontBold100;
    public Font fontBold110;
    public Font fontPlain28;
    public Font fontPlain32;
    public Font maruMonica;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList();
    ArrayList<Integer> messageCounter = new ArrayList();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int hotbarSlot = 0;
    public boolean inHotbarMode = false;
    public boolean inHotbarAssignmentMode = false;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    int charIndex = 0;
    String combinedText = "";
    int subState = 0;
    int counter = 0;
    public Entity npc;
    private BufferedImage titleBackground;

    public UI(GamePanel gp) {
        this.gp = gp;
        try {
            InputStream is = this.getClass().getResourceAsStream("/font/Monica.ttf");
            this.maruMonica = Font.createFont(0, is);
        }
        catch (FontFormatException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.fontBold16 = this.maruMonica.deriveFont(1, 16.0f);
        this.fontBold18 = this.maruMonica.deriveFont(1, 18.0f);
        this.fontBold20 = this.maruMonica.deriveFont(1, 20.0f);
        this.fontBold22 = this.maruMonica.deriveFont(1, 22.0f);
        this.fontBold24 = this.maruMonica.deriveFont(1, 24.0f);
        this.fontBold32 = this.maruMonica.deriveFont(1, 32.0f);
        this.fontBold50 = this.maruMonica.deriveFont(1, 50.0f);
        this.fontBold100 = this.maruMonica.deriveFont(1, 100.0f);
        this.fontBold110 = this.maruMonica.deriveFont(1, 110.0f);
        this.fontPlain28 = this.maruMonica.deriveFont(0, 28.0f);
        this.fontPlain32 = this.maruMonica.deriveFont(0, 32.0f);
        OBJ_Heart heart = new OBJ_Heart(gp);
        this.heart_full = heart.image;
        this.heart_half = heart.image2;
        this.heart_blank = heart.image3;
        OBJ_ManaCrystal crystal = new OBJ_ManaCrystal(gp);
        this.crystal_full = crystal.image;
        this.crystal_blank = crystal.image2;
        OBJ_Coin_Bronze bronzeCoin = new OBJ_Coin_Bronze(gp);
        this.coin = bronzeCoin.down1;
        this.titleBackground = ImageCache.getImage("/Title Background/home", gp.screenWidth, gp.screenHeight);
    }

    public void addMessage(String text) {
        this.message.add(text);
        this.messageCounter.add(0);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(this.maruMonica);
        g2.setColor(Color.white);
        int n = this.gp.gameState;
        this.gp.getClass();
        if (n == 0) {
            this.drawTitleScreen();
        }
        int n2 = this.gp.gameState;
        this.gp.getClass();
        if (n2 == 1) {
            this.drawPlayerLife();
            this.drawMonsterLife();
            this.drawEntitiesFrozenIndicator();
            this.drawHotbar();
            this.drawDayState();
        }
        int n3 = this.gp.gameState;
        this.gp.getClass();
        if (n3 == 2) {
            this.drawDialogueScreen();
        }
        int n4 = this.gp.gameState;
        this.gp.getClass();
        if (n4 == 3) {
            this.drawCharacterScreen();
            this.drawInventory(this.gp.player, true);
            this.drawDayState();
        }
        int n5 = this.gp.gameState;
        this.gp.getClass();
        if (n5 == 4) {
            this.drawOptionsScreen();
        }
        int n6 = this.gp.gameState;
        this.gp.getClass();
        if (n6 == 5) {
            this.drawGameOverScreen();
        }
        int n7 = this.gp.gameState;
        this.gp.getClass();
        if (n7 == 6) {
            this.drawTransition();
        }
        int n8 = this.gp.gameState;
        this.gp.getClass();
        if (n8 == 11) {
            this.drawCutsceneTransitionScreen();
        }
        int n9 = this.gp.gameState;
        this.gp.getClass();
        if (n9 == 7) {
            this.drawTradeScreen();
        }
        int n10 = this.gp.gameState;
        this.gp.getClass();
        if (n10 == 8) {
            this.drawSleepScreen();
        }
        int n11 = this.gp.gameState;
        this.gp.getClass();
        if (n11 == 13 && this.gp.debugManager.characterEditor) {
            this.drawPlayerLife();
        }
        this.drawMessage();
    }

    public void drawPlayerLife() {
        int x = this.gp.tileSize / 2;
        int y = this.gp.tileSize / 2;
        int i = 0;
        int iconSize = 32;
        int manaStartX = this.gp.tileSize / 2 - 5;
        int manaStartY = 0;
        while (i < this.gp.player.maxLife / 2) {
            this.g2.drawImage(this.heart_blank, x, y, iconSize, iconSize, null);
            x += iconSize;
            manaStartY = y + 32;
            if (++i % 8 != 0) continue;
            x = this.gp.tileSize / 2;
            y += iconSize;
        }
        x = this.gp.tileSize / 2;
        y = this.gp.tileSize / 2;
        i = 0;
        while (i < this.gp.player.life) {
            this.g2.drawImage(this.heart_half, x, y, iconSize, iconSize, null);
            if (++i < this.gp.player.life) {
                this.g2.drawImage(this.heart_full, x, y, iconSize, iconSize, null);
            }
            x += iconSize;
            if (++i % 16 != 0) continue;
            x = this.gp.tileSize / 2;
            y += iconSize;
        }
        x = manaStartX;
        y = manaStartY;
        i = 0;
        while (i < this.gp.player.maxMana) {
            this.g2.drawImage(this.crystal_blank, x, y, iconSize, iconSize, null);
            x += 18;
            if (++i % 10 != 0) continue;
            x = manaStartX;
            y += iconSize;
        }
        x = this.gp.tileSize / 2 - 5;
        y = manaStartY;
        i = 0;
        while (i < this.gp.player.mana) {
            this.g2.drawImage(this.crystal_full, x, y, iconSize, iconSize, null);
            x += 18;
            if (++i % 10 != 0) continue;
            x = manaStartX;
            y += iconSize;
        }
    }

    public void drawMonsterLife() {
        int i = 0;
        while (i < this.gp.monster[1].length) {
            Entity monster = this.gp.monster[this.gp.currentMap][i];
            if (monster != null && monster.inCamera()) {
                double hpBarValue;
                double oneScale;
                if (monster.hpBarOn && !monster.boss) {
                    oneScale = (double)this.gp.tileSize / (double)monster.maxLife;
                    hpBarValue = oneScale * (double)monster.life;
                    this.g2.setColor(new Color(35, 35, 35));
                    this.g2.fillRect(monster.getScreenX() - 1, monster.getScreenY() - 16, this.gp.tileSize + 2, 12);
                    this.g2.setColor(new Color(255, 0, 30));
                    this.g2.fillRect(monster.getScreenX(), monster.getScreenY() - 15, (int)hpBarValue, 10);
                    ++monster.hpBarCounter;
                    if (monster.hpBarCounter > 600) {
                        monster.hpBarCounter = 0;
                        monster.hpBarOn = false;
                    }
                } else if (monster.boss) {
                    oneScale = (double)this.gp.tileSize * 8.0 / (double)monster.maxLife;
                    hpBarValue = oneScale * (double)monster.life;
                    int x = this.gp.screenWidth / 2 - this.gp.tileSize * 4;
                    int y = this.gp.tileSize * 10;
                    this.g2.setColor(new Color(35, 35, 35));
                    this.g2.fillRect(x - 1, y - 1, this.gp.tileSize * 8 + 2, 22);
                    if (monster.name.equals("Skeleton Lord") && monster.invincible && monster.sleep) {
                        this.g2.setColor(new Color(100, 150, 255));
                    } else {
                        this.g2.setColor(new Color(255, 0, 30));
                    }
                    this.g2.fillRect(x, y, (int)hpBarValue, 20);
                    this.g2.setFont(this.fontBold24);
                    this.g2.setColor(Color.white);
                    this.g2.drawString(monster.name, x + 4, y - 10);
                }
            }
            ++i;
        }
    }

    public void drawMessage() {
        int messageX = this.gp.tileSize / 2;
        int messageY = this.gp.screenHeight / 2 + this.gp.tileSize * 2;
        int n = this.gp.gameState;
        this.gp.getClass();
        if (n == 3) {
            messageX = this.gp.screenWidth / 2 - this.gp.tileSize * 2;
            messageY = this.gp.screenHeight - this.gp.tileSize * 2 - this.gp.tileSize / 2;
        } else if (this.gp.debugManager.debugMode) {
            messageX = this.gp.screenWidth - this.gp.tileSize * 4;
            messageY = this.gp.screenHeight / 2 + this.gp.tileSize * 3;
        }
        this.g2.setFont(this.fontBold20);
        int i = 0;
        while (i < this.messageCounter.size()) {
            this.messageCounter.set(i, this.messageCounter.get(i) + 1);
            ++i;
        }
        i = this.messageCounter.size() - 1;
        while (i >= 0) {
            if (this.messageCounter.get(i) > 180) {
                this.message.remove(i);
                this.messageCounter.remove(i);
            }
            --i;
        }
        int lineHeight = 50;
        int count = this.message.size();
        int start = Math.max(0, count - 3);
        int line = 0;
        int i2 = count - 1;
        while (i2 >= start) {
            String msg = this.message.get(i2);
            if (msg != null) {
                int y = messageY - line * 50;
                this.g2.setColor(Color.black);
                this.g2.drawString(msg, messageX + 2, y + 2);
                this.g2.setColor(Color.white);
                this.g2.drawString(msg, messageX, y);
                ++line;
            }
            --i2;
        }
    }

    public void drawTitleScreen() {
        this.g2.drawImage(this.titleBackground, 0, 0, this.gp.screenWidth, this.gp.screenHeight, null);
        this.g2.setFont(this.fontBold100);
        String text = "Monkey Boy";
        int x = this.getXforCenteredText(text);
        int y = this.gp.tileSize * 3;
        this.g2.setColor(Color.black);
        this.g2.drawString(text, x + 5, y + 5);
        this.g2.setColor(new Color(204, 85, 0));
        this.g2.drawString(text, x, y);
        x = this.gp.screenWidth / 2 - this.gp.tileSize * 2 / 2;
        this.g2.drawImage(this.gp.player.right1, x, y += this.gp.tileSize * 2, this.gp.tileSize * 2, this.gp.tileSize * 2, null);
        this.g2.setFont(this.fontBold32);
        this.g2.setColor(Color.white);
        text = "NEW GAME";
        x = this.getXforCenteredText(text);
        this.g2.drawString(text, x, y += this.gp.tileSize * 4);
        if (this.commandNum == 0) {
            this.g2.drawString(">", x - this.gp.tileSize, y);
        }
        text = "LOAD GAME";
        x = this.getXforCenteredText(text);
        this.g2.drawString(text, x, y += this.gp.tileSize);
        if (this.commandNum == 1) {
            this.g2.drawString(">", x - this.gp.tileSize, y);
        }
        text = "QUIT";
        x = this.getXforCenteredText(text);
        this.g2.drawString(text, x, y += this.gp.tileSize);
        if (this.commandNum == 2) {
            this.g2.drawString(">", x - this.gp.tileSize, y);
        }
    }

    public void drawDialogueScreen() {
        int y;
        int x;
        block18: {
            block17: {
                block20: {
                    block19: {
                        char[] characters;
                        String originalText;
                        int imageSize;
                        int imageY;
                        String currentText;
                        x = this.gp.tileSize * 3;
                        y = this.gp.tileSize / 2;
                        int width = this.gp.screenWidth - this.gp.tileSize * 6;
                        int height = this.gp.tileSize * 4;
                        this.drawSubWindow(x, y, width, height);
                        this.g2.setFont(this.fontPlain32);
                        String currentSpeaker = "UNKNOWN";
                        if (this.npc.dialogues[this.npc.dialogueSet][this.npc.dialogueIndex] != null && (currentText = this.npc.dialogues[this.npc.dialogueSet][this.npc.dialogueIndex]).contains(":") && currentText.indexOf(":") < currentText.length() - 1) {
                            currentSpeaker = currentText.substring(0, currentText.indexOf(":"));
                        }
                        if (currentSpeaker.equals("PLAYER")) {
                            int imageX = x + this.gp.tileSize / 2;
                            imageY = y + this.gp.tileSize / 2 + 20;
                            imageSize = this.gp.tileSize * 2;
                            this.g2.drawImage(this.gp.player.down1, imageX, imageY, imageSize, imageSize, null);
                            x += this.gp.tileSize * 3;
                            y += this.gp.tileSize;
                        } else if (this.npc != null && (this.npc.type == this.npc.type_npc || this.npc.type == this.npc.type_monster)) {
                            int imageX;
                            if (this.npc.name.equals("Mom") || this.npc.name.equals("Dad")) {
                                imageX = x + this.gp.tileSize / 2;
                                imageY = y + this.gp.tileSize / 4;
                                int imageSize1 = this.gp.tileSize * 2;
                                int imageSize2 = this.gp.tileSize * 3;
                                this.g2.drawImage(this.npc.down1, imageX, imageY, imageSize1, imageSize2, null);
                            } else {
                                imageX = x + this.gp.tileSize / 2;
                                imageY = y + this.gp.tileSize / 2 + 20;
                                imageSize = this.gp.tileSize * 2;
                                this.g2.drawImage(this.npc.down1, imageX, imageY, imageSize, imageSize, null);
                            }
                            x += this.gp.tileSize * 3;
                            y += this.gp.tileSize;
                        } else {
                            x += this.gp.tileSize;
                            y += this.gp.tileSize;
                        }
                        if (this.npc.dialogues[this.npc.dialogueSet][this.npc.dialogueIndex] == null) break block17;
                        String textToType = originalText = this.npc.dialogues[this.npc.dialogueSet][this.npc.dialogueIndex];
                        if (originalText.contains(":") && originalText.indexOf(":") < originalText.length() - 1) {
                            textToType = originalText.substring(originalText.indexOf(":") + 2);
                        }
                        if (this.charIndex < (characters = textToType.toCharArray()).length) {
                            this.gp.playSE(17);
                            String s = String.valueOf(characters[this.charIndex]);
                            this.combinedText = String.valueOf(this.combinedText) + s;
                            ++this.charIndex;
                        }
                        if (!this.gp.keyH.enterPressed) break block18;
                        if (this.charIndex >= characters.length) break block19;
                        this.charIndex = characters.length;
                        this.combinedText = textToType;
                        this.gp.keyH.enterPressed = false;
                        break block18;
                    }
                    this.charIndex = 0;
                    this.combinedText = "";
                    int n = this.gp.gameState;
                    this.gp.getClass();
                    if (n == 2) break block20;
                    int n2 = this.gp.gameState;
                    this.gp.getClass();
                    if (n2 != 10) break block18;
                }
                if (this.npc instanceof NPC_Mom) {
                    ((NPC_Mom)this.npc).advanceDialogue();
                } else {
                    ++this.npc.dialogueIndex;
                }
                this.gp.keyH.enterPressed = false;
                break block18;
            }
            this.npc.dialogueIndex = 0;
            int n = this.gp.gameState;
            this.gp.getClass();
            if (n == 2) {
                this.gp.getClass();
                this.gp.gameState = 1;
            }
            int n3 = this.gp.gameState;
            this.gp.getClass();
            if (n3 == 10) {
                ++this.gp.csManager.scenePhase;
            }
        }
        if (this.combinedText != null && !this.combinedText.isEmpty()) {
            String[] stringArray = this.combinedText.split("\n");
            int n = stringArray.length;
            int n4 = 0;
            while (n4 < n) {
                String line = stringArray[n4];
                this.g2.drawString(line, x, y);
                y += 40;
                ++n4;
            }
        }
    }

    public void drawCharacterScreen() {
        int frameX = this.gp.tileSize * 2;
        int frameY = this.gp.tileSize;
        int frameWidth = this.gp.tileSize * 5;
        int frameHeight = this.gp.tileSize * 10;
        this.drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        this.g2.setColor(Color.white);
        this.g2.setFont(this.fontBold32);
        int textX = frameX + 20;
        int textY = frameY + this.gp.tileSize;
        int lineHeight = 35;
        this.g2.drawString("Level", textX, textY);
        this.g2.drawString("Life", textX, textY += 35);
        this.g2.drawString("Mana", textX, textY += 35);
        this.g2.drawString("Strength", textX, textY += 35);
        this.g2.drawString("Dexterity", textX, textY += 35);
        this.g2.drawString("Attack", textX, textY += 35);
        this.g2.drawString("Defense", textX, textY += 35);
        this.g2.drawString("Exp", textX, textY += 35);
        this.g2.drawString("Next Level", textX, textY += 35);
        this.g2.drawString("Coin", textX, textY += 35);
        this.g2.drawString("Weapon", textX, textY += 45);
        this.g2.drawString("Shield", textX, textY += 50);
        textY += 35;
        int tailX = frameX + frameWidth - 10;
        textY = frameY + this.gp.tileSize;
        String value = String.valueOf(this.gp.player.level);
        textX = this.getXforAlignToRightText(value, tailX);
        this.g2.drawString(value, textX, textY);
        value = String.valueOf(this.gp.player.life + "/" + this.gp.player.maxLife);
        textX = this.getXforAlignToRightText(value, tailX);
        this.g2.drawString(value, textX, textY += 35);
        value = String.valueOf(this.gp.player.mana + "/" + this.gp.player.maxMana);
        textX = this.getXforAlignToRightText(value, tailX);
        this.g2.drawString(value, textX, textY += 35);
        value = String.valueOf(this.gp.player.strength);
        textX = this.getXforAlignToRightText(value, tailX);
        this.g2.drawString(value, textX, textY += 35);
        value = String.valueOf(this.gp.player.dexterity);
        textX = this.getXforAlignToRightText(value, tailX);
        this.g2.drawString(value, textX, textY += 35);
        value = String.valueOf(this.gp.player.attack);
        textX = this.getXforAlignToRightText(value, tailX);
        this.g2.drawString(value, textX, textY += 35);
        value = String.valueOf(this.gp.player.defense);
        textX = this.getXforAlignToRightText(value, tailX);
        this.g2.drawString(value, textX, textY += 35);
        value = String.valueOf(this.gp.player.exp);
        textX = this.getXforAlignToRightText(value, tailX);
        this.g2.drawString(value, textX, textY += 35);
        value = String.valueOf(this.gp.player.nextLevelExp);
        textX = this.getXforAlignToRightText(value, tailX);
        this.g2.drawString(value, textX, textY += 35);
        value = String.valueOf(this.gp.player.coin);
        textX = this.getXforAlignToRightText(value, tailX);
        this.g2.drawString(value, textX, textY += 35);
        textY += 35;
        if (this.gp.player.currentWeapon != null) {
            this.g2.drawImage((Image)this.gp.player.currentWeapon.down1, textX - this.gp.tileSize / 2 - this.gp.tileSize / 4, textY - 24, null);
        }
        textY += this.gp.tileSize;
        if (this.gp.player.currentShield != null) {
            this.g2.drawImage((Image)this.gp.player.currentShield.down1, textX - this.gp.tileSize / 2 - this.gp.tileSize / 4, textY - 24, null);
        }
        this.drawHotbar();
    }

    public void drawInventory(Entity entity, boolean cursor) {
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;
        if (entity == this.gp.player) {
            frameX = this.gp.tileSize * 12;
            frameY = this.gp.tileSize;
            frameWidth = this.gp.tileSize * 6;
            frameHeight = this.gp.tileSize * 5;
            slotCol = this.playerSlotCol;
            slotRow = this.playerSlotRow;
        } else {
            frameX = this.gp.tileSize * 2;
            frameY = this.gp.tileSize;
            frameWidth = this.gp.tileSize * 6;
            frameHeight = this.gp.tileSize * 5;
            slotCol = this.npcSlotCol;
            slotRow = this.npcSlotRow;
        }
        this.drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        int slotXstart = frameX + 20;
        int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = this.gp.tileSize + 3;
        int i = 0;
        while (i < entity.inventory.size()) {
            if (entity.inventory.get(i) == entity.currentWeapon || entity.inventory.get(i) == entity.currentShield || entity.inventory.get(i) == entity.currentLight) {
                this.g2.setColor(new Color(240, 190, 90));
                this.g2.fillRoundRect(slotX, slotY, this.gp.tileSize, this.gp.tileSize, 10, 10);
            }
            this.g2.drawImage((Image)entity.inventory.get((int)i).down1, slotX, slotY, null);
            if (entity == this.gp.player && entity.inventory.get((int)i).amount > 1) {
                this.g2.setFont(this.fontPlain32);
                String s = "" + entity.inventory.get((int)i).amount;
                int amountX = this.getXforAlignToRightText(s, slotX + 14);
                int amountY = slotY + this.gp.tileSize;
                this.g2.setColor(new Color(60, 60, 60));
                this.g2.drawString(s, amountX, amountY);
                this.g2.setColor(Color.white);
                this.g2.drawString(s, amountX - 3, amountY - 3);
            }
            slotX += slotSize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
            ++i;
        }
        if (cursor) {
            int safeSlotRow;
            int safeSlotCol;
            int itemIndex;
            int cursorY;
            int cursorX;
            if (entity == this.gp.player && this.inHotbarMode) {
                int hotbarX = this.gp.screenWidth - this.gp.tileSize * 5 - 20;
                int hotbarY = this.gp.screenHeight - this.gp.tileSize - 20;
                cursorX = hotbarX + (this.gp.tileSize + 2) * this.hotbarSlot;
                cursorY = hotbarY;
                this.g2.setColor(Color.YELLOW);
                this.g2.setStroke(new BasicStroke(3.0f));
                this.g2.drawRoundRect(cursorX, cursorY, this.gp.tileSize, this.gp.tileSize, 10, 10);
            } else {
                int safeSlotCol2 = Math.max(0, Math.min(slotCol, 4));
                int safeSlotRow2 = Math.max(0, Math.min(slotRow, 3));
                cursorX = slotXstart + slotSize * safeSlotCol2;
                cursorY = slotYstart + slotSize * safeSlotRow2;
                int cursorWidth = this.gp.tileSize;
                int cursorHeight = this.gp.tileSize;
                this.g2.setColor(Color.white);
                this.g2.setStroke(new BasicStroke(3.0f));
                this.g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
            }
            int dframeX = frameX;
            int dframeY = frameY + frameHeight;
            int dframeWidth = frameWidth;
            int dframeHeight = this.gp.tileSize * 3;
            int textX = dframeX + 20;
            int textY = dframeY + this.gp.tileSize;
            this.g2.setFont(this.fontPlain28);
            if (!(entity == this.gp.player && this.inHotbarMode || (itemIndex = this.getItemIndexOnSlot(safeSlotCol = Math.max(0, Math.min(slotCol, 4)), safeSlotRow = Math.max(0, Math.min(slotRow, 3)))) < 0 || itemIndex >= entity.inventory.size())) {
                this.drawSubWindow(dframeX, dframeY, dframeWidth, dframeHeight);
                String[] stringArray = entity.inventory.get((int)itemIndex).description.split("\n");
                int n = stringArray.length;
                int n2 = 0;
                while (n2 < n) {
                    String line = stringArray[n2];
                    this.g2.drawString(line, textX, textY);
                    textY += 32;
                    ++n2;
                }
            }
        }
    }

    public void drawGameOverScreen() {
        this.g2.setColor(new Color(0, 0, 0, 150));
        this.g2.fillRect(0, 0, this.gp.screenWidth, this.gp.screenHeight);
        this.g2.setFont(this.fontBold110);
        String text = "Game Over";
        this.g2.setColor(Color.black);
        int x = this.getXforCenteredText(text);
        int y = this.gp.tileSize * 4;
        this.g2.drawString(text, x, y);
        this.g2.setColor(Color.white);
        this.g2.drawString(text, x - 4, y - 4);
        this.g2.setFont(this.fontBold50);
        text = "Retry";
        x = this.getXforCenteredText(text);
        this.g2.drawString(text, x, y += this.gp.tileSize * 4);
        if (this.commandNum == 0) {
            this.g2.drawString(">", x - 40, y);
        }
        text = "Quit";
        x = this.getXforCenteredText(text);
        this.g2.drawString(text, x, y += 55);
        if (this.commandNum == 1) {
            this.g2.drawString(">", x - 40, y);
        }
    }

    public void drawOptionsScreen() {
        this.g2.setColor(Color.white);
        this.g2.setFont(this.fontPlain32);
        int frameX = this.gp.tileSize * 6;
        int frameY = this.gp.tileSize;
        int frameWidth = this.gp.tileSize * 8;
        int frameHeight = this.gp.tileSize * 10;
        this.drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        switch (this.subState) {
            case 0: {
                this.options_top(frameX, frameY);
                break;
            }
            case 1: {
                this.options_fullScreenNotification(frameX, frameY);
                break;
            }
            case 2: {
                this.options_control(frameX, frameY);
                break;
            }
            case 3: {
                this.options_endGameConfirmation(frameX, frameY);
                break;
            }
            case 4: {
                this.options_saveConfirmation(frameX, frameY);
            }
        }
        this.gp.keyH.enterPressed = false;
    }

    public void options_top(int frameX, int frameY) {
        String text = "Options";
        int textX = this.getXforCenteredText(text);
        int textY = frameY + this.gp.tileSize;
        this.g2.drawString(text, textX, textY);
        textX = frameX + this.gp.tileSize;
        textY = (int)((double)textY + (double)this.gp.tileSize * 1.5);
        this.g2.drawString("Full Screen", textX, textY);
        if (this.commandNum == 0) {
            this.g2.drawString(">", textX - 25, textY);
            if (this.gp.keyH.enterPressed) {
                if (!this.gp.fullScreenOn) {
                    this.gp.fullScreenOn = true;
                } else if (this.gp.fullScreenOn) {
                    this.gp.fullScreenOn = false;
                }
                this.subState = 1;
            }
        }
        this.g2.drawString("Music", textX, textY += this.gp.tileSize);
        if (this.commandNum == 1) {
            this.g2.drawString(">", textX - 25, textY);
        }
        this.g2.drawString("SE", textX, textY += this.gp.tileSize);
        if (this.commandNum == 2) {
            this.g2.drawString(">", textX - 25, textY);
        }
        this.g2.drawString("Control", textX, textY += this.gp.tileSize);
        if (this.commandNum == 3) {
            this.g2.drawString(">", textX - 25, textY);
            if (this.gp.keyH.enterPressed) {
                this.subState = 2;
                this.commandNum = 0;
            }
        }
        this.g2.drawString("Save Game", textX, textY += this.gp.tileSize);
        if (this.commandNum == 4) {
            this.g2.drawString(">", textX - 25, textY);
            if (this.gp.keyH.enterPressed) {
                if (this.gp.bossBattleOn) {
                    this.gp.ui.addMessage("Cannot save during boss battle!");
                } else {
                    this.subState = 4;
                    this.commandNum = 0;
                }
                this.gp.keyH.enterPressed = false;
            }
        }
        this.g2.drawString("End Game", textX, textY += this.gp.tileSize);
        if (this.commandNum == 5) {
            this.g2.drawString(">", textX - 25, textY);
            if (this.gp.keyH.enterPressed) {
                this.subState = 3;
                this.commandNum = 0;
            }
        }
        textY = (int)((double)textY + (double)this.gp.tileSize * 1.5);
        this.g2.drawString("Back", textX, textY);
        if (this.commandNum == 6) {
            this.g2.drawString(">", textX - 25, textY);
            if (this.gp.keyH.enterPressed) {
                this.gp.getClass();
                this.gp.gameState = 1;
                this.commandNum = 0;
            }
        }
        textX = frameX + (int)((double)this.gp.tileSize * 4.5);
        textY = frameY + (int)((double)this.gp.tileSize * 1.5) + 24;
        this.g2.setStroke(new BasicStroke(3.0f));
        this.g2.drawRect(textX, textY, 24, 24);
        if (this.gp.fullScreenOn) {
            this.g2.fillRect(textX, textY, 24, 24);
        }
        this.g2.drawRect(textX, textY += this.gp.tileSize, 120, 24);
        int volumeWidth = 24 * this.gp.music.volumeScale;
        this.g2.fillRect(textX, textY, volumeWidth, 24);
        this.g2.drawRect(textX, textY += this.gp.tileSize, 120, 24);
        volumeWidth = 24 * this.gp.se.volumeScale;
        this.g2.fillRect(textX, textY, volumeWidth, 24);
        this.gp.config.saveConfig();
    }

    public void options_fullScreenNotification(int frameX, int frameY) {
        int textX = frameX + this.gp.tileSize;
        int textY = frameY + this.gp.tileSize * 3;
        this.currentDialogue = "The change will take \neffect after restarting \nthe game.";
        String[] stringArray = this.currentDialogue.split("\n");
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String line = stringArray[n2];
            this.g2.drawString(line, textX, textY);
            textY += 40;
            ++n2;
        }
        textY = frameY + this.gp.tileSize * 9;
        this.g2.drawString("Back", textX, textY);
        if (this.commandNum == 0) {
            this.g2.drawString(">", textX - 25, textY);
            if (this.gp.keyH.enterPressed) {
                this.subState = 0;
            }
        }
    }

    public void options_control(int frameX, int frameY) {
        int lineHeight = (int)((double)this.gp.tileSize * 0.75);
        String text = "Control";
        int textX = this.getXforCenteredText(text);
        int textY = frameY + this.gp.tileSize;
        this.g2.drawString(text, textX, textY);
        textX = frameX + this.gp.tileSize;
        this.g2.drawString("Move", textX, textY += this.gp.tileSize);
        this.g2.drawString("Confirm/Attack", textX, textY += lineHeight);
        this.g2.drawString("Shoot/Cast", textX, textY += lineHeight);
        this.g2.drawString("Guard/Block", textX, textY += lineHeight);
        this.g2.drawString("Character Screen", textX, textY += lineHeight);
        this.g2.drawString("Map Screen", textX, textY += lineHeight);
        this.g2.drawString("Mini Map Toggle", textX, textY += lineHeight);
        this.g2.drawString("Drop Item", textX, textY += lineHeight);
        this.g2.drawString("Options", textX, textY += lineHeight);
        textY += lineHeight;
        textX = frameX + this.gp.tileSize * 6;
        textY = frameY + this.gp.tileSize * 2;
        this.g2.drawString("WASD", textX, textY);
        this.g2.drawString("ENTER", textX, textY += lineHeight);
        this.g2.drawString("F", textX, textY += lineHeight);
        this.g2.drawString("SPACE", textX, textY += lineHeight);
        this.g2.drawString("C", textX, textY += lineHeight);
        this.g2.drawString("M", textX, textY += lineHeight);
        this.g2.drawString("X", textX, textY += lineHeight);
        this.g2.drawString("V", textX, textY += lineHeight);
        this.g2.drawString("ESC", textX, textY += lineHeight);
        textY += lineHeight;
        textX = frameX + this.gp.tileSize;
        textY = frameY + this.gp.tileSize * 9;
        this.g2.drawString("Back", textX, textY);
        if (this.commandNum == 0) {
            this.g2.drawString(">", textX - 25, textY);
            if (this.gp.keyH.enterPressed) {
                this.subState = 0;
            }
        }
    }

    public void options_endGameConfirmation(int frameX, int frameY) {
        int textX = frameX + this.gp.tileSize;
        int textY = frameY + this.gp.tileSize * 3;
        this.currentDialogue = "Quit the game and \nreturn to the title screen?";
        String[] stringArray = this.currentDialogue.split("\n");
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String line = stringArray[n2];
            this.g2.drawString(line, textX, textY);
            textY += 40;
            ++n2;
        }
        String text = "Yes";
        textX = this.getXforCenteredText(text);
        this.g2.drawString(text, textX, textY += this.gp.tileSize * 3);
        if (this.commandNum == 0) {
            this.g2.drawString(">", textX - 25, textY);
            if (this.gp.keyH.enterPressed) {
                this.subState = 0;
                this.gp.getClass();
                this.gp.gameState = 0;
                this.gp.restart();
            }
        }
        text = "No";
        textX = this.getXforCenteredText(text);
        this.g2.drawString(text, textX, textY += this.gp.tileSize);
        if (this.commandNum == 1) {
            this.g2.drawString(">", textX - 25, textY);
            if (this.gp.keyH.enterPressed) {
                this.subState = 0;
                this.commandNum = 3;
            }
        }
    }

    public void options_saveConfirmation(int frameX, int frameY) {
        int textX = frameX + this.gp.tileSize;
        int textY = frameY + this.gp.tileSize * 3;
        this.currentDialogue = "Save current progress?";
        String[] stringArray = this.currentDialogue.split("\n");
        int n = stringArray.length;
        int n2 = 0;
        while (n2 < n) {
            String line = stringArray[n2];
            this.g2.drawString(line, textX, textY);
            textY += 40;
            ++n2;
        }
        String text = "Yes";
        textX = this.getXforCenteredText(text);
        this.g2.drawString(text, textX, textY += this.gp.tileSize * 3);
        if (this.commandNum == 0) {
            this.g2.drawString(">", textX - 25, textY);
            if (this.gp.keyH.enterPressed) {
                this.gp.saveLoad.save();
                this.gp.ui.addMessage("Game Saved!");
                this.subState = 0;
                this.commandNum = 4;
            }
        }
        text = "No";
        textX = this.getXforCenteredText(text);
        this.g2.drawString(text, textX, textY += this.gp.tileSize);
        if (this.commandNum == 1) {
            this.g2.drawString(">", textX - 25, textY);
            if (this.gp.keyH.enterPressed) {
                this.subState = 0;
                this.commandNum = 4;
            }
        }
    }

    public void drawTransition() {
        ++this.counter;
        this.g2.setColor(new Color(0, 0, 0, this.counter * 5));
        this.g2.fillRect(0, 0, this.gp.screenWidth, this.gp.screenHeight);
        if (this.counter == 50) {
            this.counter = 0;
            this.gp.getClass();
            this.gp.gameState = 1;
            this.gp.previousMap = this.gp.currentMap;
            this.gp.currentMap = this.gp.eHandler.tempMap;
            this.gp.player.worldX = this.gp.tileSize * this.gp.eHandler.tempCol;
            this.gp.player.worldY = this.gp.tileSize * this.gp.eHandler.tempRow;
            this.gp.eHandler.previousEventX = this.gp.player.worldX;
            this.gp.eHandler.previousEventY = this.gp.player.worldY;
            this.gp.changeArea();
        }
    }

    public void drawCutsceneTransitionScreen() {
        ++this.counter;
        if (this.counter < 60) {
            this.g2.setColor(new Color(0, 0, 0, this.counter * 4));
        } else if (this.counter < 360) {
            this.g2.setColor(new Color(0.0f, 0.0f, 0.0f, 1.0f));
        } else {
            this.g2.setColor(new Color(0, 0, 0, 616 - (this.counter + 60)));
            if (this.counter == 540) {
                this.counter = 0;
            }
        }
        this.g2.fillRect(0, 0, this.gp.screenWidth, this.gp.screenHeight);
    }

    public void drawTradeScreen() {
        switch (this.subState) {
            case 0: {
                this.trade_select();
                break;
            }
            case 1: {
                this.trade_buy();
                break;
            }
            case 2: {
                this.trade_sell();
            }
        }
        this.gp.keyH.enterPressed = false;
    }

    public void drawSleepScreen() {
        ++this.counter;
        if (this.counter < 120) {
            this.gp.eManager.lighting.filterAlpha += 0.01f;
            if (this.gp.eManager.lighting.filterAlpha > 1.0f) {
                this.gp.eManager.lighting.filterAlpha = 1.0f;
            }
        }
        if (this.counter >= 120) {
            this.gp.eManager.lighting.filterAlpha -= 0.01f;
            if (this.gp.eManager.lighting.filterAlpha <= 0.0f) {
                this.gp.eManager.lighting.filterAlpha = 0.0f;
                this.counter = 0;
                this.gp.eManager.lighting.dayState = this.gp.eManager.lighting.day;
                this.gp.eManager.lighting.dayCounter = 0;
                this.gp.getClass();
                this.gp.gameState = 1;
                this.gp.player.getImage();
            }
        }
    }

    public void trade_select() {
        this.npc.dialogueSet = 0;
        this.drawDialogueScreen();
        int x = this.gp.tileSize * 15;
        int y = this.gp.tileSize * 4;
        int width = this.gp.tileSize * 3;
        int height = (int)((double)this.gp.tileSize * 3.5);
        this.drawSubWindow(x, y, width, height);
        this.g2.drawString("Buy", x += this.gp.tileSize, y += this.gp.tileSize);
        if (this.commandNum == 0) {
            this.g2.drawString(">", x - 24, y);
            if (this.gp.keyH.enterPressed) {
                this.subState = 1;
            }
        }
        this.g2.drawString("Sell", x, y += this.gp.tileSize);
        if (this.commandNum == 1) {
            this.g2.drawString(">", x - 24, y);
            if (this.gp.keyH.enterPressed) {
                this.subState = 2;
            }
        }
        this.g2.drawString("Leave", x, y += this.gp.tileSize);
        if (this.commandNum == 2) {
            this.g2.drawString(">", x - 24, y);
            if (this.gp.keyH.enterPressed) {
                this.commandNum = 0;
                this.npc.startDialogue(this.npc, 1);
            }
        }
    }

    public void trade_buy() {
        this.drawInventory(this.gp.player, false);
        this.drawInventory(this.npc, true);
        int x = this.gp.tileSize * 2;
        int y = this.gp.tileSize * 9;
        int width = this.gp.tileSize * 6;
        int height = this.gp.tileSize * 2;
        this.drawSubWindow(x, y, width, height);
        this.g2.drawString("[ESC] Back", x + 24, y + 60);
        x = this.gp.tileSize * 12;
        y = this.gp.tileSize * 9;
        width = this.gp.tileSize * 6;
        height = this.gp.tileSize * 2;
        this.drawSubWindow(x, y, width, height);
        this.g2.drawString("Your Coins: " + this.gp.player.coin, x + 24, y + 60);
        int itemIndex = this.getItemIndexOnSlot(this.npcSlotCol, this.npcSlotRow);
        if (itemIndex < this.npc.inventory.size()) {
            x = (int)((double)this.gp.tileSize * 5.5);
            y = (int)((double)this.gp.tileSize * 5.5);
            width = (int)((double)this.gp.tileSize * 2.5);
            height = this.gp.tileSize;
            this.drawSubWindow(x, y, width, height);
            this.g2.drawImage(this.coin, x + 10, y + 8, 32, 32, null);
            int price = this.npc.inventory.get((int)itemIndex).price;
            String text = "" + price;
            x = this.getXforAlignToRightText(text, this.gp.tileSize * 8 - 20);
            this.g2.drawString(text, x, y + 34);
            if (this.gp.keyH.enterPressed) {
                if (this.npc.inventory.get((int)itemIndex).price > this.gp.player.coin) {
                    this.subState = 0;
                    this.npc.startDialogue(this.npc, 2);
                } else if (this.gp.player.canObtainItem(this.npc.inventory.get(itemIndex))) {
                    this.gp.player.coin -= this.npc.inventory.get((int)itemIndex).price;
                    if (this.npc.inventory.get((int)itemIndex).name == "Sugar") {
                        this.npc.inventory.remove(itemIndex);
                        this.gp.sugarPurchased = true;
                        this.npc.startDialogue(this.npc, 5);
                    }
                } else {
                    this.subState = 0;
                    this.npc.startDialogue(this.npc, 3);
                }
            }
        }
    }

    public void trade_sell() {
        this.drawInventory(this.gp.player, true);
        int x = this.gp.tileSize * 2;
        int y = this.gp.tileSize * 9;
        int width = this.gp.tileSize * 6;
        int height = this.gp.tileSize * 2;
        this.drawSubWindow(x, y, width, height);
        this.g2.drawString("[ESC] Back", x + 24, y + 60);
        x = this.gp.tileSize * 12;
        y = this.gp.tileSize * 9;
        width = this.gp.tileSize * 6;
        height = this.gp.tileSize * 2;
        this.drawSubWindow(x, y, width, height);
        this.g2.drawString("Your Coins: " + this.gp.player.coin, x + 24, y + 60);
        int itemIndex = this.getItemIndexOnSlot(this.playerSlotCol, this.playerSlotRow);
        if (itemIndex < this.gp.player.inventory.size()) {
            x = (int)((double)this.gp.tileSize * 15.5);
            y = (int)((double)this.gp.tileSize * 5.5);
            width = (int)((double)this.gp.tileSize * 2.5);
            height = this.gp.tileSize;
            this.drawSubWindow(x, y, width, height);
            this.g2.drawImage(this.coin, x + 10, y + 8, 32, 32, null);
            int price = ((Entity)this.gp.player.inventory.get((int)itemIndex)).price / 2;
            String text = "" + price;
            x = this.getXforAlignToRightText(text, this.gp.tileSize * 18 - 20);
            this.g2.drawString(text, x, y + 34);
            if (this.gp.keyH.enterPressed) {
                if (this.gp.player.inventory.get(itemIndex) == this.gp.player.currentWeapon || this.gp.player.inventory.get(itemIndex) == this.gp.player.currentShield) {
                    this.commandNum = 0;
                    this.subState = 0;
                    this.npc.startDialogue(this.npc, 4);
                } else if (((Entity)this.gp.player.inventory.get((int)itemIndex)).name == "Sugar") {
                    this.commandNum = 0;
                    this.subState = 0;
                    this.npc.startDialogue(this.npc, 6);
                } else {
                    if (((Entity)this.gp.player.inventory.get((int)itemIndex)).amount > 1) {
                        --((Entity)this.gp.player.inventory.get((int)itemIndex)).amount;
                    } else {
                        this.gp.player.inventory.remove(itemIndex);
                    }
                    this.gp.player.coin += price;
                }
            }
        }
    }

    public int getItemIndexOnSlot(int slotCol, int slotRow) {
        int itemIndex = slotCol + slotRow * 5;
        return itemIndex;
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        this.g2.setColor(c);
        this.g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255);
        this.g2.setColor(c);
        this.g2.setStroke(new BasicStroke(5.0f));
        this.g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXforCenteredText(String text) {
        int length = (int)this.g2.getFontMetrics().getStringBounds(text, this.g2).getWidth();
        int x = this.gp.screenWidth / 2 - length / 2;
        return x;
    }

    public int getXforAlignToRightText(String text, int tailX) {
        int length = (int)this.g2.getFontMetrics().getStringBounds(text, this.g2).getWidth();
        int x = tailX - length;
        return x;
    }

    public void drawEntitiesFrozenIndicator() {
        if (this.gp.entitiesFrozen) {
            this.g2.setFont(this.fontBold24);
            this.g2.setColor(Color.red);
            String text = "ENTITIES FROZEN";
            int x = this.getXforCenteredText(text);
            int y = this.gp.tileSize * 2;
            this.g2.drawString(text, x, y);
        }
    }

    public void drawHotbar() {
        int hotbarX = this.gp.screenWidth - this.gp.tileSize * 5 - 20;
        int hotbarY = this.gp.screenHeight - this.gp.tileSize - 20;
        int slotX = hotbarX;
        int slotY = hotbarY;
        int slotSize = this.gp.tileSize;
        int i = 0;
        while (i < 5) {
            this.g2.setColor(new Color(0, 0, 0, 220));
            this.g2.fillRoundRect(slotX, slotY, slotSize, slotSize, 10, 10);
            if (this.inHotbarAssignmentMode && i == this.hotbarSlot) {
                this.g2.setColor(Color.YELLOW);
                this.g2.setStroke(new BasicStroke(3.0f));
            } else if (this.gp.player.hotbar[i] != null && (this.gp.player.hotbar[i] == this.gp.player.currentWeapon || this.gp.player.hotbar[i] == this.gp.player.currentShield || this.gp.player.hotbar[i] == this.gp.player.currentLight)) {
                this.g2.setColor(new Color(240, 190, 90));
                this.g2.setStroke(new BasicStroke(3.0f));
            } else {
                this.g2.setColor(Color.WHITE);
                this.g2.setStroke(new BasicStroke(2.0f));
            }
            this.g2.drawRoundRect(slotX, slotY, slotSize, slotSize, 10, 10);
            if (this.gp.player.hotbar[i] != null) {
                Entity item = this.gp.player.hotbar[i];
                int scaledSize = (int)((double)this.gp.tileSize * 0.75);
                int offsetX = (this.gp.tileSize - scaledSize) / 2;
                int offsetY = (this.gp.tileSize - scaledSize) / 2;
                this.g2.drawImage(item.down1, slotX + offsetX, slotY + offsetY, scaledSize, scaledSize, null);
                if (item.stackable && item.amount > 1) {
                    this.g2.setFont(this.fontBold20);
                    String amountText = "" + item.amount;
                    int amountX = slotX + slotSize - 18;
                    if (item.amount < 10) {
                        amountX = slotX + slotSize - 8;
                    }
                    int amountY = slotY + slotSize - 5;
                    this.g2.setColor(new Color(60, 60, 60));
                    this.g2.drawString(amountText, amountX - 2, amountY - 2);
                    this.g2.setColor(Color.white);
                    this.g2.drawString(amountText, amountX - 4, amountY - 4);
                }
            }
            this.g2.setColor(Color.YELLOW);
            this.g2.setFont(this.fontBold16);
            this.g2.drawString("" + (i + 1), slotX + this.gp.tileSize - 10, slotY + 16);
            slotX += slotSize + 2;
            ++i;
        }
    }

    public void drawDayState() {
        block9: {
            block8: {
                int n = this.gp.gameState;
                this.gp.getClass();
                if (n == 2) break block8;
                int n2 = this.gp.gameState;
                this.gp.getClass();
                if (n2 == 10) break block8;
                int n3 = this.gp.gameState;
                this.gp.getClass();
                if (n3 != 11) break block9;
            }
            return;
        }
        String situation = "";
        switch (this.gp.eManager.lighting.dayState) {
            case 0: {
                situation = "Day";
                break;
            }
            case 1: {
                situation = "Dusk";
                break;
            }
            case 2: {
                situation = "Night";
                break;
            }
            case 3: {
                situation = "Dawn";
            }
        }
        this.g2.setColor(Color.white);
        this.g2.setFont(this.fontBold20);
        this.g2.drawString(situation, 920, 500);
    }
}
