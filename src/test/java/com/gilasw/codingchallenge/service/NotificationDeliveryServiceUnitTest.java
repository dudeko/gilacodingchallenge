package com.gilasw.codingchallenge.service;

import com.gilasw.codingchallenge.dto.NotificationDTO;
import com.gilasw.codingchallenge.model.User;
import com.gilasw.codingchallenge.service.notificationtype.EmailNotificationTypeService;
import com.gilasw.codingchallenge.service.notificationtype.IBaseNotificationTypeService;
import com.gilasw.codingchallenge.service.notificationtype.NotificationTypeServiceSelector;
import com.gilasw.codingchallenge.service.notificationtype.PushNotificationTypeService;
import com.gilasw.codingchallenge.service.notificationtype.SmsNotificationTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static com.gilasw.codingchallenge.model.NotificationType.EMAIL;
import static com.gilasw.codingchallenge.model.NotificationType.PUSH;
import static com.gilasw.codingchallenge.model.NotificationType.SMS;
import static java.util.List.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NotificationDeliveryServiceUnitTest {

    private NotificationDeliveryService notificationDeliveryService;
    private NotificationTypeServiceSelector notificationTypeServiceSelector;
    private UserService userService;

    @BeforeEach
    void setUp() {
        notificationDeliveryService = new NotificationDeliveryService();
        notificationTypeServiceSelector = mock(NotificationTypeServiceSelector.class);
        userService = mock(UserService.class);
        ReflectionTestUtils.setField(notificationDeliveryService, "notificationTypeServiceSelector", notificationTypeServiceSelector);
        ReflectionTestUtils.setField(notificationDeliveryService, "userService", userService);
    }

    @Test
    void sendSmsToOneUserSuccessfully() {
        //given
        List<User> userList = of(
                User.create().name("Donna").channels(of(SMS))
        );
        when(userService.findWithCategory(anyString())).thenReturn(userList);
        SmsNotificationTypeService smsNotificationTypeService = mock(SmsNotificationTypeService.class);
        when(notificationTypeServiceSelector.getNotificationService(SMS)).thenReturn(smsNotificationTypeService);

        //when
        notificationDeliveryService.send(anyString(), "Team A scored a goal!");

        //then
        verify(smsNotificationTypeService, times(1)).sendAndLogExceptions(any(NotificationDTO.class));
    }

    @Test
    void sendMultipleNotificationsToMultipleUsersSuccessfully() {
        //given
        List<User> userList = of(
                User.create().name("Freddie").channels(of(EMAIL, PUSH, SMS)),
                User.create().name("Clint").channels(of(EMAIL, SMS)),
                User.create().name("Francis").channels(of(SMS))
        );
        when(userService.findWithCategory(anyString())).thenReturn(userList);
        EmailNotificationTypeService emailNotificationTypeService = mock(EmailNotificationTypeService.class);
        PushNotificationTypeService pushNotificationTypeService = mock(PushNotificationTypeService.class);
        SmsNotificationTypeService smsNotificationTypeService = mock(SmsNotificationTypeService.class);
        when(notificationTypeServiceSelector.getNotificationService(EMAIL)).thenReturn(emailNotificationTypeService);
        when(notificationTypeServiceSelector.getNotificationService(PUSH)).thenReturn(pushNotificationTypeService);
        when(notificationTypeServiceSelector.getNotificationService(SMS)).thenReturn(smsNotificationTypeService);

        //when
        notificationDeliveryService.send(anyString(), "Team A scored a goal!");

        //then
        verify(emailNotificationTypeService, times(2)).sendAndLogExceptions(any(NotificationDTO.class));
        verify(pushNotificationTypeService, times(1)).sendAndLogExceptions(any(NotificationDTO.class));
        verify(smsNotificationTypeService, times(3)).sendAndLogExceptions(any(NotificationDTO.class));
    }

    @Test
    void categoryWithoutUsersSubscribedShouldNotSendAnyNotification() {
        //given
        List<User> emptyUserList = of();
        when(userService.findWithCategory(anyString())).thenReturn(emptyUserList);
        IBaseNotificationTypeService notificationTypeService = mock(IBaseNotificationTypeService.class);

        //when
        notificationDeliveryService.send(anyString(), "AFC Richmond won the match!");

        //then
        verify(notificationTypeService, times(0)).sendAndLogExceptions(any(NotificationDTO.class));
    }

    @Test
    void exceptionShouldNotStopNextNotificationsFromBeingSent() {
        //given
        List<User> userList = of(
                User.create().name("Sadie").channels(of(SMS, PUSH)),
                User.create().name("Duke").channels(of(SMS))
        );
        when(userService.findWithCategory(anyString())).thenReturn(userList);
        PushNotificationTypeService pushNotificationTypeService = mock(PushNotificationTypeService.class);
        SmsNotificationTypeService smsNotificationTypeService = mock(SmsNotificationTypeService.class);
        when(notificationTypeServiceSelector.getNotificationService(PUSH)).thenReturn(pushNotificationTypeService);
        when(notificationTypeServiceSelector.getNotificationService(SMS)).thenReturn(smsNotificationTypeService);
        doCallRealMethod().when(pushNotificationTypeService).sendAndLogExceptions(any(NotificationDTO.class));
        doThrow(Exception.class).when(pushNotificationTypeService).send(any(NotificationDTO.class));
        ReflectionTestUtils.setField(pushNotificationTypeService, "logger", mock(Logger.class));

        //when
        notificationDeliveryService.send(anyString(), "Skynet stocks are rising!");

        //then
        verify(smsNotificationTypeService, times(2)).sendAndLogExceptions(any(NotificationDTO.class));
        verify(pushNotificationTypeService, times(1)).sendAndLogExceptions(any(NotificationDTO.class));
    }
}