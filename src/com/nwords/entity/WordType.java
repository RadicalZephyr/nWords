package com.nwords.entity;

public enum WordType {

    noun (1),               // A noun is a type of word that represents a person, thing, or place, like mother, apple,
                            // or valley.

    verb (2),               // A verb is a type of word that describes an action or a state of being, like wiggle, walk,
                            // run, jump, be, do, have, or think.

    pronoun (3),            // A pronoun is a substitute for a noun. Some pronouns are: I, me, she, hers, he, him, it,
                            // you, they, them, etc.

    adjective (4),          // An adjective is a word that describes something (a noun). Some adjectives are: big, cold,
                            // blue, and silly. One special type of adjective is an article but here it's a separate
                            // type.

    adverb (5),             // An adverb is a word that tells "how," "when," "where," or "how much". Some adverbs are:
                            // easily, warmly, quickly, mainly, freely, often, and unfortunately.

    preposition (6),        // A preposition shows how something is related to another word. It shows the spatial
                            // (space), temporal (time), or logical relationship of an object to the rest of the
                            // sentence. The words above, near, at, by, after, with and from are prepositions.

    conjunction (7),        // A conjunction is a word that joins other words, phrases, clauses or sentences.
                            // Some conjunctions are: and, as, because, but, or, since, so, until, and while.

    interjection (8),       // An interjection is a word that expresses emotion. An interjection often starts a sentence
                            // but it can be contained within a sentence or can stand alone. Some interjections are oh,
                            // wow, ugh, hurray, eh, and ah.

    article (9);            // An article is a special type of adjective. An article is a word that introduces a noun
                            // and also limits or clarifies it; in English, the indefinite articles are a and an, the
                            // definite article is the.


    private int i;

    WordType(int i) {
        this.i = i;
    }

    public int value() {
        return this.i;
    }

    public WordType getWordType(int i) {

        for (WordType w: WordType.values()) {
            if (w.value() == i) return w;
        }
        return null;
    }

}
