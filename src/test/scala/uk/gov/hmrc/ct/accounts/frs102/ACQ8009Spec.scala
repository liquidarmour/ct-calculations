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

import org.joda.time.LocalDate
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfterEach, Matchers, WordSpec}
import uk.gov.hmrc.ct.{CompaniesHouseFiling, HMRCFiling, MicroEntityFiling}
import uk.gov.hmrc.ct.accounts.{AC3, AC4, MockFrs10xDirectorsRetriever}
import uk.gov.hmrc.ct.accounts.frs10x.boxes.{AC8021, AC8023, ACQ8003, ACQ8009}
import uk.gov.hmrc.ct.box.CtValidation

class ACQ8009Spec
  extends WordSpec
    with Matchers
    with MockitoSugar
    with BeforeAndAfterEach
    with MockFrs10xDirectorsRetriever {

  override protected def beforeEach(): Unit = {
    when (accountsBoxRetriever.ac3()).thenReturn (AC3(new LocalDate(2015, 4, 6)) )
    when (accountsBoxRetriever.ac4()).thenReturn (AC4(new LocalDate(2016, 4, 5)) )

    // directors report enabled responses
    when (filingAttributesBoxValueRetriever.companiesHouseFiling()).thenReturn (CompaniesHouseFiling (true) )
    when (filingAttributesBoxValueRetriever.hmrcFiling()).thenReturn (HMRCFiling (true) )
    when (filingAttributesBoxValueRetriever.microEntityFiling()).thenReturn (MicroEntityFiling (true) )
    when (boxRetriever.ac8021()).thenReturn (AC8021 (Some (true) ) )
    when (boxRetriever.ac8023()).thenReturn (AC8023 (Some (true) ) )

    // no appointments response
    when (boxRetriever.acQ8003 () ).thenReturn (ACQ8003 (Some (false) ) )
    when (boxRetriever.acQ8009 () ).thenReturn (ACQ8009 (Some (false) ) )
    super.beforeEach()
  }




  "ACQ8009 should" should {

    "validate successfully when no errors present" in {

      val secretary = ACQ8009(Some(true))

      secretary.validate(boxRetriever) shouldBe empty
    }

    "validate as mandatory" in {

      val secretary = ACQ8009(None)

      secretary.validate(boxRetriever) shouldBe Set(CtValidation(Some("ACQ8009"), "error.ACQ8009.required", None))
    }

    "no validate if no directors report" in {

      when(boxRetriever.ac8021()).thenReturn(AC8021(None))
      when(boxRetriever.ac8023()).thenReturn(AC8023(Some(false)))

      val secretary = ACQ8009(None)

      secretary.validate(boxRetriever) shouldBe empty
    }
  }
}
