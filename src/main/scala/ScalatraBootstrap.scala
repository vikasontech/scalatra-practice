import akka.actor.{ActorRef, ActorSystem, Props}
import com.example.app._
import com.example.app.db.actor.{EmployeeDbActor, EmployeeActor}
import com.example.app.web.EmployeeController
import javax.servlet.ServletContext
import org.scalatra._

import scala.language.postfixOps

class ScalatraBootstrap extends LifeCycle {

  val system: ActorSystem = ActorSystem()

  private val employeeDbActorRef: ActorRef = system.actorOf(Props(new EmployeeDbActor()))
  private val sampleActorRef: ActorRef = system.actorOf(Props(new EmployeeActor(employeeDbActorRef)))

  override def init(context: ServletContext) {

    context.mount(new MyScalatraServlet, "/*")
    context.mount(new EmployeeController(system, sampleActorRef), "/sample")
  }

  override def destroy(context: ServletContext): Unit = {
    system.terminate()
    super.destroy(context)
  }
}