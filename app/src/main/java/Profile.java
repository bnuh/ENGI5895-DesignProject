package dependency.greendao.test.tinder.directional;

public class Profile {

    public Profile(String id){
        this.id = id;
    }

    String tweet;
    String tweetID;
    String name;
    String location;
    String imageURL;
    String username;
    String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return imageURL;
    }

    public void setImage(String image) {
        this.imageURL = image;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public void setTweetID(String tweetID) {
        this.tweetID = tweetID;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }

    public String getLocation() {
        return "Temp";
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
