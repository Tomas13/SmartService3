package kz.growit.smartservice3.adapters;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kz.growit.smartservice3.R;
import kz.growit.smartservice3.activities.InsideCategory;
import kz.growit.smartservice3.models.Category;


/**
 * Created by jean on 9/21/2015.
 */
public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.CategoryViewHolder>{

    private ArrayList<Category> categories;
    private ArrayList<Drawable> icons;

    public CategoryRVAdapter(ArrayList<Category> categoryList, ArrayList<Drawable> icons) {
        this.categories = categoryList;
        this.icons = icons;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.category_row, parent, false);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSearch = new Intent(v.getContext(), InsideCategory.class);
                v.getContext().startActivity(goToSearch);

            }
        });
        return new CategoryViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.categoryName.setText(categories.get(position).getDescription());
        holder.profilesCount.setText(String.valueOf(categories.get(position).getProfilesCount()));
        holder.imageView.setImageDrawable(icons.get(position));

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{
        protected TextView categoryName, profilesCount;
        protected ImageView imageView;
        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryName = (TextView) itemView.findViewById(R.id.categoryNameCategoryRow);
            profilesCount = (TextView) itemView.findViewById(R.id.profilesCountCategoryRow);
            imageView = (ImageView) itemView.findViewById(R.id.thumbnailCategoryRow);

        }
    }
}
