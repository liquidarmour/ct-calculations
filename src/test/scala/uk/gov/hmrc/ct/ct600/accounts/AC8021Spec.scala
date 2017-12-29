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

package uk.gov.hmrc.ct.ct600.accounts

import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfterEach, Matchers, WordSpec}
import uk.gov.hmrc.ct.accounts.MockFrs10xAccountsRetriever
import uk.gov.hmrc.ct.accounts.frs10x.boxes.{Directors, _}
import uk.gov.hmrc.ct.box.CtValidation
import uk.gov.hmrc.ct.{CompaniesHouseFiling, HMRCFiling, MicroEntityFiling, StatutoryAccountsFiling}


class AC8021Spec extends WordSpec with MockitoSugar with Matchers with BeforeAndAfterEach with MockFrs10xAccountsRetriever {

  override def beforeEach(): Unit = {
    super.beforeEach()

    when(boxRetriever.directors()).thenReturn(Directors(List.empty))
    when(boxRetriever.acQ8003()).thenReturn(ACQ8003(Some(true)))
    when(boxRetriever.ac8033()).thenReturn(AC8033(Some("test")))
    when(boxRetriever.acQ8009()).thenReturn(ACQ8009(Some(true)))
    when(boxRetriever.ac8051()).thenReturn(AC8051(Some("test")))
    when(boxRetriever.ac8052()).thenReturn(AC8052(Some("test")))
    when(boxRetriever.ac8053()).thenReturn(AC8053(Some("test")))
    when(boxRetriever.ac8054()).thenReturn(AC8054(Some("test")))
    when(boxRetriever.ac8899()).thenReturn(AC8899(Some(true)))
  }

  "AC8021 validate" should {
    "for statutory CoHo Only filing" when {
      "return errors when AC8021 is empty" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(false))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(true))
        when(boxRetriever.ac8023()).thenReturn(AC8023(None))

