package com.jarylchng.reactivemongoexample.common;

import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.*;
import io.reactivex.Single;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class User {
    @BsonId
    private String id;
    private String userID;
    private String name;
    private String phoneNumber;

    public User() {
    }

    public User(String userID, String name, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.userID = userID;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String id) {
        this.userID = id;
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
                "id='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    public static class ReactiveDAO {
        private static MongoClient mongoClient = MongoClients.create("mongodb://localhost:27018");
        private static MongoDatabase database = mongoClient.getDatabase("reactiveexample");
        private static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClients.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        private static MongoCollection<User> collection = database.getCollection("user", User.class)
                .withCodecRegistry(pojoCodecRegistry);

        public static Single<User> findByID(String id) {
            return Single.fromPublisher(collection.find(eq("userID", id)).first());
        }

        public static Single<Success> insert(User user) {
            return Single.fromPublisher(collection.insertOne(user));
        }

        private ReactiveDAO() {
        }
    }

    public static class SyncedDAO {
        private static com.mongodb.client.MongoClient mongoClient = com.mongodb.client.MongoClients.create("mongodb://localhost:27018");
        private static com.mongodb.client.MongoDatabase database = mongoClient.getDatabase("reactiveexample");
        private static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        private static com.mongodb.client.MongoCollection<User> collection = database.getCollection("user", User.class)
                .withCodecRegistry(pojoCodecRegistry);

        public static User findByID(String id) {
            return collection.find(eq("userID", id)).first();
        }

        public static void insert(User user) {
            collection.insertOne(user);
        }

        private SyncedDAO() {
        }
    }
}
