package com.yurak.yurii.compon.interfaces_classes;

import com.yurak.yurii.compon.base.BaseComponent;

public class EventComponent {
    public int eventSenderId;
    public BaseComponent eventReceiverComponent;
    public EventComponent(int sender, BaseComponent receiver) {
        eventSenderId = sender;
        eventReceiverComponent = receiver;
    }
}
