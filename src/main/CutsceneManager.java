/*
 * Decompiled with CFR 0.152.
 */
package main;

import entity.Entity;
import entity.NPC_OldMan;
import entity.PlayerDummy;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Objects;
import main.GamePanel;
import main.UI;
import monsters.OrcDummy;
import objects.OBJ_Door_Iron;
import tile_interactive.IT_Bed;

public class CutsceneManager {
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    public final int NA = 0;
    public final int orcRobbery = 1;
    public final int slimeBoss = 2;
    public final int skeletonLord = 3;
    public final int endScene = 4;
    int orcTop = 0;
    int orcLeft = 0;
    int orcLeader = 0;
    int counter = 0;
    int dummyPlayer = 0;
    float alpha = 0.0f;
    int y = 0;
    String endCredit = "";

    public CutsceneManager(GamePanel gamePanel) {
        this.gp = gamePanel;
        this.endCredit = "Passion Project by\nJames\n\n\n\n\n\n\n\n\n\n\n\n\nArt & Development by James\n\nProgramming by James\n\nMusic by James\n\nSound Effects by James\n\nSpecial Thanks to RyiSnow\n\n\n\n\n\n\n\nThank you for playing!";
    }

    public void draw(Graphics2D graphics2D) {
        this.g2 = graphics2D;
        switch (this.sceneNum) {
            case 1: {
                this.scene_orcRobbery();
                break;
            }
            case 2: {
                this.scene_slimeBoss();
                break;
            }
            case 3: {
                this.scene_skeletonLord();
                break;
            }
            case 4: {
                this.endScene();
            }
        }
    }

