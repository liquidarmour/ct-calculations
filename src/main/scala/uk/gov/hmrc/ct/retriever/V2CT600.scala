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

package uk.gov.hmrc.ct.retriever

import uk.gov.hmrc.ct.accounts.approval.retriever.{CoHoAccountsApprovalBoxRetriever, HmrcAccountsApprovalBoxRetriever}
import uk.gov.hmrc.ct.ct600.v2.retriever.CT600BoxRetriever
import uk.gov.hmrc.ct.ct600a.v2.retriever.CT600ABoxRetriever
import uk.gov.hmrc.ct.ct600e.v2.retriever.CT600EBoxRetriever
import uk.gov.hmrc.ct.ct600j.v2.retriever.CT600JBoxRetriever

trait V2CT600 {

  def ct600BoxRetriever(): Option[CT600BoxRetriever]

  def ct600ABoxRetriever(): Option[CT600ABoxRetriever]

  def ct600EBoxRetriever(): Option[CT600EBoxRetriever]

  def ct600JBoxRetriever(): Option[CT600JBoxRetriever]

  def hmrcAccountsApprovalBoxRetriever(): Option[HmrcAccountsApprovalBoxRetriever]

}
