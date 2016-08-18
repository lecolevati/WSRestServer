package test;

import com.google.gson.Gson;

import model.Pessoa;
import persistence.PessoaDao;

public class MainTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Pessoa p = new Pessoa();
		p.setId(1);
		p.setNome("Le");
		p.setSobrenome("Santos");
		Gson gson = new Gson();
		String pessoaJSONString = gson.toJson(p);
		
		PessoaDao pDao = new PessoaDao();
//		pDao.inserePessoa(pessoaJSONString);
//		pDao.updatePessoa(p.getId(), pessoaJSONString);
		pDao.deletePessoa(3);
		
		System.out.println(pDao.consultaPessoas());
	}
	
}
