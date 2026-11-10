package cognirise.backend.services.ai;

import java.util.List;

import cognirise.backend.dto.CitationDto;

public record RetrievedContext(
        List<CitationDto> citations,
        String contextText) {
}
