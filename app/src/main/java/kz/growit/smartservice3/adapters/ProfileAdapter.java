package kz.growit.smartservice3.adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Transformers.FlipHorizontalTransformer;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.transform.Transformer;

import kz.growit.smartservice3.R;
import kz.growit.smartservice3.models.UserProfile;
import kz.growit.smartservice3.singleton.AppController;

/**
 * Created by jean on 9/23/2015.
 */
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.UserProfileViewHolder> {

    private ArrayList<UserProfile> userProfiles;
    private Activity activity;
    private UserProfile temp;

    public ProfileAdapter(ArrayList<UserProfile> userProfiles, Activity activity) {
        this.userProfiles = userProfiles;
        this.activity = activity;
    }

    @Override
    public UserProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserProfileViewHolder(LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.profile_row, parent, false));
    }

    @Override
    public void onBindViewHolder(UserProfileViewHolder holder, int position) {
        temp = userProfiles.get(position);
        holder.profileUserName.setText(temp.getNameSurname());
        Date tempD = AppController.getInstance().fromServerDateToString(temp.getProfileUpdateDate());
        holder.updateDateProfileRow.setText(tempD.toString());
        //holder.ProfileImageUrl.setImageDrawable(userProfiles.get(position).getProfileImageUrl());
        // holder.ProfileImageUrl.setImageDrawable((Drawable.createFromPath("smartservice.kz/"+userProfiles.get(position).getProfileImageUrl())));
        String url = AppController.SERVERURL + temp.getProfileImageUrl();
        holder.UserProfileImage.setImageUrl(url, AppController.getInstance().getImageLoader());
        holder.ProfileImageUrl.removeAllSliders();
        if (temp.getAllPictures().size() > 0) {
            for (int i = 0; i < temp.getAllPictures().size(); i++) {
                DefaultSliderView textSliderView = new DefaultSliderView(activity);
                textSliderView
                        .image(AppController.SERVERURL + temp.getAllPictures().get(i));
                holder.ProfileImageUrl.addSlider(textSliderView);
            }
            holder.ProfileImageUrl.setPresetTransformer(SliderLayout.Transformer.FlipPage);
        }else{
            DefaultSliderView defaultSliderView = new DefaultSliderView(activity);
            defaultSliderView.image(R.drawable.temp_profile);
            holder.ProfileImageUrl.addSlider(defaultSliderView);
            holder.ProfileImageUrl.stopAutoCycle();
        }
        holder.callText.setText("Позвонить");
        holder.callText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:+" + temp.getUsername()));
                activity.startActivity(callIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userProfiles.size();
    }

    public static class UserProfileViewHolder extends RecyclerView.ViewHolder {
        protected TextView profileUserName, updateDateProfileRow, callText;
        protected NetworkImageView UserProfileImage;
        protected SliderLayout ProfileImageUrl;


        public UserProfileViewHolder(View itemView) {
            super(itemView);
            profileUserName = (TextView) itemView.findViewById(R.id.userNameProfileRow);
            UserProfileImage = (NetworkImageView) itemView.findViewById(R.id.lvis_photo);
            updateDateProfileRow = (TextView) itemView.findViewById(R.id.updateDateProfileRow);
            ProfileImageUrl = (SliderLayout) itemView.findViewById(R.id.ProfileImageUrl);
            callText = (TextView) itemView.findViewById(R.id.lvis_like);
        }
    }
}
