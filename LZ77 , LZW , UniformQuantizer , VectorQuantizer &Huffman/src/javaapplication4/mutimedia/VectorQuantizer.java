/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4.mutimedia;

/**
 *
 * @author Abdussalam AbduHelal
 */
import static java.lang.Math.pow;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class VectorQuantizer {

    public ArrayList<Double> vectorAverages = new ArrayList<>();
    public ArrayList<Integer> compressData = new ArrayList<>();
    public static ArrayList<Integer> orginal = new ArrayList<>();
    public ArrayList<Integer> allAverages = new ArrayList<>();
    public ArrayList<Integer> getVector = new ArrayList<>();
    public static int width = 0;
    public static int height = 0;
    public String fileStore;
    public Scanner read;
    public String data = "";

	//
	/*
     * public int[][] pixels = { { 6, 2, 6, 3 }, { 7, 6, 7, 7 }, { 2, 2, 2, 3 },
     * { 1, 6, 1, 7 }, { 5, 1, 5, 2 }, { 6, 6, 6, 7 }, { 1, 2, 1, 3 } };
     * 
     * VectorQuantizer() { for (int i = 0; i < 7; i++) { for (int j = 0; j < 4;
     * j++) {
     * 
     * orginal.add(pixels[i][j]); //generalList.add(pixels[i][j]);
     * 
     * } //System.out.println(); }
     * 
     * }
     */
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

    public static int[][] readImage(String filePath) {
        File file = new File(filePath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        width = image.getWidth();
        height = image.getHeight();
        int[][] pixels = new int[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = (rgb >> 0) & 0xff;

                pixels[y][x] = r;
                orginal.add(r);
            }
        }
        return pixels;
    }

    public ArrayList<Double> getAverage(ArrayList<ArrayList<Integer>> lists) {
        ArrayList<Double> averages = new ArrayList<>();
        double average1 = 0;
        double average2 = 0;

        for (int i = 0; i < lists.size(); i++) {

            for (int j = 0; j < lists.get(i).size();) {

                average1 += (int) lists.get(i).get(j);
                j += 1;
                average2 += (int) lists.get(i).get(j);
                j++;

            }

            average1 = average1 / (lists.get(i).size() / 2);
            average2 = average2 / ((lists.get(i).size()) / 2);

            averages.add(average1);
            averages.add(average2);

        }
        return averages;
    }

    public ArrayList<Integer> split(ArrayList<Double> average) {

        ArrayList<Integer> vector = new ArrayList<>();
        for (int i = 0; i < average.size(); i += 2) {

            vector.add((int) (average.get(i) - 1));
            vector.add((int) (average.get(i + 1) - 1));
            vector.add((int) (average.get(i) + 1));
            vector.add((int) (average.get(i + 1) + 1));

        }
        return vector;
    }

    public int indexOfMin(ArrayList<Integer> arr) {

        int index = -1;
        int min = arr.get(0);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) <= min) {
                index = i;
                min = arr.get(i);
            }
        }

        return index;

    }

    public ArrayList<ArrayList<Integer>> listDestrbuation(
            ArrayList<Integer> splits, int k) {
        ArrayList<Integer> compare = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < pow(2, k); i++) {
            lists.add(new ArrayList<Integer>());
        }

        int var1 = 0;
        int var2 = 0;

        for (int j = 0; j < orginal.size(); j += 2) {
            compare.clear();
            for (int i = 0; i < splits.size(); i += 2) {

                var1 = (((int) pow(orginal.get(j) - splits.get(i), 2) + (int) pow(
                        orginal.get(j + 1) - splits.get(i + 1), 2)));
                compare.add(var1);

            }

            int indexOfSplits = indexOfMin(compare); // findMinIndex(compare);
            int s = orginal.get(j);
            lists.get(indexOfSplits).add(s);
            lists.get(indexOfSplits).add(orginal.get(j + 1));
        }
        return lists;
    }

    public void deCompress() {
        ArrayList<Double> deCompressData = new ArrayList<>();
        for (int i = 0; i < compressData.size(); i++) {
            int index = compressData.get(i) + compressData.get(i);// because is
            // vectore
            // size=2
            deCompressData.add(vectorAverages.get(index));
            deCompressData.add(vectorAverages.get(index + 1));

        }
        int k = 0;
        int img[][] = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                img[j][i] = (deCompressData.get(k).intValue());
                k++;
            }
        }
        writeImage(img, "DeCompresss.jpg");
    }

    public void FileWrite(String nameFileWrite) {
        File file = new File(nameFileWrite);

        try {
            FileWriter fw = new FileWriter(file);
            fw.write(vectorAverages.toString());
            fw.write(",");
            fw.write(compressData.toString());
            fw.close();
        } catch (Exception e) {
            System.out.println("could not find the file ");
        }

    }

    public ArrayList<Integer> listDestrbuationDecompress(
            ArrayList<Double> splits, int k) {
        ArrayList<Integer> compare = new ArrayList<Integer>();
        ArrayList<Integer> listaya = new ArrayList<Integer>();
        int var1 = 0;
        int var2 = 0;
        for (int j = 0; j < orginal.size(); j += 2) {
            compare.clear();
            for (int i = 0; i < splits.size(); i += 2) {

                var1 = (((int) pow(orginal.get(j) - splits.get(i), 2) + (int) pow(
                        orginal.get(j + 1) - splits.get(i + 1), 2)));
                compare.add(var1);
            }

            
            int indexOfSplits = indexOfMin(compare); // findMinIndex(compare);
            int s = orginal.get(j);
            listaya.add(indexOfSplits);
        }
        return listaya;
    }

    public void compress(int bits) {

        ArrayList<Integer> vectorSplits = new ArrayList<>();
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        lists.add(orginal);
        vectorAverages.addAll(getAverage(lists));
        lists.clear();
        int size = 0;

        for (int i = 1; i < bits; i++) {
            vectorSplits.addAll(split(vectorAverages));
            lists = listDestrbuation(vectorSplits, i);// 3 ,3 ,5 ,5;
            vectorAverages.clear();
            vectorSplits.clear();
            vectorAverages.addAll(getAverage(lists));

        }
        System.out.println(vectorAverages);
        compressData = listDestrbuationDecompress(vectorAverages, bits);
        System.out.println("output :" + compressData);
        FileWrite("VectorQ.txt");

    }

    public void readFile(String nameFile, int bits)
            throws FileNotFoundException {
        vectorAverages.clear();
        compressData.clear();
        read = new Scanner(new File(nameFile));
        while (read.hasNext()) {
            fileStore += read.next();
        }
        fileStore = fileStore.replace("[", "");
        fileStore = fileStore.replace("]", "");
        fileStore = fileStore.replace("null", "");
        String[] parts = fileStore.split(",");
        for (int i = 0; i < parts.length; i++) {

            if (i >= ((pow(2, bits)) * 2)) {
                compressData.add(Integer.parseInt(parts[i]));
            } else {
                vectorAverages.add(Double.parseDouble(parts[i]));
            }
        }

    }
}
