package br.com.fiap.usermanager.controller.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidateError extends StandardError {
    private List<ValidadeMessage> mensagens = new ArrayList<ValidadeMessage>();

    public List<ValidadeMessage> getMessages() {
        return mensagens;
    }

    public void addMensagens(String campo, String mensagem) {
        mensagens.add(new ValidadeMessage(campo, mensagem));
        return;
    }
}
