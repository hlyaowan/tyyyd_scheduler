package com.tyyd.scheduler.engine;

@SuppressWarnings("serial")
public class RootDirectory extends Directory {

    public RootDirectory(String path){
        super(path, "/");
    }

    public Directory newDirectory(String path) {
        return new Directory(this.getPath(), "");
    }
}
