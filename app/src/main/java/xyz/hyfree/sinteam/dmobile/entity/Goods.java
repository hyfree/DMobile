package xyz.hyfree.sinteam.dmobile.entity;

/**
 * Created by Administrator on 2018/8/24.
 */
public class Goods {


    private String name;
    private int pic;
    private String price;
    private String date;
    private String qualityperiod;
    private String number;

    public Goods(String name, int pic, String price, String date, String qualityperiod, String number) {
        this.name = name;
        this.pic = pic;
        this.price = price;
        this.date = date;
        this.qualityperiod = qualityperiod;
        this.number = number;
    }

    public Goods() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQualityperiod() {
        return qualityperiod;
    }

    public void setQualityperiod(String qualityperiod) {
        this.qualityperiod = qualityperiod;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
