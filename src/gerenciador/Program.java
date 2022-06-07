package gerenciador;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        var contacts = List.of(
                new Contacto("contact 1", "+55 (88) 88888-8888", "88888888888", "address 1", 22),
                new Contacto("contact 2", "+55 (77) 77777-7777", "77777777777", "address 2", 29),
                new Contacto("contact 3", "+55 (66) 66666-6666", "66666666666", "address 3", 55),
                new Contacto("contact 4", "+55 (55) 55555-5555", "55555555555", "address 4", 72));

        final String contactFilePath = "./contacts.txt";
        final String contactCSVPath = "./contacts.csv";

        ContactParser file = new ContactFileParser();
        ContactParser csv = (ContactParser) new ContactCSVParser();

        file.write(contactFilePath, contacts);
        csv.write(contactCSVPath, contacts);

        System.out.println("Reading from " + contactFilePath);
        List<Contacto> contactsFromFile = file.read(contactFilePath);
        contactsFromFile.forEach(System.out::println);

        System.out.println("Reading from " + contactCSVPath);
        List<Contacto> contactsFromCSV = csv.read(contactCSVPath);
        contactsFromCSV.forEach(System.out::println);
    }
}