package com.example.demo.tcp;

import java.util.HashMap;

public class ReceivedMapDataGlobal {
    
    static HashMap<String, ReceivedLinkedMapData> receivedMapDataGlobal;
    
    public static HashMap<String, ReceivedLinkedMapData> getReceivedMapDataGlobal() {
        return receivedMapDataGlobal;
    }
    public static void setReceivedMapDataGlobal(HashMap<String, ReceivedLinkedMapData> receivedMapDataGlobal) {
        ReceivedMapDataGlobal.receivedMapDataGlobal = receivedMapDataGlobal;
    }
    @Override
    public String toString() {
        return "ReceivedMapDataGlobal [receivedMapDataGlobal=" + receivedMapDataGlobal + "]";
    }
    
}