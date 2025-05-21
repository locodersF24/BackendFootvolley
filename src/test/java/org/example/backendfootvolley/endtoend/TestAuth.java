package org.example.backendfootvolley.endtoend;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class TestAuth {

    @Autowired
    private WebTestClient webTestClient;

    @DisplayName("/token POST Admin correct password")
    @Order(1)
    @Test
    public void loginAsAdminWithCorrectCredentials() {

        // Arrange
        String requestJson = "{\"email\":\"admin@admin.net\",\"password\":\"admin123\"}";
        webTestClient
                .post()
                .uri("/auth/token")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestJson), String.class)

                // Act
                .exchange()

                // Assert: response status
                .expectStatus()
                .isOk()

                // Arrange: admin token for further testing
                .returnResult(String.class)
                .consumeWith(response -> System.setProperty("adminToken", response.getResponseBody().blockLast()));
    }

    @DisplayName("/token POST Admin incorrect password")
    @Order(2)
    @Test
    public void loginAsAdminWithIncorrectCredentials() {

        // Arrange
        String requestJson = "{\"email\":\"admin@admin.net\",\"password\":\"admin12345\"}";
        webTestClient
                .post()
                .uri("/auth/token")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestJson), String.class)

                // Act
                .exchange()

                // Assert: response status
                .expectStatus()
                .isUnauthorized();
    }

    @DisplayName("/me GET No token")
    @Order(3)
    @Test
    public void authMeWithoutToken() {

        // Arrange
        webTestClient
                .get()
                .uri("/auth/me")

                // Act
                .exchange()

                // Assert: response status
                .expectStatus()
                .isUnauthorized();
    }

    @DisplayName("/me GET Valid token")
    @Order(4)
    @Test
    public void authMeWithToken() {

        // Arrange
        webTestClient
                .get()
                .uri("/auth/me")
                .headers(headers -> headers.setBearerAuth(System.getProperty("adminToken")))

                // Act
                .exchange()

                // Assert: response status
                .expectStatus()
                .isOk()

                // Assert: response body
                .expectBody()
                .jsonPath("$.email").isEqualTo("admin@admin.net")
                .jsonPath("$.role").isEqualTo("ADMIN");
    }

}
