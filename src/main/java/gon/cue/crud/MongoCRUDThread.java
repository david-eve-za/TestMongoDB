package gon.cue.crud;

import gon.cue.crud.impl.InsertDocumentsImpl;

public class MongoCRUDThread implements Runnable {

    @Override
    public void run() {
        InsertDocumentsImpl insert = new InsertDocumentsImpl();
        insert.loadMethods();
    }
}
