package aSync

import org.scalajs.jquery.jQuery

import scala.scalajs.js.JSApp

object GFlow extends JSApp {
  override def main() {
    val elements = jQuery(".gFlow")
    println(elements)
    /*for (element <- elements) {
      element match {
        case svg: SVG =>
          println("Initializing svg with id " + svg.id)
        case e =>
          println("The usage of " + e.getClass + " is not permitted.")
      }
    }*/
  }
}
