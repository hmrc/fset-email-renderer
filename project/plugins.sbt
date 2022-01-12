resolvers += MavenRepository("HMRC-open-artefacts-maven2", "https://open.artefacts.tax.service.gov.uk/maven2")
resolvers += Resolver.url("HMRC-open-artefacts-ivy2", url("https://open.artefacts.tax.service.gov.uk/ivy2"))(Resolver.ivyStylePatterns)
resolvers += Resolver.typesafeRepo("releases")


addSbtPlugin("com.typesafe.play"  %  "sbt-plugin"             % "2.8.8")
addSbtPlugin("uk.gov.hmrc"        %  "sbt-artifactory"        % "1.13.0")
addSbtPlugin("uk.gov.hmrc"        %  "sbt-auto-build"         % "2.13.0")
addSbtPlugin("uk.gov.hmrc"        %  "sbt-distributables"     % "2.1.0")
addSbtPlugin("uk.gov.hmrc"        %  "sbt-git-versioning"     % "2.2.0")
addSbtPlugin("uk.gov.hmrc"        %  "sbt-settings"           % "4.7.0")

//use the scalastyle plugin
addSbtPlugin("org.scalastyle"     %% "scalastyle-sbt-plugin"  % "1.0.0")
