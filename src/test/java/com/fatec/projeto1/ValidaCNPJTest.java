package com.fatec.projeto1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fatec.projeto1.service.ValidaCNPJ;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ValidaCNPJ")
class ValidaCNPJTest {

    private final ValidaCNPJ validador = new ValidaCNPJ();

    @Test
    @DisplayName("aceita CNPJ válido sem formatação")
    void deveAceitarCnpjValidoSemFormatacao() {
        assertTrue(validador.isValido("11222333000181"));
    }

    @Test
    @DisplayName("aceita CNPJ válido com formatação")
    void deveAceitarCnpjValidoComFormatacao() {
        assertTrue(validador.isValido("11.222.333/0001-81"));
    }

    @Test
    @DisplayName("rejeita CNPJ nulo ou vazio")
    void deveRejeitarCnpjNuloOuVazio() {
        assertAll(
                () -> assertFalse(validador.isValido(null)),
                () -> assertFalse(validador.isValido("")));
    }

    @Test
    @DisplayName("rejeita CNPJ com quantidade incorreta de dígitos")
    void deveRejeitarCnpjComQuantidadeIncorretaDeDigitos() {
        assertAll(
                () -> assertFalse(validador.isValido("1122233300018")),
                () -> assertFalse(validador.isValido("112223330001810")));
    }

    @Test
    @DisplayName("rejeita CNPJ formado por dígitos repetidos")
    void deveRejeitarCnpjComDigitosRepetidos() {
        assertAll(
                () -> assertFalse(validador.isValido("00000000000000")),
                () -> assertFalse(validador.isValido("11111111111111")));
    }

    @Test
    @DisplayName("rejeita CNPJ com dígitos verificadores incorretos")
    void deveRejeitarCnpjComDigitosVerificadoresIncorretos() {
        assertFalse(validador.isValido("11222333000182"));
    }
}
