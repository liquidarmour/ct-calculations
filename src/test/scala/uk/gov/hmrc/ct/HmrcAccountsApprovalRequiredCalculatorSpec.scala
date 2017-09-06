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

package uk.gov.hmrc.ct

import org.mockito.Matchers._
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{Matchers, WordSpec}
import uk.gov.hmrc.ct.accounts.{MockAccountsRetriever, MockFrs10xAccountsRetriever}
import uk.gov.hmrc.ct.accounts.frs10x.boxes.{AC8021, AC8023, ACQ8161}
import uk.gov.hmrc.ct.box.retriever.{BoxRetriever, FilingAttributesBoxValueRetriever}
import uk.gov.hmrc.ct.version.Return
import uk.gov.hmrc.ct.version.calculations.ReturnVersionsCalculator

class HmrcAccountsApprovalRequiredCalculatorSpec
  extends WordSpec
    with Matchers
    with MockitoSugar
    with MockFrs10xAccountsRetriever {

  import uk.gov.hmrc.ct.version.calculations.ReturnVersionsFixture._

  "HmrcAccountsApprovalRequiredCalculator" should {
    allCoHoOnlyReturnCombinations.zipWithIndex.foreach {
      case (returns, index) =>
        s"return false for CoHo Only filing return set $index" in {
          hmrcAccountsApprovalRequiredCalculatorForTest(returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(false)
        }
    }

    hmrcOnlyFRSSE2008ReturnCombinationsNoUploads.zipWithIndex.foreach {
      case (returns, index) =>
        s"return true for HMRC Only FRSSE2008 filing non upload return set $index" in {
          hmrcAccountsApprovalRequiredCalculatorForTest(returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(true)
        }
    }

    hmrcOnlyFRS10xReturnCombinationsNoUploads.zipWithIndex.foreach {
      case (returns, index) =>
        s"return true for HMRC Only FRS10x filing non upload return set $index" in {
          hmrcAccountsApprovalRequiredCalculatorForTest(returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(true)
        }
    }

    hmrcOnlyUploadAccountsReturnCombinations.zipWithIndex.foreach {
      case (returns, index) =>
        s"return false for HMRC Only filing upload return set $index" in {
          hmrcAccountsApprovalRequiredCalculatorForTest(returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(false)
        }
    }

    jointReturnCombinationsWithSameAccountsForCoHoAndHmrc.zipWithIndex.foreach {
      case (returns, index) =>
        s"return false for joint filing same accounts for both return set $index" in {
          hmrcAccountsApprovalRequiredCalculatorForTest(returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(false)
        }
    }

    allJointReturnCombinationsWithDifferentAccountsBetweenHmrcAndCoHo.zipWithIndex.foreach {
      case (returns, index) =>
        s"return false for joint filing different accounts for both return set $index" in {
          hmrcAccountsApprovalRequiredCalculatorForTest(returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(true)
        }
    }

    "return false for FRS102 and answered true to file P&l and directors report to CoHo" in {
      when(frs10xDirectorsBoxRetriever.ac8021()).thenReturn(AC8021(Some(true)))
      when(boxRetriever.acq8161()).thenReturn(ACQ8161(Some(true)))
      hmrcAccountsApprovalRequiredCalculatorForTest(jointStatutoryFRS102V3Returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(false)
      hmrcAccountsApprovalRequiredCalculatorForTest(jointAbridgedFRS102V3Returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(false)
    }

    "return true for FRS102 and answered true to file P&l and false directors report to CoHo" in {
      when(frs10xDirectorsBoxRetriever.ac8021()).thenReturn(AC8021(Some(false)))
      when(boxRetriever.acq8161()).thenReturn(ACQ8161(Some(true)))
      hmrcAccountsApprovalRequiredCalculatorForTest(jointStatutoryFRS102V3Returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(true)
      hmrcAccountsApprovalRequiredCalculatorForTest(jointAbridgedFRS102V3Returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(true)
    }

    "return true for FRS102 and answered false to file P&l and true directors report to CoHo" in {
      when(frs10xDirectorsBoxRetriever.ac8021()).thenReturn(AC8021(Some(true)))
      when(boxRetriever.acq8161()).thenReturn(ACQ8161(Some(false)))
      hmrcAccountsApprovalRequiredCalculatorForTest(jointStatutoryFRS102V3Returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(true)
      hmrcAccountsApprovalRequiredCalculatorForTest(jointAbridgedFRS102V3Returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(true)
    }

    "return true for FRS102 and answered false to file P&l and directors report to CoHo" in {
      when(frs10xDirectorsBoxRetriever.ac8021()).thenReturn(AC8021(Some(false)))
      when(boxRetriever.acq8161()).thenReturn(ACQ8161(Some(false)))
      hmrcAccountsApprovalRequiredCalculatorForTest(jointStatutoryFRS102V3Returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(true)
      hmrcAccountsApprovalRequiredCalculatorForTest(jointAbridgedFRS102V3Returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(true)
    }

    "return false for joint filing FRS105 and answered false to file directors report to HMRC and true to P&L" in {
      when(frs10xDirectorsBoxRetriever.ac8021()).thenReturn(AC8021(None))
      when(frs10xDirectorsBoxRetriever.ac8023()).thenReturn(AC8023(Some(false)))
      when(boxRetriever.acq8161()).thenReturn(ACQ8161(Some(true)))
      hmrcAccountsApprovalRequiredCalculatorForTest(jointMicroFRS105V3Returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(false)
    }

    "return true for FRS105 and answered false to file directors report to CoHo and true to P&L" in {
      when(frs10xDirectorsBoxRetriever.ac8021()).thenReturn(AC8021(Some(false)))
      when(frs10xDirectorsBoxRetriever.ac8023()).thenReturn(AC8023(Some(true)))
      when(boxRetriever.acq8161()).thenReturn(ACQ8161(Some(true)))
      hmrcAccountsApprovalRequiredCalculatorForTest(jointMicroFRS105V3Returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(true)
    }

    "return true for FRS105 and answered false to file P&L to CoHo and true to directors report" in {
      when(frs10xDirectorsBoxRetriever.ac8021()).thenReturn(AC8021(Some(true)))
      when(frs10xDirectorsBoxRetriever.ac8023()).thenReturn(AC8023(Some(true)))
      when(boxRetriever.acq8161()).thenReturn(ACQ8161(Some(false)))
      hmrcAccountsApprovalRequiredCalculatorForTest(jointMicroFRS105V3Returns).calculateApprovalRequired(boxRetriever) shouldBe HmrcAccountsApprovalRequired(true)
    }
  }

  def hmrcAccountsApprovalRequiredCalculatorForTest(returns: Set[Return]) = {
    val returnVersionCalculator = mock[ReturnVersionsCalculator]
    when(returnVersionCalculator.doCalculation(any[FilingAttributesBoxValueRetriever], any[Option[BoxRetriever]])).thenReturn(returns)
    HmrcAccountsApprovalRequiredCalculator(returnVersionCalculator)
  }
}
