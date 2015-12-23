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

package uk.gov.hmrc.ct.ct600.v3

import uk.gov.hmrc.ct.UTR
import uk.gov.hmrc.ct.box.{Linked, CtBoxIdentifier, CtString, Input}


case class B3(value: String) extends CtBoxIdentifier(name = "Unique Taxpayer Reference") with CtString

object B3 extends Linked[UTR, B3] {

  override def apply(source: UTR): B3 = B3(source.value)
}