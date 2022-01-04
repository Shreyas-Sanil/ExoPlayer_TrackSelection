package com.example.exo;

/**
 * Created by Shreyas on 08-12-2017.
 */

public class Video_List {

    private String id,title,subtitle,price,image;



    public Video_List() {
    }

    public Video_List(String id, String title, String subtitle,String Image,String price) {
        super();
        this.title = title;
        this.id = id;
        this.subtitle = subtitle;
        this.image = Image;
        this.price = price;
    }

    public String Get_Id() {
        return id;
    }

    public void Set_Id(String id) {
        this.id = id;
    }

    public String Get_Title() {
        return title;
    }

    public void Set_Title(String title) {
        this.title = title;
    }

    public String Get_Subtitle() {
        return subtitle;
    }

    public void Set_Subtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String Get_Image() {
        return image;
    }

    public void Set_Image(String image) {
        this.image = image;
    }

    public String Get_Price() {
        return price;
    }

    public void Set_Price(String price) {
        this.price = price;
    }




}
