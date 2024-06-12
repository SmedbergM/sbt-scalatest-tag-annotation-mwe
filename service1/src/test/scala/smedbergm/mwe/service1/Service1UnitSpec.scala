package smedbergm.mwe.service1

import org.scalatest.flatspec.AnyFlatSpecLike
import org.scalatest.matchers.should.Matchers

class Service1UnitSpec extends AnyFlatSpecLike with Matchers {
  "A Service1" should "concatenate strings" in {
    "foo" + "bar" shouldEqual "foobar"
  }
}
