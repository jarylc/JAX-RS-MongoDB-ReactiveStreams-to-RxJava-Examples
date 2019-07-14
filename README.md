# JAX-RS MongoDB ReactiveStreams to RxJava Examples

#### A one stop project for examples of using the ReactiveStreams MongoDB driver with RxJava.
##### Note: runs embedded MongoDB on separate port 27018 by default, no need to install it.

# Current Implementations Done
  - Jersey on Grizzly container (grizzly-jersey)
  - RestEasy on Undertow container (undertow-resteasy)

# Guide
## 1. Running implementations
### Packaging
```sh
$ maven package
```
### Running
```sh
$ java -jar (implementation)/target/(implementation)-1.0.jar
$ java -jar grizzly-jersey/target/grizzly-jersey-1.0.jar
```

## 2. Endpoints
| METHOD | ENDPOINT | DESCRIPTION |
| ------ | ------ | ------ |
| GET | /user/{userID} | Get user from database
| GET | /user/{userID}/{name}/{phoneNumber} | Create user (GET to be benchmarkable with [wrk](https://github.com/wg/wrk))

## 3. Benchmark
Use your tool of choice like [wrk](https://github.com/wg/wrk) or [JMeter](https://jmeter.apache.org/)
```sh
$ wrk -t12 -c400 -d30s http://127.0.0.1:8080/user/jaryl/Jaryl/01189998819991197253
```

### Todos
 - More implementations (Spring, etc.)