package com.rse.webservice.locket.test.designpattern.observer;

public class AppReceiver implements Receiver {
    private String name;

    public AppReceiver(String name) {
        this.name = name;
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + ": " + message);

    }
}
