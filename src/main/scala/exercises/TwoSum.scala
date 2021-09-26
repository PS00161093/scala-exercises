package exercises

/**
 * Problem URL: https://leetcode.com/problems/two-sum/
 *
 * @author Prashant Sharma
 */

object TwoSum extends App {

    def twoSum(nums: Array[Int], target: Int): Array[Int] = {
        val map = scala.collection.mutable.Map[Int, Int]()
        (for {
            pair@(n, i) <- nums.zipWithIndex
            if map.contains(target - n) || {
                map += pair
                false
            }
            j <- map.get(target - n)
        } yield Array(j, i)).flatten
    }

    println(twoSum(Array(3, 2, 3), 6).mkString("Array(", ", ", ")"))
}