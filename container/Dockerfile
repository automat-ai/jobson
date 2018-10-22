FROM anapsix/alpine-java:8_jdk AS build

ENV LANG=C.UTF-8 \
    MAVEN_HOME=/usr/share/java/maven-3 \
    PATH=${PATH}:/usr/share/java/maven-3/bin

RUN apk add --update-cache \
            --repository http://dl-4.alpinelinux.org/alpine/edge/community/ \
            --allow-untrusted \
          maven \
    && \
    rm -rf /var/cache/apk/*


COPY . /app
WORKDIR /app

ENV artifactId=jobson \
    version=0.0.2

RUN mvn package -DskipTests

###############################################################################

FROM alpine:3.6

# Default to UTF-8 file.encoding
ENV LANG=C.UTF-8 \
    JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk/jre \
    PATH=${PATH}:/usr/lib/jvm/java-1.8-openjdk/jre/bin:/usr/lib/jvm/java-1.8-openjdk/bin \
    artifactId=jobson \
    version=0.0.2

RUN apk add --update-cache  --repository http://dl-4.alpinelinux.org/alpine/edge/main/ --allow-untrusted  "nodejs>8" openjdk8-jre bash jq python python2-dev py-pip build-base curl && \
    rm -rf /var/cache/apk/*

RUN curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl && \
    chmod +x ./kubectl && \
    mv ./kubectl /usr/local/bin/kubectl

COPY --from=build /app/target/jobson-0.0.2.jar /app/src/main/bash/jobson /app/
RUN chmod a+x /app/jobson

WORKDIR /app
EXPOSE 8080 8081
VOLUME /app/jobs /app/specs /app/wds
CMD /app/jobson new && /app/jobson serve config.yml