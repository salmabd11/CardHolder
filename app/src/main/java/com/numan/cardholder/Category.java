package com.numan.cardholder;

public class Category {
    private String name,email,phone,MainBuisness,url;
    private String ImageUri;
    private String ImageUri1;

    public Category() {
    }

    public Category(String name, String email,
                    String phone, String mainBuisness,
                    String url, String imageUri,
                    String imageUri1) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        MainBuisness = mainBuisness;
        this.url = url;
        ImageUri = imageUri;
        ImageUri1 = imageUri1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMainBuisness() {
        return MainBuisness;
    }

    public void setMainBuisness(String mainBuisness) {
        MainBuisness = mainBuisness;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        ImageUri = imageUri;
    }

    public String getImageUri1() {
        return ImageUri1;
    }

    public void setImageUri1(String imageUri1) {
        ImageUri1 = imageUri1;
    }
}
