package kz.growit.smartservice3.activities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kz.growit.smartservice3.R;
import kz.growit.smartservice3.adapters.CategoryRVAdapter;
import kz.growit.smartservice3.models.Category;
import kz.growit.smartservice3.models.Specialization;
import kz.growit.smartservice3.singleton.AppController;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);

        //creating an account header
        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(getResources().getDrawable(R.drawable.profile))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();


        //NAVIGATION DRAWER Добавляем
        AppController.getInstance().getDrawer(this, toolbar, headerResult);



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.categoriesRV);
        ArrayList<Category> categories = AppController.getInstance().getCategories();
        ArrayList<Drawable> icons = new ArrayList<>();

        icons.add(getResources().getDrawable(R.drawable.cat1));
        icons.add(getResources().getDrawable(R.drawable.cat2));
        icons.add(getResources().getDrawable(R.drawable.cat3));
        icons.add(getResources().getDrawable(R.drawable.cat4));
        icons.add(getResources().getDrawable(R.drawable.cat5));
        icons.add(getResources().getDrawable(R.drawable.cat6));
        icons.add(getResources().getDrawable(R.drawable.cat7));
        icons.add(getResources().getDrawable(R.drawable.cat8));
        icons.add(getResources().getDrawable(R.drawable.cat9));
        icons.add(getResources().getDrawable(R.drawable.cat10));
        icons.add(getResources().getDrawable(R.drawable.cat11));
        icons.add(getResources().getDrawable(R.drawable.cat12));
        icons.add(getResources().getDrawable(R.drawable.cat13));
        icons.add(getResources().getDrawable(R.drawable.cat14));
        icons.add(getResources().getDrawable(R.drawable.cat15));

        for (int i = 0; i < icons.size(); i++) {
            icons.get(i).setColorFilter(new
                    PorterDuffColorFilter(Color.parseColor("#FFFFFFFF"), PorterDuff.Mode.SRC_ATOP));
        }

        CategoryRVAdapter myAdapter = new CategoryRVAdapter(categories, icons);

        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(llm);

        recyclerView.setAdapter(myAdapter);

        if(AppController.getInstance().getSelectedCategoryId()==0){
            AppController.getInstance().setSelectedCategoryId(1);
        }
        String url = "http://smartservice.kz/api/SpecializationsApi/GetCategorySpecializations/" +
                AppController.getInstance().getSelectedCategoryId();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url,
                new JSONObject(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Specialization> arrayList = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                Specialization temp = new Specialization(response.getJSONObject(i));
                                arrayList.add(temp);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        AppController.getInstance().setSpecializations(arrayList);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Spec request error",error.getMessage());
                    }
                });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest, "Spec request");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
