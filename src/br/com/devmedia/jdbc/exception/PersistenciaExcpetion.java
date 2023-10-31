package br.com.devmedia.jdbc.exception;

public class PersistenciaExcpetion extends  Exception {

    private static final long serialVersionUID = -8796457926599751430L;

    public PersistenciaExcpetion(String msg, Exception exception){
        super(msg, exception);
    }


    public PersistenciaExcpetion(String msg){
        super(msg);
    }
    
}
