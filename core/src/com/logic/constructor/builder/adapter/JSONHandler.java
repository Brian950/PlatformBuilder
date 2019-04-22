package com.logic.constructor.builder.adapter;

public class JSONHandler implements IHandler{

    public JSONHandler(){}

    @Override
    public String getData(String data) {
        return this.convertJSON(data);
    }

    public String convertJSON(String data){
        return "json";
    }

}
