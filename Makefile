artifactId:=jobson
version:=0.0.2

all: jobson-builder package image

clean: clean-container

jobson-builder:
	docker images | grep jobson-builder 2>&1 > /dev/null || docker build -t jobson-builder -f container/Dockerfile-builder .

package:
	docker run -tv $(shell pwd)/container/tmp/artifact:/app/artifact -v $(shell pwd)/container/tmp/m2:/root/.m2 jobson-builder /bin/sh -c 'mvn package && cp target/${artifactId}-${version}.jar /app/artifact/'

image:
	docker build -t jobson -f container/Dockerfile .

clean-container: clean-artifact clean-m2 clean-image

clean-artifact:
	rm -rf container/tmp/artifact

clean-m2:
	rm -rf container/tmp/m2

clean-image:
	docker rmi jobson-builder

run:
	docker run -tip 8082:8080 -p 8081:8081 jobson