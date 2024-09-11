import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Namenseingabe mit Überprüfung auf leere Eingabe
        String name;
        while (true) {
            System.out.print("Bitte geben Sie Ihren Namen ein: ");
            name = scanner.nextLine().trim();

            if (!name.isEmpty()) {
                break; // Schleife verlassen, wenn der Name nicht leer ist
            } else {
                System.out.println("Der Name darf nicht leer sein. Bitte versuchen Sie es erneut.");
            }
        }

        // Hauptprogramm
        while (true) {
            System.out.println("Hallo " + name + "! Wählen Sie eine Option:");
            System.out.println("1. Tage zwischen Bestellung und Auslieferung berechnen");
            System.out.println("2. Arbeitszeit berechnen");

            String choice;
            while (true) {
                System.out.print("Ihre Wahl (1/2): ");
                choice = scanner.nextLine().trim();

                if (choice.equals("1") || choice.equals("2")) {
                    break; // Gültige Auswahl, Schleife verlassen
                } else {
                    System.out.println("Ungültige Auswahl. Bitte geben Sie '1' für Bestellung oder '2' für Arbeitszeit ein.");
                }
            }

            if (choice.equals("1")) {
                // Berechnung der Tage zwischen Bestellung und Auslieferung
                String orderDateInput, deliveryDateInput;

                while (true) {
                    System.out.print("Bitte geben Sie das Bestelldatum im Format 'dd-MM-yyyy' ein: ");
                    orderDateInput = scanner.nextLine();

                    System.out.print("Bitte geben Sie das Lieferdatum im Format 'dd-MM-yyyy' ein: ");
                    deliveryDateInput = scanner.nextLine();

                    try {
                        LocalDate orderDate = LocalDate.parse(orderDateInput, dateFormatter);
                        LocalDate deliveryDate = LocalDate.parse(deliveryDateInput, dateFormatter);

                        long daysBetween = Duration.between(orderDate.atStartOfDay(), deliveryDate.atStartOfDay()).toDays();
                        System.out.println("Die Anzahl der Tage zwischen Bestellung und Auslieferung beträgt: " + daysBetween + " Tage");
                        break; // Schleife verlassen, wenn die Eingabe gültig ist
                    } catch (Exception e) {
                        System.out.println("Ungültiges Datum. Bitte verwenden Sie das Format 'dd-MM-yyyy'.");
                    }
                }

            } else if (choice.equals("2")) {
                // Berechnung der Arbeitszeit
                String startDateTimeInput, endDateTimeInput;

                while (true) {
                    System.out.print("Bitte geben Sie den Arbeitsbeginn im Format 'dd-MM-yyyy HH:mm:ss' ein: ");
                    startDateTimeInput = scanner.nextLine();

                    System.out.print("Bitte geben Sie das Arbeitsende im Format 'dd-MM-yyyy HH:mm:ss' ein: ");
                    endDateTimeInput = scanner.nextLine();

                    try {
                        LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeInput, dateTimeFormatter);
                        LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeInput, dateTimeFormatter);

                        Duration duration = Duration.between(startDateTime, endDateTime);
                        long hours = duration.toHours();
                        long minutes = (duration.toMinutes() % 60);
                        long seconds = (duration.getSeconds() % 60);

                        System.out.printf("Die Arbeitszeit beträgt: %d Stunden, %d Minuten und %d Sekunden%n", hours, minutes, seconds);
                        break; // Schleife verlassen, wenn die Eingabe gültig ist
                    } catch (Exception e) {
                        System.out.println("Ungültiges Datum/Zeitformat. Bitte verwenden Sie das Format 'dd-MM-yyyy HH:mm:ss'.");
                    }
                }
            }

            // Abfrage, ob der Benutzer fortfahren möchte
            String continueChoice;
            while (true) {
                System.out.print("Möchten Sie eine weitere Berechnung durchführen? (J/N): ");
                continueChoice = scanner.nextLine().trim().toUpperCase();

                if (continueChoice.equals("J")) {
                    break; // Schleife neu starten
                } else if (continueChoice.equals("N")) {
                    System.out.println("Danke, dass Sie unser Programm verwendet haben. \nAuf Wiedersehen, " + name + "!");
                    scanner.close();
                    return; // Programm beenden
                } else {
                    System.out.println("Ungültige Auswahl. Bitte geben Sie 'J' für Ja oder 'N' für Nein ein.");
                }
            }
        }
    }
}
