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

package uk.gov.hmrc.ct.box

import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}
import uk.gov.hmrc.ct.ct600.v3.retriever.RepaymentsBoxRetriever
import uk.gov.hmrc.ct.ct600.v3.validators.BankDetailsValidation
import uk.gov.hmrc.ct.ct600.v3.{B920, B925, B930, B935}

class BankDetailsValidationSpec extends WordSpec with Matchers with BankDetailsValidation with MockitoSugar {

  def decrypt(string: CtString): String = string.value

  "BankDetailsValidation" should {

    "validateAllFilledOrEmptyStrings" should {
      "pass if all strings non-empty" in {
        validateAllFilledOrEmptyStrings("testBox", Set("something", "something")) shouldBe Set()
      }

      "pass if all string empty" in {
        validateAllFilledOrEmptyStrings("testBox", Set("", "")) shouldBe Set()
      }

      "return error if mix of empty and non-empty" in {
        validateAllFilledOrEmptyStrings("testBox", Set("something", "")) shouldBe Set(CtValidation(Some("testBox"), "error.testBox.allornone"))
      }

      "validateAllFilledOrEmptyStringsForBankDetails" should {
        "return error if mixing empty and non-empty" in {
          val mockBoxRetriever = mock[RepaymentsBoxRetriever]
          when(mockBoxRetriever.retrieveB920()).thenReturn(B920(""))
          when(mockBoxRetriever.retrieveB925()).thenReturn(B925(""))
          when(mockBoxRetriever.retrieveB930()).thenReturn(B930(""))
          when(mockBoxRetriever.retrieveB935()).thenReturn(B935("something"))

          validateAllFilledOrEmptyStringsForBankDetails(mockBoxRetriever, "testBox")(decrypt) shouldBe Set(CtValidation(Some("testBox"), "error.testBox.allornone"))

          verify(mockBoxRetriever).retrieveB920()
          verify(mockBoxRetriever).retrieveB925()
          verify(mockBoxRetriever).retrieveB930()
          verify(mockBoxRetriever).retrieveB935()
          verifyNoMoreInteractions(mockBoxRetriever)
        }
      }

    }
  }

}
