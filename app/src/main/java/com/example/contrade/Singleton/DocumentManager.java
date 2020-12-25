package com.example.contrade.Singleton;

public class DocumentManager {
    private static DocumentManager instance=null;
    private int currentFolderId;

    private DocumentManager(){
    }

    public static DocumentManager getInstance(){
        if(instance==null){
            instance=new DocumentManager();
        }
        return instance;
    }

}