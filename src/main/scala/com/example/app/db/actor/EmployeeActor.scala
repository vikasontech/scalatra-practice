package com.example.app.db.actor

import akka.actor.{Actor, ActorRef}
import akka.util.Timeout

import scala.concurrent.duration._
import scala.language.postfixOps

class EmployeeActor(val employeeDbActor: ActorRef) extends Actor {
  implicit val timeout: Timeout = 60 seconds

  override def receive: Receive = {
    case FindAll =>
      sender ! akka.pattern.ask(employeeDbActor, FindAll)

    case Save(emp) =>
      akka.pattern.ask(employeeDbActor, Save(emp))
      sender ! "Save Successful!"

    case Update(id, emp) =>
      akka.pattern.ask(employeeDbActor, Update(id, emp))
      sender ! "Save Successful!"

    case Delete(id) =>
      akka.pattern.ask(employeeDbActor, Delete(id))
      sender ! "Delete Successful"

    case _ => println("What do you want to say ?")
  }
}