/*
 * Decompiled with CFR 0.152.
 */
package main;

import ai.PathFinder;
import data.Progress;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;
import main.AssetSetter;
import main.CollisionChecker;
import main.Config;
import main.CutsceneManager;
import main.DebugManager;
import main.EntityGenerator;
import main.EventHandler;
import main.ImageCache;
import main.KeyHandler;
import main.Main;
import main.Sound;
import main.UI;
import spellBook.snakeGame;
import tile.MiniMap;
import tile.TileManager;
import tile_interactive.InteractiveTile;

public class GamePanel
extends JPanel
implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = 48;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = 960;
    public final int screenHeight = 576;
    public int maxWorldCol;
    public int maxWorldRow;
    public final int maxMap = 15;
    public int currentMap = 0;
    int screenWidth2 = 960;
    int screenHeight2 = 576;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;
    int fps = 60;
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    public PathFinder pFinder = new PathFinder(this);
    public EnvironmentManager eManager = new EnvironmentManager(this);
    MiniMap map = new MiniMap(this);
    SaveLoad saveLoad = new SaveLoad(this);
    public EntityGenerator eGenerator = new EntityGenerator(this);
    public CutsceneManager csManager = new CutsceneManager(this);
    public DebugManager debugManager = new DebugManager(this);
    public snakeGame snakeGame = null;
    Thread gameThread;
    public Player player = new Player(this, this.keyH);
    public Entity[][] obj = new Entity[15][20];
    public Entity[][] npc = new Entity[15][10];
    public Entity[][] monster = new Entity[15][35];
    public InteractiveTile[][] iTile = new InteractiveTile[15][50];
    public Entity[][] projectile = new Entity[15][20];
    public ArrayList<Entity> particleList = new ArrayList();
    ArrayList<Entity> entityList = new ArrayList();
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int dialogueState = 2;
    public final int characterState = 3;
    public final int optionsState = 4;
    public final int gameOverState = 5;
    public final int transitionState = 6;
    public final int tradeState = 7;
    public final int sleepState = 8;
    public final int mapState = 9;
    public final int cutsceneState = 10;
    public final int cutsceneTransitionState = 11;
    public final int snakeGameState = 12;
    public final int debugState = 13;
    public final int loadingState = 14;
    public boolean bossBattleOn = false;
    public boolean entitiesFrozen = false;
    public boolean talkedToMom = false;
    public boolean orcRobbedPlayer = false;
    public boolean sugarPurchased = false;
    public boolean talkedToOldMan = false;
    public boolean hasReceivedMomCoins = false;
    public boolean questCompleted = false;
    public long drawTime = 0L;
    public int playerLight = 0;
    public int environmentLight = 1;
    public int bossLight = 2;
    public final int outside = 1;
    public final int indoor = 2;
    public final int dungeon = 3;
    public int currentArea = 2;
    public int nextArea;
    public int previousMap = 0;
    public final int bedroom = 0;
    public final int downstairs = 1;
    public final int worldmap = 2;
    public final int worldmap2 = 3;
    public final int worldmap3 = 4;
    public final int village = 5;
    public final int store = 6;
    public final int wizardHome = 7;
    public final int worldmap4 = 8;
    public final int slimeBossRoom = 9;
    public final int worldmap5 = 10;
    public final int dungeon01 = 11;
    public final int dungeon02 = 12;
    public final int skeletonLordRoom = 13;
    public final int village1 = 1;
    public final int village2 = 2;
    public final int village3 = 3;
    public final int village1Back = 4;
    public final int village3Back = 5;
    public final int shop = 6;
    private boolean pendingRetry = false;
    private int loadingCounter = 0;
    private String loadingMessage = "Loading...";
    private int loadingProgress = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(960, 576));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        this.gameState = 14;
        this.loadingMessage = "Initializing...";
        this.loadingProgress = 0;
        this.loadingMessage = "Loading image cache...";
        this.loadingProgress = 10;
        ImageCache.initialize(this);
        this.loadingMessage = "Preloading critical images...";
        this.loadingProgress = 20;
        ImageCache.preloadCriticalImages();
        this.loadingMessage = "Preloading map images...";
        this.loadingProgress = 25;
        ImageCache.preloadMapImages(this.currentMap);
        this.loadingMessage = "Loading objects...";
        this.loadingProgress = 30;
        this.aSetter.setObject();
        this.loadingMessage = "Loading NPCs...";
        this.loadingProgress = 50;
        this.aSetter.setNPC();
        this.loadingMessage = "Loading monsters...";
        this.loadingProgress = 70;
        this.aSetter.setMonster();
        if (!Progress.slimeBossDefeated) {
            this.aSetter.setSlimeBoss();
        }
        if (!Progress.skeletonLordDefeated) {
            this.aSetter.setSkeletonLord();
        }
        this.loadingMessage = "Loading interactive tiles...";
        this.loadingProgress = 85;
        this.aSetter.setInteractiveTile();
        this.loadingMessage = "Loading player assets...";
        this.loadingProgress = 95;
        this.player.getImage();
        this.player.getAttackImage();
        this.player.getGuardImage();
        this.loadingMessage = "Finalizing...";
        this.loadingProgress = 98;
        this.eManager.setup();
        this.loadingMessage = "Complete!";
        this.loadingProgress = 100;
        try {
            Thread.sleep(200L);
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        this.gameState = 0;
        this.currentArea = 1;
        this.playMusic(0);
        this.player.setDefaultPositions(this.currentMap);
        this.tempScreen = new BufferedImage(960, 576, 2);
        this.g2 = (Graphics2D)this.tempScreen.getGraphics();
        if (this.fullScreenOn) {
            this.setFullScreen();
        }
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    public void startRetryWithLoading() {
        this.pendingRetry = true;
        this.loadingCounter = 0;
        this.loadingMessage = "Loading...";
        this.loadingProgress = 0;
        this.gameState = 14;
    }

    public void retry() {
        this.stopMusic();
        this.removeTempEntity();
        this.playMusicForCurrentArea();
        this.player.setRetryPositions(this.currentMap, this.previousMap);
        this.player.restoreStatus();
        this.aSetter.setNPC();
        this.aSetter.setMonster();
        this.bossBattleOn = false;
        this.player.resetCounter();
        if (this.currentMap == 9) {
            this.clearSlimeBossRoom();
        }
        if (this.currentMap == 13) {
            this.clearSkeletonLordRoom();
        }
        if (!Progress.skeletonLordDefeated) {
            this.aSetter.setSkeletonLord();
        }
        if (!Progress.slimeBossDefeated) {
            this.aSetter.setSlimeBoss();
        }
    }

    public void restart() {
        this.stopMusic();
        this.removeTempEntity();
        this.clearAll();
        this.playMusic(0);
        this.currentMap = 0;
        this.aSetter.setNPC();
        this.aSetter.setMonster();
        this.aSetter.setObject();
        this.aSetter.setSlimeBoss();
        this.aSetter.setSkeletonLord();
        this.aSetter.setInteractiveTile();
        this.player.setDefaultValues();
        this.player.setDefaultPositions(this.currentMap);
        this.player.restoreStatus();
        this.player.resetCounter();
        this.eManager.lighting.resetDay();
        if (this.debugManager != null) {
            this.debugManager.debugMode = false;
            this.debugManager.godMode = false;
            this.debugManager.freezeEnemies = false;
            this.debugManager.collisionOff = false;
            this.debugManager.characterEditor = false;
            this.debugManager.debugStore = false;
            this.debugManager.showCollision = false;
            this.debugManager.darknessFilterOff = false;
            this.debugManager.debugSuspended = false;
            this.entitiesFrozen = false;
            this.tileM.drawPath = false;
        }
        this.resetStates();
    }

    public void resetStates() {
        this.bossBattleOn = false;
        this.entitiesFrozen = false;
        this.talkedToMom = false;
        this.orcRobbedPlayer = false;
        this.sugarPurchased = false;
        this.talkedToOldMan = false;
        this.hasReceivedMomCoins = false;
        this.questCompleted = false;
        Progress.skeletonLordDefeated = false;
        Progress.slimeBossDefeated = false;
    }

    public void setFullScreen() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = dimension.getWidth();
        double screenHeight = dimension.getHeight();
        Main.window.setExtendedState(6);
        this.screenWidth2 = (int)screenWidth;
        this.screenHeight2 = (int)screenHeight;
    }

    @Override
    public void run() {
        double nanosecondsPerFrame = 1000000000 / this.fps;
        double delta = 0.0;
        long lastTime = System.nanoTime();
        while (this.gameThread != null) {
            long now = System.nanoTime();
            delta += (double)(now - lastTime) / nanosecondsPerFrame;
            lastTime = now;
            if (!(delta >= 1.0)) continue;
            this.update();
            this.drawToTempScreen();
            this.drawToScreen();
            delta -= 1.0;
        }
    }

    public void update() {
        this.debugManager.update();
        if (this.gameState == 14) {
            ++this.loadingCounter;
            if (this.loadingCounter == 2 && this.pendingRetry) {
                this.retry();
                this.pendingRetry = false;
            }
            if (this.loadingCounter > 30) {
                this.gameState = 1;
            }
        } else if (this.gameState == 1) {
            int i;
            this.player.update();
            for (i = 0; i < this.npc[1].length; ++i) {
                if (this.npc[this.currentMap][i] == null) continue;
                this.npc[this.currentMap][i].update();
            }
            for (i = 0; i < this.monster[1].length; ++i) {
                if (this.monster[this.currentMap][i] == null) continue;
                if (this.monster[this.currentMap][i].alive && !this.monster[this.currentMap][i].dying) {
                    this.monster[this.currentMap][i].update();
                    continue;
                }
                if (this.monster[this.currentMap][i].alive) continue;
                this.monster[this.currentMap][i].checkDrop();
                this.monster[this.currentMap][i] = null;
            }
            for (i = 0; i < this.projectile[1].length; ++i) {
                if (this.projectile[this.currentMap][i] == null) continue;
                if (this.projectile[this.currentMap][i].alive) {
                    this.projectile[this.currentMap][i].update();
                    continue;
                }
                if (this.projectile[this.currentMap][i].alive) continue;
                this.projectile[this.currentMap][i] = null;
            }
            for (i = 0; i < this.particleList.size(); ++i) {
                if (this.particleList.get(i) == null) continue;
                if (this.particleList.get((int)i).alive) {
                    this.particleList.get(i).update();
                    continue;
                }
                if (this.particleList.get((int)i).alive) continue;
                this.particleList.remove(i);
            }
            for (i = 0; i < this.iTile[1].length; ++i) {
                if (this.iTile[this.currentMap][i] == null) continue;
                this.iTile[this.currentMap][i].update();
            }
            this.eManager.update();
        }
        if (this.gameState == 12 && this.snakeGame != null) {
            this.snakeGame.update();
        }
    }

    public void drawToTempScreen() {
        long drawStartTime = 0L;
        if (this.keyH.showDebugText || this.debugManager.debugMode) {
            drawStartTime = System.nanoTime();
        }
        if (this.gameState == 0) {
            this.ui.draw(this.g2);
        } else if (this.gameState == 9) {
            this.map.drawFullMapScreen(this.g2);
        } else if (this.gameState == 14) {
            this.g2.setColor(Color.black);
            this.g2.fillRect(0, 0, 960, 576);
            this.g2.setColor(Color.white);
            this.g2.setFont(this.ui.fontBold50);
            int messageX = (960 - this.g2.getFontMetrics().stringWidth(this.loadingMessage)) / 2;
            int messageY = 258;
            this.g2.drawString(this.loadingMessage, messageX, messageY);
            int barWidth = 300;
            int barHeight = 20;
            int barX = (960 - barWidth) / 2;
            int barY = 308;
            this.g2.setColor(Color.gray);
            this.g2.fillRect(barX, barY, barWidth, barHeight);
            this.g2.setColor(Color.green);
            int fillWidth = (int)((double)barWidth * ((double)this.loadingProgress / 100.0));
            this.g2.fillRect(barX, barY, fillWidth, barHeight);
            this.g2.setColor(Color.white);
            this.g2.setFont(this.ui.fontBold24);
            String percentText = this.loadingProgress + "%";
            int percentX = (960 - this.g2.getFontMetrics().stringWidth(percentText)) / 2;
            int percentY = barY + barHeight + 30;
            this.g2.drawString(percentText, percentX, percentY);
        } else if (this.gameState == 12 && this.snakeGame != null) {
            this.snakeGame.draw(this.g2);
        } else {
            this.tileM.draw(this.g2);
            for (int i = 0; i < this.iTile[1].length; ++i) {
                if (this.iTile[this.currentMap][i] == null) continue;
                this.iTile[this.currentMap][i].draw(this.g2);
            }
            this.entityList.add(this.player);
            for (int i = 0; i < this.npc[1].length; ++i) {
                if (this.npc[this.currentMap][i] == null) continue;
                this.entityList.add(this.npc[this.currentMap][i]);
            }
            for (int i = 0; i < this.obj[1].length; ++i) {
                if (this.obj[this.currentMap][i] == null) continue;
                this.entityList.add(this.obj[this.currentMap][i]);
            }
            for (int i = 0; i < this.monster[1].length; ++i) {
                if (this.monster[this.currentMap][i] == null) continue;
                this.entityList.add(this.monster[this.currentMap][i]);
            }
            for (int i = 0; i < this.projectile[1].length; ++i) {
                if (this.projectile[this.currentMap][i] == null) continue;
                this.entityList.add(this.projectile[this.currentMap][i]);
            }
            for (int i = 0; i < this.particleList.size(); ++i) {
                if (this.particleList.get(i) == null) continue;
                this.entityList.add(this.particleList.get(i));
            }
            Collections.sort(this.entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity firstEntity, Entity secondEntity) {
                    return Integer.compare(firstEntity.worldY, secondEntity.worldY);
                }
            });
            for (int i = 0; i < this.entityList.size(); ++i) {
                this.entityList.get(i).draw(this.g2);
            }
            this.entityList.clear();
            this.eManager.draw(this.g2);
            this.map.drawMiniMap(this.g2);
            this.csManager.draw(this.g2);
            this.debugManager.draw(this.g2);
            this.ui.draw(this.g2);
        }
        if (this.keyH.showDebugText || this.debugManager.debugMode) {
            long drawEndTime = System.nanoTime();
            this.drawTime = drawEndTime - drawStartTime;
        }
    }

    public void drawToScreen() {
        Graphics graphics = this.getGraphics();
        graphics.drawImage(this.tempScreen, 0, 0, this.screenWidth2, this.screenHeight2, null);
        graphics.dispose();
    }

    public void playMusic(int musicIndex) {
        this.music.setFile(musicIndex);
        this.music.play();
        this.music.loop();
    }

    public void stopMusic() {
        this.music.stop();
    }

    public void playSE(int soundIndex) {
        this.se.setFile(soundIndex);
        this.se.play();
    }

    public void applyAreaForMap(int mapIndex) {
        if (mapIndex == this.dungeon01 || mapIndex == this.dungeon02 || mapIndex == this.skeletonLordRoom) {
            this.currentArea = this.dungeon;
        } else if (mapIndex == this.bedroom || mapIndex == this.downstairs || mapIndex == this.store || mapIndex == this.wizardHome) {
            this.currentArea = this.indoor;
        } else {
            this.currentArea = this.outside;
        }
    }

    public void playMusicForCurrentArea() {
        if (this.currentArea == 1) {
            this.playMusic(0);
        } else if (this.currentArea == 2) {
            this.playMusic(18);
        } else if (this.currentArea == 3) {
            this.playMusic(19);
        }
    }

    public void changeArea() {
        if (this.nextArea != this.currentArea) {
            this.stopMusic();
            if (this.nextArea == 1) {
                this.playMusic(0);
            } else if (this.nextArea == 2) {
                this.playMusic(18);
            } else if (this.nextArea == 3) {
                this.playMusic(19);
            }
            this.aSetter.setNPC();
        }
        this.currentArea = this.nextArea;
        this.aSetter.setMonster();
        if (!Progress.slimeBossDefeated) {
            this.aSetter.setSlimeBoss();
        }
        if (!Progress.skeletonLordDefeated) {
            this.aSetter.setSkeletonLord();
        }
    }

    public void removeTempEntity() {
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < this.obj[1].length; ++j) {
                if (this.obj[i][j] == null || !this.obj[i][j].temp) continue;
                this.obj[i][j] = null;
            }
        }
    }

    public void clearSlimeBossRoom() {
        int i;
        for (i = 0; i < this.monster[9].length; ++i) {
            if (this.monster[9][i] == null || !this.monster[9][i].name.equals("Red Slime") && !this.monster[9][i].name.equals("Green Slime") && !this.monster[9][i].name.equals("Blue Slime")) continue;
            this.monster[9][i] = null;
        }
        if (!Progress.slimeBossDefeated) {
            for (i = 0; i < this.obj[9].length && this.obj[9][i] != null; ++i) {
                this.obj[9][i] = null;
            }
        }
    }

    public void clearSkeletonLordRoom() {
        int i;
        for (i = 0; i < this.monster[13].length; ++i) {
            if (this.monster[13][i] == null || !this.monster[13][i].name.equals("Dungeon Orc")) continue;
            this.monster[13][i] = null;
        }
        if (!Progress.skeletonLordDefeated) {
            for (i = 0; i < this.obj[13].length && this.obj[13][i] != null; ++i) {
                this.obj[13][i] = null;
            }
        }
    }

    public void clearInteractiveTiles() {
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < this.iTile[i].length; ++j) {
                this.iTile[i][j] = null;
            }
        }
    }

    public void clearObjects() {
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < this.obj[i].length; ++j) {
                this.obj[i][j] = null;
            }
        }
    }

    public void clearNPCs() {
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < this.npc[i].length; ++j) {
                this.npc[i][j] = null;
            }
        }
    }

    public void clearMonsters() {
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < this.monster[i].length; ++j) {
                this.monster[i][j] = null;
            }
        }
    }

    public void clearProjectiles() {
        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < this.projectile[i].length; ++j) {
                this.projectile[i][j] = null;
            }
        }
    }

    public void clearParticles() {
        this.particleList.clear();
    }

    public void clearAll() {
        this.clearObjects();
        this.clearNPCs();
        this.clearMonsters();
        this.clearProjectiles();
        this.clearInteractiveTiles();
        this.clearParticles();
        this.clearSlimeBossRoom();
        this.clearSkeletonLordRoom();
    }
}
