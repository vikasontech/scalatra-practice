package com.example.app.db.actor

import akka.actor.Actor
import com.example.app.db.documents.Employee
import com.example.app.db.repos.EmployeeRepo


class EmployeeDbActor extends Actor{

  override def receive: Receive = {
    case FindAll =>
      sender ! EmployeeRepo.findAll()

    case Save(emp) =>
      sender ! EmployeeRepo.save(emp)

    case Update(id, emp) =>
      sender ! EmployeeRepo.update(id, emp)

    case Delete(id) =>
      sender ! EmployeeRepo.delete(id)

  }
}

sealed trait EmployeeDbAction
object FindAll extends EmployeeDbAction
case class Save(employee:Employee) extends EmployeeDbAction
case class Update(id: String, employee:Employee) extends EmployeeDbAction
case class Delete(id: String) extends EmployeeDbAction
