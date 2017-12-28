# ct-calculations

[![Build Status](https://travis-ci.org/liquidarmour/ct-calculations.svg?branch=master)](https://travis-ci.org/liquidarmour/ct-calculations) [ ![Download](https://api.bintray.com/packages/liquid-armour/maven/ct-calculations/images/download.svg) ](https://bintray.com/liquid-armour/maven/ct-calculations/_latestVersion)


CT Calculations is a library that contains the domain needed to create a Corporations Tax return for HMRC. This includes implementations of Boxes and calculations required to produce a CT600, including CT600J, CT600A and Computations. 

At the moment this library supports a subset of Boxes and calculations but we are open to supporting the full set of available boxes. 

# Download ct-calculations
```scala
resolvers += Resolver.bintrayRepo("liquid-armour", "maven")

libraryDependencies += "liquidarmour" %% "ct-calculations" % "x.x.x"
```

# License
 
This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
