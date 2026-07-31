package fraud_detection.controller;

import fraud_detection.entity.Rule;
import fraud_detection.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @PostMapping
    public Rule saveRule(@RequestBody Rule rule) {
        return ruleService.saveRule(rule);
    }

    @GetMapping
    public List<Rule> getAllRules() {
        return ruleService.getAllRules();
    }

    @GetMapping("/{id}")
    public Rule getRuleById(@PathVariable Long id) {
        return ruleService.getRuleById(id);
    }

    // Update Rule
    @PutMapping("/{id}")
    public Rule updateRule(@PathVariable Long id,
                           @RequestBody Rule rule) {

        return ruleService.updateRule(id, rule);
    }

    @DeleteMapping("/{id}")
    public String deleteRule(@PathVariable Long id) {
        ruleService.deleteRule(id);
        return "Rule deleted successfully";
    }

    @GetMapping("/search")
    public List<Rule> searchRules(@RequestParam String keyword) {
        return ruleService.searchRules(keyword);
    }
}