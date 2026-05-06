package co.clean_architecture.api.dish;

import co.clean_architecture.api.config.SecurityUtil;
import co.clean_architecture.api.dish.mapper.DishResponseMapper;
import co.clean_architecture.api.dish.mapper.RegisterDishRequestMapper;
import co.clean_architecture.api.dish.mapper.UpdateDishRequestMapper;
import co.clean_architecture.api.dish.request.RegisterDishRequest;
import co.clean_architecture.api.dish.request.UpdateDishRequest;
import co.clean_architecture.api.dish.response.DishResponse;
import co.clean_architecture.model.dish.CategoryEnum;
import co.clean_architecture.model.dish.criteria.DishCriteria;
import co.clean_architecture.usecase.dish.ListDishesUseCase;
import co.clean_architecture.usecase.dish.RegisterDishUseCase;
import co.clean_architecture.usecase.dish.UpdateDishUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dish")
public class DishRest {

    private final RegisterDishUseCase registerDishUseCase;
    private final UpdateDishUseCase updateDishUseCase;
    private final ListDishesUseCase listDishesUseCase;

    @PostMapping
    public ResponseEntity<DishResponse> register(@RequestBody RegisterDishRequest request) {

        Long ownerId = SecurityUtil.getCurrentUserId();

        return ResponseEntity.status(HttpStatus.CREATED).body(
                DishResponseMapper.toResponse(
                        registerDishUseCase.execute(
                                RegisterDishRequestMapper.toCommand(request),
                                ownerId
                        )
                )
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody UpdateDishRequest request) {
        updateDishUseCase.execute(UpdateDishRequestMapper.toCommand(request), id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<DishResponse>> list(
            @RequestParam Long restaurantId,
            @RequestParam(required = false) List<CategoryEnum> categories,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset
    ) {

        DishCriteria criteria = DishCriteria.of(
                restaurantId,
                categories,
                limit,
                offset
        );

        return ResponseEntity.ok(
                listDishesUseCase.execute(criteria)
                        .stream()
                        .map(DishResponseMapper::toResponse)
                        .toList()
        );
    }
}
