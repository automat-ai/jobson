FROM alpine:3.6

# Default to UTF-8 file.encoding
ENV LANG=C.UTF-8 \
    JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk/jre \
    PATH=${PATH}:/usr/lib/jvm/java-1.8-openjdk/jre/bin:/usr/lib/jvm/java-1.8-openjdk/bin \
    artifactId=jobson \
    version=0.0.2

RUN apk add --update-cache  --repository http://dl-4.alpinelinux.org/alpine/edge/main/ --allow-untrusted  "nodejs>8" openjdk8-jre bash jq && \
    rm -rf /var/cache/apk/*

COPY container/tmp/artifact/jobson-0.0.2.jar src/main/bash/jobson /app/
RUN chmod a+x /app/jobson

WORKDIR /app
EXPOSE 8080 8081
VOLUME /app/jobs /app/specs /app/wds
CMD /app/jobson new && /app/jobson serve config.yml