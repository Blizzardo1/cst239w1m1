package com.toasternetwork.examples;

import com.fasterxml.jackson.databind.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
public class InventoryDatabase {
    private final String fileName;

    private final File file;

    private static InventoryDatabase instance;
    public static InventoryDatabase getInstance() {
        return instance;
    }

    /**
     * A new JSON Database for Inventory
     * @param fileName The filename of the database to read from
     * @throws Exception The filename is likely not found
     */
    public InventoryDatabase(String fileName) throws Exception {
        this.fileName = fileName;
        file = new File(fileName);
        checkFile();
        instance = this;
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
    public List<Product> readFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(Paths.get(getFileName()).toFile(), Product[].class));
    }

    /**
     * Writes the JSON object to file
     * @param products The JSON object to write
     * @throws IOException The file may not be proper or the file may not be writable
     */
    public void writeContents(ArrayList<Product> products) throws IOException {
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file);
        PrintWriter pw = new PrintWriter(writer);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonStr = mapper.writeValueAsString(products);
        pw.println(jsonStr);
        pw.close();
    }

    /**
     * Gets the registered filename of the instantiated object
     * @return The filename registered with the instantiated object
     */
    public String getFileName() {
        return fileName;
    }
}
