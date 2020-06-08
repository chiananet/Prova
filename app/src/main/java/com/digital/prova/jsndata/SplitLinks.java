package com.digital.prova.jsndata;

public class SplitLinks {
    private static final String TAG = "SplitLinks";
    public SplitLinks() {}

    String ImageUrl(String img_category, String jsn){
        String urlImage = null;
        if(jsn.equals("JsnData")) {
            String[] url = img_category.split("preview2=");
            urlImage = url[0].replace("preview1=", "");
            urlImage = urlImage.replace(",", "");
            urlImage = urlImage.replace("{", "");

            if (urlImage.equals("")) {
                url = img_category.split("preview1=");
                urlImage = url[0].replace("preview2=", "");
                urlImage = urlImage.replace(",", "");
                urlImage = urlImage.replace("{", "");
            } else {
                url = img_category.split("preview2=");
                urlImage = url[0].replace("preview1=", "");
                urlImage = urlImage.replace(",", "");
                urlImage = urlImage.replace("{", "");
                urlImage = urlImage.replace("}", "");
            }

        }else if(jsn.equals("JsnDetail")){
            String[] url = img_category.split("icon=");
            String[] newUrl = url[1].split("\\}");
            urlImage = newUrl[0].replace("{512x512=", "");
        }
        if(urlImage != null) {
            return urlImage;
        }else{
            return "";
        }
    }
}