package cogniRise.backend.services.ai;

import java.util.List;

import cogniRise.backend.dto.CitationDto;

public record RetrievedContext(
        List<CitationDto> citations,
        String contextText) {
}
