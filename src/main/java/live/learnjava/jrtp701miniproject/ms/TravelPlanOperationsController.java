package live.learnjava.jrtp701miniproject.ms;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import live.learnjava.jrtp701miniproject.entity.TravelPlan;
import live.learnjava.jrtp701miniproject.service.ITravelPlanManagementService;

@RestController
@RequestMapping("/travel/plan") // global path
public class TravelPlanOperationsController {

	@Autowired
	ITravelPlanManagementService planService;

	@GetMapping("/categories")
	public ResponseEntity<?> showTravelPlanCategories() {
		// invoke service class methods
		try {
			Map<Integer, String> mapCategories = planService.getTravelPlanCategories();
			return new ResponseEntity<Map<Integer, String>>(mapCategories, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<String> saveTourPlan(@RequestBody TravelPlan plan) {
		// use service.
		try {
			String msg = planService.registerTravelPlan(plan);
			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// showAllTravelPlans
	@GetMapping("/all")
	public ResponseEntity<?> getAllTravelPlans() {
		// use service
		try {
			List<TravelPlan> list = planService.showAllTravelPlans();
			return new ResponseEntity<List<TravelPlan>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/find/{planid}")
	public ResponseEntity<?> showTravelPlanById(@PathVariable Integer planId) {
		try {
			TravelPlan plan = planService.showTravelPlanById(planId);
			return new ResponseEntity<TravelPlan>(plan, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateTravelPlan(@RequestBody TravelPlan plan) {
		// use service
		try {
			String message = planService.updateTravelPlan(plan);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{planid}")
	public ResponseEntity<?> removeTravelPlanByPlanId(@PathVariable Integer planId) {
		// use service
		try {
			String message = planService.deleteTravelPlan(planId);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<?> removeTravelPlanById(@PathVariable Integer planId, @PathVariable String status) {
		try {
			String message = planService.changeTravelPlanStatus(planId, status);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

} //class
