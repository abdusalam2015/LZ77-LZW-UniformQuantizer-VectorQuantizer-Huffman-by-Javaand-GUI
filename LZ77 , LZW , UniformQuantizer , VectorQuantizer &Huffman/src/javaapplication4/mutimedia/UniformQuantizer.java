/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * @author Abdussalam AbduHelal Al-Shouibi
 */
public class UniformQuantizer {

    public static int width = 0;
    public static int height = 0;
    public static ArrayList<Integer> orginalData = new ArrayList<>();

    private static int[][] mirror(int[][] pixels) {
        int temp = 0;
        for (int i = 0; i < pixels.length; i++) {

            for (int j = 0, k = pixels[i].length - 1; j <= k; j++, k--) {
                temp = pixels[i][j];
                pixels[i][j] = pixels[i][k];
                pixels[i][k] = temp;
            }

        }
        return pixels;
    }
    public ArrayList<Integer> compressionData = new ArrayList<>();
    ArrayList<Integer> refrenceQuantizer = new ArrayList<>();
    ArrayList<Integer> deCompress = new ArrayList<>();
    public String fileStore = "";
    public static void readImage(String filePath) {
        File file = new File(filePath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
        }

        height = image.getWidth();
        width = image.getHeight();

        int[][] pixels = new int[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = (rgb >> 0) & 0xff;

                pixels[y][x] = r;
                orginalData.add(r);
            }
        }
    }

    public static void writeImage(int[][] pixels, String outputFilePath) {
        File fileout = new File(outputFilePath);
        BufferedImage image2 = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image2.setRGB(x, y, (pixels[y][x] << 16) | (pixels[y][x] << 8)
                        | (pixels[y][x]));
            }
        }
        try {
            ImageIO.write(image2, "jpg", fileout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    String numberOfbits;
    public void deComperssion(int levels) throws IOException {
        int size = 256 / levels;
        String[] parts = fileStore.split(" ");
        for (int i = 0; i < parts.length; i++) {
            compressionData.add(Integer.parseInt(parts[i]));
        }

        for (int i = 0; i < levels; i++) {
            int quantizer = ((i * size) + ((i + 1) * size)) / 2;
            refrenceQuantizer.add(quantizer);

        }
        for (int i = 0; i < compressionData.size(); i++) {
            int index = compressionData.get(i);
            deCompress.add(refrenceQuantizer.get(index));
        }
        int k = 0;
        int img[][] = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                img[j][i] = deCompress.get(k);
                k++;
            }
        }
        writeImage(img, "LenaDeCompress.jpg");
    }
    public double calcMAE() {
        double meanSquareError = 0.0;
        double temp = 0.0;
        for (int i = 0; i < orginalData.size(); i++) {
            temp = orginalData.get(i) - deCompress.get(i);
            if (temp < 0) {
                temp *= -1;
            }
            meanSquareError += temp;
        }
        meanSquareError = meanSquareError / orginalData.size();
        return meanSquareError;
    }
    public void clear() {
        
        fileStore = "";
        compressionData.clear();
        refrenceQuantizer.clear();
    }
    public void openFile(String nameFile) throws IOException {// open &read
        fileStore = "";
        BufferedReader br = new BufferedReader(new FileReader(nameFile));
        String line = "";
        try {
            Scanner read = new Scanner(new File(nameFile));
        } catch (Exception e) {
            System.out.printf("could not find the file ");
        }
        while ((line = br.readLine()) != null) {
            fileStore += line;
        }
    }
    public String print() {
        return fileStore;
    }

    public void comperssion(int levels) throws IOException {
        ArrayList<String> newList = new ArrayList<>();
        int sizeOfRange = 256 / levels;
        for (int i = 0; i < orginalData.size(); i++) {
            int s = orginalData.get(i) / sizeOfRange;
            compressionData.add(s);
            newList.add(String.valueOf(s) + " ");
        }
        FileWrite("quantization.txt", newList);
        int[][] img = new int[width][height];
        int k = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                img[j][i] = compressionData.get(k);
                k++;
            }
        }
        writeImage(img, "LenaCompress.jpg");
    }

    public void FileWrite(String nameFileWrite, ArrayList<String> data) throws IOException {

        try {
            FileWriter writer = new FileWriter(nameFileWrite);
            for (String str : data) {

                writer.write(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
