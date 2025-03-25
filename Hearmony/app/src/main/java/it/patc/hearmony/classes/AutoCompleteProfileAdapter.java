package it.patc.hearmony.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

import it.patc.hearmony.R;

public class AutoCompleteProfileAdapter extends ArrayAdapter<ProfileItem>
{
    private List<ProfileItem> profileListFull;
    public AutoCompleteProfileAdapter(@NonNull Context context, @NonNull List<ProfileItem> profileList)
    {
        super(context, 0, profileList);
        profileListFull = new ArrayList<>(profileList);
    }

    @NonNull
    @Override
    public Filter getFilter()
    {
        return profileFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.profile_autocomplite_row, parent, false
            );
        }

        TextView textViewName = convertView.findViewById(R.id.name_view_user);
        ShapeableImageView image = convertView.findViewById(R.id.image_view_propic);

        ProfileItem profileItem = getItem(position);

        if(profileItem != null)
        {
            textViewName.setText(profileItem.getName());
            image.setImageResource(profileItem.getPropic());
        }
        return convertView;
    }

    private Filter profileFilter = new Filter()
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            FilterResults results = new FilterResults();
            List<ProfileItem> suggestions = new ArrayList<>();

            if(constraint == null || constraint.length() == 0)
            {
                suggestions.addAll(profileListFull);
            }
            else
            {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(ProfileItem item : profileListFull)
                {
                    if(item.getName().toLowerCase().contains(filterPattern))
                    {
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue)
        {
            return ((ProfileItem) resultValue).getName();
        }
    };
}
