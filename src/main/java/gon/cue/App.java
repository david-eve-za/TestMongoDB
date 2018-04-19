package gon.cue;

import gon.cue.crud.DeleteDocuments;
import gon.cue.crud.InsertDocuments;
import gon.cue.crud.QueryDocuments;
import gon.cue.crud.UpdateDocuments;
import gon.cue.crud.impl.DeleteDocumentsImpl;
import gon.cue.crud.impl.InsertDocumentsImpl;
import gon.cue.crud.impl.QueryDocumentsImpl;
import gon.cue.crud.impl.UpdateDocumentsImpl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class App {
    private static Logger log         = Logger.getLogger(App.class);
    public final String   INVALID_MSG = "Invalid Argument(s) \n1 - Read / 2 - Write / 3 - Update / 4 - Delete";

    public App(String[] args) {
        boolean valid = true;
        for (int i = 0; i < args.length; i++) {
            if (Integer.valueOf(args[i]) > 4) {
                valid = false;
            }
        }
        if (valid) {
            startProcess(args);
        } else {
            log.info(INVALID_MSG);
        }
    }

    /**
     * Start the operation(s) based on input
     * 
     * @param args - operation ID, which is given by user
     */
    private void startProcess(String[] args) {
        for (String arg : args) {
            if (Integer.valueOf(arg) > 4) {
                log.info(INVALID_MSG);
                break;
            } else {
                log.info("--------------------------------------");
                switch (Integer.valueOf(arg)) {
                    case 1:
                        log.info("Read Process is started...");
                        log.info("--------------------------------------");
                        QueryDocuments document = new QueryDocumentsImpl();
                        document.loadMethods();
                        break;
                    case 2:
                        log.info("Write Process is started...");
                        log.info("--------------------------------------");
                        InsertDocuments insert = new InsertDocumentsImpl();
                        insert.loadMethods();
                        break;
                    case 3:
                        log.info("Update Process is started...");
                        log.info("--------------------------------------");
                        UpdateDocuments update = new UpdateDocumentsImpl();
                        update.loadMethods();
                        break;
                    case 4:
                        log.info("Delete Process is started...");
                        log.info("--------------------------------------");
                        DeleteDocuments delete = new DeleteDocumentsImpl();
                        delete.loadMethods();
                        break;
                    default:
                        log.info("Invalid Argument : " + arg);
                        log.info("--------------------------------------");
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            CSVParser parse =
                              org.apache.commons.csv.CSVParser.parse(new File("torrent_dump_full.csv"), Charset.forName("utf-8"),
                                                                     CSVFormat.EXCEL.withDelimiter(';').withHeader());


            for (CSVRecord record : parse) {
                String added = record.get("#ADDED");
                String hash = record.get("HASH(B64)");
                String name = record.get("NAME");
                String size = record.get("SIZE(BYTES)");

                System.out.println(added + " " + hash + " " + name + " " + size);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        System.exit(0);

        try {
            log.info("Welcome to MongoDB CRUD Operations!!!");
            if (args.length > 0 && args.length <= 4) {
                new App(args);
            } else {
                log.info("At least one argument, at most four argument is Required, (" + args.length + " given)");
            }
        } catch (Exception e) {
            log.error("Exception occurred : " + e, e);
        }
    }

    static {
        ConsoleAppender console = new ConsoleAppender(); // create appender
        // configure the appender
        String PATTERN = "%d [%p|%c|%C{1}] %m%n";
        console.setLayout(new PatternLayout(PATTERN));
        console.setThreshold(Level.ALL);
        console.activateOptions();
        // add appender to any Logger (here is root)
        Logger.getRootLogger().addAppender(console);

        FileAppender fa = new FileAppender();
        fa.setName("FileLogger");
        fa.setFile("INFO.log");
        fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
        fa.setThreshold(Level.ALL);
        fa.setAppend(true);
        fa.activateOptions();

        // add appender to any Logger (here is root)
        Logger.getRootLogger().addAppender(fa);
        // repeat with all other desired appenders
    }
}
