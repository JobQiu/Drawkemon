package com.letsolve.oyster.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashMap;

/**
 * @author xavier.qiu
 * 8/1/18 6:41 PM
 */

@Data
@Entity
public class Vocabulary {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long userId;


    /**
     * word you cannot understand when read it
     */
    private String understand = "";

    /**
     * word you cannot pronounce
     */
    private String pronounce = "";

    /**
     * word you cannot catch it out when hear it
     */
    private String hearOut = "";
    /**
     * word you cannot spell
     */
    private String spell = "";
    /**
     * word you cannot use in your writing
     */
    private String useWriting = "";

    /**
     * word you cannot use in your speaking
     */
    private String useSpeaking = "";


    /**
     * finish the whole circle, those words will be stored here
     */
    private String archived = "";

    /**
     * this is a json of a map,
     * used to reflect the frequency
     * of word that appears more than once
     */
    private String moreThanOnce = "";


    public boolean addNewWord(String wordId) {
        wordId = wordId.trim();

        // 1. if the word has been archived before, and encounter again, put it in the first level
        if (this.archived.contains("," + wordId + ",") || this.archived.startsWith(wordId + ",")) {
            this.archived = this.archived.replace(wordId + ",", "");
            this.understand = this.understand + wordId + ",";
        }

        // 2. check if it has been recorded before, if so, increase its frequency
        this.understand = this.understand.replace(" ", "");
        this.pronounce = this.pronounce.replace(" ", "");
        this.hearOut = this.hearOut.replace(" ", "");
        this.spell = this.spell.replace(" ", "");
        this.useWriting = this.useWriting.replace(" ", "");
        this.useSpeaking = this.useSpeaking.replace(" ", "");

        String temp = this.understand + this.pronounce + this.hearOut + this.spell + this.useWriting + this.useSpeaking;
        if (temp.contains("," + wordId + ",")
                || temp.startsWith(wordId + ",")) {
            HashMap<String, Integer> tempMap = JSON.parseObject(moreThanOnce, HashMap.class);
            int before = tempMap.getOrDefault(wordId, 0);
            tempMap.put(wordId, before + 1);
            this.moreThanOnce = JSON.toJSONString(tempMap);
        } else {
            this.understand = this.understand + wordId.trim() + ",";
        }
        return true;
    }

    public Vocabulary() {
        this.moreThanOnce = JSON.toJSONString(new HashMap<>());

    }

    public Vocabulary(long userId) {
        this.userId = userId;
        this.moreThanOnce = JSON.toJSONString(new HashMap<>());

    }

    public Vocabulary(long userId, String understand, String pronounce, String hearOut, String spell, String useWriting, String useSpeaking, String moreThanOnce) {
        this.userId = userId;
        this.understand = understand;
        this.pronounce = pronounce;
        this.hearOut = hearOut;
        this.spell = spell;
        this.useWriting = useWriting;
        this.useSpeaking = useSpeaking;
        this.moreThanOnce = JSON.toJSONString(new HashMap<>());
    }

    public void reviewWord(String wordId) {
        wordId = wordId.trim();
        String wordId_ = wordId + ",";
        if (this.understand.contains(wordId_)) {
            this.understand = this.understand.replace(wordId_, "");
            this.pronounce = this.pronounce + wordId_;
            return;
        }

        if (this.pronounce.contains(wordId_)) {
            this.pronounce = this.pronounce.replace(wordId_, "");
            this.hearOut = this.hearOut + wordId_;
            return;
        }
        if (this.hearOut.contains(wordId_)) {
            this.hearOut = this.hearOut.replace(wordId_, "");
            this.spell = this.spell + wordId_;
            return;
        }
        if (this.spell.contains(wordId_)) {
            this.spell = this.spell.replace(wordId_, "");
            this.useWriting = this.useWriting + wordId_;
            return;
        }
        if (this.useWriting.contains(wordId_)) {
            this.useWriting = this.useWriting.replace(wordId_, "");
            this.useSpeaking = this.useSpeaking + wordId_;
            return;
        }
        if (this.useSpeaking.contains(wordId_)) {
            this.useSpeaking = this.useSpeaking.replace(wordId_, "");
            this.archived = this.archived + wordId_;
            return;
        }

        addNewWord(wordId);
    }


}
