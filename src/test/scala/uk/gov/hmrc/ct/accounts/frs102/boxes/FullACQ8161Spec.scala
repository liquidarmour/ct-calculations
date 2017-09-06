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

import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfterEach, Matchers, WordSpec}
import uk.gov.hmrc.ct.accounts.frs102.boxes.{AC13 => FullAC13}
import uk.gov.hmrc.ct.accounts.frs105.boxes._
import uk.gov.hmrc.ct.accounts.frs10x.boxes.ACQ8161
import uk.gov.hmrc.ct.accounts._
import uk.gov.hmrc.ct.box.CtValidation
import uk.gov.hmrc.ct.{CompaniesHouseFiling, HMRCFiling}


class FullACQ8161Spec
  extends WordSpec
    with MockitoSugar
    with Matchers
    with BeforeAndAfterEach
    with MockFullAccountsRetriever {

  override protected def beforeEach(): Unit = {
    super.beforeEach()

    when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
    when(accountsBoxRetriever.ac12()).thenReturn(AC12(Some(10)))
//    when(boxRetriever.ac13()).thenReturn(AC13(Some(10)))
//    when(boxRetriever.ac405()).thenReturn(AC405(Some(10)))
//    when(boxRetriever.ac406()).thenReturn(AC406(Some(10)))
//    when(boxRetriever.ac410()).thenReturn(AC410(Some(10)))
//    when(boxRetriever.ac411()).thenReturn(AC411(Some(10)))
//    when(boxRetriever.ac415()).thenReturn(AC415(Some(10)))
//    when(boxRetriever.ac416()).thenReturn(AC416(Some(10)))
//    when(boxRetriever.ac420()).thenReturn(AC420(Some(10)))
//    when(boxRetriever.ac421()).thenReturn(AC421(Some(10)))
//    when(boxRetriever.ac425()).thenReturn(AC425(Some(10)))
//    when(boxRetriever.ac426()).thenReturn(AC426(Some(10)))
    when(boxRetriever.ac34()).thenReturn(frs102.boxes.AC34(Some(10)))
    when(boxRetriever.ac35()).thenReturn(frs102.boxes.AC35(Some(10)))

//    when(mockFrs102AbridgedBoxRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
//    when(mockFrs102AbridgedBoxRetriever.ac16()).thenReturn(AC16(Some(10)))
//    when(mockFrs102AbridgedBoxRetriever.ac17()).thenReturn(AC17(Some(10)))
//    when(mockFrs102AbridgedBoxRetriever.ac18()).thenReturn(AC18(Some(10)))
//    when(mockFrs102AbridgedBoxRetriever.ac19()).thenReturn(AC19(Some(10)))
//    when(mockFrs102AbridgedBoxRetriever.ac20()).thenReturn(AC20(Some(10)))
//    when(mockFrs102AbridgedBoxRetriever.ac21()).thenReturn(AC21(Some(10)))
//    when(mockFrs102AbridgedBoxRetriever.ac26()).thenReturn(AC26(Some(10)))
//    when(mockFrs102AbridgedBoxRetriever.ac27()).thenReturn(AC27(Some(10)))
//    when(mockFrs102AbridgedBoxRetriever.ac28()).thenReturn(AC28(Some(10)))
//    when(mockFrs102AbridgedBoxRetriever.ac29()).thenReturn(AC29(Some(10)))
//    when(mockFrs102AbridgedBoxRetriever.ac30()).thenReturn(AC30(Some(10)))
//    when(mockFrs102AbridgedBoxRetriever.ac31()).thenReturn(AC31(Some(10)))
//    when(mockFrs102AbridgedBoxRetriever.ac34()).thenReturn(frs102.boxes.AC34(Some(10)))
//    when(mockFrs102AbridgedBoxRetriever.ac35()).thenReturn(frs102.boxes.AC35(Some(10)))
//    when(mockFrs102AbridgedBoxRetriever.ac36()).thenReturn(AC36(Some(10)))
//    when(mockFrs102AbridgedBoxRetriever.ac37()).thenReturn(AC37(Some(10)))
//    when(mockFrs102AbridgedBoxRetriever.ac5032()).thenReturn(AC5032(Some("asd")))

//    mockFrs102FullBoxRetriever = mock[Test102FullBoxRetriever]

    when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
    when(accountsBoxRetriever.ac12()).thenReturn(AC12(Some(10)))
    when(boxRetriever.ac13()).thenReturn(FullAC13(Some(10)))
    when(boxRetriever.ac14()).thenReturn(AC14(Some(10)))
    when(boxRetriever.ac15()).thenReturn(AC15(Some(10)))
    when(boxRetriever.ac16()).thenReturn(AC16(Some(10)))
    when(boxRetriever.ac17()).thenReturn(AC17(Some(10)))
    when(boxRetriever.ac18()).thenReturn(AC18(Some(10)))
    when(boxRetriever.ac19()).thenReturn(AC19(Some(10)))
    when(boxRetriever.ac20()).thenReturn(AC20(Some(10)))
    when(boxRetriever.ac21()).thenReturn(AC21(Some(10)))
    when(boxRetriever.ac26()).thenReturn(AC26(Some(10)))
    when(boxRetriever.ac27()).thenReturn(AC27(Some(10)))
    when(boxRetriever.ac28()).thenReturn(AC28(Some(10)))
    when(boxRetriever.ac29()).thenReturn(AC29(Some(10)))
    when(boxRetriever.ac30()).thenReturn(AC30(Some(10)))
    when(boxRetriever.ac31()).thenReturn(AC31(Some(10)))
    when(boxRetriever.ac34()).thenReturn(frs102.boxes.AC34(Some(10)))
    when(boxRetriever.ac35()).thenReturn(frs102.boxes.AC35(Some(10)))
    when(boxRetriever.ac36()).thenReturn(AC36(Some(10)))
    when(boxRetriever.ac37()).thenReturn(AC37(Some(10)))
    when(boxRetriever.ac5032()).thenReturn(AC5032(Some("asd")))
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

//    "not return errors when filing is CoHo only and ACQ8161 is false (frs102 abridged variant)" in {
//      when(mockFrs102AbridgedBoxRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
//      when(mockFrs102AbridgedBoxRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))
//      when(mockFrs102AbridgedBoxRetriever.ac16()).thenReturn(AC16(None))
//      when(mockFrs102AbridgedBoxRetriever.ac17()).thenReturn(AC17(None))
//      when(mockFrs102AbridgedBoxRetriever.ac18()).thenReturn(AC18(None))
//      when(mockFrs102AbridgedBoxRetriever.ac19()).thenReturn(AC19(None))
//      when(mockFrs102AbridgedBoxRetriever.ac20()).thenReturn(AC20(None))
//      when(mockFrs102AbridgedBoxRetriever.ac21()).thenReturn(AC21(None))
//      when(mockFrs102AbridgedBoxRetriever.ac26()).thenReturn(AC26(None))
//      when(mockFrs102AbridgedBoxRetriever.ac27()).thenReturn(AC27(None))
//      when(mockFrs102AbridgedBoxRetriever.ac28()).thenReturn(AC28(None))
//      when(mockFrs102AbridgedBoxRetriever.ac29()).thenReturn(AC29(None))
//      when(mockFrs102AbridgedBoxRetriever.ac30()).thenReturn(AC30(None))
//      when(mockFrs102AbridgedBoxRetriever.ac31()).thenReturn(AC31(None))
//      when(mockFrs102AbridgedBoxRetriever.ac34()).thenReturn(frs102.boxes.AC34(None))
//      when(mockFrs102AbridgedBoxRetriever.ac35()).thenReturn(frs102.boxes.AC35(None))
//      when(mockFrs102AbridgedBoxRetriever.ac36()).thenReturn(AC36(None))
//      when(mockFrs102AbridgedBoxRetriever.ac37()).thenReturn(AC37(None))
//      when(mockFrs102AbridgedBoxRetriever.ac5032()).thenReturn(AC5032(None))
//
//      ACQ8161(Some(false)).validate(mockFrs102AbridgedBoxRetriever) shouldBe Set()
//    }

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

    "return 'cannot exist' error when filing is for FRS102 full for CoHo Only and ACQ8161 is false" in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
      when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))

      ACQ8161(Some(false)).validate(boxRetriever) shouldBe Set(CtValidation(None, "error.profitAndLoss.cannot.exist"))
    }

    "return 'cannot exist' error when filing is for FRS102 full for CoHo Only and ACQ8161 is false with only non abridged fields populated" in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
      when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))
      when(boxRetriever.ac16()).thenReturn(AC16(None))
      when(boxRetriever.ac17()).thenReturn(AC17(None))
      when(boxRetriever.ac18()).thenReturn(AC18(None))
      when(boxRetriever.ac19()).thenReturn(AC19(None))
      when(boxRetriever.ac20()).thenReturn(AC20(None))
      when(boxRetriever.ac21()).thenReturn(AC21(None))
      when(boxRetriever.ac26()).thenReturn(AC26(None))
      when(boxRetriever.ac27()).thenReturn(AC27(None))
      when(boxRetriever.ac28()).thenReturn(AC28(None))
      when(boxRetriever.ac29()).thenReturn(AC29(None))
      when(boxRetriever.ac30()).thenReturn(AC30(None))
      when(boxRetriever.ac31()).thenReturn(AC31(None))
      when(boxRetriever.ac34()).thenReturn(frs102.boxes.AC34(None))
      when(boxRetriever.ac35()).thenReturn(frs102.boxes.AC35(None))
      when(boxRetriever.ac36()).thenReturn(AC36(None))
      when(boxRetriever.ac37()).thenReturn(AC37(None))
      when(boxRetriever.ac5032()).thenReturn(AC5032(None))


      ACQ8161(Some(false)).validate(boxRetriever) shouldBe Set(CtValidation(None, "error.profitAndLoss.cannot.exist"))
    }

    "not return errors when filing is not for CoHo and ACQ8161 is empty" in {
      when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(false))

      ACQ8161(None).validate(boxRetriever) shouldBe Set()
    }
  }
}
