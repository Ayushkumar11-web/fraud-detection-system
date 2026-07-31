package fraud_detection.controller;

import fraud_detection.entity.FraudAlert;
import fraud_detection.service.FraudAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fraud-alerts")
public class FraudAlertController {

    @Autowired
    private FraudAlertService fraudAlertService;

    @GetMapping
    public List<FraudAlert> getAllAlerts() {
        return fraudAlertService.getAllAlerts();
    }

    @GetMapping("/{id}")
    public FraudAlert getAlertById(@PathVariable Long id) {
        return fraudAlertService.getAlertById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteAlert(@PathVariable Long id) {
        fraudAlertService.deleteAlert(id);
        return "Fraud Alert deleted successfully";
    }
}