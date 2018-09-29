package waka.wakamarket.wakamarket;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by alome daniel on 02 Oct 2018  been Friday
 */
public   class  Pizza {
    private String name;
    private String price;
    private String imageUrl;
    private String storeName;
    private String stAdd;
    private String stTime;
    private String storeImage;
    public Pizza(){};
    public Pizza(String name, String price, String  imageUrL, String storeName, String stAdd, String stTime, String storeImage) {
        this.name = name;
        this.price = price;
        this.imageUrl=imageUrL;
        this.storeName=storeName;
        this.stAdd=stAdd;
        this.stTime=stTime;
        this.storeImage=storeImage;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStAdd() {
        return stAdd;
    }

    public void setStAdd(String stAdd) {
        this.stAdd = stAdd;
    }

    public String getStTime() {
        return stTime;
    }

    public void setStTime(String stTime) {
        this.stTime = stTime;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }


}
