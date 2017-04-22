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
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfterEach, Matchers, WordSpec}
import uk.gov.hmrc.ct.HMRCFiling
import uk.gov.hmrc.ct.accounts.frs10x.retriever.{Frs10xDirectorsBoxRetriever, Frs10xDormancyBoxRetriever, Frs10xFilingQuestionsBoxRetriever}
import uk.gov.hmrc.ct.box.retriever.FilingAttributesBoxValueRetriever

class ProfitAndLossStatementRequiredSpec extends WordSpec with Matchers with MockitoSugar with BeforeAndAfterEach {

  trait MockRetriever extends Frs10xDormancyBoxRetriever

  val mockBoxRetriever: MockRetriever = mock[MockRetriever] (RETURNS_SMART_NULLS)
  val filingAttributesBoxRetriever = mock[FilingAttributesBoxValueRetriever]
  val frs10xFilingQuestionsBoxRetriever = mock[Frs10xFilingQuestionsBoxRetriever]
  val frs10xDirectorsBoxRetriever = mock[Frs10xDirectorsBoxRetriever]

  "NotTradedStatementRequired should" should {

    when(mockBoxRetriever.frs10xFilingQuestionsBoxRetriever).thenReturn(frs10xFilingQuestionsBoxRetriever)
    when(mockBoxRetriever.frs10xDirectorsBoxRetriever).thenReturn(frs10xDirectorsBoxRetriever)
    when(mockBoxRetriever.filingAttributesBoxRetriever).thenReturn(filingAttributesBoxRetriever)

    "be false if not dormant" in {
      when(mockBoxRetriever.acq8999()).thenReturn(ACQ8999(None))
      when(mockBoxRetriever.filingAttributesBoxRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
      ProfitAndLossStatementRequired.calculate(mockBoxRetriever) shouldBe ProfitAndLossStatementRequired(false)

      when(mockBoxRetriever.acq8999()).thenReturn(ACQ8999(Some(false)))
      ProfitAndLossStatementRequired.calculate(mockBoxRetriever) shouldBe ProfitAndLossStatementRequired(false)
    }

    "be false if dormant, coho only and not filing P&L to coho" in {
      when(mockBoxRetriever.acq8999()).thenReturn(ACQ8999(Some(true)))

      when(mockBoxRetriever.filingAttributesBoxRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))
      when(mockBoxRetriever.frs10xFilingQuestionsBoxRetriever.acq8161()).thenReturn(ACQ8161(Some(false)))
      ProfitAndLossStatementRequired.calculate(mockBoxRetriever) shouldBe ProfitAndLossStatementRequired(false)
    }

    "be true if dormant and 1) not coho only or 2) filing P&L to coho" in {
      when(mockBoxRetriever.acq8999()).thenReturn(ACQ8999(Some(true)))

      when(mockBoxRetriever.filingAttributesBoxRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
      when(mockBoxRetriever.frs10xFilingQuestionsBoxRetriever.acq8161()).thenReturn(ACQ8161(Some(false)))
      ProfitAndLossStatementRequired.calculate(mockBoxRetriever) shouldBe ProfitAndLossStatementRequired(true)

      when(mockBoxRetriever.filingAttributesBoxRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))
      when(mockBoxRetriever.frs10xFilingQuestionsBoxRetriever.acq8161()).thenReturn(ACQ8161(Some(true)))
      ProfitAndLossStatementRequired.calculate(mockBoxRetriever) shouldBe ProfitAndLossStatementRequired(true)
    }
  }
}
