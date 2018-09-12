package xyz.hyfree.sinteam.dmobile.entity;

/**
 * Created by Administrator on 2018/8/29.
 */
public class History {
    private String uid;
    private String g_name;
    private int g_pic;
    private String g_price;

    public History(String uid, String g_name, int g_pic, String g_price) {
        this.uid = uid;
        this.g_name = g_name;
        this.g_pic = g_pic;
        this.g_price = g_price;
    }

    public History() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public int getG_pic() {
        return g_pic;
    }

    public void setG_pic(int g_pic) {
        this.g_pic = g_pic;
    }

    public String getG_price() {
        return g_price;
    }

    public void setG_price(String g_price) {
        this.g_price = g_price;
    }
}
