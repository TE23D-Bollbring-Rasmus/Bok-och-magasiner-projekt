package bokmag;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kong.unirest.Unirest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final List<Book> books = new ArrayList<>();
    private static final List<Magazine> magazines = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMENY");
            System.out.println("1. Hämta böcker (från server)");
            System.out.println("2. Hämta tidningar (från server)");
            System.out.println("3. Skriva ut hämtade böcker");
            System.out.println("4. Skriva ut hämtade tidningar");
            System.out.println("5. Lägg till bok");
            System.out.println("6. Lägg till tidning");
            System.out.println("7. Avsluta");
            System.out.print("Val: ");

            int val = Integer.parseInt(sc.nextLine());

            switch (val) {
                case 1 -> hamtaBocker();
                case 2 -> hamtaTidningar();
                case 3 -> skrivUtBocker();
                case 4 -> skrivUtTidningar();
                case 5 -> laggTillBok(sc);
                case 6 -> laggTillTidning(sc);
                case 7 -> running = false;
                default -> System.out.println("Fel val!");
            }
        }
    }

    // HÄMTA BÖCKER
    private static void hamtaBocker() {
        var response = Unirest.get("http://10.151.168.5:3119/books").asString();
        Book[] arr = gson.fromJson(response.getBody(), Book[].class);

        for (Book b : arr) {
            books.add(b);
        }

        System.out.println("Böcker hämtade: " + arr.length);
    }


    // HÄMTA TIDNINGAR

    private static void hamtaTidningar() {
        var response = Unirest.get("http://10.151.168.5:3119/magazines").asString();
        Magazine[] arr = gson.fromJson(response.getBody(), Magazine[].class);

        for (Magazine m : arr) {
            magazines.add(m);
        }

        System.out.println("Tidningar hämtade: " + arr.length);
    }


    // SKRIV UT BÖCKER
    private static void skrivUtBocker() {
        System.out.println("\nBöcker");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    // SKRIV UT TIDNINGAR
    private static void skrivUtTidningar() {
        System.out.println("\nTidningar");
        for (Magazine m : magazines) {
            System.out.println(m);
        }
    }

    // LÄGG TILL BOK

    private static void laggTillBok(Scanner sc) {
        System.out.print("Titel: ");
        String title = sc.nextLine();

        System.out.print("Författare: ");
        String author = sc.nextLine();

        System.out.print("Genre: ");
        String genre = sc.nextLine();

        System.out.print("Antal sidor: ");
        int pages = Integer.parseInt(sc.nextLine());

        Book b = new Book(
                "local-" + (books.size() + 1),
                title,
                true,
                author,
                genre,
                pages
        );

        books.add(b);

        System.out.println("Bok tillagd!");
    }

    // LÄGG TILL TIDNING
    private static void laggTillTidning(Scanner sc) {
        System.out.print("Titel: ");
        String title = sc.nextLine();

        System.out.print("Kategori: ");
        String category = sc.nextLine();

        System.out.print("Nummer: ");
        int issue = Integer.parseInt(sc.nextLine());

        System.out.print("Publiceringsår: ");
        int year = Integer.parseInt(sc.nextLine());

        Magazine m = new Magazine(
                "local " + (magazines.size() + 1),
                title,
                true,
                issue,
                category,
                year
        );

        magazines.add(m);

        System.out.println("Tidning tillagd!");
    }
}
