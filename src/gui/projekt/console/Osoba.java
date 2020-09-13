package gui.projekt.s19852;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Osoba
{
    Pomieszczenie p;
    private String imie, nazwisko, dataUrodzenia;
    private static LocalDate startWynajmu;
    private int identyfikator;
    private String PESEL;

    private static final ArrayList <Pomieszczenie> mojeWynajete = new ArrayList<Pomieszczenie>();

    public ArrayList<Pomieszczenie> getListaWynajetychPomieszczen() {
        return listaWynajetychPomieszczen;
    }

    private static ArrayList<Pomieszczenie> listaWynajetychPomieszczen = new ArrayList<Pomieszczenie>();
    private static ArrayList<Osoba> zarejestrowanyNajemca = new ArrayList<Osoba>();
    //private Optional<LocalDate> op = Optional.ofNullable(startWynajmu);

    Osoba(String imie, String nazwisko, String dataUrodzenia,String PESEL)
    {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.PESEL = PESEL;
        this.zarejestrowanyNajemca.add(this);
        this.identyfikator = zarejestrowanyNajemca.indexOf(this);

    }
    public String getImie() { return this.imie; }
    public String getNazwisko()
    {
        return this.nazwisko;
    }
    public LocalDate getStartWynajmu() { return startWynajmu; }

    public void pokazWynajete(Osoba osoba)
    {
    for(Pomieszczenie wynajete: osoba.listaWynajetychPomieszczen){
        System.out.println(wynajete +  "\n");
    }
    if(listaWynajetychPomieszczen.isEmpty())System.out.println("Brak wynajetych lokali");
    }

    public static void pokazdlaosoby(int idOsoby){
        if(zarejestrowanyNajemca.contains((zarejestrowanyNajemca.get(idOsoby))))
        {
            Osoba o = Osoba.zarejestrowanyNajemca.get(idOsoby);
            for(Pomieszczenie l : listaWynajetychPomieszczen)
            {
                if(l.getOwner()==o){
                    System.out.println(l);
                }
            }
        }
    }

    public void pierwszyWynajemINFO(){
        Optional<LocalDate> op = Optional.ofNullable(startWynajmu);

        if (op.isPresent())
        {
            System.out.println("Data Twojego pierwszego najmu " + op.get());
        }

       /*if(op.isEmpty()) throw new NeverRentException("Do tej pory nie wynajales zadnego pomieszczenia.");
       else System.out.println("Pomieszczenie nr. " + p.getId() + " zostalo wynajete: " + op.get());

       Magazyn.stanMagazynu();*/
    }
    public static void wynajmijPomieszczenie(int idOsoby, int numerLokalu) throws IOException {

        if(zarejestrowanyNajemca.contains(zarejestrowanyNajemca.get(idOsoby)) && Magazyn.getMagazyn().contains(Magazyn.getMagazyn().get(numerLokalu))) {
            Osoba o = Osoba.zarejestrowanyNajemca.get(idOsoby);
            Pomieszczenie p = Magazyn.getMagazyn().get(numerLokalu);

            if (p.isCzyWolne() && p.isCzyRemont() == false) {
                if (startWynajmu == null) o.startWynajmu = LocalDate.now();
                p.setOwner(o);
                p.setCzyWolne(false);
                o.getListaWynajetychPomieszczen().add(p);

            } else System.err.println("Pomieszczenie niedostepne");
        }else System.out.println("Wprowadzono zle dane");

    }

    public static void pokaNajemcowbyku(){
        for(Osoba najemcaZarejestrowany: zarejestrowanyNajemca)System.out.println(najemcaZarejestrowany);
    }


    public static void wynajmijdlaOsoby(Osoba o, int numerLokalu){
        if( Magazyn.getMagazyn().get(numerLokalu ) != null){

           Pomieszczenie l = Magazyn.getMagazyn().get(numerLokalu);
           if(l.isCzyWolne() && l.isCzyRemont() == false){
               o.mojeWynajete.add(l);
           }else System.out.println("lokal niedostepny");
        }else System.out.println("Najpierw prosze podac osobe oraz lokal dla ktorych ma zostac dokonana interakcja");
    }

    public static void pokazMojeWynajete(Osoba o){
        if(o!=null){

            for(Pomieszczenie l : mojeWynajete)System.out.println(l);
        }
    }


    public static Osoba wybierzOsobe(int idOsoby) {

        Osoba tmp = null;
        for (Osoba o : zarejestrowanyNajemca) {
            if (o.identyfikator == idOsoby) tmp = zarejestrowanyNajemca.get(idOsoby);
        }
        if (tmp != null) return tmp;
        else {
            System.out.println("Blad - brak zarejestrowanej osoby");
            return null;
        }
    }

    public static Pomieszczenie wybierzLokal(int numerLokalu){
        Pomieszczenie lokal = null;
        for(Pomieszczenie l : Magazyn.getMagazyn()){
            if (Magazyn.getMagazyn().indexOf(l)==numerLokalu) lokal = Magazyn.getMagazyn().get(numerLokalu);
        }
        if (lokal != null) return lokal;
        else {
            System.out.println("Blad - brak lokalu na terenie magazynu");
            return null;
        }
    }

    /*public static Pomieszczenie wybierzLokalDlaOsoby(int numerek){
        Pomieszczenie lokal = null;
        for(Pomieszczenie l : listaWynajetychPomieszczen){
            if(listaWynajetychPomieszczen.indexOf(l) == numerek) lokal = listaWynajetychPomieszczen.get(numerek);
        }
        if(lokal!=null)return lokal;
        else {
            System.out.println("Blad, brak wynajmowanego lokalu");
            return null;
        }
    }*/
        /*Osoba osoba;
        int tmp=-1;

        for(Osoba o:zarejestrowanyNajemca){
            if(zarejestrowanyNajemca.indexOf(o)==idOsoby){
                tmp=zarejestrowanyNajemca.indexOf(o);
            }
        }
        if(tmp!=-1) {
            osoba = zarejestrowanyNajemca.get(tmp);
            return osoba;
        }
        else{
            System.out.println("Nie ma osoby o takim ID.");
            return null;
        }*/

        //public static void sprawdzenieCaseTrzyMain(Osoba os)
        /*{
            if(os!=null && !os.listaWynajetychPomieszczen.isEmpty()){
                os.pokazWynajete();
                System.out.print("Wybierz numer lokalu, aby zerknac do srodka --> ");
            }else if(os == null) System.out.println("nie wybrales profilu najemcy, ktorym jestes");
            else System.out.println("nie podnajmujesz zadnego lokalu");
        }*/

        public static void pokaZawartoscLokalu(Osoba o, int numerekLokalu)
        {

        }




    public String toString(){
        return "Zarejestrowany najemca: " + imie + " " + nazwisko +" o systemowym ID: "+zarejestrowanyNajemca.indexOf(this) + "\n" + "PESEL: " + PESEL ;
    }

}
