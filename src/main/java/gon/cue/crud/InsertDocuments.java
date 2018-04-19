package gon.cue.crud;

import gon.cue.utils.Commons;

public interface InsertDocuments extends Commons{
    public void insertUsingDocument();

    public void insertUsingMap();

    public void insertSingleDocument();

    public void insertMultipleDocuments();
}
