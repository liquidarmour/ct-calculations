/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ct.box

trait StringValidation {

  val validNonForeignLessRestrictiveCharacters = "[A-Za-z0-9 ,\\.\\(\\)/&'\\-\"!%\\*_\\+:@<>\\?=;]*"
  val validNonForeignMoreRestrictiveCharacters = "[A-Za-z0-9 ,\\.\\(\\)/&'\\-\"]*"

  def validateStringAsMandatory(boxId: String, box: CtOptionalString): Set[CtValidation] = {
    box.value match {
      case None => Set(CtValidation(Some(boxId), s"error.$boxId.required"))
      case Some(x) if x.isEmpty => Set(CtValidation(Some(boxId), s"error.$boxId.required"))
      case _ => Set()
    }
  }

  def validateStringAsBlank(boxId: String, box: CtOptionalString): Set[CtValidation] = {
    box.value match {
      case None => Set()
      case Some(x) if x.isEmpty => Set()
      case _ => Set(CtValidation(Some(boxId), s"error.$boxId.nonBlankValue"))
    }
  }

  def validateOptionalStringByRegex(boxId: String, box: CtOptionalString, regex: String): Set[CtValidation] = {
    box.value match {
      case Some(x) if x.nonEmpty => {
        if (x.matches(regex)) Set()
        else Set(CtValidation(Some(boxId), s"error.$boxId.regexFailure"))
      }
      case _ => Set()
    }
  }

  def validateStringByRegex(boxId: String, box: String, regex: String): Set[CtValidation] = {
    if (box.isEmpty || box.matches(regex)) Set()
    else Set(CtValidation(Some(boxId), s"error.$boxId.regexFailure"))
  }

  def validateOptionalStringByLength(boxId: String, box: CtOptionalString, min: Int, max: Int): Set[CtValidation] = {
    box.value match {
      case Some(x) => validateStringByLength(boxId, x, min, max)
      case _ => Set()
    }
  }

  def validateStringByLength(boxId: String, box: CtString, min:Int, max:Int): Set[CtValidation] = {
    validateStringByLength(boxId, box.value, min, max)
  }

  def validateStringByLength(boxId: String, value: String, min: Int, max: Int): Set[CtValidation] = {
    if (value.nonEmpty && value.size < min || value.size > max) {
      Set(CtValidation(Some(boxId), s"error.$boxId.text.sizeRange", Some(Seq(min.toString, max.toString))))
    } else Set()
  }

}
