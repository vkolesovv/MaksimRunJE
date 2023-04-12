package ru.vadim.maksimrun;

import java.io.File;
import java.util.Objects;

public class AssetManager {
    public static File getImage(String id) {
        String path = "assets" + File.separator + "images" + File.separator + id + ".png";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String absolutePath = Objects.requireNonNull(classLoader.getResource(path)).getPath();
        return new File(absolutePath);
    }
}
