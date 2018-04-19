package gon.cue.crud;

import gon.cue.utils.Commons;

public interface UpdateDocuments extends Commons{
    public void updateOneDocument();

	public void updateManyDocument();

	public void updateDocumentWithCurrentDate();
}
