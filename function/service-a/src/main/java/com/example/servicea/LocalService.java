package com.example.servicea;

import org.springframework.stereotype.Service;

@Service
public class LocalService {
    
    @Tracing
    public void dummyFunction() {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
