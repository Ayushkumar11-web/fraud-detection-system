package fraud_detection.controller;

import fraud_detection.dto.ReportDTO;
import fraud_detection.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public ReportDTO getReport() {

        return reportService.getReport();

    }

}