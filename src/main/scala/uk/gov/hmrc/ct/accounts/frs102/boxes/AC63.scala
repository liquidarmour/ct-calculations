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

package uk.gov.hmrc.ct.accounts.frs102.boxes

import uk.gov.hmrc.ct.accounts.frs102.calculations.TotalAssetsLessCurrentLiabilitiesCalculator
import uk.gov.hmrc.ct.accounts.frs102.retriever.Frs102AccountsBoxRetriever
import uk.gov.hmrc.ct.box.{Calculated, CtBoxIdentifier, CtOptionalInteger}

case class AC63(value: Option[Int]) extends CtBoxIdentifier(name = "Total assets less current liabilities (previous PoA)") with CtOptionalInteger with Calculated

object AC63 extends TotalAssetsLessCurrentLiabilitiesCalculator {

  def calculate(boxRetriever: Frs102AccountsBoxRetriever): AC63 = {
    import boxRetriever._
    calculatePreviousTotalAssetsLessCurrentLiabilities(ac61(), ac49())
  }
}
