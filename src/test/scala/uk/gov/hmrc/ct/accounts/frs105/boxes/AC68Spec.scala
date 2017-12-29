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

package uk.gov.hmrc.ct.accounts.frs105.boxes

import org.mockito.Mockito._
import uk.gov.hmrc.ct.accounts.MockFrs105AccountsRetriever
import uk.gov.hmrc.ct.accounts.frs105.retriever.Frs105AccountsBoxRetriever
import uk.gov.hmrc.ct.accounts.validation.ValidateAssetsEqualSharesSpec

class AC68Spec extends ValidateAssetsEqualSharesSpec[Frs105AccountsBoxRetriever] with MockFrs105AccountsRetriever {

  override def addOtherBoxValue100Mock(mockRetriever: Frs105AccountsBoxRetriever) =
    when(mockRetriever.ac490()).thenReturn(AC490(Some(100)))

  override def addOtherBoxValueNoneMock(mockRetriever: Frs105AccountsBoxRetriever) =
    when(mockRetriever.ac490()).thenReturn(AC490(None))

  testAssetsEqualToSharesValidation(boxRetriever, filingAttributesBoxValueRetriever)("AC68", AC68.apply)
}
