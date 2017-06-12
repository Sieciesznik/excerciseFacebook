package facebook;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;

public class Facebook implements Comparable<Facebook>{

    int id;
    Date birthday;
    String firstname;
    String lastname;
    String occupation;
    Gender gender;
    FacebookCity city;
    String work;
    Set<Integer> friends;
    String school;
    String location;
    String relationship;
    Map<Integer, String> posts;

    public Facebook(JSONObject json){
        friends = new TreeSet<>();
        posts = new HashMap<>();
        this.id = json.getInt("id");
        this.birthday =  new Date(json.getBigInteger("birthday").longValue());
        this.firstname = json.getString("firstname");
        this.lastname = json.getString("lastname");
        this.occupation = json.getString("occupation");
        this.gender = (json.getString("gender") == "female") ? Gender.FEMALE : Gender.MALE;
        this.city = new FacebookCity(json.getJSONObject("city"));
        this.work = json.getString("work");
        JSONArray friendsArray = json.getJSONArray("friends");
            for(int i = 0; i < friendsArray.length(); i++) this.friends.add(friendsArray.getInt(i));
        this.school = json.getString("school");
        this.location = json.getString("location");
        this.relationship = json.getString("relationship");
        JSONArray postsArray = json.getJSONArray("posts");
            for(int i = 0; i < postsArray.length(); i++) posts.put(postsArray.getJSONObject(i).getInt("id"), postsArray.getJSONObject(i).getString("message"));

    }

    public String toString(){
        return  "\n\nid = " + this.id +
                "\nbirthday = " + this.birthday.toString() +
                "\nfirstname = " + this.firstname +
                "\nlastname = " + this.lastname +
                "\noccupation = " + this.occupation +
                "\ngender = " + this.gender.toString() +
                "\ncity: " + this.city.toString() +
                "\nwork = " + this.work +
                "\nfriends = " + this.friends.toString() +
                "\nschool = " + this.school +
                "\nlocation = " + this.location +
                "\nrelationship = " + this.relationship +
                "\nposts: " + this.posts.toString();
    }

    @Override
    public int compareTo(Facebook o) {
        int comparison = this.firstname.compareTo(o.firstname);
        if(comparison == 0) comparison = this.lastname.compareTo(o.lastname);
        return comparison;
    }
}
