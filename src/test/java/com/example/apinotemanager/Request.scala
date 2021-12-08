package com.example.apinotemanager

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

import java.util.UUID

object Request {
  val userId = "61a7ddef1c0013176af3a174"

  def getNotes(): ChainBuilder = {
    exec(
      http("getNotes")
        .get("/v1/notes").queryParam("user", userId)
        .check(status.is(200))
    )
  }

  def postNote(): ChainBuilder = {
    exec(
      http("postNote")
        .post("/v1/notes")
        .body(StringBody(_ =>
          s"""
            |{
            |  "body": "test ${UUID.randomUUID}",
            |  "user": "$userId"
            |}""".stripMargin
        ))
        .header("content-type", "application/json")
        .check(status.is(201))
        .check(jsonPath("$.id").saveAs("noteId"))
    )
  }
  def getNoteById(): ChainBuilder = {
    exec(
      http("getNoteById")
        .get("/v1/notes/${noteId}")
        .check(status.is(200))
    )
  }
  def putNote(): ChainBuilder = {
    exec(
      http("putNote")
        .put("/v1/notes")
        .body(StringBody(
          """
             |{
             |  "id": "${noteId}",
             |  "body": "updated test",
             |  "user": "61a7ddef1c0013176af3a174"
             |}""".stripMargin
        ))
        .header("content-type", "application/json")
        .check(status.is(200))
    )
  }
  def deleteNote(): ChainBuilder = {
    exec(
      http("deleteNote")
        .delete("/v1/notes/${noteId}")
        .check(status.is(204))
    )
  }
}
