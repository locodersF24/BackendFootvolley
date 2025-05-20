package org.example.backendfootvolley.endtoend;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class TestApiClub {

    @Autowired
    private WebTestClient webTestClient;

    @DisplayName("POST No token")
    @Order(1)
    @Test
    public void registerWithoutToken() {

        // Arrange
        String requestJson = "{\"clubName\":\"CPH-FV\",\"established\":\"2025\",\"email\":\"jens@hansen.net\",\"firstName\":\"Jens\",\"lastName\":\"Hasen\",\"country\":\"DK\",\"cityName\":\"Copenhagen\"}";
        webTestClient
                .post()
                .uri("/api/clubs")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestJson), String.class)

                // Act
                .exchange()

                // Assert: response status
                .expectStatus()
                .isUnauthorized();
    }

    @DisplayName("POST Admin token")
    @Order(2)
    @Test
    public void registerWithAdminToken() {

        // Arrange
        String requestJson1 = "{\"password\":\"club123\",\"clubName\":\"CPH-FV\",\"established\":\"2025\",\"email\":\"jens@hansen.net\",\"firstName\":\"Jens\",\"lastName\":\"Hasen\",\"country\":\"DK\",\"cityName\":\"Copenhagen\"}";
        webTestClient
                .post()
                .uri("/api/clubs")
                .headers(headers -> headers.setBearerAuth(System.getProperty("adminToken")))
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestJson1), String.class)

                // Act
                .exchange()

                // Assert: response status
                .expectStatus()
                .isCreated();

        // Arrange: club user token for further testing
        String requestJson2 = "{\"email\":\"jens@hansen.net\",\"password\":\"club123\"}";
        webTestClient
                .post()
                .uri("/auth/token")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestJson2), String.class)
                .exchange()
                .returnResult(String.class)
                .consumeWith(response -> System.setProperty("clubToken", response.getResponseBody().blockLast()));
    }

    @DisplayName("POST Club token")
    @Order(3)
    @Test
    public void registerWithClubToken() {

        // Arrange
        String requestJson = "{\"password\":\"club123\",\"clubName\":\"CPH-FV\",\"established\":\"2025\",\"email\":\"jens@hansen.net\",\"firstName\":\"Jens\",\"lastName\":\"Hasen\",\"country\":\"DK\",\"cityName\":\"Copenhagen\"}";
        webTestClient
                .post()
                .uri("/api/clubs")
                .headers(headers -> headers.setBearerAuth(System.getProperty("clubToken")))
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestJson), String.class)

                // Act
                .exchange()

                // Assert: response status
                .expectStatus()
                .isForbidden();
    }

    @DisplayName("PUT No token")
    @Order(4)
    @Test
    public void editClubInfoWithoutToken() {

        // Arrange
        String requestJson = "{\"name\":\"CPH-Footvolley\",\"established\":\"2024\"}";
        webTestClient
                .put()
                .uri("/api/clubs")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestJson), String.class)

                // Act
                .exchange()

                // Assert: response status
                .expectStatus()
                .isUnauthorized();
    }

    @DisplayName("PUT Club token")
    @Order(5)
    @Test
    public void editClubInfoWithToken() {

        // Arrange
        String requestJson1 = "{\"name\":\"CPH-Footvolley\",\"established\":\"2024\"}";
        webTestClient
                .put()
                .uri("/api/clubs")
                .headers(headers -> headers.setBearerAuth(System.getProperty("clubToken")))
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestJson1), String.class)

                // Act
                .exchange()

                // Assert: response status
                .expectStatus()
                .isOk()

                // Assert: response body
                .expectBody()
                .jsonPath("$.name").isEqualTo("CPH-Footvolley")
                .jsonPath("$.established").isEqualTo("2024");
    }
}
