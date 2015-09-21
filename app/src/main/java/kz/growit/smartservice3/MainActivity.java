package kz.growit.smartservice3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kz.growit.smartservice3.activities.CategoryActivity;
import kz.growit.smartservice3.models.Category;
import kz.growit.smartservice3.singleton.AppController;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Category> Categories = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            //do smth
        }else{

            final AppController myApp = AppController.getInstance();
            myApp.setSelectedCityId(1);
            myApp.setSelectedRegionId(1);
            String URL = "http://smartservice.kz/api/ServiceCategoriesApi/GetServiceCategories";
            JsonArrayRequest caterogiesRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    URL,
                    new JSONObject(),
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject current = response.getJSONObject(i);
                                    Categories.add(new Category(current));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            myApp.setCategories(Categories);
                            Intent goToSearch = new Intent(MainActivity.this, CategoryActivity.class);
                            startActivity(goToSearch);
                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Server is not available", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
            );
            myApp.addToRequestQueue(caterogiesRequest, "CategoryRequest");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
