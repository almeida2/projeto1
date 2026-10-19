package com.fatec.projeto1.service;
public class ValidaCNPJ {
    public boolean isValido(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) {
            return false;
        }

        // Remover todos os caracteres não numéricos
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verificar se possui exatamente 14 dígitos
        if (cnpj.length() != 14) {
            return false;
        }

        // Verificar se todos os dígitos são iguais
        if (cnpj.matches("(\\d).*(\\1)")) {
            return false;
        }

        // Calcular o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * (13 - i);
        }
        int resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : 11 - resto;

        // Calcular o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * (14 - i);
        }
        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : 11 - resto;

        // Verificar os dígitos verificadores
        return (cnpj.charAt(12) - '0' == digito1) && (cnpj.charAt(13) - '0' == digito2);
    }
}
