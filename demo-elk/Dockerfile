FROM openjdk:8u212-b04-jre-slim

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms1024m -Xmx1024m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /app

WORKDIR /app

EXPOSE 9004

ADD ./target/demo-elk-0.0.1-SNAPSHOT.jar ./

CMD sleep 120;java $JAVA_OPTS -jar demo-elk-0.0.1-SNAPSHOT.jar