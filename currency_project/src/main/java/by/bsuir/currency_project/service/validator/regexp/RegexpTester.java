package by.bsuir.currency_project.service.validator.regexp;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public final class RegexpTester {

    public boolean test(String regexp, String str) {
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
