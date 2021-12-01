package com.example.apinotemanager.dto;

import com.example.apinotemanager.model.Note;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", imports = {ObjectId.class})
public interface NoteMapper {

    @Mapping(target = "id", expression = "java(note.getId().toHexString())")
    @Mapping(target = "user", expression = "java(note.getUser().toHexString())")
    NoteDto convertToDto(Note note);

    @Mapping(target = "id", expression = "java(new ObjectId(noteDTO.getId()))")
    @Mapping(target = "user", expression = "java(new ObjectId(noteDTO.getUser()))")
    Note convertToEntity(NoteDto noteDTO);

    Note convertToEntity(AddNoteDto noteDTO);

    @Mapping(target = "id", expression = "java(note.getId().toHexString())")
    List<NoteDto> convertToDtoList(List<Note> notes);

}