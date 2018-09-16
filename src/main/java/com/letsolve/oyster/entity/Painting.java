package com.letsolve.oyster.entity;

import lombok.Data;

/**
 * @author xavier.qiu
 * 9/15/18 9:36 PM
 */
@Data
public class Painting {


    private String nickname;
    private String imageUrl;

    private int like;
    private int dislike;

    private String name;

    public Painting(String nickname, String imageUrl, int like, int dislike, String name) {
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.like = like;
        this.dislike = dislike;
        this.name = name;
    }

    public Painting() {
    }

    /***
     *
     *
     * @param value 0_0_http://localhost:8083/downloadFile/blob
     * @param key image_ijfowirej_gnwejgoiw_5015-695d-43f6-9a83-11edc7ba6749
     * @return
     * @throws
     **/
    public Painting(String key, String value) {
        int t1 = key.indexOf('_');
        key = key.substring(t1 + 1);
        t1 = key.indexOf('_');
        this.nickname = key.substring(0, t1);
        key = key.substring(t1 + 1);

        t1 = key.indexOf('_');
        this.name = key.substring(0, t1);
        key = key.substring(t1 + 1);

        t1 = value.indexOf('_');
        this.like = Integer.valueOf(value.substring(0, t1));
        value = value.substring(t1 + 1);

        t1 = value.indexOf('_');
        this.dislike = Integer.valueOf(value.substring(0, t1));
        value = value.substring(t1 + 1);

        t1 = value.indexOf('_');
        this.imageUrl = value.substring(t1 + 1);


    }

    public static void main(String[] args) {
        String key = "image_ijfowirej_gnwejgoiw_5015-695d-43f6-9a83-11edc7ba6749";
        String value = "0_0_http://localhost:8083/downloadFile/blob";

        Painting p = new Painting(key, value);

        System.out.println("123");

    }
}
