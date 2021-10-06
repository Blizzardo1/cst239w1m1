package com.toasternetwork.examples;

import java.io.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class JsonDb {
    private final String fileName;

    private final File file;
    private FileReader reader;
    private FileWriter writer;

    private final JSONParser parser;

    /**
     * A new JSON Database
     * @param fileName The filename of the database to read from
     * @throws Exception The filename is likely not found
     */
    public JsonDb(String fileName) throws Exception {
        this.fileName = fileName;
        file = new File(fileName);
        parser = new JSONParser();
        checkFile();
    }

    /**
     * Identifies whether the file is an actual file
     * @throws Exception The file is not a file
     */
    private void checkFile() throws Exception {
        if(!file.isFile()) throw new Exception("Not a File");
        if(file.canRead())
            reader = new FileReader(file);
        if(file.canWrite())
            writer = new FileWriter(file);
    }

    /**
     * Reads the file as a JSON object
     * @return The contents of a JSON file
     * @throws IOException The file may not be proper or the file may not be readable
     * @throws ParseException There is a JSON parsing issue
     */
    public JSONObject readFile() throws IOException, ParseException {
        return (JSONObject) parser.parse(reader);
    }

    /**
     * Writes the JSON object to file
     * @param json The JSON object to write
     * @throws IOException The file may not be proper or the file may not be writable
     */
    public void writeContents(JSONObject json) throws IOException {
        writer.write(json.toJSONString());
    }

    /**
     * Gets the registered filename of the instantiated object
     * @return The filename registered with the instantiated object
     */
    public String getFileName() {
        return fileName;
    }
}
