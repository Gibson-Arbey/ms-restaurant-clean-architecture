package co.clean_architecture.usecase.restaurant;

import co.clean_architecture.model.traceability.gateways.TraceabilityRepository;
import co.clean_architecture.model.traceability.response.EmployeeAverageResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetEmployeeOrderAverageDurationUseCase {

    private final TraceabilityRepository traceabilityRepository;

    public List<EmployeeAverageResponse> execute(Long restaurantId) {
        return traceabilityRepository.getAverageTimeEmployeeByRestaurantId(restaurantId);
    }

}