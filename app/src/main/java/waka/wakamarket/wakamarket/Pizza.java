package waka.wakamarket.wakamarket;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by Abhi on 02 Oct 2017 002.
 */
public class  Pizza {
    private String name;
    private int imageResource;
    private String price;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Pizza(String name, int imageResource, String price) {
        this.name = name;
        this.imageResource = imageResource;
        this.price = price;
    }


}
