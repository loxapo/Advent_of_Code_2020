import java.io.File
import java.io.InputStream

fun main(args: Array<String>)
{
    val numbers = ArrayList<Long>()
    File("C:\\Users\\Paolo\\VSCODE\\Advent_of_Code_2020\\Day 9\\Day_9_Input.txt").inputStream().bufferedReader().forEachLine{numbers.add(it.toLong())}
    
    // Part 1
    val preambleLength = 25
    var invalidNum: Long = 0
    var invalidFlag = false

    for (i in preambleLength..numbers.lastIndex)
    {
        for (j in (i-preambleLength)..(i-2))
        {
            var continueFlag = false
            for (k in (j+1)..(i-1))
            {
                if (numbers[j] + numbers[k] == numbers[i]) 
                {
                    continueFlag = true
                    break
                }
                if (j == (i-2) && k == (i-1)) invalidFlag = true
            }
            if (continueFlag) break
        }
        if (invalidFlag) 
        {
            invalidNum = numbers[i]
            break
        }
    }
    println("Part 1: The first invalid number is: " + invalidNum)

    // Part 2
    var doneFlag = false
    var contigList = ArrayList<Long>()
    for (i in 0..(numbers.lastIndex-1))
    {
        var sum: Long = numbers[i]
        for (j in (i+1)..numbers.lastIndex)
        {
            sum += numbers[j]
            if (sum > invalidNum) break
            if (sum == invalidNum)
            {
                contigList = ArrayList(numbers.slice(i..j))
                doneFlag = true
            }
        }
        if (doneFlag) break
    }
    println("Part 2: The encryption weakness is: " + ((contigList.minOrNull()?:0)+(contigList.maxOrNull()?:0)))
}