package kz.growit.smartservice3.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jean on 9/18/2015.
 */
public class Category {
    private int Id;
    private String Description;
    private int ProfilesCount;

    public Category(JSONObject jsonObject) {
        try {
            this.Id = jsonObject.getInt("Id");
            this.Description = jsonObject.getString("Description");
            this.ProfilesCount = jsonObject.getInt("ProfilesCount");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return Id;
    }

    public String getDescription() {
        return Description;
    }

    public int getProfilesCount() {
        return ProfilesCount;
    }
}