    public void scene_orcRobbery() {
        if (this.scenePhase == 0) {
            int orcCount = 0;
            int slotIndex = 0;
            while (true) {
                Entity[][] entityArray = this.gp.npc;
                Objects.requireNonNull(this.gp);
                if (slotIndex >= entityArray[5].length) break;
                Entity[][] entityArray2 = this.gp.npc;
                Objects.requireNonNull(this.gp);
                if (entityArray2[5][slotIndex].name.equals(NPC_OldMan.npcName)) {
                    Entity[][] entityArray3 = this.gp.npc;
                    Objects.requireNonNull(this.gp);
                    entityArray3[5][slotIndex] = null;
                    break;
                }
                ++slotIndex;
            }
            slotIndex = 0;
            while (true) {
                Entity[][] entityArray = this.gp.npc;
                Objects.requireNonNull(this.gp);
                if (slotIndex >= entityArray[4].length || orcCount >= 3) break;
                Entity[][] entityArray4 = this.gp.npc;
                Objects.requireNonNull(this.gp);
                if (entityArray4[4][slotIndex] == null) {
                    Entity[][] entityArray5 = this.gp.npc;
                    Objects.requireNonNull(this.gp);
                    entityArray5[4][slotIndex] = new OrcDummy(this.gp);
                    switch (orcCount) {
                        case 0: {
                            Entity[][] entityArray6 = this.gp.npc;
                            Objects.requireNonNull(this.gp);
                            entityArray6[4][slotIndex].worldX = this.gp.tileSize * 18;
                            Entity[][] entityArray7 = this.gp.npc;
                            Objects.requireNonNull(this.gp);
                            entityArray7[4][slotIndex].worldY = this.gp.tileSize * 37;
                            Entity[][] entityArray8 = this.gp.npc;
                            Objects.requireNonNull(this.gp);
                            entityArray8[4][slotIndex].direction = "right";
                            this.orcLeader = slotIndex;
                            break;
                        }
                        case 1: {
                            Entity[][] entityArray9 = this.gp.npc;
                            Objects.requireNonNull(this.gp);
                            entityArray9[4][slotIndex].worldX = this.gp.tileSize * 19;
                            Entity[][] entityArray10 = this.gp.npc;
                            Objects.requireNonNull(this.gp);
                            entityArray10[4][slotIndex].worldY = this.gp.tileSize * 36;
                            Entity[][] entityArray11 = this.gp.npc;
                            Objects.requireNonNull(this.gp);
                            entityArray11[4][slotIndex].direction = "right";
                            break;
                        }
                        case 2: {
                            Entity[][] entityArray12 = this.gp.npc;
                            Objects.requireNonNull(this.gp);
                            entityArray12[4][slotIndex].worldX = this.gp.tileSize * 19;
                            Entity[][] entityArray13 = this.gp.npc;
                            Objects.requireNonNull(this.gp);
                            entityArray13[4][slotIndex].worldY = this.gp.tileSize * 38;
                            Entity[][] entityArray14 = this.gp.npc;
                            Objects.requireNonNull(this.gp);
                            entityArray14[4][slotIndex].direction = "right";
                        }
                    }
                    ++orcCount;
                }
                ++slotIndex;
            }
            ++this.scenePhase;
        } else if (this.scenePhase == 1) {
            this.gp.player.worldX -= 2;
            this.animateEntity(this.gp.player);
            if (this.gp.player.worldX < this.gp.tileSize * 21) {
                this.gp.player.spriteNum = 1;
                ++this.scenePhase;
            }
        } else if (this.scenePhase == 2) {
            int orcCount = 0;
            int slotIndex = 0;
            while (true) {
                Entity[][] entityArray = this.gp.npc;
                Objects.requireNonNull(this.gp);
                if (slotIndex >= entityArray[4].length || orcCount >= 2) break;
                Entity[][] entityArray15 = this.gp.npc;
                Objects.requireNonNull(this.gp);
                if (entityArray15[4][slotIndex] == null) {
                    Entity[][] entityArray16 = this.gp.npc;
                    Objects.requireNonNull(this.gp);
                    entityArray16[4][slotIndex] = new OrcDummy(this.gp);
                    switch (orcCount) {
                        case 0: {
                            Entity[][] entityArray17 = this.gp.npc;
                            Objects.requireNonNull(this.gp);
                            entityArray17[4][slotIndex].worldX = this.gp.tileSize * 20;
                            Entity[][] entityArray18 = this.gp.npc;
                            Objects.requireNonNull(this.gp);
                            entityArray18[4][slotIndex].worldY = this.gp.tileSize * 25;
                            Entity[][] entityArray19 = this.gp.npc;
                            Objects.requireNonNull(this.gp);
                            entityArray19[4][slotIndex].direction = "down";
                            this.orcTop = slotIndex;
                            break;
                        }
                        case 1: {
                            Entity[][] entityArray20 = this.gp.npc;
                            Objects.requireNonNull(this.gp);
                            entityArray20[4][slotIndex].worldX = this.gp.tileSize * 33;
                            Entity[][] entityArray21 = this.gp.npc;
                            Objects.requireNonNull(this.gp);
                            entityArray21[4][slotIndex].worldY = this.gp.tileSize * 37;
                            Entity[][] entityArray22 = this.gp.npc;
                            Objects.requireNonNull(this.gp);
                            entityArray22[4][slotIndex].direction = "left";
                            this.orcLeft = slotIndex;
                        }
                    }
                    ++orcCount;
                }
                ++slotIndex;
            }
            ++this.scenePhase;
        } else if (this.scenePhase == 3) {
            Entity[][] entityArray = this.gp.npc;
            Objects.requireNonNull(this.gp);
            entityArray[4][this.orcTop].worldY += 3;
            Entity[][] entityArray23 = this.gp.npc;
            Objects.requireNonNull(this.gp);
            entityArray23[4][this.orcLeft].worldX -= 3;
            Entity[][] entityArray24 = this.gp.npc;
            Objects.requireNonNull(this.gp);
            this.animateEntity(entityArray24[4][this.orcTop]);
            Entity[][] entityArray25 = this.gp.npc;
            Objects.requireNonNull(this.gp);
            this.animateEntity(entityArray25[4][this.orcLeft]);
            Entity[][] entityArray26 = this.gp.npc;
            Objects.requireNonNull(this.gp);
            if (entityArray26[4][this.orcTop].worldY > this.gp.tileSize * 35) {
                ++this.scenePhase;
            }
        } else if (this.scenePhase == 4) {
            UI ui = this.gp.ui;
            Entity[][] entityArray = this.gp.npc;
            Objects.requireNonNull(this.gp);
            ui.npc = entityArray[4][this.orcTop];
            this.gp.ui.drawDialogueScreen();
        } else if (this.scenePhase == 5) {
            --this.gp.player.worldX;
            Entity[][] entityArray = this.gp.npc;
            Objects.requireNonNull(this.gp);
            ++entityArray[4][this.orcLeader].worldX;
            Entity[][] entityArray27 = this.gp.npc;
            Objects.requireNonNull(this.gp);
            ++entityArray27[4][this.orcTop].worldY;
            Entity[][] entityArray28 = this.gp.npc;
            Objects.requireNonNull(this.gp);
            --entityArray28[4][this.orcLeft].worldX;
            Entity[][] entityArray29 = this.gp.npc;
            Objects.requireNonNull(this.gp);
            this.animateEntity(entityArray29[4][this.orcLeader]);
            Entity[][] entityArray30 = this.gp.npc;
            Objects.requireNonNull(this.gp);
            this.animateEntity(entityArray30[4][this.orcTop]);
            Entity[][] entityArray31 = this.gp.npc;
            Objects.requireNonNull(this.gp);
            this.animateEntity(entityArray31[4][this.orcLeft]);
            this.animateEntity(this.gp.player);
            if (this.gp.player.worldX < this.gp.tileSize * 20) {
                ++this.scenePhase;
            }
        } else if (this.scenePhase == 6) {
            if (this.counter == 0) {
                this.gp.playSE(7);
            }
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 11;
            if (this.counter % 90 == 0 && this.counter < 240) {
                this.gp.playSE(6);
            }
            if (this.counter == 330) {
                int i;
                for (i = 0; i < this.gp.npc[1].length; ++i) {
                    if (this.gp.npc[this.gp.currentMap][i] == null || !this.gp.npc[this.gp.currentMap][i].name.equals("OrcDummy")) continue;
                    this.gp.npc[this.gp.currentMap][i] = null;
                }
                Objects.requireNonNull(this.gp);
                this.gp.currentMap = 7;
                this.gp.player.setDefaultPositions(this.gp.currentMap);
                for (i = 0; i < this.gp.npc[1].length; ++i) {
                    if (this.gp.npc[this.gp.currentMap][i] != null) continue;
                    this.gp.npc[this.gp.currentMap][i] = new PlayerDummy(this.gp);
                    this.gp.npc[this.gp.currentMap][i].worldX = this.gp.player.worldX;
                    this.gp.npc[this.gp.currentMap][i].worldY = this.gp.player.worldY;
                    this.gp.npc[this.gp.currentMap][i].direction = this.gp.player.direction;
                    this.dummyPlayer = i;
                    break;
                }
            }
            if (this.counter > 540) {
                ++this.scenePhase;
                this.counter = 0;
                Objects.requireNonNull(this.gp);
                this.gp.gameState = 10;
            }
            ++this.counter;
        } else if (this.scenePhase == 7) {
            this.gp.player.life = this.gp.player.maxLife;
            this.gp.player.inventory.remove(this.gp.player.searchItemInInventory("Sugar"));
            ++this.scenePhase;
        } else if (this.scenePhase == 8) {
            UI ui = this.gp.ui;
            Entity[][] entityArray = this.gp.npc;
            Objects.requireNonNull(this.gp);
            ui.npc = entityArray[7][this.dummyPlayer];
            this.gp.ui.drawDialogueScreen();
        } else if (this.scenePhase == 9) {
            for (int i = 0; i < this.gp.npc[1].length; ++i) {
                if (this.gp.npc[this.gp.currentMap][i] == null || !this.gp.npc[this.gp.currentMap][i].name.equals("PlayerDummy")) continue;
                this.gp.npc[this.gp.currentMap][i] = null;
                break;
            }
            this.sceneNum = 0;
            this.scenePhase = 0;
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 1;
            this.gp.orcRobbedPlayer = true;
            this.gp.stopMusic();
            this.gp.playMusic(18);
        }
    }

