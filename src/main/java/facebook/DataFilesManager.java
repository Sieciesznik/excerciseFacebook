package facebook;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DataFilesManager extends DataManagerBase{

    File dir = new File("facebookFiles");
    Map<Integer, String> fileBase;
    private Set<Facebook> allRecords = null;

    public DataFilesManager(){
        this.fileBase = new HashMap<>();
        try {
            this.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    Facebook getRecord(int id) {
        File file = new File(fileBase.get(id));
        try {
            return new Facebook(new JSONObject(new String(Files.readAllBytes(Paths.get(file.getPath())))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    Set<Facebook> getAllRecords() throws java.io.IOException{
        if(allRecords == null) {
            allRecords = new HashSet<>();
            File[] directoryListing = dir.listFiles();

            if (directoryListing != null) {
                for (File file : directoryListing) {
                    Facebook tempFacebook = new Facebook(new JSONObject(new String(Files.readAllBytes(Paths.get(file.getPath())))));
                    allRecords.add(tempFacebook);
                }
            } else {
                throw new java.io.IOException("There is no such directory: " + dir.toString());
            }
            return allRecords;
        }
        else{
            return allRecords;
        }
    }

    public void refresh() throws java.io.IOException{

        JSONObject tempJSON;
        int counter = 0;

        File[] directoryListing = dir.listFiles();

        if (directoryListing != null) {
            for (File file : directoryListing) {

                String path = file.toString();
                tempJSON = new JSONObject(new String(Files.readAllBytes(Paths.get(file.getPath()))));
                int id = tempJSON.getInt("id");
                if(fileBase.put(id, path) != null){
                    counter++;
                }
            }
            System.out.println("Number of object overwritten: " + counter);
        }
        else {
          throw new java.io.IOException("There is no such directory: " + dir.toString());

        }
    }

}
