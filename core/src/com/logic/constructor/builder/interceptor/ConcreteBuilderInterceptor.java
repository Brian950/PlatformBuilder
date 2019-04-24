package com.logic.constructor.builder.interceptor;

import com.logic.constructor.builder.IBuilder;
import com.logic.constructor.builder.LevelBuilder;
import com.logic.constructor.builder.LevelDirector;
import com.logic.constructor.builder.ReverseLevelBuilder;
import com.logic.constructor.builder.adapter.BuilderHandler;
import com.logic.constructor.builder.adapter.IBuilderHandler;


public class ConcreteBuilderInterceptor implements BuilderFileInterceptor {

    private static ConcreteBuilderInterceptor instance;
    private IBuilderHandler fileHandler;
    private IBuilder builder;

    public ConcreteBuilderInterceptor(){
    }

    @Override
    public void preRequest(BuilderFileContext context) {
        this.fileHandler = new BuilderHandler();
        context.setData(fileHandler.getData(context.getFilepath(), context.getFileType()));
    }

    @Override
    public void postRequest(BuilderFileContext context) {
        System.out.println("Context data: "+context.getdata());
        if(context.getLevelType().equals("normal"))
            this.builder = new LevelBuilder(context.getdata());
        else if(context.getLevelType().equals("reverse"))
            this.builder = new ReverseLevelBuilder(context.getdata());

        LevelDirector director = new LevelDirector(this.builder);
        director.buildLevel();
        context.setLevel(director.getLevel());
    }

    public static ConcreteBuilderInterceptor getInstance(){
        if(instance == null) {
            instance = new ConcreteBuilderInterceptor();
            return instance;
        }
        else
            return instance;
    }

}
