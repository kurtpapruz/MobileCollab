package com.example.studentvotingsystem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PositionPagerAdapter extends RecyclerView.Adapter<PositionPagerAdapter.PositionViewHolder> {
    private Context context;
    private List<JSONObject> positionList;

    public PositionPagerAdapter(Context context, List<JSONObject> positionList) {
        this.context = context;
        this.positionList = positionList;
    }

    @NonNull
    @Override
    public PositionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_position_page, parent, false);
        return new PositionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PositionViewHolder holder, int position) {
        try {
            JSONObject positionObj = positionList.get(position);
            String positionName = positionObj.getString("position_name");
            holder.positionTitle.setText(positionName);

            JSONArray candidates = positionObj.getJSONArray("candidates");
            holder.radioGroup.removeAllViews();

            for (int i = 0; i < candidates.length(); i++) {
                JSONObject candidate = candidates.getJSONObject(i);
                String name = candidate.getString("name");
                String imagePath = candidate.optString("image_path", "");

                // Create vertical layout per candidate
                LinearLayout candidateLayout = new LinearLayout(context);
                candidateLayout.setOrientation(LinearLayout.VERTICAL);
                candidateLayout.setGravity(Gravity.CENTER_HORIZONTAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f
                );
                candidateLayout.setLayoutParams(layoutParams);
                candidateLayout.setPadding(8, 8, 8, 8);

                // ImageView for candidate image
                ImageView imageView = new ImageView(context);
                int size = (int) (100 * context.getResources().getDisplayMetrics().density);
                LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(size, size);
                imageView.setLayoutParams(imageParams);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                Glide.with(context)
                        .load("http://10.0.2.2:8000/storage/" + imagePath)
                        .placeholder(R.drawable.placeholder)
                        .into(imageView);

                // RadioButton for candidate name
                RadioButton radioButton = new RadioButton(context);
                radioButton.setText(name);
                radioButton.setGravity(Gravity.CENTER);
                radioButton.setButtonTintList(ContextCompat.getColorStateList(context, R.color.black));

                // Only one can be selected per page
                radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        if (holder.checkedButton != null && holder.checkedButton != radioButton) {
                            holder.checkedButton.setChecked(false);
                        }
                        holder.checkedButton = radioButton;
                    } else {
                        if (holder.checkedButton == radioButton) {
                            holder.checkedButton = null;
                        }
                    }
                });

                // Add views to layout and layout to group
                candidateLayout.addView(imageView);
                candidateLayout.addView(radioButton);
                holder.radioGroup.addView(candidateLayout);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return positionList.size();
    }

    static class PositionViewHolder extends RecyclerView.ViewHolder {
        TextView positionTitle;
        RadioGroup radioGroup;
        RadioButton checkedButton = null;

        PositionViewHolder(View itemView) {
            super(itemView);
            positionTitle = itemView.findViewById(R.id.positionTitle);
            radioGroup = itemView.findViewById(R.id.candidatesRadioGroup);

            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                if (checkedId == -1) {
                    checkedButton = null;
                    return;
                }

                RadioButton newlyChecked = group.findViewById(checkedId);
                if (checkedButton == newlyChecked) {
                    group.clearCheck();
                    checkedButton = null;
                } else {
                    checkedButton = newlyChecked;
                }
            });
        }
    }
}
