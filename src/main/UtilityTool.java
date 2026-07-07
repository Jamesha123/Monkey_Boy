/*
 * Decompiled with CFR 0.152.
 */
package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage sourceImage, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, sourceImage.getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(sourceImage, 0, 0, width, height, null);
        graphics2D.dispose();
        return scaledImage;
    }
}
