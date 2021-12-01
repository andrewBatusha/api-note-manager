package com.example.apinotemanager.service;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

interface BasicService<T extends Serializable, I extends Serializable> {

    T getById(I id);

    T save(T object);

    T delete(T object);
}
