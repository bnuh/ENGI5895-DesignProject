package ENGI5895.DesignProject.BryanAndre;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;


public class Profile {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("tweetID")
    @Expose
    private String tweetID;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("tweet")
    @Expose
    private String tweet;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("location")
    @Expose
    private String location;

    public String getName() {
        return name;
    }

    public String getID() {
        return tweetID;
    }

    public void setID(String id) {
        this.tweetID = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
