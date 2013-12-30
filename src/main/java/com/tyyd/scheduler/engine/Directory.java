package com.tyyd.scheduler.engine;

import java.io.*;

@SuppressWarnings("serial")
public class Directory extends File {

    final public String  representativePath;
    final public boolean isRoot;

    // final static public Directory root;

    public Directory(String path, String representativePath){
        super(path);
        this.representativePath = representativePath;
        isRoot = representativePath.contentEquals("/");
    }

    public Directory(Directory root, String path){
        super(root.toString() + path);
        representativePath = path;
        isRoot = false;
    }
}
