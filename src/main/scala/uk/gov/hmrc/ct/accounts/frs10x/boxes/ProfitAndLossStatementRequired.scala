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

package uk.gov.hmrc.ct.accounts.frs10x.boxes

import uk.gov.hmrc.ct.accounts.frs10x.retriever.{Frs10xAccountsBoxRetriever, Frs10xDormancyBoxRetriever}
import uk.gov.hmrc.ct.box.{Calculated, CtBoolean, CtBoxIdentifier}

case class ProfitAndLossStatementRequired(value: Boolean) extends CtBoxIdentifier(name = "Dormancy profit and loss statement required") with CtBoolean

object ProfitAndLossStatementRequired extends Calculated[ProfitAndLossStatementRequired, Frs10xAccountsBoxRetriever] {

  override def calculate(fieldValueRetriever: Frs10xAccountsBoxRetriever): ProfitAndLossStatementRequired = {
    val dormant = fieldValueRetriever.acq8999().orFalse
    val cohoOnly = !fieldValueRetriever.filingAttributesBoxValueRetriever.hmrcFiling().value

    val result = dormant && !(cohoOnly && !fieldValueRetriever.acq8161().orFalse)
    ProfitAndLossStatementRequired(result)
  }
}
