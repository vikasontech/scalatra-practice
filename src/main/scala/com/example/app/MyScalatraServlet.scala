package com.example.app

import org.scalatra._

class MyScalatraServlet extends ScalatraServlet {
  val uri = "/api/v1/articles"

  get(uri + "/:id") {
//    {params{"id"}}
    Ok(s"data found for ${params{"id"}}")
//    NotFound(s"data found for ${params{"id"}}")
  }
  post(uri) {
    ???
  }
  put(uri + "/:id") {
    ???
  }
  delete(uri + "/:id") {
    ???
  }

}
