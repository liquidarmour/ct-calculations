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

package uk.gov.hmrc.ct.accounts.frs10x.abridged

import uk.gov.hmrc.ct.accounts.frs10x.abridged.calculations.TotalShareholdersFundsCalculator
import uk.gov.hmrc.ct.accounts.frs10x.abridged.validation.AssetsEqualToSharesValidator
import uk.gov.hmrc.ct.accounts.frs10x.retriever.Frs10xAccountsBoxRetriever
import uk.gov.hmrc.ct.box._

case class AC80(value: Option[Int]) extends CtBoxIdentifier(name = "Total Shareholders Funds (current PoA)")
  with CtOptionalInteger with AssetsEqualToSharesValidator {

  override def validate(boxRetriever: Frs10xAccountsBoxRetriever): Set[CtValidation] = {
    validateAssetsEqualToShares("AC80", boxRetriever.retrieveAC68())
  }
}

object AC80 extends Calculated[AC80, Frs10xAccountsBoxRetriever] with TotalShareholdersFundsCalculator {

  override def calculate(boxRetriever: Frs10xAccountsBoxRetriever): AC80 = {
    import boxRetriever._
    calculateCurrentTotalShareholdersFunds(retrieveAC70(), retrieveAC76(), retrieveAC74())
  }
}
