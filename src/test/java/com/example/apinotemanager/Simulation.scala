package com.example.apinotemanager

import com.example.apinotemanager.Request.{deleteNote, getNoteById, getNotes, postNote, putNote}
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder

object Simulation {
  def crudScen: ScenarioBuilder = scenario("crud scenario")
    .exec(getNotes())
    .exec(postNote())
    .exec(getNoteById())
    .exec(putNote())
    .exec(deleteNote())
}
