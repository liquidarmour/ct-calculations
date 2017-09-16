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

package uk.gov.hmrc.ct.accounts.frs102

import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfterEach, Matchers, WordSpec}
import uk.gov.hmrc.ct.accounts.MockFrs10xAccountsRetriever
import uk.gov.hmrc.ct.accounts.frs10x.boxes.{AC8021, AC8023, ACQ8003}
import uk.gov.hmrc.ct.box.CtValidation
import uk.gov.hmrc.ct.{CompaniesHouseFiling, HMRCFiling, MicroEntityFiling}

class ACQ8003Spec
  extends WordSpec
    with Matchers
    with MockitoSugar
    with BeforeAndAfterEach
    with MockFrs10xAccountsRetriever {

  "AC8033 should" should {

    "validate successfully when no errors present" in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
      when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
      when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(true))
      when(boxRetriever.ac8021()).thenReturn(AC8021(Some(false)))
      when(boxRetriever.ac8023()).thenReturn(AC8023(Some(false)))
      val secretary = ACQ8003(Some(true))

      secretary.validate(boxRetriever) shouldBe empty
    }

    "validate as mandatory for CoHo only and want to file to CoHo" in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
      when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))
      when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(false))
      when(boxRetriever.ac8021()).thenReturn(AC8021(Some(true)))
      when(boxRetriever.ac8023()).thenReturn(AC8023(Some(false)))

      val secretary = ACQ8003(None)

      secretary.validate(boxRetriever) shouldBe Set(CtValidation(Some("ACQ8003"), "error.ACQ8003.required", None))
    }

    "validate as mandatory for Joint and want to file to CoHo" in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
      when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
      when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(false))
      when(boxRetriever.ac8021()).thenReturn(AC8021(Some(true)))
      when(boxRetriever.ac8023()).thenReturn(AC8023(Some(true)))

      val secretary = ACQ8003(None)

      secretary.validate(boxRetriever) shouldBe Set(CtValidation(Some("ACQ8003"), "error.ACQ8003.required", None))
    }

    "validate as mandatory for Joint and non micro entity filing" in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
      when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
      when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(false))
      when(boxRetriever.ac8021()).thenReturn(AC8021(Some(false)))
      when(boxRetriever.ac8023()).thenReturn(AC8023(Some(false)))

      val secretary = ACQ8003(None)

      secretary.validate(boxRetriever) shouldBe Set(CtValidation(Some("ACQ8003"), "error.ACQ8003.required", None))
    }

    "no validate if no directors report" in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
      when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))
      when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(false))

      when(boxRetriever.ac8021()).thenReturn(AC8021(None))
      when(boxRetriever.ac8023()).thenReturn(AC8023(Some(false)))

      val secretary = ACQ8003(None)

      secretary.validate(boxRetriever) shouldBe empty
    }
  }
}
