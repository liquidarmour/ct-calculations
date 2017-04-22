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
import org.scalatest.mock.MockitoSugar
import uk.gov.hmrc.ct.accounts.frs10x.boxes.{AC8021, AC8023, ACQ8003, ACQ8009}
import uk.gov.hmrc.ct.accounts.frs10x.retriever.Frs10xDirectorsBoxRetriever
import uk.gov.hmrc.ct.accounts.retriever.AccountsBoxRetriever
import uk.gov.hmrc.ct.accounts.{AC3, AC4}
import uk.gov.hmrc.ct.box.retriever.FilingAttributesBoxValueRetriever
import uk.gov.hmrc.ct.{CompaniesHouseFiling, HMRCFiling, MicroEntityFiling}

object DirectorsMockSetup extends MockitoSugar {

  def setupDefaults(mockBoxRetriever: Frs10xDirectorsBoxRetriever) = {
    val accountsBoxRetriever = mock[AccountsBoxRetriever]
    val filingAttributesBoxRetriever = mock[FilingAttributesBoxValueRetriever]

    when(mockBoxRetriever.accountsBoxRetriever).thenReturn(accountsBoxRetriever)
    when(mockBoxRetriever.filingAttributesBoxRetriever).thenReturn(filingAttributesBoxRetriever)

    // POA responses
    when (accountsBoxRetriever.ac3()).thenReturn (AC3(new LocalDate(2015, 4, 6)) )
    when (accountsBoxRetriever.ac4()).thenReturn (AC4(new LocalDate(2016, 4, 5)) )

    // directors report enabled responses
    when (mockBoxRetriever.filingAttributesBoxRetriever.companiesHouseFiling()).thenReturn (CompaniesHouseFiling (true) )
    when (mockBoxRetriever.filingAttributesBoxRetriever.hmrcFiling()).thenReturn (HMRCFiling (true) )
    when (mockBoxRetriever.filingAttributesBoxRetriever.microEntityFiling()).thenReturn (MicroEntityFiling (true) )
    when (mockBoxRetriever.ac8021()).thenReturn (AC8021 (Some (true) ) )
    when (mockBoxRetriever.ac8023()).thenReturn (AC8023 (Some (true) ) )

    // no appointments response
    when (mockBoxRetriever.acQ8003 () ).thenReturn (ACQ8003 (Some (false) ) )
    when (mockBoxRetriever.acQ8009 () ).thenReturn (ACQ8009 (Some (false) ) )
  }
}
