package com.nwords.interactors;

import com.nwords.entity.WordType;

public class TrainingParameters {

    public Integer maxAmountOfWords;            // amount of words for 1 training session

    public Integer domain;                   // words domain, for ex, family, travelling, country, etc.

    public WordType type;                // type of words for training, for ex, WordType.verb or WordType.article.

    public TrainingParameters(Integer maxAmountOfWords, Integer domain, WordType type) {

        this.maxAmountOfWords = maxAmountOfWords;
        this.domain = domain;
        this.type = type;
    }
}

