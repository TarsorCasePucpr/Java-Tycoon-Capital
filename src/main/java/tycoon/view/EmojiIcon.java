package tycoon.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;

public class EmojiIcon {

    private static final Map<String, ImageIcon> cache = new HashMap<>();

    public static ImageIcon get(String name, int size) {
        String key = name + "@" + size;
        return cache.computeIfAbsent(key, k -> load(name, size));
    }

    private static ImageIcon load(String name, int size) {
        try {
            File f = new File("emoji/" + name + ".png");
            if (f.exists()) {
                BufferedImage img = ImageIO.read(f);
                Image scaled = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
                return new ImageIcon(scaled);
            }
        } catch (Exception ignored) {}
        return null;
    }
}
