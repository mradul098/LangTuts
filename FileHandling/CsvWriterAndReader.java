import java.io.*;

public class CsvWriterAndReader {

    public static void main(String[] args) throws IOException {
        //Writing

        File file= new File("students.csv");
        if(file.exists()){
            System.out.println("File exists.");
        }else{
            file.createNewFile();
        }

        String data="name, class, age\nJohn, 10, 15\nAlice, 9, 14\nBob, 11, 16";
        FileWriter fileWriter=new FileWriter(file);
        String[] strings=data.split("\n");
        for(String str:strings){
            fileWriter.write(str+"\n");
        }
        fileWriter.close();

        //Reading

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader= new BufferedReader(fileReader);
        String line= bufferedReader.readLine();

        while(line!=null){
            System.out.println(line);
            line= bufferedReader.readLine();
        }


    }

}
