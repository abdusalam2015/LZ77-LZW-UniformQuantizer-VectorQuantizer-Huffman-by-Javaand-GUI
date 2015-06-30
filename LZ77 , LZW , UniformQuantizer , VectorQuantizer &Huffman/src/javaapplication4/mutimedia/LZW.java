import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LZW {
	private Scanner read;
	public String fileStore ="";
        public String decompressed ="";
	public class Dictionary {
		public int ascii;
		public String info;
                
		public Dictionary(int ascii, String info) {
			this.ascii = ascii;
			this.info = info;
		}
		public Dictionary() {

		}
	}

	ArrayList<Dictionary> list;

	public LZW() {
		list = new ArrayList<Dictionary>();
		for (int i = 0; i < 97; i++)
			list.add(new Dictionary(i, null));
		for (int i = 97; i < 128; i++) {
			list.add(new Dictionary(i, "" + ((char) i)));
		}
	}

	public ArrayList<Integer> tempAsscii = new ArrayList<>();
	public String[] tempDictionary = null;
        public String toString(){
        String res = "";
        
        for(Integer ascci : tempAsscii){
               res += ascci+" ";
        }
        return res;
        }
        public void clear (){
        tempAsscii.clear();
       // d="";
        fileStore="";
      
    }
	public void comprision() {
		String temp2 = "";
		int check = 0;
		int counter = 128;
		int checkCopy = 0;
		for (int i = 0; i < fileStore.length();) {
			temp2 += fileStore.substring(i, i + 1);
			check = search(temp2);

			if (check == 0) {

				Dictionary object = new Dictionary(counter, temp2);
				list.add(object);
				tempAsscii.add(checkCopy);
				counter++;
				temp2 = "";

			} else {
				i++;
				checkCopy = check;
				if (i == fileStore.length()) {//last 
					tempAsscii.add(checkCopy);
				}
			}
		}

		
	}
public String print(){
	String compresionCode="";
	for (int i : tempAsscii) {
	//	System.out.print(i + "  ");
		compresionCode +=i +" ";
	}
	return compresionCode;
}
	public void deCompresion() {
		list.clear();
		LZW object2 = new LZW();
		list.addAll(object2.list);
		//System.out.println("list.size: " + list.size());
		int j = 0, num2 = 0;
		int check = 0, check2 = 0;
		String str = ""; // list.get(num).info;
		//String result = "";
		String wordBefor = "";
		String currentWord = "";
		int counter = 128;
		int num = tempAsscii.get(0);
		decompressed = "" + (char) num;

		for (int i = 1; i < tempAsscii.size(); i++) {

			num = tempAsscii.get(i);
			num2 = tempAsscii.get(i - 1);
			check = searchAscii(num);
			check2 = searchAscii(num2)
					;
			if (check != 0) {

				wordBefor = list.get(check2).info;
				currentWord = list.get(check).info;
				//System.out.println("currentword :" + currentWord);
				decompressed += currentWord;
				str = wordBefor + currentWord.charAt(0);
				Dictionary obj = new Dictionary(counter, str);
				list.add(obj);
				counter++;

			} else {

				currentWord += currentWord.charAt(0);
				Dictionary obj2 = new Dictionary(counter, currentWord);
				list.add(obj2);
				decompressed += currentWord;
				counter++;
			}
		}
		//System.out.println("result :" + result);
             //   return result;
	}

	public int search(String target) {
		for (int i = 97; i < list.size(); i++) {
			if (list.get(i).info.equals(target)) {
				int number = list.get(i).ascii;
				return number;
			}
		}

		return 0;
	}

	public int searchAscii(int target) {
		for (int i = 97; i < list.size(); i++) {
			if (list.get(i).ascii == target) {
				int number = list.get(i).ascii;
				return i;
			}
		}

		return 0;
	}
	public void openFile(String nameFile) throws FileNotFoundException, IOException {//open &read
		fileStore="";
        BufferedReader br = new BufferedReader( new FileReader(nameFile));
        String line="";
        try {
            read = new Scanner(new File(nameFile));
        } catch (Exception e) {
            System.out.printf("could not find the file ");
        }
        while((line=br.readLine())!=null){
            fileStore+=line;
        }
        System.out.println("fileStore :"+fileStore);
    }
    public void FileWrite (String nameFileWrite){
        File file =new File (nameFileWrite);
        //LZW obj33=new LZ77();
        try{
           FileWriter fw = new FileWriter(file);
           String all = print();
           fw.write(all); 
           fw.close();
        }catch (Exception e){
            System.out.println("could not find the file ");
        }
    }
    public void splitAscii() {
    	tempAsscii.clear();
        fileStore = fileStore.replace(" ", ",");
        String[] parts = fileStore.split(",");
        for (int i = 0 ; i < parts.length;i++){
        int ascii = Integer.parseInt(parts[i]);
        tempAsscii.add(ascii);
        }
    }
}
