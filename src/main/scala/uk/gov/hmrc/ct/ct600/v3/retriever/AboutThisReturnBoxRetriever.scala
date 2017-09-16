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

package uk.gov.hmrc.ct.ct600.v3.retriever

import uk.gov.hmrc.ct.accounts.retriever.AccountsBoxRetriever
import uk.gov.hmrc.ct.box.retriever.BoxRetriever
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever
import uk.gov.hmrc.ct.ct600.v3._
import uk.gov.hmrc.ct.ct600a.v3.retriever.CT600ABoxRetriever
import uk.gov.hmrc.ct.ct600e.v3.B115
import uk.gov.hmrc.ct.ct600e.v3.retriever.CT600EBoxRetriever
import uk.gov.hmrc.ct.ct600j.v3.B140

abstract class AboutThisReturnBoxRetriever(val accountsBoxRetriever: AccountsBoxRetriever,
                                           val computationsBoxRetriever: Option[ComputationsBoxRetriever],
                                           val ct600EBoxRetriever: Option[CT600EBoxRetriever],
                                           val ct600ABoxRetriever: Option[CT600ABoxRetriever]) extends BoxRetriever {

  def b30(): B30 = (computationsBoxRetriever.map ( br => B30(br.cp1()) ) orElse
                    ct600EBoxRetriever.map ( br => B30(br.e3())))
                    .getOrElse(throw new IllegalStateException(s"This box retriever [$this] does not have an AP start date."))

  def b35(): B35 = (computationsBoxRetriever.map ( br => B35(br.cp2()) ) orElse
                    ct600EBoxRetriever.map ( br => B35(br.e4())))
                    .getOrElse(throw new IllegalStateException(s"This box retriever [$this] does not have an AP end date."))

  def b40(): B40

  def b45(): B45 = B45.calculate(this)

  def b45Input(): B45Input

  def b50(): B50 = B50.calculate(accountsBoxRetriever)

  def b55(): B55

  def b65(): B65

  def b80A(): B80A

  def b85A(): B85A

  def b90A(): B90A

  def b95(): B95 = ct600ABoxRetriever.map ( br => B95(br.lpq01())).getOrElse(B95(false))

  def b115(): B115 = B115(ct600EBoxRetriever.nonEmpty)

  def b140(): B140 = B140(b65())
}
