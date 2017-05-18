package aSync

import org.scalajs.dom.svg.SVG
import org.scalajs.jquery.jQuery

import scala.scalajs.js.JSApp
import scalatags.JsDom.implicits._
import scalatags.JsDom.svgAttrs._
import scalatags.JsDom.svgTags._
import scalatags.JsDom.{svgAttrs, svgTags}

object GFlow extends JSApp {
  override def main() {
    val elements = jQuery(".gFlow").get()
    for (element <- elements) {
      element match {
        case svg: SVG =>
          svg.appendChild(
            defs(
              svgTags.filter(id := "glow", x := "-5000%", y := "-5000%", width := "10000%", height := "10000%")(
                feMorphology(in := "sourceGraphic", result := "dilated", operator := "dilate", radius := "3"),
                feGaussianBlur(in := "dilated", result := "coloredBlur", stdDeviation := "4"),
                feMerge(
                  feMergeNode(in := "coloredBlur"),
                  feMergeNode(in := "SourceGraphic")
                )
              ),
              pattern(id := "grid", width := "64", height := "64", patternUnits := "userSpaceOnUse")(
                circle(cx := "32", cy := "32", r := "1", fill := "DodgerBlue", svgAttrs.filter := "url(#glow)")
              )
            ).render
          )
          svg.appendChild(
            rect(width := "100%", height := "100%", fill := "url(#grid)").render
          )
      }
    }
  }
}