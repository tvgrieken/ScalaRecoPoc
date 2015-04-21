name := "Recommendations"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies += "org.spark-project" % "spark-core_2.9.3" % "0.7.3"

libraryDependencies += "org.apache.spark" % "spark-mllib_2.10" % "1.1.0"

libraryDependencies += "com.twitter" %% "util-eval" % "[6.2.4,)"

libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "1.0.4"

libraryDependencies += "org.mongodb" % "mongo-java-driver" % "2.11.4"

libraryDependencies += "org.mongodb" % "mongo-hadoop-core" % "1.3.0"

resolvers ++= Seq(
	"Akka Repo" at "http://repo.akka.io/releases/",
	"Spray " at "http://repo.spray.cc/"
)
