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

package uk.gov.hmrc.ct.ct600.v3.validators

import uk.gov.hmrc.ct.box.{CtString, CtValidation, StringValidation}
import uk.gov.hmrc.ct.ct600.v3.retriever.RepaymentsBoxRetriever

trait BankDetailsValidation extends StringValidation {

  val SortCodeValidChars = """^[0-9]{6}$"""
  val AccountNumberValidChars = """^[0-9]{8}$"""

  def validateAllFilledOrEmptyStringsForBankDetails(boxRetriever: RepaymentsBoxRetriever, boxId: String)(decryptor: (CtString) => String): Set[CtValidation] = {
    val bankDetailsBoxGroup: Set[String] = Set(
      decryptor(boxRetriever.retrieveB920()),
      decryptor(boxRetriever.retrieveB925()),
      decryptor(boxRetriever.retrieveB930()),
      decryptor(boxRetriever.retrieveB935())
    )
    validateAllFilledOrEmptyStrings(boxId, bankDetailsBoxGroup)
  }

  def validateAllFilledOrEmptyStrings(boxId: String, allBoxes: Set[String]): Set[CtValidation] = {
    val allEmpty = allBoxes.count(_.isEmpty) == allBoxes.size
    val allNonEmpty = allBoxes.count(_.nonEmpty) == allBoxes.size

    if(allEmpty || allNonEmpty) {
      Set()
    } else {
      Set(CtValidation(Some(boxId), s"error.$boxId.allornone"))
    }
  }

}
