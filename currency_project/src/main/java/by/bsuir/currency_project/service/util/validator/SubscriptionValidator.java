package by.bsuir.currency_project.service.util.validator;

import by.bsuir.currency_project.service.exception.BadRequestException;
import by.bsuir.currency_project.service.util.validator.regexp.RegExp;
import by.bsuir.currency_project.service.util.validator.regexp.RegExpTester;

public final class SubscriptionValidator {

    public static void validateSubscription(String type) {
        if (type == null || type.isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExpTester.test(RegExp.SUBSCRIPTION_REG_EXP, type) ) {
            throw new BadRequestException();
        }
    }

}
