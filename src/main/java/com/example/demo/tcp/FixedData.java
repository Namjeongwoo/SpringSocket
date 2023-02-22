package com.example.demo.tcp;

public class FixedData {
    
    public final int ifid = 12;        // interface id
    public final int rqsy = 6;        // request system
    public final int glid = 32;        // global id
    public final int iqid = 8;      // inquiry id
    public final int stdt = 8;      // start date
    public final int endt = 8;      // end date
 
    public String ifid_str = "Interface Id";
    public String rqsy_str = "Request Sys";
    public String glid_str = "Global Id";
    public String iqid_str = "Inquiry Id";
    public String stdt_str = "Start Date";
    public String endt_str = "End Date";
 
    public byte[] firsByte = new byte[0];
    public byte[] ifidByte = new byte[ifid];
    public byte[] rqsyByte = new byte[rqsy];
    public byte[] glidByte = new byte[glid];
    public byte[] iqidByte = new byte[iqid];
    public byte[] stdtByte = new byte[stdt];
    public byte[] endtByte = new byte[endt];
 
    public int[] arrayId = { 0, ifid, rqsy, glid, iqid, stdt, endt };
    public String[] arrayStr = { "", ifid_str, rqsy_str, glid_str, iqid_str, stdt_str, endt_str };
    public byte[] arrayByte[] = { firsByte, ifidByte, rqsyByte, glidByte, iqidByte, stdtByte, endtByte };
    public String[] arrayKey = { "", "ifid_str", "rqsy_str", "glid_str", "iqid_str", "stdt_str", "endt_str" };
    
}