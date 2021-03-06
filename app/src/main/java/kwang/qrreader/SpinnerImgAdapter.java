package kwang.qrreader;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * Created by Oliver on 1/28/2016.
 */
public class SpinnerImgAdapter extends ArrayAdapter<Integer>
{

    private final Activity context;

    private final Integer[] names;

    public SpinnerImgAdapter(Activity context, int textViewResourceId, Integer[] objects)
    {
        super(context, R.layout.spinner_op, objects);
        this.context = context;
        this.names = objects;
    }

    static class ViewHolder
    {
        public ImageView imageView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        return getImageView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        return getImageView(position, convertView, parent);
    }

    private View getImageView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;

        View rowView = convertView;

        if (rowView == null)
        {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.spinner_op, null, true);
            holder = new ViewHolder();
            holder.imageView = (ImageView) rowView.findViewById(R.id.icon);
            rowView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) rowView.getTag();
        }

        Integer integer = names[position];

        holder.imageView.setImageResource(integer);

        return rowView;
    }
}
