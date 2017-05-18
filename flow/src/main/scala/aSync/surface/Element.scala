package aSync.surface

import org.scalajs.dom.raw.SVGElement

trait Element {
  def render(): SVGElement
}
