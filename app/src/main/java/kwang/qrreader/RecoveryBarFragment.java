package kwang.qrreader;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Oliver on 1/27/2016.
 */
public class RecoveryBarFragment extends Fragment implements View.OnClickListener{

    private ImageView top;
    private ImageButton calories, fat, protein, sugar;
    private int alpha = 200;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.recoverybar_fragment, container, false);
        top = (ImageView) view.findViewById(R.id.topimage);
        calories = (ImageButton) view.findViewById(R.id.calories);
        fat = (ImageButton) view.findViewById(R.id.fat);
        protein = (ImageButton) view.findViewById(R.id.protein);
        sugar = (ImageButton) view.findViewById(R.id.sugar);

        top.setImageAlpha(alpha);
        calories.setImageAlpha(alpha);
        fat.setImageAlpha(alpha);
        protein.setImageAlpha(alpha);
        sugar.setImageAlpha(alpha);
        calories.getBackground().setAlpha(0);
        fat.getBackground().setAlpha(0);
        protein.getBackground().setAlpha(0);
        sugar.getBackground().setAlpha(0);

        return view;
    }


    public void onClick(View v) {
    }

}