    public void scene_slimeBoss() {
        if (this.scenePhase == 0) {
            this.gp.stopMusic();
            this.gp.bossBattleOn = true;
            for (int i = 0; i < this.gp.npc[1].length; ++i) {
                if (this.gp.npc[this.gp.currentMap][i] != null) continue;
                this.gp.npc[this.gp.currentMap][i] = new PlayerDummy(this.gp);
                this.gp.npc[this.gp.currentMap][i].worldX = this.gp.player.worldX;
                this.gp.npc[this.gp.currentMap][i].worldY = this.gp.player.worldY;
                this.gp.npc[this.gp.currentMap][i].direction = this.gp.player.direction;
                break;
            }
            this.gp.player.drawing = false;
            ++this.scenePhase;
        } else if (this.scenePhase == 1) {
            this.gp.player.worldY += 2;
            if (this.gp.player.worldY > this.gp.tileSize * 21) {
                ++this.scenePhase;
            }
        } else if (this.scenePhase == 2) {
            for (int i = 0; i < this.gp.monster[1].length; ++i) {
                if (this.gp.monster[this.gp.currentMap][i] == null || this.gp.monster[this.gp.currentMap][i].name != "Slime Boss") continue;
                this.gp.monster[this.gp.currentMap][i].sleep = false;
                this.gp.ui.npc = this.gp.monster[this.gp.currentMap][i];
                ++this.scenePhase;
                break;
            }
        } else if (this.scenePhase == 3) {
            this.gp.ui.drawDialogueScreen();
        } else if (this.scenePhase == 4) {
            for (int i = 0; i < this.gp.npc[1].length; ++i) {
                if (this.gp.npc[this.gp.currentMap][i] == null || !this.gp.npc[this.gp.currentMap][i].name.equals("PlayerDummy")) continue;
                this.gp.player.worldX = this.gp.npc[this.gp.currentMap][i].worldX;
                this.gp.player.worldY = this.gp.npc[this.gp.currentMap][i].worldY;
                this.gp.npc[this.gp.currentMap][i] = null;
                break;
            }
            this.gp.player.drawing = true;
            this.sceneNum = 0;
            this.scenePhase = 0;
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 1;
            this.gp.stopMusic();
            this.gp.playMusic(22);
        }
    }

