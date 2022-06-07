package gerenciador;

import java.util.regex.Pattern;

public class Contacto {

    static final String fileTemplate = "Nombre: %s Telefono: %s CPF: %s Endereco: %s Idade: %d";
    static final Pattern filePattern = Pattern.compile("^\\w+: (.*) \\w+: (.*) \\w+: (.*) \\w+: (.*) \\w+: (\\d+)$");

    static final String CSVTemplate = "%s,%s,%s,%s,%d";
    static final Pattern CSVPattern = Pattern.compile("^([\\w\\s\\d]+),([\\W\\s\\d]+),([\\W\\s\\d]{11,14}),([\\w\\s\\d]+),(\\d+)$");

    private String nombre;
    private String telefono;
    private String cpf;
    private String endereco;
    private int idade;

    public Contacto(String nombre, String telefono, String cpf, String endereco, int idade) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.cpf = cpf;
        this.endereco = endereco;
        this.idade = idade;
    }

    public String toCSVString() {
        return CSVTemplate.formatted(nombre, telefono, cpf, endereco, idade);
    }

    @Override
    public String toString() {
        return fileTemplate.formatted(nombre, telefono, cpf, endereco, idade);
    }
}
