package com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.com.greentech.jyotirmay.cleardebt.recycler_handler;

/**
 * Created by Jyotirmay on 19-Jan-18.
 */

public class DataBean {

    private long id;
    private String value;
    private String category;
    private String date;
    private String name;
    private String acc_type;

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
