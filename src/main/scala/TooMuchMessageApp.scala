import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

class SlowActor extends Actor {
  val log = Logging(context.system, this)
  def receive = {
    case message: String => {
      log.info(message)
      Thread.sleep(1000)
    }
    case _ =>
  }
}

object TooMuchMessageApp extends App {
  val system = ActorSystem("tooMuchMessageApp")
  val slowActor = system.actorOf(Props[SlowActor], "slowActor")
  while(true) {
    slowActor ! LocalDateTime.now().toString
  }
}
