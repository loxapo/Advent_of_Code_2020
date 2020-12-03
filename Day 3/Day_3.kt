import java.io.File
import java.io.InputStream

fun countTrees(pattern: ArrayList<String>, horizStep: Int, vertStep: Int): Long
{
    var row = 0;
    var column = 0;
    var width = pattern.get(0).length;
    var treeCount: Long = 0;
    while (row < pattern.lastIndex)
    {
        column = column + horizStep
        row = row + vertStep
        // Use mod operator to loop around the pattern
        if(pattern.get(row)[column % width] == '#') treeCount++
    }
    return treeCount
}

fun main(args: Array<String>)
{
    // Import the text file and storen the values in an ArrayList
    val input: InputStream = File("C:\\Users\\Paolo\\VSCODE\\Advent_of_Code_2020\\Day 3\\Day_3_Input.txt").inputStream()
    var pattern = ArrayList<String>()
    input.bufferedReader().forEachLine{pattern.add(it)}

    /****************** PART 1 ******************/

    val right3down1 = countTrees(pattern, 3, 1)
    println("Part 1: We'll smash into this many trees going down the slope: " + right3down1)

    /****************** PART 2 ******************/

    val productOfSmashedTrees = right3down1*countTrees(pattern, 1,1)*countTrees(pattern, 5, 1)*countTrees(pattern, 7, 1)*countTrees(pattern, 1, 2)
    println("Part 2: The product of all trees smashed going down each slope is: " + productOfSmashedTrees)
}