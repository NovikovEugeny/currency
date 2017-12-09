package by.bsuir.currency_project.service.util;

import org.springframework.mail.SimpleMailMessage;

public final class MailMessenger {

    private final static SimpleMailMessage MESSENGER = new SimpleMailMessage();

    private MailMessenger() {
    }

    public static SimpleMailMessage getMessenger(String email, String content) {
        MESSENGER.setSubject("FinanceToday daily notification");
        MESSENGER.setText(content);
        MESSENGER.setTo(email);
        return MESSENGER;
    }
}
