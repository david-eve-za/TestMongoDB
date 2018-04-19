package gon.cue.crud;

import gon.cue.utils.Commons;

public interface DeleteDocuments extends Commons{
    public abstract void deleteOneDocument();

    public abstract void deleteManyDocument();
}
