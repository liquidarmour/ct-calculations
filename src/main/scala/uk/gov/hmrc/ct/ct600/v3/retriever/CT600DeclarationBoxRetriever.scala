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

package uk.gov.hmrc.ct.ct600.v3.retriever

import uk.gov.hmrc.ct.box.retriever.{BoxValues, BoxRetriever}
import uk.gov.hmrc.ct.ct600.v3.{B980, N092, B985, B975}

object CT600DeclarationBoxRetriever extends BoxValues[CT600DeclarationBoxRetriever]

trait CT600DeclarationBoxRetriever extends BoxRetriever {

  def retrieveB975(): B975

  def retrieveB985(): B985

  def retrieveB980(): B980

  def retrieveN092(): N092

}