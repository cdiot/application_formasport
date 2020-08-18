package fr.formasport.catalogue.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.formasport.catalogue.R;
import fr.formasport.catalogue.models.Formation;

public class CustomListView extends ArrayAdapter<Formation> {

    Context context;
    List<Formation> arrayListFormation;

    /**
     *
     * @param context
     * @param arrayListFormation
     */
    public CustomListView(@NonNull Context context, List<Formation> arrayListFormation) {
        super(context, R.layout.activity_custom_list_view, arrayListFormation);

        this.context = context;
        this.arrayListFormation = arrayListFormation;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_custom_list_view, null, true);


        TextView tv_id = view.findViewById(R.id.tv_id);
        TextView tv_title = view.findViewById(R.id.tv_titre);

        tv_id.setText(arrayListFormation.get(position).getId_formation());
        tv_title.setText(arrayListFormation.get(position).getTitle());

        return view;
    }
}