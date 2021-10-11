package com.toasternetwork.examples;

import com.fasterxml.jackson.databind.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

/**
 * The inventory database
 */
public class InventoryDatabase {
    private static String fileName;
    private static File file;

    /**
     * Opens JSON Database for Inventory
     * @param fileName The filename of the database to read from
     * @throws Exception The filename is likely not found
     */
    public static void open(String fileName) throws Exception {
        InventoryDatabase.fileName = fileName;
        file = new File(fileName);
        checkFile();
    }

    /**
     * Identifies whether the file is an actual file
     * @throws Exception The file is not a file
     */
    private static void checkFile() throws Exception {
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
    public static List<Product> readFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(Paths.get(getFileName()).toFile(), Product[].class));
    }

    /**
     * Writes the JSON object to file
     * @param products The JSON object to write
     * @throws IOException The file may not be proper or the file may not be writable
     */
    public static void writeContents(ArrayList<Product> products) throws IOException {
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
     * Gets the registered filename of the single instance
     * @return The filename registered with the single instance
     */
    public static String getFileName() {
        return fileName;
    }
}
