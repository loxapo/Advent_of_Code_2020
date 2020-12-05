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
            val currentKey = keys.get(k)
            if (hashmap.containsKey(currentKey)) 
            {
                if (currentKey == "byr")
                {
                    if (hashmap.get(currentKey)?.toInt() in 1920..2002) 
                    {
                        fields++
                    }
                }
                else if (currentKey == "iyr")
                {
                    if (hashmap.get(currentKey)?.toInt() in 2010..2020) 
                    {
                        fields++
                    }
                }
                else if (currentKey == "eyr")
                {
                    if (hashmap.get(currentKey)?.toInt() in 2020..2030) 
                    {
                        fields++
                    }
                }
                else if (currentKey == "hgt")
                {
                    var value = hashmap.get(currentKey)
                    if (value?.substring(value.length-2) == "cm" && value.substring(0, value.length-2).toInt() in 150..193) 
                    {
                        fields++
                    }
                    else if (value?.substring(value.length-2) == "in" && value.substring(0, value.length-2).toInt() in 59..76) 
                    {
                        fields++
                    }
                }
                else if (currentKey == "hcl")
                {
                    var value = hashmap.get(currentKey)
                    if (value?.substring(0, 1) == "#" && value.substring(1).matches("^[a-f0-9]*$".toRegex())) 
                    {
                        fields++
                    }
                }
                else if (currentKey == "ecl")
                {
                    var value = hashmap.get(currentKey)
                    if(value == "amb" || value == "blu" || value == "brn" || value == "gry" || value == "grn" || value == "hzl" || value == "oth") 
                    {
                        fields++
                    }
                }
                else if (currentKey == "pid")
                {
                    var value = hashmap.get(currentKey)
                    if (value?.length == 9 && value.matches("^[0-9]*$".toRegex())) 
                    {
                        fields++
                    }
                }
                else if (currentKey == "cid")
                {
                    fields++
                }
            }
            else missingFields.add(keys.get(k))
        }
        if (fields == keys.size || ((fields == keys.size - 1) && missingFields.contains(optionalKey))) validPassports++
        // Reset
        hashmap.clear()
        i++
    }
    println("Part 2: The number of valid passports is: " + validPassports)
}