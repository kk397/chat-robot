FROM openjdk:17-jdk-slim

ENV PARAMS=""

ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD /chatgpt-api-interfaces/target/chat-robot.jar /chatbot-api.jar

ENTRYPOINT ["sh", "-c", "java -jar $JAVA_OPTS /chatbot-api.jar $PARAMS"]