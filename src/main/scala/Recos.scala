import java.util.Properties
import org.apache.spark._
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.mllib.recommendation.ALS
import org.apache.spark.mllib.recommendation.Rating

object Recos {
	def main(args: Array[String]){

		
		

		// Load and parse the data
		val sc = new SparkContext(new SparkConf().setMaster("local[2]").setAppName("ALS"))
			val data = sc.textFile("hdfs://localhost/user/cloudera/data/recommendations/data2.csv")
		val ratings = data.map(_.split(';') match { case Array(user, item, rate) =>
    		Rating(user.toInt, item.toInt, rate.toDouble)
 		})


		// Build the recommendation model using ALS
		val rank = 10
		val numIterations = 20
		val model = ALS.train(ratings, rank, numIterations, 0.01)

		// Evaluate the model on rating data
		val usersProducts = ratings.map { case Rating(user, product, rate) =>
  		(user, product)
		}
		val predictions = model.predict(usersProducts).map { case Rating(user, product, rate) => 
    		((user, product), rate)
  		}
		val ratesAndPreds = ratings.map { case Rating(user, product, rate) => 
  		((user, product), rate)}.join(predictions)
		val MSE = ratesAndPreds.map { case ((user, product), (r1, r2)) => 
  			val err = (r1 - r2)
  			err * err
		}.mean()
		println("Mean Squared Error = " + MSE)
		predictions.saveAsTextFile("hdfs://localhost/user/cloudera/data/recommendations/output/")




	}
}
