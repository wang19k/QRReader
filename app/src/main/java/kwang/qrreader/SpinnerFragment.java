package kwang.qrreader;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

/**
 * Created by Oliver on 1/28/2016.
 */
public class SpinnerFragment extends Fragment implements Button.OnClickListener {

    private ImageButton calories, fat, protein, sugar, ingredients;
    private int alpha = 200;
    private boolean isCal, isFat, isProtein, isSugar, isIngredient;
    private Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.spinner_test, container, false);

        calories = (ImageButton) view.findViewById(R.id.calories);
        fat = (ImageButton) view.findViewById(R.id.fat);
        protein = (ImageButton) view.findViewById(R.id.protein);
        sugar = (ImageButton) view.findViewById(R.id.sugar);

        calories.setImageAlpha(alpha);
        fat.setImageAlpha(alpha);
        protein.setImageAlpha(alpha);
        sugar.setImageAlpha(alpha);

        calories.getBackground().setAlpha(0);
        fat.getBackground().setAlpha(0);
        protein.getBackground().setAlpha(0);
        sugar.getBackground().setAlpha(0);

        calories.setOnClickListener(this);
        fat.setOnClickListener(this);
        sugar.setOnClickListener(this);
        protein.setOnClickListener(this);

        spinner = (Spinner) view.findViewById(R.id.ingspinner);
        Integer[] image = {R.drawable.ingredientes_list};
        spinner.getLayoutParams().width = 1;
        spinner.setAdapter(new SpinnerImgAdapter(this.getActivity(), R.layout.spinner_op, image));

        return view;
    }


    public void onClick(View view) {
        if (view.getId() == R.id.fat) {
            if (isFat) {
                Drawable cal;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    cal = ContextCompat.getDrawable(getContext(), R.drawable.dailyvalue);
                } else {
                    cal = getResources().getDrawable(R.drawable.dailyvalue);
                }
                fat.setImageDrawable(cal);
                isFat = false;
            } else {
                Drawable f;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    f = ContextCompat.getDrawable(getContext(), R.drawable.fat);
                } else {
                    f = getResources().getDrawable(R.drawable.fat);
                }
                fat.setImageDrawable(f);
                isFat = true;
            }
        } else if (view.getId() == R.id.calories) {
            if (isCal) {
                Drawable fat;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    fat = ContextCompat.getDrawable(getContext(), R.drawable.dailyvalue);
                } else {
                    fat = getResources().getDrawable(R.drawable.dailyvalue);
                }
                calories.setImageDrawable(fat);
                isCal = false;
            } else {
                Drawable cal;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    cal = ContextCompat.getDrawable(getContext(), R.drawable.calories);
                } else {
                    cal = getResources().getDrawable(R.drawable.calories);
                }
                calories.setImageDrawable(cal);
                isCal = true;
            }
        } else if (view.getId() == R.id.sugar) {
            if (isSugar) {
                Drawable prot;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    prot = ContextCompat.getDrawable(getContext(), R.drawable.dailyvalue);
                } else {
                    prot = getResources().getDrawable(R.drawable.dailyvalue);
                }
                sugar.setImageDrawable(prot);
                isSugar = false;
            } else {
                Drawable sug;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    sug = ContextCompat.getDrawable(getContext(), R.drawable.sugar);
                } else {
                    sug = getResources().getDrawable(R.drawable.sugar);
                }
                sugar.setImageDrawable(sug);
                isSugar = true;
            }
        } else if (view.getId() == R.id.protein) {
            if (isProtein) {
                Drawable sug;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    sug = ContextCompat.getDrawable(getContext(), R.drawable.dailyvalue);
                } else {
                    sug = getResources().getDrawable(R.drawable.dailyvalue);
                }
                protein.setImageDrawable(sug);
                isProtein = false;
            } else {
                Drawable prot;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    prot = ContextCompat.getDrawable(getContext(), R.drawable.protein);
                } else {
                    prot = getResources().getDrawable(R.drawable.protein);
                }
                protein.setImageDrawable(prot);
                isProtein = true;
            }
        } else if (view.getId() == R.id.ingredientsbutton) {
            if (isIngredient) {
                Drawable list;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    list = ContextCompat.getDrawable(getContext(), R.drawable.ingredientes_list);
                } else {
                    list = getResources().getDrawable(R.drawable.ingredientes_list);
                }
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
                ingredients.setImageDrawable(list);
                isIngredient = false;
            } else {
                Drawable ing;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ing = ContextCompat.getDrawable(getContext(), R.drawable.ingredient_tab);
                } else {
                    ing = getResources().getDrawable(R.drawable.ingredient_tab);
                }
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

                ingredients.setImageDrawable(ing);
                isIngredient = true;
            }
        }
    }
}
