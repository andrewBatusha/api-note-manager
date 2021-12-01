package com.example.apinotemanager.service;

import com.example.apinotemanager.model.Note;
import com.example.apinotemanager.repository.NoteRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note getById(String id) {
        return noteRepository.findById(id).get();
    }

    @Override
    public List<Note> getAll(ObjectId user, Pageable pageable) {
        return noteRepository.findAllByUser(user,pageable).getContent();
    }

    @Override
    public Note save(Note object) {
        return noteRepository.save(object);
    }

    @Override
    public Note delete(Note object) {
        noteRepository.delete(object);
        return object;
    }
}
