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

package uk.gov.hmrc.ct

package object retriever {

  trait Frsse2008CompaniesHouseBoxRetriever extends BaseComposedBoxRetriever with Frsse2008Accounts

  trait Frs102CompaniesHouseBoxRetriever extends BaseComposedBoxRetriever with Frs102Accounts

  trait Frs105CompaniesHouseBoxRetriever extends BaseComposedBoxRetriever with Frs105Accounts

  type Frsse2008CompaniesHouseOnlyBoxRetriever = Frsse2008CompaniesHouseBoxRetriever

  type Frs102CompaniesHouseOnlyBoxRetriever = Frs102CompaniesHouseBoxRetriever

  type Frs105CompaniesHouseOnlyBoxRetriever = Frs105CompaniesHouseBoxRetriever

  trait CT600V2HmrcBoxRetriever extends BaseComposedBoxRetriever with V2CT600

  trait CT600V3HmrcBoxRetriever extends BaseComposedBoxRetriever with V3CT600
}
