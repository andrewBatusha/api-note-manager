import com.example.apinotemanager.Simulation.crudScen
import io.gatling.core.Predef._
import io.gatling.http.Predef.http

class LoadTest extends Simulation {
  val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmRyaWlAZ21haWwuY29tIiwiaWF0IjoxNjM4OTU5NDgzLCJleHAiOjE2MzkwNDY0ODN9.EpjdEsKVRmW8C8g_KmRee2y1kkwTOZwwBC1JpTlwdy7NNrarfvUUK0i8CS0dS65460T0IfiVFmOGOU9nNJVBFw"
  val httpConf = http.baseUrl("https://api-note-manager.herokuapp.com/")
   .authorizationHeader(s"Bearer $token")
   .acceptHeader("application/json, */*")
   .acceptCharsetHeader("UTF-8")

  setUp(
    crudScen.inject(
     atOnceUsers(10000)
  ).protocols(httpConf)
  )
}