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

package uk.gov.hmrc.ct.accounts.frs105.boxes

import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfterEach, Matchers, WordSpec}
import uk.gov.hmrc.ct.accounts.frs102.boxes.{AC13 => FullAC13}
import uk.gov.hmrc.ct.accounts.frs10x.boxes.ACQ8161
import uk.gov.hmrc.ct.accounts.{AC12, MockFrs105AccountsRetriever, frs105}
import uk.gov.hmrc.ct.box.CtValidation
import uk.gov.hmrc.ct.{CompaniesHouseFiling, HMRCFiling}

class ACQ8161Spec
  extends WordSpec
    with MockitoSugar
    with Matchers
    with BeforeAndAfterEach
    with MockFrs105AccountsRetriever {

  override protected def beforeEach(): Unit = {
    super.beforeEach()

    when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
    when(boxRetriever.ac12()).thenReturn(AC12(Some(10)))
    when(boxRetriever.ac13()).thenReturn(AC13(Some(10)))
    when(boxRetriever.ac405()).thenReturn(AC405(Some(10)))
    when(boxRetriever.ac406()).thenReturn(AC406(Some(10)))
    when(boxRetriever.ac410()).thenReturn(AC410(Some(10)))
    when(boxRetriever.ac411()).thenReturn(AC411(Some(10)))
    when(boxRetriever.ac415()).thenReturn(AC415(Some(10)))
    when(boxRetriever.ac416()).thenReturn(AC416(Some(10)))
    when(boxRetriever.ac420()).thenReturn(AC420(Some(10)))
    when(boxRetriever.ac421()).thenReturn(AC421(Some(10)))
    when(boxRetriever.ac425()).thenReturn(AC425(Some(10)))
    when(boxRetriever.ac426()).thenReturn(AC426(Some(10)))
    when(boxRetriever.ac34()).thenReturn(frs105.boxes.AC34(Some(10)))
    when(boxRetriever.ac35()).thenReturn(frs105.boxes.AC35(Some(10)))
  }

  "ACQ8161 validate" should {
    "return errors when filing is for CoHo and ACQ8161 is empty" in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))

      ACQ8161(None).validate(boxRetriever) shouldBe Set(CtValidation(Some("ACQ8161"), "error.ACQ8161.required"))
    }

    "not return errors when filing is not CoHo and ACQ8161 is empty" in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(false))

      ACQ8161(None).validate(boxRetriever) shouldBe Set()
    }

    "not return errors when filing is for CoHo and ACQ8161 is true" in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))

      ACQ8161(Some(true)).validate(boxRetriever) shouldBe Set()
    }

    "not return errors when filing is Joint and ACQ8161 is true" in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))

      ACQ8161(Some(true)).validate(boxRetriever) shouldBe Set()
    }

    "not return errors when filing is CoHo only and ACQ8161 is false (frs105 variant) " in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
      when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))
      when(boxRetriever.ac12()).thenReturn(AC12(None))
      when(boxRetriever.ac13()).thenReturn(AC13(None))
      when(boxRetriever.ac405()).thenReturn(AC405(None))
      when(boxRetriever.ac406()).thenReturn(AC406(None))
      when(boxRetriever.ac410()).thenReturn(AC410(None))
      when(boxRetriever.ac411()).thenReturn(AC411(None))
      when(boxRetriever.ac415()).thenReturn(AC415(None))
      when(boxRetriever.ac416()).thenReturn(AC416(None))
      when(boxRetriever.ac420()).thenReturn(AC420(None))
      when(boxRetriever.ac421()).thenReturn(AC421(None))
      when(boxRetriever.ac425()).thenReturn(AC425(None))
      when(boxRetriever.ac426()).thenReturn(AC426(None))
      when(boxRetriever.ac34()).thenReturn(frs105.boxes.AC34(None))
      when(boxRetriever.ac35()).thenReturn(frs105.boxes.AC35(None))

      ACQ8161(Some(false)).validate(boxRetriever) shouldBe Set()
    }

    "not return errors when filing is Joint and ACQ8161 is false" in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
      when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))

      ACQ8161(Some(false)).validate(boxRetriever) shouldBe Set()
    }

    "return 'cannot exist' error when filing is for FRS105 for CoHo Only and ACQ8161 is false" in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
      when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))

      ACQ8161(Some(false)).validate(boxRetriever) shouldBe Set(CtValidation(None, "error.profitAndLoss.cannot.exist"))
    }

    "return 'cannot exist' error when filing is for FRS105 for CoHo Only and ACQ8161 is false with AC12 and AC13 only populated." in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
      when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))
      when(boxRetriever.ac405()).thenReturn(AC405(None))
      when(boxRetriever.ac406()).thenReturn(AC406(None))
      when(boxRetriever.ac410()).thenReturn(AC410(None))
      when(boxRetriever.ac411()).thenReturn(AC411(None))
      when(boxRetriever.ac415()).thenReturn(AC415(None))
      when(boxRetriever.ac416()).thenReturn(AC416(None))
      when(boxRetriever.ac420()).thenReturn(AC420(None))
      when(boxRetriever.ac421()).thenReturn(AC421(None))
      when(boxRetriever.ac425()).thenReturn(AC425(None))
      when(boxRetriever.ac426()).thenReturn(AC426(None))
      when(boxRetriever.ac34()).thenReturn(frs105.boxes.AC34(None))
      when(boxRetriever.ac35()).thenReturn(frs105.boxes.AC35(None))

      ACQ8161(Some(false)).validate(boxRetriever) shouldBe Set(CtValidation(None, "error.profitAndLoss.cannot.exist"))
    }

    "not return errors when filing is not for CoHo and ACQ8161 is empty" in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(false))

      ACQ8161(None).validate(boxRetriever) shouldBe Set()
    }
  }
}
