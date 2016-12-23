package com.demo

import akka.actor.ActorSystem
import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.{Http, server}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Source}
import com.demo.config.Config

/**
  * Created by nattapornc on 23/12/2559.
  */
object Boot extends App {
  StartApp
}

object StartApp {

  import Config.Api._

  implicit val system: ActorSystem = ActorSystem("websocket-consumer-system")
  implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()

  val serverUrl = bindHost
  val port = bindPort


  Http().bindAndHandle(MyApi.myRoute, serverUrl, port)
  println(s"start http://$serverUrl:$port ...")
}

object MyApi {
  val greeter: Flow[Message, Message, Any] = Flow[Message].mapConcat {
    case tm: TextMessage => TextMessage(Source.single("Hello") ++ tm.textStream ++ Source.single("!")) :: Nil

  }
  val myRoute: server.Route = (get | options) {
    path("ping") {
      complete("ok")
    }
  } ~ path("greeter") {
    handleWebSocketMessages(greeter)
  }
}
