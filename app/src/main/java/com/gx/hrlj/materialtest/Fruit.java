package com.gx.hrlj.materialtest;

import java.io.Serializable;

/**
 * Created by 777 on 2018/7/7.
 */
public class Fruit {
    private String name;
    private int imageId;

    public Fruit(String name, int imageid) {
        this.name = name;
        imageId = imageid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
