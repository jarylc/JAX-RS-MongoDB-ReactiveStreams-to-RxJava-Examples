# JAX-RS MongoDB ReactiveStreams to RxJava Examples

#### A one stop project for examples of using the ReactiveStreams MongoDB driver with RxJava.
##### Note: runs embedded MongoDB on separate port 27018 by default, no need to install it.

# Current Implementations Done
  - Jersey on Grizzly (grizzly-jersey)
  - Jersey on Netty (netty-jersey)
  - RestEasy on Undertow (undertow-resteasy)
  - RestEasy on Netty (netty-resteasy)

## Resource references
  - [Jersey Reactive Resource](https://gitlab.com/jarylc/jax-rs-mongodb-reactivestreams-to-rxjava-examples/blob/master/common-jersey/src/main/java/com/jarylchng/reactivemongoexample/common/ResourceReactive.java) - Jersey does not have support for RxJava and is implemented with Completable
  - [RESTEasy Reactive Resource](https://gitlab.com/jarylc/jax-rs-mongodb-reactivestreams-to-rxjava-examples/blob/master/common-resteasy/src/main/java/com/jarylchng/reactivemongoexample/common/ResourceReactive.java)) - RESTEasy does have pluggable support for RxJava (using resteasy-rxjava2) and is implemented with Single
  - [Synced Resource](https://gitlab.com/jarylc/jax-rs-mongodb-reactivestreams-to-rxjava-examples/blob/master/common/src/main/java/com/jarylchng/reactivemongoexample/common/ResourceSynced.java)

# Guide
## 1. Running implementations
### Packaging
```sh
$ maven package
```
### Running
```sh
$ java -jar (implementation)/target/(implementation).jar
$ java -jar grizzly-jersey/target/grizzly-jersey.jar
```

## 2. Endpoints
| METHOD | ENDPOINT | DESCRIPTION |
| ------ | ------ | ------ |
| GET | /async/{userID} | Get user from database
| GET | /async/{userID}/{name}/{phoneNumber} | Create user
| GET | /sync/{userID} | Get user from database (synced)
| GET | /sync/{userID}/{name}/{phoneNumber} | Create user (synced)

## 3. Benchmark
Use your tool of choice like [wrk](https://github.com/wg/wrk) or [JMeter](https://jmeter.apache.org/)
```sh
$ wrk -t12 -c400 -d30s http://127.0.0.1:8080/user/jaryl/Jaryl%20Chng/01189998819991197253
```

### Todos
 - More implementations (Spring, etc.)