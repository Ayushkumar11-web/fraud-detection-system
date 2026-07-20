package fraud_detection.service;

import fraud_detection.entity.FraudAlert;
import fraud_detection.repository.FraudAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraudAlertService {

    @Autowired
    private FraudAlertRepository fraudAlertRepository;

    public FraudAlert saveAlert(FraudAlert alert) {
        return fraudAlertRepository.save(alert);
    }

    public List<FraudAlert> getAllAlerts() {
        return fraudAlertRepository.findAll();
    }

    public FraudAlert getAlertById(Long id) {
        return fraudAlertRepository.findById(id).orElse(null);
    }

    public void deleteAlert(Long id) {
        fraudAlertRepository.deleteById(id);
    }
}