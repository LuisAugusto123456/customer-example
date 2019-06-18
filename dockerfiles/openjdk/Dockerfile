FROM openjdk:8-jdk-alpine

MAINTAINER Luis Augusto Paz Davila <lpazd2006unp@gmail.com>

CMD ["sh","-c","java -jar /data/${artifact_id}/jar/${artifact_id}-${artifact_version}.jar -Duser.timezone=America/Lima -Dambiente=${ambiente} ${java_options}"]
