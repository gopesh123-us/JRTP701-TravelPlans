package live.learnjava.jrtp701miniproject.service;

import java.util.List;
import java.util.Map;

import live.learnjava.jrtp701miniproject.entity.TravelPlan;

public interface ITravelPlanManagementService {

	public String registerTravelPlan(TravelPlan plan); // save

	public Map<Integer, String> getTravelPlanCategories(); // select operation

	public List<TravelPlan> showAllTravelPlans(); // for select operation

	public TravelPlan showTravelPlanById(Integer planId); // for edit operation form launching to show existing record for editing.

	public String updateTravelPlan(TravelPlan plan); // for edit operation submission

	public String deleteTravelPlan(Integer planId); // hard deletion

	public String changeTravelPlanStatus(Integer planId, String satus); // soft deletion active, inactive

}
