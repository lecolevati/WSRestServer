package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import model.Pessoa;
import persistence.PessoaDao;

import com.google.gson.Gson;

@Path("/pessoas")
public class PessoaService {

	@Path("/incluir")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String inserirPessoa(String JSONPessoa) {
		PessoaDao pDao = new PessoaDao();
		pDao.inserePessoa(JSONPessoa);
		return "Inserido com sucesso !";
	}
	
	@Path("/consulta")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String consultaPessoas() {
		Gson gson = new Gson();
		return gson.toJson(new PessoaDao().consultaPessoas());
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultaPessoaPorId(@PathParam("id") int id) {
		Gson gson = new Gson();
		String ret = gson.toJson(new PessoaDao().consultaPessoa(id));
		if (!ret.equals("null")) {
			return Response.status(200).entity(ret).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	//@Path("/atualizaPessoa")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String atualizaPessoa(String JSONPessoa) {
		PessoaDao pDao = new PessoaDao();
		Gson gson = new Gson();
		Pessoa p = gson.fromJson(JSONPessoa, Pessoa.class);
		pDao.updatePessoa(p.getId(), JSONPessoa);
		return "Atualizado com sucesso !";
	}

	//@Path("/deletaPessoa/{id}")
	@Path("/{id}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String excluiPessoa(@PathParam("id") int id) {
		new PessoaDao().deletePessoa(id);
		return "Excluído com sucesso !";

	}

}
