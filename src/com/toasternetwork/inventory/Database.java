package com.toasternetwork.inventory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

/**
 * The inventory database
 */
public class Database<T> {
    private final String fileName;
    private File file;

    /**
     * A new database
     * @param fileName The file to load it from
     */
    public Database(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Opens JSON Database for Inventory
     * @throws Exception The filename is likely not found
     */
    public void open() throws Exception {
        file = new File(fileName);
        checkFile();
    }

    /**
     * Identifies whether the file is an actual file
     * @throws Exception The file is not a file
     */
    private void checkFile() throws Exception {
        if(!file.exists()) {
            writeContents(new ArrayList<>());
        }
        if(!file.isFile()) throw new Exception("Not a File");
    }

    /**
     * Reads the file as a JSON object
     * @return The contents of a JSON file
     * @throws IOException The file may not be proper or the file may not be readable
     */
    public List<T> readFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = mapper.readTree(Paths.get(getFileName()).toFile());
        return List.of(mapper.convertValue(node, new TypeReference<T[]>() {}));

    }

    /**
     * Writes the JSON object to file
     * @param elements The JSON object to write
     * @throws IOException The file may not be proper or the file may not be writable
     */
    public void writeContents(ArrayList<T> elements) throws IOException {
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file);
        PrintWriter pw = new PrintWriter(writer);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonStr = mapper.writeValueAsString(elements);
        pw.println(jsonStr);
        pw.close();
    }

    /**
     * Gets the registered filename of the single instance
     * @return The filename registered with the single instance
     */
    public String getFileName() {
        return fileName;
    }
}
