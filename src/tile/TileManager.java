/*
 * Decompiled with CFR 0.152.
 */
package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;
import tile.Tile;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNum;
    public boolean drawPath = false;
    ArrayList<String> fileNames = new ArrayList();
    ArrayList<String> collisionStatus = new ArrayList();
    private BufferedImage[] worldMapImage;

    public TileManager(GamePanel gamePanel) {
        this.gp = gamePanel;
        InputStream tileDataStream = this.getClass().getResourceAsStream("/Map/tiledata.txt");
        BufferedReader tileDataReader = new BufferedReader(new InputStreamReader(tileDataStream));
        try {
            String tileFileName;
            while ((tileFileName = tileDataReader.readLine()) != null) {
                this.fileNames.add(tileFileName);
                this.collisionStatus.add(tileDataReader.readLine());
            }
            tileDataReader.close();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
        this.tile = new Tile[this.fileNames.size()];
        this.getTileImage();
        tileDataStream = this.getClass().getResourceAsStream("/Map/worldmap.txt");
        tileDataReader = new BufferedReader(new InputStreamReader(tileDataStream));
        try {
            String worldMapHeader = tileDataReader.readLine();
            String[] worldMapDimensions = worldMapHeader.split(" ");
            gamePanel.maxWorldCol = worldMapDimensions.length;
            gamePanel.maxWorldRow = worldMapDimensions.length;
            this.mapTileNum = new int[gamePanel.maxMap][gamePanel.maxWorldCol][gamePanel.maxWorldRow];
            tileDataReader.close();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
        this.worldMapImage = new BufferedImage[gamePanel.maxMap];
        Objects.requireNonNull(gamePanel);
        this.loadMap("/Map/bedroom.txt", 0);
        Objects.requireNonNull(gamePanel);
        this.loadMap("/Map/downstairs.txt", 1);
        Objects.requireNonNull(gamePanel);
        this.loadMap("/Map/worldmap.txt", 2);
        Objects.requireNonNull(gamePanel);
        this.loadMap("/Map/worldmap2.txt", 3);
        Objects.requireNonNull(gamePanel);
        this.loadMap("/Map/worldmap3.txt", 4);
        Objects.requireNonNull(gamePanel);
        this.loadMap("/Map/worldmap4.txt", 8);
        Objects.requireNonNull(gamePanel);
        this.loadMap("/Map/worldmap5.txt", 10);
        Objects.requireNonNull(gamePanel);
        this.loadMap("/Map/village.txt", 5);
        Objects.requireNonNull(gamePanel);
        this.loadMap("/Map/store.txt", 6);
        Objects.requireNonNull(gamePanel);
        this.loadMap("/Map/wizardHome.txt", 7);
        Objects.requireNonNull(gamePanel);
        this.loadMap("/Map/slimeBossRoom.txt", 9);
        Objects.requireNonNull(gamePanel);
        this.loadMap("/Map/dungeon01.txt", 11);
        Objects.requireNonNull(gamePanel);
        this.loadMap("/Map/dungeon02.txt", 12);
        Objects.requireNonNull(gamePanel);
        this.loadMap("/Map/skeletonLordRoom.txt", 13);
    }

    public void getTileImage() {
        for (int i = 0; i < this.fileNames.size(); ++i) {
            String tileFileName = this.fileNames.get(i);
            boolean hasCollision = this.collisionStatus.get(i).equals("true");
            this.setup(i, tileFileName, hasCollision);
        }
    }

    public void setup(int tileIndex, String tileFileName, boolean hasCollision) {
        UtilityTool imageScaler = new UtilityTool();
        try {
            this.tile[tileIndex] = new Tile();
            this.tile[tileIndex].image = ImageIO.read(this.getClass().getResourceAsStream("/Tiles/" + tileFileName));
            this.tile[tileIndex].image = imageScaler.scaleImage(this.tile[tileIndex].image, this.gp.tileSize, this.gp.tileSize);
            this.tile[tileIndex].collision = hasCollision;
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void loadMap(String mapPath, int mapIndex) {
        try {
            InputStream mapStream = this.getClass().getResourceAsStream(mapPath);
            BufferedReader mapReader = new BufferedReader(new InputStreamReader(mapStream));
            int col = 0;
            int row = 0;
            while (col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {
                String mapLine = mapReader.readLine();
                while (col < this.gp.maxWorldCol) {
                    int tileNum;
                    String[] tileNumbers = mapLine.split(" ");
                    this.mapTileNum[mapIndex][col][row] = tileNum = Integer.parseInt(tileNumbers[col]);
                    ++col;
                }
                if (col != this.gp.maxWorldCol) continue;
                col = 0;
                ++row;
            }
            mapReader.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (this.worldMapImage != null && mapIndex >= 0 && mapIndex < this.worldMapImage.length) {
            this.worldMapImage[mapIndex] = null;
        }
    }

    private void buildWorldMapImage(int mapIndex) {
        int mapWidth = this.gp.maxWorldCol * this.gp.tileSize;
        int mapHeight = this.gp.maxWorldRow * this.gp.tileSize;
        BufferedImage worldMapImage = new BufferedImage(mapWidth, mapHeight, 2);
        Graphics2D graphics2D = worldMapImage.createGraphics();
        for (int i = 0; i < this.gp.maxWorldCol; ++i) {
            for (int j = 0; j < this.gp.maxWorldRow; ++j) {
                int tileNum = this.mapTileNum[mapIndex][i][j];
                int worldX = i * this.gp.tileSize;
                int worldY = j * this.gp.tileSize;
                graphics2D.drawImage((Image)this.tile[tileNum].image, worldX, worldY, null);
            }
        }
        graphics2D.dispose();
        this.worldMapImage[mapIndex] = worldMapImage;
    }

    public void refreshWorldMapImageForCurrentMap() {
        if (this.worldMapImage != null) {
            this.worldMapImage[this.gp.currentMap] = null;
        }
    }

    public void draw(Graphics2D graphics2D) {
        int sourceRight;
        int sourceTop;
        int sourceLeft;
        int worldViewTop;
        int worldViewLeft;
        BufferedImage worldMapImage;
        if (this.worldMapImage[this.gp.currentMap] == null) {
            this.buildWorldMapImage(this.gp.currentMap);
        }
        if ((worldMapImage = this.worldMapImage[this.gp.currentMap]) != null) {
            worldViewLeft = this.gp.player.worldX - this.gp.player.screenX;
            worldViewTop = this.gp.player.worldY - this.gp.player.screenY;
            sourceLeft = Math.max(0, worldViewLeft);
            sourceTop = Math.max(0, worldViewTop);
            sourceRight = Math.min(worldMapImage.getWidth(), worldViewLeft + this.gp.screenWidth);
            int sourceBottom = Math.min(worldMapImage.getHeight(), worldViewTop + this.gp.screenHeight);
            int destLeft = Math.max(0, -worldViewLeft);
            int destTop = Math.max(0, -worldViewTop);
            int destRight = destLeft + (sourceRight - sourceLeft);
            int destBottom = destTop + (sourceBottom - sourceTop);
            graphics2D.drawImage(worldMapImage, destLeft, destTop, destRight, destBottom, sourceLeft, sourceTop, sourceRight, sourceBottom, null);
        }
        if (this.drawPath) {
            graphics2D.setColor(new Color(255, 0, 0, 70));
            for (worldViewLeft = 0; worldViewLeft < this.gp.pFinder.pathList.size(); ++worldViewLeft) {
                worldViewTop = this.gp.pFinder.pathList.get((int)worldViewLeft).col * this.gp.tileSize;
                sourceLeft = this.gp.pFinder.pathList.get((int)worldViewLeft).row * this.gp.tileSize;
                sourceTop = worldViewTop - this.gp.player.worldX + this.gp.player.screenX;
                sourceRight = sourceLeft - this.gp.player.worldY + this.gp.player.screenY;
                graphics2D.fillRect(sourceTop, sourceRight, this.gp.tileSize, this.gp.tileSize);
            }
        }
    }
}
