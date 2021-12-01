package com.example.apinotemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString(exclude={"user"})
@JsonIgnoreProperties({"user"})
@Document(collection="notes")
public class Note implements Serializable {

    @Id
    private ObjectId id;

    private String body;

    private ObjectId user;
}
