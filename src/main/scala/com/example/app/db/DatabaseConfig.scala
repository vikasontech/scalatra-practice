package com.example.app.db

import ch.rasc.bsoncodec.math.BigDecimalStringCodec
import ch.rasc.bsoncodec.time.LocalDateTimeDateCodec
import com.example.app.db.documents.Employee
import com.mongodb
import org.bson.codecs.configuration.CodecRegistry
import org.mongodb.scala.{MongoClientSettings, MongoCredential, ServerAddress}

import scala.collection.JavaConverters.seqAsJavaListConverter

object DatabaseConfig {
  import org.bson.codecs.configuration.CodecRegistries
  import org.bson.codecs.configuration.CodecRegistries._
  import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
  import org.mongodb.scala.bson.codecs.Macros._
  import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}


  val user: String = "root"
  val password: Array[Char] = "example".toCharArray
  val source: String = "admin"
  private val credential: MongoCredential = mongodb.MongoCredential.createCredential(user, source, password)

  private val javaCodecs = CodecRegistries.fromCodecs(
    new LocalDateTimeDateCodec(),
    new BigDecimalStringCodec())

  private val registry: CodecRegistry = CodecRegistries.fromProviders(classOf[Employee])

  val settings: MongoClientSettings = MongoClientSettings.builder()
    .applyToClusterSettings(b => b.hosts(List(new ServerAddress("localhost")).asJava))
    .credential(credential)
    .codecRegistry(fromRegistries(registry, javaCodecs, DEFAULT_CODEC_REGISTRY))
    .build()

  val client: MongoClient = MongoClient(settings)

  val database: MongoDatabase = client.getDatabase("test")

  val employees: MongoCollection[Employee] = database.getCollection("employee")

}
