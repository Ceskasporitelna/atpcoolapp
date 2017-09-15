
# Very Cool ATP's Application

Database
```
docker -H localhost:2375 run --name bigsixdb -e POSTGRES_DB=bigsix -e POSTGRES_USER=bigsix -e POSTGRES_PASSWORD=big6 -p 5432:5432 -d postgres
```


Build and run a Docker container
```
docker -H localhost:2375 build -t bigsix .

docker -H localhost:2375 run --name bigsix -p 80:8080 -d bigsix
```