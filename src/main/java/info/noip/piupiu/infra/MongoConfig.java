package info.noip.piupiu.infra;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

public class MongoConfig {

	private static final String MONGO_DATABASE_NAME = "piupiuprod";

	public String getDatabaseName() {
		return MONGO_DATABASE_NAME;
	}

	public Mongo mongo() throws Exception {
		MongoClient mongo = new MongoClient("piupiu.no-ip.info");
		mongo.setWriteConcern(WriteConcern.ACKNOWLEDGED);
		return mongo;
	}

}
