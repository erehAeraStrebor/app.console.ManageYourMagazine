package gui.projekt.s19852;

public class Przedmiot implements IZlozony,IRozlozony
{
    private String name;
    private double powierzchniaPrzedmiotu;
    private double powierzchniaZajmowana;
    public boolean czyZlozony;

    Przedmiot(String name, double powierzchniaPrzedmiotu)
    {
        this.name = name;
        this.powierzchniaPrzedmiotu = powierzchniaPrzedmiotu;
        this.powierzchniaZajmowana = powierzchniaPrzedmiotu;
        this.czyZlozony = true;
    }

    public double pobierzPowierzchniePrzedmiotuZajmowana()
    {
        return powierzchniaZajmowana;
    }


    public String getName() { return name; }




    @Override
    public void rozlozony()
    {
        this.czyZlozony = false;
        this.powierzchniaZajmowana = this.powierzchniaPrzedmiotu;

    }

    @Override
    public void zlozony(int zlozenieXtimes)
    {
        this.czyZlozony = true;
        this.powierzchniaZajmowana = (this.powierzchniaPrzedmiotu/zlozenieXtimes);
    }

    public String toString(){
        return "Obiekt: " + this.getClass().getName() + "\n" + "Nazwa: " + this.getName() + "\n" +
                "Powierzchnia zajmowana: " + this.pobierzPowierzchniePrzedmiotuZajmowana() + " m3";
    }

    public static void name(Przedmiot przedmiot) {
    System.out.println(przedmiot.name);
    }
}
