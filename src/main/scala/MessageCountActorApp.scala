import akka.actor.{Actor, ActorSystem, Props}

class Counter extends Actor {
  var count = 0
  def receive = {
    case _ =>
      println(count)
      count = count + 1
  }
}

object MessageCountActorApp extends App {
  val system = ActorSystem("messageCountActor")
  val counter = system.actorOf(Props[Counter], "counter")

  for (i <- 1 to 10000) {
    counter ! "up"
  }

  Thread.currentThread().join()
}
