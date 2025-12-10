import java.io.*;

public class FileHandlingMain {
    public static void main(String[] args) throws IOException {
        File file=new File("example.txt");  // just creating a file object "File = folder/file manager"

        if(file.exists()){
            System.out.println("File already exists.");
        } else {
            System.out.println("File does not exist. Creating new file.");
            file.createNewFile(); // actually creates the file on disk
        }

        FileWriter fileWriter=new FileWriter(file); //FileWriter is used to write data to the file
        fileWriter.write("Hello, World!\n");   //write the data to file
        fileWriter.write("This is a sample file.\n");
        fileWriter.close(); // save the content and close

        //Reading File using FileReader

        FileReader fileReader = new FileReader(file); // FileReader is used to read data from the file
        while(true){
            int data = fileReader.read(); // read one character at a time
            if(data == -1){ // -1 indicates end of file
                break;
            }
            System.out.print((char)data); // cast int to char and print
        }
        fileReader.close(); // close the reader

        // Making reading easier Line by Line using BufferedReader
        System.out.println("______________________________");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file)); // or you could have just given new BufferedReader(fileReader)
        String line= bufferedReader.readLine();

        while(line!=null){
            System.out.println(line);
            line= bufferedReader.readLine();
        }

        bufferedReader.close(); // close the reader

        //        SUMMARY SO FAR (Very Simple)

        //Task	            | What to Use	    | Why
        //Create File	    | File	            | Represents a file
        //Write into File	| FileWriter	    | Writes characters
        //Read from File	| FileReader	    | Reads characters
        //Read line-by-line	| BufferedReader	| Easier and faster

        // Appending the changes in file instead of making new file everytime

        FileWriter fileWriterappend= new FileWriter(file,true); // true indicates append mode
        fileWriterappend.write("Appended Line 1\n");
        fileWriterappend.close();

        bufferedReader = new BufferedReader(new FileReader(file));
        String line2 = bufferedReader.readLine();
        while(line2 != null){
            System.out.println(line2);
            line2 = bufferedReader.readLine();
        }
        bufferedReader.close(); //File wont be deleted if the reader is open

        //Deleting File
        if(file.delete()){
            System.out.println("File deleted successfully");
        }else{
            System.out.println("File not found some error occurred");
        }

        //Create Folders
        File folder = new File("new Folder");
        folder.mkdir();

        //Create Nested folders
        File nestedFolders = new File("parentFolder/childFolder/grandChildFolder");
        nestedFolders.mkdirs();

        File listFolder = new File("parentFolder"); //Listing files inside a folder

        String[] files = listFolder.list();
        for(String f : files) {
            System.out.println(f);
        }

        //Or using File[] and listFiles()

        File[] fileFiles = listFolder.listFiles();
        for(File f : fileFiles) {
            System.out.println(f.getName());
        }

        //Try Catch

        //Bas upar wala code ko try catch me daal do
        //Tweak in that method if you declare the resources inside the () you dont have to worry
        //about the closing part it will be closed automatically
        //example- try (FileReader fr = new FileReader("data.txt")){} catch(Exception e){}

    }
}
