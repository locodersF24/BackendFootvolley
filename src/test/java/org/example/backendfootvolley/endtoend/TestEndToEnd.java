package org.example.backendfootvolley.endtoend;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@DisplayName("End-to-end")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class TestEndToEnd {

    @DisplayName("/auth")
    @Order(1)
    @Nested
    public class First extends TestAuth {}

    @DisplayName("/api/clubs")
    @Order(2)
    @Nested
    public class Second extends TestApiClub {}

}
