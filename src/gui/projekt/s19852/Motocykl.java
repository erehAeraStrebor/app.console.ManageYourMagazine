package gui.projekt.s19852;

public class Motocykl extends Pojazd
{


    private boolean czyHomologacja;
    Motocykl(String name, double powierzchnia, boolean czyHomologacja)

    {
        super(name, powierzchnia);
        this.czyHomologacja = czyHomologacja;
    }
    public boolean isCzyHomologacja() { return czyHomologacja; }
    public String toString(){
        return super.toString() + "\n" +
                "Homologacja: " + czyHomologacja + "\n";
    }
}
