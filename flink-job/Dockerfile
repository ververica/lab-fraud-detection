FROM eclipse-temurin:8 AS job-build
WORKDIR /home/gradle/app/

COPY build.gradle settings.gradle ./
COPY gradlew ./
COPY gradle gradle
# Cuase download and cache of verifyGoogleJavaFormat dependency
RUN echo "class Dummy {}" > Dummy.java
# download dependencies
RUN ./gradlew build
COPY . .
RUN rm Dummy.java
RUN ./gradlew build


# RUN gradle --no-daemon resolveDependencies
# COPY src src
# RUN gradle --no-daemon build

# ---

FROM flink:1.8.2

# # COPY --from=job-build /home/gradle/work/build/dynamic-fraud-detection-demo.jar lib/job.jar
COPY --from=job-build /home/gradle/app/build/libs/dynamic-fraud-detection-demo-*-all.jar lib/job.jar
# # COPY build/libs/dynamic-fraud-detection-demo.jar lib/job.jar
COPY docker-entrypoint.sh /

USER flink
EXPOSE 8081 6123
ENTRYPOINT ["/docker-entrypoint.sh"]
