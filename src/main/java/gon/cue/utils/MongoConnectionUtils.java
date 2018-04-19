package gon.cue.utils;

import gon.cue.App;

import org.apache.log4j.Logger;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class MongoConnectionUtils {
    private static final Logger log         = Logger.getLogger(App.class);

    private MongoClient         mongoClient = null;

    private String              dataBase;
    private String              sampleCollection;

    public String getDataBase() {
        return dataBase;
    }

    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    public String getSampleCollection() {
        return sampleCollection;
    }

    public void setSampleCollection(String sampleCollection) {
        this.sampleCollection = sampleCollection;
    }


    /**
     * create the mongo client connection
     * 
     * @return - mongo client 
     */
    public MongoClient getMongoConnection() {
        String host = "127.0.0.1";
        int port = 27017;
        try {
            if (mongoClient == null) {
                mongoClient = new MongoClient(host, port);
                setDataBase("DavidTest");
                setSampleCollection("SampleCollection");
            }
        } catch (MongoException e) {
            log.error("Exception occurred while get Connection : " + e, e);
        }
        return mongoClient;
    }

    /**
     * close the mongo client 
     * 
     * @param mongoClient - client which is have to close 
     */
    public void closeMongoClient(MongoClient mongoClient) {
        try {
            if (mongoClient != null) {
                mongoClient.close();
            }
        } catch (MongoException e) {
            log.error("Exception occurred while close MongoClient : " + e, e);
        }
    }
}
