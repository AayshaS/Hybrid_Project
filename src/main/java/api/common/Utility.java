package api.common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static api.common.BaseClass.log;

public class Utility {

    public static void saveResponseIntoJsonFile(String responseData ){

        String filePath = "src/main/resources/api_response.json";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) {
            writer.append(responseData).append(System.lineSeparator());
            System.out.println("API response has been written to '" + filePath + "'");
            log("API response has been written to '" + filePath + "'" +responseData+ "'");
        } catch (IOException e) {
            System.err.println("Error writing API response to file: " + e.getMessage());
            log("Error writing API response to file: " + e.getMessage());
        }
    }

}
