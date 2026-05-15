package co.clean_architecture.consumer.traceability;

import co.clean_architecture.consumer.traceability.mapper.RegisterTraceabilityRequestMapper;
import co.clean_architecture.consumer.traceability.request.RegisterTraceabilityRequest;
import co.clean_architecture.model.traceability.Traceability;
import co.clean_architecture.model.traceability.exception.InvalidTraceabilityException;
import co.clean_architecture.model.traceability.gateways.TraceabilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class TraceabilityConsumer implements TraceabilityRepository {

    private final RestClient traceabilityRestClient;
    public TraceabilityConsumer(@Qualifier("traceabilityRestClient") RestClient traceabilityRestClient) {
        this.traceabilityRestClient = traceabilityRestClient;
    }

    @Override
    public void save(Traceability traceability) {
        log.info("Traceability saved: {}", traceability);
        RegisterTraceabilityRequest request = RegisterTraceabilityRequestMapper.toRequest(traceability);
        if(request == null) {
            throw new InvalidTraceabilityException("Traceability request cannot be null");

        };
        try {
             traceabilityRestClient.post()
                    .uri("/api/v1/traceability")
                    .body(request)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            log.error("Error consuming user service: {}", e.getMessage());
            throw new InvalidTraceabilityException("Error consuming user service: " + e.getMessage());
        }
    }
}
