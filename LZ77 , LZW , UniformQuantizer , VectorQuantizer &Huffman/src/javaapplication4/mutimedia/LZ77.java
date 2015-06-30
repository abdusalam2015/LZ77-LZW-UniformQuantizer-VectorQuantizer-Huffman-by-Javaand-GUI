package multimedia;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Formatter;

/**
 * @author Abdussalam AbduHelal
 */
public class LZ77 {
    public String decompressed;

    public void clear (){
        tags.clear();
        decompressed="";
        fileStore="";
        
    }
    public void splitTags() {
        fileStore = fileStore.replace(" ",",") ;
        String[] parts = fileStore.split(",")  ;
        tags.clear();
        for (int i = 0 ; i < parts.length;i+=3) {
            int pointer = Integer.parseInt(parts[i]);
            int length = Integer.parseInt(parts[i+1]);
            char next = parts[i+2].charAt(0);
            tags.add(new Tag(pointer, length, next));
        } 
        decompressed = "";
        for (Tag tag : tags) {
        String temp = decompressed.substring(tag.pointer,(tag.pointer+tag.length));//abbab
        System.out.println(temp);
        decompressed += (temp+tag.nextchar);
        }
        }
    public class Tag {
        public int pointer;
        private int length;
        private char nextchar;
        public Tag(int pointer , int  length , char nextchar) {
            this.pointer = pointer;
            this.length = length;
            this.nextchar = nextchar;
        }
        public Tag() {
            pointer=0; 
            length=0 ; 
            }
        public String toString (){
           return (pointer +","+length+","+nextchar+" ") ;
        }
    }
    public String print(){
        String temp ="";
        for (Tag tag : tags) {
            temp +=tag.toString();
        }
        return temp;
    } 
         
    public ArrayList<Tag> tags = new ArrayList<>();
    public String fileStore;
    private Scanner read;

    public LZ77() {

    }
    private Formatter write  ;
    
    public void FileWrite (String nameFileWrite){
        File file =new File (nameFileWrite);
        LZ77 obj33=new LZ77();
        try{
           FileWriter fw = new FileWriter(file);
           String all = print();
           fw.write(all); 
           fw.close();
        }catch (Exception e){
            System.out.println("could not find the file ");
        }
    }
    public void openFile(String nameFile) throws FileNotFoundException, IOException {//open &read
        BufferedReader br = new BufferedReader( new FileReader(nameFile));
        String line="";
        fileStore="";
        try {
            read = new Scanner(new File(nameFile));
        } catch (Exception e) {
            System.out.printf("could not find the file ");
        }
        while((line=br.readLine())!=null){
            fileStore+=line;
        }
    }
    public void closeFile (){
        read.close();
    }
    public void compression() {
        String temp =""; 
        String temp2="";
        boolean check=false ; 
        int match_position=0;   
        String copyTemp ="";
        
        char []ch = null;
                int i = 0;
                temp2= fileStore.substring(0,0);
                temp+= fileStore.substring(i,i+1);
                char [] ch1=temp.toCharArray();
                Tag obj1 = new Tag(0,0,ch1[0]);
                tags.add(obj1) ;
                temp="" ;
        for ( i=1 ; i <fileStore.length()-1; ) {
            temp += fileStore.substring(i,i+1);
            int n = temp.length()-1;
            temp2=fileStore.substring(0,i-n);
            check = temp2.contains(temp);
            if(check){ 
                i++;
            }else{  
                ch=temp.toCharArray();
               copyTemp ="" ;
               for (int j=0; j<temp.length()-1 ; j++){
                    copyTemp+=ch[j];
               }
               match_position=temp2.indexOf(copyTemp) ;
               Tag obj =new Tag(match_position,( temp.length()-1),ch[temp.length()-1]);
               tags.add(obj);
               temp="";
               i++;   
            }
        }
        if (temp.equals("")){
            return ;
        }
       // System.out.println(temp +"+++++");
         ch=temp.toCharArray();
        // copyTemp="";
        // for (int j=0; j<temp.length()-1 ; j++){
             //  d     copyTemp+=ch[j];
             //  }
        match_position=temp2.indexOf(temp) ;
       // ch=temp.toCharArray();
        Tag obj2 =new Tag(match_position,(temp.length()-1),temp.charAt(temp.length()-1));
         tags.add(obj2); 
        // System.out.println(tags);
    }
    
    public void  deCompression(){
        splitTags();
    }
}