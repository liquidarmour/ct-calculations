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

import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfterEach, Matchers, WordSpec}
import uk.gov.hmrc.ct.HMRCFiling
import uk.gov.hmrc.ct.accounts.MockFrs10xAccountsRetriever

class ProfitAndLossStatementRequiredSpec
  extends WordSpec
    with Matchers
    with MockitoSugar
    with BeforeAndAfterEach
    with MockFrs10xAccountsRetriever {

  "NotTradedStatementRequired should" should {

    "be false if not dormant" in {
      when(boxRetriever.acq8999()).thenReturn(ACQ8999(None))
      when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
      ProfitAndLossStatementRequired.calculate(boxRetriever) shouldBe ProfitAndLossStatementRequired(false)

      when(boxRetriever.acq8999()).thenReturn(ACQ8999(Some(false)))
      ProfitAndLossStatementRequired.calculate(boxRetriever) shouldBe ProfitAndLossStatementRequired(false)
    }

    "be false if dormant, coho only and not filing P&L to coho" in {
      when(boxRetriever.acq8999()).thenReturn(ACQ8999(Some(true)))

      when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))
      when(boxRetriever.acq8161()).thenReturn(ACQ8161(Some(false)))
      ProfitAndLossStatementRequired.calculate(boxRetriever) shouldBe ProfitAndLossStatementRequired(false)
    }

    "be true if dormant and 1) not coho only or 2) filing P&L to coho" in {
      when(boxRetriever.acq8999()).thenReturn(ACQ8999(Some(true)))

      when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
      when(boxRetriever.acq8161()).thenReturn(ACQ8161(Some(false)))
      ProfitAndLossStatementRequired.calculate(boxRetriever) shouldBe ProfitAndLossStatementRequired(true)

      when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))
      when(boxRetriever.acq8161()).thenReturn(ACQ8161(Some(true)))
      ProfitAndLossStatementRequired.calculate(boxRetriever) shouldBe ProfitAndLossStatementRequired(true)
    }
  }
}
