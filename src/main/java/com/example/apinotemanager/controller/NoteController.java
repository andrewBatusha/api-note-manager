package com.example.apinotemanager.controller;

import com.example.apinotemanager.dto.AddNoteDto;
import com.example.apinotemanager.dto.NoteDto;
import com.example.apinotemanager.dto.NoteMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.example.apinotemanager.model.Note;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.apinotemanager.service.NoteService;

import java.util.List;

@RestController
@Api(tags = "Note API")
@RequiredArgsConstructor
@RequestMapping("/v1/notes")
public class NoteController {

    private final NoteService noteService;
    private final NoteMapper noteMapper;

    @GetMapping("/{id}")
    @ApiOperation("Get note info by id")
    public ResponseEntity<NoteDto> get(@PathVariable("id") String id) {
        Note note = noteService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(noteMapper.convertToDto(note));
    }


    @GetMapping
    @ApiOperation(value = "Get the list of all user's notes")
    public ResponseEntity<List<NoteDto>> getPage(@PageableDefault(sort = {"id"}) Pageable pageable, @RequestParam String user) {
        return ResponseEntity.ok().body(noteMapper.convertToDtoList(noteService.getAll(new ObjectId(user), pageable)));
    }


    @PostMapping
    @ApiOperation(value = "Create new note")
    public ResponseEntity<NoteDto> save(@RequestBody AddNoteDto addNoteDto) {
        Note note = noteService.save(noteMapper.convertToEntity(addNoteDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(noteMapper.convertToDto(note));

    }

    @PutMapping
    @ApiOperation(value = "Update existing note")
    public ResponseEntity<NoteDto> update(@RequestBody NoteDto noteDto) {
            Note note = noteService.save(noteMapper.convertToEntity(noteDto));
            return ResponseEntity.status(HttpStatus.OK).body(noteMapper.convertToDto(note));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete note by id")
    public ResponseEntity delete(@PathVariable("id") String id) {
        Note note = noteService.getById(id);
        noteService.delete(note);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
