package by.bsuir.currency_project.service.util.validator;

import by.bsuir.currency_project.service.exception.BadRequestException;
import by.bsuir.currency_project.service.util.validator.regexp.RegExp;
import by.bsuir.currency_project.service.util.validator.regexp.RegExpTester;

public final class AnalyticsValidator {

    public static void validateOrder(String type) {
        if (type == null || type.isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExpTester.test(RegExp.ANALYTICS_REG_EXP, type) ) {
            throw new BadRequestException();
        }
    }

}
