package com.example.apinotemanager.repository;

import com.example.apinotemanager.model.Note;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {

    Page<Note> findAllByUser(ObjectId user, Pageable pageable);
}
