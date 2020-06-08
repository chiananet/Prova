package com.digital.prova.jsndata;

import com.google.gson.annotations.SerializedName;

public class JsnDetail {
    private static final String TAG = "JsnDetail";
    @SerializedName("id")
    private final String id;
    @SerializedName("title")
    private final String title;
    @SerializedName("images")
    private final Object images;

    public JsnDetail(String id, String title, String[] images) {
        this.id = id;
        this.title = title;
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        SplitLinks spL = new SplitLinks();
        String jsn = "JsnDetail";
        return spL.ImageUrl(images.toString(), jsn);


    }
}
