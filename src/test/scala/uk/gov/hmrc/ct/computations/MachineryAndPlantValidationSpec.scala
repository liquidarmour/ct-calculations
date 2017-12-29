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

import org.scalatest.{Matchers, WordSpec}
import uk.gov.hmrc.ct.accounts.MockComputationsRetriever
import uk.gov.hmrc.ct.box.CtValidation
import uk.gov.hmrc.ct.mocks.MockComputationsBoxRetriever
import org.mockito.Mockito._
import uk.gov.hmrc.ct.{CATO02, CATO20, CATO21, CATO22}

class MachineryAndPlantValidationSpec extends WordSpec with Matchers {
  val stubBoxRetriever = MockComputationsBoxRetriever()

  "CP78 " should {
    "validate if present and non-negative or if not present, otherwise fail" in {
      CP78(Some(0)).validate(stubBoxRetriever) shouldBe Set()
      CP78(None).validate(stubBoxRetriever) shouldBe Set()
      CP78(Some(-1)).validate(stubBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP78"), errorMessageKey = "error.CP78.mustBeZeroOrPositive"))
    }
  }

  "CP666 " should {
    "validate if present and non-negative or if not present, otherwise fail" in {
      CP666(Some(0)).validate(stubBoxRetriever) shouldBe Set()
      CP666(None).validate(stubBoxRetriever) shouldBe Set()
      CP666(Some(-1)).validate(stubBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP666"), errorMessageKey = "error.CP666.mustBeZeroOrPositive"))
    }
  }

  "CP79" should {
    "validate if present and non-negative or if not present, otherwise fail" in {
      CP79(Some(0)).validate(stubBoxRetriever) shouldBe Set()
      CP79(None).validate(stubBoxRetriever) shouldBe Set()
      CP79(Some(-1)).validate(stubBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP79"), errorMessageKey = "error.CP79.mustBeZeroOrPositive"))
    }
  }

  "CP80" should {
    "validate if present and non-negative or if not present, otherwise fail" in {
      CP80(Some(0)).validate(stubBoxRetriever) shouldBe Set()
      CP80(None).validate(stubBoxRetriever) shouldBe Set()
      CP80(Some(-1)).validate(stubBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP80"), errorMessageKey = "error.CP80.mustBeZeroOrPositive"))
    }
  }

  "CP82 " should {
    "validate if present and non-negative or if not present, otherwise fail" in {
      CP82(Some(0)).validate(stubBoxRetriever) shouldBe Set()
      CP82(None).validate(stubBoxRetriever) shouldBe Set()
      CP82(Some(-1)).validate(stubBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP82"), errorMessageKey = "error.CP82.mustBeZeroOrPositive"))
    }
  }

  "CP83 " should {
    "validate if present and non-negative or if not present, otherwise fail" in {
      CP83(Some(0)).validate(stubBoxRetriever) shouldBe Set()
      CP83(None).validate(stubBoxRetriever) shouldBe Set()
      CP83(Some(-1)).validate(stubBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP83"), errorMessageKey = "error.CP83.mustBeZeroOrPositive"))
    }
  }

  "CP674 " should {
    "validate if present and non-negative or if not present, otherwise fail" in {
      CP674(Some(0)).validate(stubBoxRetriever) shouldBe Set()
      CP674(None).validate(stubBoxRetriever) shouldBe Set()
      CP674(Some(-1)).validate(stubBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP674"), errorMessageKey = "error.CP674.mustBeZeroOrPositive"))
    }
  }

  "CP84 " should {
    "validate if present and non-negative or if not present, otherwise fail" in {
      CP84(Some(0)).validate(stubBoxRetriever) shouldBe Set()
      CP84(None).validate(stubBoxRetriever) shouldBe Set()
      CP84(Some(-1)).validate(stubBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP84"), errorMessageKey = "error.CP84.mustBeZeroOrPositive"))
    }
  }

  "CP252" should {
    "validate if present and non-negative or if not present, otherwise fail" in {
      CP252(Some(0)).validate(stubBoxRetriever) shouldBe Set()
      CP252(None).validate(stubBoxRetriever) shouldBe Set()
      CP252(Some(-1)).validate(stubBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP252"), errorMessageKey = "error.CP252.mustBeZeroOrPositive"))
    }
  }

  "CP673 " should {
    "validate if present and non-negative or if not present, otherwise fail" in {
      CP673(Some(0)).validate(stubBoxRetriever) shouldBe Set()
      CP673(None).validate(stubBoxRetriever) shouldBe Set()
      CP673(Some(-1)).validate(stubBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP673"), errorMessageKey = "error.CP673.mustBeZeroOrPositive"))
    }
  }

  "CP667 " should {
    "validate if present and non-negative or if not present, otherwise fail" in {
      CP667(Some(0)).validate(stubBoxRetriever) shouldBe Set()
      CP667(None).validate(stubBoxRetriever) shouldBe Set()
      CP667(Some(-1)).validate(stubBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP667"), errorMessageKey = "error.CP667.mustBeZeroOrPositive"))
    }
  }

  "CP672 " should {
    "validate if present and non-negative or if not present, otherwise fail" in {
      CP672(Some(0)).validate(stubBoxRetriever) shouldBe Set()
      CP672(None).validate(stubBoxRetriever) shouldBe Set()
      CP672(Some(-1)).validate(stubBoxRetriever) shouldBe Set(CtValidation(boxId = Some("CP672"), errorMessageKey = "error.CP672.mustBeZeroOrPositive"))
    }
  }

  "CP87Input, given is trading and first Year Allowance Not Greater Than Max FYA" should {
    "validate if present and non-negative, otherwise fail" in {
      val stubTestComputationsRetriever = MockComputationsBoxRetriever(cpq8Param = Some(false))

      CP87Input(Some(0)).validate(stubTestComputationsRetriever) shouldBe Set()
      CP87Input(Some(-1)).validate(stubTestComputationsRetriever) shouldBe Set(CtValidation(boxId = Some("CP87Input"), errorMessageKey = "error.CP87Input.mustBeZeroOrPositive"))
    }
  }

  "CP87Input, given is non-negative" should {
    "validate correctly when not greater than CP81 CPaux1" in new MockComputationsRetriever {
      when(boxRetriever.cp81).thenReturn(CP81(100))
      when(boxRetriever.cpAux1()).thenReturn(CPAux1(100))

      when(boxRetriever.cp80).thenReturn(CP80(Some(29)))
      when(boxRetriever.cp79()).thenReturn(CP79(Some(20)))
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(Some(false)))

      CP87Input(Some(100)).validate(boxRetriever) shouldBe Set()
    }

    "fail validation when greater than CP81+ CPaux1" in new MockComputationsRetriever {
      when(boxRetriever.cp81).thenReturn(CP81(50))
      when(boxRetriever.cpAux1()).thenReturn(CPAux1(50))

      when(boxRetriever.cp80).thenReturn(CP80(Some(29)))
      when(boxRetriever.cp79()).thenReturn(CP79(Some(20)))
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(Some(false)))

      CP87Input(Some(101)).validate(boxRetriever) shouldBe Set(CtValidation(boxId = Some("CP87Input"), errorMessageKey = "error.CP87Input.firstYearAllowanceClaimExceedsAllowance", args = Some(Seq("100"))))
    }

    "validate because FYA defaults to 0 when not entered" in {
      val stubTestComputationsRetriever = MockComputationsBoxRetriever(
        cpq8Param = Some(true),
        cp79Param = Some(20),
        cp80Param = Some(29)
//        ,
//        cpAux1Param = 51
           )

      CP87Input(None).validate(stubTestComputationsRetriever) shouldBe Set()
    }

    "fail validation when trading but no value entered" in {
      val stubTestComputationsRetriever = MockComputationsBoxRetriever(cpq8Param = Some(false))

      CP87Input(None).validate(stubTestComputationsRetriever) shouldBe Set(CtValidation(boxId = Some("CP87Input"), errorMessageKey = "error.CP87Input.fieldMustHaveValueIfTrading"))
    }
    "validate when ceased trading but no value entered" in {
      val stubTestComputationsRetriever = MockComputationsBoxRetriever(cpq8Param = Some(true))

      CP87Input(None).validate(stubTestComputationsRetriever) shouldBe Set()
    }
    "validate when ceased trading not set" in {
      val stubTestComputationsRetriever = MockComputationsBoxRetriever()

      CP87Input(None).validate(stubTestComputationsRetriever) shouldBe Set()
    }
    "fails validation when negative" in {
      val stubTestComputationsRetriever = MockComputationsBoxRetriever(cpq8Param = Some(false))

      CP87Input(-1).validate(stubTestComputationsRetriever) shouldBe Set(CtValidation(boxId = Some("CP87Input"), errorMessageKey = "error.CP87Input.mustBeZeroOrPositive"))
    }
  }

  "CP88(annual investment allowance claimed)" should {

    "fail to validate when negative" in new MockComputationsRetriever {
//      val stubTestComputationsRetriever = MockComputationsBoxRetriever()
      when(boxRetriever.cp83).thenReturn(CP83(None))
      when(boxRetriever.cato02()).thenReturn(CATO02(0))
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(Some(false)))
      CP88(Some(-1)).validate(boxRetriever) shouldBe Set(CtValidation(boxId = Some("CP88"), errorMessageKey = "error.CP88.mustBeZeroOrPositive"))
    }

    "validate correctly when not greater than the minimum of CATO02 (maxAIA) and CP83 (expenditureQualifyingAnnualInvestmentAllowance)" in new MockComputationsRetriever {
//      val stubTestComputationsRetriever = MockComputationsBoxRetriever(
//        cp83Param = Some(11)
////        ,
////        cato02Param = 10
//      )
      when(boxRetriever.cp83).thenReturn(CP83(Some(11)))
      when(boxRetriever.cato02()).thenReturn(CATO02(10))
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(None))

      CP88(Some(10)).validate(boxRetriever) shouldBe Set()
    }

    "fails validation when greater than the minimum of CATO02 (maxAIA) and CP83 (expenditureQualifyingAnnualInvestmentAllowance)" in new MockComputationsRetriever {
      when(boxRetriever.cp83).thenReturn(CP83(Some(11)))
      when(boxRetriever.cato02()).thenReturn(CATO02(10))
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(None))

      CP88(Some(11)).validate(boxRetriever) shouldBe Set(CtValidation(boxId = Some("CP88"), errorMessageKey = "error.CP88.annualInvestmentAllowanceExceeded", args = Some(Seq("10"))))
    }

    "fails validation when CATO02 (maxAIA) is the minimum" in new MockComputationsRetriever {
      when(boxRetriever.cp83).thenReturn(CP83(Some(10)))
      when(boxRetriever.cato02()).thenReturn(CATO02(11))
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(None))

      CP88(Some(11)).validate(boxRetriever) shouldBe Set(CtValidation(boxId = Some("CP88"), errorMessageKey = "error.CP88.annualInvestmentAllowanceExceeded", args = Some(Seq("10"))))
    }

    "fail validation when trading but no value entered" in new MockComputationsRetriever {
      when(boxRetriever.cp83).thenReturn(CP83(Some(11)))
      when(boxRetriever.cato02()).thenReturn(CATO02(10))
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(Some(false)))

      CP88(None).validate(boxRetriever) shouldBe Set(CtValidation(boxId = Some("CP88"), errorMessageKey = "error.CP88.fieldMustHaveValueIfTrading"))
    }
    "validate when ceased trading but no value entered" in new MockComputationsRetriever {
      when(boxRetriever.cp83).thenReturn(CP83(Some(11)))
      when(boxRetriever.cato02()).thenReturn(CATO02(10))
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(Some(true)))

      CP88(None).validate(boxRetriever) shouldBe Set()
    }
    "validate when ceased trading not set" in new MockComputationsRetriever {
      when(boxRetriever.cp83).thenReturn(CP83(None))
      when(boxRetriever.cato02()).thenReturn(CATO02(0))
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(None))

      CP88(None).validate(boxRetriever) shouldBe Set()
    }
    "fails validation when negative" in new MockComputationsRetriever {
      when(boxRetriever.cp83).thenReturn(CP83(None))
      when(boxRetriever.cato02()).thenReturn(CATO02(0))
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(Some(false)))

      CP88(-1).validate(boxRetriever) shouldBe Set(CtValidation(boxId = Some("CP88"), errorMessageKey = "error.CP88.mustBeZeroOrPositive"))
    }
  }

  "CP89 (Writing Down Allowance claimed from Main pool)" should {

    "validates correctly when not greater than MAX(0, MainPool% * ( CP78 (Main Pool brought forward) " +
      "+ CP82 (Additions Qualifying for Main Pool) + MainRatePool - CP672 (Proceed from Disposals from Main Pool) " +
      "+ UnclaimedAIA_FYA (Unclaimed FYA and AIA amounts)) - CATO-2730" in new MockComputationsRetriever {
      when(boxRetriever.cp78).thenReturn(CP78(Some(2000)))
      when(boxRetriever.cp79).thenReturn(CP79(Some(20)))
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(None))
      when(boxRetriever.cp81).thenReturn(CP81(50))
      when(boxRetriever.cp82).thenReturn(CP82(Some(2000)))
      when(boxRetriever.cp83).thenReturn(CP83(None))
      when(boxRetriever.cp87).thenReturn(CP87(0))
      when(boxRetriever.cp88).thenReturn(CP88(None))
      when(boxRetriever.cp87Input()).thenReturn(CP87Input(Some(50)))
      when(boxRetriever.cp672()).thenReturn(CP672(Some(1000)))
      when(boxRetriever.cpAux1()).thenReturn(CPAux1(0))
      when(boxRetriever.cpAux2()).thenReturn(CPAux2(0))
      when(boxRetriever.cato21()).thenReturn(CATO21(18))

      CP89(549).validate(boxRetriever) shouldBe Set()
      CP89(550).validate(boxRetriever) shouldBe Set(CtValidation(boxId = Some("CP89"), errorMessageKey = "error.CP89.mainPoolAllowanceExceeded", Some(Seq("549"))))
    }

    "validates when greater than MAX(0, MainPool% * ( CP78 (Main Pool brought forward) " +
      "+ CP82 (Additions Qualifying for Main Pool) + MainRatePool - CP672 (Proceed from Disposals from Main Pool) " +
      "+ LEC14 (Unclaimed FYA and AIA amounts)))" in new MockComputationsRetriever {
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(None))
      when(boxRetriever.cp78).thenReturn(CP78(Some(100)))
      when(boxRetriever.cp79).thenReturn(CP79(None))
      when(boxRetriever.cp80).thenReturn(CP80(None))
      when(boxRetriever.cp81).thenReturn(CP81(0))
      when(boxRetriever.cp82).thenReturn(CP82(Some(100)))
      when(boxRetriever.cp83).thenReturn(CP83(None))
      when(boxRetriever.cp87).thenReturn(CP87(0))
      when(boxRetriever.cp88).thenReturn(CP88(None))
      when(boxRetriever.cp87Input()).thenReturn(CP87Input(Some(50)))
      when(boxRetriever.cp672()).thenReturn(CP672(Some(100)))
      when(boxRetriever.cpAux1()).thenReturn(CPAux1(0))
      when(boxRetriever.cpAux2()).thenReturn(CPAux2(50))
      when(boxRetriever.cato20()).thenReturn(CATO20(50))
      when(boxRetriever.cato21()).thenReturn(CATO21(10))

      CP89(15).validate(boxRetriever) shouldBe Set()
      CP89(16).validate(boxRetriever) shouldBe Set(CtValidation(boxId = Some("CP89"), errorMessageKey = "error.CP89.mainPoolAllowanceExceeded", Some(Seq("15"))))
    }

