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

package uk.gov.hmrc.ct.computations

import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import org.scalatest.{Matchers, WordSpec}
import uk.gov.hmrc.ct.CATO21
import uk.gov.hmrc.ct.box.CtValidation
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever

class CP89Spec extends WordSpec with Matchers with MockitoSugar {

  "CP89" should {

    "be mandatory if CPQ8 is false and CPAux2 + CP78 > CP672" in {

      val mockRetriever = setupRetriever()

      when(mockRetriever.retrieveCPQ8()).thenReturn(CPQ8(Some(false)))
      when(mockRetriever.retrieveCPAux2()).thenReturn(CPAux2(50))
      when(mockRetriever.retrieveCP78()).thenReturn(CP78(Some(50)))
      when(mockRetriever.retrieveCP672()).thenReturn(CP672(Some(50)))

      CP89(None).validate(mockRetriever) shouldBe Set(CtValidation(Some("CP89"), "error.CP89.mainPoolAllowanceRequired"))
    }

    "return no error if CPQ8 is false and CPAux2 + CP78 > CP672 and CP89 has a value of 0" in {

      val mockRetriever = setupRetriever()

      when(mockRetriever.retrieveCPQ8()).thenReturn(CPQ8(Some(false)))
      when(mockRetriever.retrieveCPAux2()).thenReturn(CPAux2(50))
      when(mockRetriever.retrieveCP78()).thenReturn(CP78(Some(50)))
      when(mockRetriever.retrieveCP672()).thenReturn(CP672(Some(50)))

      CP89(Some(0)).validate(mockRetriever) shouldBe Set.empty
    }

    "return a negative number error if CPQ8 is false and CPAux2 + CP78 > CP672 and CP89 has a negative value" in {

      val mockRetriever = setupRetriever()

      when(mockRetriever.retrieveCPQ8()).thenReturn(CPQ8(Some(false)))
      when(mockRetriever.retrieveCPAux2()).thenReturn(CPAux2(50))
      when(mockRetriever.retrieveCP78()).thenReturn(CP78(Some(50)))
      when(mockRetriever.retrieveCP672()).thenReturn(CP672(Some(50)))

      CP89(Some(-20)).validate(mockRetriever) shouldBe Set(CtValidation(Some("CP89"), "error.CP89.mustBeZeroOrPositive"))
    }

    "not be mandatory if CPQ8 is true" in {

      val mockRetriever = setupRetriever()

      when(mockRetriever.retrieveCPQ8()).thenReturn(CPQ8(Some(true)))
      when(mockRetriever.retrieveCPAux2()).thenReturn(CPAux2(50))
      when(mockRetriever.retrieveCP78()).thenReturn(CP78(Some(50)))
      when(mockRetriever.retrieveCP672()).thenReturn(CP672(Some(50)))

      CP89(None).validate(mockRetriever) shouldBe empty
    }

    "not be mandatory if CPQ8 is false and CPAux2 + CP78 is equal to then CP672" in {

      val mockRetriever = setupRetriever()

      when(mockRetriever.retrieveCPQ8()).thenReturn(CPQ8(Some(false)))
      when(mockRetriever.retrieveCPAux2()).thenReturn(CPAux2(25))
      when(mockRetriever.retrieveCP78()).thenReturn(CP78(Some(25)))
      when(mockRetriever.retrieveCP672()).thenReturn(CP672(Some(50)))

      CP89(None).validate(mockRetriever) shouldBe empty
    }

    "not be mandatory if CPQ8 is false and CP672 is greater then CPAux2 + CP78" in {

      val mockRetriever = setupRetriever()

      when(mockRetriever.retrieveCPQ8()).thenReturn(CPQ8(Some(false)))
      when(mockRetriever.retrieveCPAux2()).thenReturn(CPAux2(25))
      when(mockRetriever.retrieveCP78()).thenReturn(CP78(Some(25)))
      when(mockRetriever.retrieveCP672()).thenReturn(CP672(Some(100)))

      CP89(None).validate(mockRetriever) shouldBe empty
    }

    "no errors should be returned if CPQ8 is true and CP89 is negative" in {

      val mockRetriever = setupRetriever()

      when(mockRetriever.retrieveCPQ8()).thenReturn(CPQ8(Some(true)))
      when(mockRetriever.retrieveCPAux2()).thenReturn(CPAux2(50))
      when(mockRetriever.retrieveCP78()).thenReturn(CP78(Some(25)))
      when(mockRetriever.retrieveCP672()).thenReturn(CP672(Some(50)))

      CP89(-20).validate(mockRetriever) shouldBe empty
    }

  }

  private def setupRetriever(): ComputationsBoxRetriever = {
    val mockRetriever = mock[ComputationsBoxRetriever]

    when(mockRetriever.retrieveCP81()).thenReturn(CP81(0))
    when(mockRetriever.retrieveCP82()).thenReturn(CP82(0))
    when(mockRetriever.retrieveCP83()).thenReturn(CP83(0))
    when(mockRetriever.retrieveCP87()).thenReturn(CP87(0))
    when(mockRetriever.retrieveCP88()).thenReturn(CP88(0))
    when(mockRetriever.retrieveCPAux1()).thenReturn(CPAux1(0))
    when(mockRetriever.retrieveCATO21()).thenReturn(CATO21(0))

    mockRetriever
  }

}
