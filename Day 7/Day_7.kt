import java.io.File
import java.io.InputStream

fun recursion(list: ArrayList<String>, bag: String): Int
{
    var finalResult = 0
    var lineList = ArrayList<String>()
    if (bag != "no other")
    {
        loop@ for (i in list.indices)
        {
            if (list[i].substringBefore("bags").contains(bag)) 
            {
                lineList = ArrayList(list[i].substringAfter("contain").split(","))
                break@loop
            }
        }
        for (j in lineList.indices)
        {
            var num = 0
            if (lineList[j].trim().substringBefore(" ") != "no") num = lineList[j].trim().substringBefore(" ").toInt()
            var nextBag = lineList[j].trim().substringAfter(" ").substringBefore(" bag")
            var result = num + num*recursion(list, nextBag)
            finalResult += result
        }
    }
    return finalResult
}

fun main(args: Array<String>)
{
    val baggageList = ArrayList<String>()
    File("C:\\Users\\Paolo\\VSCODE\\Advent_of_Code_2020\\Day 7\\Day_7_Input.txt").inputStream().bufferedReader().forEachLine{baggageList.add(it)}
    
    var firstPass = HashMap<String, Int>()
    for (i in baggageList.indices)
    {
        var topBag = baggageList[i].substringBefore("bags")
        if (baggageList[i].substringAfter("bags").contains("shiny gold")) firstPass.put(topBag.trim(), 1)
    }
    var currentList = firstPass
    var masterList = HashMap(firstPass)
    var keepChecking = 1
    while (keepChecking > 0)
    {
        keepChecking = 0
        var nextList = HashMap<String, Int>()
        for ((b,v) in currentList)
        {
            for (k in baggageList.indices)
            {
                var topBag = baggageList[k].substringBefore("bags")
                if (baggageList[k].substringAfter("bags").contains(b)) 
                {
                    nextList.put(topBag.trim(), 1)
                    masterList.put(topBag.trim(), 1)
                    keepChecking++
                }
            }
        }
        currentList = nextList
    }
    println("Part 1: This many bags can eventually hold a shiny gold bag: " + masterList.size)

    // Part 2
    var totalBags = recursion(baggageList, "shiny gold")
    println("Part 2: This many individual bags are required: " + totalBags)

}