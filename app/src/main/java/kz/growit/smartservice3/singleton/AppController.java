package kz.growit.smartservice3.singleton;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;

import kz.growit.smartservice3.R;
import kz.growit.smartservice3.models.Category;
import kz.growit.smartservice3.models.Specialization;
import kz.growit.smartservice3.models.UserProfile;
import kz.growit.smartservice3.utils.LruBitmapCache;


/**
 * Created by jean on 9/8/2015.
 */
public class AppController extends Application{
    public static final String TAG = AppController.class
            .getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;


    private static AppController mInstance;

    //custom vars
    private ArrayList<Category> Categories = new ArrayList<>();
    private ArrayList<Specialization> Specializations = new ArrayList<>();
    private ArrayList<UserProfile> UserProfiles = new ArrayList<>();

    private int SelectedCityId, SelectedRegionId, SelectedCategoryId, SelectedSpecializationId,
            SelectedUserProfilesId;
    // end of custom vars



    //my methods


    public ArrayList<UserProfile> getUserProfiles() {
        return UserProfiles;
    }

    public void setUserProfiles(ArrayList<UserProfile> userProfiles) {
        UserProfiles = userProfiles;
    }


    public ArrayList<Specialization> getSpecializations() {
        return Specializations;
    }

    public void setSpecializations(ArrayList<Specialization> specializations) {
        Specializations = specializations;
    }

    public ArrayList<Category> getCategories() {
        return Categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        Categories = categories;
    }

    public int getSelectedCityId() {
        return SelectedCityId;
    }

    public void setSelectedCityId(int selectedCityId) {
        SelectedCityId = selectedCityId;
    }

    public int getSelectedRegionId() {
        return SelectedRegionId;
    }

    public void setSelectedRegionId(int selectedRegionId) {
        SelectedRegionId = selectedRegionId;
    }

    public int getSelectedCategoryId() {
        return SelectedCategoryId;
    }

    public void setSelectedCategoryId(int selectedCategoryId) {
        SelectedCategoryId = selectedCategoryId;
    }

    public int getSelectedSpecializationId() {
        return SelectedSpecializationId;
    }

    public void setSelectedSpecializationId(int selectedSpecializationId) {
        SelectedSpecializationId = selectedSpecializationId;
    }


    //end of my method


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }


    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }



    //creating an navigation drawer
    public Drawer getDrawer(final Activity activity, Toolbar toolbar, AccountHeader headerResult){
        return new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(activity)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(R.string.drawer_item_home)
                                .withIcon(FontAwesome.Icon.faw_home)
                                .withIdentifier(1)
                                .withBadge("99"),
                        new PrimaryDrawerItem()
                                .withName(R.string.drawer_item_free_play)
                                .withIdentifier(2)

                                .withIcon(FontAwesome.Icon.faw_gamepad),
                        new PrimaryDrawerItem()
                                .withName(R.string.drawer_item_custom)
                                .withIdentifier(3)

                                .withIcon(FontAwesome.Icon.faw_eye)
                                .withBadge("6"),
                        new SectionDrawerItem()
                                .withIdentifier(4)

                                .withName(R.string.drawer_item_settings),
                        new SecondaryDrawerItem()
                                .withName(R.string.drawer_item_help)
                                .withIdentifier(5)

                                .withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem()
                                .withName(R.string.drawer_item_open_source)
                                .withIdentifier(6)

                                .withIcon(FontAwesome.Icon.faw_question),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem()
                                .withName(R.string.drawer_item_contact)
                                .withIdentifier(7)

                                .withIcon(FontAwesome.Icon.faw_github)
                                .withBadge("12+")
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        switch (iDrawerItem.getIdentifier()) {
                            case 1:
                                Snackbar.make(view, "first selected", Snackbar.LENGTH_LONG).show();
                                return false;
                            case 2:
                                Snackbar.make(view, "second selected", Snackbar.LENGTH_LONG).show();
                                return false;
                            case 3:
                                Snackbar.make(view, "third selected", Snackbar.LENGTH_LONG).show();
                                return false;
                            case 5:
                                Toast.makeText(getApplicationContext(), "fourth choose", Toast.LENGTH_SHORT).show();
                                return false;
                            case 6:
                                Toast.makeText(getApplicationContext(), "fifth choose", Toast.LENGTH_SHORT).show();
                                return false;
                            case 8:
                                Toast.makeText(getApplicationContext(), "Setting choose", Toast.LENGTH_SHORT).show();
                                return false;

                            default:
                                return false;
                        }
                    }
                })
                .build();
    }

}
