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

package uk.gov.hmrc.ct.accounts.approval.accountsApproval.accountsApproval

import org.scalatest.mockito.MockitoSugar
import org.scalatest.{Matchers, WordSpec}
import uk.gov.hmrc.ct.accounts.approval.boxes.AC8091
import uk.gov.hmrc.ct.accounts.retriever.AccountsBoxRetriever
import uk.gov.hmrc.ct.accounts.{AccountsFreeTextValidationFixture, MockAccountsRetriever}
import uk.gov.hmrc.ct.box.CtValidation

class AC8091Spec extends WordSpec
  with MockitoSugar
  with Matchers
  with MockAccountsRetriever
  with AccountsFreeTextValidationFixture[AccountsBoxRetriever] {


  "AC8091 validate" should {
    "return errors when AC8091 is empty" in {
      AC8091(None).validate(boxRetriever) shouldBe Set(CtValidation(Some("AC8091"), "error.AC8091.required"))
    }

    "return errors when AC8091 is false" in {
      AC8091(Some(false)).validate(boxRetriever) shouldBe Set(CtValidation(Some("AC8091"), "error.AC8091.required"))
    }

    "return value when AC8091 is true" in {
      AC8091(Some(true)).value shouldBe Some(true)
    }
  }
}
