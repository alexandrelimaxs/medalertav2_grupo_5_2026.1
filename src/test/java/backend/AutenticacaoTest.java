package backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

class AutenticacaoTest {

    @Test
    void encriptarSenha_deveGerarHashEsperado() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String hash = Autenticacao.encriptarSenha("email@teste.com", "senha123");

        assertEquals("E5EF563A569BF1535EC2D00DCF8EBD19A55BF4A084EEF8989AB9094B612809B7", hash);
    }

    @Test
    void encriptarSenha_deveGerarHashDiferenteParaEmailsDiferentes() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String hashEmailA = Autenticacao.encriptarSenha("a@teste.com", "senha123");
        String hashEmailB = Autenticacao.encriptarSenha("b@teste.com", "senha123");

        assertNotEquals(hashEmailA, hashEmailB);
    }

    @Test
    void autenticar_deveRetornarTrueQuandoSenhaConfere() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String senhaArquivo = Autenticacao.encriptarSenha("email@teste.com", "senha123");

        boolean autenticado = Autenticacao.autenticar("email@teste.com", "senha123", senhaArquivo);

        assertTrue(autenticado);
    }

    @Test
    void autenticar_deveRetornarFalseQuandoSenhaNaoConfere() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String senhaArquivo = Autenticacao.encriptarSenha("email@teste.com", "senha123");

        boolean autenticado = Autenticacao.autenticar("email@teste.com", "senhaErrada", senhaArquivo);

        assertFalse(autenticado);
    }

    @Test
    void autenticar_deveRetornarFalseQuandoEmailNaoConfere() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String senhaArquivo = Autenticacao.encriptarSenha("email@teste.com", "senha123");

        boolean autenticado = Autenticacao.autenticar("outro@teste.com", "senha123", senhaArquivo);

        assertFalse(autenticado);
    }

    @Test
    void autenticar_deveFuncionarQuandoEmailForVazio() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String senhaArquivo = Autenticacao.encriptarSenha("", "senha123");

        boolean autenticado = Autenticacao.autenticar("", "senha123", senhaArquivo);

        assertTrue(autenticado);
    }

    @Test
    void autenticar_deveFuncionarQuandoSenhaForVazia() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String senhaArquivo = Autenticacao.encriptarSenha("email@teste.com", "");

        boolean autenticado = Autenticacao.autenticar("email@teste.com", "", senhaArquivo);

        assertTrue(autenticado);
    }
}
