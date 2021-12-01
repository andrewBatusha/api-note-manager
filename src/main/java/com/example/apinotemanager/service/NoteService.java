package com.example.apinotemanager.service;

import com.example.apinotemanager.model.Note;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoteService extends BasicService<Note, String>{
    List<Note> getAll(ObjectId user, Pageable pageable);

}