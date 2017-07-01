package exercise.packagecom.headlines.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by abhijeet on 8/6/17.
 */

public class Articles {

    @SerializedName("title")
    @Expose

    private String title;

    public String getTile(){
        return title;
    }

    @SerializedName("urlToImage")
    @Expose

    private String url;

    public String getUrl(){
        return url;
    }

    private String description;

    public String getDescription(){

        return description;
    }

}
