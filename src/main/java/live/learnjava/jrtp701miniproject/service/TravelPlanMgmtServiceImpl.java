package live.learnjava.jrtp701miniproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import live.learnjava.jrtp701miniproject.config.AppConfigProperties;
import live.learnjava.jrtp701miniproject.constants.TravelPlanConstants;
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

	private Map<String, String> messages;

	@Autowired
	public TravelPlanMgmtServiceImpl(AppConfigProperties props) {
		System.out.println("Properties: " + props.getMessages());
		this.messages = props.getMessages();
	}

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
		return saved.getPlanId() != null ? messages.get(TravelPlanConstants.SAVE_SUCCESS) + " " + saved.getPlanId()
				: messages.get(TravelPlanConstants.SAVE_FAILURE);

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
		return travelPlanRepo.findAll();
	}

	@Override
	public TravelPlan showTravelPlanById(Integer planId) {
		return travelPlanRepo.findById(planId)
				.orElseThrow(() -> new IllegalArgumentException(messages.get(TravelPlanConstants.FIND_BY_ID_FAILURE)));
	}

	@Override
	public String updateTravelPlan(TravelPlan plan) {
		// update object
		Optional<TravelPlan> opt = travelPlanRepo.findById(plan.getPlanId());
		if (opt.isPresent()) {
			travelPlanRepo.save(plan);
			return plan.getPlanId() + messages.get(TravelPlanConstants.UPDATE_SUCCESS);
		} else
			return plan.getPlanId() + " " + messages.get(TravelPlanConstants.UPDATE_FAILURE);
	}

	@Override
	public String deleteTravelPlan(Integer planId) {
		// load if available delete is other not
		Optional<TravelPlan> opt = travelPlanRepo.findById(planId);
		if (opt.isPresent()) {
			travelPlanRepo.deleteById(planId);
			return planId + " " + messages.get(TravelPlanConstants.DELETE_SUCCESS);
		} else
			return planId + " " + messages.get(TravelPlanConstants.DELETE_FAILURE);

	}

	@Override
	public String changeTravelPlanStatus(Integer planId, String status) {
		Optional<TravelPlan> opt = travelPlanRepo.findById(planId);
		if (opt.isPresent()) {
			TravelPlan plan = opt.get();
			plan.setActiveSW(status);
			travelPlanRepo.save(plan);
			travelPlanRepo.deleteById(planId);
			return messages.get(TravelPlanConstants.STATUS_CHANGE_SUCCESS);
		} else
			return planId + " " + messages.get(TravelPlanConstants.STATUS_CHANGE_FAILURE);
	}

}
