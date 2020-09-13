package gui.projekt.s19852;

public class Rower extends Pojazd implements IZlozony,IRozlozony
{

    private boolean czyZlozonyRower;
    private double powierzchniaRoweru, powierzchniaZajmowanaRoweru;
    private int iloscPrzerzutek;
    Rower(String name, double powierzchnia, int iloscPrzerzutek)
    {
        super(name, powierzchnia);
        this.iloscPrzerzutek = iloscPrzerzutek;
    }
    public int getIloscPrzerzutek() { return iloscPrzerzutek; }
    @Override
    public void rozlozony()
    {
        this.czyZlozonyRower = false;
    }

    @Override
    public void zlozony(int iloscCzesci)
    {
        this.czyZlozonyRower = true;
        this.powierzchniaZajmowanaRoweru = (this.powierzchniaRoweru/iloscCzesci);
    }


    public boolean isCzyZlozonyRower()
    {
        return czyZlozonyRower;
    }

    public String toString(){
        return super.toString() + "\n" +
                "Ilosc przerzutek " + this.iloscPrzerzutek + "\n";
    }
}

