package endtoendtests;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@ActiveProfiles("test")
@RequiredArgsConstructor
public class UserAccountTest {

    private final RestTemplate restTemplate;

    @Test
    public void testLoginAsAdmin() {
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/auth/token", , String.class);

    }

}
