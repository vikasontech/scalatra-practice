package com.example.app.db.repos

import java.time.LocalDate
import java.util.UUID

import com.example.app.db.DatabaseConfig
import com.example.app.db.config.JsonUtils
import com.example.app.db.documents.Employee
import org.mongodb.scala.{Completed, MongoCollection}
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.model.FindOneAndUpdateOptions
import org.mongodb.scala.model.Updates.{combine, set}
import org.mongodb.scala.result.DeleteResult

import scala.concurrent.Future

object EmployeeRepo extends JsonUtils {
  private val employees: MongoCollection[Employee] = DatabaseConfig.employees

  def findAll(): Future[Seq[Employee]] = {
    employees.find().toFuture()
  }

  def save(emp: Employee): Future[Completed] = {
    val empDoc = emp.copy(_id = UUID.randomUUID().toString)
    employees.insertOne(empDoc).toFuture()
  }

  def update(id: String, emp: Employee): Future[Employee] = {
    employees
      .findOneAndUpdate(equal("_id", id),
        setBsonValue(emp),
        FindOneAndUpdateOptions().upsert(false)).toFuture()
  }

  def delete(id: String): Future[DeleteResult] = {
    employees.deleteOne(equal("_id", id)).toFuture()
  }

  private def setBsonValue(emp: Employee): org.mongodb.scala.bson.conversions.Bson = {
    combine(
      set("name", emp.name),
      set("dateOfBirth", emp.dateOfBirth)
    )
  }
}
