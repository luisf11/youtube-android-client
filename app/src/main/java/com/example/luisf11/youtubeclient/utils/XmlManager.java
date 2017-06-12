package com.example.luisf11.youtubeclient.utils;

import android.content.Context;
import android.util.Xml;

import com.example.luisf11.youtubeclient.models.ServerConfig;


import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.StringWriter;


/**
 * Created by Luis Fernando Pena on 6/12/2017.
 */

public class XmlManager {

    public void xmlWriter(ServerConfig config, Context context){
        final String xmlFile = "userData";

        try {
            FileOutputStream fos = new  FileOutputStream("userData.xml");
            FileOutputStream fileos= context.getApplicationContext().openFileOutput(xmlFile, Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "serverConfig");
            xmlSerializer.startTag(null, "IP");
            xmlSerializer.text(config.getIp());
            xmlSerializer.endTag(null, "IP");
            xmlSerializer.startTag(null,"Puerto");
            xmlSerializer.text(config.getPort());
            xmlSerializer.endTag(null, "Puerto");
            xmlSerializer.startTag(null,"Prefix");
            xmlSerializer.text(config.getPrefix());
            xmlSerializer.endTag(null, "Prefix");
            xmlSerializer.endTag(null, "serverConfig");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();
            fileos.write(dataWrite.getBytes());
            fileos.close();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
