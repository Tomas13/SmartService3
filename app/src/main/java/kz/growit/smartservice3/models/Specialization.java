package kz.growit.smartservice3.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jean on 9/22/2015.
 */
public class Specialization {
    private  String Description;
    private  int Id, ProfilesCount;

    public Specialization(JSONObject jsonObject) {
        try {
            this.Id = jsonObject.getInt("Id");
            this.ProfilesCount = jsonObject.getInt("ProfilesCount");
            this.Description = jsonObject.getString("Description");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getDescription() {
        return Description;
    }

    public int getId() {
        return Id;
    }

    public int getProfilesCount() {
        return ProfilesCount;
    }
}
