package co.clean_architecture.model.traceability.gateways;

import co.clean_architecture.model.traceability.Traceability;

public interface TraceabilityRepository {

    void save(Traceability traceability);
}
