package fraud_detection.service;

import fraud_detection.entity.Rule;
import fraud_detection.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    // ==========================
    // Add Rule
    // ==========================
    public Rule saveRule(Rule rule) {
        return ruleRepository.save(rule);
    }

    // ==========================
    // Get All Rules
    // ==========================
    public List<Rule> getAllRules() {
        return ruleRepository.findAll();
    }

    // ==========================
    // Search Rules
    // ==========================
    public List<Rule> searchRules(String keyword) {
        return ruleRepository.findByRuleNameContainingIgnoreCase(keyword);
    }

    // ==========================
    // Get Rule By ID
    // ==========================
    public Rule getRuleById(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rule not found"));
    }

    // ==========================
    // Update Rule
    // ==========================
    public Rule updateRule(Long id, Rule updatedRule) {

        Rule rule = ruleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rule not found"));

        rule.setRuleName(updatedRule.getRuleName());
        rule.setThresholdAmount(updatedRule.getThresholdAmount());
        rule.setSeverity(updatedRule.getSeverity());
        rule.setActive(updatedRule.getActive());

        return ruleRepository.save(rule);
    }

    // ==========================
    // Delete Rule
    // ==========================
    public void deleteRule(Long id) {

        Rule rule = ruleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rule not found"));

        ruleRepository.delete(rule);
    }

}