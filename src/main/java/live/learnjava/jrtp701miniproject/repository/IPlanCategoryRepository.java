package live.learnjava.jrtp701miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import live.learnjava.jrtp701miniproject.entity.PlanCategory;

public interface IPlanCategoryRepository extends JpaRepository<PlanCategory, Integer> {

}
