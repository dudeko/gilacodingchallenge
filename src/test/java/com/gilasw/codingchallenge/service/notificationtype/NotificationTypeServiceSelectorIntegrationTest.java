package com.gilasw.codingchallenge.service.notificationtype;

import com.gilasw.codingchallenge.model.NotificationType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.gilasw.codingchallenge.model.NotificationType.EMAIL;
import static com.gilasw.codingchallenge.model.NotificationType.PUSH;
import static com.gilasw.codingchallenge.model.NotificationType.SMS;
import static org.mockito.Mockito.mock;


@SpringBootTest
class NotificationTypeServiceSelectorIntegrationTest {

    @Autowired
    private NotificationTypeServiceSelector notificationTypeServiceSelector;

    @Test
    void getExistingNotificationService() {
        assert notificationTypeServiceSelector.getNotificationService(EMAIL).getClass() == EmailNotificationTypeService.class;
        assert notificationTypeServiceSelector.getNotificationService(PUSH).getClass() == PushNotificationTypeService.class;
        assert notificationTypeServiceSelector.getNotificationService(SMS).getClass() == SmsNotificationTypeService.class;
    }

    @Test
    void getNonExistingNotificationService() {
        NotificationType NON_EXISTING_TYPE = mock(NotificationType.class);
        assert notificationTypeServiceSelector.getNotificationService(NON_EXISTING_TYPE) == null;
    }
}