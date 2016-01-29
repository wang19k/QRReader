package kwang.qrreader;

import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Oliver on 1/28/2016.
 */
public class IngredientsFragment extends Fragment implements Button.OnClickListener {

    private ImageButton calories, fat, protein, sugar, ingredients, bottom;
    private int alpha = 200;
    private boolean isCal, isFat, isProtein, isSugar, isIngredient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.ingredients_fragment, container, false);

        calories = (ImageButton) view.findViewById(R.id.calories);
        fat = (ImageButton) view.findViewById(R.id.fat);
        protein = (ImageButton) view.findViewById(R.id.protein);
        sugar = (ImageButton) view.findViewById(R.id.sugar);
        ingredients = (ImageButton) view.findViewById(R.id.ingredientsbutton);
        bottom = (ImageButton) view.findViewById(R.id.imageView2);

        calories.setImageAlpha(alpha);
        fat.setImageAlpha(alpha);
        protein.setImageAlpha(alpha);
        sugar.setImageAlpha(alpha);
        ingredients.setImageAlpha(alpha);
        bottom.setImageAlpha(alpha);
        calories.getBackground().setAlpha(0);
        fat.getBackground().setAlpha(0);
        protein.getBackground().setAlpha(0);
        sugar.getBackground().setAlpha(0);
        ingredients.getBackground().setAlpha(0);
        bottom.getBackground().setAlpha(0);

        calories.setOnClickListener(this);
        fat.setOnClickListener(this);
        sugar.setOnClickListener(this);
        protein.setOnClickListener(this);
        ingredients.setOnClickListener(this);

        return view;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.fat) {
            if (isFat) {
                fat.setImageResource(R.drawable.dailyvalue);
                isFat = false;
            } else {
                fat.setImageResource(R.drawable.fat);
                isFat = true;
            }
        } else if (view.getId() == R.id.calories) {
            if (isCal) {
                calories.setImageResource(R.drawable.dailyvalue);
                isCal = false;
            } else {
                calories.setImageResource(R.drawable.calories);
                isCal = true;
            }
        } else if (view.getId() == R.id.sugar) {
            if (isSugar) {
                sugar.setImageResource(R.drawable.dailyvalue);
                isSugar = false;
            } else {
                sugar.setImageResource(R.drawable.sugar);
                isSugar = true;
            }
        } else if (view.getId() == R.id.protein) {
            if (isProtein) {
                protein.setImageResource(R.drawable.dailyvalue);
                isProtein = false;
            } else {
                protein.setImageResource(R.drawable.protein);
                isProtein = true;
            }
        } else if (view.getId() == R.id.ingredientsbutton) {
            if (isIngredient) {
                //sets the four nutrients buttons to transparent
                calories.setImageAlpha(0);
                fat.setImageAlpha(0);
                protein.setImageAlpha(0);
                sugar.setImageAlpha(0);
                //makes sure that when the four nutrient buttons are transparent, they aren't changing when pressed
                calories.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                fat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                sugar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                protein.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                ingredients.setImageResource(R.drawable.ingredientes_list);
                isIngredient = false;
            } else {
                //makes the four nutrients visible again
                calories.setImageAlpha(alpha);
                fat.setImageAlpha(alpha);
                protein.setImageAlpha(alpha);
                sugar.setImageAlpha(alpha);
                //reverts the four nutrients buttons back to changing when pressed
                calories.setOnClickListener(this);
                fat.setOnClickListener(this);
                protein.setOnClickListener(this);
                sugar.setOnClickListener(this);

                ingredients.setImageResource(R.drawable.ingredient_tab);
                isIngredient = true;
            }
        }
    }
}
