FROM docker.io/openjdk:8-jdk

USER root
ARG service=blancblock-account
ARG port=8171
ARG xxljob_port=50006

ENV JAVA_ARGS="-Dfile.encoding=UTF8 -Duser.timezone=GMT+08"
ENV APP_NAME=${service}
ENV SKYWALKING_COLLECTOR_BACKEND_SERVICE="18.143.93.135:11800"
ENV JAVA_OPTS="-Xms2048m -Xmx2048m  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/logs/${service}/dump.hprof"



WORKDIR /data/${service}
RUN mkdir -p  /usr/local/skywalking-agent
ADD ./blancblock-account-web/target/${service}.jar ${service}.jar
COPY ./skywalking-agent /usr/local/skywalking-agent
RUN echo "Asia/Shanghai" > /etc/timezone

java -Dspring.profiles.active=prd -jar yudao-server.jar --spring.config.location=/opt/backend/application-prd.yaml

EXPOSE ${port}
EXPOSE ${xxljob_port}

CMD java ${JAVA_ARGS} ${JAVA_OPTS} -Dspring.profiles.active=prd -verbose:gc -javaagent:/usr/local/skywalking-agent/skywalking-agent.jar   -Dskywalking.agent.service_name=$SPRING_PROFILES_ACTIVE::$APP_NAME -Dskywalking.collector.backend_service=$SKYWALKING_COLLECTOR_BACKEND_SERVICE  -jar ${APP_NAME}.jar

