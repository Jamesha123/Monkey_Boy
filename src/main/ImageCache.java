/*
 * Decompiled with CFR 0.152.
 */
package main;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class ImageCache {
    private static Map<String, BufferedImage> cache = new HashMap<String, BufferedImage>();
    private static GamePanel gp;

    public static void initialize(GamePanel gamePanel) {
        gp = gamePanel;
    }

    public static void preloadCriticalImages() {
        ImageCache.getImage("/Player/Walking sprites/boy_up_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
        ImageCache.getImage("/Player/Walking sprites/boy_up_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
        ImageCache.getImage("/Player/Walking sprites/boy_down_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
        ImageCache.getImage("/Player/Walking sprites/boy_down_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
        ImageCache.getImage("/Player/Walking sprites/boy_left_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
        ImageCache.getImage("/Player/Walking sprites/boy_left_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
        ImageCache.getImage("/Player/Walking sprites/boy_left_3", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
        ImageCache.getImage("/Player/Walking sprites/boy_right_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
        ImageCache.getImage("/Player/Walking sprites/boy_right_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
        ImageCache.getImage("/Player/Walking sprites/boy_right_3", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
        ImageCache.getImage("/Object/heart_full", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
        ImageCache.getImage("/Object/heart_half", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
        ImageCache.getImage("/Object/heart_blank", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
    }

    public static void preloadMapImages(int n) {
        block3: {
            block8: {
                block7: {
                    block6: {
                        block5: {
                            block4: {
                                block2: {
                                    Objects.requireNonNull(gp);
                                    if (n != 3) break block2;
                                    ImageCache.getImage("/Monster/greenslime_down_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                    ImageCache.getImage("/Monster/greenslime_down_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                    ImageCache.getImage("/Monster/redslime_down_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                    ImageCache.getImage("/Monster/redslime_down_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                    ImageCache.getImage("/Object/chest", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                    break block3;
                                }
                                Objects.requireNonNull(gp);
                                if (n != 4) break block4;
                                ImageCache.getImage("/Monster/greenslime_down_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                ImageCache.getImage("/Monster/greenslime_down_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                ImageCache.getImage("/Monster/redslime_down_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                ImageCache.getImage("/Monster/redslime_down_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                ImageCache.getImage("/Monster/orc_up_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                ImageCache.getImage("/Monster/orc_up_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                ImageCache.getImage("/Monster/orc_down_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                ImageCache.getImage("/Monster/orc_down_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                ImageCache.getImage("/Monster/orc_left_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                ImageCache.getImage("/Monster/orc_left_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                ImageCache.getImage("/Monster/orc_right_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                ImageCache.getImage("/Monster/orc_right_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                ImageCache.getImage("/Object/chest", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                                break block3;
                            }
                            Objects.requireNonNull(gp);
                            if (n != 8) break block5;
                            ImageCache.getImage("/Monster/greenslime_down_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                            ImageCache.getImage("/Monster/greenslime_down_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                            ImageCache.getImage("/Monster/redslime_down_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                            ImageCache.getImage("/Monster/redslime_down_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                            ImageCache.getImage("/Monster/orc_up_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                            ImageCache.getImage("/Monster/orc_up_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                            ImageCache.getImage("/Monster/orc_down_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                            ImageCache.getImage("/Monster/orc_down_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                            ImageCache.getImage("/Monster/orc_left_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                            ImageCache.getImage("/Monster/orc_left_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                            ImageCache.getImage("/Monster/orc_right_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                            ImageCache.getImage("/Monster/orc_right_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                            ImageCache.getImage("/Object/chest", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                            break block3;
                        }
                        Objects.requireNonNull(gp);
                        if (n != 10) break block6;
                        ImageCache.getImage("/Monster/greenslime_down_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                        ImageCache.getImage("/Monster/greenslime_down_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                        ImageCache.getImage("/Monster/redslime_down_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                        ImageCache.getImage("/Monster/redslime_down_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                        ImageCache.getImage("/Monster/orc_up_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                        ImageCache.getImage("/Monster/orc_up_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                        ImageCache.getImage("/Monster/orc_down_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                        ImageCache.getImage("/Monster/orc_down_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                        ImageCache.getImage("/Monster/orc_left_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                        ImageCache.getImage("/Monster/orc_left_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                        ImageCache.getImage("/Monster/orc_right_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                        ImageCache.getImage("/Monster/orc_right_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                        ImageCache.getImage("/Object/chest", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                        break block3;
                    }
                    Objects.requireNonNull(gp);
                    if (n != 5) break block7;
                    ImageCache.getImage("/NPC/garther_down_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                    ImageCache.getImage("/NPC/garther_down_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                    ImageCache.getImage("/NPC/blacksmith_down_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                    ImageCache.getImage("/NPC/blacksmith_down_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
                    break block3;
                }
                Objects.requireNonNull(gp);
                if (n == 11) break block8;
                Objects.requireNonNull(gp);
                if (n != 12) break block3;
            }
            ImageCache.getImage("/Monster/bat_down_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
            ImageCache.getImage("/Monster/bat_down_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
            ImageCache.getImage("/Monster/orc_up_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
            ImageCache.getImage("/Monster/orc_up_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
            ImageCache.getImage("/Monster/orc_down_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
            ImageCache.getImage("/Monster/orc_down_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
            ImageCache.getImage("/Monster/orc_left_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
            ImageCache.getImage("/Monster/orc_left_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
            ImageCache.getImage("/Monster/orc_right_1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
            ImageCache.getImage("/Monster/orc_right_2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
            ImageCache.getImage("/Object/chest", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
            ImageCache.getImage("/Object/torch1", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
            ImageCache.getImage("/Object/torch2", ImageCache.gp.tileSize, ImageCache.gp.tileSize);
        }
    }

    public static BufferedImage getImage(String path, int width, int height) {
        String normalizedPath = ImageCache.normalizePath(path);
        String cacheKey = normalizedPath + "_" + width + "x" + height;
        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }
        BufferedImage image = ImageCache.loadImage(normalizedPath, width, height);
        if (image != null) {
            cache.put(cacheKey, image);
        }
        return image;
    }

    private static String normalizePath(String path) {
        while (path.endsWith(".png")) {
            path = path.substring(0, path.length() - 4);
        }
        return path;
    }

    private static BufferedImage loadImage(String path, int width, int height) {
        try (InputStream stream = ImageCache.class.getResourceAsStream(path + ".png")) {
            if (stream == null) {
                System.err.println("Missing image resource: " + path + ".png");
                return null;
            }
            BufferedImage image = ImageIO.read(stream);
            if (image != null) {
                UtilityTool utilityTool = new UtilityTool();
                return utilityTool.scaleImage(image, width, height);
            }
        } catch (Exception exception) {
            System.err.println("Failed to load image: " + path + ".png");
            exception.printStackTrace();
        }
        return null;
    }

    public static void clearCache() {
        cache.clear();
    }

    public static int getCacheSize() {
        return cache.size();
    }
}
