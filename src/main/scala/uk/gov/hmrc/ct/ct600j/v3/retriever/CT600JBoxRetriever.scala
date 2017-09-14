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

package uk.gov.hmrc.ct.ct600j.v3.retriever

import uk.gov.hmrc.ct.box.retriever.{BoxRetriever, FilingAttributesBoxValueRetriever}
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever
import uk.gov.hmrc.ct.ct600.v3.retriever.{AboutThisReturnBoxRetriever, CT600BoxRetriever}
import uk.gov.hmrc.ct.ct600e.v3.retriever.CT600EBoxRetriever
import uk.gov.hmrc.ct.ct600j.v3._

abstract class CT600JBoxRetriever(val aboutThisReturnBoxRetriever: Option[AboutThisReturnBoxRetriever],
                                  val computationsBoxRetriever: Option[ComputationsBoxRetriever],
                                  val cT600BoxRetriever: Option[CT600BoxRetriever],
                                  val cT600EBoxRetriever: Option[CT600EBoxRetriever],
                                  val filingAttributesBoxValueRetriever: FilingAttributesBoxValueRetriever) extends BoxRetriever {

  def j1(): J1 = (cT600BoxRetriever.map(br => J1(br.b1())) orElse
                  cT600EBoxRetriever.map( br => J1(br.e1().value)))
                  .getOrElse(throw new IllegalStateException("Could not the company name"))

  def j2(): J2 = (cT600BoxRetriever.map(br => J2(br.b3())) orElse
                  cT600EBoxRetriever.map(br => J2(br.e2().value)))
                  .getOrElse(J2(filingAttributesBoxValueRetriever.utr().value))

  def j3(): J3 = (aboutThisReturnBoxRetriever.map(br => J3(br.b30())) orElse
                  computationsBoxRetriever.map( br => J3(br.cp1().value)))
                  .getOrElse(throw new IllegalStateException("Could not get the AP start date."))

  def j4(): J4 = (aboutThisReturnBoxRetriever.map(br => J4(br.b35())) orElse
                  computationsBoxRetriever.map( br => J4(br.cp2().value)))
                  .getOrElse(throw new IllegalStateException("Could not get the AP end date."))

  def j5(): J5
  def j10(): J10
  def j15(): J15
  def j20(): J20
  def j25(): J25
  def j30(): J30
  def j35(): J35
  def j40(): J40
  def j45(): J45
  def j50(): J50

  def j5A(): J5A
  def j10A(): J10A
  def j15A(): J15A
  def j20A(): J20A
  def j25A(): J25A
  def j30A(): J30A
  def j35A(): J35A
  def j40A(): J40A
  def j45A(): J45A
  def j50A(): J50A
}
