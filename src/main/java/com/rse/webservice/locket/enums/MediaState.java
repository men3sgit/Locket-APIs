package com.rse.webservice.locket.enums;

public enum MediaState {
    PUBLIC, PRIVATE, PENDING, DRAFT, FRIENDS;

    public static MediaState fromString(String input) {
        try {
            return valueOf(input.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid input: " + input);
        }
    }
}
