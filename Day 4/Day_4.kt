import java.io.File
import java.io.InputStream

fun main(args: Array<String>)
{
    // Import the batch file and store in an ArrayList
    val passports = ArrayList<String>()
    File("C:\\Users\\Paolo\\VSCODE\\Advent_of_Code_2020\\Day 4\\Day_4_Input.txt").inputStream().bufferedReader().forEachLine{passports.add(it)}
    
    val keys = arrayOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid")
    val optionalKey = "cid"
    var hashmap = HashMap<String, String>()
    var validPassports = 0
    var delimiter1 = " "
    var delimiter2 = ":"
    var i = 0

    while (i <= passports.lastIndex)
    {
        while (i <= passports.lastIndex && passports.get(i) != "")
        {
            var line = passports.get(i).split(delimiter1)
            for (j in line.indices)
            {
                var currentPair = line.get(j)
                hashmap.put(currentPair.substringBefore(delimiter2), currentPair.substringAfter(delimiter2))
            }
            i++
        }
        var fields = 0
        var missingFields = ArrayList<String>()
        for (k in keys.indices)
        {  
            if (hashmap.containsKey(keys.get(k))) fields++
            else missingFields.add(keys.get(k))
        }
        if (fields == keys.size || ((fields == keys.size - 1) && missingFields.contains(optionalKey))) validPassports++
        // Reset
        hashmap.clear()
        i++
    }
    println("Part 1: The number of valid passports is: " + validPassports)
}