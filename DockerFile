FROM openjdk:11-slim
ADD /target/news.jar news.jar
ENV JAVA_OPTS="-Xms128m -Xmx300m"
ENTRYPOINT exec java $JAVA_OPTS -jar news.jar