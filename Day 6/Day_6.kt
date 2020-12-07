import java.io.File
import java.io.InputStream

fun main(args: Array<String>)
{
    val answerList = ArrayList<String>()
    File("C:\\Users\\Paolo\\VSCODE\\Advent_of_Code_2020\\Day 6\\Day_6_Input.txt").inputStream().bufferedReader().forEachLine{answerList.add(it)}
    
    var sum = 0
    var i = 0
    while (i <= answerList.lastIndex)
    {
        var map = HashMap<Char, Int>()
        val value = 1
        var groupSize = 0
        while(i <= answerList.lastIndex && answerList.get(i) != "")
        {
            groupSize++
            for(j in answerList.get(i).indices)
            {
                if (map.containsKey(answerList.get(i)[j]))
                {
                    map.put(answerList.get(i)[j], map.get(answerList.get(i)[j])!! + value)
                }
                else map.put(answerList.get(i)[j], value)
            }
            i++
        }
        sum += map.filterValues{it == groupSize}.size
        i++
    }
    println("Part 2: The sum is: " + sum)
}