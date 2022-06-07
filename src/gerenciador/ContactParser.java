package gerenciador;

import java.util.List;

public interface ContactParser {

    void write(String path, List<Contacto> contacts);

    List<Contacto> read(String path);
}
