package backend;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import backend.usuario.PessoaFisica;
import backend.Agenda;
import backend.Endereco;
import backend.Pessoa;
import backend.usuario.Medico;

public class AgendaTest {
	private Agenda agenda;
	private PessoaFisica pessoa;
	private Medico medico;
	
	
	@BeforeEach
	void inicaliza() {
		agenda = new Agenda();
		pessoa = new PessoaFisica("Leonardo", "21985473573", "leo@gmail.com", "1012", "123", new Endereco("Paulo Pereira","213"));
		medico = new Medico("Andre", "2126030000", "medicoandre@gmail.com", "321", "Onitorrino");
		
		agenda.adicionarContato(pessoa);
		agenda.adicionarContato(medico);
	}

	
	@Test
	public void testAdicionarContato() {		
		assertEquals(2, agenda.getContatos().size());
		assertTrue(agenda.getContatos().contains(pessoa));
		assertTrue(agenda.getContatos().contains(medico));
	}
	
	@Test
	public void testGetContatosOrdenados() {
		ArrayList<Pessoa> contatos = agenda.getContatos();
		
		assertEquals("Andre", contatos.get(0).getNome());
		assertEquals("Leonardo", contatos.get(1).getNome());
	}
	
	
	@Test
	public void testAdicionarContatoComException() {
		assertThrows(IllegalArgumentException.class, () -> agenda.adicionarContato(null));
	}
	
	@Test
	public void testAdicionarContadoDuplicado() {
		// Bug: O contato já existe na agenda, e não deveria ser adicionado novamente.
		
		agenda.adicionarContato(pessoa);
		
		// O tamanho da agenda aumenta, indicando que o contato duplicado foi adicionado
		assertEquals(3, agenda.getContatos().size());
	}
	
	
	@Test
	public void testAlterarNomeContato() {
		assertTrue(agenda.alterarNomeContato("Andre","Diogo"));
		assertEquals("Diogo", medico.getNome());
	}
	
	@Test
	public void testAlterarNomeContatoParaVazio() {
		// Bug: É permitido alterar o nome de um contato para null
		
		assertTrue(agenda.alterarNomeContato("Andre", null));
		assertEquals(null, medico.getNome());
	}
	
	
	@Test
	public void testAlterarTelContato() {
		assertTrue(agenda.alterarTelContato("Leonardo", "40028922"));
		assertEquals("40028922", pessoa.getTelefone());
	}
	
	@Test
	public void testAlterarTelContatoParaVazio() {
		// Bug: É permitido alterar o telefone de um contato para null
		
		assertTrue(agenda.alterarTelContato("Leonardo", null));
		assertEquals(null, pessoa.getTelefone());
	}
	
	@Test
	public void testAlterarEmailContato() {
		assertTrue(agenda.alterarEmailContato("Andre", "diogosilva@gmail.com"));
		assertEquals("diogosilva@gmail.com", medico.getEmail());
	}
	
	@Test
	public void testAlterarParticularidadeContatoMedico() {
		assertTrue(agenda.alterarParticularidadeContato("Andre", "Clinico Geral"));
		assertEquals("Clinico Geral", medico.getParticularidade());
	}
	
	@Test
	public void testAlterarParticularidadeContatoPessoaF() {
		assertTrue(agenda.alterarParticularidadeContato("Leonardo", new Endereco("Rua Lima Cleto","187")));
		assertEquals("Rua Lima Cleto", pessoa.getEndereco().getNomeDaRua());
		assertEquals("187", pessoa.getEndereco().getNumero());
	}
	
	@Test
	public void testAlterarNomeContatoNaoEncontrado() {
		assertFalse(agenda.alterarNomeContato("Neymar", "Leonardo"));
	}
	
	@Test
	public void testAlterarTelContatoNaoEncontrado() {
		assertFalse(agenda.alterarTelContato("Teodoro", "37192885"));
	}
	
	@Test
	public void testAlterarEmailContatoNaoEncontrado() {
		assertFalse(agenda.alterarEmailContato("João", "diogosilva@gmail.com"));
	}
	
	@Test
	public void testAlterarParticularidadeContatoNaoEncontrado() {
		assertFalse(agenda.alterarParticularidadeContato("Julio", "Clinico geral"));
	}
	
	
	@Test
	public void testRemoverContato() {
		agenda.removerContato("Leonardo");
		assertEquals(1, agenda.getContatos().size());
		assertFalse(agenda.getContatos().contains(pessoa));
	}
	
	@Test
	public void testRemoverContatoComNomeIgual() {
		// Bug: Ao remover um contato que tenha o mesmo nome de outro, o primeiro contato a ser encontrado é removido
		
		PessoaFisica pessoa2 = new PessoaFisica("Leonardo", "21985101117", "abc@gmail.com", "3010", "101010", new Endereco("Tavares","516"));
		agenda.adicionarContato(pessoa2);
		
		agenda.removerContato("Leonardo"); // Queremos remover o contato que acabamos de adicionar à agenda
		assertEquals(2, agenda.getContatos().size());
		
		// Prova de que o contato que queriamos apagar ainda continua na agenda
		assertEquals("21985101117", agenda.getContatos().get(1).getTelefone());
	}
	
	
	@Test
	public void testRemoverContatoNaoEncontrado() {
		assertFalse(agenda.removerContato("Marcelo"));
		assertEquals(2, agenda.getContatos().size());
	}
	
}
