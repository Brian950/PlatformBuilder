package com.logic.constructor.builder.adapter;


public class BuilderHandler implements IBuilderHandler {

    private JSONHandler jsonHandler;
    private XMLHandler xmlHandler;

    public BuilderHandler(String data, String type){
        jsonHandler = new JSONHandler();
        xmlHandler = new XMLHandler();
    }

    @Override
    public String getData(String data, String type) {
        if(type == "json")
            return jsonHandler.getData(data);
        else if(type == "xml")
            return xmlHandler.getData(data);
        else
            return "Invalid File Type";
    }
}
