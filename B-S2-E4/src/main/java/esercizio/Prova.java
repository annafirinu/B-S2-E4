package esercizio;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Prova {
    public static void main(String[] args) {
        //Prodotti
        Prodotti libro1 = new Prodotti(1234L, "Il piccolo principe", "Books", 101.80);
        Prodotti libro2 = new Prodotti(4321L, "Orgoglio e pregiudizio", "Books", 9.20);
        Prodotti libro3 = new Prodotti(2134L, "Il nome della rosa", "Books", 111.50);
        Prodotti baby1 = new Prodotti(5678L, "Pannolini", "Baby", 12.50);
        Prodotti baby2 = new Prodotti(8765L, "Omogeneizzato", "Baby", 4.09);
        Prodotti baby3 = new Prodotti(6578L, "Latte in polvere", "Baby", 23.27);
        Prodotti ragazzi1 = new Prodotti(9012L, "Orologio", "Boys", 100.0);
        Prodotti ragazzi2 = new Prodotti(1290L, "Auricolari", "Boys", 38.95);
        Prodotti ragazzi3 = new Prodotti(2091L, "Telefono", "Boys", 260.40);

        //Creo una lista di prodotti
        List<Prodotti> listaProdotti = List.of(libro1, libro2, libro3, baby1, baby2, baby3, ragazzi1, ragazzi2, ragazzi3);
        List<Prodotti> listaProdotti1 = List.of(libro1, baby1, baby2);
        List<Prodotti> listaProdotti2 = List.of(libro3, ragazzi2, ragazzi3);
        List<Prodotti> listaProdotti3 = List.of(baby1, baby2, ragazzi3);
        System.out.println(listaProdotti);


        //Clienti
        Cliente cliente1 = new Cliente(9879L, "Mario", 2);
        Cliente cliente2 = new Cliente(9877L, "Carlo", 1);
        Cliente cliente3 = new Cliente(9999L, "Andrea", 2);

        //Creo ordini
        Ordini ordine1 = new Ordini(8758975L, "creato", LocalDate.of(2021, 3, 4), LocalDate.of(2021, 3, 6), listaProdotti1, cliente1);
        Ordini ordine2 = new Ordini(768765876L, "pagato", LocalDate.of(2025, 2, 7), LocalDate.of(2025, 2, 11), listaProdotti2, cliente2);
        Ordini ordine3 = new Ordini(98796876L, "spedito", LocalDate.of(2025, 3, 9), LocalDate.of(2025, 3, 13), listaProdotti3, cliente3);
        //Creo una lista di ordini
        List<Ordini> listaOrdini = List.of(ordine1, ordine2, ordine3);
        System.out.println("----------------------------------------------");
        System.out.println(listaOrdini);

    }
}