    public void scene_skeletonLord() {
        if (this.scenePhase == 0) {
            int i;
            this.gp.bossBattleOn = true;
            for (i = 0; i < this.gp.obj[1].length; ++i) {
                if (this.gp.obj[this.gp.currentMap][i] != null) continue;
                this.gp.obj[this.gp.currentMap][i] = new OBJ_Door_Iron(this.gp);
                this.gp.obj[this.gp.currentMap][i].worldX = this.gp.tileSize * 25;
                this.gp.obj[this.gp.currentMap][i].worldY = this.gp.tileSize * 11;
                this.gp.obj[this.gp.currentMap][i].temp = true;
                this.gp.obj[this.gp.currentMap][i + 1] = new OBJ_Door_Iron(this.gp);
                this.gp.obj[this.gp.currentMap][i + 1].worldX = this.gp.tileSize * 25;
                this.gp.obj[this.gp.currentMap][i + 1].worldY = this.gp.tileSize * 33;
                this.gp.obj[this.gp.currentMap][i + 1].temp = true;
                this.gp.playSE(21);
                break;
            }
            for (i = 0; i < this.gp.npc[1].length; ++i) {
                if (this.gp.npc[this.gp.currentMap][i] != null) continue;
                this.gp.npc[this.gp.currentMap][i] = new PlayerDummy(this.gp);
                this.gp.npc[this.gp.currentMap][i].worldX = this.gp.player.worldX;
                this.gp.npc[this.gp.currentMap][i].worldY = this.gp.player.worldY;
                this.gp.npc[this.gp.currentMap][i].direction = this.gp.player.direction;
                break;
            }
            this.gp.player.drawing = false;
            ++this.scenePhase;
        } else if (this.scenePhase == 1) {
            this.gp.player.worldY -= 2;
            if (this.gp.player.worldY < this.gp.tileSize * 16) {
                this.gp.player.spriteNum = 3;
                ++this.scenePhase;
            }
        } else if (this.scenePhase == 2) {
            for (int i = 0; i < this.gp.monster[1].length; ++i) {
                if (this.gp.monster[this.gp.currentMap][i] == null || this.gp.monster[this.gp.currentMap][i].name != "Skeleton Lord") continue;
                this.gp.monster[this.gp.currentMap][i].sleep = false;
                this.gp.ui.npc = this.gp.monster[this.gp.currentMap][i];
                ++this.scenePhase;
                break;
            }
        } else if (this.scenePhase == 3) {
            this.gp.ui.drawDialogueScreen();
        } else if (this.scenePhase == 4) {
            for (int i = 0; i < this.gp.npc[1].length; ++i) {
                if (this.gp.npc[this.gp.currentMap][i] == null || !this.gp.npc[this.gp.currentMap][i].name.equals("PlayerDummy")) continue;
                this.gp.player.worldX = this.gp.npc[this.gp.currentMap][i].worldX;
                this.gp.player.worldY = this.gp.npc[this.gp.currentMap][i].worldY;
                this.gp.npc[this.gp.currentMap][i] = null;
                break;
            }
            this.gp.player.drawing = true;
            this.sceneNum = 0;
            this.scenePhase = 0;
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 1;
            this.gp.stopMusic();
            this.gp.playMusic(22);
        }
    }

