package co.clean_architecture.model.traceability.response;

import java.util.List;

public record EmployeeAverageResponse(Long employeeId, String avg, List<OrderAverageResponse>orders) {
}
