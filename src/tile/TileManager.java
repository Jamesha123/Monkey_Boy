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
        InputStream inputStream = this.getClass().getResourceAsStream("/Map/tiledata.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                this.fileNames.add(string);
                this.collisionStatus.add(bufferedReader.readLine());
            }
            bufferedReader.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        this.tile = new Tile[this.fileNames.size()];
        this.getTileImage();
        inputStream = this.getClass().getResourceAsStream("/Map/worldmap.txt");
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String string = bufferedReader.readLine();
            String[] stringArray = string.split(" ");
            gamePanel.maxWorldCol = stringArray.length;
            gamePanel.maxWorldRow = stringArray.length;
            this.mapTileNum = new int[gamePanel.maxMap][gamePanel.maxWorldCol][gamePanel.maxWorldRow];
            bufferedReader.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
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
            String string = this.fileNames.get(i);
            boolean bl = this.collisionStatus.get(i).equals("true");
            this.setup(i, string, bl);
        }
    }

    public void setup(int n, String string, boolean bl) {
        UtilityTool utilityTool = new UtilityTool();
        try {
            this.tile[n] = new Tile();
            this.tile[n].image = ImageIO.read(this.getClass().getResourceAsStream("/Tiles/" + string));
            this.tile[n].image = utilityTool.scaleImage(this.tile[n].image, this.gp.tileSize, this.gp.tileSize);
            this.tile[n].collision = bl;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void loadMap(String string, int n) {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(string);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int n2 = 0;
            int n3 = 0;
            while (n2 < this.gp.maxWorldCol && n3 < this.gp.maxWorldRow) {
                String string2 = bufferedReader.readLine();
                while (n2 < this.gp.maxWorldCol) {
                    int n4;
                    String[] stringArray = string2.split(" ");
                    this.mapTileNum[n][n2][n3] = n4 = Integer.parseInt(stringArray[n2]);
                    ++n2;
                }
                if (n2 != this.gp.maxWorldCol) continue;
                n2 = 0;
                ++n3;
            }
            bufferedReader.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (this.worldMapImage != null && n >= 0 && n < this.worldMapImage.length) {
            this.worldMapImage[n] = null;
        }
    }

    private void buildWorldMapImage(int n) {
        int n2 = this.gp.maxWorldCol * this.gp.tileSize;
        int n3 = this.gp.maxWorldRow * this.gp.tileSize;
        BufferedImage bufferedImage = new BufferedImage(n2, n3, 2);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        for (int i = 0; i < this.gp.maxWorldCol; ++i) {
            for (int j = 0; j < this.gp.maxWorldRow; ++j) {
                int n4 = this.mapTileNum[n][i][j];
                int n5 = i * this.gp.tileSize;
                int n6 = j * this.gp.tileSize;
                graphics2D.drawImage((Image)this.tile[n4].image, n5, n6, null);
            }
        }
        graphics2D.dispose();
        this.worldMapImage[n] = bufferedImage;
    }

    public void refreshWorldMapImageForCurrentMap() {
        if (this.worldMapImage != null) {
            this.worldMapImage[this.gp.currentMap] = null;
        }
    }

    public void draw(Graphics2D graphics2D) {
        int n;
        int n2;
        int n3;
        int n4;
        int n5;
        BufferedImage bufferedImage;
        if (this.worldMapImage[this.gp.currentMap] == null) {
            this.buildWorldMapImage(this.gp.currentMap);
        }
        if ((bufferedImage = this.worldMapImage[this.gp.currentMap]) != null) {
            n5 = this.gp.player.worldX - this.gp.player.screenX;
            n4 = this.gp.player.worldY - this.gp.player.screenY;
            n3 = Math.max(0, n5);
            n2 = Math.max(0, n4);
            n = Math.min(bufferedImage.getWidth(), n5 + this.gp.screenWidth);
            int n6 = Math.min(bufferedImage.getHeight(), n4 + this.gp.screenHeight);
            int n7 = Math.max(0, -n5);
            int n8 = Math.max(0, -n4);
            int n9 = n7 + (n - n3);
            int n10 = n8 + (n6 - n2);
            graphics2D.drawImage(bufferedImage, n7, n8, n9, n10, n3, n2, n, n6, null);
        }
        if (this.drawPath) {
            graphics2D.setColor(new Color(255, 0, 0, 70));
            for (n5 = 0; n5 < this.gp.pFinder.pathList.size(); ++n5) {
                n4 = this.gp.pFinder.pathList.get((int)n5).col * this.gp.tileSize;
                n3 = this.gp.pFinder.pathList.get((int)n5).row * this.gp.tileSize;
                n2 = n4 - this.gp.player.worldX + this.gp.player.screenX;
                n = n3 - this.gp.player.worldY + this.gp.player.screenY;
                graphics2D.fillRect(n2, n, this.gp.tileSize, this.gp.tileSize);
            }
        }
    }
}
