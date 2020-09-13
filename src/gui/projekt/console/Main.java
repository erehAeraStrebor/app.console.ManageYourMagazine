package gui.projekt.s19852;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, TooManyThingsException, NeverRentException {
        Pomieszczenie pom1 = new Pomieszczenie(Math.random()*250+75);
        Pomieszczenie pom2 = new Pomieszczenie(Math.random()*250+75);
        Pomieszczenie pom3 = new Pomieszczenie(Math.random()*250+75);
        Pomieszczenie pom4 = new Pomieszczenie(Math.random()*250+75);
        Pomieszczenie pom5 = new Pomieszczenie(Math.random()*250+75);
        Pomieszczenie pom6 = new Pomieszczenie(Math.random()*250+75);
        Pomieszczenie pom7 = new Pomieszczenie(Math.random()*250+75);
        Pomieszczenie pom8 = new Pomieszczenie(Math.random()*250+75);
        Pomieszczenie pom9 = new Pomieszczenie(Math.random()*250+75);
        Pomieszczenie pom10 = new Pomieszczenie(Math.random()*250+75);


        Osoba osoba1 = new Osoba("Billy", "Monroe", "03-07-1959","12345678910" );
        Osoba osoba2 = new Osoba("Kim", "Weithethreads", "07-03-1979", "25438572339");
        Osoba osoba3 = new Osoba("Anil", "Kalasznikov", "12-04-1992", "98987612345");
        Osoba osoba4 = new Osoba("Edmund", "Greethick", "03-01-1986", "23782378739");
        Osoba osoba5 = new Osoba("Gregor", "Ambiwalent", "05-09-1980", "45237878739");



        Magazyn.dodajPomieszczenie(pom1);
        Magazyn.dodajPomieszczenie(pom2);
        Magazyn.dodajPomieszczenie(pom3);
        Magazyn.dodajPomieszczenie(pom4);
        Magazyn.dodajPomieszczenie(pom5);
        Magazyn.dodajPomieszczenie(pom6);
        Magazyn.dodajPomieszczenie(pom7);
        Magazyn.dodajPomieszczenie(pom8);
        Magazyn.dodajPomieszczenie(pom9);
        Magazyn.dodajPomieszczenie(pom10);



        /*pom1.wlozNowyObiektDoPom();
        pom1.wlozNowyObiektDoPom();
        System.out.println(pom1.toString());
        osoba1.wynajmijPomieszczenie(pom2);
        osoba1.wynajmijPomieszczenie(pom5);
        osoba1.pokazWynajete();*/
//wyswietlanie menu
        Scanner menuStartoweOdczyt = new Scanner(new File("menuWyboru.txt"));
        while(menuStartoweOdczyt.hasNextLine())System.out.println(menuStartoweOdczyt.nextLine());
        menuStartoweOdczyt.close();
        Osoba.pokaNajemcowbyku();

        Scanner wejscie = new Scanner(System.in);
        int opcja; //String opcja -< tu byla
        Osoba najemcaWybrany = null;
        Pomieszczenie lokal = null;
        int id = -1;
        int numerLokalu = -1;
        do {
            System.out.println();
            System.out.print("WYBIERZ OPCJE Z MENU: ");
            opcja = wejscie.nextInt();  //<-- a tu nextLine
            //opcja = wejscie.nextLine();

                switch(opcja)
                {
                    case 9:
                        System.out.println("Dziekujemy za skorzystanie z programu, do widzenia");
                        System.exit(1);
                                break;
                    case 1:
                        System.out.print("Która jestes osoba? Wpisz ID.");
                        id = wejscie.nextInt();
                        najemcaWybrany = Osoba.wybierzOsobe(id);
                        //if(najemcaWybrany!=null)System.out.println(najemcaWybrany);
                                break;
                    case 2:

                        if(najemcaWybrany!=null) {
                            System.out.println(najemcaWybrany);
                            if(najemcaWybrany!=null)najemcaWybrany.pokazWynajete(najemcaWybrany);//Osoba.pokazdlaosoby(id);
                            //najemcaWybrany.pokazWynajete(najemcaWybrany);
                           // Osoba.pokazMojeWynajete(najemcaWybrany);
                        }
                        else{
                            System.out.println("Nie wybrales osoby ktora jestes.");
                        }
                                break;
                    case 3:
                        if(najemcaWybrany!=null && !najemcaWybrany.getListaWynajetychPomieszczen().isEmpty()){
                            najemcaWybrany.pokazWynajete(najemcaWybrany);
                            System.out.print("Wybierz numer lokalu, aby zerknac do srodka --> "); numerLokalu = wejscie.nextInt();
                            if(numerLokalu != -1 && Osoba.wybierzLokal(numerLokalu)!=null)
                            {
                                lokal = Osoba.wybierzLokal(numerLokalu);
                                lokal.pokazZawartoscLokalu();
                            }
                        }else if(najemcaWybrany == null) System.out.println("nie wybrales profilu najemcy, ktorym jestes");
                        else System.out.println("nie podnajmujesz zadnego lokalu");
                                break;

                    case 4:
                        if(id != -1)
                        {
                            if(najemcaWybrany == null)System.out.println("Wybierz profil najemcy");
                            else {
                                System.out.println("Podaj numer lokalu do ktorego chcesz cos wsadzic");
                                numerLokalu = wejscie.nextInt();
                                if (numerLokalu != -1) {
                                    lokal = Osoba.wybierzLokal(numerLokalu);
                                    if(lokal.getOwner() == najemcaWybrany && Magazyn.getMagazyn().indexOf(lokal)==numerLokalu) {
                                       // Scanner osobny = new Scanner(System.in);
                                        //System.out.println("Podaj unikalny ID do dodawania");
                                        //int idlokalu = osobny.nextInt();
                                        lokal.wlozObiekt(numerLokalu);

                                    }
                                    else {
                                        System.out.println("Nie jestes wlascicielem tego lokalu, nastepnym razem wybierz numer lokalu ze swojej listy");
                                        najemcaWybrany.pokazWynajete(najemcaWybrany);
                                    }
                                } else System.out.println("Niedozwolona wartosc");
                            }
                        }else System.out.println("Wybierz profil najemcy");
                        break;


                        /*if(najemcaWybrany==null)System.out.println("Wybierz profil najemcy");
                        else if(lokal == null)System.out.println("Wybierz lokal, do ktorego chcesz wlozyc nowy obiekt");
                        else lokal.wlozNowyObiektDoPom();
                            break;*/

                    case 5:
                        if(najemcaWybrany==null)System.out.println("Wybierz profil najemcy");
                        else if(lokal == null)System.out.println("Wybierz lokal, aby usunac dowolny obiekt");
                        else {
                            System.out.println("Wybierz kod obiektu, aby usunac go z magazynu");
                            lokal.pokazZawartoscLokalu();
                            int kod = wejscie.nextInt();
                            lokal.getZawartoscPom().remove(kod);
                            //lokal.wyjmijZPom(kod);
                        }
                        break;

                    case 6:
                        Magazyn.wyswietlWolnePomieszczenia();
                        break;
                    case 7:
                       /* System.out.println("Podaj id osoby dla wynajmu");
                        Scanner osobny = new Scanner(System.in);
                        int idOsoby  = osobny.nextInt();
                        System.out.println("Podaj numer lokalu do wynajmu");
                        int numerL = osobny.nextInt();
                        Osoba.wynajmijPomieszczenie(idOsoby,numerL);
                        Magazyn.stanMagazynu();*/

                       if(id!=-1 && najemcaWybrany!=null)
                       {
                           if(najemcaWybrany!=null)System.out.println("Podaj lokal ktory chcesz wynajac dla osoby " + najemcaWybrany);
                           numerLokalu = wejscie.nextInt();
                           if(numerLokalu!=-1)
                           {
                                if(Osoba.wybierzLokal(numerLokalu)!=null) {
                                        Osoba.wynajmijPomieszczenie(id, numerLokalu);
                                        Magazyn.getMagazyn().get(numerLokalu).setOwner(najemcaWybrany);
                                }
                           Magazyn.stanMagazynu();
                           }
                       }else System.out.println("Cofnij sie do wyboru postaci - opcja numer 1");

                            break;
                    default: System.out.println("wybierz prawidlowa opcje");
                            break;
                }


        }while(opcja!=9);


    }
}
//System.out.println(" \n napisz START aby przejsc do menu, lub 'q' aby wyjsc z programu");
//if(opcja.equalsIgnoreCase("q"))System.exit(-1);
//else if(opcja.equalsIgnoreCase("START"))System.out.println("Witamy w programie, milo Cie znow widziec");
//else System.out.println("Podaj odpowiednia wartosc");