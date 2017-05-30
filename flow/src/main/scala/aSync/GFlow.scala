package aSync

import aSync.surface.Surface
import org.scalajs.dom.svg.SVG
import org.scalajs.jquery.jQuery

import scala.scalajs.js.JSApp

object GFlow extends JSApp {
  override def main() {
    val elements = jQuery(".gFlow").get()
    for (element <- elements) {
      element match {
        case svg: SVG =>
          val surface = new Surface
          svg.appendChild(surface.definitions())
          svg.appendChild(surface.render())
      }
    }
  }
}