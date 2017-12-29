/*
 * Copyright 2017 HM Revenue & Customs
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

package uk.gov.hmrc.ct.ct600a.v3

import uk.gov.hmrc.ct.box.{CtBoxIdentifier, CtOptionalInteger}
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever
import uk.gov.hmrc.ct.ct600.v3.calculations.LoansToParticipatorsCalculator
import uk.gov.hmrc.ct.ct600a.v3.retriever.CT600ABoxRetriever

case class A60Inverse(value: Option[Int]) extends CtBoxIdentifier(name = "A60Inverse - Loans made during the return period which have been released/written off more than 9 months after period end date where relief is NOT YET DUE")
with CtOptionalInteger

object A60Inverse extends LoansToParticipatorsCalculator {

  def calculate(fieldValueRetriever: CT600ABoxRetriever, computationsBoxRetriever: ComputationsBoxRetriever): A60Inverse = {
    calculateA60Inverse(computationsBoxRetriever.cp2(), fieldValueRetriever.loansToParticipators(), fieldValueRetriever.lpq07())
  }
}
