import java.io.File
import java.io.InputStream

fun main(args: Array<String>)
{
    val joltages = ArrayList<Int>()
    File("C:\\Users\\Paolo\\VSCODE\\Advent_of_Code_2020\\Day 10\\Day_10_Input.txt").inputStream().bufferedReader().forEachLine{joltages.add(it.toInt())}

    // Part 1
    joltages.add(0)
    joltages.sort()
    joltages.add(joltages[joltages.lastIndex] + 3)
    var ones = 0
    var threes = 0
    for (i in 0..(joltages.lastIndex-1))
    {
        if (joltages[i+1] - joltages[i] == 1) ones++
        else if (joltages[i+1] - joltages[i] == 3) threes++
    }
    println("Part 1: The product of the number of one and three jolt differences is: " + (ones*threes))

    // Part 2
    var memo = LongArray(joltages.last())
    println("Part 2: There are this many possible combinations: " + countPossibilities(joltages, memo, joltages.last()))
}

fun countPossibilities(joltages: ArrayList<Int>, memo: LongArray, end: Int): Long
{
    var result: Long = 0
    if (end == 0) result = 1
    else if (joltages.contains(end) && memo[end-1] == (0).toLong())
    {
        memo[end-1] = (countPossibilities(joltages, memo, end-1) + countPossibilities(joltages, memo, end-2) + countPossibilities(joltages, memo, end-3))
        result = memo[end-1]
    }
    else if ((end-1) >= 0 && memo[end-1] != (0).toLong()) result = memo[end-1]
    else result = 0 
    return result
}