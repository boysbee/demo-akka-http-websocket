package com.demo.actor

import akka.actor.Props
import akka.stream.actor.ActorPublisher
import akka.stream.actor.ActorPublisherMessage.Request

/**
  * Created by nattapornc on 23/12/2559.
  */
object EventPublisherActor {
  def props = Props[EventPublisherActor]
}

class EventPublisherActor extends ActorPublisher[String] {
  var messageCache: List[String] = Nil
  context.system.eventStream.subscribe(self, classOf[String])

  override def receive: Receive = {
    case Request(n) =>
      while (isActive && totalDemand > 0 && messageCache.nonEmpty) {
        val (head :: tail) = messageCache
        onNext(head)
        messageCache = tail
      }
    case event: String =>
      if (isActive && totalDemand > 0)
        onNext(event)
      else
        messageCache :+= event
  }
}