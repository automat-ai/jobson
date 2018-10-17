artifactId:=jobson
version:=0.0.2
NAMESPACE:=${USER}

all: jobson-builder package image

clean: clean-container

jobson-builder:
	docker images | grep jobson-builder 2>&1 > /dev/null || docker build -t jobson-builder -f container/Dockerfile-builder .

package:
	docker run -tv $(shell pwd)/container/tmp/artifact:/app/artifact -v $(shell pwd)/container/tmp/m2:/root/.m2 jobson-builder /bin/sh -c 'mvn package -DskipTests && cp target/${artifactId}-${version}.jar /app/artifact/${artifactId}-${version}.jar'

image:
	docker build -t jobson -f container/Dockerfile .

clean-container: clean-artifact clean-m2 clean-image

clean-artifact:
	rm -rf container/tmp/artifact

clean-m2:
	rm -rf container/tmp/m2

clean-image:
	-docker rmi -f jobson-builder

push:
	docker tag jobson ${NAMESPACE}/jobson
	docker tag jobson ${NAMESPACE}/jobson:${version}
	docker push ${NAMESPACE}/jobson
	docker push ${NAMESPACE}/jobson:${version}

run:
	- docker rm -f jobson >& /dev/null
	docker run -d --name jobson -h jobson -ti -v $(shell pwd)/container/tmp/app/jobs:/app/jobs -v $(shell pwd)/container/tmp/app/specs:/app/specs -v $(shell pwd)/container/tmp/app/wds:/app/wds jobson