package br.com.jonyfs.spring.boot.data.embedded.mongodb.config;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;

@Configuration
public class MongoConfig {

	private static final String MONGO_DB_URL = "localhost";
	private static final int MONGO_DB_PORT = 12345;

	MongodStarter starter = MongodStarter.getDefaultInstance();
	MongodExecutable mongodExecutable;

	@Bean
	public MongoDbFactory mongoDbFactory() throws UnknownHostException {
		Mongo mongo = new MongoClient(MONGO_DB_URL, MONGO_DB_PORT);
		UserCredentials userCredentials = new UserCredentials("", ""); // username and password
		return new SimpleMongoDbFactory(mongo, "store", userCredentials);
	}

	@Bean
	public MongoTemplate mongoTemplate() throws UnknownHostException {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}

	@PostConstruct
	public void construct() throws UnknownHostException, IOException {
		IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION).net(new Net(MONGO_DB_URL, MONGO_DB_PORT, true)).build();
		mongodExecutable = starter.prepare(mongodConfig);
		MongodProcess mongod = mongodExecutable.start();
	}

	@PreDestroy
	public void destroy() {
		if (mongodExecutable != null) {
			mongodExecutable.stop();
		}
	}
}