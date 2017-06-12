package facebook;

import java.io.IOException;
import java.util.*;

public class DataProfileManager extends DataManagerBase implements FacebookService {
    private DataFilesManager DFM;
    private Set<Facebook> allProfiles = null;
    private Map<Integer, String> idToPostMap = null;

    DataProfileManager(){
        this.DFM = new DataFilesManager();
    }

    @Override
    public void refresh() {
        try {
            DFM.refresh();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Facebook findById(String id) {
        return DFM.getRecord(Integer.parseInt(id));
    }

    @Override
    public Map<String, Long> findMostCommonWords() {
        Long counter;
        if(idToPostMap == null) buildPostMap();
        Map<String, Long> words = new HashMap<>();
        for(Map.Entry<Integer, String> post : idToPostMap.entrySet()){
            String[] tempStringArray = post.getValue().split("[^\\w]+");
            for(String s : tempStringArray) {
                counter = words.put(s, Integer.toUnsignedLong(1));
                if(counter != null) words.put(s, counter + 1);
            }
        }
        return words;
    }

    @Override
    public Set<String> findPostIdsByKeyword(String word) {
        Set<String> result = new HashSet<>();
        if(idToPostMap == null) buildPostMap();
        for(Map.Entry<Integer, String> post : idToPostMap.entrySet()){
            if(post.getValue().contains(word)) result.add(post.getKey().toString());
        }
        return result;
    }

    @Override
    public Set<Facebook> findAll() {

        if(allProfiles == null) {
            buildAllProfiles();
        }

        return allProfiles;
    }

    private void buildAllProfiles(){
        try {
            allProfiles = DFM.getAllRecords();
            ArrayList<Facebook> sortingList = new ArrayList<>();
            for(Facebook facebook : allProfiles) {
                sortingList.add(facebook);
            }
            Collections.sort(sortingList);
            allProfiles.clear();
            for(int i = 0; i<sortingList.size(); i++) {
                allProfiles.add(sortingList.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildPostMap(){
        if(allProfiles == null) buildAllProfiles();
        idToPostMap = new HashMap<>();

        for(Facebook facebook : allProfiles){
            for(Map.Entry<Integer, String> post : facebook.posts.entrySet()){
                idToPostMap.put(post.getKey(), post.getValue());
            }
        }
    }

    boolean isIdExisting(int id){
        return DFM.fileBase.containsKey(id);
    }
}
