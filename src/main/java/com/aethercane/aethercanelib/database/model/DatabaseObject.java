package com.aethercane.aethercanelib.database.model;

public class DatabaseObject {

    private boolean changed = true;

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
