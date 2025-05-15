package esercizio;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
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
        Ordini ordine4 = new Ordini(7657567L, "spedito", LocalDate.of(2025, 3, 9), LocalDate.of(2025, 3, 13), listaProdotti1, cliente3);

        //Creo una lista di ordini
        List<Ordini> listaOrdini = List.of(ordine1, ordine2, ordine3,ordine4);
        System.out.println("----------------------------------------------");
        System.out.println(listaOrdini);

        //Esercizio1
        Map<Cliente,List<Ordini>> esercizio1 = listaOrdini.stream().collect(Collectors.groupingBy(Ordini::getCustomer));
        System.out.println("----------------------------------------------");
        System.out.println("Esercizio1: "+esercizio1);

        //Esercizio2
         /*
        per ottenere una mappa con customer e relativi totali degli ordini, utilizzo la groupingBy che ha come
        primo parametro il customer e come secondo parametro il richiamo al metodo summingDouble che sommerà
        i totali di tutti gli ordini di quel customer. SummingDouble avrà come parametro una espressione lambdache
        crea uno stream dai prodotti degli ordini e attraverso il metodo mapToDouble le calcola la somma dei prezzi
         */

        Map<Cliente, Double> esercizio2 = listaOrdini.stream()
                .collect(Collectors.groupingBy(
                        Ordini::getCustomer,
                        Collectors.summingDouble(ordine -> ordine.getProducts().stream()
                                .mapToDouble(Prodotti::getPrice)
                                .sum())
                ));
        System.out.println("----------------------------------------------");
        System.out.println("Esercizio2: "+esercizio2);

        //Esercizio3
         /*
        Ho applicato un filtro che va a selezionare solo i prodotti che hanno un prezzo maggiore o uguale al prezzo massimo
        dei prodotti. iL prezzo massimo dei prodotti lo ottengo creando un altro stream collegato a prodotti, dal quale
        ottengo il max.
         */
        List<Prodotti> esercizio3 = listaProdotti.stream().filter(product -> product.getPrice()==listaProdotti.stream().mapToDouble(Prodotti::getPrice).max().getAsDouble()).toList();
        System.out.println("----------------------------------------------");
        System.out.println("Esercizio3: "+esercizio3);


        //Esercizio4
        Double esercizio4 = listaOrdini.stream()
                .mapToDouble(ordine -> ordine.getProducts().stream()
                        .mapToDouble(Prodotti::getPrice)//sommo i prezzi dei prodotti per ogni ordine
                        .sum())
                .average().orElse(0.0);//faccio la media

        System.out.println("----------------------------------------------");
        System.out.println("Esercizio4: "+esercizio4);

        //Esercizio5
        Map<String,Double> esercizio5 = listaProdotti.stream().collect(Collectors.groupingBy(Prodotti::getCategory,Collectors.summingDouble(Prodotti::getPrice)));
        System.out.println("----------------------------------------------");
        System.out.println("Esercizio5: "+esercizio5);

        //Esercizio6-7
        System.out.println("----------------------------------------------");
        System.out.println("Esercizio7: ");
        try {
            salvaProdottiSuDisco(listaProdotti);
            leggiProdottiDaDisco().forEach(product -> System.out.println(product));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }






    }

    //Metodo esercizio6
    public static void salvaProdottiSuDisco(List<Prodotti> prodotti) throws IOException {
        String prodottiStringati = "";
        //con questo stream sui prodotti mappo ogni prodotto con una stringa ottenuta dai campi del prodotto
        //e poi applico allo stream di stringhe così ottenuto, una joining per unire tutte le stringhe concatenandole con #
        prodottiStringati=prodotti.stream().map(product -> {String prodottoStringato =
                product.getName()+"@"+product.getCategory()+"@"+product.getPrice();
            return prodottoStringato;}).collect(Collectors.joining("#"));

        File file = new File("oggettiStringati.txt");

        FileUtils.writeStringToFile(file,prodottiStringati,"UTF-8", false);
    }

    //Metodo esercizio7
    public static List<Prodotti> leggiProdottiDaDisco() throws IOException{
        File file = new File("oggettiStringati.txt");

        String prodottiStringati = FileUtils.readFileToString(file, "UTF-8");

        //con questo metodo split, divido la stringa unica ottenuta, ottenendo un array di stringhe che contiene
        //le stringhe le rappresentano un singolo prodotto
        String[] arrayProdottiStringati = prodottiStringati.split("#");

        //qui ho usato uno stream sull'array precedente. Ho usato map per associare ad ogni stringa presente nell'array
        //un oggetto di tipo Product ottenuto splittando di nuovo la stringa per @ e usando i valori presenti in questo
        // nuovo array come valori per creare un nuovo prodotto. Il toList finale crea una lista di prodotti
        return Arrays.stream(arrayProdottiStringati).map(s -> {String[] prodottoStringato = s.split("@");
            Prodotti p = new Prodotti(new Random().nextLong(1,101),prodottoStringato[0],prodottoStringato[1],Double.parseDouble(prodottoStringato[2]));
            return p;}).toList();
    }
}