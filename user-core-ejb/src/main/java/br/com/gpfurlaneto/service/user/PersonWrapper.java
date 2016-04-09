package br.com.gpfurlaneto.service.user;

public class PersonWrapper {
    private final Long id;
    private final String nome;
    
    public PersonWrapper(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
