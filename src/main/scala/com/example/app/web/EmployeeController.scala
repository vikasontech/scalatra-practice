package com.example.app.web

import java.time.LocalDate

import akka.actor.{ActorRef, ActorSystem}
import akka.util.Timeout
import com.example.app.db.actor.{Delete, FindAll, Save, Update}
import com.example.app.db.config.serializer.DateSerializer
import com.example.app.db.documents.Employee
import org.json4s.JsonAST.{JField, JObject, JString, JValue}
import org.json4s.{CustomSerializer, DefaultFormats, Formats}
import org.scalatra.{FutureSupport, ScalatraServlet}
import org.scalatra.json._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.language.postfixOps


class EmployeeController(system: ActorSystem, ref: ActorRef)
  extends ScalatraServlet with FutureSupport with JacksonJsonSupport {
  implicit protected def jsonFormats: Formats = DefaultFormats + new DateSerializer

  before() {
    contentType = "application/json"
  }

  override protected implicit def executor: ExecutionContext = system.dispatcher

  implicit val timeout: Timeout = 60 seconds

  get("/hello") {
    akka.pattern.ask(ref, FindAll)
  }

  post("/save") {
    val employee = parsedBody.extract[Employee]
    akka.pattern.ask(ref, Save(employee))
  }

  put("/save/:id") {
    val id = params("id")
    val employee = parsedBody.extract[Employee]
    akka.pattern.ask(ref, Update(id, employee))
  }

  delete("/delete/:id") {
    val id = params("id")
    akka.pattern.ask(ref, Delete(id))
  }
}
