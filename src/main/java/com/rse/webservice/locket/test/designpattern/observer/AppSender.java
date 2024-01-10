package com.rse.webservice.locket.test.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class AppSender implements Sender {
    List<Receiver> list = new ArrayList<>();

    @Override
    public void sendMessage(String message) {
        list.forEach(receiver -> receiver.receiveMessage(message));
    }

    @Override
    public void registerReceiver(Receiver receiver) {
        list.add(receiver);
    }

    @Override
    public void unregisterReceiver(Receiver receiver) {
        list.remove(receiver);
    }

    public static void main(String[] args) {
        Sender sender = new AppSender();
        Receiver r1 = new AppReceiver("Duong Duy Men");
        Receiver r2 = new AppReceiver("Nguyen Tu Linh");
        Receiver r3 = new AppReceiver("Nguyen Duong");
        sender.registerReceiver(r1);
        sender.registerReceiver(r2);
        sender.registerReceiver(r3);
        sender.sendMessage("Haha");
    }

}
