package com.zyl.configuration;

import org.springframework.context.ApplicationEvent;

/**
 * @author zyl
 */
public class DelayEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public DelayEvent(Runnable source) {
        super(source);
    }

    public Runnable getDelaySource() {
        return (Runnable) source;
    }
}
