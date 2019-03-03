package ie.kf.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ie.kf.R;
import ie.kf.models.Bird;

public class BirdAdapter extends ArrayAdapter<Bird> {

    private Context context;
    public List<Bird> birdList;

    public BirdAdapter(Context context, List<Bird> birdList) {
        super(context, R.layout.bird_row, birdList);
        this.context = context;
        this.birdList = birdList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.bird_row, parent, false);
        Bird bird = birdList.get(position);
        ImageView imgView = view.findViewById(R.id.rowImgView);
        TextView speciesTV = view.findViewById(R.id.rowSpeciesTV);
        TextView sexTV = view.findViewById(R.id.rowSexTV);
        TextView ageTV = view.findViewById(R.id.rowAgeTV);

        Bitmap bitmap = BitmapFactory.decodeByteArray(bird.byteArray, 0, bird.byteArray.length);

        imgView.setImageBitmap(bitmap);

        speciesTV.setText(bird.species);
        sexTV.setText(bird.sex);
        ageTV.setText(bird.age);

        return view;
    }

    @Override
    public Bird getItem(int position) {
        return birdList.get(position);
    }

    @Override
    public int getCount()
    {
        return birdList.size();
    }

}
