package com.rse.webservice.locket.test.designpattern.observer;

public interface Sender {

    void sendMessage(String message);
    void registerReceiver(Receiver receiver);
    void unregisterReceiver(Receiver receiver);

}
