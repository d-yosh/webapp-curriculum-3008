import akka.actor.{Actor, ActorSystem, Props}

class Reply extends Actor {
  def receive = {
    case str if str == "aaa" =>
      println(str)
      sender() ! "bbb"
    case str if str == "bbb" =>
      println(str)
      sender() ! "aaa"
  }
}

object ReplyActorApp extends App {
  val system = ActorSystem("reply")
  val reply1 = system.actorOf(Props[Reply], "reply1")
  val reply2 = system.actorOf(Props[Reply], "reply2")
  reply1.tell("aaa", reply2)
}
