package kz.growit.smartservice3.singleton;

import android.app.Activity;
import android.app.Application;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

import kz.growit.smartservice3.models.Category;
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
    private int SelectedCityId, SelectedRegionId, SelectedCategoryId, SelectedSpecializationId;
    // end of custom vars



    //my methods


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

    public Drawer getDrawer(Activity activity, Toolbar toolbar){
        return new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        switch (i) {
                            case 0:
                                return false;
                            default:
                                return false;
                        }
                    }
                })
                .build();
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


}
