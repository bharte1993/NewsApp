package exercise.packagecom.headlines;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActiivity extends AppCompatActivity {
    TextView mtextView2;
    ImageView mImageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_fragment_layout);
        mtextView2 = (TextView)findViewById(R.id.id_detail_text_view);
        mImageView2 = (ImageView)findViewById(R.id.id_detail_image_view);

        Intent intent = getIntent();
       String s1 = intent.getStringExtra("key1");
        String s2 = intent.getStringExtra("key2");
        Log.i("the url is " , s2);

        Picasso.with(this).load(s2).resize(2500,2500).into(mImageView2);
        mtextView2.setText(s1);



    }
}
