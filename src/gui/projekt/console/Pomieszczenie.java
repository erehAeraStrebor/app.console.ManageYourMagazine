package gui.projekt.s19852;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class Pomieszczenie
{
    private static int id;
    private double powierzchnia;
    private double powierzchniaWolna;
    private boolean czyWolne;
    private boolean czyRemont;
    //private static int counter;
    private Osoba owner;
    Przedmiot obiekt;
    private ArrayList<Przedmiot> zawartoscPom = new ArrayList<Przedmiot>();

    Pomieszczenie(double powierzchnia)
    {
        this.powierzchnia = powierzchnia;
        this.powierzchniaWolna = powierzchnia;

        this.czyWolne = true;
        this.czyRemont = false;
        this.owner = null;
        this.id++;

    }
    public int getId()
    {
        return id;
    }
    public ArrayList<Przedmiot> getZawartoscPom() {
        return zawartoscPom;
    }
    public Osoba getOwner(){ return owner; }
    public double getPowierzchnia() { return powierzchnia; }
    public double getPowierzchniaWolna() { return powierzchniaWolna; }
    public boolean isCzyWolne()
    {
        return czyWolne;
    }
    public boolean isCzyRemont()
    {
        return czyRemont;
    }
    public void setCzyRemont(boolean czyRemont)
    {
        this.czyRemont = czyRemont;
    }
    public void setOwner(Osoba owner)
    {
        this.owner = owner;
    }
    public void setCzyWolne(boolean czyWolne)
    {
        this.czyWolne = czyWolne;
    }



    /*public void wlozNowyObiektDoPom(int numerLokalu) throws IOException, TooManyThingsException {
        Pomieszczenie p = Magazyn.getMagazyn().get(numerLokalu);
        int index = Magazyn.getMagazyn().indexOf(p);
        if(numerLokalu == Magazyn.getMagazyn().indexOf(p)) {
            System.out.println("WYBIERZ TYP OBIEKTU: samochod, rower, motocykl, przedmiot");
            Scanner scan = new Scanner(System.in);
            String type = scan.nextLine();
            switch (type.toLowerCase()) {
                case "samochod":
                    String nazwaS;
                    double powierzchniaS;
                    String rodzajPaliwa;
                    System.out.println("Podaj marke samochodu");
                    nazwaS = scan.nextLine();
                    System.out.println("Podaj rodzaj paliwa");
                    rodzajPaliwa = scan.nextLine();
                    System.out.println("Podaj zajmowana powierzchnie samochodu (m3)");
                    powierzchniaS = scan.nextDouble();

                    Samochod dodawanySamochod = new Samochod(nazwaS, powierzchniaS, rodzajPaliwa);
                    sprawdziDodaj(dodawanySamochod, index);
                    //p.sprawdzMiejsceIDodaj(numerLokalu, dodawanySamochod);

                    break;
                case "rower":
                    String nazwaR;
                    double powierzchniaR;
                    int iloscPrzerzutek;
                    System.out.println("Podaj marke roweru");
                    nazwaR = scan.nextLine();
                    System.out.println("Podaj zajmowana powierzchnie roweru (m3)");
                    powierzchniaR = scan.nextDouble();
                    System.out.println("Podaj ilosc przerzutek");
                    iloscPrzerzutek = scan.nextInt();

                    Rower dodawanyRower = new Rower(nazwaR, powierzchniaR, iloscPrzerzutek);
                    sprawdziDodaj(dodawanyRower, index);
                    //p.sprawdzMiejsceIDodaj(numerLokalu, dodawanyRower);
                    break;
                case "motocykl":
                    String nazwaM;
                    double powierzchniaM;
                    boolean homologacja;
                    System.out.println("Podaj marke motocykla");
                    nazwaM = scan.nextLine();
                    System.out.println("Podaj zajmowana powierzchnie motocykla (m3)");
                    powierzchniaM = scan.nextDouble();
                    System.out.println("Czy posiada homologacje (true - tak | false - nie)");
                    homologacja = scan.nextBoolean();

                    Motocykl dodawanyMotocykl = new Motocykl(nazwaM, powierzchniaM, homologacja);
                    sprawdziDodaj(dodawanyMotocykl, index);
                    // p.sprawdzMiejsceIDodaj(numerLokalu, dodawanyMotocykl);
                    break;
                case "przedmiot":
                    String nazwaP;
                    double powierzchniaP;
                    System.out.println("Podaj nazwe przedmiotu");
                    nazwaP = scan.nextLine();
                    System.out.println("Podaj zajmowana powierzchnie przedmiotu (m3)");
                    powierzchniaP = scan.nextDouble();

                    Przedmiot dodawanyPrzedmiot = new Przedmiot(nazwaP, powierzchniaP);
                    sprawdziDodaj(dodawanyPrzedmiot, index);
                    // p.sprawdzMiejsceIDodaj(numerLokalu, dodawanyPrzedmiot);
                    break;

                default:
                    System.out.println("Podano niewlasciwy typ obiektu, sprobuj ponownie");
            }
        }
    }*/
    public void sprawdzSortnijDodajdlaWkladania(Przedmiot obiekt) throws TooManyThingsException {

        if(obiekt.pobierzPowierzchniePrzedmiotuZajmowana() > powierzchniaWolna) throw new TooManyThingsException("Brak miejsca w tym pomieszczenia");
        else {
            zawartoscPom.add(obiekt);
            Collections.sort(zawartoscPom, new Comparator<Przedmiot>() {
                @Override
                public int compare(Przedmiot o1, Przedmiot o2) {
                    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                    if(o1.getName().equals(o2.getName())) return o1.getName().compareTo(o2.getName());
                    return (int) (o2.pobierzPowierzchniePrzedmiotuZajmowana() - o1.pobierzPowierzchniePrzedmiotuZajmowana());
                }
            });
            powierzchniaWolna -= obiekt.pobierzPowierzchniePrzedmiotuZajmowana();
        }
    }


    public void wlozObiekt(int numerPomieszczenia) throws IOException, TooManyThingsException {
        Pomieszczenie p = Osoba.wybierzLokal(numerPomieszczenia);
        System.out.println("WYBIERZ TYP OBIEKTU: samochod, rower, motocykl, przedmiot");
        Scanner scan = new Scanner(System.in);
        String type = scan.nextLine();
        switch (type.toLowerCase()) {
            case "samochod":
                String nazwaS;
                double powierzchniaS;
                String rodzajPaliwa;
                System.out.println("Podaj marke samochodu");
                nazwaS = scan.nextLine();
                System.out.println("Podaj rodzaj paliwa");
                rodzajPaliwa = scan.nextLine();
                System.out.println("Podaj zajmowana powierzchnie samochodu (m3)");
                powierzchniaS = scan.nextDouble();

                Samochod dodawanySamochod = new Samochod(nazwaS, powierzchniaS, rodzajPaliwa);

                sprawdzSortnijDodajdlaWkladania(dodawanySamochod);
               /* if(dodawanySamochod.pobierzPowierzchniePrzedmiotuZajmowana() > p.getPowierzchniaWolna()) throw new TooManyThingsException("Brak miejsca w tym pomieszczenia");
                else {
                    p.getZawartoscPom().add(dodawanySamochod);
                    Collections.sort(zawartoscPom, new Comparator<Przedmiot>() {
                        @Override
                        public int compare(Przedmiot o1, Przedmiot o2) {
                            // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                            if(o1.getName().equals(o2.getName())) return o1.getName().compareTo(o2.getName());
                            return (int) (o2.pobierzPowierzchniePrzedmiotuZajmowana() - o1.pobierzPowierzchniePrzedmiotuZajmowana());
                        }
                    });
                    p.powierzchniaWolna -= dodawanySamochod.pobierzPowierzchniePrzedmiotuZajmowana();
                }*/

                //if (p.getPowierzchniaWolna() > dodawanySamochod.pobierzPowierzchniePrzedmiotuZajmowana())
                 //   p.getZawartoscPom().add(dodawanySamochod);
                Magazyn.posortujMagazyn();
                Magazyn.stanMagazynu();
                //p.sprawdzMiejsceIDodaj(numerLokalu, dodawanySamochod);

                break;
            case "rower":
                String nazwaR;
                double powierzchniaR;
                int iloscPrzerzutek;
                System.out.println("Podaj marke roweru");
                nazwaR = scan.nextLine();
                System.out.println("Podaj zajmowana powierzchnie roweru (m3)");
                powierzchniaR = scan.nextDouble();
                System.out.println("Podaj ilosc przerzutek");
                iloscPrzerzutek = scan.nextInt();

                Rower dodawanyRower = new Rower(nazwaR, powierzchniaR, iloscPrzerzutek);
                sprawdzSortnijDodajdlaWkladania(dodawanyRower);
                Magazyn.posortujMagazyn();
                Magazyn.stanMagazynu();
                /*if (p.getPowierzchniaWolna() > dodawanyRower.pobierzPowierzchniePrzedmiotuZajmowana())
                    p.getZawartoscPom().add(dodawanyRower);
                Magazyn.posortujMagazyn();
                Magazyn.stanMagazynu();
                //p.sprawdzMiejsceIDodaj(numerLokalu, dodawanyRower);*/
                break;
            case "motocykl":
                String nazwaM;
                double powierzchniaM;
                boolean homologacja;
                System.out.println("Podaj marke motocykla");
                nazwaM = scan.nextLine();
                System.out.println("Podaj zajmowana powierzchnie motocykla (m3)");
                powierzchniaM = scan.nextDouble();
                System.out.println("Czy posiada homologacje (true - tak | false - nie)");
                homologacja = scan.nextBoolean();

                Motocykl dodawanyMotocykl = new Motocykl(nazwaM, powierzchniaM, homologacja);
                sprawdzSortnijDodajdlaWkladania(dodawanyMotocykl);
                Magazyn.posortujMagazyn();
                Magazyn.stanMagazynu();
                /*if (p.getPowierzchniaWolna() > dodawanyMotocykl.pobierzPowierzchniePrzedmiotuZajmowana())
                    p.getZawartoscPom().add(dodawanyMotocykl);
                Magazyn.posortujMagazyn();
                Magazyn.stanMagazynu();
                // p.sprawdzMiejsceIDodaj(numerLokalu, dodawanyMotocykl);*/
                break;
            case "przedmiot":
                String nazwaP;
                double powierzchniaP;
                System.out.println("Podaj nazwe przedmiotu");
                nazwaP = scan.nextLine();
                System.out.println("Podaj zajmowana powierzchnie przedmiotu (m3)");
                powierzchniaP = scan.nextDouble();

                Przedmiot dodawanyPrzedmiot = new Przedmiot(nazwaP, powierzchniaP);
                sprawdzSortnijDodajdlaWkladania(dodawanyPrzedmiot);
                Magazyn.posortujMagazyn();
                Magazyn.stanMagazynu();
                /*if (p.getPowierzchniaWolna() > dodawanyPrzedmiot.pobierzPowierzchniePrzedmiotuZajmowana())
                    p.getZawartoscPom().add(dodawanyPrzedmiot);
                Magazyn.posortujMagazyn();
                Magazyn.stanMagazynu();*/
                // p.sprawdzMiejsceIDodaj(numerLokalu, dodawanyPrzedmiot);
                break;

            default:
                System.out.println("Podano niewlasciwy typ obiektu, sprobuj ponownie");

        }
    }
    /*private void sprawdzMiejsceIDodaj(int numerLokalu, Przedmiot obiekt) throws TooManyThingsException, IOException {
        Pomieszczenie p = Magazyn.getMagazyn().get(numerLokalu);
        if(obiekt.pobierzPowierzchniePrzedmiotuZajmowana() > p.powierzchnia) throw new TooManyThingsException("Brak miejsca w tym pomieszczenia");
        else {
            p.getZawartoscPom().add(obiekt);
            Collections.sort(zawartoscPom, new Comparator<Przedmiot>() {
                @Override
                public int compare(Przedmiot o1, Przedmiot o2) {
                    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                    if(o1.getName().equals(o2.getName())) return o1.getName().compareTo(o2.getName());
                    return (int) (o2.pobierzPowierzchniePrzedmiotuZajmowana() - o1.pobierzPowierzchniePrzedmiotuZajmowana());
                }
            });
            p.powierzchniaWolna -= obiekt.pobierzPowierzchniePrzedmiotuZajmowana();
        }
        Magazyn.posortujMagazyn();
        Magazyn.stanMagazynu();
    }*/

    /*public void sprawdziDodaj(Przedmiot obiekt, int numer) throws TooManyThingsException {
        Pomieszczenie p = Magazyn.getMagazyn().get(numer);
        if(p.getPowierzchniaWolna() < obiekt.pobierzPowierzchniePrzedmiotuZajmowana()) throw new TooManyThingsException("Niewystarczajaco duzo miejsca");
        else Magazyn.getMagazyn().get(numer).getZawartoscPom().add(obiekt);


    }*/
        public void wyjmijZPom ( int id) throws IOException {
            zawartoscPom.remove(id);
            this.powierzchniaWolna += obiekt.pobierzPowierzchniePrzedmiotuZajmowana();
            Magazyn.stanMagazynu();
        }

        public void pokazZawartoscLokalu () {
            System.out.println("---------------------------------------- \n Oto stan Twojego lokalu:");
            for (Przedmiot obiekt : zawartoscPom) {
                if (!zawartoscPom.isEmpty()) System.out.println(obiekt + ", o kodzie: " + zawartoscPom.indexOf(obiekt));
            }

        }


        public String toString () {
            return "Pomieszczenie nr." + Magazyn.getMagazyn().indexOf(this)
                    + "\n Powierzchnia calkowita: " + this.getPowierzchnia() + " m3"
                    + "\n Powierzchnia wolna: " + this.getPowierzchniaWolna() + " m3"
                    + "\n";
        }
}


//wkladanie/wyjmowanie
    /*public void wlozDoPom() throws TooManyThingsException, IOException {
        if(obiekt.pobierzPowierzchniePrzedmiotu() > this.powierzchnia) throw new TooManyThingsException("Brak miejsca w tym pomieszczenia");
        else {
            this.zawartoscPom.add(obiekt);
            Collections.sort(zawartoscPom, new Comparator<Przedmiot>() {
                @Override
                public int compare(Przedmiot o1, Przedmiot o2) {
                    // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                    if(o1.getName().equals(o2.getName())) return o1.getName().compareTo(o2.getName());
                    return (int) (o2.pobierzPowierzchniePrzedmiotu() - o1.pobierzPowierzchniePrzedmiotu());
                }
            });
            this.powierzchnia -= obiekt.pobierzPowierzchniePrzedmiotu();
            }
        Magazyn.stanMagazynu();
    }*/
    /*public String wyswietlObiektyzPomieszczenia(){
        for(Przedmiot rzecz:zawartoscPom){
            if(rzecz.getName() != null) return rzecz.getName();
        }
    }*/
