package persistence;

import java.io.IOException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

	public MongoConnection() {
		try {
			Runtime.getRuntime().exec("mongod");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public MongoDatabase connection() {
		MongoClient mc = null;
		MongoDatabase db = null;
		try {
			mc = new MongoClient();
			db = mc.getDatabase("test");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return db;
	}

}