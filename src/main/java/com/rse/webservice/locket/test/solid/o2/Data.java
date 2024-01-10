package com.rse.webservice.locket.test.solid.o2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

@Builder
public class Data {
    private String title;
    private String message;
    private int statusCode;
    private String body;

    private FormatterResponse formatterResponse;

    public void setFormatterResponse(FormatterResponse formatterResponse) {

        this.formatterResponse = formatterResponse;
    }

    @Override
    public String toString() {
        return formatterResponse.format(this);
    }

    public static void main(String[] args) {
        // Create an instance with JSON formatting
        Data jsonData = Data.builder()
                .title("JSON Title")
                .message("JSON Message")
                .statusCode(200)
                .body("JSON Body")
                .formatterResponse(new JSONResponse())
                .build();

        // Create an instance with XML formatting
        Data xmlData = Data.builder()
                .title("XML Title")
                .message("XML Message")
                .statusCode(404)
                .body("XML Body")
                .formatterResponse(new XMLResponse())
                .build();

        // Display the formatted output
        System.out.println("JSON Data:\n" + jsonData);
        System.out.println("\nXML Data:\n" + xmlData);
    }
}
