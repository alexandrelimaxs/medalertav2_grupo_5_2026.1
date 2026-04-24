package backend.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import backend.Endereco;
import backend.Medicamento;

class PessoaFisicaTest {

    @Test
    void getNomeArquivoUsos_deveMontarNomeComCpf() {
        PessoaFisica pessoa = novaPessoaBase();

        String nomeArquivo = pessoa.getNomeArquivoUsos();

        assertEquals("backend\\usuario\\arquivosUsosUsuarios\\Uso12345678900.txt", nomeArquivo);
    }

    @Test
    void getUsoListaUsoMedicamentos_deveRetornarNullQuandoListaNaoExiste() {
        PessoaFisica pessoa = novaPessoaBase();

        Uso uso = pessoa.getUsoListaUsoMedicamentos("Paracetamol");

        assertNull(uso);
    }

    @Test
    void getUsoListaUsoMedicamentos_deveRetornarUsoQuandoNomeExistir() {
        PessoaFisica pessoa = novaPessoaBase();
        Uso usoParacetamol = novoUso("Paracetamol");
        Uso usoDipirona = novoUso("Dipirona");
        pessoa.setListaUsoMedicamentos(new ArrayList<>(Arrays.asList(usoParacetamol, usoDipirona)), false);

        Uso uso = pessoa.getUsoListaUsoMedicamentos("Dipirona");

        assertNotNull(uso);
        assertEquals("Dipirona", uso.getRemedio().getNome());
    }

    @Test
    void toString_deveConterNullParaCamposSemListaUso() {
        PessoaFisica pessoa = novaPessoaBase();

        String pessoaString = pessoa.toString();

        assertTrue(pessoaString.contains(",null,"));
        assertTrue(pessoaString.contains("12345678900"));
        assertTrue(pessoaString.contains("Rua Teste/10/Apto 1"));
    }

    @Test
    void toString_deveConterNomeArquivoUsosQuandoListaExistir() {
        PessoaFisica pessoa = novaPessoaBase();
        pessoa.setListaUsoMedicamentos(new ArrayList<>(List.of(novoUso("Paracetamol"))), false);

        String pessoaString = pessoa.toString();

        assertTrue(pessoaString.contains("backend\\usuario\\arquivosUsosUsuarios\\Uso12345678900.txt"));
    }

    @Test
    void setParticularidade_deveAtualizarEndereco() {
        PessoaFisica pessoa = novaPessoaBase();
        Endereco novoEndereco = new Endereco("Rua Nova", "200", "Casa");

        pessoa.setParticularidade(novoEndereco);

        assertEquals("Rua Nova", pessoa.getEndereco().getNomeDaRua());
        assertEquals("200", pessoa.getEndereco().getNumero());
    }

    @Test
    void setCpf_deveAtualizarValorCorretamente() {
        PessoaFisica pessoa = novaPessoaBase();

        pessoa.setCpf("99999999999");

        assertEquals("99999999999", pessoa.getCpf());
    }

    @Test
    void setEndereco_deveAtualizarEnderecoCorretamente() {
        PessoaFisica pessoa = novaPessoaBase();
        Endereco novoEndereco = new Endereco("Rua Nova", "99", "Casa 2");

        pessoa.setEndereco(novoEndereco);

        assertEquals("Rua Nova", pessoa.getEndereco().getNomeDaRua());
        assertEquals("99", pessoa.getEndereco().getNumero());
        assertEquals("Casa 2", pessoa.getEndereco().getComplemento());
    }

    @Test
    void setListaUsoMedicamentos_semPersistencia_deveAtualizarLista() {
        PessoaFisica pessoa = novaPessoaBase();
        List<Uso> usos = new ArrayList<>(List.of(novoUso("Dipirona")));

        pessoa.setListaUsoMedicamentos(usos, false);

        assertNotNull(pessoa.getListaUsoMedicamentos());
        assertEquals(1, pessoa.getListaUsoMedicamentos().size());
        assertEquals("Dipirona", pessoa.getListaUsoMedicamentos().get(0).getRemedio().getNome());
    }

    @Test
    void resgatarListaUsoMedicamentosArquivo_deveRetornarListaVaziaQuandoArquivoNaoExiste() {
        List<Uso> listaUsos = PessoaFisica.resgatarListaUsoMedicamentosArquivo("arquivo-que-nao-existe-123.txt");

        assertTrue(listaUsos.isEmpty());
    }

    private PessoaFisica novaPessoaBase() {
        Endereco endereco = new Endereco("Rua Teste", "10", "Apto 1");
        return new PessoaFisica("Fulano", "21999999999", "fulano@teste.com", "12345678900", "senha123", endereco);
    }

    private Uso novoUso(String nomeRemedio) {
        return new Uso(
                new Medicamento(nomeRemedio),
                1,
                new ArrayList<>(Arrays.asList("08:00", "20:00")),
                7,
                14,
                8,
                12);
    }
}
