package com.demo.config

import com.typesafe.config.ConfigFactory

/**
  * Created by nattapornc on 23/12/2559.
  */
object Config {
  private val config = ConfigFactory.load()

  object Api {
    val bindHost = apiConfig.getString("host")
    val bindPort = apiConfig.getInt("port")
    private val apiConfig = config.getConfig("api")
  }

}
