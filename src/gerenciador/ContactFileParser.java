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

public class ContactFileParser implements ContactParser {

    @Override
    public void write(String path, List<Contacto> contacts) {
        Path pathToWrite = Paths.get(Objects.requireNonNull(path));

        List<String> fomatedContacts = Objects.requireNonNull(contacts).stream()
                .map(Contacto::toString)
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
        Matcher contactoFilePattern = Contacto.filePattern.matcher(line);

        if (contactoFilePattern.matches()) {
            String contactName = contactoFilePattern.group(1);
            String phoneNumber = contactoFilePattern.group(2);
            String cpf = contactoFilePattern.group(3);
            String address = contactoFilePattern.group(4);
            Integer age = Integer.valueOf(contactoFilePattern.group(5));

            return new Contacto(contactName, phoneNumber, cpf, address, age);
        }

        return null;
    }
}