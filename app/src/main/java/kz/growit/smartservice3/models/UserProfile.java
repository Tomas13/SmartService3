package kz.growit.smartservice3.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jean on 9/22/2015.
 */
public class UserProfile {
    private int Id, Rating,RatingCount;
    private String Username;
    private String NameSurname;
    private String ProfileImageUrl;



    private String ProfileUpdateDate;
    private String Latitude,Longitude;
    private ArrayList<String> AllPictures,AllBigPictures;

    public UserProfile(JSONObject jsonObject){
        try {
            this.Id = jsonObject.getInt("Id");
            this.Rating = jsonObject.getInt("Rating");
            this.RatingCount = jsonObject.getInt("RatingCount");
            this.Username = jsonObject.getString("Username");
            this.NameSurname = jsonObject.getString("NameSurname");
            this.ProfileImageUrl = jsonObject.getString("ProfileImageUrl");
            this.ProfileUpdateDate = jsonObject.getString("ProfileUpdateDate");
            this.Latitude = jsonObject.getString("Latitude");
            this.Longitude = jsonObject.getString("Longitude");
            JSONArray allPictures =  jsonObject.getJSONArray("AllPictures");
            JSONArray allBigPics =  jsonObject.getJSONArray("AllBigPictures");
            this.AllPictures = new ArrayList<>();
            this.AllBigPictures = new ArrayList<>();
            for (int i = 0; i < allPictures.length(); i++) {
                this.AllPictures.add(allPictures.getString(i));
            }
            for (int i = 0; i < allBigPics.length(); i++) {
                this.AllBigPictures.add(allBigPics.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getProfileUpdateDate() {
        return ProfileUpdateDate;
    }


    public String getProfileImageUrl() {
        return ProfileImageUrl;
    }

    public int getId() {
        return Id;
    }

    public String getUsername() {
        return Username;
    }

    public String getNameSurname() {
        return NameSurname;
    }
}
