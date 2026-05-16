package co.clean_architecture.model.traceability.gateways;

import co.clean_architecture.model.traceability.Traceability;
import co.clean_architecture.model.traceability.response.EmployeeAverageResponse;

import java.util.List;

public interface TraceabilityRepository {

    void save(Traceability traceability);

    List<EmployeeAverageResponse> getAverageTimeEmployeeByRestaurantId(Long restaurantId);
}
