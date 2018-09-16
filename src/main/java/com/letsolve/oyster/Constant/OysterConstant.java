package com.letsolve.oyster.Constant;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xavier.qiu
 * 8/1/18 6:54 PM
 */

public class OysterConstant {

    public static final String VOCABULARY = "voca";

    public static final String NOTE = "note";
    public static final String NODE = "node";
    public static final String IDEA = "idea";
    public static final String PROGRESS = "prog";
    public static final String FIT = "fit";

    public static final Set<String> TYPES;

    static {
        TYPES = new HashSet<>();
        TYPES.add(NOTE);
        TYPES.add(NODE);
        TYPES.add(IDEA);
        TYPES.add(PROGRESS);
        TYPES.add(FIT);

    }

}
