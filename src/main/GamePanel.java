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
        double d = dimension.getWidth();
        double d2 = dimension.getHeight();
        Main.window.setExtendedState(6);
        this.screenWidth2 = (int)d;
        this.screenHeight2 = (int)d2;
    }

    @Override
    public void run() {
        double d = 1000000000 / this.fps;
        double d2 = 0.0;
        long l = System.nanoTime();
        while (this.gameThread != null) {
            long l2 = System.nanoTime();
            d2 += (double)(l2 - l) / d;
            l = l2;
            if (!(d2 >= 1.0)) continue;
            this.update();
            this.drawToTempScreen();
            this.drawToScreen();
            d2 -= 1.0;
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
            int n;
            this.player.update();
            for (n = 0; n < this.npc[1].length; ++n) {
                if (this.npc[this.currentMap][n] == null) continue;
                this.npc[this.currentMap][n].update();
            }
            for (n = 0; n < this.monster[1].length; ++n) {
                if (this.monster[this.currentMap][n] == null) continue;
                if (this.monster[this.currentMap][n].alive && !this.monster[this.currentMap][n].dying) {
                    this.monster[this.currentMap][n].update();
                    continue;
                }
                if (this.monster[this.currentMap][n].alive) continue;
                this.monster[this.currentMap][n].checkDrop();
                this.monster[this.currentMap][n] = null;
            }
            for (n = 0; n < this.projectile[1].length; ++n) {
                if (this.projectile[this.currentMap][n] == null) continue;
                if (this.projectile[this.currentMap][n].alive) {
                    this.projectile[this.currentMap][n].update();
                    continue;
                }
                if (this.projectile[this.currentMap][n].alive) continue;
                this.projectile[this.currentMap][n] = null;
            }
            for (n = 0; n < this.particleList.size(); ++n) {
                if (this.particleList.get(n) == null) continue;
                if (this.particleList.get((int)n).alive) {
                    this.particleList.get(n).update();
                    continue;
                }
                if (this.particleList.get((int)n).alive) continue;
                this.particleList.remove(n);
            }
            for (n = 0; n < this.iTile[1].length; ++n) {
                if (this.iTile[this.currentMap][n] == null) continue;
                this.iTile[this.currentMap][n].update();
            }
            this.eManager.update();
        }
        if (this.gameState == 12 && this.snakeGame != null) {
            this.snakeGame.update();
        }
    }

    public void drawToTempScreen() {
        long l = 0L;
        if (this.keyH.showDebugText || this.debugManager.debugMode) {
            l = System.nanoTime();
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
            var3_2 = (960 - this.g2.getFontMetrics().stringWidth(this.loadingMessage)) / 2;
            int n = 258;
            this.g2.drawString(this.loadingMessage, var3_2, n);
            int n2 = 300;
            int n3 = 20;
            int n4 = (960 - n2) / 2;
            int n5 = 308;
            this.g2.setColor(Color.gray);
            this.g2.fillRect(n4, n5, n2, n3);
            this.g2.setColor(Color.green);
            int n6 = (int)((double)n2 * ((double)this.loadingProgress / 100.0));
            this.g2.fillRect(n4, n5, n6, n3);
            this.g2.setColor(Color.white);
            this.g2.setFont(this.ui.fontBold24);
            String string = this.loadingProgress + "%";
            int n7 = (960 - this.g2.getFontMetrics().stringWidth(string)) / 2;
            int n8 = n5 + n3 + 30;
            this.g2.drawString(string, n7, n8);
        } else if (this.gameState == 12 && this.snakeGame != null) {
            this.snakeGame.draw(this.g2);
        } else {
            this.tileM.draw(this.g2);
            for (var3_2 = 0; var3_2 < this.iTile[1].length; ++var3_2) {
                if (this.iTile[this.currentMap][var3_2] == null) continue;
                this.iTile[this.currentMap][var3_2].draw(this.g2);
            }
            this.entityList.add(this.player);
            for (var3_2 = 0; var3_2 < this.npc[1].length; ++var3_2) {
                if (this.npc[this.currentMap][var3_2] == null) continue;
                this.entityList.add(this.npc[this.currentMap][var3_2]);
            }
            for (var3_2 = 0; var3_2 < this.obj[1].length; ++var3_2) {
                if (this.obj[this.currentMap][var3_2] == null) continue;
                this.entityList.add(this.obj[this.currentMap][var3_2]);
            }
            for (var3_2 = 0; var3_2 < this.monster[1].length; ++var3_2) {
                if (this.monster[this.currentMap][var3_2] == null) continue;
                this.entityList.add(this.monster[this.currentMap][var3_2]);
            }
            for (var3_2 = 0; var3_2 < this.projectile[1].length; ++var3_2) {
                if (this.projectile[this.currentMap][var3_2] == null) continue;
                this.entityList.add(this.projectile[this.currentMap][var3_2]);
            }
            for (var3_2 = 0; var3_2 < this.particleList.size(); ++var3_2) {
                if (this.particleList.get(var3_2) == null) continue;
                this.entityList.add(this.particleList.get(var3_2));
            }
            Collections.sort(this.entityList, new Comparator<Entity>(this){

                @Override
                public int compare(Entity entity, Entity entity2) {
                    int n = Integer.compare(entity.worldY, entity2.worldY);
                    return n;
                }
            });
            for (var3_2 = 0; var3_2 < this.entityList.size(); ++var3_2) {
                this.entityList.get(var3_2).draw(this.g2);
            }
            this.entityList.clear();
            this.eManager.draw(this.g2);
            this.map.drawMiniMap(this.g2);
            this.csManager.draw(this.g2);
            this.debugManager.draw(this.g2);
            this.ui.draw(this.g2);
        }
        if (this.keyH.showDebugText || this.debugManager.debugMode) {
            long l2 = System.nanoTime();
            this.drawTime = l2 - l;
        }
    }

    public void drawToScreen() {
        Graphics graphics = this.getGraphics();
        graphics.drawImage(this.tempScreen, 0, 0, this.screenWidth2, this.screenHeight2, null);
        graphics.dispose();
    }

    public void playMusic(int n) {
        this.music.setFile(n);
        this.music.play();
        this.music.loop();
    }

    public void stopMusic() {
        this.music.stop();
    }

    public void playSE(int n) {
        this.se.setFile(n);
        this.se.play();
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
        int n;
        for (n = 0; n < this.monster[9].length; ++n) {
            if (this.monster[9][n] == null || !this.monster[9][n].name.equals("Red Slime") && !this.monster[9][n].name.equals("Green Slime") && !this.monster[9][n].name.equals("Blue Slime")) continue;
            this.monster[9][n] = null;
        }
        if (!Progress.slimeBossDefeated) {
            for (n = 0; n < this.obj[9].length && this.obj[9][n] != null; ++n) {
                this.obj[9][n] = null;
            }
        }
    }

    public void clearSkeletonLordRoom() {
        int n;
        for (n = 0; n < this.monster[13].length; ++n) {
            if (this.monster[13][n] == null || !this.monster[13][n].name.equals("Dungeon Orc")) continue;
            this.monster[13][n] = null;
        }
        if (!Progress.skeletonLordDefeated) {
            for (n = 0; n < this.obj[13].length && this.obj[13][n] != null; ++n) {
                this.obj[13][n] = null;
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
