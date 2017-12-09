package by.bsuir.currency_project.aop;

import by.bsuir.currency_project.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogger {

    private Logger LOGGER = LoggerFactory.getLogger(AspectLogger.class);

    @AfterReturning(
            pointcut = "execution(* by.bsuir.currency_project.service.security.SecurityService.autologin(..))",
            returning = "user"
    )
    public void login(JoinPoint joinPoint, User user) {
        LOGGER.info(String.format("Auto login %s successfully!", user.getLogin()));
    }

}
