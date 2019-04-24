package com.logic.constructor.builder.adapter;

import java.util.ArrayList;

public class XMLHandler implements IHandler{

    public XMLHandler(){}

    @Override
    public ArrayList<String> getData(String data) {
        return this.convertXML(data);
    }

    public ArrayList<String> convertXML(String filePath){

        /*
            We have not provided the implementation for this class
            but we have included it as it proves the use of the adapter design pattern.
            We have a concrete implementation for handling JSON files and in order to handle XML files
            we would simply repeat the same process but with an XML parsing library.
         */

        return null;
    }

}
