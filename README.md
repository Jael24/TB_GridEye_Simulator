# README #

## Compilation & Run

```bash
mvn clean verify && java -Xmx10g -jar target/grideye-service-log-generator-1.0.0-SNAPSHOT.jar
```

## Swagger documentation
```http request
http://127.0.0.1:4050/lg/docs
```

## Health Check
```http request
GET http://127.0.0.1:4050/lg/health
```

## Services
### Data Insertion
- INFO log every minute while simulating data insertion.
- WARN log every three minutes while discarding one invalid data.
- ERROR log every seven minutes while discarding one data from unknown source.

### CPU processing
- DEBUG log every eleven minutes during a simulation task which consume a lot of CPU during 30 to 60 seconds.
- ERROR log in case of something wrong.

### RAM processing
- DEBUG log every sixteen minutes during a simulation task which consume around 8GB RAM during 30 seconds.
- ERROR log in case of something wrong.

### Crash processing
- ERROR log when application crash twice a day, at 10 a.m. and 4 p.m.
- Be sure to restart the application after ;-)