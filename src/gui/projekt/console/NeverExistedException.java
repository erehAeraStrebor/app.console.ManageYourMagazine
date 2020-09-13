package gui.projekt.s19852;

public class NeverExistedException extends Exception
{
    NeverExistedException(){}
    public NeverExistedException(String info)
    {
        super(info);
    }
}
