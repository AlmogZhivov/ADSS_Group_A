public class Response {

    public String ErrorMessage;
    public Object ReturnValue;

    public boolean ErrorOccurred(){
        return ReturnValue == null;
    }
}
