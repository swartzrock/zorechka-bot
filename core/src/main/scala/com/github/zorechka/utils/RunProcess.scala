package com.github.zorechka.utils

import java.io.IOException
import java.nio.file.Path

import scala.collection.mutable.ListBuffer
import scala.sys.process.{Process, ProcessLogger}

object RunProcess {
  def execCmd(command: List[String], workDir: Path, extraEnv: List[(String, String)] = List.empty): List[String] = {
    val lb = ListBuffer.empty[String]
    val log = new ProcessLogger {
      override def out(s: => String): Unit = {
        println(s)
        lb.append(s)
      }
      override def err(s: => String): Unit = {
        println(s)
        lb.append(s)
      }
      override def buffer[T](f: => T): T = f
    }

    Process(command, Some(workDir.toFile), extraEnv: _*).!(log)
    lb.result()
  }
}
