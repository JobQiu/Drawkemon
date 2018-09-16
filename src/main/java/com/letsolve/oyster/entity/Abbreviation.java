package com.letsolve.oyster.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author xavier.qiu
 * 8/1/18 8:06 PM
 */

@Data
@Entity
public class Abbreviation {

    // for example cs224n is cs224n nlp, etc
    // slp is speech and language progress
    // ml is machine learning, etc

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String abbreviation;

    private String content;
}
