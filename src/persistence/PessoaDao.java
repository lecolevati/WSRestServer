package persistence;

import java.util.ArrayList;
import java.util.List;

import model.Pessoa;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class PessoaDao implements IPessoaDao {

	MongoDatabase db;

	public PessoaDao() {
		MongoConnection mc = new MongoConnection();
		db = mc.connection();
	}

	public void inserePessoa(String JSONPessoa) {
		MongoCollection<Document> collection = db.getCollection("pessoa");
		collection.insertOne(Document.parse(JSONPessoa));
	}

	public void deletePessoa(int id){
		db.getCollection("pessoa").deleteOne(new Document("id",id));
	}
	
	public void updatePessoa(int id, String JSONPessoa){
		String idStr = "{id : "+id+"}";
		db.getCollection("pessoa").replaceOne(Document.parse(idStr), 
				Document.parse(JSONPessoa));
	}
	
	public List<Pessoa> consultaPessoas() {
		List<Pessoa> lista = new ArrayList<Pessoa>();
		MongoCollection<Document> collection = db.getCollection("pessoa");
		Gson gson = new Gson();
		for (Document doc : collection.find()) {
			Pessoa p = gson.fromJson(doc.toJson(), Pessoa.class);
			lista.add(p);
		}
		return lista;
	}

	public Pessoa consultaPessoa(int id) {
		final List<Pessoa> lista = new ArrayList<Pessoa>();
		FindIterable<Document> it = db.getCollection("pessoa").find(
				new Document("id", id));
		final Gson gson = new Gson();
		long tamanho = db.getCollection("pessoa").count(new Document("id", id));
		if (tamanho > 0) {
			it.forEach(new Block<Document>() {
				@Override
				public void apply(final Document doc) {
					Pessoa p = gson.fromJson(doc.toJson(), Pessoa.class);
					lista.add(p);
				}
			});
		}
		if (lista.isEmpty()){
			return null;
		} else {
			return lista.get(0);
		}
	}
}
