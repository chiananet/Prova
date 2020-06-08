package com.digital.prova.jsndata;

import com.google.gson.annotations.SerializedName;

public class JsnData {
    private static final String TAG = "JsnData";
    @SerializedName("id_category")
    private final String id_category;
    @SerializedName("name")
    private final String name;
    @SerializedName("img_category")
    private final Object img_category;

    public JsnData(String id_category, String name, String[] img_category) {
        this.id_category = id_category;
        this.name = name;
        this.img_category = img_category;
    }

    public String getIdCategory() {
        return id_category;
    }

    public String getName() {
        return name;
    }

    public String getImageCategory() {
        SplitLinks spL = new SplitLinks();
        String jsn = "JsnData";
        return spL.ImageUrl(img_category.toString(), jsn);
    }
}

