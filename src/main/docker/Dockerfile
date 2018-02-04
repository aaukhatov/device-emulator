FROM java:8
MAINTAINER Arthur Aukhatov <aaukhatov@gmail.com>
COPY *.jar app.jar
CMD java -jar ./app.jar -h $HOST -p $PORT --topic $TOPIC