package test;

public class MyException extends Throwable{
    @Override
    public String getMessage() {
        return "error: Sai định dạng dữ liệu";
    }
}
