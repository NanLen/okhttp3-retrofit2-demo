package com.android.http.bus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by liyanan on 16/5/4.
 */
public class RxBus {
    private static RxBus instance;

    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    // If multiple threads are going to emit events to this
    // then it must be made thread-safe like this instead
    private final Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());

    public void send(Object o) {
        bus.onNext(o);
    }

    public Observable<Object> toObserverable() {
        return bus;
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }
}
