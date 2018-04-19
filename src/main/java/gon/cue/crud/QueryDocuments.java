package gon.cue.crud;

import gon.cue.utils.Commons;


public interface QueryDocuments extends Commons{
    public void getAllDocuments();

    public void getSpecificDocument(String operator);
}
