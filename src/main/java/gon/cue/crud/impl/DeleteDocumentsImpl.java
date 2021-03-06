package gon.cue.crud.impl;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

import gon.cue.crud.DeleteDocuments;
import gon.cue.utils.MongoConnectionUtils;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;

public class DeleteDocumentsImpl implements DeleteDocuments {
    private static final Logger  log    = Logger.getLogger(DeleteDocumentsImpl.class);

    private MongoConnectionUtils mongo  = null;
    private MongoClient          client = null;

    public DeleteDocumentsImpl() {
        mongo = new MongoConnectionUtils();
        client = mongo.getMongoConnection();
    }

    /**
     * invoke the all deletion methods
     */
    @Override
    public void loadMethods() {
        deleteOneDocument();
        deleteManyDocument();
        finalized();
    }

    /**
     * This is delete the single document, which is first matched
     */
    @Override
    public void deleteOneDocument() {
        MongoDatabase db = null;
        MongoCollection<Document> collection = null;
        Bson query = null;
        try {
            db = client.getDatabase(mongo.getDataBase());
            collection = db.getCollection(mongo.getSampleCollection());
            query = eq("name", "sundar");
            DeleteResult result = collection.deleteMany(query);
            if (result.wasAcknowledged()) {
                log.info("Single Document deleted successfully \nNo of Document Deleted : " + result.getDeletedCount());
            }
        } catch (MongoException e) {
            log.error("Exception occurred while delete Single Document : " + e, e);
        }
    }

    /**
     * This is deleted delete all document(s), which is matched
     */
    @Override
    public void deleteManyDocument() {
        MongoDatabase db = null;
        MongoCollection<Document> collection = null;
        Bson query = null;
        try {
            db = client.getDatabase(mongo.getDataBase());
            collection = db.getCollection(mongo.getSampleCollection());
            query = lt("age", 20);
            DeleteResult result = collection.deleteMany(query);
            if (result.wasAcknowledged()) {
                log.info("Document deleted successfully \nNo of Document(s) Deleted : " + result.getDeletedCount());
            }
        } catch (MongoException e) {
            log.error("Exception occurred while delete Many Document : " + e, e);
        }
    }

    /**
     * This is closed the mongo client, no need to fours to close the mongo
     * client, mongo cluster will take care about close the mongo client, for
     * safety purpose we close the client
     */
    @Override
    public void finalized() {
        mongo.closeMongoClient(client);
    }
}
