package xyz.hyfree.sinteam.dmobile;

/**
 * Created by Administrator on 2018/4/3.
 */

public class AnimeCard {
    public String imgURL="";
    public String title;
    public  Boolean AnimeStatic;
    public AnimeCard(String imgURL,String title,Boolean animeStatic){
        this.imgURL=imgURL;
        this.title=title;
        this.AnimeStatic=animeStatic;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getAnimeStatic() {
        return AnimeStatic;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAnimeStatic(Boolean animeStatic) {
        AnimeStatic = animeStatic;
    }
}
