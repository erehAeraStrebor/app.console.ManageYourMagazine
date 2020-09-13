package gui.projekt.s19852;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class Magazyn
{
    private static Pomieszczenie pomieszczenie;
    private static Osoba osoba;
    private static Przedmiot przedmiot;

    public static ArrayList<Pomieszczenie> getMagazyn() { return magazyn; }

    private static ArrayList<Pomieszczenie> magazyn = new ArrayList<Pomieszczenie>();


    public static void dodajPomieszczenie(Pomieszczenie pom) throws IOException {
        magazyn.add(pom);
        Collections.sort(magazyn, new Comparator<Pomieszczenie>() {
            @Override
            public int compare(Pomieszczenie p1, Pomieszczenie p2) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return (int) (p1.getPowierzchnia() - p2.getPowierzchnia());
            }
        });
        Magazyn.stanMagazynu();

    }

    public static void posortujMagazyn(){Collections.sort(magazyn, new Comparator<Pomieszczenie>() {
        @Override
        public int compare(Pomieszczenie p1, Pomieszczenie p2) {
            // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
            return (int) (p1.getPowierzchnia() - p2.getPowierzchnia());
        }
    });}

    public static void wyswietlWolnePomieszczenia()
    {
        for(Pomieszczenie wolne:magazyn)
        {
            if(wolne.isCzyWolne())System.out.println(wolne);
        }
    }

    public static void stanMagazynu() throws IOException
    {
        FileWriter zapis = null;
        try{
            zapis = new FileWriter("D:\\AKTUALNE_IIrok\\GUI - JAVA\\Ćwiczenia\\ProjektGUI\\stanMagazynu.txt");
            zapis.write("========Magazyn========\n");
            zapis.write("-----------------------\n");
            zapis.write("=====Pomieszczenia=====\n");
            for ( Pomieszczenie pomieszczenie: magazyn) {
                zapis.write("INFO::::Pomieszczenie o ID: " + pomieszczenie.getId() + "\n" + "INFO::::Powierzchnia pomieszczenia: " + pomieszczenie.getPowierzchnia() + "\n" + "INFO::::Powierzchnia wolna: " + pomieszczenie.getPowierzchniaWolna() + "\n");

                if(pomieszczenie.getOwner() != null)
                zapis.write("STATUS::::Wlasciciel: " + pomieszczenie.getOwner().getImie() + " " + pomieszczenie.getOwner().getNazwisko()+ "\n");
                else zapis.write("STATUS:: do wynajecia\n");

                if(!pomieszczenie.getZawartoscPom().isEmpty()) {
                    for (Przedmiot przedmiot : pomieszczenie.getZawartoscPom()) {
                        if (przedmiot.getName() != null)
                            zapis.write("STAN::::Przedmiot : " + przedmiot.getName() + " o powierzchni zajmowanej " + przedmiot.pobierzPowierzchniePrzedmiotuZajmowana() + "\n\n");
                    }
                }else zapis.write("STAN:: Pomieszczenie puste\n\n");
            }
        }finally {
            if(zapis != null){
                zapis.close();
            }
        }
    }



}

