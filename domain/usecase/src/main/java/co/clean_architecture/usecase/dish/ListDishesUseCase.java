package co.clean_architecture.usecase.dish;

import co.clean_architecture.model.dish.Dish;
import co.clean_architecture.model.dish.criteria.DishCriteria;
import co.clean_architecture.model.dish.gateways.DishRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListDishesUseCase {

    private final DishRepository dishRepository;

    public List<Dish> execute(DishCriteria criteria) {
        return dishRepository.findAllByCriteria(criteria);
    }
}
