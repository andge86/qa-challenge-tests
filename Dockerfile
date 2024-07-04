FROM maven:3.9.8-amazoncorretto-21-al2023

COPY . .

ENTRYPOINT ["mvn", "clean", "test"]
