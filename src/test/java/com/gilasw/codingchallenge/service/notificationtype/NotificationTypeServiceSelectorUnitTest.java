package com.gilasw.codingchallenge.service.notificationtype;

import com.gilasw.codingchallenge.model.NotificationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static com.gilasw.codingchallenge.model.NotificationType.EMAIL;
import static com.gilasw.codingchallenge.model.NotificationType.PUSH;
import static com.gilasw.codingchallenge.model.NotificationType.SMS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class NotificationTypeServiceSelectorUnitTest {

    private NotificationTypeServiceSelector notificationTypeServiceSelector;

    @BeforeEach
    void setUp() {
        notificationTypeServiceSelector = new NotificationTypeServiceSelector();
        ReflectionTestUtils.setField(notificationTypeServiceSelector, "notificationTypeServiceMap", Map.of(
                EMAIL.name(), new EmailNotificationTypeService(),
                PUSH.name(), new PushNotificationTypeService(),
                SMS.name(), new SmsNotificationTypeService()
        ));
    }

    @Test
    void getExistingNotificationService() {
        assert notificationTypeServiceSelector.getNotificationService(EMAIL).getClass() == EmailNotificationTypeService.class;
        assert notificationTypeServiceSelector.getNotificationService(PUSH).getClass() == PushNotificationTypeService.class;
        assert notificationTypeServiceSelector.getNotificationService(SMS).getClass() == SmsNotificationTypeService.class;
    }

    @Test
    void getNonExistingNotificationService() {
        NotificationType NON_EXISTING_TYPE = mock(NotificationType.class);
        when(NON_EXISTING_TYPE.name()).thenReturn("NON_EXISTING_TYPE");
        assert notificationTypeServiceSelector.getNotificationService(NON_EXISTING_TYPE) == null;
    }
}