import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    // finds a list of the line indices that contains the text you are looking for in a list of strings
    static ArrayList<Number> findTextLine(ArrayList<String> lines, String word){
        ArrayList<Number> lineIndex = new ArrayList<>();
        for(int i = 0; i < lines.size(); i++){
            if(lines.get(i).contains(word)) 
            lineIndex.add(i);
        }
        return lineIndex;
    }

    // finds the position(s) of a word in a single line of text
    static ArrayList<Number> findTextPosn(String line, String word){
        ArrayList<Number> posnArr = new ArrayList<>();
        String[] strArr = line2StringArray(line);
        for(int i = 0; i < strArr.length; i++){
            if(strArr[i].compareTo(word) == 0) 
            posnArr.add(i);
        }
        return posnArr;
    }

    // converts a line of text to a string array of single words separated by a space " "
    static String[] line2StringArray(String line){
        String[] strArr = null;
        strArr = line.replaceAll("[^a-zA-Z ]", "").trim().split("\\s+");
        return strArr;
    }

    public static void main(String[] args) throws IOException {
        Path fileName = Path.of("ProgrammingHistory.txt");
        String str = Files.readString(fileName);
        String s = str.replaceAll("\\r\n","");
        String s2 = s.replaceAll("\\.","\\.\n");

        FileWriter fw = new FileWriter("ProgrammingHistory_sentence.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(s2);
        bw.close();

        FileReader fr = new FileReader("ProgrammingHistory_sentence.txt");
        BufferedReader br = new BufferedReader(fr);
      
        //String[] strArr = s2.split("\\.");
        /*ArrayList<String> lines = new ArrayList<>();

        for(int k = 0; k < strArr.length; k++){
            lines.add(strArr[k]);
        }*/

        ArrayList<String> lines = new ArrayList<>();
        String line;

        while((line = br.readLine()) != null){
            lines.add(line);
        }
        br.close();
   
        String word = "However"; // enter the word you are searching for in the text file (case sensitive)
        ArrayList<Number> lineIndex = findTextLine(lines, word);
        int lineNum;

        System.out.println("The word '" + word + "' can be found in:\r");
        for(int i = 0; i < lineIndex.size(); i++){
            lineNum = lineIndex.get(i).intValue();
            line = lines.get(lineNum);
            ArrayList<Number> posnArr = findTextPosn(line, word);
            String wordNum = "";
            for(int j = 0; j < posnArr.size(); j++){
                wordNum += " " + (posnArr.get(j).intValue()+1);
            }

            System.out.println("sentence: " + (lineNum+1) + " word number: " + wordNum);
        }
 

    }
}