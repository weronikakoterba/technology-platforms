package org.example;

import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
//https://www.baeldung.com/java-truncating-strings

public class Photos {
    public Pair<String, BufferedImage> get_path_and_photo(Path p) {
        try {
            BufferedImage image = ImageIO.read(p.toFile());
            String name = String.valueOf(p.getFileName());
            return Pair.of(name, image);
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public Pair<String, BufferedImage> modify_photo(Pair<String, BufferedImage> pair) {
        BufferedImage originalImage = pair.getRight();
        BufferedImage modifiedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), originalImage.getType());
            for (int i = 0; i < originalImage.getWidth(); i++) {
                for (int j = 0; j < originalImage.getHeight(); j++) {
                    int rgb = originalImage.getRGB(i, j);
                    Color color = new Color(rgb);
                    int red = color.getRed();
                    int blue = color.getBlue();
                    int green = color.getGreen();
                    Color outColor = new Color(red, blue, green);
                    int outRgb = outColor.getRGB();
                    modifiedImage.setRGB(i, j, outRgb);
                }
            }
            return Pair.of(pair.getLeft(), modifiedImage);
    }
    public void save_image(Pair<String, BufferedImage> pair) {
        try {
            String originalFileName = pair.getLeft();
            String modifiedFileName = originalFileName.substring(0, originalFileName.lastIndexOf('.'))
                    + "_modyfikacja"
                    + originalFileName.substring(originalFileName.lastIndexOf('.'));
            String outputFilePath = "C:\\Users\\DELL\\IdeaProjects\\lab6\\Obrazy cw 6 Java\\modification\\" + modifiedFileName;
            Path outputPath = Path.of(outputFilePath);
            ImageIO.write(pair.getRight(), "jpg", outputPath.toFile());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
