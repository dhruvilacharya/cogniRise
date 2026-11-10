package cognirise.backend;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import cognirise.backend.dto.ChatMessageRequest;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
@ActiveProfiles("test")
class BackendApplicationTests {

	@Autowired
	private Validator validator;

	@Test
	void contextLoads() {
	}

	@Test
	void rejectsBlankChatMessages() {
		assertFalse(validator.validate(new ChatMessageRequest(" ")).isEmpty());
	}

}
