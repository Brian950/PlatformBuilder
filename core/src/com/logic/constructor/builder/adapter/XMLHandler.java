package com.logic.constructor.builder.adapter;

public class XMLHandler implements IHandler{

    public XMLHandler(){}

    @Override
    public String getData(String data) {
        return this.convertXML(data);
    }

    public String convertXML(String data){
        return "xml";
    }

}
