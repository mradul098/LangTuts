import java.io.*;
import java.util.ArrayList;

public class FileHandlingExercise {
    public static void main(String[] args) throws IOException {
        File file = new File("exercise.txt");
        ArrayList<String> arrayList = new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line=bufferedReader.readLine();
        while(line!=null){
            if(line.equals("After this line we need to insert")){
                arrayList.add(line);
                arrayList.add("New line ooooooooooooooooo");
            }else{
                arrayList.add(line);
            }
            line=bufferedReader.readLine();
        }

        FileWriter fileWriter= new FileWriter(file);
        for(String str:arrayList){
            fileWriter.write(str+"\n");
        }
        fileWriter.close();

    }
}
