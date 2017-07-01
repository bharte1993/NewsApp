package exercise.packagecom.headlines;

import android.content.Context;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;



import java.util.ArrayList;

import java.util.List;

import exercise.packagecom.headlines.Model.Articles;
import exercise.packagecom.headlines.Model.HeadlinesResponse;
import exercise.packagecom.headlines.network.headlines.ApiClient;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    public RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private List<String> mDatalist = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private List<String> mdescriptionList = new ArrayList<>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button)findViewById(R.id.id_button);

        mRecyclerView = (RecyclerView)findViewById(R.id.id_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);


    }

    public void download(View view) {

        mDatalist.removeAll(mDatalist);
        mTitleList.removeAll(mTitleList);

        mRecyclerView.setVisibility(View.GONE);

        ApiClient.getClient().getData("the-times-of-india","latest","79b94db521034213ac07e9a37af255f6")
                .enqueue(new ApiClient.Callback<HeadlinesResponse>(){

                    @Override
                    public void sucess(HeadlinesResponse response) {
                        Log.i("Does it works","YES it does");


                        for(Articles articles : response.getArticles()){

                            mTitleList.add(articles.getTile().toString());

                            mDatalist.add(articles.getUrl().toString());

                            mdescriptionList.add(articles.getDescription().toString());



                        }

                        for(int i = 0 ;i < mDatalist.size() ; i++){

                            Log.i("the url is ", mDatalist.get(i));
                        }



                        mRecyclerView.setVisibility(View.VISIBLE);


                    }

                    @Override
                    public void failure(String error) {

                        Log.i("Does it works","Try again");
                        mRecyclerView.setVisibility(View.GONE);

                    }

                    @Override
                    public void sucessError(String reason) {

                    }

    });

}


 class RecyclerViewAdapter extends RecyclerView.Adapter<Holder> {



    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout,parent,false);

        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        holder.bindData(mDatalist.get(position),mTitleList.get(position));

    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }


 }




public class Holder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    TextView mtextview;



    public Holder(final View itemView) {
        super(itemView);

        mImageView = (ImageView)itemView.findViewById(R.id.id_image_view);
        mtextview = (TextView) itemView.findViewById(R.id.id_textview);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position1 = mRecyclerView.getChildLayoutPosition(v);
                 String value = mdescriptionList.get(position1).toString();
                String value2 = mDatalist.get(position1).toString();



                // Toast.makeText(MainActivity.this, value,Toast.LENGTH_SHORT).show();




                Intent intent = new Intent(MainActivity.this , DetailsActiivity.class);
                intent.putExtra("key1",value);
                intent.putExtra("key2",value2);
                MainActivity.this.startActivity(intent);


            }
        });
    }

    public void bindData(final String url ,final String title){

        Picasso.with(MainActivity.this)
                .load(url).resize(150,150).into(mImageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });

        mtextview.setText(title);
    }


}



}

