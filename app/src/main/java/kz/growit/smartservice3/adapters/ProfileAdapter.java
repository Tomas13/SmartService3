package kz.growit.smartservice3.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import kz.growit.smartservice3.R;
import kz.growit.smartservice3.models.Category;
import kz.growit.smartservice3.models.UserProfile;

/**
 * Created by jean on 9/23/2015.
 */
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.UserProfileViewHolder> {

    private ArrayList<UserProfile> userProfiles;

    public ProfileAdapter(ArrayList<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    @Override
    public UserProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserProfileViewHolder(LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.profile_row, parent, false));
    }

    @Override
    public void onBindViewHolder(UserProfileViewHolder holder, int position) {
        holder.profileUserName.setText(userProfiles.get(position).getNameSurname());
        holder.updateDateProfileRow.setText(String.valueOf(userProfiles.get(position).getProfileUpdateDate()));
        //holder.ProfileImageUrl.setImageDrawable(userProfiles.get(position).getProfileImageUrl());
        holder.ProfileImageUrl.setImageDrawable((Drawable.createFromPath("smartservice.kz/"+userProfiles.get(position).getProfileImageUrl())));
    }

    @Override
    public int getItemCount() {
        return userProfiles.size();
    }

    public static class UserProfileViewHolder extends RecyclerView.ViewHolder {
        protected TextView profileUserName, updateDateProfileRow;
        protected ImageView ProfileImageUrl;


        public UserProfileViewHolder(View itemView) {
            super(itemView);
            profileUserName = (TextView) itemView.findViewById(R.id.userNameProfileRow);
            updateDateProfileRow = (TextView) itemView.findViewById(R.id.updateDateProfileRow);
            ProfileImageUrl = (ImageView) itemView.findViewById(R.id.ProfileImageUrl);
        }
    }
}