        AC8021(None).validate(boxRetriever) shouldBe Set(CtValidation(Some("AC8021"), "error.AC8021.required"))
      }

      "not return errors when statutory filing is for CoHo and AC8021 is true" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(false))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(true))
        when(boxRetriever.ac8023()).thenReturn(AC8023(None))

        AC8021(Some(true)).validate(boxRetriever) shouldBe Set()
      }

      "not return errors when statutory filing is for CoHo and AC8021 is false" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(false))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(true))
        when(boxRetriever.ac8023()).thenReturn(AC8023(None))
        when(boxRetriever.directors()).thenReturn(Directors(List.empty))
        when(boxRetriever.acQ8003()).thenReturn(ACQ8003(None))
        when(boxRetriever.ac8033()).thenReturn(AC8033(None))
        when(boxRetriever.acQ8009()).thenReturn(ACQ8009(None))
        when(boxRetriever.ac8051()).thenReturn(AC8051(None))
        when(boxRetriever.ac8052()).thenReturn(AC8052(None))
        when(boxRetriever.ac8053()).thenReturn(AC8053(None))
        when(boxRetriever.ac8054()).thenReturn(AC8054(None))
        when(boxRetriever.ac8899()).thenReturn(AC8899(None))

        AC8021(Some(false)).validate(boxRetriever) shouldBe Set()
      }
    }

    "for micro entity CoHo Only filing" when {
      "return errors when AC8021 is empty" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(true))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(false))
        when(boxRetriever.ac8023()).thenReturn(AC8023(None))

        AC8021(None).validate(boxRetriever) shouldBe Set(CtValidation(Some("AC8021"), "error.AC8021.required"))
      }

      "not return errors when AC8021 is true" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(true))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(false))
        when(boxRetriever.ac8023()).thenReturn(AC8023(None))

        AC8021(Some(true)).validate(boxRetriever) shouldBe Set()
      }

      "not return errors when AC8021 is false" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(false))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(true))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(false))
        when(boxRetriever.ac8023()).thenReturn(AC8023(None))
        when(boxRetriever.directors()).thenReturn(Directors(List.empty))
        when(boxRetriever.acQ8003()).thenReturn(ACQ8003(None))
        when(boxRetriever.ac8033()).thenReturn(AC8033(None))
        when(boxRetriever.acQ8009()).thenReturn(ACQ8009(None))
        when(boxRetriever.ac8051()).thenReturn(AC8051(None))
        when(boxRetriever.ac8052()).thenReturn(AC8052(None))
        when(boxRetriever.ac8053()).thenReturn(AC8053(None))
        when(boxRetriever.ac8054()).thenReturn(AC8054(None))
        when(boxRetriever.ac8899()).thenReturn(AC8899(None))

        AC8021(Some(false)).validate(boxRetriever) shouldBe Set()
      }
    }

    "for statutory HMRC Only filing" when {
      "validate successfully when AC8021 is empty" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(false))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(false))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(true))
        when(boxRetriever.ac8023()).thenReturn(AC8023(None))

        AC8021(None).validate(boxRetriever) shouldBe Set()
      }

      "cannot exist when AC8021 is true" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(false))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(false))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(true))
        when(boxRetriever.ac8023()).thenReturn(AC8023(None))

        AC8021(Some(true)).validate(boxRetriever) shouldBe Set(CtValidation(Some("AC8021"), "error.AC8021.cannot.exist"))
      }

      "cannot exist when AC8021 is false" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(false))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(false))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(true))
        when(boxRetriever.ac8023()).thenReturn(AC8023(None))

        AC8021(Some(false)).validate(boxRetriever) shouldBe Set(CtValidation(Some("AC8021"), "error.AC8021.cannot.exist"))
      }
    }

    "for micro entity HMRC Only filing" when {
      "validate successfully when AC8021 is empty" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(false))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(true))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(false))
        when(boxRetriever.ac8023()).thenReturn(AC8023(None))

        AC8021(None).validate(boxRetriever) shouldBe Set()
      }

      "cannot exist when AC8021 is true" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(false))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(true))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(false))
        when(boxRetriever.ac8023()).thenReturn(AC8023(None))

        AC8021(Some(true)).validate(boxRetriever) shouldBe Set(CtValidation(Some("AC8021"), "error.AC8021.cannot.exist"))
      }

      "cannot exist when AC8021 is false" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(false))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(true))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(false))
        when(boxRetriever.ac8023()).thenReturn(AC8023(None))

        AC8021(Some(false)).validate(boxRetriever) shouldBe Set(CtValidation(Some("AC8021"), "error.AC8021.cannot.exist"))
      }
    }

    "for statutory Joint filing" when {
      "required error when AC8021 is empty" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(false))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(true))
        when(boxRetriever.ac8023()).thenReturn(AC8023(None))

        AC8021(None).validate(boxRetriever) shouldBe Set(CtValidation(Some("AC8021"), "error.AC8021.required"))
      }

      "validate successfully when AC8021 is true" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(false))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(true))
        when(boxRetriever.ac8023()).thenReturn(AC8023(None))

        AC8021(Some(true)).validate(boxRetriever) shouldBe Set()
      }

      "validate successfully when AC8021 is false" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(false))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(true))
        when(boxRetriever.ac8023()).thenReturn(AC8023(None))

        AC8021(Some(false)).validate(boxRetriever) shouldBe Set()
      }
    }

    "for micro entity Joint filing where AC8023 is true" when {
      "required when AC8021 is empty" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(true))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(false))
        when(boxRetriever.ac8023()).thenReturn(AC8023(Some(true)))

        AC8021(None).validate(boxRetriever) shouldBe Set(CtValidation(Some("AC8021"), "error.AC8021.required"))
      }

      "validate successfully when AC8021 is true" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(true))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(false))
        when(boxRetriever.ac8023()).thenReturn(AC8023(Some(true)))

        AC8021(Some(true)).validate(boxRetriever) shouldBe Set()
      }

      "validate successfully when AC8021 is false" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(true))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(false))
        when(boxRetriever.ac8023()).thenReturn(AC8023(Some(true)))

        AC8021(Some(false)).validate(boxRetriever) shouldBe Set()
      }
    }

    "for micro entity Joint filing where AC8023 is false" when {
      "validate successfully when AC8021 is empty" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(true))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(false))
        when(boxRetriever.ac8023()).thenReturn(AC8023(Some(false)))

        AC8021(None).validate(boxRetriever) shouldBe Set()
      }

      "cannot be true error when AC8021 is true" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(true))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(false))
        when(boxRetriever.ac8023()).thenReturn(AC8023(Some(false)))

        AC8021(Some(true)).validate(boxRetriever) shouldBe Set(CtValidation(Some("AC8021"), "error.AC8021.cannot.be.true"))
      }

      "validate successfully when AC8021 is false" in {
        when(filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
        when(filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn(HMRCFiling(true))
        when(filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn(MicroEntityFiling(true))
        when(filingAttributesBoxValueRetriever.statutoryAccountsFiling()).thenReturn(StatutoryAccountsFiling(false))
        when(boxRetriever.ac8023()).thenReturn(AC8023(Some(false)))

        AC8021(Some(false)).validate(boxRetriever) shouldBe Set()
      }
    }
  }
}
