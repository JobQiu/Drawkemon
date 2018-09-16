package com.letsolve.oyster.entity;

import lombok.Data;

/**
 * @author xavier.qiu
 * 9/15/18 11:42 PM
 */
@Data
public class MiniPainting {

    private String url;
    private Integer like = 0;
    private Integer dislike = 0;

    public String toString() {
        return like.toString() + "_" + dislike.toString() + "_" + url;
    }

    public MiniPainting(String test) {

        int t1 = test.indexOf("_");
        int n1 = Integer.valueOf(test.substring(0, t1));
        test = test.substring(t1 + 1);
        int t2 = test.indexOf("_");
        int n2 = Integer.valueOf(test.substring(0, t1));
        url = test.substring(t2+1);
        like = n1;
        dislike = n2;

    }

    public MiniPainting(String url, Integer like, Integer dislike) {
        this.url = url;
        this.like = like;
        this.dislike = dislike;
    }

    public MiniPainting() {
    }

    public static void main(String[] args) {
        String test = "123_456_http://teegf";
        MiniPainting m = new MiniPainting(test);

        System.out.println("123");

    }
}