    public void endScene() {
        if (this.scenePhase == 0) {
            this.gp.ui.npc = new IT_Bed(this.gp, 0, 0);
            ++this.scenePhase;
        } else if (this.scenePhase == 1) {
            this.gp.stopMusic();
            this.gp.ui.npc.startDialogue(this.gp.ui.npc, 1);
            ++this.scenePhase;
        } else if (this.scenePhase == 2) {
            this.gp.playSE(4);
            ++this.scenePhase;
        } else if (this.scenePhase == 3) {
            if (this.counterReached(200) || this.gp.keyH.enterPressed) {
                ++this.scenePhase;
                Objects.requireNonNull(this.gp);
                this.gp.gameState = 10;
            }
        } else if (this.scenePhase == 4) {
            this.alpha += 0.005f;
            if (this.alpha >= 1.0f) {
                this.alpha = 1.0f;
            }
            this.fadeOut(this.alpha);
            if (this.alpha == 1.0f) {
                ++this.scenePhase;
            }
        } else if (this.scenePhase == 5) {
            this.fadeOut(1.0f);
            this.alpha += 0.005f;
            if (this.alpha >= 1.0f) {
                this.alpha = 1.0f;
            }
            String completionText = "After a long journey, the little monkey could finally rest.\nYou've completed the game!\nThank you for playing!";
            this.drawString(this.alpha, 38.0f, completionText, 200, 70, Color.white);
            if (this.counterReached(540)) {
                this.gp.playMusic(0);
                ++this.scenePhase;
            }
        } else if (this.scenePhase == 6) {
            this.fadeOut(1.0f);
            this.y = this.gp.screenHeight / 2;
            this.drawString(1.0f, 120.0f, "Monkey Boy", this.y, 40, new Color(204, 85, 0));
            if (this.counterReached(400)) {
                ++this.scenePhase;
            }
        } else if (this.scenePhase == 7) {
            this.fadeOut(1.0f);
            this.g2.setColor(Color.white);
            this.drawString(1.0f, 38.0f, this.endCredit, this.y, 40, Color.white);
            if (this.counterReached(240)) {
                ++this.scenePhase;
            }
        } else if (this.scenePhase == 8) {
            this.fadeOut(1.0f);
            --this.y;
            this.drawString(1.0f, 38.0f, this.endCredit, this.y, 40, Color.white);
            if (this.counterReached(1800)) {
                ++this.scenePhase;
            }
        } else if (this.scenePhase == 9) {
            this.sceneNum = 0;
            this.scenePhase = 0;
            Objects.requireNonNull(this.gp);
            this.gp.gameState = 0;
        }
    }

    public void drawString(float alpha, float fontSize, String text, int y, int lineSpacing, Color color) {
        this.g2.setComposite(AlphaComposite.getInstance(3, alpha));
        this.g2.setFont(this.g2.getFont().deriveFont(fontSize));
        this.g2.setColor(color);
        for (String line : text.split("\n")) {
            int x = this.gp.ui.getXforCenteredText(line);
            this.g2.drawString(line, x, y);
            y += lineSpacing;
        }
        this.g2.setComposite(AlphaComposite.getInstance(3, 1.0f));
    }

    public void fadeOut(float alpha) {
        this.g2.setComposite(AlphaComposite.getInstance(3, alpha));
        this.g2.setColor(Color.BLACK);
        this.g2.fillRect(0, 0, this.gp.screenWidth, this.gp.screenHeight);
        this.g2.setComposite(AlphaComposite.getInstance(3, 1.0f));
    }

    public boolean counterReached(int targetCount) {
        boolean reached = false;
        ++this.counter;
        if (this.counter >= targetCount) {
            reached = true;
            this.counter = 0;
        }
        return reached;
    }

    private void animateEntity(Entity entity) {
        if (entity != null) {
            ++entity.spriteCounter;
            if (entity == this.gp.player) {
                if (entity.spriteCounter > entity.walkAnimationSpeed) {
                    if (entity.direction.equals("left") || entity.direction.equals("right")) {
                        if (entity.spriteNum == 1) {
                            entity.spriteNum = 2;
                        } else if (entity.spriteNum == 2) {
                            entity.spriteNum = 3;
                        } else if (entity.spriteNum == 3) {
                            entity.spriteNum = 2;
                        }
                    } else if (entity.spriteNum == 1) {
                        entity.spriteNum = 2;
                    } else if (entity.spriteNum == 2) {
                        entity.spriteNum = 1;
                    }
                    entity.spriteCounter = 0;
                }
            } else if (entity.spriteCounter > entity.walkAnimationSpeed) {
                entity.spriteNum = entity.spriteNum == 1 ? 2 : 1;
                entity.spriteCounter = 0;
            }
        }
    }
}
