package com.example.luisf11.youtubeclient.models;

/**
 * Created by Luis Fernando Pena on 6/12/2017.
 */

public class ServerConfig {
    private String Ip;
    private String Port;
    private String Prefix;

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public String getPort() {
        return Port;
    }

    public void setPort(String port) {
        Port = port;
    }

    public String getPrefix() {
        return Prefix;
    }

    public void setPrefix(String prefix) {
        Prefix = prefix;
    }
}
