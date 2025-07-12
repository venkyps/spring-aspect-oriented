package com.example.logging.service;

import com.example.logging.dto.*;
import org.springframework.stereotype.Service;

@Service
public class LogFacade {
    public void logInvocation(InvocationLogDto dto) {
        System.out.println("Invocation: " + dto);
    }

    public void logReturnValue(ReturnLogDto dto) {
        System.out.println("Return Value: " + dto);
    }

    public void logThrownException(ExceptionLogDto dto) {
        System.out.println("Exception: " + dto);
    }

    public void logEntitySave(EntitySaveLogDto dto) {
        System.out.println("Entity Saved: " + dto);
    }

    public void logEntitySavingTime(EntitySaveTimeLogDto dto) {
        System.out.println("Entity Save Time: " + dto);
    }
}
