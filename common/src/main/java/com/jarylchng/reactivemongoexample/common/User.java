package com.jarylchng.reactivemongoexample.common;

import com.mongodb.reactivestreams.client.*;
import io.reactivex.Single;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class User {
    private String id;
    private String name;
    private String phoneNumber;

    public User() {
    }

    public User(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    public static class DAO {
        private static MongoClient mongoClient = MongoClients.create("mongodb://localhost:27018");
        private static MongoDatabase database = mongoClient.getDatabase("reactiveexample");
        private static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClients.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        private static MongoCollection<User> collection = database.getCollection("user", User.class)
                .withCodecRegistry(pojoCodecRegistry);

        public static Single<User> findByID(String id) {
            return Single.fromPublisher(collection.find(eq("_id", id)).first());
        }

        /*public static Single<User> findByName(String name) {
            return Single.fromPublisher(collection.find(eq("name", name)).first());
        }*/

        public static Single<Success> insert(User user) {
            return Single.fromPublisher(collection.insertOne(user));
        }

        private DAO() {
        }
    }
}
