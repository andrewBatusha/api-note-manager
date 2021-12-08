import com.example.apinotemanager.Simulation.crudScen
import io.gatling.core.Predef._
import io.gatling.http.Predef.http

class LoadTest extends Simulation {
  val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmRyaWlAZ21haWwuY29tIiwiaWF0IjoxNjM4OTEzMDg0LCJleHAiOjE2Mzg5MjMwODR9.gELANF8iAgCQ-vtAOV0k3mweb9ye69pFMsJItpmvc_ggDRa5TXT56z5rbhAyQ9kY5FDkqREh3iNYvNg2X-nUCg"
  val httpConf = http.baseUrl("http://localhost:8086")
   .authorizationHeader(s"Bearer $token")
   .acceptHeader("application/json, */*")
   .acceptCharsetHeader("UTF-8")

  setUp(
    crudScen.inject(
     constantUsersPerSec(1) during 10
  ).protocols(httpConf)
  )
}