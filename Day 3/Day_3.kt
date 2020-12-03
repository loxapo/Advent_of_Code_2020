import java.io.File
import java.io.InputStream

fun main(args: Array<String>)
{
    // Import the text file and storen the values in an ArrayList
    val input: InputStream = File("C:\\Users\\Paolo\\VSCODE\\Advent_of_Code_2020\\Day 3\\Day_3_Input.txt").inputStream()
    var pattern = ArrayList<String>()
    input.bufferedReader().forEachLine{pattern.add(it)}

    // Set horizontal and vertical step values
    val horizStep = 3
    val vertStep = 1

    // Go down the slope, starting at (0,0)
    var row = 0;
    var column = 0;
    val width = pattern.get(0).length;
    var treeCount = 0;
    while (row < pattern.lastIndex)
    {
        column = column + horizStep
        row = row + vertStep
        // Use mod operator to loop around the pattern
        if(pattern.get(row)[column % width] == '#') treeCount++
    }
    println("Part 1: We'll smash into this many trees going down the slope: " + treeCount)

}