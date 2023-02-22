package com.example.demo.tcp;

import java.util.LinkedHashMap;

public class ReceivedLinkedMapData {
    
    LinkedHashMap<String, String> receivedLinkedHashMap;
 
    public LinkedHashMap<String, String> getReceivedLinkedHashMap() {
        return receivedLinkedHashMap;
    }
    public void setReceivedLinkedHashMap(LinkedHashMap<String, String> receivedLinkedHashMap) {
        this.receivedLinkedHashMap = receivedLinkedHashMap;
    }
    @Override
    public String toString() {
        return "ReceivedLinkedMapData [receivedLinkedHashMap=" + receivedLinkedHashMap + "]";
    }
 
}