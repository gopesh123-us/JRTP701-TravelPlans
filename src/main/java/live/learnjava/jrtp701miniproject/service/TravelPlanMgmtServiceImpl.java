package live.learnjava.jrtp701miniproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import live.learnjava.jrtp701miniproject.entity.PlanCategory;
import live.learnjava.jrtp701miniproject.entity.TravelPlan;
import live.learnjava.jrtp701miniproject.repository.IPlanCategoryRepository;
import live.learnjava.jrtp701miniproject.repository.ITravelPlanRepository;

@Service
public class TravelPlanMgmtServiceImpl implements ITravelPlanManagementService {

	@Autowired
	private ITravelPlanRepository travelPlanRepo;

	@Autowired
	private IPlanCategoryRepository planCategoryRepo;

	@Override
	public String registerTravelPlan(TravelPlan plan) {
		// Save the object
		TravelPlan saved = travelPlanRepo.save(plan);
		/*
		 * if (saved.getPlanId() != null) { return
		 * "Travel Plan is Saved with ID value: " + saved.getPlanId();
		 * 
		 * } else return "Problem in saving the TravelPlan";
		 */
		return saved.getPlanId() != null ? "Travel Plan is saved with Id value:: " + saved.getPlanId()
				: "Problem in saving Tour plan";

	}

	@Override
	public Map<Integer, String> getTravelPlanCategories() {
		// get all travel plan categories
		List<PlanCategory> list = planCategoryRepo.findAll();
		// Map<Integer, String> categoriesMap = new HahMap<Integer, String>();
		// list.stream().map(category -> categoriesMap.put(category.getCategoryId(),
		// category.getCategoryName())).collect(Collectors.toMap());
		Map<Integer, String> categoriesMap = new HashMap<Integer, String>();
		list.forEach(category -> categoriesMap.put(category.getCategoryId(), category.getCategoryName()));
		return categoriesMap;

	}

	@Override
	public List<TravelPlan> showAllTravelPlans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TravelPlan showTravelPlanById(Integer planId) {
		return travelPlanRepo.findById(planId)
				.orElseThrow(() -> new IllegalArgumentException("TravelPlan is not found"));
	}

	@Override
	public String updateTravelPlan(TravelPlan plan) {
		// update object
		Optional<TravelPlan> opt = travelPlanRepo.findById(plan.getPlanId());
		if (opt.isPresent()) {
			travelPlanRepo.save(plan);
			return plan.getPlanId() + "is updated";
		} else
			return plan.getPlanId() + " Travel Plan is not found";
	}

	@Override
	public String deleteTravelPlan(Integer planId) {
		// load if available delete is other not
		Optional<TravelPlan> opt = travelPlanRepo.findById(planId);
		if (opt.isPresent()) {
			travelPlanRepo.deleteById(planId);
			return planId + " Travel Plan is deleted";
		} else
			return planId + " Travel Plan is not found";

	}

	@Override
	public String changeTravelPlanStatus(Integer planId, String status) {
		Optional<TravelPlan> opt = travelPlanRepo.findById(planId);
		if (opt.isPresent()) {
			TravelPlan plan = opt.get();
			plan.setActiveSW(status);
			travelPlanRepo.save(plan);
			travelPlanRepo.deleteById(planId);
			return "Travel Plan status is changed";
		} else
			return planId + " Travel Plan is not found";
	}

}
