package gerenciador;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.stream.Stream;

public class ContactCSVParser implements ContactParser {

    @Override
    public void write(String path, List<Contacto> contacts) {
        Path pathToWrite = Paths.get(Objects.requireNonNull(path));

        List<String> fomatedContacts = Objects.requireNonNull(contacts).stream()
                .map(Contacto::toCSVString)
                .toList();

        try {
            Files.write(pathToWrite, fomatedContacts,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Contacto> read(String path) {
        Path pathToRead = Paths.get(Objects.requireNonNull(path));

        try (Stream<String> lines = Files.lines(pathToRead, StandardCharsets.UTF_8)) {
            return lines.map(this::stringToContact)
                    .filter(Objects::nonNull)
                    .toList();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return List.of();
    }

    private Contacto stringToContact(String line) {
        Matcher contactoCSVPattern = Contacto.CSVPattern.matcher(line);

        if (contactoCSVPattern.matches()) {
            String contactName = contactoCSVPattern.group(1);
            String phoneNumber = contactoCSVPattern.group(2);
            String cpf = contactoCSVPattern.group(3);
            String address = contactoCSVPattern.group(4);
            Integer age = Integer.valueOf(contactoCSVPattern.group(5));

            return new Contacto(contactName, phoneNumber, cpf, address, age);
        }

        return null;
    }
}
