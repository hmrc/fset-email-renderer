resolvers += MavenRepository("HMRC-open-artefacts-maven2", "https://open.artefacts.tax.service.gov.uk/maven2")
resolvers += Resolver.url("HMRC-open-artefacts-ivy2", url("https://open.artefacts.tax.service.gov.uk/ivy2"))(Resolver.ivyStylePatterns)
resolvers += Resolver.typesafeRepo("releases")


addSbtPlugin("org.playframework"  %  "sbt-plugin"             % "3.0.0")
addSbtPlugin("uk.gov.hmrc"        %  "sbt-auto-build"         % "3.20.0")
addSbtPlugin("uk.gov.hmrc"        %  "sbt-distributables"     % "2.4.0")

//use the scalastyle plugin
addSbtPlugin("org.scalastyle"     %% "scalastyle-sbt-plugin"  % "1.0.0" exclude("org.scala-lang.modules", "scala-xml_2.12"))
