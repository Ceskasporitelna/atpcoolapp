
# Very Cool ATP's Application

Build and run a Docker container
```
docker -H localhost:2375 build -t atpcoolapp .

docker -H localhost:2375 run --name atpcoolapp01 -p 80:8080 -d atpcoolapp
```