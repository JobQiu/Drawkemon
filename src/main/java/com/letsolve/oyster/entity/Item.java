package com.letsolve.oyster.entity;

import com.letsolve.oyster.util.DateUtil;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author xavier.qiu
 * 7/27/18 8:56 PM
 */
@Data
@Entity
public class Item {

    /**
     * .node knowledge node, used to build a knowledge tree
     * .note similar to node.
     * .idea an idea
     * .fit daily exercise,
     * .progress for example shuati, leetcode, a certain course, etc.
     */
    private String type;

    private boolean openView;

    @Transient
    public void setPublic(boolean public_) {
        this.openView = public_;
    }

    @Transient
    public boolean isPublic() {
        return openView;
    }

    private String imageUrl;
    private int frequency = 1;

    private boolean archived;

    private String tagId;

    private Date lastModifiedTime = new Date();

    private long userId;

    /**
     * the probability for a question to be valid
     */
    private float valid = 0.0f;

    /**
     * -1 give up,0 unsolved, 1 processing, 2 done
     * the number of progress, like the chapter number
     */
    private int status = 0;

    public String getLastModifiedTimeStr() {

        return DateUtil.toYYYYMMDD(lastModifiedTime);
    }

    public boolean getValidStr() {
        if (valid > 0.5) {
            return true;
        }
        return false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String content;

    public Item() {
    }

    public Item(long userId, String content) {
        this.userId = userId;
        this.content = content.trim();
    }

    public Item(String type, long userId, String content, int status, float valid, String imageUrl) {
        this.userId = userId;
        this.content = content.trim();
        this.status = status;
        this.valid = valid;
        this.type = type;
        this.imageUrl = imageUrl;
    }

    public Item(Date lastModifiedTime, long userId, float valid, int status, String content) {

        this.lastModifiedTime = lastModifiedTime;
        this.userId = userId;
        this.valid = valid;
        this.status = status;
        this.content = content;
    }

    public Item(String type, boolean openView, String imageUrl, int frequency, boolean archived, String tagId, Date lastModifiedTime, long userId, float valid, int status, String content) {
        this.type = type;
        this.openView = openView;
        this.imageUrl = imageUrl;
        this.frequency = frequency;
        this.archived = archived;
        this.tagId = tagId;
        this.lastModifiedTime = lastModifiedTime;
        this.userId = userId;
        this.valid = valid;
        this.status = status;
        this.content = content;
    }
}
