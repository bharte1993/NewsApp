package exercise.packagecom.headlines.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhijeet on 8/6/17.
 */

public class HeadlinesResponse {

    @SerializedName("articles")
    @Expose
    private List<Articles> articles = null;

   public List<Articles> getArticles(){
       return articles;
   }
}
