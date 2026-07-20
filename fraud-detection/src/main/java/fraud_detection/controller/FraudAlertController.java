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

    @PostMapping
    public FraudAlert saveAlert(@RequestBody FraudAlert alert) {
        return fraudAlertService.saveAlert(alert);
    }

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
        return "Alert deleted successfully";
    }
}