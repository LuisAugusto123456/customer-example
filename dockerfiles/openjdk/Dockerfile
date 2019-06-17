FROM openjdk:8-jdk-alpine
WORKDIR /app
ARG artifact_id
ARG artifact_version
ENV jar_file="${artifact_id}-${artifact_version}.jar"
COPY target/${jar_file} /app
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar /app/${jar_file} -Duser.timezone=America/Lima ${java_options}"]