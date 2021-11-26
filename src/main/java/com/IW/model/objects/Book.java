package com.IW.model.objects;

import com.IW.interfaces.IBeans;

public class Book implements IBeans.IBook {
    private long id;

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
