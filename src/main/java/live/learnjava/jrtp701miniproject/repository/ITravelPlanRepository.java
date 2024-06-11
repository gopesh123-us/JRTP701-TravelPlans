package live.learnjava.jrtp701miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import live.learnjava.jrtp701miniproject.entity.TravelPlan;

public interface ITravelPlanRepository extends JpaRepository<TravelPlan, Integer> {

}
