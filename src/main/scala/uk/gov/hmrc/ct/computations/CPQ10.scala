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

package uk.gov.hmrc.ct.computations

import uk.gov.hmrc.ct.box._
import uk.gov.hmrc.ct.computations.Validators.AllowancesQuestionsValidation
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever

case class CPQ10(value: Option[Boolean]) extends CtBoxIdentifier(name = "Did you have machinery or plant?")
  with CtOptionalBoolean
  with Input
  with ValidatableBox[ComputationsBoxRetriever]
  with AllowancesQuestionsValidation {

  def validate(boxRetriever: ComputationsBoxRetriever): Set[CtValidation] = validateAgainstCPQ7(boxRetriever, "CPQ10", value)

//  {
//    (boxRetriever.retrieveCPQ7(), value) match {
//      case (CPQ7(Some(true)), None) => validateBooleanAsMandatory(this.getClass.getSimpleName, this)
//      case (CPQ7(Some(false)), Some(true)) => Set(CtValidation(Some(this.getClass.getSimpleName), s"error.${this.getClass.getSimpleName}.notClaiming.required"))
//      case _ => Set.empty
//    }
//  }
}