    "validated when CP672 is large enough to make the total -ve and any +ve claim is made" in new MockComputationsRetriever {
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(None))
      when(boxRetriever.cp78).thenReturn(CP78(Some(100)))
      when(boxRetriever.cp79).thenReturn(CP79(None))
      when(boxRetriever.cp80).thenReturn(CP80(None))
      when(boxRetriever.cp81).thenReturn(CP81(0))
      when(boxRetriever.cp82).thenReturn(CP82(Some(100)))
      when(boxRetriever.cp83).thenReturn(CP83(None))
      when(boxRetriever.cp87).thenReturn(CP87(0))
      when(boxRetriever.cp88).thenReturn(CP88(None))
      when(boxRetriever.cp87Input()).thenReturn(CP87Input(Some(50)))
      when(boxRetriever.cp672()).thenReturn(CP672(Some(1000)))
      when(boxRetriever.cpAux1()).thenReturn(CPAux1(0))
      when(boxRetriever.cpAux2()).thenReturn(CPAux2(100))
      when(boxRetriever.cato20()).thenReturn(CATO20(50))
      when(boxRetriever.cato21()).thenReturn(CATO21(10))


      CP89(0).validate(boxRetriever) shouldBe Set()
      CP89(1).validate(boxRetriever) shouldBe Set(CtValidation(boxId = Some("CP89"), errorMessageKey = "error.CP89.mainPoolAllowanceExceeded", Some(Seq("0"))))
    }

    "validate when ceased trading but no value entered" in new MockComputationsRetriever {
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(Some(true)))
      when(boxRetriever.cp78).thenReturn(CP78(Some(100)))
      when(boxRetriever.cp79).thenReturn(CP79(None))
      when(boxRetriever.cp80).thenReturn(CP80(None))
      when(boxRetriever.cp81).thenReturn(CP81(0))
      when(boxRetriever.cp82).thenReturn(CP82(Some(100)))
      when(boxRetriever.cp83).thenReturn(CP83(None))
      when(boxRetriever.cp87).thenReturn(CP87(0))
      when(boxRetriever.cp88).thenReturn(CP88(None))
      when(boxRetriever.cp87Input()).thenReturn(CP87Input(Some(50)))
      when(boxRetriever.cp672()).thenReturn(CP672(Some(1000)))
      when(boxRetriever.cpAux1()).thenReturn(CPAux1(0))
      when(boxRetriever.cpAux2()).thenReturn(CPAux2(100))
      when(boxRetriever.cato20()).thenReturn(CATO20(50))
      when(boxRetriever.cato21()).thenReturn(CATO21(10))

      CP89(None).validate(boxRetriever) shouldBe Set()
    }

    "validate when ceased trading not set" in new MockComputationsRetriever {
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(Some(true)))
      when(boxRetriever.cp78).thenReturn(CP78(Some(100)))
      when(boxRetriever.cp79).thenReturn(CP79(None))
      when(boxRetriever.cp80).thenReturn(CP80(None))
      when(boxRetriever.cp81).thenReturn(CP81(0))
      when(boxRetriever.cp82).thenReturn(CP82(Some(100)))
      when(boxRetriever.cp83).thenReturn(CP83(None))
      when(boxRetriever.cp87).thenReturn(CP87(0))
      when(boxRetriever.cp88).thenReturn(CP88(None))
      when(boxRetriever.cp87Input()).thenReturn(CP87Input(Some(50)))
      when(boxRetriever.cp672()).thenReturn(CP672(Some(1000)))
      when(boxRetriever.cpAux1()).thenReturn(CPAux1(0))
      when(boxRetriever.cpAux2()).thenReturn(CPAux2(100))
      when(boxRetriever.cato20()).thenReturn(CATO20(50))
      when(boxRetriever.cato21()).thenReturn(CATO21(10))

      CP89(None).validate(boxRetriever) shouldBe Set()
    }

    "fails validation when negative" in new MockComputationsRetriever {
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(Some(false)))
      when(boxRetriever.cp78).thenReturn(CP78(Some(100)))
      when(boxRetriever.cp79).thenReturn(CP79(None))
      when(boxRetriever.cp80).thenReturn(CP80(None))
      when(boxRetriever.cp81).thenReturn(CP81(0))
      when(boxRetriever.cp82).thenReturn(CP82(Some(100)))
      when(boxRetriever.cp83).thenReturn(CP83(None))
      when(boxRetriever.cp87).thenReturn(CP87(0))
      when(boxRetriever.cp88).thenReturn(CP88(None))
      when(boxRetriever.cp87Input()).thenReturn(CP87Input(Some(50)))
      when(boxRetriever.cp672()).thenReturn(CP672(Some(1000)))
      when(boxRetriever.cpAux1()).thenReturn(CPAux1(0))
      when(boxRetriever.cpAux2()).thenReturn(CPAux2(100))
      when(boxRetriever.cato20()).thenReturn(CATO20(50))
      when(boxRetriever.cato21()).thenReturn(CATO21(10))

      CP89(-1).validate(boxRetriever) shouldBe Set(CtValidation(boxId = Some("CP89"), errorMessageKey = "error.CP89.mustBeZeroOrPositive"))
    }
  }

  "(CP668) Writing Down Allowance claimed from Special rate pool" should {
     "validates correctly when not greater than MAX( 0, SpecialPool% * ( CP666 + CPaux3 - CP667) )" in new MockComputationsRetriever {
       when(boxRetriever.cpQ8()).thenReturn(CPQ8(None))
       when(boxRetriever.cp666()).thenReturn(CP666(Some(100)))
       when(boxRetriever.cp667()).thenReturn(CP667(Some(100)))
       when(boxRetriever.cpAux3()).thenReturn(CPAux3(100))
       when(boxRetriever.cato22()).thenReturn(CATO22(10))

       CP668(10).validate(boxRetriever) shouldBe Set()
       CP668(11).validate(boxRetriever) shouldBe Set(CtValidation(boxId = Some("CP668"), errorMessageKey = "error.CP668.specialRatePoolAllowanceExceeded", Some(Seq("10"))))
     }

    "fails validation when CP667 is large enough to make the total -ve and any +ve claim is made" in new MockComputationsRetriever {
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(None))
      when(boxRetriever.cp666()).thenReturn(CP666(Some(100)))
      when(boxRetriever.cp667()).thenReturn(CP667(Some(1000)))
      when(boxRetriever.cpAux3()).thenReturn(CPAux3(100))
      when(boxRetriever.cato22()).thenReturn(CATO22(10))

      CP668(0).validate(boxRetriever) shouldBe Set()
      CP668(1).validate(boxRetriever) shouldBe Set(CtValidation(boxId = Some("CP668"), errorMessageKey = "error.CP668.specialRatePoolAllowanceExceeded", Some(Seq("0"))))
    }

    "validate when ceased trading but no value entered" in new MockComputationsRetriever {
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(Some(true)))
      when(boxRetriever.cp666()).thenReturn(CP666(Some(100)))
      when(boxRetriever.cp667()).thenReturn(CP667(Some(1000)))
      when(boxRetriever.cpAux3()).thenReturn(CPAux3(100))
      when(boxRetriever.cato22()).thenReturn(CATO22(10))

      val stubTestComputationsRetriever = MockComputationsBoxRetriever(cpq8Param = Some(true))

      CP668(None).validate(boxRetriever) shouldBe Set()
    }
    "validate when ceased trading not set" in new MockComputationsRetriever {
      when(boxRetriever.cpQ8()).thenReturn(CPQ8(None))
      when(boxRetriever.cp666()).thenReturn(CP666(Some(100)))
      when(boxRetriever.cp667()).thenReturn(CP667(Some(1000)))
      when(boxRetriever.cpAux3()).thenReturn(CPAux3(100))
      when(boxRetriever.cato22()).thenReturn(CATO22(10))

      CP668(None).validate(boxRetriever) shouldBe Set()
    }
  }
}
