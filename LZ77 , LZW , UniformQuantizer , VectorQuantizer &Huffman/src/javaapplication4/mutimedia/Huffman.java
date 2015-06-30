
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Huffman {

    public String fileName;
    public String decompressed;

    public void writeByByte(String nameFileWrite, String str) {
        System.out.println("str : " + str);
        String storByte = "";
        int temp2 = 0;
        File file = new File(nameFileWrite);
        try {
            temp2 = Integer.parseInt(str, 2);
            storByte = temp2 + "";
            FileWriter fw = new FileWriter(file);
            fw.write(storByte);
            fw.close();
        } catch (Exception e) {
            System.out.println("could not find the file ");
        }

    }

    public void readFileByByte(String nameFile) throws FileNotFoundException, IOException {
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
        int storint = Integer.parseInt(fileStore);
        fileStore = Integer.toBinaryString(storint);
    }

    public class DataRepet implements Comparable<DataRepet> {

        public int repeat;
        public String info;

        public DataRepet(String info, int repeat) {
            this.repeat = repeat;
            this.info = info;
        }

        public DataRepet() {
        }

        public String toString() {
            return (info + "   " + repeat);
        }

        public int compareTo(DataRepet right) {
            if (this.repeat == right.repeat) {
                return 0;
            } else if (this.repeat < right.repeat) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    String fileStore = "";
    String binaryCodeComperssion = "";
    ArrayList<DataRepet> list = new ArrayList<>();
    ArrayList<ArrayList<DataRepet>> comperssionList = new ArrayList<ArrayList<DataRepet>>();
    HashMap<String, String> dictionary = new HashMap<>();

    public String comprision() throws IOException {
        String n = Integer.toBinaryString(20761);
        System.out.println(" n : " + n);
        int nnn = Integer.parseInt("101000100011001", 2);
        System.out.println(" nnn : " + nnn);
        dictionary = huffmanCoding(list);
        String str = encode(fileStore);
        writeByByte("ata.txt", str);
        writeDictionary("abdussalam.txt");
        return str;
    }

    public String print() throws IOException {
        System.out.println("prient " + fileStore);
        return fileStore;
    }

    public void writeDictionary(String nameFile) {

        File file = new File(nameFile);
        try {
            FileWriter fw = new FileWriter(file);
            String all = dictionary.toString();
            all = all.replace("=", " ");
            all = all.replace(",", "");
            all = all.replace("{", "");
            all = all.replace("}", "");
            fw.write(all);
            fw.close();
        } catch (Exception e) {
            System.out.println("could not find the file ");
        }
    }
    String out = "";

    public String encode(String fileStore2) {
        for (int i = 0; i < fileStore.length(); i++) {
            out += dictionary.get(fileStore2.charAt(i) + "");
        }
        return out;
    }

    public HashMap< String, String> huffmanCoding(ArrayList<DataRepet> inList) {
        Collections.sort(inList);
        if (inList.size() == 2) {
            HashMap<String, String> newList = new HashMap<String, String>();
            newList.put(inList.get(0).info, "0");
            newList.put(inList.get(1).info, "1");
            return newList;

        } else {
            ArrayList<DataRepet> previuosList = new ArrayList<DataRepet>();
            previuosList = addLast2(inList);
            Collections.sort(previuosList);
            HashMap<String, String> tempList = huffmanCoding(previuosList);

            HashMap<String, String> newList = new HashMap<String, String>();
            {

                for (int i = 0; i < inList.size(); i++) {
                    if (tempList.containsKey(inList.get(i).info)) {
                        newList.put(inList.get(i).info,
                                tempList.get(inList.get(i).info));
                    } else {
                        String temp = inList.get(i + 1).info
                                + inList.get(i).info;
                        newList.put(inList.get(i).info, tempList.get(temp)
                                + "0"); // tempList.get(temp)
                        newList.put(inList.get(i + 1).info, tempList.get(temp)
                                + "1");
                        break;
                    } // end else

                }// end for loop
            } // end regsion  
            return newList;

        }
    }

    private ArrayList<DataRepet> addLast2(ArrayList<DataRepet> inList) {

        ArrayList<DataRepet> temp = new ArrayList<Huffman.DataRepet>();
        for (int i = 0; i < inList.size() - 2; i++) {
            temp.add(inList.get(i));
        }
        int last = inList.size() - 1;

        DataRepet ob = new DataRepet(inList.get(last).info
                + inList.get(last - 1).info, inList.get(last).repeat
                + inList.get(last - 1).repeat);
        temp.add(ob);
        return temp;
    }

    public int repetitionAccount(String ch, String source) {
        String temp = "";
        int counter = 0;
        for (int i = 0; i < source.length(); i++) {
            temp = source.substring(i, i + 1);
            if (temp.equals(ch)) {
                counter++;
            }
        }
        return counter;
    }

    public void removeDup() {

        int countrepeat = 0;
        String cleardata = "", temp = "";

        for (int i = 0; i < fileStore.length(); i++) {
            int count = 1;
            for (int j = 0; j < cleardata.length(); j++) {

                if (fileStore.charAt(i) == cleardata.charAt(j)) {
                    count++;
                }
            }

            if (count == 1) {
                cleardata += fileStore.charAt(i);
            }
        }
        for (int i = 0; i < cleardata.length(); i++) {
            temp = cleardata.substring(i, i + 1);
            countrepeat = repetitionAccount(temp, fileStore);
            // String n = countrepeat + "";
            DataRepet obj = new DataRepet(temp, countrepeat);
            list.add(obj);
        }
        Collections.sort(list);
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

    public void FileWrite(String nameFileWrite) throws IOException {

        File file = new File(nameFileWrite);
        try {
            FileWriter fw = new FileWriter(file);
            String all = print();
            fw.write(all);
            fw.close();
        } catch (Exception e) {
            System.out.println("could not find the file ");
        }
    }

    public String deComperssion() throws IOException {
        readFileByByte("ata.txt");
        readDictionary("abdussalam.txt");
        decompressed = deCode(fileStore);
        FileWrite("abdu2.txt");
        return decompressed;
    }

    public void readDictionary(String fileName) throws IOException {

        String fileDictionary = "";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = "";
        try {
            Scanner read = new Scanner(new File(fileName));
        } catch (Exception e) {
            System.out.printf("could not find the file ");
        }
        while ((line = br.readLine()) != null) {
            fileDictionary += line;
        }
        String[] parts = fileDictionary.split(" ");
        for (int i = 0; i < parts.length; i += 2) {
            dictionary.put(parts[i], parts[i + 1]);
        }
    }

    public String deCode(String str) {
        String out = "";
        for (int i = 0; i < str.length();) {
            int k = i + 1;
            String temp = str.substring(i, k);

            while (!dictionary.containsValue(temp)) {

                if (k >= str.length() + 1) {
                    break;
                }
                temp = str.substring(i, k++);
            }
            out += getKeyByValue(dictionary, temp);
            i += temp.length();
        }
        return out;
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Entry<T, E> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void clear() {
        dictionary.clear();
        fileStore = "";

    }
}
