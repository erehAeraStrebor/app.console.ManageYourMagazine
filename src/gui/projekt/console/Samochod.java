package gui.projekt.s19852;

public class Samochod extends Pojazd
{
    private String rodzajPaliwa;
    Samochod(String name, double powierzchnia, String rodzajPaliwa)
    {
        super(name, powierzchnia);
        this.rodzajPaliwa = rodzajPaliwa;
    }
    public String getRodzajPaliwa() { return rodzajPaliwa; }

    public String toString(){
        return super.toString() + "\n" +
                "Rodzaj paliwa: " + this.rodzajPaliwa + "\n";
    }
}
