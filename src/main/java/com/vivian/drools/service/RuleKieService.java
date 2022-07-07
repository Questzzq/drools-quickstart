package com.vivian.drools.service;

import com.vivian.drools.common.InvalidRuleException;
import com.vivian.drools.entity.User;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.KieContainer;
import org.springframework.stereotype.Service;

/**
 * @author Eric Tseng
 * @description RuleKieService
 * @since 2022/6/28 20:19
 */
@Service
public class RuleKieService {
    public static final String rule1 = "package user\n" +
            "\n" +
            "import com.vivian.drools.entity.User\n" +
            "\n" +
            "rule \"rule_1\"\n" +
            "    when\n" +
            "        $user:User(age < 100)\n" +
            "    then\n" +
            "        $user.setMatCode(\"MAT\" + $user.getAge());\n" +
            "        System.out.println(\"成功匹配到规则1,推荐产品\");\n" +
            "        System.out.println($user);\n" +
            "end";

    public Object updateSlotRule(String slotCode, String expression) throws InvalidRuleException {

        String rule = "package com.fei.drools\r\n";
        rule += "import com.vivian.drools.entity.User;\r\n";
        rule += "rule \"rule1\"\r\n";
        rule += "\twhen\r\n";
        rule += "User(age < 100)";
        rule += "\tthen\r\n";
        rule += "\t\tSystem.out.println( 1+\":\"+123 );\r\n";
        rule += "end\r\n";

        expression = rule;

        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write("src/main/resources/rules/" + slotCode + "rule.drl", expression);
        KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
        KieBaseModel kieBaseModel1 = kieModuleModel.newKieBaseModel("KBase");
        kieBaseModel1.newKieSessionModel("newSession");
        kieFileSystem.writeKModuleXML(kieModuleModel.toXML());
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        if (kieBuilder.getResults().getMessages(Message.Level.ERROR).size() > 0) {
            throw new InvalidRuleException("rule content invalid!!");
        }
        KieRepository kieRepository = kieServices.getRepository();
        KieContainer kieContainer = kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
        KieBase kBase = kieContainer.newKieBase("KBase", kieServices.newKieBaseConfiguration());
        KieCommands commands = kieServices.getCommands();

        User user = new User(20, null);
        commands.newInsert(user);
        commands.newFireAllRules();
        System.out.println(user.toString());
        return user;
    }

    public Object test01(String slotCode, String expression) throws InvalidRuleException {
        return updateSlotRule(slotCode, rule1);
    }
}