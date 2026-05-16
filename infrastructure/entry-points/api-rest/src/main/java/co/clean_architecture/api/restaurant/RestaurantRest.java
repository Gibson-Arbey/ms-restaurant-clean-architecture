package co.clean_architecture.api.restaurant;

import co.clean_architecture.api.restaurant.mapper.RegisterRestaurantRequestMapper;
import co.clean_architecture.api.restaurant.mapper.RestaurantResponseMapper;
import co.clean_architecture.api.restaurant.request.RegisterRestaurantRequest;
import co.clean_architecture.api.restaurant.response.RestaurantResponse;
import co.clean_architecture.model.restaurant.criteria.RestaurantCriteria;
import co.clean_architecture.model.restaurant.projection.RestaurantSummaryProjection;
import co.clean_architecture.model.traceability.response.EmployeeAverageResponse;
import co.clean_architecture.usecase.restaurant.GetEmployeeOrderAverageDurationUseCase;
import co.clean_architecture.usecase.restaurant.ListRestaurantsUseCase;
import co.clean_architecture.usecase.restaurant.RegisterRestaurantUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurant")
public class RestaurantRest {

    private final RegisterRestaurantUseCase registerRestaurantUseCase;
    private final ListRestaurantsUseCase listRestaurantsUseCase;
    private final GetEmployeeOrderAverageDurationUseCase getEmployeeOrderAverageDurationUseCase;

    @PostMapping
    public ResponseEntity<RestaurantResponse> registerRestaurant(@RequestBody RegisterRestaurantRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                RestaurantResponseMapper.toResponse(
                        registerRestaurantUseCase.execute(
                                RegisterRestaurantRequestMapper.toCommand(request)
                        )
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<RestaurantSummaryProjection>> getAllRestaurants(
            @RequestParam("limit") Integer limit,
            @RequestParam("offset") Integer offset,
            @RequestParam("search") String search
    ) {
        RestaurantCriteria criteria = RestaurantCriteria.of(search, limit, offset);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(listRestaurantsUseCase.execute(criteria)
        );
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<EmployeeAverageResponse>> getEmployeeAverage(@PathVariable Long restaurantId) {
        return ResponseEntity
                .status(HttpStatus.OK).
                body(getEmployeeOrderAverageDurationUseCase.execute(restaurantId));
    }
}
