/*
 * Copyright 2015 HM Revenue & Customs
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

package uk.gov.hmrc.ct.ct600.v3

import uk.gov.hmrc.ct.box.{Calculated, CtBoxIdentifier, CtInteger}
import uk.gov.hmrc.ct.ct600.v3.calculations.CorporationTaxCalculator
import uk.gov.hmrc.ct.ct600.v3.retriever.CT600BoxRetriever

case class B300(value: Int) extends CtBoxIdentifier("Profits chargeable to Corporation Tax") with CtInteger

object B300 extends CorporationTaxCalculator with Calculated[B300, CT600BoxRetriever] {

  override def calculate(fieldValueRetriever: CT600BoxRetriever): B300 =
    calculateProfitsChargeableToCorporationTax(fieldValueRetriever.retrieveB235(), fieldValueRetriever.retrieveB275())
}