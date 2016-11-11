package com.nwords.entity;

import java.util.Iterator;
import java.util.Random;

public class EntityArray<VocabularyEntity> implements Iterable<VocabularyEntity> {

    private Entity first;
    private int size;

    public EntityArray(){ size = 0;}

    public Iterator iterator() {
        return new RecordsIterator();
    }

    private class RecordsIterator implements Iterator<VocabularyEntity> {

        Entity current = first;
        Entity previous = null;

        public boolean hasNext() {
            return current != null;
        }

        public VocabularyEntity next() {
            VocabularyEntity value = current.value;
            previous = current;
            current = current.next;
            return value;
        }

        public void remove() {
            previous.next = current.next;
        }
    }

    private class Entity {

        Entity next;
        VocabularyEntity value;

        Entity(VocabularyEntity v){
            this.value = v;
            this.next = null;
        }
    }

    public EntityArray<VocabularyEntity> getShuffledArray() {

        EntityArray<VocabularyEntity> shuffled = new EntityArray<>();
        int len = size;
        int[] ids = new int[len];
        for (int i = 0; i < len; i++) ids[i] = i;
        Random rand = new Random();
        while (len >= 1) {
            int randId = rand.nextInt(len);
            Entity current = first;
            for (int j = 0; j < ids[randId]; j++) {
                current = current.next;
            }
            shuffled.add(current.value);
            int swap = ids[randId];
            ids[randId] = ids[len-1];
            ids[len-1] = swap;
            len--;
        }
        return shuffled;
    }

    public void add(VocabularyEntity value) {
        if (first == null) {
            first = new Entity(value);
        } else {
            Entity rec = first;
            while(rec.next != null) rec = rec.next;
            rec.next = new Entity(value);
        }
        size += 1;
    }

    public int size() {
        return size;
    }
}
