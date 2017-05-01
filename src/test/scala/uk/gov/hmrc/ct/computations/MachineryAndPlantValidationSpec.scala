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

package uk.gov.hmrc.ct.computations

import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}
import uk.gov.hmrc.ct._
import uk.gov.hmrc.ct.box.CtValidation
import uk.gov.hmrc.ct.computations.fixture.ComputationsTestFixture
import org.mockito.Mockito._

class MachineryAndPlantValidationSpec extends WordSpec with Matchers with MockitoSugar {

  "CP78 " should {
    "validate if present and non-negative or if not present, otherwise fail" in new ComputationsTestFixture {
      CP78(Some(0)).validate(computationsBoxRetriever) shouldBe Set()
      CP78(None).validate(computationsBoxRetriever) shouldBe Set()
      CP78(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP78"), errorMessageKey = "error.CP78.mustBeZeroOrPositive"))
    }
  }

  "CP666 " should {
    "validate if present and non-negative or if not present, otherwise fail" in new ComputationsTestFixture {
      CP666(Some(0)).validate(computationsBoxRetriever) shouldBe Set()
      CP666(None).validate(computationsBoxRetriever) shouldBe Set()
      CP666(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP666"), errorMessageKey = "error.CP666.mustBeZeroOrPositive"))
    }
  }

  "CP79" should {
    "validate if present and non-negative or if not present, otherwise fail" in new ComputationsTestFixture {
      withBox(CPQ8(Some(false)))
      CP79(Some(0)).validate(computationsBoxRetriever) shouldBe Set()
      CP79(None).validate(computationsBoxRetriever) shouldBe Set()
      CP79(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP79"), errorMessageKey = "error.CP79.mustBeZeroOrPositive"))
    }
    "fail cannot exist validation if CPQ8 is true" in new ComputationsTestFixture {
      withBox(CPQ8(Some(true)))
      CP79(Some(0)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP79"), errorMessageKey = "error.CP79.cannot.exist"))
      CP79(None).validate(computationsBoxRetriever) shouldBe Set()
      CP79(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP79"), errorMessageKey = "error.CP79.cannot.exist"),
                                                                     CtValidation(boxId = Some("CP79"), errorMessageKey = "error.CP79.mustBeZeroOrPositive"))
    }
  }

  "CP80" should {
    "validate if present and non-negative or if not present, otherwise fail" in new ComputationsTestFixture {
      CP80(Some(0)).validate(computationsBoxRetriever) shouldBe Set()
      CP80(None).validate(computationsBoxRetriever) shouldBe Set()
      CP80(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP80"), errorMessageKey = "error.CP80.mustBeZeroOrPositive"))
    }
  }

  "CP82 " should {
    "validate if present and non-negative or if not present, otherwise fail" in new ComputationsTestFixture {
      withBox(CPQ8(Some(false)))
      CP82(Some(0)).validate(computationsBoxRetriever) shouldBe Set()
      CP82(None).validate(computationsBoxRetriever) shouldBe Set()
      CP82(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP82"), errorMessageKey = "error.CP82.mustBeZeroOrPositive"))
    }
    "fail cannot exist validation if cpq8 is true" in new ComputationsTestFixture {
      withBox(CPQ8(Some(true)))
      CP82(Some(0)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP82"), errorMessageKey = "error.CP82.cannot.exist"))
      CP82(None).validate(computationsBoxRetriever) shouldBe Set()
      CP82(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP82"), errorMessageKey = "error.CP82.cannot.exist"),
                                                                     CtValidation(boxId = Some("CP82"), errorMessageKey = "error.CP82.mustBeZeroOrPositive"))
    }
  }

  "CP83 " should {
    "validate if present and non-negative or if not present, otherwise fail" in new ComputationsTestFixture {
      withBox(CPQ8(Some(false)))
      CP83(Some(0)).validate(computationsBoxRetriever) shouldBe Set()
      CP83(None).validate(computationsBoxRetriever) shouldBe Set()
      CP83(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP83"), errorMessageKey = "error.CP83.mustBeZeroOrPositive"))
    }
    "fail cannot exist if CPQ8 is true" in new ComputationsTestFixture {
      withBox(CPQ8(Some(true)))
      CP83(Some(0)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP83"), errorMessageKey = "error.CP83.cannot.exist"))
      CP83(None).validate(computationsBoxRetriever) shouldBe Set()
      CP83(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP83"), errorMessageKey = "error.CP83.cannot.exist"),
                                                                     CtValidation(boxId = Some("CP83"), errorMessageKey = "error.CP83.mustBeZeroOrPositive"))
    }
  }

  "CP674 " should {
    "validate if present and non-negative or if not present, otherwise fail" in new ComputationsTestFixture {
      CP674(Some(0)).validate(computationsBoxRetriever) shouldBe Set()
      CP674(None).validate(computationsBoxRetriever) shouldBe Set()
      CP674(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP674"), errorMessageKey = "error.CP674.mustBeZeroOrPositive"))
    }
  }

  "CP84 " should {
    "validate if present and non-negative or if not present, otherwise fail" in new ComputationsTestFixture {
      CP84(Some(0)).validate(computationsBoxRetriever) shouldBe Set()
      CP84(None).validate(computationsBoxRetriever) shouldBe Set()
      CP84(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP84"), errorMessageKey = "error.CP84.mustBeZeroOrPositive"))
    }
  }

  "CP252" should {
    "validate if present and non-negative or if not present, otherwise fail" in new ComputationsTestFixture {
      withBox(CPQ8(Some(false)))
      withBox(CP79(Some(100)))
      CP252(Some(0)).validate(computationsBoxRetriever) shouldBe Set()
      CP252(None).validate(computationsBoxRetriever) shouldBe Set()
      CP252(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP252"), errorMessageKey = "error.CP252.mustBeZeroOrPositive"))
    }
    "fail cannot exist if CPQ8 is true" in new ComputationsTestFixture {
      withBox(CPQ8(Some(true)))
      withBox(CP79(Some(100)))
      CP252(Some(0)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP252"), errorMessageKey = "error.CP252.cannot.exist"))
      CP252(None).validate(computationsBoxRetriever) shouldBe Set()
      CP252(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP252"), errorMessageKey = "error.CP252.mustBeZeroOrPositive"),
                                                                      CtValidation(boxId = Some("CP252"), errorMessageKey = "error.CP252.cannot.exist"))
    }
  }

  "CP673 " should {
    "validate if present and non-negative or if not present, otherwise fail" in new ComputationsTestFixture {
      CP673(Some(0)).validate(computationsBoxRetriever) shouldBe Set()
      CP673(None).validate(computationsBoxRetriever) shouldBe Set()
      CP673(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP673"), errorMessageKey = "error.CP673.mustBeZeroOrPositive"))
    }
  }

  "CP667 " should {
    "validate if present and non-negative or if not present, otherwise fail" in new ComputationsTestFixture {
      withBox(CPQ8(Some(false)))
      CP667(Some(0)).validate(computationsBoxRetriever) shouldBe Set()
      CP667(None).validate(computationsBoxRetriever) shouldBe Set()
      CP667(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP667"), errorMessageKey = "error.CP667.mustBeZeroOrPositive"))
    }
  }

  "CP672 " should {
    "validate if present and non-negative or if not present, otherwise fail" in new ComputationsTestFixture {
      withBox(CPQ8(Some(false)))
      CP672(Some(0)).validate(computationsBoxRetriever) shouldBe Set()
      CP672(None).validate(computationsBoxRetriever) shouldBe Set()
      CP672(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP672"), errorMessageKey = "error.CP672.mustBeZeroOrPositive"))
    }
  }

  "CP87Input, given is trading and first Year Allowance Not Greater Than Max FYA" should {
    "validate if present and non-negative, otherwise fail" in new ComputationsTestFixture {
      withBox(CPQ8(Some(false)))
      withBox(CP81(100))
      withBox(CPAux1(100))
      CP87Input(Some(0)).validate(computationsBoxRetriever) shouldBe Set()
      CP87Input(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP87Input"), errorMessageKey = "error.CP87Input.mustBeZeroOrPositive"))
      CP87Input(Some(201)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP87Input"), errorMessageKey = "error.CP87Input.firstYearAllowanceClaimExceedsAllowance", Some(List("200"))))
    }
  }

  "CP87Input, given is non-negative" should {
    "validate correctly when not greater than CP81  CPaux1" in new ComputationsTestFixture {
      withBox(CPQ8(Some(false)))
      withBox(CP79(Some(20)))
      withBox(CP80(Some(29)))
      withBox(CPAux1(51))

      when(computationsBoxRetriever.cp81()).thenCallRealMethod()

      CP87Input(Some(100)).validate(computationsBoxRetriever) shouldBe Set()
    }

    "fail validation when greater than CP81  CPaux1" in new ComputationsTestFixture {
      withBox(CPQ8(Some(false)))
      withBox(CP79(Some(20)))
      withBox(CP80(Some(29)))
      withBox(CPAux1(51))

      when(computationsBoxRetriever.cp81()).thenCallRealMethod()

      CP87Input(Some(101)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP87Input"), errorMessageKey = "error.CP87Input.firstYearAllowanceClaimExceedsAllowance", args = Some(Seq("100"))))
    }

    "validate because FYA defaults to 0 when not entered" in new ComputationsTestFixture {
      withBox(CPQ8(Some(true)))
      withBox(CP79(Some(20)))
      withBox(CP80(Some(29)))
      withBox(CPAux1(51))
      when(computationsBoxRetriever.cp81()).thenCallRealMethod()

      CP87Input(None).validate(computationsBoxRetriever) shouldBe Set()
    }

    "fail validation when trading but no value entered" in new ComputationsTestFixture {
      withBox(CPQ8(Some(false)))
      withBox(CP81(29))
      withBox(CPAux1(51))

      CP87Input(None).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP87Input"), errorMessageKey = "error.CP87Input.fieldMustHaveValueIfTrading"))
    }
    "validate when ceased trading but no value entered" in new ComputationsTestFixture {
      withBox(CPQ8(Some(true)))
      withBox(CP81(29))
      withBox(CPAux1(51))

      CP87Input(None).validate(computationsBoxRetriever) shouldBe Set()
    }
    "validate when ceased trading not set" in new ComputationsTestFixture {
      withBox(CPQ8(None))
      withBox(CP81(29))
      withBox(CPAux1(51))
      CP87Input(None).validate(computationsBoxRetriever) shouldBe Set()
    }
    "fails validation when negative" in new ComputationsTestFixture {
      withBox(CPQ8(Some(false)))
      withBox(CP81(29))
      withBox(CPAux1(51))

      CP87Input(-1).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP87Input"), errorMessageKey = "error.CP87Input.mustBeZeroOrPositive"))
    }
  }

  "CP88(annual investment allowance claimed)" should {

    "fail to validate when negative" in new ComputationsTestFixture {
      withBox(CPQ8(Some(false)))
      withBox(CP83(None))
      withBox(CATO02(0))
      CP88(Some(-1)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP88"), errorMessageKey = "error.CP88.mustBeZeroOrPositive"))
    }

    "validate correctly when not greater than the minimum of CATO02 (maxAIA) and CP83 (expenditureQualifyingAnnualInvestmentAllowance)" in new ComputationsTestFixture {
      withBox(CPQ8(Some(false)))
      withBox(CP83(Some(11)))
      withBox(CATO02(10))

      CP88(Some(10)).validate(computationsBoxRetriever) shouldBe Set()
    }

    "fails validation when greater than the minimum of CATO02 (maxAIA) and CP83 (expenditureQualifyingAnnualInvestmentAllowance)" in new ComputationsTestFixture {
      withBox(CPQ8(Some(false)))
      withBox(CP83(Some(11)))
      withBox(CATO02(10))

      CP88(Some(11)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP88"), errorMessageKey = "error.CP88.annualInvestmentAllowanceExceeded", args = Some(Seq("10"))))
    }

    "fails validation when CATO02 (maxAIA) is the minimum" in new ComputationsTestFixture {
      withBox(CPQ8(Some(false)))
      withBox(CP83(Some(11)))
      withBox(CATO02(10))

      CP88(Some(11)).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP88"), errorMessageKey = "error.CP88.annualInvestmentAllowanceExceeded", args = Some(Seq("10"))))
    }

    "fail validation when trading but no value entered" in new ComputationsTestFixture {
      withBox(CATO02(2))
      withBox(CP83(Some(10)))
      withBox(CPQ8(Some(false)))
      CP88(None).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP88"), errorMessageKey = "error.CP88.fieldMustHaveValueIfTrading"))
    }

    "validate when ceased trading but no value entered" in new ComputationsTestFixture {
      withBox(CATO02(2))
      withBox(CPQ8(Some(true)))
      withBox(CP83(Some(10)))

      CP88(None).validate(computationsBoxRetriever) shouldBe Set()
    }

    "validate when ceased trading not set" in new ComputationsTestFixture {
      withBox(CPQ8(None))
      withBox(CP83(Some(10)))
      withBox(CATO02(2))
      CP88(None).validate(computationsBoxRetriever) shouldBe Set()
    }

    "fails validation when negative" in new ComputationsTestFixture {
      withBox(CPQ8(Some(false)))
      withBox(CP83(Some(10)))
      withBox(CATO02(2))

      CP88(-1).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP88"), errorMessageKey = "error.CP88.mustBeZeroOrPositive"))
    }
  }

  "CP89 (Writing Down Allowance claimed from Main pool)" should {

    "validates correctly when not greater than MAX(0, MainPool% * ( CP78 (Main Pool brought forward) " +
    "+ CP82 (Additions Qualifying for Main Pool) + MainRatePool - CP672 (Proceed from Disposals from Main Pool) " +
    "+ UnclaimedAIA_FYA (Unclaimed FYA and AIA amounts)) - CATO-2730" in new ComputationsTestFixture {
      withBox(CPQ8(None))
      withBox(CP78(Some(2000)))
      withBox(CP79(Some(20)))
      withBox(CP80(Some(30)))
      withBox(CP82(Some(2000)))
      withBox(CP83(Some(50)))
      withBox(CP87(0))
      withBox(CP87Input(Some(50)))
      withBox(CP88(0))
      withBox(CP672(Some(1000)))
      withBox(CPAux1(0))
      withBox(CPAux2(0))
      withBox(CATO21(18))

      when(computationsBoxRetriever.cp81()).thenCallRealMethod()

      CP89(549).validate(computationsBoxRetriever) shouldBe Set()
      CP89(559).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP89"), errorMessageKey = "error.CP89.mainPoolAllowanceExceeded", Some(Seq("558"))))
    }

    "validates when greater than MAX(0, MainPool% * ( CP78 (Main Pool brought forward) " +
    "+ CP82 (Additions Qualifying for Main Pool) + MainRatePool - CP672 (Proceed from Disposals from Main Pool) " +
    "+ LEC14 (Unclaimed FYA and AIA amounts)))" in new ComputationsTestFixture {
      withBox(CPQ8(None))
      withBox(CP78(Some(100)))
      withBox(CP79(None))
      withBox(CP80(None))
      withBox(CP82(Some(100)))
      withBox(CP83(None))
      withBox(CP87(0))
      withBox(CP88(0))
      withBox(CP672(Some(100)))
      withBox(CPAux1(0))
      withBox(CPAux2(50))
      withBox(CATO21(10))
      withBox(CATO22(50))

      when(computationsBoxRetriever.cp81()).thenCallRealMethod()

      CP89(15).validate(computationsBoxRetriever) shouldBe Set()
      CP89(16).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP89"), errorMessageKey = "error.CP89.mainPoolAllowanceExceeded", Some(Seq("15"))))
    }

    "validated when CP672 is large enough to make the total -ve and any +ve claim is made" in new ComputationsTestFixture {

      withDefaults()

      withBox(CPQ8(None))
      withBox(CP78(Some(100)))
      withBox(CP82(Some(100)))
      withBox(CP672(Some(1000)))
      withBox(CPAux2(100))
      withBox(CATO21(10))

      when(computationsBoxRetriever.cp81()).thenCallRealMethod()

      CP89(0).validate(computationsBoxRetriever) shouldBe Set()
      CP89(1).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP89"), errorMessageKey = "error.CP89.mainPoolAllowanceExceeded", Some(Seq("0"))))
    }

    "validate when ceased trading but no value entered" in new ComputationsTestFixture {
      withDefaults()
      withBox(CPQ8(Some(true)))

      when(computationsBoxRetriever.cp81()).thenCallRealMethod()

      CP89(None).validate(computationsBoxRetriever) shouldBe Set()
    }

    "validate when ceased trading not set" in new ComputationsTestFixture {
      withDefaults()
      when(computationsBoxRetriever.cp81()).thenCallRealMethod()
      CP89(None).validate(computationsBoxRetriever) shouldBe Set()
    }

    "fails validation when negative" in new ComputationsTestFixture {
      withDefaults()
      withBox(CPQ8(Some(false)))

      when(computationsBoxRetriever.cp81()).thenCallRealMethod()
      CP89(-1).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP89"), errorMessageKey = "error.CP89.mustBeZeroOrPositive"))
    }
  }

  "(CP668) Writing Down Allowance claimed from Special rate pool" should {
    "validates correctly when not greater than MAX( 0, SpecialPool% * ( CP666 + CPaux3 - CP667) )" in new ComputationsTestFixture {
      withDefaults()
      withBox(CP666(Some(100)))
      withBox(CP667(100))
      withBox(CPAux3(100))
      withBox(CATO22(10))

      CP668(10).validate(computationsBoxRetriever) shouldBe Set()
      CP668(11).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP668"), errorMessageKey = "error.CP668.specialRatePoolAllowanceExceeded", Some(Seq("10"))))
    }

    "fails validation when CP667 is large enough to make the total -ve and any +ve claim is made" in new ComputationsTestFixture {
      withDefaults()
      withBox(CP666(Some(100)))
      withBox(CP667(1000))
      withBox(CPAux3(100))
      withBox(CATO22(10))

      CP668(0).validate(computationsBoxRetriever) shouldBe Set()
      CP668(1).validate(computationsBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP668"), errorMessageKey = "error.CP668.specialRatePoolAllowanceExceeded", Some(Seq("0"))))
    }

    "validate when ceased trading but no value entered" in new ComputationsTestFixture {
      withDefaults()
      withBox(CPQ8(Some(true)))

      CP668(None).validate(computationsBoxRetriever) shouldBe Set()
    }
    "validate when ceased trading not set" in new ComputationsTestFixture {
      withDefaults()
      CP668(None).validate(computationsBoxRetriever) shouldBe Set()
    }
  }
}
