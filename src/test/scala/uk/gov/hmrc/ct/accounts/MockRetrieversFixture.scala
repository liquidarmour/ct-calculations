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

package uk.gov.hmrc.ct.accounts

import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import uk.gov.hmrc.ct.accounts.frs102.retriever._
import uk.gov.hmrc.ct.accounts.frs105.retriever.Frs105AccountsBoxRetriever
import uk.gov.hmrc.ct.accounts.frs10x.retriever.Frs10xAccountsBoxRetriever
import uk.gov.hmrc.ct.accounts.retriever.AccountsBoxRetriever
import uk.gov.hmrc.ct.box.retriever.FilingAttributesBoxValueRetriever
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever



trait MockAccountsRetriever extends MockitoSugar {
  val boxRetriever = mock[AccountsBoxRetriever]
  val filingAttributesBoxValueRetriever = mock[FilingAttributesBoxValueRetriever]
  when(boxRetriever.filingAttributesBoxValueRetriever).thenReturn(filingAttributesBoxValueRetriever)
}

trait MockComputationsRetriever extends MockitoSugar {
  val boxRetriever = mock[ComputationsBoxRetriever]
  val accountsBoxRetriever = mock[AccountsBoxRetriever]
  when(boxRetriever.accountsBoxRetriever).thenReturn(accountsBoxRetriever)
  val filingAttributesBoxValueRetriever = mock[FilingAttributesBoxValueRetriever]
  when(accountsBoxRetriever.filingAttributesBoxValueRetriever).thenReturn(filingAttributesBoxValueRetriever)
  when(boxRetriever.filingAttributesBoxValueRetriever).thenReturn(filingAttributesBoxValueRetriever)
}

trait MockFrs10xAccountsRetriever extends MockitoSugar {
  val boxRetriever = mock[Frs10xAccountsBoxRetriever]
  val filingAttributesBoxValueRetriever = mock[FilingAttributesBoxValueRetriever]
  when(boxRetriever.filingAttributesBoxValueRetriever).thenReturn(filingAttributesBoxValueRetriever)
}

trait MockFrs102AccountsRetriever extends MockitoSugar {
  val boxRetriever = mock[Frs102AccountsBoxRetriever]
  val filingAttributesBoxValueRetriever = mock[FilingAttributesBoxValueRetriever]
  when(boxRetriever.filingAttributesBoxValueRetriever).thenReturn(filingAttributesBoxValueRetriever)
}

trait MockFrsse2008AccountsRetriever extends MockitoSugar {
  val boxRetriever = mock[MockFrsse2008AccountsRetriever]
  val filingAttributesBoxValueRetriever = mock[FilingAttributesBoxValueRetriever]
  when(boxRetriever.filingAttributesBoxValueRetriever).thenReturn(filingAttributesBoxValueRetriever)
}

trait MockFrs105AccountsRetriever extends MockitoSugar {
  val boxRetriever = mock[Frs105AccountsBoxRetriever](RETURNS_SMART_NULLS)
  val filingAttributesBoxValueRetriever = mock[FilingAttributesBoxValueRetriever]
  when(boxRetriever.filingAttributesBoxValueRetriever).thenReturn(filingAttributesBoxValueRetriever)
}

trait MockAbridgedAccountsRetriever extends MockitoSugar {
  val boxRetriever = mock[AbridgedAccountsBoxRetriever]
  val filingAttributesBoxValueRetriever = mock[FilingAttributesBoxValueRetriever]
  when(boxRetriever.filingAttributesBoxValueRetriever).thenReturn(filingAttributesBoxValueRetriever)
}

trait MockFullAccountsRetriever extends MockitoSugar {
  val boxRetriever = mock[FullAccountsBoxRetriever]
  val filingAttributesBoxValueRetriever = mock[FilingAttributesBoxValueRetriever]
  when(boxRetriever.filingAttributesBoxValueRetriever).thenReturn(filingAttributesBoxValueRetriever)
}
