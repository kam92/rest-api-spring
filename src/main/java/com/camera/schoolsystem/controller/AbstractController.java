package com.camera.schoolsystem.controller;

import com.camera.schoolsystem.service.ServiceInterface;
import org.springframework.http.ResponseEntity;

public abstract class AbstractController<T extends ServiceInterface> {

    //metodo global para onde vai passar as operações de todos os services
    protected ResponseEntity<byte[]> doOperation() {
        return null;
    }

}
