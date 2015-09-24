package kz.growit.smartservice3.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.rey.material.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kz.growit.smartservice3.R;
import kz.growit.smartservice3.adapters.ProfileAdapter;
import kz.growit.smartservice3.models.Specialization;
import kz.growit.smartservice3.models.UserProfile;
import kz.growit.smartservice3.singleton.AppController;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayListFragment extends Fragment {

    private Spinner spinnerUserProfiles;
    private View view;

    public DisplayListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_display_list, container, false);


        if (AppController.getInstance().getSelectedCategoryId() == 0) {
            AppController.getInstance().setSelectedCategoryId(19);
        }
        AppController.getInstance().setSelectedCityId(1);
        AppController.getInstance().setSelectedRegionId(1);

        String url = "http://smartservice.kz/api/UserProfilesApi/SearchUserProfiles?SearchQuery=&CityId=" +
                AppController.getInstance().getSelectedCityId() +
                "&RegionId=" +
                AppController.getInstance().getSelectedRegionId() +
                "&ServiceCategoryId=" +
                AppController.getInstance().getSelectedCategoryId();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url,
                new JSONObject(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<UserProfile> arrayList = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                UserProfile temp = new UserProfile(response.getJSONObject(i));
                                arrayList.add(temp);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        AppController.getInstance().setUserProfiles(arrayList);
                        //String blah = "blah";


                        //trying to display UserProfiles info here
                        ArrayList<UserProfile> arrayListUserProfiles = AppController.getInstance().getUserProfiles();

                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.userProfilesRV);
                        ProfileAdapter myAdapter = new ProfileAdapter(arrayListUserProfiles, getActivity());
                        recyclerView.setHasFixedSize(true);
                        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
                        recyclerView.setLayoutManager(llm);

                        recyclerView.setAdapter(myAdapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("UserProf request error", error.getMessage());
                    }
                });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest, "UserProfile request");


        //spinner with list of specializations
        Spinner spinner1 = (Spinner) view.findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        ArrayList<Specialization> arrayList = AppController.getInstance().getSpecializations();
        for (int i = 0; i < arrayList.size(); i++) {
            list.add(arrayList.get(i).getDescription());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
