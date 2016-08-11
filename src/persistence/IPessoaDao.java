package persistence;

import java.util.List;

import model.Pessoa;

public interface IPessoaDao {

	public void inserePessoa(String JSONPessoa);
	public void deletePessoa(int id);
	public void updatePessoa(int id, String JSONPessoa);
	public List<Pessoa> consultaPessoas();
	public Pessoa consultaPessoa(int id);
	
}
